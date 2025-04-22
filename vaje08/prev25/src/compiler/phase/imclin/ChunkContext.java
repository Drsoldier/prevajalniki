package compiler.phase.imclin;

import java.util.Vector;

import compiler.phase.abstr.*;
import compiler.phase.imcgen.*;

public class ChunkContext {
    public Vector<IMC.Stmt> statementsToBeAdded = new Vector<IMC.Stmt>();

    public ChunkContext(){

    }
    public void add(IMC.Stmt n){
        statementsToBeAdded.addLast(n);
    }
    public void addFirst(IMC.Stmt n){
        statementsToBeAdded.add(0,n);
    }
    public void addAll(Vector<IMC.Stmt> n){
        statementsToBeAdded.addAll(n);
    }

    public Vector<IMC.Stmt> getVec(){
        return statementsToBeAdded;
    }

    
}
