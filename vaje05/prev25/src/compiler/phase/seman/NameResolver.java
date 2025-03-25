package compiler.phase.seman;

import java.util.*;

import compiler.common.report.*;
import compiler.phase.abstr.*;
import compiler.phase.abstr.AST.*;

/**
 * Name resolver.
 * 
 * The name resolver connects each node of a abstract syntax tree where a name
 * is used with the node where it is defined. The only exceptions are struct and
 * union component names which are connected with their definitions by the type
 * resolver. The results of the name resolver are stored in
 * {@link compiler.phase.seman.SemAn#defAt}.
 * 
 * @author bostjan.slivnik@fri.uni-lj.si
 */
public class NameResolver implements AST.FullVisitor<Object, NameResolver.Mode> {

	/** Constructs a new name resolver. */
	public NameResolver() {
	}

	/** Two passes of name resolving. */
	protected enum Mode {
		/** The first pass: declaring names. */
		DECLARE,
		/** The second pass: resolving names. */
		RESOLVE,
	}

    private void declare(String name, Defn defn) {
        try{
			symbTable.ins(name, defn);
		}catch(NameResolver.SymbTable.CannotInsNameException e){
			throw new Report.Error(defn.location(), "Double declaration: " + name);
		}
		
        return;
    }

	private AST.Defn resolveName(String name, NameType node) {
		try{
    		SemAn.defAt.put(node, symbTable.fnd(name));

		}catch(NameResolver.SymbTable.CannotFndNameException e){
			throw new Report.Error(node.location(), "Cannot find declaration: " + name);
		}
		return null;
	}

	private AST.Defn resolveNameExpr(String name, NameExpr node) {
		try{
    		SemAn.defAt.put(node, symbTable.fnd(name));

		}catch(NameResolver.SymbTable.CannotFndNameException e){
			throw new Report.Error(node.location(), "Cannot find declaration: " + name);
		}
		return null;
	}

	/** The symbol table. */
	private SymbTable symbTable = new SymbTable();

	@Override
	public Object visit(Nodes<? extends Node> nodes, NameResolver.Mode arg) {
		for (final Node node : nodes){
			node.accept(this, NameResolver.Mode.DECLARE);

		}
		for (final Node node : nodes){
			node.accept(this, NameResolver.Mode.RESOLVE);
		}

		return null;
	}

	// VISIT METODE 
	@Override
	public Object visit(TypDefn typDefn, Mode arg) {
		if(arg == Mode.DECLARE){
			//Report.info("Declaring " + typDefn.name);
			declare(typDefn.name, typDefn);
		}else{
			//Report.info("HALLO :DDD");
			typDefn.type.accept(this, arg);
		}
		
		return null;
	}

	@Override
	public Object visit(VarDefn varDefn, Mode arg) {
		if(arg == Mode.DECLARE){
			declare(varDefn.name, varDefn);
		}if(arg == Mode.RESOLVE){
			varDefn.type.accept(this, arg);
		}
		return null;
	}

	public Object visit(DefFunDefn defFunDefn, Mode arg) {
		if (arg == Mode.DECLARE) {
			declare(defFunDefn.name, defFunDefn);	
		}
		symbTable.newScope();
		defFunDefn.pars.accept(this, arg);
		if (arg == Mode.RESOLVE) {
			defFunDefn.type.accept(this, arg);
			defFunDefn.stmts.accept(this, arg);
		}
		symbTable.oldScope();
		
		return null;
		
	}

	@Override
	public Object visit(ExtFunDefn extFunDefn, Mode arg) {

		if(arg == Mode.DECLARE){
			declare(extFunDefn.name, extFunDefn);
		}
		symbTable.newScope();

		extFunDefn.pars.accept(this, arg);
		if(arg == Mode.RESOLVE){
			extFunDefn.type.accept(this, arg);
			symbTable.oldScope();
		}
		
		return null;
	}


	@Override
	public Object visit(ParDefn parDefn, Mode arg) {
		if(arg==Mode.DECLARE){
			declare(parDefn.name, parDefn);
		}if(arg == Mode.RESOLVE){
			//resolve(parDefn.name, parDefn);
		}
		parDefn.type.accept(this, arg);

    	return null;
	}

