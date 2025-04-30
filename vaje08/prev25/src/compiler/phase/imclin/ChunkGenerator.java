package compiler.phase.imclin;

import compiler.common.report.*;
import compiler.phase.memory.*;
import compiler.phase.abstr.*;
import compiler.phase.abstr.AST.Nodes;
import compiler.phase.imclin.LIN.*;
import compiler.phase.imcgen.*;
import compiler.phase.imcgen.IMC.CJUMP;
import compiler.phase.seman.*;

import java.util.*;

public class ChunkGenerator implements AST.FullVisitor<IMC.Expr, ChunkContext> {
    
    public static HashMap<String, IMC.TEMP> nameTempMap = new HashMap<>();

    @Override
	public IMC.Expr visit(AST.Nodes<? extends AST.Node> nodes, ChunkContext arg) {
        
        
        for (final AST.Node node : nodes)   {
            if(node instanceof AST.DefFunDefn){
                arg = new ChunkContext();
            }
            node.accept(this, arg);
		}
        
		
		return null;
	}


    @Override
    public IMC.Expr visit(AST.SfxExpr sfxExpr, ChunkContext cc) {
        //Report.info(sfxExpr, "sem v sfx Expr");
        var lbl  = new IMC.TEMP(new MEM.Temp());
        var imc = (IMC.Expr)sfxExpr.subExpr.accept(this, cc);
        
        if(SemAn.ofType.get(sfxExpr.subExpr) instanceof TYP.PtrType c){
            if(c.baseType instanceof TYP.CharType || c.baseType instanceof TYP.BoolType){

                cc.add(new IMC.MOVE(lbl, new IMC.MEM1(imc)));
                return lbl;
            }
            cc.add(new IMC.MOVE(lbl, new IMC.MEM8(imc)));
            return lbl;

        }if(sfxExpr.subExpr instanceof AST.CallExpr){
            imc=sfxExpr.subExpr.accept(this, cc);

        }
        cc.add(new IMC.MOVE(lbl, imc));
        return lbl;
    }

    /*@Override
    public IMC.Expr visit(AST.ExprStmt exprStmt, ChunkContext cc){
        exprStmt.expr.accept(this,cc);
        return null;
    }*/

    @Override
    public IMC.Expr visit(AST.CastExpr castExpr, ChunkContext cc) {
        var imc = castExpr.expr.accept(this, cc);
        var l = new IMC.TEMP(new MEM.Temp());
        cc.add(new IMC.MOVE(l, imc));
        return l;
    }

    @Override
    public IMC.Expr visit(AST.SizeExpr sizeExpr, ChunkContext cc) {
        var imc = (IMC.CONST)ImcGen.expr.get(sizeExpr);
        var l = new IMC.TEMP(new MEM.Temp());
        cc.add(new IMC.MOVE(l, imc));
        return l;
    }

    @Override
    public IMC.Expr visit(AST.BinExpr binExpr, ChunkContext cc) {
        var t = (IMC.BINOP) ImcGen.expr.get(binExpr);
        Report.info(binExpr, "");
        var lbl1 = (IMC.TEMP) binExpr.fstExpr.accept(this, cc);
        var lbl2 = (IMC.TEMP) binExpr.sndExpr.accept(this, cc);
        if(lbl2 == null){
            //Report.warning(binExpr,"Fuck");
        }
        Report.info(binExpr, lbl1.toString() + " " + lbl2.toString());
        var l = new IMC.TEMP(new MEM.Temp());
        cc.add(new IMC.MOVE(l, new IMC.BINOP(t.oper, lbl1, lbl2)));
        return l;
    }


    @Override
    public IMC.Expr visit(AST.PfxExpr pfxExpr, ChunkContext cc) {
        var lbl = new IMC.TEMP(new MEM.Temp());
        Report.info("B");
        IMC.Expr lblOdSub = ImcGen.expr.get(pfxExpr);
        if(pfxExpr.subExpr instanceof AST.NameExpr){
            Report.info("AAAAAAAA");
            cc.add(new IMC.MOVE(lbl, lblOdSub));
            
            return lbl;
        }
        cc.add(new IMC.MOVE(lbl, lblOdSub));
        return lbl;
    }


