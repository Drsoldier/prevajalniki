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
			if(tmp instanceof TYP.VoidType){
				throw new Report.Error(ptrType, "Base pointer cannot be void!");
			}else if(tmp instanceof TYP.NameType){
				
				TYP.Type b = SemAn.isType.get(ptrType.baseType);
				/*if(b instanceof TYP.NameType){
					while(b instanceof TYP.NameType){
						b = b.actualType();
					}
				}*/
				
				if(b instanceof TYP.VoidType){
					throw new Report.Error(ptrType, "Base pointer cannot be void (even if its being cast >:( )!");
				}
				
			}
			TYP.PtrType neki = new TYP.PtrType(tmp);
			return SemAn.isType.put(ptrType,neki);
		}
		return null;
	}


	public boolean istiId(Types<TYP.Type> b, int id){
		for(TYP.Type trenutniNode : b){
			if(trenutniNode instanceof TYP.NameType){
				while(trenutniNode instanceof TYP.NameType){
					trenutniNode = trenutniNode.actualType();
				}
			}
			Report.info(trenutniNode.toString());
			if(trenutniNode instanceof TYP.RecType){
				TYP.RecType temp = (TYP.RecType)(trenutniNode);
				return istiId(temp.compTypes, id);
			}
			if(trenutniNode.id == id){
				return true;
			}
		}
		return false;
	}

	@Override
	public TYP.Type visit(AST.StrType strType, Mode arg) {
		if(arg == Mode.DECLARE){
            TYP.Type typ =  new TYP.StrType(null);
            return SemAn.isType.put(strType, typ);
        } else if (arg == Mode.RESOLVE){
			TYP.Type trenList = SemAn.isType.get(strType);
			LinkedList<TYP.Type> typelist = new LinkedList<TYP.Type>();
            for (Node comp : strType.comps) {
				TYP.Type b = comp.accept(this, arg);
				if(b instanceof TYP.VoidType){
					throw new Report.Error(strType, "Components cannot be void");
				}

				/*if (b instanceof TYP.RecType){
					b = ((AST.CompDefn)comp).type.accept(this, arg);
					Report.info(b.toString());
					Report.info(comp.toString());
					while(b instanceof TYP.RecType){
						SemAn.isType.get(comp);

					}	
				}
				/*Report.info(b.toString() + " trenutniId: " + b.id);
				Report.info("Entering recursive check");
				//check if recursive
				if(b instanceof TYP.RecType){
					TYP.RecType d = (TYP.RecType)b;
					Report.info("Zdaj primerjam trenutniId z " + trenList.id);
					if(istiId(d.compTypes, trenList.id)){

						throw new Report.Error("Possible recursive struct");
					}
				}*/
				

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
			//TYP.NameType tmp = new TYP.NameType(nameType.name);
			//return SemAn.isType.put(nameType, tmp);
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
			TYP.FunType newFunType = new TYP.FunType(a, b);
			return SemAn.isType.put(funType, newFunType);
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
		if(arg==Mode.RESOLVE){
			TYP.Type b = varDefn.type.accept(this, arg);
			TYP.Type a = SemAn.isType.get(varDefn.type);
			return SemAn.ofType.put(varDefn, b);
		}
		return null;
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
				//Report.info("s");
				// AST.ReturnStmt castStmt = (AST.ReturnStmt)stmt;
				// TYP.Type retValue = castStmt.retExpr.accept(this, Mode.RESOLVE);
				// TYP.Type b = SemAn.ofType.get(castStmt.retExpr);
				// if(b != null){
				// 	if(b.actualType() == a.actualType())
				// 		return true;
				// }
				// if(retValue != null){
				// 	if(retValue.actualType() == a.actualType())
				// 		return true;
				// }
				
				return true;
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
			
			
			return true;
			
		}
		if(stmt instanceof AST.WhileStmt){
			AST.WhileStmt castStmt =(AST.WhileStmt)stmt;
			return doesStmtHaveReturn(castStmt.stmts, a);
		}


		return false;
	}

	@Override
	public TYP.Type visit(AST.DefFunDefn defFunDefn, Mode arg) {
		boolean hasReturn = false;
		boolean noError = false;
		TYP.Type returnType = null;
		defFunDefn.pars.accept(this, arg);
		if(arg == Mode.DECLARE){
		}
		if (arg == Mode.RESOLVE){
			TYP.Type a = (defFunDefn.type).accept(this, arg);
			defFunDefn.stmts.accept(this, arg);
			/*for (var stmt : defFunDefn.stmts){
				if(doesStmtHaveReturn(stmt, a)){
					//Report.warning("FFF");
					hasReturn = true;
					returnType = stmt.accept(this, arg);
					if(returnType != null){
						Report.warning(returnType.toString());
					}
					//if(returnType instanceof a){
						noError = true;
					//}

				
				}
			}
			if(!hasReturn)
				throw new Report.Error(defFunDefn,"Function should have a return statement");

			if(!noError){
				throw new Report.Error(defFunDefn,"Function returns wrong type");
			}*/

			
			return SemAn.ofType.put(defFunDefn, a);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.ExtFunDefn extFunDefn, Mode arg) {
		
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

}
