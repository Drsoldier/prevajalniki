package compiler.phase.memory;

import java.util.*;
import compiler.phase.abstr.*;
import compiler.phase.seman.*;

/**
 * Computing memory layout: stack frames and variable accesses.
 * 
 * @author bostjan.slivnik@fri.uni-lj.si
 */
public class MemEvaluator implements AST.FullVisitor<Object, Object> {
    @Override
    public Object visit(AST.VarDefn varDefn, Object arg) {
        if (Memory.accesses.get(varDefn) == null)
            return null;
        //log(Memory.accesses.get(varDefn));
        return null;
    }

    @Override
    public Object visit(AST.DefFunDefn defFunDefn, Object arg) {
        if (Memory.frames.get(defFunDefn) == null)
            return null;
        //log(Memory.frames.get(defFunDefn));
        return null;
    }

    @Override
    public Object visit(AST.ParDefn parDefn, Object arg) {
        if (Memory.accesses.get(parDefn) == null)
            return null;
        //log(Memory.accesses.get(parDefn));
        return null;
    }

    @Override
    public Object visit(AST.CompDefn compDefn, Object arg) {
        if (Memory.accesses.get(compDefn) == null)
            return null;
        //log(Memory.accesses.get(compDefn));
        return null;
    }

    @Override
    public Object visit(AST.AtomExpr atomExpr, Object arg) {
        switch (atomExpr.type) {
        case STR:
           // log(Memory.strings.get(atomExpr));
            break;
        default:
            break;
        }
        return null;
    }
}
