package compiler.phase.imclin;

import compiler.common.report.*;
import compiler.phase.imcgen.*;
import compiler.phase.memory.*;
import java.util.*;

public class StatementEvaluator implements IMC.Visitor<Vector<IMC.Stmt>, Object> {

    public Vector<IMC.Stmt> visit(IMC.BINOP binOp, Object visArg) {
        throw new Report.InternalError();
    }

    public Vector<IMC.Stmt> visit(IMC.CALL call, Object visArg) {
        throw new Report.InternalError();
    }

    public Vector<IMC.Stmt> visit(IMC.CJUMP cjump, Object visArg) {
        Vector<IMC.Stmt> result= new Vector<IMC.Stmt>();
        IMC.Expr cond = cjump.cond.accept(new ExpressionEvaluator(), result);
        result.add(new IMC.CJUMP(cond, cjump.posAddr, cjump.negAddr));
        return result;
    }

    public Vector<IMC.Stmt> visit(IMC.CONST constant, Object visArg) {
        throw new Report.InternalError();
    }

    public Vector<IMC.Stmt> visit(IMC.ESTMT eStmt, Object visArg) {
        if (eStmt.expr instanceof IMC.CALL) {
            Vector<IMC.Stmt> result = new Vector<IMC.Stmt>();
            IMC.CALL imcCall = (IMC.CALL) eStmt.expr;
            Vector<IMC.Expr> canonArgs = new Vector<IMC.Expr>();
            for (IMC.Expr arg : imcCall.args) {
                IMC.Expr canonArg=arg.accept(new ExpressionEvaluator(), result);
                canonArgs.add(canonArg);
            }
            result.add(new IMC.ESTMT(new IMC.CALL(imcCall.addr, imcCall.offs, canonArgs)));
            return result;
        }
        Vector<IMC.Stmt> result = new Vector<IMC.Stmt>();
        IMC.Expr expr = eStmt.expr.accept(new ExpressionEvaluator(), result);
        result.add(new IMC.ESTMT(expr));
        return result;
    }

    public Vector<IMC.Stmt> visit(IMC.JUMP jump, Object visArg) {
        Vector<IMC.Stmt> result = new Vector<IMC.Stmt>();
        result.add(new IMC.JUMP(jump.addr));
        return result;
    }

    public Vector<IMC.Stmt> visit(IMC.LABEL label, Object visArg) {
        Vector<IMC.Stmt> result = new Vector<IMC.Stmt>();
        result.add(new IMC.LABEL(label.label));
        return result;
    }

    public Vector<IMC.Stmt> visit(IMC.MEM1 mem, Object visArg) {
        throw new Report.InternalError();
    }

    public Vector<IMC.Stmt> visit(IMC.MEM8 mem, Object visArg) {
        throw new Report.InternalError();
    }

    public Vector<IMC.Stmt> visit(IMC.MOVE move, Object visArg) {
        if (move.dst instanceof IMC.MEM1 mem1) {
            Vector<IMC.Stmt> result=new Vector<IMC.Stmt>();
            IMC.Expr dstExpr = mem1.addr.accept(new ExpressionEvaluator(), result);
            MEM.Temp dstTemp = new MEM.Temp();
            result.add(new IMC.MOVE(new IMC.TEMP(dstTemp), dstExpr));
            IMC.Expr srcExpr = move.src.accept(new ExpressionEvaluator(), result);
            MEM.Temp srcTemp = new MEM.Temp();
            result.add(new IMC.MOVE(new IMC.TEMP(srcTemp), srcExpr));
            result.add(new IMC.MOVE(new IMC.MEM1(new IMC.TEMP(dstTemp)), new IMC.TEMP(srcTemp)));
            return result;
        }
        if (move.dst instanceof IMC.MEM8 mem1) {
            Vector<IMC.Stmt> result = new Vector<IMC.Stmt>();
            IMC.Expr dstExpr = mem1.addr.accept(new ExpressionEvaluator(), result);
            MEM.Temp dstTemp = new MEM.Temp();
            result.add(new IMC.MOVE(new IMC.TEMP(dstTemp), dstExpr));
            IMC.Expr srcExpr = move.src.accept(new ExpressionEvaluator(), result);
            MEM.Temp srcTemp = new MEM.Temp();
            result.add(new IMC.MOVE(new IMC.TEMP(srcTemp), srcExpr));
            result.add(new IMC.MOVE(new IMC.MEM8(new IMC.TEMP(dstTemp)), new IMC.TEMP(srcTemp)));
            return result;
        }
        if (move.dst instanceof IMC.TEMP temp) {
            Vector<IMC.Stmt> result = new Vector<IMC.Stmt>();
            MEM.Temp dstTemp = temp.temp;
            IMC.Expr srcExpr = move.src.accept(new ExpressionEvaluator(), result);
            MEM.Temp srcTemp = new MEM.Temp();
            result.add(new IMC.MOVE(new IMC.TEMP(srcTemp), srcExpr));
            result.add(new IMC.MOVE(new IMC.TEMP(dstTemp), new IMC.TEMP(srcTemp)));
            return result;
        }
        throw new Report.InternalError();
    }

    public Vector<IMC.Stmt> visit(IMC.NAME name, Object visArg) {
        throw new Report.InternalError();
    }

    public Vector<IMC.Stmt> visit(IMC.SEXPR sExpr, Object visArg) {
        throw new Report.InternalError();
    }

    public Vector<IMC.Stmt> visit(IMC.STMTS stmts, Object visArg) {
        Vector<IMC.Stmt> res = new Vector<IMC.Stmt>();
        for (IMC.Stmt stmt : stmts.stmts) {
            res.addAll(stmt.accept(this, null));
        }
        return res;
    }

    public Vector<IMC.Stmt> visit(IMC.TEMP temp, Object visArg) {
        throw new Report.InternalError();
    }

    public Vector<IMC.Stmt> visit(IMC.UNOP unOp, Object visArg) {
        throw new Report.InternalError();
    }
}
