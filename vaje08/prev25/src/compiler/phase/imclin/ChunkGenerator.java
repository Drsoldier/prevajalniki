package compiler.phase.imclin;

import compiler.common.report.*;
import compiler.phase.memory.*;
import compiler.phase.abstr.*;
import compiler.phase.abstr.AST.Nodes;
import compiler.phase.imclin.LIN.*;
import compiler.phase.imcgen.*;
import compiler.phase.seman.*;

import java.util.*;

public class ChunkGenerator implements AST.FullVisitor<Object, ChunkContext> {
    
    @Override
	public Object visit(AST.Nodes<? extends AST.Node> nodes, ChunkContext arg) {
        
        
        for (final AST.Node node : nodes)   {
            if(node instanceof AST.DefFunDefn){
                arg = new ChunkContext();
            }
            node.accept(this, arg);
		}
        
		
		return null;
	}


    @Override
    public Object visit(AST.SfxExpr sfxExpr, ChunkContext cc){

        return null;
    }

    @Override
    public Object visit(AST.PfxExpr pfxExpr, ChunkContext cc){

        return null;
    }


    public boolean isCallExpr(AST.Node b){
        if(b instanceof AST.CallExpr){
            return true;
        }
        if(b instanceof AST.BinExpr c){
            return isCallExpr(c.fstExpr) || isCallExpr(c.sndExpr);
        }
        if(b instanceof AST.CastExpr c){
            return isCallExpr(c.expr);
        }
        if(b instanceof AST.SfxExpr c){
            return isCallExpr(c.subExpr);
        }
        if(b instanceof AST.PfxExpr c){
            return isCallExpr(c.subExpr);
        }

        return false;
    }
    
    public boolean isCallExprOneLevel(AST.Node b){
        if(b instanceof AST.CallExpr){
            return true;
        }

        return false;
    }

    @Override
    public Object visit(AST.BinExpr binExpr, ChunkContext cc){

        IMC.Stmt fstExpr;
        IMC.Stmt sndExpr;
        IMC.BINOP binOp = (IMC.BINOP)ImcGen.expr.get(binExpr);
        //preglej prvo 
        if(isCallExprOneLevel(binExpr.fstExpr)){

            binExpr.fstExpr.accept(this, cc);
            IMC.MOVE zzz = (IMC.MOVE)cc.getVec().getLast();

        }else{
            binExpr.fstExpr.accept(this, cc);
            fstExpr = cc.getVec().get(0);
        }
        


        //preglej drugo

        return null;
    }

    @Override
    public Object visit(AST.DefFunDefn defFunDefn, ChunkContext cc){
        //entry
        MEM.Frame frameOfFunction = Memory.frames.get(defFunDefn);
        MEM.Label oldEnt = ImcGen.entryLabel.get(defFunDefn);
        MEM.Label oldExit = ImcGen.exitLabel.get(defFunDefn);
        //CODE CHUNK
        cc.funEntry = ImcGen.entryLabel.get(defFunDefn);
        cc.funExit = ImcGen.exitLabel.get(defFunDefn);
        Vector<IMC.Stmt> statementsToBeAdded = new Vector<IMC.Stmt>();
        var newContext = new ChunkContext();
        ChunkContext neki;
        newContext.add(new IMC.LABEL(oldEnt));

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
            LIN.CodeChunk cd = new CodeChunk(frameOfFunction, neki.statementsToBeAdded, oldEnt, oldExit);
            ImcLin.addCodeChunk(cd);
        }

