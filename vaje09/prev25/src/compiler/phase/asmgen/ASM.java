package compiler.phase.asmgen;

import java.util.Vector;

import compiler.common.logger.Loggable;
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

public class ASM {

    public static abstract class Instr implements Loggable {

		public abstract <Result, Arg> Result accept(Visitor<Result, Arg> visitor, Arg accArg);

	}
   
    public static abstract class Chunk {
	}

	/**
	 * A chuck of data.
	 */
	public static class DataChunk extends Chunk {

		/** The label where data is placed at. */
		public final MEM.Label label;

		/** The size of data. */
		public final long size;

		/** The initial value. */
		public final String init;

		public DataChunk(MEM.AbsAccess absAccess) {
			this.label = absAccess.label;
			this.size = absAccess.size;
			this.init = absAccess.init;
		}

	}

	/**
	 * A chuck of code.
	 */
	public static class CodeChunk extends Chunk {

		/** A frame of a function. */
		public final MEM.Frame frame;

		/** The statements of a function body. */
		private final Vector<IMC.Stmt> stmts;

		/**
		 * The function's body entry label, i.e., the label the prologue jumps to.
		 */
		public final MEM.Label entryLabel;

		/**
		 * The function's body exit label, i.e., the label at which the epilogue starts.
		 */
		public final MEM.Label exitLabel;

		/**
		 * Constructs a new code chunk.
		 * 
		 * @param frame      A frame of a function.
		 * @param stmts      The statements of a function body.
		 * @param entryLabel The function's body entry label, i.e., the label the
		 *                   prologue jumps to.
		 * @param exitLabel  The function's body exit label, i.e., the label at which
		 *                   the epilogue starts.
		 */
		public CodeChunk(MEM.Frame frame, Vector<IMC.Stmt> stmts, MEM.Label entryLabel, MEM.Label exitLabel) {
			this.frame = frame;
			this.stmts = new Vector<IMC.Stmt>(stmts);
			this.entryLabel = entryLabel;
			this.exitLabel = exitLabel;
		}

		/**
		 * Returns the statements of a function body.
		 * 
		 * @return The statements of a function body.
		 */
		public Vector<IMC.Stmt> stmts() {
			return new Vector<IMC.Stmt>(stmts);
		}

	}

    /**
	 * An abstract visitor of the intermediate code.
	 * 
	 * @author sliva
	 *
	 * @param <Result> The result the visitor produces.
	 * @param <Arg>    The argument the visitor carries around.
	 */
	public static interface Visitor<Result, Arg> {

		public default Result visit(BINOP binOp, Arg visArg) {
			throw new Report.InternalError();
		}

		public default Result visit(CALL call, Arg visArg) {
			throw new Report.InternalError();
		}

		public default Result visit(CJUMP cjump, Arg visArg) {
			throw new Report.InternalError();
		}

		public default Result visit(CONST constant, Arg visArg) {
			throw new Report.InternalError();
		}

		public default Result visit(ESTMT eStmt, Arg visArg) {
			throw new Report.InternalError();
		}

		public default Result visit(JUMP jump, Arg visArg) {
			throw new Report.InternalError();
		}

		public default Result visit(LABEL label, Arg visArg) {
			throw new Report.InternalError();
		}

		public default Result visit(MEM1 mem, Arg visArg) {
			throw new Report.InternalError();
		}

		public default Result visit(MEM8 mem, Arg visArg) {
			throw new Report.InternalError();
		}

		public default Result visit(MOVE move, Arg visArg) {
			throw new Report.InternalError();
		}

		public default Result visit(NAME name, Arg visArg) {
			throw new Report.InternalError();
		}

		public default Result visit(SEXPR sExpr, Arg visArg) {
			throw new Report.InternalError();
		}

		public default Result visit(STMTS stmts, Arg visArg) {
			throw new Report.InternalError();
		}

		public default Result visit(TEMP temp, Arg visArg) {
			throw new Report.InternalError();
		}

		public default Result visit(UNOP unOp, Arg visArg) {
			throw new Report.InternalError();
		}

	}
}
