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
	public static final Register gp = new Register("x3");

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
            return lbl.label.toString();
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

    
	public class SingleRegInstr extends Instr{
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
			return String.format("break %s, %s", rs1, rs2);
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


	public class DoubleRegInstr extends Instr{
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

	public class RegisterAndValue extends Instr{
		public long value;
		public Register rd;
		public RegisterAndValue(String opcode, Register rd, long value) {
			super(opcode);
			this.rd = rd;
			this.value = value;
			this.def.add(rd);
		}
		
	}
	
	public class RegisterAndOffset extends Instr{
		public Register rs1;
		public Register rd;
		public long offset;
		public RegisterAndOffset(String opcode, Register rd, Register rs1, long offset) {
			super(opcode);
			this.offset = offset;
			this.rd = rd;
			this.use.add(rs1);
			this.def.add(rd);
		}
		public String toString() {
			return String.format("%s %s, %l(%s)", opcode, rd.toString(), offset, rs1.toString());
		}
	}

    
}