    @Override
    public IMC.Expr visit(AST.DefFunDefn defFunDefn, ChunkContext cc){
        //entry
        MEM.Frame frameOfFunction = Memory.frames.get(defFunDefn);
        MEM.Label oldEnt = ImcGen.entryLabel.get(defFunDefn);
        MEM.Label oldExit = ImcGen.exitLabel.get(defFunDefn);
        
        //CODE CHUNK
        cc.funEntry = ImcGen.entryLabel.get(defFunDefn);
        cc.funExit = ImcGen.exitLabel.get(defFunDefn);
        cc.sl = frameOfFunction.FP;
        cc.rv = frameOfFunction.RV;
        cc.currFrame = frameOfFunction;

        Vector<IMC.Stmt> statementsToBeAdded = new Vector<IMC.Stmt>();
        var newContext = new ChunkContext();

        newContext.add(new IMC.LABEL(oldEnt));
        newContext.sl = frameOfFunction.FP;
        newContext.rv = frameOfFunction.RV;
        newContext.funEntry = cc.funEntry;
        newContext.funExit = cc.funExit;
        newContext.currFrame = frameOfFunction;


        defFunDefn.stmts.accept(this, newContext);
        newContext.add(new IMC.JUMP(new IMC.NAME(oldExit)));
        if(frameOfFunction.label.name.charAt(0) == 'L'){
            /*var newLbl = new MEM.Label();
            IMC.Stmt neki2 = new IMC.LABEL(oldEnt);
            
            statementsToBeAdded.add(neki2);*/
            LIN.CodeChunk cd = new CodeChunk(frameOfFunction, newContext.getVec(), oldEnt, oldExit);
            ImcLin.addCodeChunk(cd);
            //cc.addAll(newContext.getVec());
        }else{
            LIN.CodeChunk cd = new CodeChunk(frameOfFunction, newContext.getVec(), oldEnt, oldExit);
            ImcLin.addCodeChunk(cd);
        }
        //cc.add(new IMC.JUMP(new IMC.NAME(oldExit)));

        return null;
    }

    @Override
    public IMC.Expr visit(AST.ReturnStmt returnStmt, ChunkContext cc){
        
        var t = (IMC.Expr)returnStmt.retExpr.accept(this, cc);
        //Report.info(returnStmt.retExpr.toString());
        cc.add(new IMC.MOVE(new IMC.TEMP(cc.rv), t));
        cc.add(new IMC.JUMP(new IMC.NAME(cc.funExit)));
        return new IMC.TEMP(cc.rv);
    }

    public IMC.Expr visit(AST.NameExpr nameExpr, ChunkContext cc) {
        if (cc.nameTempMap.containsKey(nameExpr.name)) {
            return cc.nameTempMap.get(nameExpr.name);
        }
        
        var imc = ImcGen.expr.get(nameExpr);
        var l = new IMC.TEMP(new MEM.Temp());
        cc.add(new IMC.MOVE(l, imc));
        cc.nameTempMap.put(nameExpr.name, l);
        return l;
    }

    @Override
    public IMC.Expr visit(AST.AtomExpr atomExpr, ChunkContext cc) {
        //Report.info(atomExpr,"v atomExpr");
        var imc = ImcGen.expr.get(atomExpr);
        var newTemp = new IMC.TEMP(new MEM.Temp());
        //Report.info(atomExpr, atomExpr.value);
        if(atomExpr.type == AST.AtomExpr.Type.STR){
            
            MEM.AbsAccess n2 = Memory.strings.get(atomExpr);
            //Report.info("null");
            LIN.DataChunk dc = new DataChunk(n2);
            ImcLin.addDataChunk(dc);
        }
        cc.add(new IMC.MOVE(newTemp, imc));
        return newTemp;
    }




