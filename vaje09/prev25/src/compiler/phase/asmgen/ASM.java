package compiler.phase.asmgen;

import java.util.HashMap;
import java.util.Vector;

import compiler.common.logger.*;
import compiler.common.report.Report;
import compiler.phase.imcgen.IMC;
import compiler.phase.memory.MEM;


// https://msyksphinz-self.github.io/riscv-isadoc/html/rvi.html

public class ASM {

	public static abstract class Line {
    }

	public static HashMap<String, Register> registers = new HashMap<>();

	public static final Register zero = new Register("x0");
	public static final Register retaddr = new Register("x1");
	public static final Register sp = new Register("x2");
	public static final Register gp = new Register("gp");
	public static final Register t2 = new Register("t2");

	public static class AsmChunk {
		public Vector<Line> lines = new Vector<>();

		public void addLine(Line line) {
			lines.add(line);
		}
	}

	public static class Register{
		String name;
		IMC.TEMP connectedTemp; 
		public Register(String name) {
			this.name = name;
			registers.put(name, this);
		}
		public Register(IMC.TEMP temp) {
			this.name = temp.temp.toString();
			this.connectedTemp = temp;
			registers.put(name, this);
		}
		public String toString() {
			if(connectedTemp != null) {
				return connectedTemp.temp.toString();
			}
			return name;
		}
	}

    
    public static class Label extends Line {
        public IMC.LABEL lbl;

        public Label(IMC.LABEL lbl) {
            this.lbl = lbl;
        }

        public String toString() {
            return lbl.label.name;
        }
    }

    public abstract static class Instr extends Line {
        public String opcode;
        public Vector<Register> use = new Vector<>();
        public Vector<Register> def = new Vector<>();
		public Instr(String opcode) {
			this.opcode = opcode;
		}
		public Instr(String opcode, Vector<Register> use, Vector<Register> def) {
			this.opcode = opcode;
			this.use = use;
			this.def = def;
		}
    }

    public static class JumpAndLink extends Instr{
		public Label label;
		public Register rd;

		public JumpAndLink(String opcode, Register rd, Label label) {
			super(opcode);
			this.label = label;
			this.rd = rd;
			this.def.add(rd);
		}

		public String toString() {
			return String.format("%s %s, %s", opcode, rd, label);
		}
    }

    public static class DataEntry extends Line{
		public String toString(){
			return ".data";
		}
	}

	public static class Data extends Line{
		String label;
		String data;
		boolean neki;
		public Data(String lbl, String dt, boolean n){
			label = lbl;
			data = dt;
			neki = n;
		}
		public String toString(){
			if(neki){
				StringBuilder sb = new StringBuilder(label+": .data");
				sb.append("0".repeat((data.length())));
				return sb.toString();
			}
			
			StringBuilder sb = new StringBuilder(label+": ");
			for(int i=0; i<data.length()-1; i++){
				char x = data.charAt(i);
				sb.append(Integer.toHexString((int)x) + "");
			}
			char x = data.charAt(data.length()-1);
			sb.append(Integer.toHexString((int)x)+"00");
			return sb.toString();
		}
	}




	public static class SingleRegInstr extends Instr{
		public Register rs1;
		public Register rd;

        public SingleRegInstr(String opcode, Register rd, Register rs1) {
			super(opcode);
			use.add(rs1);
			def.add(rd);
			this.rd = rd;
            this.rs1 = rs1;
            this.use.add(rs1);
            this.def.add(rd);
        }
		
		public String toString() {
            return String.format("%s %s, %s", opcode, rd, rs1);
        }
	}


	public static class LoadAddress extends Instr{
		public ASM.Label rs1;
		public Register rd;

		public LoadAddress(String opcode, Register rd, ASM.Label lbl){
			super(opcode);
			rs1 = lbl;
			this.rd = rd;
			this.def.add(rd);
		}
		public String toString(){
			return String.format("%s %s, %s",opcode, rd, rs1);
		}
	}

