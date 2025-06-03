package compiler.phase.regall;

import compiler.common.report.*;
import compiler.phase.*;
import compiler.phase.asmgen.*;
import compiler.phase.asmgen.ASM.*;

import java.util.*;

import compiler.phase.livean.*;
import compiler.phase.memory.MEM;
import compiler.phase.memory.SizeEvaluator;
import compiler.phase.seman.TYP;
import compiler.phase.imcgen.*;

public class RegAll extends Phase{

    public final HashMap<Register, Integer> tempToReg = new HashMap<Register, Integer>();
    public static Map<ASM.Register, Integer> firstUse = new HashMap<>();
    public static Map<ASM.Register, Integer> lastUse = new HashMap<>();
    public static int highestRegister = 0;

    public Vector<ASM.AsmChunk> vseFunkcije; 
    int numberOfRegisters;
    public RegAll(){
        super("regall");
        numberOfRegisters = 27;
        allRegisters = new Register[32];
        for(int i=4; i<32; i++){
            StringBuilder sb = new StringBuilder("x");
            if(i == 8){
                continue;
            }
            sb.append(Integer.toString(i));
            allRegisters[i] = new Register(sb.toString());
        }
        allRegisters[0] = ASM.zero;
        allRegisters[1] = ASM.retaddr;
        allRegisters[2] = ASM.sp;
        allRegisters[3] = ASM.gp;
        allRegisters[8] = ASM.fp;
        allRegisters[10] = ASM.a0;

        tempToReg.put(ASM.zero, 0);
        tempToReg.put(ASM.retaddr, 1);
        tempToReg.put(ASM.sp, 2);
        tempToReg.put(ASM.gp, 3);
        tempToReg.put(ASM.a0, 10);
    }

    public RegAll(int x){
        super("regall");
        if(x > 27)
            throw new Report.Error("Please choose number of registers betweem 2 and 28, as others are required for proper usage");
        numberOfRegisters = x;
        allRegisters = new Register[32];
        for(int i=4; i<32; i++){
            StringBuilder sb = new StringBuilder("x");
            if(i == 8){
                continue;
            }
            sb.append(Integer.toString(i));
            allRegisters[i] = new Register(sb.toString());
        }
        allRegisters[0] = ASM.zero;
        allRegisters[1] = ASM.retaddr;
        allRegisters[2] = ASM.sp;
        allRegisters[3] = ASM.gp;
        allRegisters[8] = ASM.fp;
        allRegisters[10] = ASM.a0;
        tempToReg.put(ASM.zero, 0);
        tempToReg.put(ASM.retaddr, 1);
        tempToReg.put(ASM.sp, 2);
        tempToReg.put(ASM.gp, 3);
        tempToReg.put(ASM.a0, 10);
    }
    
    public Register[] allRegisters;
    // builds graph for single functions, as we dont really care about other functions as of now
    public Graph build(ASM.AsmChunk koda){
        Graph graph = new Graph();
        LiveAn la = new LiveAn(null);
        ASM.AsmChunk x = la.livean(koda);
        System.out.println("This is for debug purposes");
        System.out.println(x.toString());
        for(var tmp : x.lines){
            // if the line is an instruction, we add it to the graph 
            if(tmp instanceof ASM.Instr instruction){
                for(Register specificUseNode : instruction.use){
                    graph.addNode(specificUseNode);
                }
                for (Register specificDefNode : instruction.def){
                    graph.addNode(specificDefNode);
                }
            }
        }
        for(var tmp : x.lines){
            if(tmp instanceof ASM.Instr instruction){
                for(Register defTemp : instruction.def){
                    for(Register useTemp : instruction.use){
                        graph.addEdge(defTemp, useTemp);
                    }
                }
            }
        }
        graph.removeNode(ASM.zero);
        graph.removeNode(ASM.retaddr);
        graph.removeNode(ASM.sp);
        graph.removeNode(ASM.gp);
        graph.removeNode(ASM.fp);
        graph.removeNode(ASM.a0);

        la = null;
        return graph;
    }


