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
	
	/** Does file have main */
	static boolean hasMain;
	/** Constructs a new name resolver. */
	public TypeResolver() {
		hasMain = false;
	}


	@Override
	public TYP.Type visit(Nodes<? extends AST.Node> nodes, Mode arg) {
		//Report.info("aaaaaaaaa");
		for (final AST.Node node : nodes){
			node.accept(this, Mode.DECLARE);
		}
		for (final AST.Node node : nodes){
			node.accept(this, NameResolver.Mode.RESOLVE);
		}
		
		if(!hasMain){
			throw new Report.Error("No main function found!");
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
				
			}
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
				

				if(tmp instanceof TYP.VoidType){
					throw new Report.Error(ptrType, "Base pointer cannot be void (even if its being cast >:( )!");
				}
				
			}
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
			LinkedList<String> names = new LinkedList<String>();
			LinkedList<AST.Node> comps = new LinkedList<AST.Node>();
            for (Node comp : strType.comps) {
                TYP.Type b = comp.accept(this, arg);
				AST.CompDefn compDefn = (AST.CompDefn)comp;
				comps.addLast(compDefn);
				if(b instanceof TYP.VoidType){
					throw new Report.Error(strType, "Components cannot be void");
				}
				names.addLast(compDefn.name);
                typelist.addLast(b);
            }
			var typ = new TYP.StrType(typelist);
			typ.names = names;
			typ.componentNodes = comps;
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
			LinkedList<String> names = new LinkedList<String>();
			LinkedList<AST.Node> componentsNodes = new LinkedList<AST.Node>();

            for (Node comp : uniType.comps) {
                TYP.Type b = comp.accept(this, arg);
				AST.CompDefn compDefn = (AST.CompDefn)comp;
				if(b instanceof TYP.VoidType){
					throw new Report.Error(uniType, "Components cannot be void");
				}
				componentsNodes.addLast(compDefn);
				names.addLast(compDefn.name);
                typelist.addLast(b);
            }
			var typ = new TYP.UniType(typelist);
			typ.names = names;
			typ.componentNodes = componentsNodes;
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
			//SemAn.pr.put(typDefn, false);
			//SemAn.isAddr.put(typDefn, true);
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

			if(b instanceof TYP.VoidType){
				throw new Report.Error(varDefn, "Type cannot be void");
			}
			return SemAn.ofType.put(varDefn, b);
		}
		return null;
	}



	@Override
	public TYP.Type visit(AST.DefFunDefn defFunDefn, Mode arg) {
		
		if(arg == Mode.DECLARE){
			if(defFunDefn.name.equals("main")){
				hasMain = true;
			}
		}
		if (arg == Mode.RESOLVE){
			LinkedList<TYP.Type> params = new LinkedList<TYP.Type>();
			for(var param : defFunDefn.pars){
				TYP.Type resolvedParam = param.accept(this, arg);
				params.addLast(resolvedParam);
			}
			TYP.Type a = (defFunDefn.type).accept(this, arg);
			defFunDefn.stmts.accept(this, arg);

			return SemAn.ofType.put(defFunDefn, new TYP.FunType(params, a));
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.ExtFunDefn extFunDefn, Mode arg) {
		
		if (arg == Mode.RESOLVE){

			LinkedList<TYP.Type> params = new LinkedList<TYP.Type>();
			for(var param : extFunDefn.pars){
				TYP.Type resolvedParam = param.accept(this, arg);
				params.addLast(resolvedParam);
			}
			TYP.Type b = (extFunDefn.type).accept(this, arg);
			

			
			return SemAn.ofType.put(extFunDefn,  new TYP.FunType(params, b));
			
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
		if(arg == Mode.DECLARE){
			TYP.NameType tmp = new TYP.NameType(compDefn.name);
			return SemAn.isType.put(compDefn, tmp);
		}
		if(arg==Mode.RESOLVE){
			TYP.Type a = compDefn.type.accept(this, arg);
			Report.info(a.toString());
			return SemAn.ofType.put(compDefn, a);
		}

		return null;
	}

}