	public static class BreakIf extends Instr{
		public String opcode;
		public Register rs1;
		public Register rs2;
		public Label lbl;
		public BreakIf(String opcode,Register rs1, Register rs2, Label lbl) {
			super(opcode);
			this.opcode = opcode;
			this.lbl = lbl;
			this.rs1 = rs1;
			this.rs2 = rs2;
			super.use.add(rs1);
			super.use.add(rs2);
		}
		public String toString() {
			return String.format("%s %s, %s", opcode, rs1, rs2);
		}
	}

	/*public static class RegWithLabel extends Instr{
		public Register rs1;
		public Label label;
		public Register rd;
		public RegWithLabel(String opcode, Register rd, Register rs1, Label label) {
			super(opcode);
			this.rd = rd;
			this.rs1 = rs1;
			this.label = label;
		}
		public String toString() {
			return String.format("%s %s, %s", rs1, label);
		}
	}*/


	public static class DoubleRegInstr extends Instr{
		public Register rs1;
		public Register rs2;
		public Register rd;

		public DoubleRegInstr(String opcode, Register rd, Register rs1, Register rs2) {
			super(opcode);
			this.rd = rd;
			this.rs1 = rs1;
			this.rs2 = rs2;
			this.use.add(rs1);
			this.use.add(rs2);
			this.def.add(rd);
		}

		public String toString() {
			return String.format("%s %s, %s, %s", opcode, rd, rs1, rs2);
		}
	}

	public static class RegisterAndValue extends Instr{
		public long value;
		public Register rd;
		public RegisterAndValue(String opcode, Register rd, long value) {
			super(opcode);
			this.rd = rd;
			this.value = value;
			this.def.add(rd);
		}

		public String toString() {
			return String.format("%s %s, %s", opcode, rd.toString(), value);
		}
		
	}

	public static class MathOperationWithReg extends Instr{
		public Register rs1;
		public Register rs2;
		public Register rd;
		public long value;
		public MathOperationWithReg(String opcode, Register rd, Register rs1, Register rs2) {
			super(opcode);
			this.rd = rd;
			this.rs1 = rs1;
			this.rs2 = rs2;
			this.use.add(rs1);
			this.use.add(rs2);
			this.def.add(rd);
		}
		public String toString() {
			return String.format("%s %s, %s, %s", opcode, rd.toString(), rs1.toString(), rs2.toString());
		}
	}
	public static class MathOperationWithValue extends Instr{
		public Register rs1;
		public Register rd;
		public long value;
		public MathOperationWithValue(String opcode, Register rd, Register rs1, long value) {
			super(opcode);
			this.rd = rd;
			this.rs1 = rs1;
			this.value = value;
			this.use.add(rs1);
			this.def.add(rd);
		}
		public String toString() {
			return String.format("%s %s, %s, %s", opcode, rd.toString(), rs1.toString(), value);
		}
	}
	
	public static class Comment extends Line{
		String comm; 
		public Comment(String x){
			comm = "#"+x;
		}

		public String toString(){
			return comm;
		}
	}

	public static class RegisterAndOffset extends Instr{
		public Register rs1;
		public Register rd;
		public long offset;
		public RegisterAndOffset(String opcode, Register rd, Register rs1, long offset) {
			super(opcode);
			this.offset = offset;
			this.rd = rd;
			this.rs1 = rs1;
			this.use.add(rs1);
			this.def.add(rd);
		}
		public String toString() {
			return String.format("%s %s, %s(%s)", opcode, rd.toString(), offset, rs1.toString());
		}
	}



	public static class LoadData extends Instr{
		public Register rs1;
		public Register rd;
		public Object b;
		public ASM.Label b2;
		public LoadData(String opcode, Register rd, Register rs1, Object b) {
			super(opcode);
			this.b = b;
			if(b instanceof ASM.Label){
				this.b2 = (ASM.Label)b;
			}else{
				b2 = null;
			}
			this.rd = rd;
			this.rs1 = rs1;
			this.use.add(rs1);
			this.def.add(rd);
		}
		public String toString() {
			if(b2 == null)
				return String.format("%s %s, %s(%s)", opcode, rd.toString(), b.toString(), rs1.toString());
			return String.format("%s %s, %s(%s)", opcode, rd.toString(), b, rs1.toString());
		}
	}

    
}
