package compiler.phase.all;

import java.util.*;
import java.io.*;
import compiler.phase.Phase;
import compiler.phase.asmgen.ASM;
import compiler.phase.asmgen.ASM.AsmChunk;
import compiler.phase.asmgen.ASM.LoadAddress;
import compiler.phase.asmgen.ASM.MathOperationWithValue;
import compiler.phase.asmgen.ASM.RegisterAndOffset;
import compiler.phase.regall.*;
import compiler.phase.asmgen.*;
import compiler.phase.memory.*;
//Class to generate epilouge, prolouge and output to file 
public class All extends Phase{
    public File f;
    public Vector<AsmChunk> koda;
    public AsmChunk data;
    public ASM.Register[] allRegisters;
    public RegAll rgInstance;
    public All(String fileName, Vector<ASM.AsmChunk> koda, ASM.AsmChunk data, ASM.Register[] all, RegAll rg){
        super("all");
        allRegisters = all;
        f = new File(fileName+".asm");
        this.koda = koda;
        this.data = data;

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

    public void printTheThing(){
        try{
            FileWriter fw = new FileWriter(f);
            fw.append(data.toString());
            //System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA");
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