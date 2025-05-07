package compiler.phase.asmgen;


import java.util.Vector;
import compiler.phase.abstr.AST;
import compiler.phase.abstr.AST.Nodes;
import compiler.phase.asmgen.ASM.AsmChunk;
import compiler.phase.imcgen.IMC;
import compiler.phase.imcgen.IMC.*;
import compiler.phase.imcgen.NekiNovega;
import compiler.phase.memory.MEM;
import compiler.common.report.*;
import compiler.phase.imclin.*;


public class AsmGenerator implements IMC.Visitor<Object, AsmChunk> {
    
    public AsmGenerator() {
        // Constructor
    }

    public AsmGenerator(Vector<LIN.CodeChunk> chunk) {
        vseFunkcije = chunk;
    }
	public Object visit(Vector<IMC.Instr> nodes, AsmChunk arg) {
        for (final IMC.Instr node : nodes)   {
            node.accept(this, arg);
		}
		return null;
	}
    public Vector<LIN.CodeChunk> vseFunkcije;

    @Override
    public Object visit(IMC.CJUMP cjump, AsmChunk arg) {
        IMC.BINOP binop = (IMC.BINOP) cjump.cond;
        String lhs = binop.fstExpr.toString(); 
        String rhs = binop.sndExpr.toString();
        String labelTrue = cjump.posAddr.toString();             // true branch label
        String labelFalse = cjump.negAddr.toString();            // false branch label
        String instr;

        switch (binop.oper) {
        case AND : 
            IMC.TEMP x = new IMC.TEMP(new MEM.Temp());
            instr = String.format("and %s, %s, %s", x.toString(), lhs, rhs) + "\n";
            instr += String.format("bne %s, %s, %s", x.toString(), x.toString(), "x0");
            break;
        case OR:
            IMC.TEMP y = new IMC.TEMP(new MEM.Temp());
            instr = String.format("or %s, %s, %s", y.toString(), lhs, rhs) + "\n";
            instr += String.format("bne %s, %s, %s", y.toString(), y.toString(), "x0");
            break;
        case EQU:
            instr = String.format("beq %s, %s, %s", lhs, rhs, labelTrue);
            break;
        case NEQ: 
            instr = String.format("bne %s, %s, %s", lhs, rhs, labelTrue);
            break;
        case LTH: 
            instr = String.format("blt %s, %s, %s", lhs, rhs, labelTrue);
            break;
        case LEQ: 
            instr = String.format("ble %s, %s, %s", lhs, rhs, labelTrue);
            break;
        case GTH: 
            instr = String.format("bgt %s, %s, %s", lhs, rhs, labelTrue);
            break;
        case GEQ: 
            instr = String.format("bge %s, %s, %s", lhs, rhs, labelTrue);
            break;
        default:

            throw new Report.InternalError("Unsupported binary operator in CJUMP.");
        }

        // Emit false jump after the conditional branch
        String falseJump = String.format("j %s", labelFalse);

        return instr + "\n" + falseJump;
    }

    public String visit(IMC.ESTMT estmt, AsmChunk arg){
        if(estmt.expr instanceof IMC.CALL x) {
            if(x.addr instanceof IMC.NAME y) {
                IMC.TEMP x2 = new IMC.TEMP(new MEM.Temp());
                LIN.CodeChunk chunk2 = null;
                Report.warning(y.label.name);
                for(LIN.CodeChunk chunk : vseFunkcije) {
                    if(chunk.frame.label.name.equals(y.label.name)) {
                        chunk2 = chunk;
                        break;
                    }
                }
                MEM.Label lbl = y.label;
                String args = push(x.args);
                StringBuilder sb = new StringBuilder();
                sb.append("###fixing sp###\n");
                for(int i=0; i<x.args.size(); i++) {
                    sb.append("addi sp, x0, 8\n");
                }
                sb.append("###fixed sp###\n");

                if(chunk2 == null){
                    return "#outside function\njal " + lbl.name + "\n";
                }
                return 
                    args+
                    "jal " + 
                    lbl.name + 
                    "\n"+
                    "ld " + x2.temp.toString() + ", 0(x1)\n"+
                    sb.toString();
            }
        }
        return "";
    }

    @Override
    public String visit(IMC.JUMP jump, AsmChunk arg) {
        IMC.NAME x = (IMC.NAME)jump.addr;
        MEM.Label lbl = x.label;
        return "jal x0, " + lbl.name + "\n";
    }

