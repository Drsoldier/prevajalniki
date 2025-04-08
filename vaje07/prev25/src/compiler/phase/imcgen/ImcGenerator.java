package compiler.phase.imcgen;

import compiler.phase.abstr.*;
import compiler.phase.abstr.AST.CastExpr;
import compiler.phase.abstr.AST.Nodes;
import compiler.phase.memory.*;
import compiler.phase.seman.*;
import compiler.common.report.*;
import java.util.*;

/**
 * Intermediate code generator.
 * 
 * @author bostjan.slivnik@fri.uni-lj.si
 */
public class ImcGenerator implements AST.FullVisitor<Object, Object> {

    @Override
	public Object visit(Nodes<? extends AST.Node> nodes, Object arg) {
        if(arg == null)
            arg = new NekiNovega();
        
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
    public IMC.Stmt visit(AST.AssignStmt assignStmt, Object arg) {
        var dstExpr = (IMC.Expr) assignStmt.dstExpr.accept(this, arg);
        var srcExpr = (IMC.Expr) assignStmt.srcExpr.accept(this, arg);
        IMC.Stmt l = new IMC.MOVE(
                dstExpr,
                srcExpr
        );
        var b = new IMC.CONST(Long.valueOf(0));

        return ImcGen.stmt.put(assignStmt, l);
    }

    @Override
    public Object visit(AST.LetStmt letStmt, Object arg) {
        //var neki = new NekiNovega(letStmt);
        Vector<IMC.Stmt> vec = new Vector<IMC.Stmt>();
        Report.info("null");
        for (var n : letStmt.defns){
            n.accept(this, arg);
        }
        for (var n : letStmt.stmts){
            IMC.Stmt t =(IMC.Stmt) n.accept(this, arg);
            vec.addLast(t);
        }
        
        var AAAAAA = new IMC.STMTS(vec);
        return ImcGen.stmt.put(letStmt, AAAAAA);
    }

    @Override
    public IMC.Stmt visit(AST.ExprStmt exprStmt, Object arg) {
        IMC.Expr o = (IMC.Expr)exprStmt.expr.accept(this, arg);
        var b = new IMC.ESTMT(o);
        return ImcGen.stmt.put(exprStmt, b);
    }

    @Override
    public Object visit(AST.DefFunDefn defFunDefn, Object arg) {
        MEM.Frame neki = Memory.frames.get(defFunDefn);
        //NekiNovega frame = (NekiNovega)arg;
        var l1 = new MEM.Label();
        var l2 = new MEM.Label();
        ImcGen.entryLabel.put(defFunDefn, l1);
        ImcGen.exitLabel.put(defFunDefn, l2);
        NekiNovega nekiNovega = new NekiNovega(defFunDefn);
        nekiNovega.l1 = l1;
        nekiNovega.l2 = l2;
        /*for(var n : defFunDefn.stmts){
            IMC.Stmt d = (IMC.Stmt)n.accept(this, arg);
            Report.info(d.toString());
            //ImcGen.stmt.put(defFunDefn, d);
        }*/
        defFunDefn.stmts.accept(this, nekiNovega);
        //var l = new IMC.NAME(defFunDefn.label);
        return null;
    }

    @Override 
    public Object visit(AST.IfThenStmt ifThenStmt, Object arg){
        NekiNovega nekiNovega = (NekiNovega)arg;
        Vector<IMC.Expr> vec2 = new Vector<IMC.Expr>();
        Vector<IMC.Stmt> vec = new Vector<IMC.Stmt>();
        IMC.Expr vec5 = null;
        IMC.Expr vec7 = null;
        var l1 = new MEM.Label();
        IMC.LABEL ooo = new IMC.LABEL(l1);
        vec5 = new IMC.NAME(l1);
        var neki = (IMC.Expr)ifThenStmt.condExpr.accept(this, arg);
        //vec.addLast((IMC.Stmt)ooo);
        for (var n : ifThenStmt.thenStmt){
            IMC.Stmt t =(IMC.Stmt) n.accept(this, arg);
            vec.addLast(t);
            vec2.addLast(((NekiNovega)arg).lastExpr);
        }
        var l3 = new MEM.Label();
        IMC.LABEL l4 = new IMC.LABEL(l3);
        //vec.addLast(l4);
        vec7 = new IMC.NAME(l3);
        Vector<IMC.Stmt> vec3 = new Vector<IMC.Stmt>();
        var b = new IMC.CJUMP(neki, vec5, vec7);
        var l = new IMC.STMTS(vec);
        vec3.addLast(b);
        vec3.addLast(l4);
        vec3.addLast(l);
        vec3.addLast(new IMC.JUMP(vec5));
        vec3.addLast(new IMC.LABEL(new MEM.Label()));
        return ImcGen.stmt.put(ifThenStmt, new IMC.STMTS(vec3));

    }


    @Override 
    public Object visit(AST.IfThenElseStmt ifThenElseStmt, Object arg){
        NekiNovega nekiNovega = (NekiNovega)arg;
        Vector<IMC.Expr> vec2 = new Vector<IMC.Expr>();
        Vector<IMC.Stmt> vec = new Vector<IMC.Stmt>();
        IMC.Expr vec5 = null;
        IMC.Expr vec7 = null;
        var l1 = new MEM.Label();
        IMC.LABEL ooo = new IMC.LABEL(l1);
        vec5 = new IMC.NAME(l1);
        var neki = (IMC.Expr)ifThenElseStmt.condExpr.accept(this, arg);
        //vec.addLast((IMC.Stmt)ooo);
        for (var n : ifThenElseStmt.thenStmt){
            IMC.Stmt t =(IMC.Stmt) n.accept(this, arg);
            vec.addLast(t);
            vec2.addLast(((NekiNovega)arg).lastExpr);
        }
        var l3 = new MEM.Label();
        IMC.LABEL l4 = new IMC.LABEL(l3);
        //vec.addLast(l4);
        vec7 = new IMC.NAME(l3);
        Vector<IMC.Stmt> vec3 = new Vector<IMC.Stmt>();
        var b = new IMC.CJUMP(neki, vec5, vec7);
        var l = new IMC.STMTS(vec);
        vec3.addLast(b);
        vec3.addLast(l4);
        vec3.addLast(l);
        vec3.addLast(new IMC.JUMP(vec5));
        vec3.addLast(new IMC.LABEL(new MEM.Label()));
        Vector<IMC.Stmt> tmp = new Vector<IMC.Stmt>();
        for (var n : ifThenElseStmt.thenStmt){
            IMC.Stmt t =(IMC.Stmt) n.accept(this, arg);
            tmp.addLast(t);
        }
        for(var n : tmp){
            vec3.addLast(n);
        }
        var lbl = new MEM.Label();
        IMC.LABEL neki2 = new IMC.LABEL(lbl);
        vec3.addLast(new IMC.JUMP(new IMC.NAME(lbl)));
        vec3.addLast(neki2);

        return ImcGen.stmt.put(ifThenElseStmt, new IMC.STMTS(vec3));

    }

    @Override
    public IMC.Expr visit(AST.NameExpr nameExpr, Object arg) {
        var tmp = SemAn.defAt.get(nameExpr);
        if(tmp instanceof AST.DefFunDefn){
            var tmp2 = ImcGen.entryLabel.get(tmp);
            var neki = new IMC.NAME(tmp2);
            ((NekiNovega)arg).lastExpr = neki;
            return ImcGen.expr.put(nameExpr, neki);
        }
        TYP.Type t = SemAn.ofType.get(nameExpr);
        NekiNovega nekiNovega = (NekiNovega)arg;
        var neki = (MEM.Access)Memory.accesses.get(tmp);
        IMC.Expr nekaj = null;

        if(neki instanceof MEM.AbsAccess) {
            nekaj = new IMC.NAME(((MEM.AbsAccess) neki).label);
        }
        else if (neki instanceof MEM.RelAccess) {
            var getFrame = Memory.frames.get(nekiNovega.funDefn);
            IMC.NAME n = new IMC.NAME(getFrame.label);
            IMC.BINOP binop = new IMC.BINOP(
                    IMC.BINOP.Oper.ADD,
                    n,
                    new IMC.CONST(((MEM.RelAccess) neki).offset)
            );
            nekaj = binop;

        }
        if(t instanceof TYP.BoolType || t instanceof TYP.CharType){
            var n = new IMC.MEM1(nekaj);
            ((NekiNovega)arg).lastExpr = n;

            return ImcGen.expr.put(nameExpr, n);
        }
        IMC.MEM8 m8 = new IMC.MEM8(nekaj);
        ((NekiNovega)arg).lastExpr = m8;

        return ImcGen.expr.put(nameExpr, m8);
    }
    
    @Override
    public Object visit(AST.SfxExpr sfxExpr, Object arg) {
        var neki = (IMC.Expr)sfxExpr.subExpr.accept(this, arg);
        if(neki == null){
            Report.warning(sfxExpr, "SfxExpr: " + sfxExpr.toString());
            neki = new IMC.CONST(Long.valueOf(0));
        }
        IMC.Expr sfx = null;
        TYP.Type t = SemAn.ofType.get(sfxExpr.subExpr);
        if(t instanceof TYP.BoolType || t instanceof TYP.CharType){
            sfx = new IMC.MEM1(neki);
        }else 
            sfx = new IMC.MEM8(neki);

        ((NekiNovega)arg).lastExpr = sfx;

        return ImcGen.expr.put(sfxExpr, sfx);
    }

    @Override
    public IMC.Expr visit(CastExpr castExpr, Object arg) {
        var castType = SemAn.ofType.get(castExpr);
        if (castType instanceof TYP.BoolType) {
            return ImcGen.expr.put(castExpr,
                    new IMC.BINOP(IMC.BINOP.Oper.MOD, (IMC.Expr) castExpr.expr.accept(this, arg), new IMC.CONST(2)));
        } else if (castType instanceof TYP.CharType) {
            return ImcGen.expr.put(castExpr,
                    new IMC.BINOP(IMC.BINOP.Oper.MOD, (IMC.Expr) castExpr.expr.accept(this, arg), new IMC.CONST(256)));
        } else {
            return ImcGen.expr.put(castExpr, (IMC.Expr) castExpr.expr.accept(this, arg));
        }
    }

    public Object visit(AST.BinExpr binExpr, Object arg) {
        var left = (IMC.Expr)binExpr.fstExpr.accept(this, arg);
        var right = (IMC.Expr)binExpr.sndExpr.accept(this, arg);
        IMC.BINOP binop = null;
        switch(binExpr.oper) {
            case ADD:
                binop = new IMC.BINOP(IMC.BINOP.Oper.ADD, left, right);
                break;
            case SUB:
                binop = new IMC.BINOP(IMC.BINOP.Oper.SUB, left, right);
                break;
            case MUL:
                binop = new IMC.BINOP(IMC.BINOP.Oper.MUL, left, right);
                break;
            case DIV:
                binop = new IMC.BINOP(IMC.BINOP.Oper.DIV, left, right);
                break;
            case MOD:
                binop = new IMC.BINOP(IMC.BINOP.Oper.MOD, left, right);
                break;
            case AND:
                binop = new IMC.BINOP(IMC.BINOP.Oper.AND, left, right);
                break;
            case OR:
                binop = new IMC.BINOP(IMC.BINOP.Oper.OR, left, right);
                break;
            case EQU:
                binop = new IMC.BINOP(IMC.BINOP.Oper.EQU, left, right);
                break;
            case NEQ:
                binop = new IMC.BINOP(IMC.BINOP.Oper.NEQ, left, right);
                break;
            case LTH:
                binop = new IMC.BINOP(IMC.BINOP.Oper.LTH, left, right);
                break;
            case LEQ:
                binop = new IMC.BINOP(IMC.BINOP.Oper.LEQ, left, right);
                break;
            case GTH:
                binop = new IMC.BINOP(IMC.BINOP.Oper.GTH, left, right);
                break;
            case GEQ:
                binop = new IMC.BINOP(IMC.BINOP.Oper.GEQ, left, right);
                break;
        }
        ((NekiNovega)arg).lastExpr = binop;

        return ImcGen.expr.put(binExpr, binop);
    }

    public long whyDoIHaveToCastThisShit(String str){

        if(str.charAt(0)=='\''){
            str = str.substring(1, str.length()-1);
        }
        if(str.length() == 1){
            return (long)str.charAt(0);
        }
        if(str.charAt(0) == '\\' && str.charAt(1) == '0'){
            char c = str.charAt(3);
            char d = str.charAt(4);
            //convert string to hex to long
            int o = (int)c - 48; //first num
            int p = (int)d - 48; //second num
            int q = o * 16 + p;
            return (long)q;

        }
        return (long)str.charAt(1);
        
    }



    @Override
    public IMC.Expr visit(AST.AtomExpr atomExpr, Object arg) {
        IMC.CONST l = null;
        switch(atomExpr.type) {
            case INT : 
                l = new IMC.CONST(Long.valueOf(atomExpr.value));
                break;
            case BOOL : 
                if(atomExpr.value.equals("true"))
                    l = new IMC.CONST(1);
                else
                    l = new IMC.CONST(0);   
                break;
            case CHAR : 
                l = new IMC.CONST((whyDoIHaveToCastThisShit(atomExpr.value)));
                break;
            case PTR : 
                l = new IMC.CONST(0);
                break;
            default:
                throw new Report.Error("How did we get here?");
        }
        ((NekiNovega)arg).lastExpr = l;

        return ImcGen.expr.put(atomExpr, l);
    }

    public long sizeof(AST.Expr expr){
        var t = SemAn.ofType.get(expr);
        return t.accept(MemEvaluator.sizeEval, null);
        
    }
    @Override
    public Object visit(AST.CallExpr callExpr, Object arg) {
        Vector<IMC.Expr> vec = new Vector<IMC.Expr>();
        Vector<Long> vec1 = new Vector<Long>();
        var acceptedExpr = (IMC.Expr)callExpr.funExpr.accept(this, arg);
        for (var n : callExpr.argExprs){
            IMC.Expr t =(IMC.Expr) n.accept(this, arg);
            vec.addLast(t);
            vec1.addLast(sizeof(n));
        }
        var neki2 = new IMC.CALL(acceptedExpr, vec1, vec);
        ((NekiNovega)arg).lastExpr = neki2;

        return ImcGen.expr.put(callExpr, neki2);
    }

    @Override
    public IMC.Stmt visit(AST.WhileStmt whileStmt, Object arg) {
        NekiNovega nekiNovega = (NekiNovega)arg;
        Vector<IMC.Expr> vec2 = new Vector<IMC.Expr>();
        Vector<IMC.Stmt> vec = new Vector<IMC.Stmt>();
        IMC.Expr vec5 = null;
        IMC.Expr vec7 = null;
        var l1 = new MEM.Label();
        IMC.LABEL ooo = new IMC.LABEL(l1);
        vec5 = new IMC.NAME(l1);
        var neki = (IMC.Expr)whileStmt.condExpr.accept(this, arg);
        //vec.addLast((IMC.Stmt)ooo);
        for (var n : whileStmt.stmts){
            IMC.Stmt t =(IMC.Stmt) n.accept(this, arg);
            vec.addLast(t);
            vec2.addLast(((NekiNovega)arg).lastExpr);
        }
        var l3 = new MEM.Label();
        IMC.LABEL l4 = new IMC.LABEL(l3);
        //vec.addLast(l4);
        vec7 = new IMC.NAME(l3);
        Vector<IMC.Stmt> vec3 = new Vector<IMC.Stmt>();
        var b = new IMC.CJUMP(neki, vec5, vec7);
        var l = new IMC.STMTS(vec);
        vec3.addLast(ooo);
        vec3.addLast(b);
        vec3.addLast(l4);
        vec3.addLast(l);
        vec3.addLast(new IMC.JUMP(vec5));
        vec3.addLast(new IMC.LABEL(new MEM.Label()));
        return ImcGen.stmt.put(whileStmt, new IMC.STMTS(vec3));
    }

    @Override
    public Object visit(AST.ArrExpr arrExpr, Object arg) {
        TYP.Type t = SemAn.ofType.get(arrExpr.arrExpr);
        if(t instanceof TYP.ArrType){
            t = ((TYP.ArrType)t).elemType;
        }
        var neki = (IMC.Expr)arrExpr.arrExpr.accept(this, arg);
        if(neki instanceof IMC.MEM8 b){
            neki = b.addr;
        }else if(neki instanceof IMC.MEM1 b){
            neki = b.addr;
        }
        var index = (IMC.Expr)arrExpr.idx.accept(this, arg);
        var l = ((TYP.ArrType)SemAn.ofType.get(arrExpr.arrExpr)).elemType;
        var length =  SemAn.ofType.get(arrExpr.arrExpr).accept(MemEvaluator.sizeEval, null);
        if(l == null){
            Report.warning(arrExpr, "Array size is null");
            
        }
        Report.info("index: " + l);
        IMC.BINOP binop = new IMC.BINOP(
                IMC.BINOP.Oper.ADD,
                neki,
                new IMC.BINOP(IMC.BINOP.Oper.MUL,
                        index,
                        new IMC.CONST(8l))
        );
        Report.info(t.toString());


        return ImcGen.expr.put(arrExpr, binop);
    }

    @Override
    public Object visit(AST.PfxExpr pfxExpr, Object arg) {

        NekiNovega nekiNovega = (NekiNovega)arg;
        var neki = (IMC.Expr)pfxExpr.subExpr.accept(this, arg);
        if(neki == null){
            Report.warning(pfxExpr, "PfxExpr: " + pfxExpr.toString());
            neki = new IMC.CONST(Long.valueOf(0));
        }
        IMC.Expr pfx = null;
        switch(pfxExpr.oper) {
            case NOT:
                pfx = new IMC.UNOP(IMC.UNOP.Oper.NOT, neki);

                break;
            case ADD:

                break;
            case SUB:
                pfx = new IMC.UNOP(IMC.UNOP.Oper.NEG, neki);
                break;
            case PTR:
                if(neki instanceof IMC.MEM8 b){
                    pfx = b.addr;
                }else if(neki instanceof IMC.MEM1 b){
                    pfx = b.addr;
                }
                Report.warning(pfxExpr,"IDK");

                pfx = new IMC.CONST(Long.valueOf(0));
        }
        ((NekiNovega)arg).lastExpr = pfx;

        /*IMC.Expr neki = (IMC.Expr)pfxExpr.expr.accept(this, arg);
        IMC.BINOP binop = new IMC.BINOP(
                IMC.BINOP.Oper.NEG,
                neki,
                null
        );*/
        return ImcGen.expr.put(pfxExpr, pfx);
    }

    @Override
    public IMC.Stmt visit(AST.ReturnStmt returnStmt, Object arg) {
        Vector<IMC.Stmt> vec = new Vector<IMC.Stmt>();
        var neki = (IMC.Expr)returnStmt.retExpr.accept(this, arg);
        NekiNovega nekiNovega = (NekiNovega)arg;
        MEM.Frame frame = Memory.frames.get(nekiNovega.funDefn);
        IMC.MOVE move = new IMC.MOVE(
                (new IMC.TEMP(frame.RV)),
                neki
        );
        var b = new IMC.JUMP(neki);
        vec.addLast(move);
        vec.addLast(b);
        return ImcGen.stmt.put(returnStmt, new IMC.STMTS(vec));
    }
}
