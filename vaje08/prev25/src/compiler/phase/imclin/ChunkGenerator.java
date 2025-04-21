package compiler.phase.imclin;

import compiler.phase.memory.*;

public class ChunkGenerator implements AST.FullVisitor<Object, ChunkContext> {
    
    public Object accept(AST.DefFunDefn defFunDefn, ChunkContext cc){
        //entry


        //CODE CHUNK
        Vecotr<Stmt> statementsToBeAdded = new Vector<>();
        for(var x : defFunDefn.stmt){

            statementsToBeAdded = this.acceptStmt(x, cc);
        }
        
        //prolog

        
    }

    public Vector<Stmt> acceptStmt(AST.Stmt stmt, ChunkContext cc){

    }

}
