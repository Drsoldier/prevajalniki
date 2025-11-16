package compiler.phase.imclin;

import java.util.HashMap;
import java.util.Vector;

import compiler.phase.abstr.*;
import compiler.phase.imcgen.*;
import compiler.phase.memory.MEM;

public class ChunkContext {
    public Vector<IMC.Stmt> statementsToBeAdded = new Vector<IMC.Stmt>();
    public HashMap<String, IMC.TEMP> nameTempMap = new HashMap<>();
    public MEM.Label funExit;
    public MEM.Label funEntry;
    public MEM.Temp rv;
    public MEM.Temp sl;
    public MEM.Frame currFrame;
    public AST.Node enacps;
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
