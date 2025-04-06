package compiler.phase.memory;

import java.util.*;

import compiler.phase.abstr.*;
import compiler.phase.abstr.AST.LetStmt;
import compiler.phase.abstr.AST.Nodes;
import compiler.phase.seman.*;
import compiler.phase.seman.NameResolver.*;
import compiler.phase.memory.*;
import compiler.phase.memory.Neki;
import compiler.common.report.*;


/**
 * Computing memory layout: stack frames and variable accesses.
 * 
 * @author bostjan.slivnik@fri.uni-lj.si
 */
public class MemEvaluator implements AST.FullVisitor<Object, Neki> {
    public static SizeEvaluator sizeEval = new SizeEvaluator();
    
    
    
    @Override
	public TYP.Type visit(Nodes<? extends AST.Node> nodes, Neki arg) {
        //if(arg == null)
            //Report.info(nodes.get(0), nodes.get(0).getClass().toString() + "FUUUUUUUUUUUCK");
        if(arg == null)
            arg = new Neki();
        
        for (final AST.Node node : nodes)   {
            if(node instanceof AST.VarDefn a){
                //Report.info(a, "Visiting varDefn: " + a.name + ", " + arg.toString());
            }
            node.accept(this, arg);
		}
        
		
		return null;
	}
    
    @Override
    public Object visit(AST.VarDefn varDefn, Neki arg) {
        varDefn.type.accept(this, arg);
        long size = SemAn.ofType.get(varDefn).accept(sizeEval, null);
        //Prištej do večkratnika 8
        size = size + (8-size%8);
        long prefix = -1;
        if(arg.isInFunctionParam){
            prefix = 1;
        }

        //Report.info(varDefn, "Visiting varDefn: " + varDefn.name + ", " + arg.toString());
        if(arg.depth < 0){
            return Memory.accesses.put(varDefn, new MEM.AbsAccess(size, new MEM.Label(varDefn.name)));
        }
        arg.offset += size*prefix;
        return Memory.accesses.put(varDefn, new MEM.RelAccess(size, arg.offset, arg.depth));
        
    }
    
    @Override
    public Object visit(AST.DefFunDefn defFunDefn, Neki arg) {
        var label = new MEM.Label(defFunDefn.name);
        //var size = Memory.accesses.get(defFunDefn).size;
        var o = arg.depth;
        arg.depth+=1;
        var curSize = arg.size;
        var currOffset = arg.offset;
        MEM.Frame access = new MEM.Frame(label, arg.depth, 0, curSize, curSize);
        arg.changeState();
        Report.info(defFunDefn,"current args"+ arg.toString());
        //Report.info("State of arg:" +  arg.toString());
        defFunDefn.pars.accept(this, arg);
        //Report.info("going out of params");
        arg.changeState();
        arg.depth+=1;
        //Report.info("going into statements");
        //Report.info("State of arg:" +  arg.toString());
        arg.offset = 0;
        defFunDefn.stmts.accept(this, arg);
        //Report.info("going out of statements");
        arg.depth-=1;
        arg.offset = currOffset;
        //var neki = Memory.accesses.get(defFunDefn);
        //Report.info("null"+neki);
        arg.depth = o;
        return Memory.frames.put(defFunDefn, access);
    }

    @Override
    public Object visit(AST.ExtFunDefn extFunDefn, Neki arg) {
        var label = new MEM.Label(extFunDefn.name);
        //var size = Memory.accesses.get(defFunDefn).size;
        var curDepth = arg.depth+2;
        //arg.depth+=1;
        var oldSize = arg.size;
        var curOffset = arg.offset;
        extFunDefn.pars.accept(this, arg);
        arg.depth=curDepth;
        arg.offset = curOffset;
        arg.size = oldSize;
        return null;
    }

    /*@Override
    public Object visit(LetStmt letStmt, Neki arg) {
        Report.info(letStmt, "Visiting let statement at depth: " + arg.depth);
        letStmt.defns.accept(this,arg);
        letStmt.stmts.accept(this, arg);
        return null;
    }*/
  
