package compiler.phase.asmgen;

import java.util.Vector;

import compiler.phase.Phase;
import compiler.phase.abstr.AST;
import compiler.phase.imcgen.IMC;
import compiler.phase.imcgen.ImcGen;
import compiler.phase.imclin.LIN;
import compiler.phase.memory.MEM;
import compiler.phase.memory.Memory;

public class AsmGen extends Phase{

    	/** All data chunks of the program. */
	private final static Vector<LIN.DataChunk> dataChunks = new Vector<LIN.DataChunk>();

	/** All code chinks of the program. */
	private final static Vector<LIN.CodeChunk> codeChunks = new Vector<LIN.CodeChunk>();

    public AsmGen() {
        super("asmgen");
    }
    public void log() {
		Logger linLogger = new Logger(logger);
		for (LIN.DataChunk dataChunk : dataChunks)
			linLogger.log(dataChunk);
		for (LIN.CodeChunk codeChunk : codeChunks)
			linLogger.log(codeChunk);
	}

    public static class Logger implements AST.NullVisitor<Object, String> {

		/** The logger the log should be written to. */
		private final compiler.common.logger.Logger logger;

		/**
		 * Constructs a new visitor with a logger the log should be written to.
		 * 
		 * @param logger The logger the log should be written to.
		 */
		public Logger(compiler.common.logger.Logger logger) {
			this.logger = logger;
		}

		private void log(MEM.Frame frame) {
			if (logger == null)
				return;
			logger.begElement("frame");
			logger.addAttribute("label", frame.label.name);
			logger.addAttribute("depth", Long.toString(frame.depth));
			logger.addAttribute("locssize", Long.toString(frame.locsSize));
			logger.addAttribute("argssize", Long.toString(frame.argsSize));
			logger.addAttribute("size", Long.toString(frame.size));
			logger.addAttribute("FP", frame.FP.toString());
			logger.addAttribute("RV", frame.RV.toString());
			logger.endElement();
		}

		// *** CHUNK LOGGER ***

		public void log(LIN.DataChunk dataChunk) {
			if (logger == null)
				return;
			logger.begElement("datachunk");
			logger.addAttribute("label", dataChunk.label.name);
			logger.addAttribute("size", Long.toString(dataChunk.size));
			logger.addAttribute("init", dataChunk.init);
			logger.endElement();
		}

		public void log(LIN.CodeChunk codeChunk) {
			if (logger == null)
				return;
			logger.begElement("codechunk");
			logger.addAttribute("prologuelabel", codeChunk.frame.label.name);
			logger.addAttribute("bodylabel", codeChunk.entryLabel.name);
			logger.addAttribute("epiloguelabel", codeChunk.exitLabel.name);
			log(codeChunk.frame);
			for (IMC.Stmt stmt : codeChunk.stmts()) {
				//System.out.println(stmt.toString());
				logger.begElement("stmt");
				stmt.log(logger);
				logger.endElement();
			}
			logger.endElement();
		}

	}
}
