package compiler.phase.imclin;

import compiler.common.report.*;
import compiler.phase.memory.*;
import compiler.phase.abstr.*;
import compiler.phase.abstr.AST.Nodes;
import compiler.phase.imcgen.ImcGen;
import compiler.phase.imcgen.NekiNovega;
import compiler.phase.imclin.LIN.*;
import compiler.phase.imcgen.*;

import java.util.*;

public class ChunkGenerator implements AST.FullVisitor<Object, ChunkContext> {
    
    @Override
	public Object visit(AST.Nodes<? extends AST.Node> nodes, ChunkContext arg) {
        
        
        for (final AST.Node node : nodes)   {
            node.accept(this, arg);
		}
        
		
		return null;
	}

    public Object visit(AST.DefFunDefn defFunDefn, ChunkContext cc){
        //entry


        //CODE CHUNK
        Vector<AST.Stmt> statementsToBeAdded = new Vector<AST.Stmt>();
        var newContext = new ChunkContext();
        defFunDefn.stmts.accept(this, newContext);
        MEM.Frame frameOfFunction = Memory.frames.get(defFunDefn);

        LIN.CodeChunk cd = new CodeChunk(frameOfFunction, newContext.statementsToBeAdded, new MEM.Label(), new MEM.Label());
        
        ImcLin.addCodeChunk(cd);
        return null;
    }

    @Override
    public Object visit(AST.ReturnStmt returnStmt, ChunkContext cc){

        IMC.STMTS neki = (IMC.STMTS) ImcGen.stmt.get(returnStmt);
        //cc.add(neki.stmts);
        cc.addAll(neki.stmts);
        return ImcGen.stmt.get(returnStmt);
    }


    //@Override
    public Object visit(AST.Stmt stmt, ChunkContext cc){

        return null;
    }

    //lahko passam ta "statmentsToBeAdded" kot context, in pol če ni, STMTS, vemo da smo na konc
    public Vector<AST.Stmt> acceptStmt(AST.Stmt stmt, ChunkContext cc){
        Vector<AST.Stmt> statementsToBeAdded = new Vector<AST.Stmt>();
        IMC.Stmt neki = ImcGen.stmt.get(stmt);
        ChunkContext contextFromBelow = new ChunkContext();
        if(neki instanceof IMC.STMTS b){
            // vemo da je tuki več statementov. Kako je to narejeno še ne vemo. 
            // vemo da je 
            stmt.accept(this, contextFromBelow);
        }
        return null;
    }


    @Override
    public Object visit(AST.ExprStmt exprStmt, ChunkContext cc){
        //Dobimo dejanski stmt
        IMC.ESTMT imcGenStatementExpressiona = (IMC.ESTMT)ImcGen.stmt.get(exprStmt);

        if(imcGenStatementExpressiona.expr instanceof IMC.CALL){
            Report.info("hallo");
        }

        return null;
    }

    public Object visit(Object o, Object o2){


        return null;
    }

}
