package compiler.phase.all;

import java.util.*;
import java.io.*;
import compiler.phase.Phase;
import compiler.phase.asmgen.ASM;
import compiler.phase.asmgen.ASM.AsmChunk;
import compiler.phase.asmgen.ASM.LoadAddress;
import compiler.phase.asmgen.ASM.MathOperationWithValue;
import compiler.phase.asmgen.ASM.Register;
import compiler.phase.asmgen.ASM.RegisterAndOffset;
import compiler.phase.imcgen.IMC;
import compiler.phase.imclin.ImcLin;
import compiler.phase.imclin.LIN;
import compiler.phase.regall.*;
import compiler.phase.asmgen.*;
import compiler.phase.memory.*;
//Class to generate epilouge, prolouge and output to file 
public class All extends Phase{
    public File f;
    public Vector<AsmChunk> koda;
    public AsmChunk data;
    public ASM.Register[] allRegisters;
    protected HashMap<Register, Integer> registers;

    public RegAll rgInstance;
    public All(String fileName, Vector<ASM.AsmChunk> koda, ASM.AsmChunk data, ASM.Register[] all, RegAll rg){
        super("all");
        allRegisters = all;
        f = new File(fileName+".asm");
        this.koda = koda;
        this.data = data;
        registers = rg.tempToReg;
        try{
            f.createNewFile();
        }catch(Exception e){

        }
    }

    public Vector<ASM.Line> saveRegisters(ASM.AsmChunk x2){
        Vector<ASM.Line> x = new Vector<ASM.Line>();
        x.add(new ASM.Comment("Prologue"));
        x.add(x2.lines.get(0));
        for(int i = 1; i<32; i+=1){
            x.add(new RegisterAndOffset("sd", allRegisters[i], ASM.sp, -i*8-x2.tmpSize));
            /*x.add(
                new RegisterAndOffset
                ("addi", 
                ASM.sp,
                ASM.zero,
                -8L
                )
            );*/  
        }
        return x;
    }
    public AsmChunk updateTheThings(ASM.AsmChunk trenutnaFunkcija){

        //first do the prolog
        Vector<ASM.Line> x = new Vector<ASM.Line>();
        x = saveRegisters(trenutnaFunkcija);
        //System.out.println(x.toString());
        for(int i=1; i<trenutnaFunkcija.lines.size(); i++){
            x.add(trenutnaFunkcija.lines.get(i));
        }
        ASM.AsmChunk z2 = new ASM.AsmChunk(x);
        //then do the epilouge
        if(trenutnaFunkcija.frameOfCode != null){
            if(trenutnaFunkcija.frameOfCode.label.name.equals("_main")){
                z2.addLine(new ASM.NameOfFrame("\tret\n\n"));
                z2.addLine(new ASM.Comment("-----End of the main function-----"));
                return z2;
            }
        }
            //z2.addLine(new ASM.MathOperationWithReg("add", ASM.a0, ASM.zero, rgInstance.allRegisters[rgInstance.tempToReg.get(trenutnaFunkcija.frameOfCode.RV)]));
        for(int i=1; i<32; i++){
            z2.addLine(new RegisterAndOffset("ld", allRegisters[i], ASM.sp, -i*8-trenutnaFunkcija.tmpSize));
        }
        z2.addLine(new ASM.NameOfFrame("\tret\n\n"));
        z2.addLine(new ASM.Comment("-----End of function-----"));
        return z2;
    }
    protected Vector<String> codeToString(ASM.AsmChunk code) {
        Vector<String> instructions = new Vector<String>();

        for (ASM.Line instruction : code.lines) {
            String instr = "";
            if (instruction instanceof ASM.Label label) {
                instr = label + ":";
            } else {
                instr = "\t " + instruction.toString();
            }
            instr+="\n";

            instructions.add(instr);
        }

        return instructions;
    }

