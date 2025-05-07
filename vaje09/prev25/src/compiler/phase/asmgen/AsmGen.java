package compiler.phase.asmgen;

import java.util.Vector;

import compiler.phase.Phase;
import compiler.phase.abstr.AST;
import compiler.phase.imcgen.IMC;
import compiler.phase.imcgen.ImcGen;
import compiler.phase.imclin.ImcLin;
import compiler.phase.imclin.LIN;
import compiler.phase.imclin.LIN.Chunk;
import compiler.phase.memory.MEM;
import compiler.phase.memory.Memory;

public class AsmGen extends Phase{


    private final static Vector<ASM.AsmChunk> asmChunks = new Vector<ASM.AsmChunk>();

    AsmGenerator asmGenerator;
    private static Vector<LIN.CodeChunk> codeChunk = new Vector<LIN.CodeChunk>();
    
    public AsmGen() {
        super("asmgen");
    }
	
    public AsmGen(Vector<LIN.CodeChunk> chunk) {
        super("asmgen");
        codeChunk = chunk;
        asmGenerator = new AsmGenerator(chunk);
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
        for (LIN.CodeChunk cc : codeChunk) {
            sb.append("######" + cc.frame.label.name + "#####\n");
            sb.append(cc.frame.label.name+"#TODO: PROLOGUE\n");
            for(IMC.Stmt stmt : cc.stmts()) {
                sb.append(stmt.accept(asmGenerator, null));
            }
            sb.append(cc.exitLabel.name+"#TODO: EPILOGUE\n");

            sb.append("####################\n");
        }
        return sb.toString();
    }

	
}
