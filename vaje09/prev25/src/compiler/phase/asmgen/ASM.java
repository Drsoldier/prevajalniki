package compiler.phase.asmgen;

import java.util.Vector;

import compiler.common.logger.*;
import compiler.common.report.Report;
import compiler.phase.imcgen.IMC;
import compiler.phase.imcgen.IMC.BINOP;
import compiler.phase.imcgen.IMC.CALL;
import compiler.phase.imcgen.IMC.CJUMP;
import compiler.phase.imcgen.IMC.CONST;
import compiler.phase.imcgen.IMC.ESTMT;
import compiler.phase.imcgen.IMC.JUMP;
import compiler.phase.imcgen.IMC.LABEL;
import compiler.phase.imcgen.IMC.MEM1;
import compiler.phase.imcgen.IMC.MEM8;
import compiler.phase.imcgen.IMC.MOVE;
import compiler.phase.imcgen.IMC.NAME;
import compiler.phase.imcgen.IMC.SEXPR;
import compiler.phase.imcgen.IMC.STMTS;
import compiler.phase.imcgen.IMC.TEMP;
import compiler.phase.imcgen.IMC.UNOP;
import compiler.phase.memory.MEM;


// https://msyksphinz-self.github.io/riscv-isadoc/html/rvi.html

public class ASM {

	public static abstract class Line {
    }

    public static class AsmChunk {
		public Vector<Line> lines = new Vector<>();

		public void addLine(Line line) {
			lines.add(line);
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
        public Vector<IMC.TEMP> use = new Vector<>();
        public Vector<IMC.TEMP> def = new Vector<>();
		public IMC.TEMP rd;
		public Instr(String opcode, IMC.TEMP rd) {
			this.opcode = opcode;
			this.rd = rd;
		}
    }

    
	public class SingleRegInstr extends Instr{
		public IMC.TEMP rs1;

        public SingleRegInstr(String opcode, IMC.TEMP rd, IMC.TEMP rs1) {
            super(opcode, rd);
            this.rs1 = rs1;
            this.use.add(rs1);
            this.def.add(rd);
        }
		
		public String toString() {
			return opcode + " " + rd.toString() + ", " + rs1.toString();
		}
	}

	public class DoubleRegInstr extends Instr{
		public IMC.TEMP rs1;
		public IMC.TEMP rs2;

		public DoubleRegInstr(String opcode, IMC.TEMP rd, IMC.TEMP rs1, IMC.TEMP rs2) {
			super(opcode, rd);
			this.rs1 = rs1;
			this.rs2 = rs2;
			this.use.add(rs1);
			this.use.add(rs2);
			this.def.add(rd);
		}


		
		public String toString() {
			return opcode + " " + rd.toString() + ", " + rs1.toString() + ", " + rs2.toString();
		}
	}

	public class RegisterAndValue extends Instr{
		public long value;
		public RegisterAndValue(String opcode, IMC.TEMP rd, long value) {
			super(opcode, rd);
			this.value = value;
			this.def.add(rd);
		}
		
	}
	

	public class RegisterAndOffset extends Instr{
		public IMC.TEMP rs1;
		public long offset;
		public RegisterAndOffset(String opcode, IMC.TEMP rd, IMC.TEMP rs1, long offset) {
			super(opcode, rd);
			this.offset = offset;
			this.rd = rs1;
			this.use.add(rs1);
			this.def.add(rd);
		}
		public String toString() {
			return opcode + " " + rd.toString() + ", [" + rs1.toString()+"]"+offset;
		}
	}



	


    
}
