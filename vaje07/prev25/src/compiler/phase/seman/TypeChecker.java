package compiler.phase.seman;

import java.util.*;

import compiler.common.report.*;
import compiler.phase.abstr.*;
import compiler.phase.abstr.AST.*;
import compiler.phase.abstr.AST.NameType;
import compiler.phase.seman.TYP.*;
import compiler.phase.seman.NameResolver.*;
/**
 * Type checker.
 * 
 * @author bostjan.slivnik@fri.uni-lj.si
 */
public class TypeChecker implements AST.FullVisitor<TYP.Type, Mode> {
	/** Constructs a new name checker. */
	public TypeChecker() {
	}
	
	// ------- Functions ----------
	

	@Override
	public TYP.Type visit(AST.PtrType ptrType, Mode arg) {
		TYP.Type tmp = SemAn.isType.get(ptrType);
		if(tmp instanceof TYP.NameType){
			TYP.Type b = tmp; //SemAn.isType.get(ptrType.baseType);
			if(b instanceof TYP.NameType){
				while(b instanceof TYP.NameType){
					b = b.actualType();
				}
			}
			
			if(b instanceof TYP.VoidType){
				throw new Report.Error(ptrType, "Base pointer cannot be void (even if its being cast >:( )!");
			}
			
		}
		//SemAn.isAddr.put(ptrType, true);
		//SemAn.isConst.put(ptrType, false);

		return null;
	}
	@Override
	public TYP.Type visit(AST.ArrType arrType, Mode arg) {
		Long c = Long.parseLong(arrType.numElems);
		TYP.Type tmp = arrType.elemType.accept(this, arg);
		if(c<1){
			throw new Report.Error("Number of elements has to be at least 1!");
		}
		if(tmp instanceof TYP.VoidType){
			throw new Report.Error(arrType, "Void array cannot exist!");
			
		}
		//SemAn.isAddr.put(arrType, true);	
		//SemAn.isConst.put(arrType, false);
		return null;
	}

	// ----- Expressions -----

	@Override
	public TYP.Type visit(AST.ArrExpr arrExpr, Mode arg) {

		TYP.Type a = arrExpr.arrExpr.accept(this, arg);
		if(a instanceof TYP.NameType){
			a = a.actualType();
		}if(a == null){
			Report.info("SHIT");
		}
		if(!SemAn.isAddr.get(arrExpr.arrExpr)){
			throw new Report.Error(arrExpr, "Cannot access array on an expression that doesnt exist");
		}
		TYP.Type b = arrExpr.idx.accept(this, arg);
		SemAn.isAddr.put(arrExpr, true);
		SemAn.isConst.put(arrExpr, false);
		if(b instanceof TYP.NameType){
			b = b.actualType();
		}if(b == null){
			Report.info("FUCK");
		}
		if(a instanceof TYP.ArrType){
			if(b instanceof TYP.IntType){
				var c = ((TYP.ArrType)a).elemType;
				return SemAn.ofType.put(arrExpr, c);

			}else{
				throw new Report.Error(arrExpr, "Not an int");

			}

		}
		throw new Report.Error(arrExpr, "Not an array");

	}

	@Override
	public TYP.Type visit(AST.AtomExpr atomExpr, Mode arg) {
			TYP.Type temp;
			switch(atomExpr.type){
				case CHAR:
					temp = TYP.CharType.type;
					break;
				case BOOL:
					temp = TYP.BoolType.type;
					break;
				case INT:
					temp = TYP.IntType.type;
					break;
				case PTR:
					temp = TYP.PtrType.type;
					break;
				case STR:
					temp = TYP.PtrType.type;
					break;
				default:
					temp = null;
					break;
			}
			SemAn.isAddr.put(atomExpr, false);
			SemAn.isConst.put(atomExpr, true);
			return SemAn.ofType.put(atomExpr, temp);

	}