	@Override
	public Object visit(CompDefn compDefn, Mode arg) {
		if(arg == Mode.DECLARE){
			declare(compDefn.name, compDefn);
		}else if(arg == Mode.RESOLVE){
			//resolve(compDefn.name, compDefn);
			compDefn.type.accept(this, arg);
		}
		return null;
	}
	// ----- Statements -----
	//nov scope, pol 

	@Override
	public Object visit(LetStmt letStmt, Mode arg) {
	symbTable.newScope();
		if (arg == Mode.RESOLVE) {
			for (Node node : letStmt.defns) {
				if (node instanceof TypDefn) {
					node.accept(this, Mode.DECLARE);
				}
			}
			for (Node node : letStmt.defns) {
				if (!(node instanceof TypDefn)) {
					node.accept(this, Mode.DECLARE);
				}
			}
		
			for (Node node : letStmt.defns) {
				node.accept(this, Mode.RESOLVE);
			}
			letStmt.stmts.accept(this, arg);
			symbTable.oldScope();

		}

		return null;
	}

	@Override
	public Object visit(AssignStmt assignStmt, Mode arg) {
		assignStmt.dstExpr.accept(this, arg);
		assignStmt.srcExpr.accept(this, arg);
	
		
		return null;
	}

	@Override
	public Object visit(ExprStmt callStmt, Mode arg) {
		if ((callStmt.expr != null) || (!compiler.Compiler.devMode()))
			callStmt.expr.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(IfThenStmt ifThenStmt, Mode arg) {
		if ((ifThenStmt.condExpr != null) || (!compiler.Compiler.devMode()))
			ifThenStmt.condExpr.accept(this, arg);
		if ((ifThenStmt.thenStmt != null) || (!compiler.Compiler.devMode()))
			ifThenStmt.thenStmt.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(IfThenElseStmt ifThenElseStmt, Mode arg) {
		if ((ifThenElseStmt.condExpr != null) || (!compiler.Compiler.devMode()))
			ifThenElseStmt.condExpr.accept(this, arg);
		if ((ifThenElseStmt.thenStmt != null) || (!compiler.Compiler.devMode()))
			ifThenElseStmt.thenStmt.accept(this, arg);
		if ((ifThenElseStmt.elseStmt != null) || (!compiler.Compiler.devMode()))
			ifThenElseStmt.elseStmt.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(ReturnStmt returnStmt, Mode arg) {
		if ((returnStmt.retExpr != null) || (!compiler.Compiler.devMode()))
			returnStmt.retExpr.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(WhileStmt whileStmt, Mode arg) {
		if ((whileStmt.condExpr != null) || (!compiler.Compiler.devMode()))
			whileStmt.condExpr.accept(this, arg);
		if ((whileStmt.stmts != null) || (!compiler.Compiler.devMode()))
			whileStmt.stmts.accept(this, arg);
		return null;
	}

	// ----- Types -----

	@Override
	public Object visit(AtomType atomType, Mode arg) {
		return null;
	}

	@Override
	public Object visit(ArrType arrType, Mode arg) {
		if ((arrType.elemType != null) || (!compiler.Compiler.devMode()))
			arrType.elemType.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(PtrType ptrType, Mode arg) {
		if ((ptrType.baseType != null) || (!compiler.Compiler.devMode()))
			ptrType.baseType.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(StrType strType, Mode arg) {
		if(arg == Mode.RESOLVE){
			symbTable.newScope();
			strType.comps.accept(this, arg);
			symbTable.oldScope();
		}
		
		return null;
	}

	@Override
	public Object visit(UniType uniType, Mode arg) {

		if(arg == Mode.RESOLVE){
			symbTable.newScope();
			uniType.comps.accept(this, arg);
			symbTable.oldScope();
		}
		return null;
	}

	@Override
	public Object visit(FunType funType, Mode arg) {
		if ((funType.parTypes != null) || (!compiler.Compiler.devMode()))
			funType.parTypes.accept(this, arg);
		if ((funType.resType != null) || (!compiler.Compiler.devMode()))
			funType.resType.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(NameType nameType, Mode arg) {
		if(arg == Mode.DECLARE){
			//declareName(nameType.name, nameType);
		}
		if(arg == Mode.RESOLVE){
			resolveName(nameType.name, nameType);
		}
		return null;
	}

	// ----- Expressions -----

	@Override
	public Object visit(ArrExpr arrExpr, Mode arg) {
		if ((arrExpr.arrExpr != null) || (!compiler.Compiler.devMode()))
			arrExpr.arrExpr.accept(this, arg);
		if ((arrExpr.idx != null) || (!compiler.Compiler.devMode()))
			arrExpr.idx.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AtomExpr atomExpr, Mode arg) {
		return null;
	}

	@Override
	public Object visit(BinExpr binExpr, Mode arg) {
		if ((binExpr.fstExpr != null) || (!compiler.Compiler.devMode()))
			binExpr.fstExpr.accept(this, arg);
		if ((binExpr.sndExpr != null) || (!compiler.Compiler.devMode()))
			binExpr.sndExpr.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(CallExpr callExpr, Mode arg) {
		if ((callExpr.funExpr != null) || (!compiler.Compiler.devMode()))
			callExpr.funExpr.accept(this, arg);
		if ((callExpr.argExprs != null) || (!compiler.Compiler.devMode()))
			callExpr.argExprs.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(CastExpr castExpr, Mode arg) {
		if ((castExpr.type != null) || (!compiler.Compiler.devMode()))
			castExpr.type.accept(this, arg);
		if ((castExpr.expr != null) || (!compiler.Compiler.devMode()))
			castExpr.expr.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(CompExpr compExpr, Mode arg) {
		if ((compExpr.recExpr != null) || (!compiler.Compiler.devMode()))
			compExpr.recExpr.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(NameExpr nameExpr, Mode arg) {
		if(arg == Mode.RESOLVE){
			resolveNameExpr(nameExpr.name, nameExpr);
		}
		return null;
	}

	@Override
	public Object visit(PfxExpr pfxExpr, Mode arg) {
		if ((pfxExpr.subExpr != null) || (!compiler.Compiler.devMode()))
			pfxExpr.subExpr.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(SfxExpr sfxExpr, Mode arg) {
		if ((sfxExpr.subExpr != null) || (!compiler.Compiler.devMode()))
			sfxExpr.subExpr.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(SizeExpr sizeExpr, Mode arg) {
		if ((sizeExpr.type != null) || (!compiler.Compiler.devMode()))
			sizeExpr.type.accept(this, arg);
		return null;
	}
	



	// ===== SYMBOL TABLE =====

	/**
	 * A symbol table.
	 */
	public class SymbTable {

		/**
		 * A symbol table record denoting a definition of a name within a certain scope.
		 */
		private class ScopedDefn {

			/** The depth of the scope the definition belongs to. */
			public final int depth;

			/** The definition. */
			public final AST.Defn defn;

			/**
			 * Constructs a new record denoting a definition of a name within a certain
			 * scope.
			 * 
			 * @param depth The depth of the scope the definition belongs to.
			 * @param defn  The definition.
			 */
			public ScopedDefn(int depth, AST.Defn defn) {
				this.depth = depth;
				this.defn = defn;
			}

		}

		/**
		 * A mapping of names into lists of records denoting definitions at different
		 * scopes. At each moment during the lifetime of a symbol table, the definition
		 * list corresponding to a particular name contains all definitions that name
		 * within currently active scopes: the definition at the inner most scope is the
		 * first in the list and is visible, the other definitions are hidden.
		 */
		private final HashMap<String, LinkedList<ScopedDefn>> allDefnsOfAllNames;

		/**
		 * The list of scopes. Each scope is represented by a list of names defined
		 * within it.
		 */
		private final LinkedList<LinkedList<String>> scopes;

		/** The depth of the currently active scope. */
		private int currDepth;

		/** Whether the symbol table can no longer be modified or not. */
		private boolean lock;

		/**
		 * Constructs a new symbol table.
		 */
		public SymbTable() {
			allDefnsOfAllNames = new HashMap<String, LinkedList<ScopedDefn>>();
			scopes = new LinkedList<LinkedList<String>>();
			currDepth = 0;
			lock = false;
			newScope();
		}

		/**
		 * Returns the depth of the currently active scope.
		 * 
		 * @return The depth of the currently active scope.
		 */
		public int currDepth() {
			return currDepth;
		}

		/**
		 * Inserts a new definition of a name within the currently active scope or
		 * throws an exception if this name has already been defined within this scope.
		 * Once the symbol table is locked, any attempt to insert further definitions
		 * results in an internal error.
		 * 
		 * @param name The name.
		 * @param defn The definition.
		 * @throws CannotInsNameException Thrown if this name has already been defined
		 *                                within the currently active scope.
		 */
		public void ins(String name, AST.Defn defn) throws CannotInsNameException {
			if (lock)
				throw new Report.InternalError();

			LinkedList<ScopedDefn> allDefnsOfName = allDefnsOfAllNames.get(name);
			if (allDefnsOfName == null) {
				allDefnsOfName = new LinkedList<ScopedDefn>();
				allDefnsOfAllNames.put(name, allDefnsOfName);
			}

			if (!allDefnsOfName.isEmpty()) {
				ScopedDefn defnOfName = allDefnsOfName.getFirst();
				if (defnOfName.depth == currDepth)
					throw new CannotInsNameException();
			}

			allDefnsOfName.addFirst(new ScopedDefn(currDepth, defn));
			scopes.getFirst().addFirst(name);
		}

		/**
		 * Returns the currently visible definition of the specified name. If no
		 * definition of the name exists within these scopes, an exception is thrown.
		 * 
		 * @param name The name.
		 * @return The definition.
		 * @throws CannotFndNameException Thrown if the name is not defined within the
		 *                                currently active scope or any scope enclosing
		 *                                it.
		 */
		public AST.Defn fnd(String name) throws CannotFndNameException {
			LinkedList<ScopedDefn> allDefnsOfName = allDefnsOfAllNames.get(name);
			if (allDefnsOfName == null)
				throw new CannotFndNameException();

			if (allDefnsOfName.isEmpty())
				throw new CannotFndNameException();

			return allDefnsOfName.getFirst().defn;
		}

		/** Used for selecting the range of scopes. */
		public enum XScopeSelector {
			/** All live scopes. */
			ALL,
			/** Currently active scope. */
			ACT,
		}

		/**
		 * Constructs a new scope within the currently active scope. The newly
		 * constructed scope becomes the currently active scope.
		 */
		public void newScope() {
			if (lock)
				throw new Report.InternalError();

			currDepth++;
			scopes.addFirst(new LinkedList<String>());
		}

		/**
		 * Destroys the currently active scope by removing all definitions belonging to
		 * it from the symbol table. Makes the enclosing scope the currently active
		 * scope.
		 */
		public void oldScope() {
			if (lock)
				throw new Report.InternalError();

			if (currDepth == 0)
				throw new Report.InternalError();

			for (String name : scopes.getFirst()) {
				allDefnsOfAllNames.get(name).removeFirst();
			}
			scopes.removeFirst();
			currDepth--;
		}

		/**
		 * Prevents further modifications of this symbol table.
		 */
		public void lock() {
			lock = true;
		}

		/**
		 * An exception thrown when the name cannot be inserted into a symbol table.
		 */
		@SuppressWarnings("serial")
		public class CannotInsNameException extends Exception {

			/**
			 * Constructs a new exception.
			 */
			private CannotInsNameException() {
			}

		}

		/**
		 * An exception thrown when the name cannot be found in the symbol table.
		 */
		@SuppressWarnings("serial")
		public class CannotFndNameException extends Exception {

			/**
			 * Constructs a new exception.
			 */
			private CannotFndNameException() {
			}

		}

	}

}