    @Override
    public Object visit(AST.ParDefn parDefn, Neki arg) {

        var b = new Neki();
        b.isInFunctionParam = arg.isInFunctionParam;
        Report.warning("in params");
        b.depth = arg.depth;
        var size = SemAn.ofType.get(parDefn);
        long l = size.accept(sizeEval, null);

        long prefix = -1;
        //offset is positive
        if(arg.isInFunctionParam){
            prefix = 1;
        }
        
        if(size instanceof TYP.RecType){
            Neki n = new Neki(arg.depth, l, arg.offset+l*prefix);
            parDefn.type.accept(this, n);
            arg.offset = n.size;
        }else{
            arg.offset += l;
            parDefn.type.accept(this, b);
        }
       

        var t = new MEM.RelAccess(l, arg.offset+l*prefix, arg.depth);
        return Memory.accesses.put(parDefn, t);
    }


    @Override
	public TYP.Type visit(AST.StrType strType, Neki arg) {
        var o = arg.offset;
        strType.comps.accept(this, null);
        arg.offset = o;
		return null;
	}
	@Override
	public TYP.Type visit(AST.UniType uniType, Neki arg) {
        var o = arg.offset;
        arg.offset=0;
        for(AST.CompDefn n : uniType.comps){
            n.accept(this, arg);
            arg.offset=0;
        }
        arg.offset = o;
        return null;
	}

    @Override
    public Object visit(AST.TypDefn typDefn, Neki arg) {
        //var size = SemAn.ofType.get(typDefn);
        //var access = new MEM.AbsAccess(size.accept(sizeEval, null), new MEM.Label(typDefn.name));
        var type = SemAn.isType.get(typDefn);
        long prefix = -1;
        if(arg.isInFunctionParam){
            prefix = 1;
        }
        if(type instanceof TYP.RecType){
            var off = arg.offset;
            arg.offset = 0;
            typDefn.type.accept(this,arg);
            arg.offset = off;
            var n = new Neki();
            n.depth = arg.depth;
            typDefn.accept(this,n);
            var size = SemAn.ofType.get(typDefn).accept(sizeEval, null);
            if(n.depth >= 0){
                var access = new MEM.RelAccess(size, n.size, n.depth);
                //return Memory.accesses.put(typDefn, access);
                return null;
            }
            return null;
            //return Memory.accesses.put(typDefn, new MEM.AbsAccess(size, new MEM.Label(typDefn.name)));
        }
        typDefn.type.accept(this,arg);
        if(arg.depth >= 0){
            var size = type.accept(sizeEval, null);
            var access = new MEM.RelAccess(-1, arg.offset+size*prefix, arg.depth);
            //Memory.accesses.put(typDefn, access);
            return null;
        }
        //var size = SemAn.ofType.get(typDefn).accept(sizeEval, null);
            var access = new MEM.AbsAccess(-1, new MEM.Label(typDefn.name));
        //Memory.accesses.put(typDefn, access);
        return null;
    }

    @Override
    public Object visit(AST.CompDefn compDefn, Neki arg) {

        TYP.Type b = SemAn.ofType.get(compDefn);
        long size = b.accept(sizeEval, null);
        long oo = size;
        if(size%8 != 0){
            oo = (size/8)*8 +8;
        }
        if(b instanceof TYP.RecType){
            Neki n = new Neki(arg.depth, size, 0);
            compDefn.type.accept(this, n);
            /*if(n.size%8 != 0){
                n.size = n.size + (8-n.size%8);
            }else{
                size = n.size;
            }*/
            //arg.offset += n.size;
        }else{
            //naprej
            compDefn.type.accept(this, arg);
        }
        MEM.RelAccess access = new MEM.RelAccess(size, arg.offset, -1);
        arg.offset = arg.offset + oo;

        
        return Memory.accesses.put(compDefn, access);
    }
    
    @Override
    public Object visit(AST.AtomExpr atomExpr, Neki arg) {
        switch (atomExpr.type) {
        case STR:
            var size = (long)(atomExpr.value.length() + 1);
            var access2 = new MEM.Temp();
            break;
        default:
            break;
        }
        return null;
    }
}