	@Override
	public TYP.Type visit(AST.TypDefn typDefn, Mode arg) {
		TYP.Type temp = SemAn.isType.get(typDefn.type);
		if(temp instanceof TYP.NameType b){
			//Report.info("Typedefn"+typDefn.name +  " is a name type");
			//SemAn.ofType.get
			try{
				var d = typDefn.type.accept(this, arg);
				if(temp == d){
					throw new Report.Error(typDefn, "Typedefn " + typDefn.name + " is recursive");
				}
			}catch(StackOverflowError e){
				throw new Report.Error(typDefn, "Typedefn " + typDefn.name + " is recursive");
				//Report.info("Error in type definition");
			}
		}

		return null;
		
	}
	
	@Override
	public TYP.Type visit(AST.NameType nameType, Mode arg) {
		
		AST.Node nameType2 = (SemAn.defAt.get(nameType));

		if(nameType2 instanceof AST.TypDefn){
			TYP.Type a = SemAn.isType.get(nameType2);
			return a;
		}
		throw new Report.Error(nameType, "Unable to resolve type for: " + nameType.name);
	

	}



	@Override
	public TYP.Type visit(AST.StrType strType, Mode arg) {
		TYP.Type trenList = SemAn.isType.get(strType);
		LinkedList<TYP.Type> typelist = new LinkedList<TYP.Type>();
		for (Node comp : strType.comps) {
			TYP.Type b = comp.accept(this, arg);
			if(b instanceof TYP.VoidType){
				throw new Report.Error(strType, "Components cannot be void");
			}

			typelist.addLast(b);
		}
		
		return trenList;
	}
	@Override
	public TYP.Type visit(AST.UniType uniType, Mode arg) {
		TYP.Type trenList = SemAn.isType.get(uniType);
		LinkedList<TYP.Type> typelist = new LinkedList<TYP.Type>();
		for (Node comp : uniType.comps) {
			AST.CompDefn compDefn = (AST.CompDefn)comp;
			TYP.Type c = SemAn.ofType.get(compDefn);
			typelist.addLast(c);	

		}
		return trenList;
	}

	@Override
	public TYP.Type visit(AST.CompDefn compDefn, Mode arg) {
		TYP.Type a = compDefn.type.accept(this, arg);
		
		if(a==null)
			return SemAn.ofType.put(compDefn, TYP.IntType.type);
		
		return SemAn.ofType.put(compDefn, a);
	}
	@Override
	public TYP.Type visit(AST.BinExpr binExpr, Mode arg) {	
		TYP.Type a = binExpr.fstExpr.accept(this, arg);
		TYP.Type b = binExpr.sndExpr.accept(this, arg);
		Boolean isConst = SemAn.isConst.get(binExpr.fstExpr);
		Boolean isConst2 = SemAn.isConst.get(binExpr.sndExpr);
		SemAn.isAddr.put(binExpr, false);
		SemAn.isConst.put(binExpr, isConst || isConst2);
		if (compTypes(a, b) && !(a instanceof TYP.RecType)) {
			
			if(b instanceof TYP.BoolType || b instanceof TYP.IntType || b instanceof TYP.CharType || b instanceof TYP.PtrType || b instanceof TYP.NameType || b instanceof TYP.VoidType){
				try{
					while(b instanceof TYP.NameType){
					b = b.actualType(); 
					}

				}catch(Exception e){
					Report.info("Error in b definition");
				}
				try{
					while(a instanceof TYP.NameType){
						a = a.actualType();
					}

				}catch(Exception e){
					Report.info("Error in a definition");
				}
				//Report.info(a.toString() + " " + b.toString());
				switch(binExpr.oper){
					case OR:
					case AND:
						if(b instanceof TYP.BoolType && a instanceof TYP.BoolType)
							return SemAn.ofType.put(binExpr, TYP.BoolType.type);
						throw new Report.Error(binExpr, "Could not coerce both types into bool");
					case MUL:
					case DIV:
					case MOD:
					case ADD:
					case SUB:
						if(b instanceof TYP.IntType && a instanceof TYP.IntType)
							return SemAn.ofType.put(binExpr, TYP.IntType.type);
						throw new Report.Error(binExpr, "Could not coerce both types into int");
					case EQU:
					case NEQ:
					case LTH:
					case GTH:
					case LEQ:
					case GEQ:	
						return SemAn.ofType.put(binExpr, TYP.BoolType.type);
					default:
						throw new  Report.Error(binExpr, "Wrong operation");
				}
			}
			else{
				throw new Report.Error(binExpr, "Types should be either int, bool, char or ptr");
			}

			//return SemAn.ofType.put(binExpr, a);

		}else{
			if(a==null){
				Report.info("a is null");
				return null;
			}
			if(b==null){
				Report.info("b is null");
				return null;
			}
			throw new Report.Error(binExpr, "Cannot coerce " + a.actualType().toString() + " into " + b.actualType().toString());
		}
	}