        return null;
    }

    @Override
    public Object visit(AST.ReturnStmt returnStmt, ChunkContext cc){
        
        IMC.STMTS neki = (IMC.STMTS) ImcGen.stmt.get(returnStmt);
        returnStmt.retExpr.accept(this, cc);
        //cc.add(neki.stmts);
        if(!(returnStmt.retExpr instanceof AST.CallExpr b)){
            cc.add(neki.stmts.get(0));
            cc.add(neki.stmts.get(1));
            return null;
        }
        
        IMC.MOVE fff = (IMC.MOVE)neki.stmts.get(0);
        IMC.MOVE zzz = (IMC.MOVE)cc.getVec().getLast();
        Report.info(zzz.toString() + "<-info3");

        cc.add(new IMC.MOVE(fff.dst, zzz.dst));
        cc.add(neki.stmts.get(1));
        return null;
    }




    @Override
    public Object visit(AST.AtomExpr atomExpr, ChunkContext cc){

        TYP.Type neki3 = SemAn.ofType.get(atomExpr);
        if(neki3 instanceof TYP.PtrType b){
            if(!(b.baseType instanceof TYP.CharType)){
                Report.info(b.toString());
                return null;
            }
            MEM.AbsAccess n2 = Memory.strings.get(atomExpr);
            //Report.info("null");
            LIN.DataChunk dc = new DataChunk(n2);
            ImcLin.addDataChunk(dc);
            

        }
        
        return null;
    }



    public IMC.Stmt check(IMC.Stmt stmt, ChunkContext cc){

        if(stmt instanceof IMC.ESTMT){

        }

        return null;
    }

    public Vector<IMC.Expr> checkAndFixArgumentCall(AST.Nodes<? extends AST.Node> nodes, ChunkContext cc, int curr){
        Vector<IMC.Expr> y = new Vector<IMC.Expr>();
        
        for(AST.Node n : nodes){
            IMC.Expr tmp = ImcGen.expr.get(n);
            if(tmp instanceof IMC.CALL t2){
                Report.info(t2.args.toString());
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
            }else if(tmp instanceof IMC.MEM8){
                y.add(tmp);
            }else{
                IMC.Expr newLbl = new IMC.TEMP(new MEM.Temp());
                IMC.MOVE t = new IMC.MOVE(newLbl, tmp);
                cc.add(t);
                y.add(newLbl);
            }
            
            
        }
        Report.info("Depth of call" + curr);
        return y;
    }

    public Vector<IMC.Expr> checkAndFixArgumentCallRec(Vector<IMC.Expr> args, ChunkContext cc, int curr){
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
            }/*9else{
                y.add(tmp);
            }*/
            
            
        }
        Report.info("Depth of call" + curr);

        return y;
    }
    public Vector<IMC.Expr> checkAndFixAccessRec(IMC.Expr expr, ChunkContext cc){
        Vector<IMC.Expr> y = new Vector<IMC.Expr>();
        
        return null;
    }

    public Vector<IMC.Expr> checkAndFixAccess(AST.NameExpr b, ChunkContext cc){
        Vector<IMC.Expr> y = new Vector<IMC.Expr>();
        
        IMC.Expr v = ImcGen.expr.get(b);
        if( v instanceof IMC.MEM8){
            IMC.Expr n = ((IMC.MEM8)v).addr;
            if(n instanceof IMC.BINOP){
                Vector<IMC.Expr> x = checkAndFixAccessRec(n, cc);
                IMC.Expr newLbl;
                /*if(x != null){
                    newLbl = x;
                }else{*/
                newLbl = new IMC.TEMP(new MEM.Temp());
                IMC.MOVE t = new IMC.MOVE(newLbl, n);
                cc.add(t);
                y.add(newLbl);
            }
        }else if(v instanceof IMC.MEM1){
            IMC.Expr n = ((IMC.MEM8)v).addr;
            if(n instanceof IMC.BINOP){

            }
        }
        return null;
    }

    //TODO FIX
    @Override
    public Object visit(AST.CallExpr callExpr, ChunkContext cc){
        //Report.info(cc.getVec().toString());
        Report.warning(callExpr.argExprs.toString());
        IMC.TEMP lbl = new IMC.TEMP(new MEM.Temp());
        var x = checkAndFixArgumentCall(callExpr.argExprs, cc, 0);
        IMC.CALL imc = (IMC.CALL)ImcGen.expr.get(callExpr);
        x.add(0,imc.args.get(0));
        IMC.MOVE newMove = new IMC.MOVE(lbl,new IMC.CALL(imc.addr,(imc).offs,x));
        cc.add(newMove);

        return null;
    }

    @Override
    public Object visit(AST.AssignStmt assignStmt, ChunkContext cc){
        //desno stran pustimo na miru
        IMC.Stmt n = ImcGen.stmt.get(assignStmt);
        if(!(n instanceof IMC.MOVE)){
            throw new Report.Error("wat da fuck");
        }
        IMC.MOVE u = (IMC.MOVE)n;
        
        //Pregledamo če je desna stran klic
        //če je, lineariziramo
        assignStmt.dstExpr.accept(this, cc);
        if(assignStmt.srcExpr instanceof AST.CallExpr b){
            //checkAndFixArguemntCall(b.argExprs, cc, 0);
            b.accept(this, cc);
            IMC.MOVE zzz = (IMC.MOVE)cc.getVec().getLast();
            Report.info(zzz.toString() + "<-info2");
            cc.add(new IMC.MOVE(u.dst, zzz.dst));
        }else{

            IMC.TEMP x = new IMC.TEMP(new MEM.Temp());
            cc.add(new IMC.MOVE(x,u.dst));
            
            
            /*if(assignStmt.dstExpr instanceof AST.NameExpr c)
            checkAndFixAccess(c, cc);*/
            //LEVA STRAN
            cc.add(new IMC.MOVE(x,u.src));
            cc.add(new IMC.MOVE(u.dst, x));
        }

        return null;
    }



    @Override
    public Object visit(AST.LetStmt letStmt, ChunkContext cc){
        var newCc = new ChunkContext();
        letStmt.defns.accept(this,newCc);
        letStmt.stmts.accept(this,newCc);
        //Report.info(letStmt, "The number of statements in this is: "+ newCc.getVec().size());
        cc.addAll(newCc.getVec());
        return null;
    }



    @Override
    public Object visit(AST.IfThenStmt ifThenStmt, ChunkContext cc){
        IMC.STMTS a = (IMC.STMTS)ImcGen.stmt.get(ifThenStmt);
        if(a instanceof IMC.STMTS){

        }
        IMC.CJUMP x = (IMC.CJUMP)a.stmts.get(0);
        

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
    public Object visit(AST.WhileStmt whileStmt, ChunkContext cc){
        IMC.STMTS a = (IMC.STMTS)ImcGen.stmt.get(whileStmt);
        
        IMC.CJUMP x = (IMC.CJUMP)a.stmts.get(1);
        

        whileStmt.stmts.accept(this, cc);
        IMC.NAME falseName = new IMC.NAME(new MEM.Label());
        IMC.LABEL falseLabel = new IMC.LABEL(falseName.label);

        IMC.JUMP jFalse = new IMC.JUMP(x.negAddr);
        IMC.CJUMP f = new IMC.CJUMP(x.cond, x.posAddr, falseName);
        a.stmts.remove(1);
        a.stmts.add(1, f);
        a.stmts.add(2,falseLabel);
        a.stmts.add(3, jFalse);
        cc.addAll(removeStmts(a));
        return null;
    }

    @Override
    public Object visit(AST.IfThenElseStmt ifThenElseStmt, ChunkContext cc){
        IMC.STMTS a = (IMC.STMTS)ImcGen.stmt.get(ifThenElseStmt);
        
        IMC.CJUMP x = (IMC.CJUMP)a.stmts.get(0);
        

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

    @Override
    public Object visit(AST.ExprStmt exprStmt, ChunkContext cc){
        //Dobimo dejanski stmt
        IMC.ESTMT imcGenStatementExpressiona = (IMC.ESTMT)ImcGen.stmt.get(exprStmt);
        if(imcGenStatementExpressiona.expr instanceof IMC.CALL t2){
            AST.CallExpr tmp = (AST.CallExpr)exprStmt.expr;
            Vector<IMC.Expr> ar = checkAndFixArgumentCall(tmp.argExprs, cc, 0);
            imcGenStatementExpressiona = new IMC.ESTMT((new IMC.CALL(t2.addr, t2.offs, ar)));
           //imcGenStatementExpressiona.expr = checkAndFixArguemntCallRec(exprStmt.expr, cc); 
        }
        cc.add(imcGenStatementExpressiona);
        return null;
    }

    public Object visit(Object o, Object o2){

        throw new Report.Error("Not yet implemented for " + o.toString());
    }

}
