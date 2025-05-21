package compiler.phase.livean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import compiler.phase.Phase;
import compiler.phase.asmgen.*;

public class LiveAn extends Phase{
    Vector<ASM.AsmChunk> koda;
    public LiveAn(Vector<ASM.AsmChunk> koda){
        super("livean");
        this.koda = koda;
    }

    public void log2(){
        for(var x : koda){
            if(x.lines.get(0) instanceof ASM.NameOfFrame l){
                System.out.println(String.format("Start of Code Chunk: %s", l.toString()));
            }
            performLivenessAnalysis(x);
        }
    }

      public static void performLivenessAnalysis(ASM.AsmChunk chunk) {
        Map<ASM.Instr, Set<ASM.Register>> in = new HashMap<>();
        Map<ASM.Instr, Set<ASM.Register>> out = new HashMap<>();
        Map<String, ASM.Instr> labelToInstr = new HashMap<>();
        List<ASM.Instr> instructions = new ArrayList<>();

        for (int i = 0; i < chunk.lines.size(); i++) {
            ASM.Line line = chunk.lines.get(i);
            if (line instanceof ASM.Label label) {
                if (i + 1 < chunk.lines.size() && chunk.lines.get(i + 1) instanceof ASM.Instr nextInstr) {
                    labelToInstr.put(label.lbl.label.name, nextInstr);
                }
            } else if (line instanceof ASM.Instr instr) {
                instructions.add(instr);
                in.put(instr, new HashSet<>());
                out.put(instr, new HashSet<>());
            }
        }

        boolean changed;
        do {
            changed = false;
            for (int i = instructions.size() - 1; i >= 0; i--) {
                ASM.Instr instr = instructions.get(i);

                Set<ASM.Register> inOld = new HashSet<>(in.get(instr));
                Set<ASM.Register> outOld = new HashSet<>(out.get(instr));

                // Calculate out[n]
                Set<ASM.Register> newOut = new HashSet<>();
                if (instr instanceof ASM.JumpAndLink jmp) {
                    ASM.Instr target = labelToInstr.get(jmp.label.lbl.label.name);
                    if (target != null) newOut.addAll(in.getOrDefault(target, Set.of()));
                } else if (instr instanceof ASM.BreakIf brk) {
                    ASM.Instr target = labelToInstr.get(brk.lbl.lbl.label.name);
                    if (target != null) newOut.addAll(in.getOrDefault(target, Set.of()));
                    if (i + 1 < instructions.size())
                        newOut.addAll(in.get(instructions.get(i + 1)));
                } else {
                    if (i + 1 < instructions.size())
                        newOut.addAll(in.get(instructions.get(i + 1)));
                }

                // Calculate in[n]
                Set<ASM.Register> newIn = new HashSet<>(instr.use);
                Set<ASM.Register> tempOut = new HashSet<>(newOut);
                tempOut.removeAll(instr.def);
                newIn.addAll(tempOut);

                in.put(instr, newIn);
                out.put(instr, newOut);

                if (!newIn.equals(inOld) || !newOut.equals(outOld)) {
                    changed = true;
                }
            }
        } while (changed);

        for (ASM.Instr instr : instructions) {
            System.out.println(instr + "\nIN: " + in.get(instr) + "\nOUT: " + out.get(instr) + "\n");
        }
    }

}