    /*public Vector<IMC.Expr> checkAndFixArgumentCall(AST.Nodes<? extends AST.Node> nodes, ChunkContext cc, int curr){
        Vector<IMC.Expr> y = new Vector<IMC.Expr>();
        Report.info("Depth of call" + curr);
        
        for(AST.Node n : nodes){
            IMC.Expr tmp = ImcGen.expr.get(n);
            if(tmp instanceof IMC.CALL t2){
                Report.info(t2.args.toString());
                Vector<IMC.Expr> x = checkAndFixArgumentCallRec(t2.args, cc, curr+1);
                IMC.Expr newLbl = new IMC.TEMP(new MEM.Temp());
                IMC.MOVE t = new IMC.MOVE(newLbl, new IMC.CALL(t2.addr, t2.offs,x));
                cc.add(t);
                y.add(newLbl);
            }else if(tmp instanceof IMC.CONST){
                y.add(tmp);
            }else if(tmp instanceof IMC.TEMP){
                y.add(tmp);
            }else if(tmp instanceof IMC.MEM8){
                y.add(tmp);
            }else{
                IMC.Expr newLbl = new IMC.TEMP(new MEM.Temp());
                IMC.MOVE t = new IMC.MOVE(newLbl, tmp);
                cc.add(t);
                y.add(newLbl);
            }
            
            
        }
        return y;
    }

    public Vector<IMC.Expr> checkAndFixArgumentCallRec(Vector<IMC.Expr> args, ChunkContext cc, int curr){
        Report.info("Depth of call" + curr);
        Vector<IMC.Expr> y = new Vector<IMC.Expr>();
        for(IMC.Expr tmp : args){
            if(tmp instanceof IMC.CALL t2){
                Vector<IMC.Expr> x = checkAndFixArgumentCallRec(t2.args, cc, curr+1);
                IMC.Expr newLbl;
                /*if(x != null){
                    newLbl = x;
                }else{
                newLbl = new IMC.TEMP(new MEM.Temp());
                IMC.MOVE t = new IMC.MOVE(newLbl, new IMC.CALL(t2.addr, t2.offs,x));
                cc.add(t);
                y.add(newLbl);
            }else if(tmp instanceof IMC.CONST){
                y.add(tmp);
            }else if(tmp instanceof IMC.TEMP){
                y.add(tmp);
            }else{
                IMC.Expr newLbl = new IMC.TEMP(new MEM.Temp());
                IMC.MOVE t = new IMC.MOVE(newLbl, tmp);
                cc.add(t);
                y.add(newLbl);
            }
            
        }

        return y;
    }*/

    /*//TODO FIX
    @Override
    public IMC.Expr visit(AST.CallExpr callExpr, ChunkContext cc){
        

        Report.warning(callExpr.argExprs.toString());
        IMC.TEMP lbl = new IMC.TEMP(new MEM.Temp());
        var x = checkAndFixArgumentCall(callExpr.argExprs, cc, 0);
        IMC.CALL imc = (IMC.CALL)ImcGen.expr.get(callExpr);
        x.add(0,imc.args.get(0));
        IMC.MOVE newMove = new IMC.MOVE(lbl,new IMC.CALL(imc.addr,(imc).offs,x));
        cc.add(newMove);
        return null;
    }*/

    @Override
    public IMC.Expr visit(AST.CallExpr callExpr, ChunkContext cc) {
        var expr = (IMC.CALL) ImcGen.expr.get(callExpr);

        //Report.info(callExpr, "FUU");
        var l = new IMC.TEMP(new MEM.Temp());
        Vector<IMC.Expr> args = new Vector<>();
        Vector<Long> offsets = new Vector<>();


        args.add(new IMC.MEM8(new IMC.TEMP(cc.sl)));
        offsets.add(8L);
        //offsets.addAll(expr.offs);
        int i=0;
        for (AST.Expr argExpr : callExpr.argExprs) {
            var tmp =(IMC.Expr)argExpr.accept(this, cc); 
            //Report.info(argExpr, argExpr.toString() + " " + tmp.toString());
            args.add(tmp);
            offsets.add(expr.offs.get(i));
            i+=1;
            //offsets.add(8L);
        }
        //Report.info(callExpr, "sizes:"+offsets.size() + " " + args.size());
        var t = new IMC.MOVE(l, new IMC.CALL(expr.addr, offsets, args));
        cc.add(t);
        return l;
    }

    
    @Override
    public IMC.Expr visit(AST.AssignStmt assignStmt, ChunkContext cc) {
        //Report.info(assignStmt, "v assignStmt");
        var l = (IMC.Expr)assignStmt.dstExpr.accept(this, cc);
        var x = (IMC.Expr)assignStmt.srcExpr.accept(this, cc);

        //Report.info(l.toString() + " " + x.toString());
        if(assignStmt.dstExpr instanceof AST.PfxExpr){
            var tm = new IMC.TEMP(new MEM.Temp());
            cc.add(new IMC.MOVE(new IMC.MEM8(l), x));
            cc.add(new IMC.MOVE(tm, l));
            return tm;
        }
        cc.add(new IMC.MOVE(l, x));
        return l;
    }