	@Override
	public TYP.Type visit(AST.CallExpr callExpr, Mode arg) {
		ArrayList<TYP.Type> parTypes = new ArrayList<TYP.Type>();
		SemAn.isAddr.put(callExpr, false);
		SemAn.isConst.put(callExpr, false);
		callExpr.funExpr.accept(this, arg);
		TYP.Type funType = SemAn.ofType.get(callExpr.funExpr);
		
		if(funType instanceof TYP.NameType){
			funType = funType.actualType();
		}
		if(!(funType instanceof TYP.FunType)){
			throw new Report.Error(callExpr,"Expected a function");
		}
		for (AST.Expr a : callExpr.argExprs)
			parTypes.add(a.accept(this, arg));
		//Report.info(funType.actualType().toString());
		while(funType instanceof TYP.NameType){
			funType = funType.actualType();
		}
		if (!(funType instanceof TYP.FunType)){
			throw new Report.Error(callExpr, "Function expression must be of type function");
		}
		if (parTypes.size() != ((TYP.FunType) funType).parTypes.size())
			throw new Report.Error(callExpr, "Function call has wrong number of arguments");
		return SemAn.ofType.put(callExpr, ((TYP.FunType) funType).resType);
		
	}

	@Override
	public TYP.Type visit(AST.CastExpr castExpr, Mode arg) {
		
		castExpr.expr.accept(this,arg);
		SemAn.defAt.get(castExpr.expr).accept(this, arg);
		boolean isAddr = SemAn.isAddr.get(castExpr.expr);
		boolean isConst = SemAn.isConst.get(castExpr.expr);

		SemAn.isAddr.put(castExpr, isAddr);
		SemAn.isConst.put(castExpr, isConst);

		TYP.Type castType = SemAn.isType.get(castExpr.type);
		if (castType == null)
			throw new Report.Error(castExpr, "Cannot resolve type " + castExpr.type);
		if (castType instanceof TYP.NameType)
			castType = ((TYP.NameType) castType).actualType();
		return SemAn.ofType.put(castExpr, castType);

	}


	@Override
	public TYP.Type visit(AST.CompExpr compExpr, Mode arg) {
		TYP.Type recType = compExpr.recExpr.accept(this, arg).actualType();
		Boolean tmp = SemAn.isAddr.get(compExpr.recExpr);
		if(!tmp){
			throw new Report.Error(compExpr, "Cannot access component on an expression that doesnt exist");
		}
		//Report.info(recType.toString());
		if(!(recType instanceof TYP.RecType)){
			throw new Report.Error(compExpr, "Cannot access component of non-record type");
		}
		TYP.RecType rec = (TYP.RecType) recType;
		int ind = rec.names.indexOf(compExpr.name);
		if(ind == -1){
			throw new Report.Error(compExpr, "Component '" + compExpr.name + "' not found in struct or union.");
		}
		TYP.Type compType = rec.compTypes.get(ind);
		SemAn.ofType.put(compExpr, compType);
		SemAn.isAddr.put(compExpr, true);
		SemAn.isConst.put(compExpr, false);
			
		return compType; 
	}

	@Override
	public TYP.Type visit(AST.NameExpr nameExpr, Mode arg) {
		AST.Defn defn = SemAn.defAt.get(nameExpr);
        TYP.Type nameType = SemAn.ofType.get(defn);
		SemAn.isAddr.put(nameExpr, true);
		SemAn.isConst.put(nameExpr, false);
        return SemAn.ofType.put(nameExpr, nameType);
	}

