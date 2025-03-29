package compiler.phase.seman;

import java.util.*;

import compiler.common.report.*;
import compiler.phase.abstr.*;
import compiler.phase.seman.NameResolver.Mode;
import compiler.phase.abstr.AST.*;
import compiler.phase.seman.TYP.*;
import compiler.phase.seman.NameResolver.*;

/**
 * Type resolver.
 * 
 * @author bostjan.slivnik@fri.uni-lj.si
 */
public class TypeResolver implements AST.FullVisitor<TYP.Type, Mode> {

	/** Constructs a new name resolver. */
	public TypeResolver() {
	}
	public TYP.Type addToAllTypes(TYP.Type a, AST.Node b){
		
		return SemAn.isType.put(b, a.actualType());
	}

	
	public TYP.Type getTypeByName(AST.NameType b){
		return SemAn.isType.get(b);
	}


	@Override
	public TYP.Type visit(Nodes<? extends AST.Node> nodes, Mode arg) {
		for (final AST.Node node : nodes){
			node.accept(this, Mode.DECLARE);

		}
		for (final AST.Node node : nodes){
			node.accept(this, NameResolver.Mode.RESOLVE);
		}

		return null;
	}

	@Override
	public TYP.Type visit(AST.AtomType atomType, Mode arg){
		if(arg == Mode.RESOLVE){
			TYP.Type temp;
			switch(atomType.type){
				case CHAR:
					temp = TYP.CharType.type;
					break;
				case BOOL:
					temp = TYP.BoolType.type;
					break;
				case INT:
					temp = TYP.IntType.type;
					break;
				case VOID:
					temp = TYP.VoidType.type;
					break;
				default:
					temp = null;
					break;
			}
			return SemAn.isType.put(atomType, temp);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.ArrType arrType, Mode arg) {
		if(arg == Mode.RESOLVE){
			Long c = Long.parseLong(arrType.numElems);
			TYP.Type tmp = arrType.elemType.accept(this, arg);
			if(c<1){
				throw new Report.Error("Number of elements has to be at least 1!");
			}
			if(tmp instanceof TYP.VoidType){
				throw new Report.Error(arrType, "Void array cannot exist!");
			
			}/*
			if(tmp instanceof TYP.NameType){
				TYP.Type b = (TYP.Type)tmp.actualType();
				if (b instanceof TYP.VoidType){

				}
			} */
			TYP.ArrType neki = new TYP.ArrType(tmp, c);
			return SemAn.isType.put(arrType, neki);
		}
		return null;
	}


	@Override
	public TYP.Type visit(AST.PtrType ptrType, Mode arg) {
		
		if(arg == Mode.RESOLVE){
			TYP.Type tmp = ptrType.baseType.accept(this, arg);
			if(tmp.actualType() == TYP.VoidType.type){
				throw new Report.Error(ptrType, "Base pointer cannot be void!");
			/*}else if(tmp instanceof TYP.NameType){
				//TYP.Type b = tmp.actualType() ;
				//if(b instanceof TYP.VoidType){
				//	throw new Report.Error(ptrType, "Base pointer cannot be void (even if its being cast >:( )!");

				//}
			} */}
			TYP.PtrType neki = new TYP.PtrType(tmp);
			return SemAn.isType.put(ptrType,neki);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.StrType strType, Mode arg) {
		if(arg == Mode.DECLARE){
            TYP.Type typ =  new TYP.StrType(null);
            return SemAn.isType.put(strType, typ);
        } else if (arg == Mode.RESOLVE){
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
        return null;
	}

	@Override
	public TYP.Type visit(AST.UniType uniType, Mode arg) {
		if(arg == Mode.DECLARE){
            TYP.Type typ =  new TYP.UniType(null);
            return SemAn.isType.put(uniType, typ);
        } else if (arg == Mode.RESOLVE){
			LinkedList<TYP.Type> typelist = new LinkedList<TYP.Type>();
            for (Node comp : uniType.comps) {
                TYP.Type b = comp.accept(this, arg);
				if(b instanceof TYP.VoidType){
					throw new Report.Error(uniType, "Components cannot be void");
				}
                typelist.addLast(b);
            }
			var typ = new TYP.UniType(typelist);
			return SemAn.isType.put(uniType, typ);
        }
		return null;
	}

	@Override
	public TYP.Type visit(AST.NameType nameType, Mode arg) {
		if(arg == Mode.DECLARE){
			TYP.NameType tmp = new TYP.NameType(nameType.name);
			return SemAn.isType.put(nameType, tmp);
		}
		if(arg == Mode.RESOLVE){
			AST.Node nameType2 = (SemAn.defAt.get(nameType));

			if(nameType2 instanceof AST.TypDefn){
				TYP.Type a = SemAn.isType.get(nameType2);
				return SemAn.isType.put(nameType, a);
			}
			throw new Report.Error(nameType, "Unable to resolve type for: " + nameType.name);
		}

		return null;
	}

	@Override
	public TYP.Type visit(AST.FunType funType, Mode arg) {
		if(arg == Mode.RESOLVE){
			var a = new ArrayList<TYP.Type>();
			TYP.Type b = funType.resType.accept(this, arg);
			
			for(Node c : funType.parTypes){
				TYP.Type d = c.accept(this, arg);
				if(d instanceof TYP.VoidType){
					throw new Report.Error(funType, "Paramater was of type void");
				}else if(d instanceof TYP.UniType){
					throw new Report.Error(funType, "Paramater was of type union");
				}else if(d instanceof TYP.StrType){
					throw new Report.Error(funType, "Paramater was of type struct");
				}else if(d instanceof TYP.ArrType){
					throw new Report.Error(funType, "Paramater was of type array");
				}
				a.add(d);
			}
			if(b instanceof TYP.UniType){
				throw new Report.Error(funType, "Return was of type union");
			}else if(b instanceof TYP.StrType){
				throw new Report.Error(funType, "Return was of type struct");
			}else if(b instanceof TYP.ArrType){
				throw new Report.Error(funType, "Return was of type array");
			}
			var l = new TYP.FunType(a, b);
			return SemAn.isType.put(funType, l);
		}
		return null;
	}

	// ---- Gor so visitorji za tipe, spodaj pa za posamezne keyworde ----

	
	@Override
	public TYP.Type visit(AST.TypDefn typDefn, Mode arg) {
		if(arg == Mode.DECLARE){
			//Report.info("Putted");
			TYP.NameType tmp = new TYP.NameType(typDefn.name);
			SemAn.isType.put(typDefn, tmp);
		}
		if(arg == Mode.RESOLVE){
			TYP.Type t = typDefn.type.accept(this, arg);
			TYP.Type tmp = SemAn.isType.get(typDefn);
			if(!(tmp instanceof TYP.NameType))
				throw new Report.Error(typDefn, "Couldnt resolve: " + typDefn.name);
			((TYP.NameType)tmp).setActType(t);	
			
		}
		return null;
		
	} 

	@Override
	public TYP.Type visit(AST.VarDefn varDefn, Mode arg) {
		varDefn.type.accept(this, arg);
		if(arg==Mode.RESOLVE){
			TYP.Type a = SemAn.isType.get(varDefn.type);
			return SemAn.ofType.put(varDefn, a);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.DefFunDefn defFunDefn, Mode arg) {
		defFunDefn.pars.accept(this, arg);
		if(arg == Mode.DECLARE){
		}
		if (arg == Mode.RESOLVE){
			defFunDefn.stmts.accept(this, arg);
			TYP.Type a = (defFunDefn.type).accept(this, arg);
			return SemAn.ofType.put(defFunDefn, a);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.ExtFunDefn extFunDefn, Mode arg) {
		extFunDefn.pars.accept(this, arg);
		if(arg == Mode.DECLARE){
		}
		if (arg == Mode.RESOLVE){

			TYP.Type a = (extFunDefn.type).accept(this, arg);
			return SemAn.ofType.put(extFunDefn, a);
			
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.ParDefn parDefn, Mode arg) {
		parDefn.type.accept(this, arg);
		if(arg==Mode.RESOLVE){
			TYP.Type a = SemAn.isType.get(parDefn.type);
			return SemAn.ofType.put(parDefn, a);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.CompDefn compDefn, Mode arg) {
		if(arg==Mode.RESOLVE){
			TYP.Type a = compDefn.type.accept(this, arg);
			return SemAn.ofType.put(compDefn, a);
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
		if(arg==Mode.RESOLVE){
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
		
		return null;
	}

	@Override
	public TYP.Type visit(AST.BinExpr binExpr, Mode arg) {
		
		if(arg == Mode.RESOLVE){
			TYP.Type a = binExpr.fstExpr.accept(this, arg);
			TYP.Type b = binExpr.sndExpr.accept(this, arg);
			if(a.actualType() == b.actualType()){
				
				if(b.actualType() == TYP.BoolType.type){

					switch(binExpr.oper){
						case OR:

						case AND:
							return SemAn.ofType.put(binExpr, TYP.BoolType.type);
						default:
					}
				}else if (b.actualType() == TYP.IntType.type){
					switch(binExpr.oper){
						case MUL:
						case DIV:
						case MOD:
						case ADD:
						case SUB:
							return SemAn.ofType.put(binExpr, TYP.IntType.type);
						default:
					}
				}else if(b.actualType() == TYP.PtrType.type ||b.actualType() == TYP.CharType.type){
					switch(binExpr.oper){
						case EQU:
						case NEQ:
						case LTH:
						case GTH:
						case LEQ:
						case GEQ:	
							return SemAn.ofType.put(binExpr, TYP.BoolType.type);
						default:
					}
				}
				else{
					throw new Report.Error(binExpr, "Tyes should be either int or bool");
				}

				return SemAn.ofType.put(binExpr, a);

			}else{
				throw new Report.Error(binExpr, "Cannot coerce " + a.actualType().toString() + " into " + b.actualType().toString());
			}
		}
		return null;
		
		/*if(!(a.actualType() == b.actualType())){
			throw new Report.Error("Coersion not implemented");
		}
		if(a.actualType() instanceof TYP.IntType){

		}*/
	}

	@Override
	public TYP.Type visit(AST.CallExpr callExpr, Mode arg) {
		if(arg == Mode.RESOLVE){

		}
		
		return null;
	}

	@Override
	public TYP.Type visit(AST.CastExpr castExpr, Mode arg) {
		castExpr.type.accept(this, arg);
		castExpr.expr.accept(this,arg);
		if(arg==Mode.RESOLVE){
			SemAn.defAt.get(castExpr.expr).accept(this, arg);
			TYP.Type b = SemAn.isType.get(castExpr.type);
			if(b.actualType() == TYP.VoidType.type)
				throw new Report.Error(castExpr, "Cannot cast to void");

			return SemAn.ofType.put(castExpr, b);
		}
		

		return null;
	}

	@Override
	public TYP.Type visit(AST.CompExpr compExpr, Mode arg) {
		TYP.Type b = compExpr.recExpr.accept(this, arg);
		if(arg==Mode.RESOLVE){
			TYP.Type a = SemAn.ofType.get(compExpr);
			if(a == null){
				Report.warning("fuck");
				if(b!=null)
					Report.info("ni null");
				return SemAn.ofType.put(compExpr, TYP.VoidType.type);	
			}
			return SemAn.ofType.put(compExpr, a);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.NameExpr nameExpr, Mode arg) {
		if(arg == Mode.RESOLVE){
			TYP.Type a = SemAn.defAt.get(nameExpr).accept(this, arg);;
			return SemAn.ofType.put(nameExpr, a);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.PfxExpr pfxExpr, Mode arg) {
		if(arg==Mode.RESOLVE){
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
					if(tmp2.actualType() == TYP.VoidType.type){
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
		return null;
	}

	@Override
	public TYP.Type visit(AST.SfxExpr sfxExpr, Mode arg) {
		if(arg == Mode.RESOLVE){
			TYP.Type temp = sfxExpr.subExpr.accept(this, arg);
			if(temp.actualType() == TYP.VoidType.type){
				throw new Report.Error(sfxExpr,"Unable to dereference a null pointer");
			}
			return SemAn.ofType.put(sfxExpr, temp);
		}
		return null;
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
	}

	@Override
	public TYP.Type visit(AST.AssignStmt assignStmt, Mode arg) {
		return null;
	}
	*/
	@Override
	public TYP.Type visit(AST.ExprStmt callStmt, Mode arg) {
		callStmt.expr.accept(this, arg);
		return null;
	}

	/*@Override
	public TYP.Type visit(AST.IfThenStmt ifThenStmt, Mode arg) {
		return null;
	}

	@Override
	public TYP.Type visit(AST.IfThenElseStmt ifThenElseStmt, Mode arg) {
		return null;
	}

	@Override
	public TYP.Type visit(AST.ReturnStmt returnStmt, Mode arg) {
		return null;
	}

	@Override
	public TYP.Type visit(AST.WhileStmt whileStmt, Mode arg) {
		return null;
	}*/
	
}
