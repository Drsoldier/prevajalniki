package compiler.phase.regall;

import compiler.common.report.*;
import compiler.phase.*;
import compiler.phase.asmgen.*;
import compiler.phase.asmgen.ASM.*;

import java.util.*;

import compiler.phase.livean.*;

public class RegAll extends Phase{

    public final HashMap<Register, Integer> tempToReg = new HashMap<Register, Integer>();
    public Vector<ASM.AsmChunk> vseFunkcije; 
    int numberOfRegisters;
    public RegAll(){
        super("regall");
        numberOfRegisters = 27;
        allRegisters = new Register[numberOfRegisters];
        for(int i=4; i<32; i++){
            StringBuilder sb = new StringBuilder("x");
            if(i == 7 || i == 8){
                continue;
            }
            sb.append(Integer.toString(i));
            allRegisters[i] = new Register(sb.toString());
        }
        allRegisters[0] = ASM.zero;
        allRegisters[1] = ASM.retaddr;
        allRegisters[2] = ASM.sp;
        allRegisters[3] = ASM.gp;
        allRegisters[7] = ASM.t2;
        allRegisters[8] = ASM.fp;

    }

    public RegAll(int x){
        super("regall");
        if(x > 27)
            throw new Report.Error("Please choose number of registers betweem 2 and 28, as others are required for proper usage");
        numberOfRegisters = x;
    }
    
    Register[] allRegisters;
    // builds graph for single functions, as we dont really care about other functions as of now
    public Graph build(ASM.AsmChunk koda){
        Graph graph = new Graph();
        LiveAn la = new LiveAn(null);
        var x = la.livean(koda);
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
        graph.removeNode(ASM.gp);
        graph.removeNode(ASM.sp);
        graph.removeNode(ASM.zero);
        graph.removeNode(ASM.retaddr);

        la = null;
        return graph;
    }

    // is the graph able to be simplified
    public boolean simple(Graph ig, Stack<Node> stack){
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

        if(selectedNode == null){
            return selectedNode;
        }

        selectedNode.potentialSpill = true;
        graph.removeNode(selectedNode.temporary);
        stack.push(selectedNode);
        return null;
    }

    public Vector<Register> select(Graph reconstructedGraph, HashMap<Node, HashSet<Node>> edges, Stack<Node> stack){
        Vector<Register> spills = new Vector<>();

        boolean coloringFound = true;

        while(!stack.isEmpty()){
            Node currNode = stack.pop();

            HashSet<Node> neighbours = reconstructedGraph.nodes();
            neighbours.retainAll(edges.get(currNode));

            boolean isPossibleColor[] = new boolean[numberOfRegisters];

            for(int i=0; i<numberOfRegisters; i++){
                isPossibleColor[i] = true;
            }
            
            reconstructedGraph.addNode(currNode);
            for (Node neighbour : neighbours) {
				reconstructedGraph.addEdge(currNode, neighbour);
				if (neighbour.color >= 0)
					isPossibleColor[neighbour.color] = false;
			}

			for (int i = 0; i < isPossibleColor.length; i++) {
				if (isPossibleColor[i]) {
					currNode.color = i;
					break;
				}
			}

			if (currNode.color < 0) {
				// No color has been found
				currNode.actualSpill = true;
				coloringFound = false;
				spills.add(currNode.temporary);
			}


        }
        return spills;
    }

    public void modify(AsmChunk code, Vector<Register> spills){
        for (Register spill : spills){
            Vector<ASM.Line> newCode = new Vector<>();

            long pointerSize = 8l;
            int overflows = (int)(code.frameOfCode.locsSize / 8l);
        }
    }


    public void allocate(){

        for (AsmChunk code : vseFunkcije){
            boolean coloringFound;
            for(ASM.Line x : code.lines){
                if(x instanceof ASM.Instr z){
                    if (z.in.size() > numberOfRegisters || z.out.size() > numberOfRegisters){
                        throw new Report.Error("Could not allocate necessary registers with original code");
                        //System.out.println("A");
                    }
                }
            }

			Graph reconstructedGraph = null;
			do {
				
				// STEP 1: BUILD INTERFERENCE GRAPH
				Graph interferenceGraph = this.build(code);
				HashMap<Node, HashSet<Node>> edges = interferenceGraph.edges();
				
				Stack<Node> stack = new Stack<Node>();
				do {
					// STEP 2: PERFORM ONE STEP OF SIMPLIFICATION
					boolean hasChanged;
					do {
						hasChanged = this.simple(interferenceGraph, stack);
					} while (hasChanged);

					// STEP 3: SPILL
					Node removedNode = this.spill(interferenceGraph, stack);
				} while (!interferenceGraph.isEmpty());

				// STEP 4: SELECT - GRAPH COLORING
				reconstructedGraph = new Graph();
				Vector<Register> spills = this.select(reconstructedGraph, edges, stack);
				coloringFound = spills.size() == 0;
                System.out.println("found smth");
				if (coloringFound)
					break;
				
				// Coloring has not yet been found, the code must be modified
				// STEP 5: MODIFY THE CODE
                modify(code, spills);
                //throw new Report.Error("Could not allocate necessary registers with original code");

			} while (!coloringFound);

			// After coloring has been found, actually use register numbers to
			// assign registers. The frame pointer was not present in the graph
			// and is precolored to value 253.
			//this.tempToReg.put(new Register(code.frameOfCode.FP), 253);
			for (Node node : reconstructedGraph.nodes()) {
				this.tempToReg.put(node.temporary, node.color);
			}

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