	@Override
	public TYP.Type visit(AST.PfxExpr pfxExpr, Mode arg) {
		TYP.Type temp;
		TYP.Type tmp2 = pfxExpr.subExpr.accept(this, arg);
		boolean isAddr = SemAn.isAddr.get(pfxExpr.subExpr);
		boolean isConst = SemAn.isConst.get(pfxExpr.subExpr);
		SemAn.isAddr.put(pfxExpr, isAddr);
		SemAn.isConst.put(pfxExpr, isConst);
		switch(pfxExpr.oper){
			case ADD:
				temp = TYP.IntType.type;
				if(tmp2.actualType() != TYP.IntType.type){
					throw new Report.Error(pfxExpr,"Unable to add on a non int type");
				}
				break;
			case SUB:
				temp = TYP.IntType.type;
				if(tmp2.actualType() != TYP.IntType.type){
					throw new Report.Error(pfxExpr,"Unable to subtract from a non int type");
				}
				break;
			case NOT:
				temp = TYP.BoolType.type;
				if(tmp2.actualType() != TYP.BoolType.type){
					throw new Report.Error(pfxExpr,"Unable to negate non boolean statements");
				}
				break;
			case PTR:
				temp = new TYP.PtrType(tmp2);
				if(tmp2 instanceof TYP.VoidType){
					throw new Report.Error(pfxExpr,"Unable to make a pointer out of a void type");
				}
				break;
			default:
				temp = null;
				break;
		}
		if(tmp2.actualType() != temp.actualType()){
			//throw new Report.Error("Coercion failed");
		}
		SemAn.isAddr.put(pfxExpr, true);
		SemAn.isConst.put(pfxExpr, false);
		return SemAn.ofType.put(pfxExpr, temp);
	}


	@Override
	public TYP.Type visit(AST.DefFunDefn defFunDefn, Mode arg) {
		//Report.info("Checking function "+defFunDefn.name);
		boolean noError = false;
		TYP.Type returnType = null;
		defFunDefn.pars.accept(this, arg);
		TYP.Type a = SemAn.ofType.get((defFunDefn));
		if(!(a instanceof TYP.FunType)){
			throw new Report.Error(defFunDefn,"What the fuck is this");
		}
		var b = (TYP.FunType)a;
		
		defFunDefn.stmts.accept(this, arg);
		//Report.info("resType:"+b.resType.toString()+" of function:" + defFunDefn.name);
		for (var stmt : defFunDefn.stmts){
			if(doesStmtHaveReturn(stmt, b.resType)){
				noError = true;
				//returnType = SemAn.ofType.get((stmt));
				//Report.info("This is the return type: " + returnType.toString());
				
				break;
				
			
			}
		}

		if(!noError){
			throw new Report.Error(defFunDefn,"Function returns wrong type");
			//throw new Report.Error(defFunDefn,"Function returns wrong type");
		}
		if(!compTypes(returnType, a)){
		}

		return SemAn.ofType.put(defFunDefn, a);
		
	}

	@Override
	public TYP.Type visit(AST.SfxExpr sfxExpr, Mode arg) {
		
		TYP.Type temp = sfxExpr.subExpr.accept(this,  Mode.RESOLVE);
		//Report.info(temp.toString());
		SemAn.isAddr.put(sfxExpr, true);
		SemAn.isConst.put(sfxExpr, false);
		if(temp.actualType() == TYP.VoidType.type){
			throw new Report.Error(sfxExpr,"Unable to dereference a null pointer");
		}
		if(temp instanceof TYP.PtrType a){
			return SemAn.ofType.put(sfxExpr, a.baseType);
		}
		
		return SemAn.ofType.put(sfxExpr, temp);
		
	}

	@Override
	public TYP.Type visit(AST.SizeExpr sizeExpr, Mode arg) {
		TYP.Type temp = sizeExpr.type.accept(this, arg);
		if(temp instanceof TYP.VoidType){
			throw new Report.Error(sizeExpr,"Unable to dereference a null pointer");
		}
		SemAn.isAddr.put(sizeExpr, false);
		SemAn.isConst.put(sizeExpr, true);
		return SemAn.ofType.put(sizeExpr, TYP.IntType.type);
	}


	// ----- Statements -----

