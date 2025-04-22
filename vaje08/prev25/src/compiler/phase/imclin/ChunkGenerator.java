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
        if(frameOfFunction.label.name.charAt(0) == 'L'){
            var newLbl = new MEM.Label();
            IMC.Stmt neki2 = new IMC.LABEL(newLbl);

            statementsToBeAdded.add(neki2);
            defFunDefn.stmts.accept(this, newContext);
            cc.addAll(newContext.getVec());
        }else{
            defFunDefn.stmts.accept(this, newContext);
            neki = newContext;
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
        cc.add(neki.stmts.get(0));
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
            Report.info("null");
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

    public Vector<IMC.Expr> checkAndFixArguemntCall(AST.Nodes<? extends AST.Node> nodes, ChunkContext cc){
        Vector<IMC.Expr> y = new Vector<IMC.Expr>();
        for(AST.Node n : nodes){
            IMC.Expr tmp = ImcGen.expr.get(n);
            if(tmp instanceof IMC.CALL t2){
                Vector<IMC.Expr> x = checkAndFixArguemntCallRec(t2.args, cc);
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

    public Vector<IMC.Expr> checkAndFixArguemntCallRec(Vector<IMC.Expr> args, ChunkContext cc){
        Vector<IMC.Expr> y = new Vector<IMC.Expr>();
        for(IMC.Expr tmp : args){
            if(tmp instanceof IMC.CALL t2){
                Vector<IMC.Expr> x = checkAndFixArguemntCallRec(t2.args, cc);
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
        return y;
    }


    //TODO FIX
    @Override
    public Object visit(AST.CallExpr callExpr, ChunkContext cc){
        /*Vector<IMC.Expr> x = checkAndFixArguemntCall(callExpr.argExprs, cc);
        IMC.CALL z = (IMC.CALL)ImcGen.expr.get(callExpr);
        IMC.MOVE zzz = new IMC.MOVE(u.dst, new IMC.CALL(z.addr,z.offs, x));
        cc.add(zzz);*/
        return null;
    }

    @Override
    public Object visit(AST.AssignStmt assignStmt, ChunkContext cc){
        //desno stran pustimo na mru
        IMC.Stmt n = ImcGen.stmt.get(assignStmt);
        if(!(n instanceof IMC.MOVE)){
            throw new Report.Error("wat da fuck");
        }
        IMC.MOVE u = (IMC.MOVE)n;
        //Pregledamo če je desna stran klic
        //če je, lineariziramo
        if(assignStmt.srcExpr instanceof AST.CallExpr dCallExpr){
            if(dCallExpr.funExpr instanceof AST.NameExpr b){

            }
            Report.warning("null");
            /*for(var neki : dCallExpr.argExprs){
                IMC.Expr allahGuidesMyCode = ImcGen.expr.get(neki);
                if(allahGuidesMyCode instanceof IMC.CALL nisemVerenBtw){
                    IMC.Expr newLbl = new IMC.TEMP(new MEM.Temp());
                    IMC.MOVE t = new IMC.MOVE(newLbl, nisemVerenBtw);
                    Report.info(assignStmt, nisemVerenBtw.toString());
                    cc.addFirst(t);
                }else{
                    cc.add(n);
                }
            }*/
            Vector<IMC.Expr> x = checkAndFixArguemntCall(dCallExpr.argExprs, cc);
            IMC.CALL z = (IMC.CALL)ImcGen.expr.get(dCallExpr);
            IMC.MOVE zzz = new IMC.MOVE(u.dst, new IMC.CALL(z.addr,z.offs, x));

            cc.add(zzz);
            return zzz;

        }else{
            assignStmt.srcExpr.accept(this, cc);
        }
        
        
        //LEVA STRAN
        cc.add(n);
        return n;
    }



    @Override
    public Object visit(AST.LetStmt letStmt, ChunkContext cc){
        var newCc = new ChunkContext();
        letStmt.defns.accept(this,newCc);
        letStmt.stmts.accept(this,newCc);
        Report.info(letStmt, "The number of statements in this is: "+ newCc.getVec().size());
        cc.addAll(newCc.getVec());
        return null;
    }


    public void fixIfThenStmt(){
        

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
            Vector<IMC.Expr> ar = checkAndFixArguemntCall(tmp.argExprs, cc);
            imcGenStatementExpressiona = new IMC.ESTMT((new IMC.CALL(t2.addr, t2.offs, ar)));
           //imcGenStatementExpressiona.expr = checkAndFixArguemntCallRec(exprStmt.expr, cc); 
        }
        cc.add(imcGenStatementExpressiona);
        return null;
    }

    public Object visit(Object o, Object o2){


        return null;
    }

}
