package compiler.phase.imclin;

import compiler.common.report.*;
import compiler.phase.memory.*;
import compiler.phase.abstr.*;
import compiler.phase.abstr.AST.Nodes;
import compiler.phase.imclin.LIN.*;
import compiler.phase.imcgen.*;
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
        Report.info(sfxExpr, "sem v sfx Expr");
        var lbl  = new IMC.TEMP(new MEM.Temp());
        var imc = (IMC.Expr)sfxExpr.subExpr.accept(this, cc);
       
        cc.add(new IMC.MOVE(lbl, new IMC.MEM1(imc)));
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
    public IMC.Expr visit(AST.BinExpr binExpr, ChunkContext cc) {
        var t = (IMC.BINOP) ImcGen.expr.get(binExpr);
        var lbl1 = (IMC.TEMP) binExpr.fstExpr.accept(this, cc);
        var lbl2 = (IMC.TEMP) binExpr.sndExpr.accept(this, cc);
        var l = new IMC.TEMP(new MEM.Temp());
        cc.add(new IMC.MOVE(l, new IMC.BINOP(t.oper, lbl1, lbl2)));
        return l;
    }


    /*@Override
    public Object visit(AST.BinExpr binExpr, ChunkContext cc){

        IMC.Expr fstExpr;
        IMC.Expr sndExpr = null;
        IMC.BINOP binOp = (IMC.BINOP)ImcGen.expr.get(binExpr);
        //preglej prvo 
        var lbl1 = (IMC.TEMP) binExpr.fstExpr.accept(this, cc);
        var lbl2 = (IMC.TEMP) binExpr.sndExpr.accept(this, cc);
        var l = new IMC.TEMP(new MEM.Temp());
        
        if(isCallExprOneLevel(binExpr.fstExpr)){
            
            binExpr.fstExpr.accept(this, cc);
            IMC.MOVE zzz = (IMC.MOVE)cc.getVec().getLast();
            
        }else{
            binExpr.fstExpr.accept(this, cc);
            
        }
        
        cc.add(new IMC.MOVE(l, new IMC.BINOP(binOp.oper, fstExpr, sndExpr)));
        //preglej drugo

        return null;
    }


    @Override
    public Object visit(AST.BinExpr binExpr, ChunkContext cc) {
        var t = (IMC.BINOP) ImcGen.expr.get(binExpr);
        Report.info(binExpr, "a");
        Report.info(t.toString());
        var lbl1 = (IMC.TEMP) binExpr.fstExpr.accept(this, cc);
        var lbl2 = (IMC.TEMP) binExpr.sndExpr.accept(this, cc);
        var l = new IMC.TEMP(new MEM.Temp());
        cc.add(new IMC.MOVE(l, new IMC.BINOP(t.oper, lbl1, lbl2)));
        return null;
    }*/


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
        ChunkContext neki;
        newContext.add(new IMC.LABEL(oldEnt));
        newContext.sl = frameOfFunction.FP;
        newContext.rv = frameOfFunction.RV;
        newContext.funEntry = cc.funEntry;
        newContext.funExit = cc.funExit;
        newContext.currFrame = frameOfFunction;


        if(frameOfFunction.label.name.charAt(0) == 'L'){
            /*var newLbl = new MEM.Label();
            IMC.Stmt neki2 = new IMC.LABEL(oldEnt);

            statementsToBeAdded.add(neki2);*/
            defFunDefn.stmts.accept(this, newContext);
            cc.addAll(newContext.getVec());
        }else{
            defFunDefn.stmts.accept(this, newContext);
            neki = newContext;
            //neki.addFirst(new IMC.LABEL(oldEnt));
            neki.add(new IMC.JUMP(new IMC.NAME(oldExit)));
            LIN.CodeChunk cd = new CodeChunk(frameOfFunction, neki.statementsToBeAdded, oldEnt, oldExit);
            ImcLin.addCodeChunk(cd);
        }
        //cc.add(new IMC.JUMP(new IMC.NAME(oldExit)));

        return null;
    }

    @Override
    public IMC.Expr visit(AST.ReturnStmt returnStmt, ChunkContext cc){
        
        var t = (IMC.Expr)returnStmt.retExpr.accept(this, cc);
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
        Report.info(atomExpr,"v atomExpr");
        TYP.Type neki3 = SemAn.ofType.get(atomExpr);
        var imc = ImcGen.expr.get(atomExpr);
        var newTemp = new IMC.TEMP(new MEM.Temp());
        Report.info(atomExpr, atomExpr.value);
        if(atomExpr.type == AST.AtomExpr.Type.STR){
            
            MEM.AbsAccess n2 = Memory.strings.get(atomExpr);
            //Report.info("null");
            LIN.DataChunk dc = new DataChunk(n2);
            ImcLin.addDataChunk(dc);
        }
        cc.add(new IMC.MOVE(newTemp, imc));
        return newTemp;
    }




    public Vector<IMC.Expr> checkAndFixArgumentCall(AST.Nodes<? extends AST.Node> nodes, ChunkContext cc, int curr){
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
                }else{*/
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
    }

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
            Report.info(argExpr, argExpr.toString() + " " + tmp.toString());
            args.add(tmp);
            offsets.add(expr.offs.get(i));
            i+=1;
            //offsets.add(8L);
        }
        Report.info(callExpr, "sizes:"+offsets.size() + " " + args.size());
        var t = new IMC.MOVE(l, new IMC.CALL(expr.addr, offsets, args));
        cc.add(t);
        return l;
    }

    /*@Override
    public IMC.Expr visit(AST.AssignStmt assignStmt, ChunkContext cc){
        //desno stran pustimo na miru

        IMC.Stmt n = ImcGen.stmt.get(assignStmt);
        if(!(n instanceof IMC.MOVE)){
            throw new Report.Error("wat da fuck");
        }
        IMC.MOVE u = (IMC.MOVE)n;
        cc.enacps = assignStmt;
        //Pregledamo če je desna stran klic
        //če je, lineariziramo
        assignStmt.dstExpr.accept(this, cc);
        if(assignStmt.srcExpr instanceof AST.CallExpr b){
            //checkAndFixArguemntCall(b.argExprs, cc, 0);
            b.accept(this, cc);
            IMC.MOVE zzz = (IMC.MOVE)cc.getVec().getLast();
            cc.add(new IMC.MOVE(u.dst, zzz.dst));
        }else{

            IMC.TEMP x = new IMC.TEMP(new MEM.Temp());
            cc.add(new IMC.MOVE(x,u.dst));
            
            
            /*if(assignStmt.dstExpr instanceof AST.NameExpr c)
            checkAndFixAccess(c, cc);*/
            //LEVA STRAN
            /*cc.add(new IMC.MOVE(x,u.src));
            cc.add(new IMC.MOVE(u.dst, x));
        }
        cc.enacps = null;
        return null;
    }*/
    @Override
    public IMC.Expr visit(AST.AssignStmt assignStmt, ChunkContext arg) {
        var l = (IMC.Expr)assignStmt.dstExpr.accept(this, arg);
        var x = (IMC.Expr)assignStmt.srcExpr.accept(this, arg);
        TYP.Type lt = SemAn.ofType.get(assignStmt.dstExpr);
        arg.add(new IMC.MOVE(l, x));
        return l;
    }





    /*@Override
    public IMC.Expr visit(AST.LetStmt letStmt, ChunkContext cc){
        var newCc = new ChunkContext();
        newCc.enacps = letStmt;
        newCc.sl = cc.sl;
        newCc.rv = cc.rv;
        newCc.currFrame = cc.currFrame;
        newCc.funExit = cc.funExit;
        newCc.funEntry = cc.funEntry;
        letStmt.defns.accept(this,newCc);
        letStmt.stmts.accept(this,newCc);
        newCc.enacps = null;
        //Report.info(letStmt, "The number of statements in this is: "+ newCc.getVec().size());
        cc.addAll(newCc.getVec());
        return null;
    }*/



    @Override
    public IMC.Expr visit(AST.IfThenStmt ifThenStmt, ChunkContext cc){
        IMC.STMTS a = (IMC.STMTS)ImcGen.stmt.get(ifThenStmt);
        
        IMC.CJUMP x = (IMC.CJUMP)a.stmts.get(0);
        
        cc.enacps = ifThenStmt;
        //ifThenStmt.thenStmt.accept(this, cc);
        IMC.NAME falseName = new IMC.NAME(new MEM.Label());
        IMC.LABEL g = new IMC.LABEL(falseName.label);
        IMC.JUMP jFalse = new IMC.JUMP(x.negAddr);
        IMC.CJUMP f = new IMC.CJUMP(x.cond, x.posAddr, falseName);
        a.stmts.remove(0);
        a.stmts.add(0, f);
        a.stmts.add(1,g);
        a.stmts.add(2, jFalse);
        cc.addAll(removeStmts(a));        
        return null;
    }


    @Override
    public IMC.Expr visit(AST.WhileStmt whileStmt, ChunkContext cc){
        IMC.STMTS a = (IMC.STMTS)ImcGen.stmt.get(whileStmt);
        
        cc.enacps = whileStmt;

        IMC.CJUMP x = (IMC.CJUMP)a.stmts.get(1);
        //IMC.Expr f2 = (IMC.Expr)whileStmt.condExpr.accept(this,cc);
        IMC.NAME falseName = new IMC.NAME(new MEM.Label());
        IMC.LABEL falseLabel = new IMC.LABEL(falseName.label);
        Report.warning(whileStmt, x.cond.toString());
        IMC.JUMP jFalse = new IMC.JUMP(x.negAddr);
        IMC.CJUMP f = new IMC.CJUMP(x.cond, x.posAddr, falseName);
        a.stmts.remove(1);
        a.stmts.add(1, f);
        a.stmts.add(2,falseLabel);
        a.stmts.add(3, jFalse);
        for(int i=0; i<a.stmts.size(); i++){
            if(!(a.stmts.get(i) instanceof IMC.STMTS))
                cc.add(a.stmts.get(i));
        }
        whileStmt.stmts.accept(this, cc);
        return null;
    }

    @Override
    public IMC.Expr visit(AST.IfThenElseStmt ifThenElseStmt, ChunkContext cc){
        IMC.STMTS a = (IMC.STMTS)ImcGen.stmt.get(ifThenElseStmt);
        
        IMC.CJUMP x = (IMC.CJUMP)a.stmts.get(0);
        

        cc.enacps = ifThenElseStmt;

        ifThenElseStmt.thenStmt.accept(this, cc);
        ifThenElseStmt.elseStmt.accept(this, cc);
        IMC.NAME falseName = new IMC.NAME(new MEM.Label());
        IMC.LABEL g = new IMC.LABEL(falseName.label);
        IMC.JUMP jFalse = new IMC.JUMP(x.negAddr);
        IMC.CJUMP f = new IMC.CJUMP(x.cond, x.posAddr, falseName);
        a.stmts.remove(0);
        a.stmts.add(0, f);
        a.stmts.add(1,g);
        a.stmts.add(2, jFalse);

        cc.addAll(removeStmts(a));
        
        return null;
    }
    


    public Vector<IMC.Stmt> removeStmts(IMC.STMTS stmt){
        Vector<IMC.Stmt> x = new Vector<IMC.Stmt>();
        for(var z : stmt.stmts){
            if(z instanceof IMC.STMTS z2){
                x.addAll(removeStmts(z2));
            }else{
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