    public HashMap<Register, Integer> getLifespanLength(AsmChunk function){
        HashMap<Register, Integer> neki = new HashMap<>();
        for(var line : function.lines){
            if (line instanceof ASM.Instr instr){
                
                Register usedReg = instr.def.get(0);
                
                // We haven't resolved 
                if(neki.get(usedReg) == null){

                }
            }
        }
        return neki;
    }


    
    // is the graph able to be simplified
    public boolean simplify(Graph ig, Stack<Node> stack){
        for (Node node : ig.nodes()){
            if(node.degree() < numberOfRegisters){
                ig.removeNode(node.temporary);
                stack.push(node);
                return true;
            }
        }
        return false;
    }

    public Node spill(Graph graph, Stack<Node> stack){
        int max = numberOfRegisters;
        Node selectedNode = null;

        for (Node n2 : graph.nodes()){
            if(n2.degree() >= max){
                max = n2.degree();
                selectedNode = n2;
            }
        }

        if(selectedNode != null){
            selectedNode.potentialSpill = true;
            graph.removeNode(selectedNode.temporary);
            stack.push(selectedNode);
        }

        
        return selectedNode;
    }

    public Vector<Register> select(Graph reconstructedGraph, HashMap<Node, HashSet<Node>> edges, Stack<Node> stack){
        Vector<Register> spills = new Vector<>();

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();

            HashSet<Node> neighbours = reconstructedGraph.nodes();
            neighbours.retainAll(edges.get(currentNode));

            boolean isPossibleColour[] = new boolean[Math.min(numberOfRegisters + 5, 32)];
            for (int i = 0; i < isPossibleColour.length; i++) {
                switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 8:  // frame pointer,   s0
                case 10: // return value,    a0
                case 17: // syscal register, a7
                    break;
                default:
                    isPossibleColour[i] = true;
                    break;
                }
            }

            reconstructedGraph.addNode(currentNode);
            for (Node neighbour : neighbours) {
                reconstructedGraph.addEdge(currentNode, neighbour);
                if (neighbour.color >= 0) {
                    isPossibleColour[neighbour.color] = false;
                }
            }

            for (int i = 0; i < isPossibleColour.length; i++) {
                if (isPossibleColour[i]) {
                    currentNode.color = i;
                    break;
                }
            }