	/**
	 * Checks if two types are the same
	 * @param a First type
	 * @param b Sencond type
	 * @return True if they are the same, false otherwise
	 */
	public static boolean compTypesDebug(TYP.Type a, TYP.Type b){
		//if(a != null && b != null)
			//Report.info("a:" +a.toString() + " b:" + b.toString());
		//else
			//Report.info("One of the types is null");
		return compTypes(a, b);
	}

	/**
	 * Compares two types and checks if they are the same
	 * @param a First type
	 * @param b Sencond type
	 * @return True if they are the same, false otherwise
	 */
	public static boolean compTypes(TYP.Type a, TYP.Type b){

		if(a instanceof TYP.NameType){
			a = a.actualType();
		}
		if(b instanceof TYP.NameType){
			b = b.actualType();
		}
		if(a == null || b == null){
			return false;
		}
		//Report.info("first arg val:" + a.toString() + " second arg val:" + b.toString());
		if(a instanceof TYP.RecType a2 && b instanceof TYP.RecType b2){
			if(a2.toString().equals(b2.toString())){
				return true;
			}
			boolean neki = true;
			if(a2.compTypes.size() != b2.compTypes.size()){
				return false;
			}
			
			for(int i = 0; i<a2.compTypes.size(); i++){
				var prviComp = a2.compTypes.get(i);
				var drugComp = b2.compTypes.get(i);
				if(prviComp instanceof TYP.NameType){
					prviComp = prviComp.actualType();
				}
				if(drugComp instanceof TYP.NameType){
					drugComp = drugComp.actualType();
				}

				if(!compTypes(prviComp, drugComp)){
					neki = false;
					break;
				};


			}
			return neki;
		}else if(a instanceof TYP.PtrType a2 && b instanceof TYP.PtrType b2){
			//Report.info("Hallo, the pointer values are:" +a2.actualType().toString()+ ","+ b2.actualType().toString());
			if( (b2.baseType.actualType() instanceof TYP.VoidType) && (a2.baseType.actualType() instanceof TYP.VoidType))
				return true;
			
			if((a2.baseType.actualType() instanceof TYP.VoidType)){

				return false;
			}
			if(b2.baseType.actualType() instanceof TYP.VoidType){

				return true;
				
			}
			return compTypes(a2.baseType, b2.baseType);
		}else if(a instanceof TYP.IntType && b instanceof TYP.IntType){
			return true;
		}else if(a instanceof TYP.BoolType && b instanceof TYP.BoolType){
			return true;
		}else if(a instanceof TYP.CharType && b instanceof TYP.CharType){
			return true;
		}else if(b instanceof TYP.FunType a2){
			return compTypes(a, a2.resType);
		}else if(a instanceof TYP.ArrType a2 && b instanceof TYP.ArrType b2){
			return compTypes(a2.elemType, b2.elemType);
		}else if(a.actualType().equals(b.actualType())){
			return true;
		}

		return false;
	}

	@Override
	public TYP.Type visit(AST.AssignStmt assignStmt, Mode arg) {
		TYP.Type dst = assignStmt.dstExpr.accept(this, arg);
		TYP.Type src = assignStmt.srcExpr.accept(this, arg);

		if(dst instanceof TYP.FunType){
			throw new Report.Error(assignStmt, "Left assignment cannot be a function"); 
		}if(src instanceof TYP.FunType){
			src = ((TYP.FunType)src).resType;
		}

		if(src != null && dst != null){
			if(compTypes(dst, src)){
				//Report.info("Types "+ src.toString()+ " and " +dst.toString() +  " are equal");
			}else{
				if(src instanceof TYP.PtrType a && dst instanceof TYP.PtrType b){
					//Report.info(a.baseType.toString() + " " + b.baseType.toString());
				}
				//Report.info("Types "+ src.toString()+ " and " +dst.toString() +  " are not equal");
				throw new Report.Error(assignStmt, "Types do not match");
			}
			
		}
		return null;
	}
	
	@Override
	public TYP.Type visit(AST.ExprStmt callStmt, Mode arg) {
		callStmt.expr.accept(this, arg);
		return null;
	}

