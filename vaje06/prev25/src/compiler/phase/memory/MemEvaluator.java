package compiler.phase.memory;

import java.util.*;
import compiler.phase.abstr.*;
import compiler.phase.abstr.AST.Nodes;
import compiler.phase.seman.*;
import compiler.phase.seman.NameResolver.*;
import compiler.phase.memory.*;
import compiler.phase.memory.MEM.Neki;
import compiler.common.report.*;


/**
 * Computing memory layout: stack frames and variable accesses.
 * 
 * @author bostjan.slivnik@fri.uni-lj.si
 */
public class MemEvaluator implements AST.FullVisitor<Object, MEM.Neki> {
    public static SizeEvaluator sizeEval = new SizeEvaluator();
    public static MEM.Neki neki;
    
    @Override
    public Object visit(AST.VarDefn varDefn, MEM.Neki arg) {
        
        Memory.accesses.put(varDefn, new MEM.AbsAccess(0, new MEM.Label(varDefn.name)));
        return null;
    }
    
    
    @Override
	public TYP.Type visit(Nodes<? extends AST.Node> nodes, Neki arg) {
		//Report.info("aaaaaaaaa");
        neki = new Neki();
        for (final AST.Node node : nodes){
			node.accept(this, neki);
		}

		
		return null;
	}


    @Override
    public Object visit(AST.DefFunDefn defFunDefn, Neki arg) {
        var label = new MEM.Label(defFunDefn.name);
        //var size = Memory.accesses.get(defFunDefn).size;
        var curDepth = arg.depth+1;
        var curSize = arg.size;
        var curOffset = arg.offset;
        arg.depth+=1;
        defFunDefn.pars.accept(this, arg);
        MEM.Frame access = new MEM.Frame(label, curDepth, 0, 0, 0);
        //var neki = Memory.accesses.get(defFunDefn);
        //Report.info("null"+neki);
        Memory.frames.put(defFunDefn, access);
        return access;
    }

    @Override
    public Object visit(AST.ParDefn parDefn, Neki arg) {
        
        var size = SemAn.ofType.get(parDefn);
        long l = size.accept(sizeEval, null) + 1l;
        l = size.accept(sizeEval, null) + 1l;
        

       if(size instanceof TYP.RecType){
            Neki n = new Neki(arg.depth, l, 0);
            parDefn.type.accept(this, n);
            arg.offset = n.size;
        }else{
            arg.offset += l;
            //naprej
            parDefn.type.accept(this, arg);
        }
       
        parDefn.type.accept(this, arg);

        var t = new MEM.RelAccess(l, arg.offset, arg.depth);
        return Memory.accesses.put(parDefn, t);
    }

    @Override
    public Object visit(AST.CompDefn compDefn, Neki arg) {
        TYP.Type b = SemAn.ofType.get(compDefn);
        long size = b.accept(sizeEval, null);
        
        var access = new MEM.RelAccess(size, arg.offset, -1);
        if(b instanceof TYP.RecType){
            Neki n = new Neki(arg.depth, size, 0);
            compDefn.type.accept(this, n);
            arg.offset = n.size;
        }else{
            arg.offset += size;
            //naprej
            compDefn.type.accept(this, arg);
        }
        
        return Memory.accesses.put(compDefn, access);
    }

    @Override
    public Object visit(AST.AtomExpr atomExpr, Neki arg) {
        switch (atomExpr.type) {
        case STR:
            break;
        default:
            break;
        }
        return null;
    }
}
