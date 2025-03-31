package compiler.phase.seman;

import java.util.*;

import compiler.common.report.*;
import compiler.phase.abstr.*;
import compiler.phase.abstr.AST.*;
import compiler.phase.seman.TYP.*;
import compiler.phase.seman.TYP.IntType;
import compiler.phase.seman.NameResolver.*;
/**
 * Type checker.
 * 
 * @author bostjan.slivnik@fri.uni-lj.si
 */
public class TypeChecker implements AST.FullVisitor<TYP.Type, Mode> {
	public int stNod;
	/** Constructs a new name checker. */
	public TypeChecker() {
		stNod = TypeResolver.stNod;
	}
	
	// ------- Functions ----------
	/*@Override
	public TYP.Type visit(AST.DefFunDefn defFunDefn, Mode arg) {
		Report.info("Checking function "+defFunDefn.name);
		boolean noError = false;
		TYP.Type returnType = null;
		defFunDefn.pars.accept(this, arg);
		
		TYP.Type a = SemAn.ofType.get((defFunDefn));
		defFunDefn.stmts.accept(this, arg);
		Report.info(a.toString());
		for (var stmt : defFunDefn.stmts){
			if(doesStmtHaveReturn(stmt, a)){
				//Report.warning("FFF");
				noError = true;
				returnType = SemAn.ofType.get((stmt));

				
			
			}
		}

		if(!noError){
			throw new Report.Error(defFunDefn,"Function returns wrong type");
		}

		return SemAn.ofType.put(defFunDefn, a);
	}*/

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
		return null;
	}
	// ----- Expressions -----

	@Override
	public TYP.Type visit(AST.ArrExpr arrExpr, Mode arg) {
		if(arg == Mode.RESOLVE){
			TYP.Type a = SemAn.defAt.get(arrExpr.arrExpr).accept(this, arg);
			TYP.Type b = SemAn.defAt.get(arrExpr.idx).accept(this, arg);
			if(a instanceof TYP.ArrType ){
				if(b.actualType() == TYP.IntType.type){
					return SemAn.isType.put(arrExpr, a);
				}else{
					throw new Report.Error(arrExpr, "Not an int");

				}

			}else{
				throw new Report.Error(arrExpr, "Not an array");
			}
		}
		return null;
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
			return SemAn.ofType.put(atomExpr, temp);

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
		
		var typ = new TYP.StrType(typelist);

		return SemAn.isType.put(strType, typ);
	}

	@Override
	public TYP.Type visit(AST.BinExpr binExpr, Mode arg) {
		/*
		TYP.Type a = binExpr.fstExpr.accept(this, arg);
		TYP.Type b = binExpr.sndExpr.accept(this, arg);
		 */
		
		binExpr.fstExpr.accept(this, arg);
		TYP.Type a = SemAn.ofType.get(binExpr.fstExpr);
		
		binExpr.sndExpr.accept(this, arg);
		TYP.Type b = SemAn.ofType.get(binExpr.sndExpr);
		if(a==null)
			Report.info("shit");
		if(b==null)
			Report.info("fuck");
		
		if (a.actualType().equals(b.actualType()) && !(a instanceof TYP.RecType)) {
			
			if(b instanceof TYP.BoolType || b instanceof TYP.IntType || b instanceof TYP.CharType || b instanceof TYP.PtrType || b instanceof TYP.NameType || b instanceof TYP.VoidType){
				try{
					while(b instanceof TYP.NameType){
					b = b.actualType(); 
					}

				}catch(Exception e){

				}
				try{
					while(a instanceof TYP.NameType){
						a = a.actualType();
					}

				}catch(Exception e){

				}
				Report.info(a.toString() + " " + b.toString());
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
			throw new Report.Error(binExpr, "Cannot coerce " + a.actualType().toString() + " into " + b.actualType().toString());
		}//return SemAn.ofType.put(binExpr, TYP.BoolType.type);
	}

	@Override
	public TYP.Type visit(AST.CallExpr callExpr, Mode arg) {
		ArrayList<TYP.Type> parTypes = new ArrayList<TYP.Type>();
		TYP.Type funType = callExpr.funExpr.accept(this, arg);
		for (AST.Expr a : callExpr.argExprs)
			parTypes.add(a.accept(this, arg));
		Report.info(funType.actualType().toString());
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
		TYP.Type castType = SemAn.isType.get(castExpr.type);
		if (castType == null)
			throw new Report.Error(castExpr, "Cannot resolve type " + castExpr.type);
		if (castType instanceof TYP.NameType)
			castType = ((TYP.NameType) castType).actualType();
		return SemAn.ofType.put(castExpr, castType);

	}


	@Override
    public TYP.Type visit(AST.CompExpr compExpr, Mode mode) {
        compExpr.recExpr.accept(this, mode);
		
        TYP.Type compType = SemAn.ofType.get(compExpr.recExpr);
		
		//this should be either struct or union
		var structOrUnion = SemAn.defAt.get(compExpr.recExpr).type;
		while(structOrUnion instanceof AST.NameType){
			structOrUnion = SemAn.defAt.get(structOrUnion).type;
		}
		AST.RecType recordType = (AST.RecType)structOrUnion;
		if(!(structOrUnion instanceof AST.RecType)){
			throw new Report.Error(compExpr, "Cannot access a non union / struct");
		}
		
		var listOfComponents = recordType.comps;

		for(CompDefn componentDefinition : listOfComponents){
			if(componentDefinition.name.equals(compExpr.name)){
				return SemAn.ofType.put(compExpr, SemAn.ofType.get(componentDefinition));
			}
		}
		if(compType == null){
            return SemAn.ofType.put(compExpr, TYP.VoidType.type);    
        }
		throw new Report.Error(compExpr,"Could not find component with name " + compExpr.name);
        
    }

	@Override
	public TYP.Type visit(AST.NameExpr nameExpr, Mode arg) {
		AST.Defn defn = SemAn.defAt.get(nameExpr);
        TYP.Type nameType = SemAn.ofType.get(defn);
        return SemAn.ofType.put(nameExpr, nameType);
	}

	@Override
	public TYP.Type visit(AST.PfxExpr pfxExpr, Mode arg) {
		TYP.Type temp;
		TYP.Type tmp2 = pfxExpr.subExpr.accept(this, arg);
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
		return SemAn.ofType.put(pfxExpr, temp);
	}


	@Override
	public TYP.Type visit(AST.SfxExpr sfxExpr, Mode arg) {
		
		TYP.Type temp = sfxExpr.subExpr.accept(this,  Mode.RESOLVE);
		//Report.info(temp.toString());
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
		return null;
	}


	// ----- Statements -----
	/*
	@Override
	public TYP.Type visit(AST.LetStmt letStmt, Mode arg) {
		return null;
	}*/

	public boolean compTypes(TYP.Type a, TYP.Type b){
		if(a instanceof TYP.NameType){
			a = a.actualType();
		}
		if(b instanceof TYP.NameType){
			b = b.actualType();
		}
		if(a instanceof TYP.RecType a2 && b instanceof TYP.RecType b2){
			boolean neki = true;
			if(a2.compTypes.size() != b2.compTypes.size())
				return false;
				
			
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
			if((a2.baseType.actualType() == TYP.VoidType.type)){
				return false;
			}
			if(b2.baseType.actualType() == TYP.VoidType.type){
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
		}

		return false;
	}

	@Override
	public TYP.Type visit(AST.AssignStmt assignStmt, Mode arg) {
		TYP.Type src = assignStmt.srcExpr.accept(this, arg);
		TYP.Type dst = assignStmt.dstExpr.accept(this, arg);

		
		if(src instanceof TYP.FunType){
			throw new Report.Error(assignStmt, "Left assignment cannot be a function"); 
		}if(dst instanceof TYP.FunType){
			dst = ((TYP.FunType)dst).resType;
		}

		/*if(src != null){
			Report.info(src.toString());
		}if(dst != null){
			Report.info(dst.toString());
		}*/
		if(src != null && dst != null){
			if(src instanceof TYP.PtrType a && dst instanceof TYP.PtrType b){
				if (a.baseType instanceof TYP.NameType){
					var d = a.baseType.actualType();
					Report.info(d.toString());
					if(d instanceof TYP.VoidType)
						return null;
					else
						throw new Report.Error(assignStmt, "Types do not match");
				}
			}else if(!(src.toString().equals(dst.toString()))){
				Report.info("'"+src.toString() + "' '" + dst.toString() + "'");
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
		if(condType.actualType() != TYP.BoolType.type){
			throw new Report.Error(ifThenStmt,"Conditional statement should be of bool");
		}
		ifThenStmt.thenStmt.accept(this,arg);
		return null;
	}

	@Override
	public TYP.Type visit(AST.IfThenElseStmt ifThenElseStmt, Mode arg) {
		TYP.Type condType = ifThenElseStmt.condExpr.accept(this, arg);
		if(condType.actualType() != TYP.BoolType.type){
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
		if(condType.actualType() != TYP.BoolType.type){
			throw new Report.Error(whileStmt,"Conditional statement should be of bool");
		}
		whileStmt.stmts.accept(this,arg);
		return null;
	}
		

	public boolean traverseDaTree(Types<TYP.Type> list, int stKorakovLeft){
		if(stKorakovLeft < 1){

		}
		return true;
	}


	public boolean doesStmtHaveReturn(Nodes<AST.Stmt> stmtList, TYP.Type a){
		//Report.info(a.toString());

		//Report.info("cccc");
		for(AST.Stmt stmt : stmtList){
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
			if(stmt instanceof AST.IfThenStmt){
				AST.IfThenStmt castStmt = (AST.IfThenStmt)stmt;
				return (doesStmtHaveReturn(castStmt.thenStmt, a));
			}
			if(stmt instanceof AST.IfThenElseStmt){
				AST.IfThenElseStmt castStmt = (AST.IfThenElseStmt)stmt;
				return (doesStmtHaveReturn(castStmt.thenStmt, a) || doesStmtHaveReturn(castStmt.elseStmt, a));
			}
			if(stmt instanceof AST.ReturnStmt){
				AST.ReturnStmt castStmt = (AST.ReturnStmt)stmt;
				TYP.Type retValue = castStmt.accept(this, Mode.RESOLVE);
				TYP.Type b = SemAn.ofType.get(castStmt.retExpr);
				if(a == null){
					Report.warning("fuck");
				}
				if(b == null){
					Report.warning("b is null");

				}else if(retValue == null){
					Report.warning("retValue is null");

				}
				if(b != null && retValue != null)
					Report.info("This is within the list statement: retValue="+retValue.toString() + " b=" + b.toString() + " a=" + a.toString());
				/*if(b != null){
					Report.info(b.toString());
					if(b.actualType() == a.actualType())
						return true;
				}*/
				if(retValue != null){
				if(retValue.actualType() == a.actualType())
					Report.warning("does it actually work?");
					return true;
				}	
			
				return false;
			}
			if(stmt instanceof AST.WhileStmt){
				AST.WhileStmt castStmt = (AST.WhileStmt)stmt;
				return doesStmtHaveReturn(castStmt.stmts, a);
			}
			
		}

		return false;
	}
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
			AST.ReturnStmt castStmt = (AST.ReturnStmt)stmt;
			TYP.Type retValue = castStmt.accept(this, Mode.RESOLVE);
			TYP.Type b = SemAn.ofType.get(castStmt.retExpr);
			if(b != null && retValue != null)
				Report.info("This is within the singular statement: retValue="+retValue.toString() + " b=" + b.toString());
			if(b.actualType().equals(a.actualType()))
				return true;
			return false;
		}
		if(stmt instanceof AST.WhileStmt){
			AST.WhileStmt castStmt =(AST.WhileStmt)stmt;
			return doesStmtHaveReturn(castStmt.stmts, a);
		}


		return false;
	}

}