    @Override
    public IMC.Expr visit(AST.IfThenStmt ifThenStmt, ChunkContext cc){
        MEM.Label falseLabel = new MEM.Label();
        MEM.Label trueLabel = new MEM.Label();
        MEM.Label endLabel = new MEM.Label();

        IMC.Expr cond = ifThenStmt.condExpr.accept(this, cc);
        cc.add(new IMC.CJUMP(cond, new IMC.NAME(trueLabel), new IMC.NAME(falseLabel)));
        cc.add(new IMC.LABEL(trueLabel));
        ifThenStmt.thenStmt.accept(this, cc);
        cc.add(new IMC.LABEL(falseLabel));
        cc.add(new IMC.JUMP(new IMC.NAME(endLabel)));
        cc.add(new IMC.LABEL(endLabel));      
        return null;
    }


    @Override
    public IMC.Expr visit(AST.WhileStmt whileStmt, ChunkContext cc){
        IMC.STMTS stariWhile = (IMC.STMTS)ImcGen.stmt.get(whileStmt);
        
        cc.enacps = whileStmt;
        MEM.Label jumpBackToStartLabel = (new MEM.Label());
        cc.add(new IMC.LABEL(jumpBackToStartLabel));
        IMC.Expr cond = whileStmt.condExpr.accept(this, cc);
        MEM.Label trueLabel = new MEM.Label();
        MEM.Label falseLabel = new MEM.Label();
        IMC.CJUMP newCjump = new IMC.CJUMP(cond, new IMC.NAME(trueLabel), new IMC.NAME(falseLabel));
        cc.add(newCjump);
        cc.add(new IMC.LABEL(trueLabel));
        whileStmt.stmts.accept(this, cc);
        cc.add(new IMC.JUMP(new IMC.NAME(jumpBackToStartLabel)));
        cc.add(new IMC.LABEL(falseLabel));
        return null;
    }

    @Override
    public IMC.Expr visit(AST.IfThenElseStmt ifThenElseStmt, ChunkContext cc){
        
        MEM.Label falseLabel = new MEM.Label();
        MEM.Label trueLabel = new MEM.Label();
        MEM.Label endLabel = new MEM.Label();

        IMC.Expr cond = ifThenElseStmt.condExpr.accept(this, cc);
        cc.add(new IMC.CJUMP(cond, new IMC.NAME(trueLabel), new IMC.NAME(falseLabel)));
        cc.add(new IMC.LABEL(trueLabel));
        ifThenElseStmt.thenStmt.accept(this, cc);
        cc.add(new IMC.JUMP(new IMC.NAME(endLabel)));
        ifThenElseStmt.elseStmt.accept(this, cc);
        cc.add(new IMC.JUMP(new IMC.NAME(endLabel)));
        cc.add(new IMC.LABEL(endLabel));
        return null;
    }
    


    public Vector<IMC.Stmt> removeStmts(IMC.STMTS stmt){
        Vector<IMC.Stmt> x = new Vector<IMC.Stmt>();
        for(var z : stmt.stmts){
            if(!(z instanceof IMC.STMTS)){
                x.add(z);
            }
        }
        return x;
    }

    /*@Override
    public IMC.Expr visit(AST.ExprStmt exprStmt, ChunkContext cc){
        //Dobimo dejanski stmt
        IMC.ESTMT imcGenStatementExpressiona = (IMC.ESTMT)ImcGen.stmt.get(exprStmt);
        cc.enacps = exprStmt;
        if(imcGenStatementExpressiona.expr instanceof IMC.CALL t2){
            AST.CallExpr tmp = (AST.CallExpr)exprStmt.expr;
            exprStmt.expr.accept(this, cc);
            Vector<IMC.Expr> ar = new Vector<IMC.Expr>(t2.args);
            Vector<Long> of = new Vector<Long>(t2.offs);
            of.add(0, 8L);
            ar.add(0, t2.args.get(0));
            //Report.info("Arguments:"+ar.toString());
            imcGenStatementExpressiona = new IMC.ESTMT((new IMC.CALL(t2.addr, of, ar)));
           //imcGenStatementExpressiona.expr = checkAndFixArguemntCallRec(exprStmt.expr, cc); 
        }else{
            exprStmt.expr.accept(this, cc);
            cc.enacps = null;
            return null;
        }
        cc.enacps = null;
        cc.add(imcGenStatementExpressiona);
        return null;
    }*/

}
