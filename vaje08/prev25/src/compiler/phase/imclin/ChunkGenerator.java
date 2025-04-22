package compiler.phase.imclin;

import compiler.common.report.*;
import compiler.phase.memory.*;
import compiler.phase.seman.SemAn;
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


        //CODE CHUNK
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
            LIN.CodeChunk cd = new CodeChunk(frameOfFunction, neki.statementsToBeAdded, new MEM.Label(), new MEM.Label());
            ImcLin.addCodeChunk(cd);
        }

        return null;
    }

    @Override
    public Object visit(AST.ReturnStmt returnStmt, ChunkContext cc){

        IMC.STMTS neki = (IMC.STMTS) ImcGen.stmt.get(returnStmt);
        //cc.add(neki.stmts);
        cc.addAll(neki.stmts);
        return ImcGen.stmt.get(returnStmt);
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
            }else{
                y.add(tmp);
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
            }else{
                y.add(tmp);
            }
            
        }
        return y;
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


    //@Override
    /*public Object visit(AST.Stmt stmt, ChunkContext cc){

        return null;
    }*/

    


    @Override
    public Object visit(AST.ExprStmt exprStmt, ChunkContext cc){
        //Dobimo dejanski stmt
        IMC.ESTMT imcGenStatementExpressiona = (IMC.ESTMT)ImcGen.stmt.get(exprStmt);

        if(imcGenStatementExpressiona.expr instanceof IMC.CALL){
            Report.info(exprStmt,"hallo");
        }
        cc.add(imcGenStatementExpressiona);
        return null;
    }

    public Object visit(Object o, Object o2){


        return null;
    }

}