            if (currentNode.color < 0) {
                currentNode.actualSpill = true;
                spills.add(currentNode.temporary);
            }
        }
        System.out.println("[");
        for(var x : spills){
            System.out.print(x.toString()+", ");
        }
        System.out.println("]");

        return spills;
    }


    //TODO
    private ASM.Instr modifyInstr(ASM.Instr instr, Register newRegister, int pos){
        ASM.Instr x = instr;
        return x;
    }

    //TODO
    public void modify(AsmChunk code, Vector<Register> spills){
        for(Register spill : spills){
            Vector<ASM.Line> modifiedInstr = new Vector<>();
            long ptrSize = new TYP.PtrType(TYP.VoidType.type).accept(new SizeEvaluator(), null);
            int overflows = (int) (code.frameOfCode.locsSize / ptrSize);
            code.tmpSize += ptrSize;
            long offset = code.frameOfCode.locsSize + 2*ptrSize + overflows * ptrSize + ptrSize;
            for(ASM.Line line : code.lines){
                if(!(line instanceof ASM.Instr))
                    continue;
                ASM.Instr instr = (ASM.Instr)line;
                boolean usesSpilledTemp = instr.use.contains(spill);
                boolean definesSpilledTemp = instr.def.contains(spill);

                //TODO
                if(usesSpilledTemp || definesSpilledTemp){
                    if(usesSpilledTemp){
                        Register resTmp = new ASM.Register(new IMC.TEMP(new MEM.Temp()));
                        Vector<Register> definesLoad = new Vector<>();
                        Vector<Register> uses = new Vector<>();
                        definesLoad.add(resTmp);
                        var load = new ASM.RegisterAndOffset("ld", resTmp, ASM.fp, offset);
                        load.def=definesLoad;
                        modifiedInstr.add(load);


                        // Replace the register used with the temporary register
                        for(Register usedTmp : instr.use){
                            if(usedTmp == spill){
                                uses.add(resTmp);
                            }else{
                                uses.add(usedTmp);
                            }
                        }
                    }
                }else{
                    modifiedInstr.add(line);
                }

                code.lines = new Vector<>(modifiedInstr);
            }
        }
    }


     public void allocate(){
        
        for (ASM.AsmChunk code : vseFunkcije) {
            boolean coloringFound        = false;
            Graph reconstructedGraph = null;
            int i = 0;
            while (!coloringFound) {
                Graph interferenceGraph = new Graph(code);
                HashMap<Node, HashSet<Node>> edges = interferenceGraph.edges();
                Stack<Node> stack                      = new Stack<>();
                do {
                    while (simplify(interferenceGraph,stack)) {}
                    this.spill(interferenceGraph, stack);
                } while (!interferenceGraph.isEmpty());

                reconstructedGraph = new Graph();
                Vector<Register> spills = this.select(reconstructedGraph, edges, stack);
                coloringFound = spills.size() == 0;

                if (coloringFound) {
                    break;
                }

                //this.modifyCode(code, spills);
            }

            this.tempToReg.put(new Register(code.frameOfCode.FP), 8);
            for (Node node : reconstructedGraph.nodes()) {
                this.tempToReg.put(node.temporary, node.color);
            }

            var vals = this.tempToReg.values();
            for (int reg : vals) {
                if (reg > highestRegister) {
                    highestRegister = reg;
                }
            }
            System.out.println("FUCK");
            for (Node node : reconstructedGraph.nodes()) {
                System.out.println(node.toString() + " " + node.color);
				this.tempToReg.putIfAbsent(node.temporary, node.color);
			}
            for(ASM.Line x : code.lines){
                if(x instanceof JumpAndLink jl){
                    jl.rd = allRegisters[this.tempToReg.get(jl.rd)];
                }
                if(x instanceof SingleRegInstr sri){
                    sri.rs1 = allRegisters[this.tempToReg.get(sri.rs1)];
                    sri.rd = allRegisters[this.tempToReg.get(sri.rd)];
                }
                if(x instanceof LoadAddress la){
                    la.rd = allRegisters[this.tempToReg.get(la.rd)];
                }
                if(x instanceof BreakIf bi){
                    bi.rs1 = allRegisters[this.tempToReg.get(bi.rs1)];
                    System.out.println(bi.rs2);
                    bi.rs2 = allRegisters[this.tempToReg.get(bi.rs2)];
                }
                if(x instanceof DoubleRegInstr dri){
                    dri.rs1 = allRegisters[this.tempToReg.get(dri.rs1)];
                    System.out.println(dri.rs2);
                    dri.rs2 = allRegisters[this.tempToReg.get(dri.rs2)]; 
                    dri.rd = allRegisters[this.tempToReg.get(dri.rd)];
                }
                if(x instanceof RegisterAndValue rav){
                    rav.rd = allRegisters[this.tempToReg.get(rav.rd)];
                }

                if(x instanceof MathOperationWithReg mowr){
                    System.out.println(mowr.rs2);
                    mowr.rs1 = allRegisters[this.tempToReg.get(mowr.rs1)];
                    mowr.rs2 = allRegisters[this.tempToReg.get(mowr.rs2)];
                    mowr.rd = allRegisters[this.tempToReg.get(mowr.rd)];
                }
                if(x instanceof MathOperationWithValue mowv){
                    mowv.rs1 = allRegisters[this.tempToReg.get(mowv.rs1)];
                    mowv.rd = allRegisters[this.tempToReg.get(mowv.rd)];
                }
                if(x instanceof RegisterAndOffset rao){
                    rao.rs1 = allRegisters[this.tempToReg.get(rao.rs1)]; 
                    rao.rd = allRegisters[this.tempToReg.get(rao.rd)];
                }
                if(x instanceof LoadData ld){
                    ld.rs1 = allRegisters[this.tempToReg.get(ld.rs1)];
                    ld.rd = allRegisters[this.tempToReg.get(ld.rd)];
                }
            }
            System.out.println(reconstructedGraph.toString());
		}
    }


    public void log2(){
        for(AsmChunk x : vseFunkcije){
            if(x.lines.get(0) instanceof ASM.NameOfFrame l){
                System.out.println(String.format("Start of Code Chunk: %s", l.toString()));
            }
            for(var z : x.lines){
                System.out.println(z.toString());
            }
        }
    }
}