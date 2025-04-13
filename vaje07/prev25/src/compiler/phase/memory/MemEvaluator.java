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
            /*if(node instanceof AST.VarDefn a){
                Report.info(a, "Visiting varDefn: " + a.name + ", " + arg.toString());
            }*/
            node.accept(this, arg);
            /*if(node instanceof AST.VarDefn a){
                Report.info(node, "State of arg:" +  arg.toString());
            }*/
		}
        
		
		return null;
	}
    
    @Override
    public Object visit(AST.VarDefn varDefn, Neki arg) {
        long correctedOffset = 0;
        varDefn.type.accept(this, arg);
        long size = SemAn.ofType.get(varDefn).accept(sizeEval, null);
        //Report.info("size:" + size);
        //Prištej do večkratnika 8
        correctedOffset = size;
        if(correctedOffset%8 != 0 && !arg.isInFunctionParam){
            correctedOffset = (correctedOffset/8)*8 +8;
        }
        //size = size + (8-size%8);
        //Report.info("correctedOffset after arithmetic:" + correctedOffset);

        long prefix = -1;
        if(arg.isInFunctionParam){
            prefix = 1;
        }

        //Report.info(varDefn, "Visiting varDefn: " + varDefn.name + ", " + arg.toString() + ", size: " + size);
        if(arg.depth < 0){
            return Memory.accesses.put(varDefn, new MEM.AbsAccess(size, new MEM.Label(varDefn.name)));
        }
        arg.offset += correctedOffset*prefix;
        return Memory.accesses.put(varDefn, new MEM.RelAccess(size, arg.offset, arg.depth));
        
    }
    
    @Override
    public Object visit(AST.DefFunDefn defFunDefn, Neki arg) {
        MEM.Label label;
        
        int k = 0;
        var oldDepth = arg.depth;
        //var size = Memory.accesses.get(defFunDefn).size;
        //Report.info(defFunDefn, "arg state before params: " + arg.toString());
        if(arg.depth < 0){
            arg.depth = 0;
        }
        if(arg.isInFunction){
            label = new MEM.Label();
        }else{
            arg.isInFunction = true;
            k = 1;
            label = new MEM.Label(defFunDefn.name);
        }

        var sizeOfBeforePars = arg.size;
        var offsetWhenEntry = arg.offset;
        arg.offset = 0;
        arg.isInFunctionParam = true;
        var oldArgSize = arg.size;

        arg.size = 0;
        //parameters
        defFunDefn.pars.accept(this, arg);
        arg.isInFunctionParam = false;
        if(arg.size%8 != 0){
            arg.size = (arg.size/8)*8 + 8;
        }
        arg.offset = 0;
        //arg.changeState();
        arg.depth+=1;
        
        //statements
        //Report.info(defFunDefn,"In function " + label.name+", with state of args:" +  arg.toString() + ", old size:" + sizeOfBeforePars);
        defFunDefn.stmts.accept(this, arg);
        var sizeOfFunction = sizeOfBeforePars-arg.offset + arg.size + 2*8;
        MEM.Frame access = new MEM.Frame(
            label, //Label
            arg.depth-1, //Depth of the function
            sizeOfBeforePars-arg.offset, //Size of local variables
            arg.size, //Size of arguments
            sizeOfFunction //Size of the function
        );
        //arg.depth-=2;
        if(k==1){
            arg.isInFunction = false;
        }
        arg.offset = offsetWhenEntry;
        arg.depth = oldDepth;
        arg.size = oldArgSize;
        //arg.changeState();
        //Report.info(defFunDefn, "AAAAAAAAAAAAAAAAAAAAAAAAA-arg state after params: " + arg.toString());
        return Memory.frames.put(defFunDefn, access);
    }

    @Override
    public Object visit(AST.ExtFunDefn extFunDefn, Neki arg) {
        var label = new MEM.Label(extFunDefn.name);
        //var size = Memory.accesses.get(defFunDefn).size;
        var oldDepth = arg.depth;
        //arg.depth+=1;
        arg.depth += 2;
        var oldSize = arg.size;
        var curOffset = arg.offset;
        arg.isInFunctionParam = true;
        arg.offset = 0;
        extFunDefn.pars.accept(this, arg);
        arg.depth=oldDepth;
        arg.isInFunctionParam = false;
        arg.offset = curOffset;
        arg.size = oldSize;
        return label;
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
        //Report.warning("in params");
        b.depth = arg.depth;
        b.offset = arg.offset;
        var tmpSize = arg.size;
        var size = SemAn.ofType.get(parDefn);
        long l = size.accept(sizeEval, null);
        long correctedOffset = l;
        if(correctedOffset%8 != 0 ){
            correctedOffset = (correctedOffset/8)*8 +8;
        }
        //Report.info(parDefn, "Visiting parDefn: " + parDefn.name + ", " + arg.toString() + ", size: " + l);
        long prefix = -1;
        //offset is positive
        if(arg.isInFunctionParam){
            prefix = 1;
        }
        
        if(size instanceof TYP.RecType){
            Neki n = new Neki(arg.depth, l, arg.offset+correctedOffset*prefix);
            parDefn.type.accept(this, n);
            arg.offset = n.size;
        }else{
            b.offset += correctedOffset;
            parDefn.type.accept(this, b);
            arg.offset = b.offset;
        }
        arg.size += l;
        var t = new MEM.RelAccess(l, arg.offset, arg.depth);
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
        for(AST.CompDefn n : uniType.comps){
            arg.offset=0;
            n.accept(this, arg);
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
                return access;
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
        return access;
    }

    @Override
    public Object visit(AST.CompDefn compDefn, Neki arg) {

        TYP.Type b = SemAn.isType.get(compDefn.type);
        long size = b.accept(sizeEval, null);
        long oo = size;
        if(size%8 != 0 && !arg.isInFunctionParam){
            oo = (size/8)*8 +8;
        }
        if(b instanceof TYP.RecType){
            Neki n = new Neki(arg.depth, size, 0);
            compDefn.type.accept(this, n);
        }else{
            //naprej
            compDefn.type.accept(this, arg);
        }
        MEM.RelAccess access = new MEM.RelAccess(size, arg.offset, -1);
        arg.offset = arg.offset + oo;

        Report.info(compDefn, access.toString() + " " + compDefn.name);
        return Memory.accesses.put(compDefn, access);
    }
    
    @Override
    public Object visit(AST.AtomExpr atomExpr, Neki arg) {
        switch (atomExpr.type) {
        case STR:
            long size = (long)(atomExpr.value.length());
            String str = atomExpr.value.substring(1, atomExpr.value.length()-1);
            var lbl = new MEM.Label();
            var idkAnymore = new MEM.AbsAccess(size-1, lbl, str);
            //var idkAnymore = new MEM.RelAccess(1l, 1l, 1l);
            //Report.info(idkAnymore.label.name +  " " + str);
            var node = atomExpr;
            //AST.Type node = atomExpr.type.accept(this, null);
            if(atomExpr instanceof AST.Node){   
                //Report.info("FUCK");
            }
            Memory.strings.put(node, idkAnymore);
            break;    
        default:
            break;
        }
        return null;
    }
}