    private Vector<String> StdFun(String name, int ecallCode) {
        // TODO:
        MEM.Frame frame      = new MEM.Frame(new MEM.Label(name), 0, 0, 8 << 1, 16 << 1);
        MEM.Label entryLabel = new MEM.Label(),
                  exitLabel  = new MEM.Label();

        Vector<ASM.Line> instructions = new Vector<ASM.Line>();
        instructions.add(new ASM.Label((name)));
        for(int i = 1; i<32; i+=1){
            instructions.add(new RegisterAndOffset("sd", allRegisters[i], ASM.sp, -i*8-frame.locsSize));
            /*x.add(
                new RegisterAndOffset
                ("addi", 
                ASM.sp,
                ASM.zero,
                -8L
                )
            );*/  
        }
        instructions.add(new ASM.Label(new IMC.LABEL(entryLabel)));
        instructions.add(new ASM.RegisterAndOffset("ld", ASM.a0, ASM.s0, 8L));
        instructions.add(new ASM.RegisterAndValue("li", ASM.t2, ecallCode));
        instructions.add(new ASM.NameOfFrame("ecall"));

        ASM.AsmChunk code = new ASM.AsmChunk(frame);
        code.lines = instructions;
        saveRegisters(code);

        registers.put(new Register(code.frameOfCode.RV), 10);

        saveRegisters(code);
        for(int i=1; i<32; i++){
            code.addLine(new RegisterAndOffset("ld", allRegisters[i], ASM.sp, -i*8-code.tmpSize));
        }

        return codeToString(code);
    }

    private Vector<String> putcharFun() {
        return StdFun("putchar", 11);
    }

    private Vector<String> putsFun() {
        return StdFun("puts", 4);
    }

    private Vector<String> putintFun() {
        return StdFun("putint", 1);
    }

    private Vector<String> generateStdlib() {
        Vector<String> instructions = new Vector<String>();

        instructions.add("# Begin stdlib ===========================\n");

        // Function _new. We don't really need to create a new stack frame. So
        // simply get the first argument (number of bytes that we want to
        // reserve) and move the heap pointer down. Then set the old heap
        // pointer as the return value.
        instructions.add("_new:\n");
        instructions.add("\tld a0 8(sp)\n");
        instructions.add("\tsd gp 8(sp)\n");
        instructions.add("\tadd gp gp a0\n");
        instructions.add("\tld a0 8(sp)\n");
        instructions.add("\tret\n\n");

        instructions.add("_del:\tret\n\n");

        // Function _putchar
        instructions.addAll(putcharFun());
        instructions.addAll(putintFun());
        instructions.addAll(putsFun());

        instructions.add("# End stdlib =============================\n");

        return instructions;
    }

    public void makeBootStrap(FileWriter fw){
        Vector<String> instructions = new Vector<String>();

        instructions.add(".data");

        for (LIN.DataChunk dc : ImcLin.dataChunks()) {
            // If the initial value is not null, that means that this data chunk is a string.
            String instruction;
            if (dc.init != null) {
                // instruction = dc.label.name + ":\n\t.string \"" + dc.init + "\"";

                instruction = dc.label.name + ":\t.byte";

                int strsize = dc.init.length();
                for (int i = 0; i < strsize; i++) {
                    instruction += String.format(" %d", (int) dc.init.charAt(i));
                }

                instruction += " 0";
            } else {
                instruction = dc.label.name + ":";
                for (int i = 0; i < dc.size; i += 8) {
                    instruction += "\t.dword 0x0\n";
                }
            }

            instructions.add(instruction);
        }

        instructions.add("\n.text");
        instructions.add("li sp 0x000000007ffffff0");
        instructions.add("li s0 0x000000007ffffff0");
        instructions.add("li gp 0x0000000010000000");
        instructions.add("call _main");

        instructions.add("mv a1 a0");

        // TODO: Add exit instruction to our program
        instructions.add("li a0 0  # exit");
        instructions.add("li a7 93 # exit");
        instructions.add("ecall    # exit");

        Vector<String> x = generateStdlib();
        printVectorToFile(fw, instructions);
        printVectorToFile(fw, x);
    }

    public void printVectorToFile(FileWriter fw, Vector<String> str){
        for(var x : str){
            try{
                fw.append(x);
            }catch(Exception e){
            }
        }
    }

    public void printTheThing(){
        try{
            FileWriter fw = new FileWriter(f);
            fw.append(data.toString());
            makeBootStrap(fw);
            for(AsmChunk x : koda){
                var o = updateTheThings(x);
                fw.append(o.toString());
            }
            System.out.println("Zakaj to ne dela");
            fw.close();
        }catch(Exception e){
            
        }
        
        
    }

}