	@Override
	public TYP.Type visit(AST.IfThenStmt ifThenStmt, Mode arg) {
		TYP.Type condType = ifThenStmt.condExpr.accept(this, arg);
		//Report.warning(ifThenStmt.condExpr.toString());
		if(!(compTypes(condType, TYP.BoolType.type))){
			//if(condType != null)
				//Report.info(condType.toString());
			throw new Report.Error(ifThenStmt,"Conditional statement should be of bool");
		}
		
		ifThenStmt.thenStmt.accept(this,arg);
		return null;
	}

	@Override
	public TYP.Type visit(AST.IfThenElseStmt ifThenElseStmt, Mode arg) {
		TYP.Type condType = ifThenElseStmt.condExpr.accept(this, arg);
		if(!(compTypes(condType, TYP.BoolType.type))){
			throw new Report.Error(ifThenElseStmt,"Conditional statement should be of bool");
		}
		
		ifThenElseStmt.thenStmt.accept(this,arg);
		ifThenElseStmt.elseStmt.accept(this,arg);
		return null;
	}

	@Override
	public TYP.Type visit(AST.ReturnStmt returnStmt, Mode arg) {
		returnStmt.retExpr.accept(this, arg);
		return null;
	}

	@Override
	public TYP.Type visit(AST.WhileStmt whileStmt, Mode arg) {
		TYP.Type condType = whileStmt.condExpr.accept(this, arg);
		if(!(compTypes(condType, TYP.BoolType.type))){
			throw new Report.Error(whileStmt,"Conditional statement should be of bool");
		}
		whileStmt.stmts.accept(this,arg);
		return null;
	}
		

	/**
	 * Checks if a statement has a return statement with the same type as the function
	 * @param stmtList go through the list and check if any of the statements have a return
	 * @param expectedType the expected type of the return statement
	 * @return True if the statement has a return statement with the same type as the function
	 */
	public boolean doesStmtHaveReturn(Nodes<AST.Stmt> stmtList, TYP.Type expectedType) {
		for (AST.Stmt stmt : stmtList) {
			if (doesStmtHaveReturn(stmt, expectedType)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a statement has a return statement with the same type as the function
	 * @param stmt the statement to check
	 * @param a the expected type of the return statement
	 * @return True if the statement has a return statement with the same type as the function
	 */
	public boolean doesStmtHaveReturn(AST.Stmt stmt, TYP.Type a){

		if(stmt instanceof AST.LetStmt){
			AST.LetStmt castStmt = (AST.LetStmt)stmt;
			return (doesStmtHaveReturn(castStmt.stmts, a));
		}
		if(stmt instanceof AST.ExprStmt){
			return false;
		}
		if(stmt instanceof AST.AssignStmt){
			return false;
		}
		if(stmt instanceof AST.IfThenElseStmt){
			AST.IfThenElseStmt castStmt = (AST.IfThenElseStmt)stmt;
			return (doesStmtHaveReturn(castStmt.thenStmt, a) || doesStmtHaveReturn(castStmt.elseStmt, a));
		}else if(stmt instanceof AST.IfThenStmt){
			//Report.info("hallo");
			AST.IfThenStmt castStmt = (AST.IfThenStmt)stmt;
			return (doesStmtHaveReturn(castStmt.thenStmt, a));
		}
		
		if(stmt instanceof AST.ReturnStmt){
			//Report.info("null3");

			AST.ReturnStmt castStmt = (AST.ReturnStmt)stmt;
			TYP.Type b = SemAn.ofType.get(castStmt.retExpr);
			//Report.info("\n----------\n"+b.toString()+"\n-------------");
			if(b instanceof TYP.VoidType){
				return false;
			}
			if(a instanceof TYP.VoidType){
				return true;
			}
			if(b.actualType().equals(a.actualType())){
				//Report.info("null");
				return true;
			}
			if(compTypesDebug(a.actualType(), b)){
				//Report.info("null2");
				return true;
			}

			return false;
		}
		if(stmt instanceof AST.WhileStmt){
			AST.WhileStmt castStmt =(AST.WhileStmt)stmt;
			return doesStmtHaveReturn(castStmt.stmts, a);
		}


		return false;
	}

}
