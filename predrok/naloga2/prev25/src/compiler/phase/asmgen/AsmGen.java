package compiler.phase.asmgen;

import java.util.Vector;
import java.util.HashMap;

import compiler.common.report.Report;
import compiler.phase.Phase;
import compiler.phase.abstr.AST;
import compiler.phase.asmgen.ASM.Data;
import compiler.phase.asmgen.ASM.NameOfFrame;
import compiler.phase.imcgen.IMC;
import compiler.phase.imcgen.ImcGen;
import compiler.phase.imclin.ImcLin;
import compiler.phase.imclin.LIN;
import compiler.phase.imclin.LIN.Chunk;
import compiler.phase.memory.MEM;
import compiler.phase.memory.Memory;

public class AsmGen extends Phase{


    private final static Vector<ASM.AsmChunk> asmChunks = new Vector<ASM.AsmChunk>();
    public static ASM.AsmChunk data = null;
    AsmGenerator asmGenerator;
    private static Vector<LIN.CodeChunk> codeChunk = new Vector<LIN.CodeChunk>();
    
    Vector<LIN.DataChunk> dc = new Vector<LIN.DataChunk>();

    public static HashMap<LIN.CodeChunk, ASM.AsmChunk> codeChunkToAsmChunk = new HashMap<LIN.CodeChunk, ASM.AsmChunk>();

    public AsmGen() {
        super("asmgen");
    }
    
    public Vector<ASM.AsmChunk> getVec(){
        return asmChunks;
    }
    public AsmGen(Vector<LIN.DataChunk> dc, Vector<LIN.CodeChunk> chunk) {
        super("asmgen");
        codeChunk = chunk;
        this.dc = dc;
        asmGenerator = new AsmGenerator(dc, chunk);
    }
    
    public static void addAsmChunk(ASM.AsmChunk asmChunk) {
        asmChunks.add(asmChunk);
    }

    /**
     * Returns a collection of all code chunks of the program.
     * 
     * @return A collection of all code chunks of the program.
     */
    public static Vector<ASM.AsmChunk> asmChunks() {
        return new Vector<ASM.AsmChunk>(asmChunks);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ASM.AsmChunk arg = new ASM.AsmChunk();
        
        for(LIN.DataChunk dc2 : dc){
            if(dc2.init == null){
                String x = "";
                for(long i=0; i<dc2.size; i++){
                    x+="\0";
                }
                arg.addLine(new Data(
                    dc2.label.name,
                    x,
                    true
                ));
            }else{
                arg.addLine(new Data(
                    dc2.label.name,
                    dc2.init,
                    false
                ));
            }
        }
        data = arg;
        System.out.println(".data");
        for(var z : arg.lines){
            System.out.println(z);
        }

        System.out.println(".text");
        for (LIN.CodeChunk cc : codeChunk) {
            ASM.AsmChunk asmChunk = new ASM.AsmChunk(cc.frame);
            //asmChunk.frameOfCode = cc.frame;
            sb.append("######" + cc.frame.label.name + "#####\n");
            sb.append(cc.frame.label.name+": #TODO IN NEXT PHASES: PROLOGUE\n");

            asmChunk.addLine(new ASM.NameOfFrame(cc.frame.label.name+":"));
            asmChunk.frameOfCode = cc.frame;
            asmChunk.addLine(new ASM.Comment("######" + cc.frame.label.name + "#####\n"));
            //asmChunk.addLine(new ASM.Label(cc.frame.label.name+"#TODO IN NEXT PHASES: PROLOGUE\n"));
            for(IMC.Stmt stmt : cc.stmts()) {
                var t = (stmt.accept(asmGenerator, asmChunk));
                
            }
            for(var  x : asmChunk.lines){
                sb.append(x.toString() + "\n");
            }
            codeChunkToAsmChunk.put(cc, asmChunk);
            asmChunk.addLine(new ASM.LabelLine(cc.exitLabel.name));
            //asmChunk.addLine(new ASM.Comment("####################\n"));
            addAsmChunk(asmChunk);
            
            sb.append(cc.exitLabel.name+"#TODO IN NEXT PHASES: EPILOGUE\n");

            sb.append("####################\n");
        }
        return sb.toString();
    }

	
}