    public String pop(IMC.Expr b){
        return "TODO";
    }

    public String push(Vector<IMC.Expr> b){
        
        StringBuilder sb = new StringBuilder();
        sb.append("#pushing args\n");
        for(IMC.Expr expr : b) {
            if(expr instanceof IMC.TEMP x) {
                sb.append("sd " + x.temp.toString() + ", 0(sp)\n");   
            }else if(expr instanceof IMC.CONST x) {
                sb.append("sd " + x.value + ", 0(sp)\n");
            }else if(expr instanceof IMC.TEMP x) {
                MEM.Temp lbl = x.temp;
                sb.append("sd " + lbl.toString() + ", 0(sp)\n");
            }
            sb.append("addi sp, sp, -8\n");
        }
        sb.append("#finished pushing args\n");
        return sb.toString();
    }

    @Override
    public String visit(IMC.LABEL label, AsmChunk arg) {
        MEM.Label lbl = label.label;
        return lbl.name + "\t#this is in a new line, this should be in line with the documentation for labels in RISC-V  \n";
    }

    @Override
    public String visit(IMC.MOVE move, AsmChunk arg) {
        if(move.dst instanceof IMC.TEMP x && move.src instanceof IMC.BINOP y){
            String src1 = y.fstExpr.toString();
            String src2 = y.sndExpr.toString();
            String dst = x.temp.toString();
            if(y.fstExpr instanceof IMC.TEMP b && y.sndExpr instanceof IMC.TEMP c){ 
                src1 = b.temp.toString();
                src2 = c.temp.toString();
                switch(y.oper) {
                    case ADD:  
                        return String.format("add %s, %s, %s", dst, src1, src2) + "\n";
                    case SUB:  
                        return String.format("sub %s, %s, %s", dst, src1, src2) + "\n";
                    case MUL:  
                        return String.format("mul %s, %s, %s", dst, src1, src2) + "\n";
                    case DIV: 
                        return String.format("div %s, %s, %s", dst, src1, src2) + "\n";
                    default:
                        throw new Report.InternalError("Unsupported binary operator in MOVE.");
                }
            }
            if(y.fstExpr instanceof IMC.CONST b2 && y.sndExpr instanceof IMC.TEMP c){
                src2 = c.temp.toString();
                IMC.TEMP b3 = new IMC.TEMP(new MEM.Temp());
                switch(y.oper) {
                    case ADD:
                        return String.format("addi %s, %s, %s", dst, b2.value, src2) + "\n";
                    case SUB:
                        String y12 = "addi " + b3 + ",x0" + ", "+ b2.value+"\n";
                        return y12+String.format("sub %s, %s, %s", dst, b3, src2) + "\n";
                    case MUL:
                        String y11 = "addi " + b3 + ", x0," + b2.value+"\n";
                        return y11+String.format("mul %s, %s, %s", dst, b3, src2) + "\n";
                    case DIV:
                        String y31231 = "addi " + b3 + ",x0" + ", "+ b2.value+"\n";
                        return y31231+String.format("div %s, %s, %s", dst, b3, src2) + "\n";
                    default:
                        throw new Report.InternalError("Unsupported binary operator in MOVE.");
                }
            }else if (y.sndExpr instanceof IMC.CONST b2 && y.fstExpr instanceof IMC.TEMP c){
                IMC.TEMP b3 = new IMC.TEMP(new MEM.Temp());
                src1 = c.temp.toString();
                switch(y.oper) {
                    case ADD:                       
                        return String.format("addi %s, %s, %s", dst, src1, b2) + "\n";
                    case SUB:   
                        String y12 = "addi " + b3 + ",x0" + ", "+ b2.value+"\n"; 
                        return y12+String.format("sub %s, %s, %s", dst, src1, b3) + "\n";
                    case MUL:
                        String y11 = "addi " + b3 + ", x0," + b2.value+"\n";
                        return y11+String.format("mul %s, %s, %s", dst, src1, b3) + "\n";
                    case DIV:    
                        String y31231 = "addi " + b3 + ",x0" + ", "+ b2.value+"\n";
                        return y31231+String.format("div %s, %s, %s", dst, src1, b3) + "\n";
                    default:
                        throw new Report.InternalError("Unsupported binary operator in MOVE.");
                }
            }
            
            
        }
        if(move.dst instanceof IMC.BINOP y && move.src instanceof IMC.TEMP x){
            IMC.BINOP binop = (IMC.BINOP)move.dst;
            String dst1 = binop.fstExpr.toString();
            String dst2 = binop.sndExpr.toString();
            String src = x.temp.toString();
            return String.format("add %s, %s, %s", dst1, dst2, src) + "\n";
        }
        if(move.dst instanceof IMC.MEM8 Y) {
            
            String dst = ((IMC.TEMP)Y.addr).temp.toString();
            String src = ((IMC.TEMP)move.src).temp.toString();
            return String.format("sd %s, 0(%s)", src, dst) + "\n";
        }else if(move.dst instanceof IMC.MEM1) {
            IMC.TEMP temp = (IMC.TEMP)move.dst;
            String dst = temp.temp.toString();
            String src = move.src.toString();
            return String.format("sb %s, 0(%s)", dst, src) + "\n";
        }
        if(move.dst instanceof IMC.TEMP && move.src instanceof IMC.TEMP x) {
            IMC.TEMP temp = (IMC.TEMP)move.dst;
            String dst = temp.temp.toString();
            String src = x.temp.toString();
            return String.format("add %s, %s, %s", dst, "x0", src) + "\n";
        }
        if(move.dst instanceof IMC.TEMP && move.src instanceof IMC.CONST b) {
            IMC.TEMP temp = (IMC.TEMP)move.dst;
            String dst = temp.temp.toString();
            return String.format("addi %s, %s, %s", dst, "x0",  b.value) + "\n";
        }
        if(move.dst instanceof IMC.TEMP x && move.src instanceof IMC.MEM1 y){
            String dst = x.temp.toString();
            String src = y.addr.toString();
            if(y.addr instanceof IMC.TEMP b){
                src = b.temp.toString();
            }else if(y.addr instanceof IMC.NAME x2){
                MEM.Label lbl = x2.label;
                src = lbl.name;    
            }
            return String.format("lb %s, 0(%s)", dst, src) + "\n";
    
        }else if(move.dst instanceof IMC.TEMP x && move.src instanceof IMC.MEM8 y){
            String dst = x.temp.toString();
            String src = y.addr.toString();
            if(y.addr instanceof IMC.TEMP b){
                src = b.temp.toString();
            }else if(y.addr instanceof IMC.NAME x2){
                MEM.Label lbl = x2.label;
                src = lbl.name;
            }
            return String.format("ld %s, 0(%s)", dst, src) + "\n";
        }
    
        if(move.dst instanceof IMC.TEMP x && move.src instanceof IMC.NAME y){;
            String dst = x.temp.toString();
            return String.format("add %s, x0, x0", dst) + "\n";
        }

        if(move.dst instanceof IMC.TEMP x && move.src instanceof CALL y){
            if(y.addr instanceof IMC.NAME z){
                LIN.CodeChunk chunk2 = null;
                for(LIN.CodeChunk chunk : vseFunkcije) {
                    if(chunk.frame.label.name.equals(z.label.name)) {
                        chunk2 = chunk;
                        break;
                    }
                }
                //System.out.println("nisem cist ziher kje se output ");
                MEM.Label lbl = z.label;
                String args = push(y.args);
                StringBuilder sb = new StringBuilder();
                sb.append("###fixing sp###\n");
                for(int i=0; i<y.args.size(); i++) {
                    sb.append("addi sp, x0, 8\n");
                }
                sb.append("###fixed sp###\n");
                return 
                    args+
                    "jal " + 
                    lbl.name + 
                    "\n"+
                    "ld " + x.temp.toString() + ", 0(x1)\n"+
                    sb.toString();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("###fixing sp###\n");
            for(int i=0; i<y.args.size(); i++) {
                sb.append("addi sp, x0, 8\n");
            }
            sb.append("###fixed sp###\n");
            //arg.addLine(new ASM.SingleRegInstr("jal", ));
            return push(y.args)+"jal " + ((IMC.TEMP)(y.addr)).temp.toString() + "\n" + sb.toString();
        }

        Report.warning(move.toString());
        throw new Report.InternalError("What the sigma");
    }

    @Override
    public String visit(IMC.STMTS stmts, AsmChunk arg) {
        throw new Report.InternalError("How did you manage to visit stmts after the linearization?");
    }
}
