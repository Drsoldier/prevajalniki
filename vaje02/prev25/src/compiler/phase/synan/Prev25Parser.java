// Generated from Prev25Parser.g4 by ANTLR 4.13.2


	package compiler.phase.synan;
	
	import java.util.*;
	import compiler.common.report.*;
	import compiler.phase.lexan.*;


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"doclint:missing", "all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class Prev25Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WHITESPACE=1, AND=2, OR=3, EQU=4, NEQ=5, LT=6, GT=7, LEQ=8, GEQ=9, MUL=10, 
		DIV=11, MOD=12, PLUS=13, MINUS=14, EXCL=15, DOT=16, POW=17, ASSIGN=18, 
		COLON=19, COMMA=20, LBRACE=21, RBRACE=22, LPARAN=23, RPARAN=24, LBRCKT=25, 
		RBRCKT=26, INT=27, BOOL=28, CHAR=29, DO=30, ELSE=31, END=32, FALSE=33, 
		FUN=34, IF=35, IN=36, LET=37, NULL=38, RETURN=39, SIZEOF=40, THEN=41, 
		TRUE=42, TYP=43, VAR=44, VOID=45, WHILE=46, IDENTIFIER=47, CONSTNUM=48, 
		COMMENT=49, CONSTCHAR=50, CONSTSTR=51, ERROR=52;
	public static final int
		RULE_source = 0, RULE_defs = 1, RULE_def = 2, RULE_args_ = 3, RULE_syn1 = 4, 
		RULE_neki = 5, RULE_stmt_ = 6, RULE_stmt = 7, RULE_whileStmt = 8, RULE_ifStmt = 9, 
		RULE_ifElseStmt = 10, RULE_letStmt = 11, RULE_stmtBase = 12, RULE_exprEntry = 13, 
		RULE_expr2nd = 14, RULE_expr3rd = 15, RULE_comprOp = 16, RULE_expr4th = 17, 
		RULE_additiveExpr = 18, RULE_expr5th = 19, RULE_mulOps = 20, RULE_expr6th = 21, 
		RULE_prefixOps = 22, RULE_exprs_ = 23, RULE_expr7th = 24, RULE_oper = 25, 
		RULE_prim = 26, RULE_exprPrio = 27, RULE_functionCall = 28, RULE_expr = 29, 
		RULE_exprEnd = 30, RULE_type = 31, RULE_syn21 = 32, RULE_typeOver = 33, 
		RULE_type2 = 34;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "defs", "def", "args_", "syn1", "neki", "stmt_", "stmt", "whileStmt", 
			"ifStmt", "ifElseStmt", "letStmt", "stmtBase", "exprEntry", "expr2nd", 
			"expr3rd", "comprOp", "expr4th", "additiveExpr", "expr5th", "mulOps", 
			"expr6th", "prefixOps", "exprs_", "expr7th", "oper", "prim", "exprPrio", 
			"functionCall", "expr", "exprEnd", "type", "syn21", "typeOver", "type2"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'&'", "'|'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", 
			"'*'", "'/'", "'%'", "'+'", "'-'", "'!'", "'.'", "'^'", "'='", "':'", 
			"','", "'{'", "'}'", "'('", "')'", "'['", "']'", "'int'", "'bool'", "'char'", 
			"'do'", "'else'", "'end'", "'false'", "'fun'", "'if'", "'in'", "'let'", 
			"'null'", "'return'", "'sizeof'", "'then'", "'true'", "'typ'", "'var'", 
			"'void'", "'while'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WHITESPACE", "AND", "OR", "EQU", "NEQ", "LT", "GT", "LEQ", "GEQ", 
			"MUL", "DIV", "MOD", "PLUS", "MINUS", "EXCL", "DOT", "POW", "ASSIGN", 
			"COLON", "COMMA", "LBRACE", "RBRACE", "LPARAN", "RPARAN", "LBRCKT", "RBRCKT", 
			"INT", "BOOL", "CHAR", "DO", "ELSE", "END", "FALSE", "FUN", "IF", "IN", 
			"LET", "NULL", "RETURN", "SIZEOF", "THEN", "TRUE", "TYP", "VAR", "VOID", 
			"WHILE", "IDENTIFIER", "CONSTNUM", "COMMENT", "CONSTCHAR", "CONSTSTR", 
			"ERROR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Prev25Parser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



		private Location loc(Token tok) { return new Location((LexAn.LocLogToken)tok); }
		private Location loc(Token     tok1, Token     tok2) { return new Location((LexAn.LocLogToken)tok1, (LexAn.LocLogToken)tok2); }
		private Location loc(Token     tok1, Locatable loc2) { return new Location((LexAn.LocLogToken)tok1, loc2); }
		private Location loc(Locatable loc1, Token     tok2) { return new Location(loc1, (LexAn.LocLogToken)tok2); }
		private Location loc(Locatable loc1, Locatable loc2) { return new Location(loc1, loc2); }


	public Prev25Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SourceContext extends ParserRuleContext {
		public DefsContext defs() {
			return getRuleContext(DefsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(Prev25Parser.EOF, 0); }
		public SourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_source; }
	}

	public final SourceContext source() throws RecognitionException {
		SourceContext _localctx = new SourceContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_source);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			defs();
			setState(71);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefsContext extends ParserRuleContext {
		public DefContext def() {
			return getRuleContext(DefContext.class,0);
		}
		public DefsContext defs() {
			return getRuleContext(DefsContext.class,0);
		}
		public DefsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defs; }
	}

	public final DefsContext defs() throws RecognitionException {
		DefsContext _localctx = new DefsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_defs);
		try {
			setState(77);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(73);
				def();
				setState(74);
				defs();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(76);
				def();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefContext extends ParserRuleContext {
		public TerminalNode TYP() { return getToken(Prev25Parser.TYP, 0); }
		public TerminalNode IDENTIFIER() { return getToken(Prev25Parser.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(Prev25Parser.ASSIGN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode VAR() { return getToken(Prev25Parser.VAR, 0); }
		public TerminalNode COLON() { return getToken(Prev25Parser.COLON, 0); }
		public TerminalNode FUN() { return getToken(Prev25Parser.FUN, 0); }
		public TerminalNode LPARAN() { return getToken(Prev25Parser.LPARAN, 0); }
		public Args_Context args_() {
			return getRuleContext(Args_Context.class,0);
		}
		public TerminalNode RPARAN() { return getToken(Prev25Parser.RPARAN, 0); }
		public Syn1Context syn1() {
			return getRuleContext(Syn1Context.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public DefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def; }
	}

	public final DefContext def() throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_def);
		try {
			setState(95);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYP:
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				match(TYP);
				setState(80);
				match(IDENTIFIER);
				setState(81);
				match(ASSIGN);
				setState(82);
				type();
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				match(VAR);
				setState(84);
				match(IDENTIFIER);
				setState(85);
				match(COLON);
				setState(86);
				type();
				}
				break;
			case FUN:
				enterOuterAlt(_localctx, 3);
				{
				setState(87);
				match(FUN);
				setState(88);
				match(IDENTIFIER);
				setState(89);
				match(LPARAN);
				setState(90);
				args_();
				setState(91);
				match(RPARAN);
				setState(92);
				syn1();
				}
				break;
			case PLUS:
			case MINUS:
			case EXCL:
			case POW:
				enterOuterAlt(_localctx, 4);
				{
				setState(94);
				expr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Args_Context extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(Prev25Parser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(Prev25Parser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public Args_Context args_() {
			return getRuleContext(Args_Context.class,0);
		}
		public Args_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args_; }
	}

	public final Args_Context args_() throws RecognitionException {
		Args_Context _localctx = new Args_Context(_ctx, getState());
		enterRule(_localctx, 6, RULE_args_);
		try {
			setState(107);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				match(IDENTIFIER);
				setState(98);
				match(COLON);
				setState(99);
				type();
				setState(100);
				match(COMMA);
				setState(101);
				args_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				match(IDENTIFIER);
				setState(104);
				match(COLON);
				setState(105);
				type();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Syn1Context extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(Prev25Parser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public NekiContext neki() {
			return getRuleContext(NekiContext.class,0);
		}
		public Syn1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_syn1; }
	}

	public final Syn1Context syn1() throws RecognitionException {
		Syn1Context _localctx = new Syn1Context(_ctx, getState());
		enterRule(_localctx, 8, RULE_syn1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(COLON);
			setState(110);
			type();
			setState(111);
			neki();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NekiContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(Prev25Parser.ASSIGN, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Prev25Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Prev25Parser.COMMA, i);
		}
		public NekiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_neki; }
	}

	public final NekiContext neki() throws RecognitionException {
		NekiContext _localctx = new NekiContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_neki);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(ASSIGN);
			setState(114);
			stmt();
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(115);
				match(COMMA);
				setState(116);
				stmt();
				}
				}
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Stmt_Context extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Prev25Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Prev25Parser.COMMA, i);
		}
		public Stmt_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt_; }
	}

	public final Stmt_Context stmt_() throws RecognitionException {
		Stmt_Context _localctx = new Stmt_Context(_ctx, getState());
		enterRule(_localctx, 12, RULE_stmt_);
		int _la;
		try {
			setState(131);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case EXCL:
			case POW:
			case IF:
			case LET:
			case RETURN:
			case WHILE:
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				stmt();
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(123);
					match(COMMA);
					setState(124);
					stmt();
					}
					}
					setState(129);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case ELSE:
			case END:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public WhileStmtContext whileStmt() {
			return getRuleContext(WhileStmtContext.class,0);
		}
		public IfStmtContext ifStmt() {
			return getRuleContext(IfStmtContext.class,0);
		}
		public LetStmtContext letStmt() {
			return getRuleContext(LetStmtContext.class,0);
		}
		public StmtBaseContext stmtBase() {
			return getRuleContext(StmtBaseContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_stmt);
		try {
			setState(137);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WHILE:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				whileStmt();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(134);
				ifStmt();
				}
				break;
			case LET:
				enterOuterAlt(_localctx, 3);
				{
				setState(135);
				letStmt();
				}
				break;
			case PLUS:
			case MINUS:
			case EXCL:
			case POW:
			case RETURN:
				enterOuterAlt(_localctx, 4);
				{
				setState(136);
				stmtBase();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileStmtContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(Prev25Parser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Stmt_Context stmt_() {
			return getRuleContext(Stmt_Context.class,0);
		}
		public TerminalNode END() { return getToken(Prev25Parser.END, 0); }
		public WhileStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStmt; }
	}

	public final WhileStmtContext whileStmt() throws RecognitionException {
		WhileStmtContext _localctx = new WhileStmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_whileStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(WHILE);
			setState(140);
			expr();
			setState(141);
			stmt_();
			setState(142);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfStmtContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(Prev25Parser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode THEN() { return getToken(Prev25Parser.THEN, 0); }
		public Stmt_Context stmt_() {
			return getRuleContext(Stmt_Context.class,0);
		}
		public IfElseStmtContext ifElseStmt() {
			return getRuleContext(IfElseStmtContext.class,0);
		}
		public IfStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStmt; }
	}

	public final IfStmtContext ifStmt() throws RecognitionException {
		IfStmtContext _localctx = new IfStmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ifStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(IF);
			setState(145);
			expr();
			setState(146);
			match(THEN);
			setState(147);
			stmt_();
			setState(148);
			ifElseStmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfElseStmtContext extends ParserRuleContext {
		public TerminalNode END() { return getToken(Prev25Parser.END, 0); }
		public TerminalNode ELSE() { return getToken(Prev25Parser.ELSE, 0); }
		public Stmt_Context stmt_() {
			return getRuleContext(Stmt_Context.class,0);
		}
		public IfElseStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifElseStmt; }
	}

	public final IfElseStmtContext ifElseStmt() throws RecognitionException {
		IfElseStmtContext _localctx = new IfElseStmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_ifElseStmt);
		try {
			setState(155);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case END:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				match(END);
				}
				break;
			case ELSE:
				enterOuterAlt(_localctx, 2);
				{
				setState(151);
				match(ELSE);
				setState(152);
				stmt_();
				setState(153);
				match(END);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LetStmtContext extends ParserRuleContext {
		public TerminalNode LET() { return getToken(Prev25Parser.LET, 0); }
		public DefsContext defs() {
			return getRuleContext(DefsContext.class,0);
		}
		public TerminalNode IN() { return getToken(Prev25Parser.IN, 0); }
		public Stmt_Context stmt_() {
			return getRuleContext(Stmt_Context.class,0);
		}
		public TerminalNode END() { return getToken(Prev25Parser.END, 0); }
		public LetStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letStmt; }
	}

	public final LetStmtContext letStmt() throws RecognitionException {
		LetStmtContext _localctx = new LetStmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_letStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(LET);
			setState(158);
			defs();
			setState(159);
			match(IN);
			setState(160);
			stmt_();
			setState(161);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtBaseContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ASSIGN() { return getToken(Prev25Parser.ASSIGN, 0); }
		public TerminalNode RETURN() { return getToken(Prev25Parser.RETURN, 0); }
		public StmtBaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmtBase; }
	}

	public final StmtBaseContext stmtBase() throws RecognitionException {
		StmtBaseContext _localctx = new StmtBaseContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_stmtBase);
		try {
			setState(170);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(163);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(164);
				expr();
				setState(165);
				match(ASSIGN);
				setState(166);
				expr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(168);
				match(RETURN);
				setState(169);
				expr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprEntryContext extends ParserRuleContext {
		public List<Expr2ndContext> expr2nd() {
			return getRuleContexts(Expr2ndContext.class);
		}
		public Expr2ndContext expr2nd(int i) {
			return getRuleContext(Expr2ndContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(Prev25Parser.OR); }
		public TerminalNode OR(int i) {
			return getToken(Prev25Parser.OR, i);
		}
		public ExprEntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprEntry; }
	}

	public final ExprEntryContext exprEntry() throws RecognitionException {
		ExprEntryContext _localctx = new ExprEntryContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_exprEntry);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			expr2nd();
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(173);
				match(OR);
				setState(174);
				expr2nd();
				}
				}
				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr2ndContext extends ParserRuleContext {
		public List<Expr3rdContext> expr3rd() {
			return getRuleContexts(Expr3rdContext.class);
		}
		public Expr3rdContext expr3rd(int i) {
			return getRuleContext(Expr3rdContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(Prev25Parser.AND); }
		public TerminalNode AND(int i) {
			return getToken(Prev25Parser.AND, i);
		}
		public Expr2ndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr2nd; }
	}

	public final Expr2ndContext expr2nd() throws RecognitionException {
		Expr2ndContext _localctx = new Expr2ndContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_expr2nd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			expr3rd();
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(181);
				match(AND);
				setState(182);
				expr3rd();
				}
				}
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr3rdContext extends ParserRuleContext {
		public List<Expr4thContext> expr4th() {
			return getRuleContexts(Expr4thContext.class);
		}
		public Expr4thContext expr4th(int i) {
			return getRuleContext(Expr4thContext.class,i);
		}
		public List<ComprOpContext> comprOp() {
			return getRuleContexts(ComprOpContext.class);
		}
		public ComprOpContext comprOp(int i) {
			return getRuleContext(ComprOpContext.class,i);
		}
		public Expr3rdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr3rd; }
	}

	public final Expr3rdContext expr3rd() throws RecognitionException {
		Expr3rdContext _localctx = new Expr3rdContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_expr3rd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			expr4th();
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1008L) != 0)) {
				{
				{
				setState(189);
				comprOp();
				setState(190);
				expr4th();
				}
				}
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComprOpContext extends ParserRuleContext {
		public TerminalNode EQU() { return getToken(Prev25Parser.EQU, 0); }
		public TerminalNode NEQ() { return getToken(Prev25Parser.NEQ, 0); }
		public TerminalNode LT() { return getToken(Prev25Parser.LT, 0); }
		public TerminalNode GT() { return getToken(Prev25Parser.GT, 0); }
		public TerminalNode LEQ() { return getToken(Prev25Parser.LEQ, 0); }
		public TerminalNode GEQ() { return getToken(Prev25Parser.GEQ, 0); }
		public ComprOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comprOp; }
	}

	public final ComprOpContext comprOp() throws RecognitionException {
		ComprOpContext _localctx = new ComprOpContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_comprOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1008L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr4thContext extends ParserRuleContext {
		public List<Expr5thContext> expr5th() {
			return getRuleContexts(Expr5thContext.class);
		}
		public Expr5thContext expr5th(int i) {
			return getRuleContext(Expr5thContext.class,i);
		}
		public List<AdditiveExprContext> additiveExpr() {
			return getRuleContexts(AdditiveExprContext.class);
		}
		public AdditiveExprContext additiveExpr(int i) {
			return getRuleContext(AdditiveExprContext.class,i);
		}
		public Expr4thContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr4th; }
	}

	public final Expr4thContext expr4th() throws RecognitionException {
		Expr4thContext _localctx = new Expr4thContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_expr4th);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			expr5th();
			setState(205);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(200);
					additiveExpr();
					setState(201);
					expr5th();
					}
					} 
				}
				setState(207);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AdditiveExprContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(Prev25Parser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(Prev25Parser.MINUS, 0); }
		public AdditiveExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpr; }
	}

	public final AdditiveExprContext additiveExpr() throws RecognitionException {
		AdditiveExprContext _localctx = new AdditiveExprContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_additiveExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			_la = _input.LA(1);
			if ( !(_la==PLUS || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr5thContext extends ParserRuleContext {
		public List<Expr6thContext> expr6th() {
			return getRuleContexts(Expr6thContext.class);
		}
		public Expr6thContext expr6th(int i) {
			return getRuleContext(Expr6thContext.class,i);
		}
		public List<MulOpsContext> mulOps() {
			return getRuleContexts(MulOpsContext.class);
		}
		public MulOpsContext mulOps(int i) {
			return getRuleContext(MulOpsContext.class,i);
		}
		public Expr5thContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr5th; }
	}

	public final Expr5thContext expr5th() throws RecognitionException {
		Expr5thContext _localctx = new Expr5thContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_expr5th);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			expr6th();
			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7168L) != 0)) {
				{
				{
				setState(211);
				mulOps();
				setState(212);
				expr6th();
				}
				}
				setState(218);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MulOpsContext extends ParserRuleContext {
		public TerminalNode MUL() { return getToken(Prev25Parser.MUL, 0); }
		public TerminalNode DIV() { return getToken(Prev25Parser.DIV, 0); }
		public TerminalNode MOD() { return getToken(Prev25Parser.MOD, 0); }
		public MulOpsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulOps; }
	}

	public final MulOpsContext mulOps() throws RecognitionException {
		MulOpsContext _localctx = new MulOpsContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_mulOps);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 7168L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr6thContext extends ParserRuleContext {
		public Expr7thContext expr7th() {
			return getRuleContext(Expr7thContext.class,0);
		}
		public PrefixOpsContext prefixOps() {
			return getRuleContext(PrefixOpsContext.class,0);
		}
		public Expr6thContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr6th; }
	}

	public final Expr6thContext expr6th() throws RecognitionException {
		Expr6thContext _localctx = new Expr6thContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_expr6th);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(221);
			prefixOps();
			}
			setState(222);
			expr7th();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrefixOpsContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(Prev25Parser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(Prev25Parser.MINUS, 0); }
		public TerminalNode EXCL() { return getToken(Prev25Parser.EXCL, 0); }
		public TerminalNode POW() { return getToken(Prev25Parser.POW, 0); }
		public PrefixOpsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixOps; }
	}

	public final PrefixOpsContext prefixOps() throws RecognitionException {
		PrefixOpsContext _localctx = new PrefixOpsContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_prefixOps);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 188416L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Exprs_Context extends ParserRuleContext {
		public List<ExprEntryContext> exprEntry() {
			return getRuleContexts(ExprEntryContext.class);
		}
		public ExprEntryContext exprEntry(int i) {
			return getRuleContext(ExprEntryContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Prev25Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Prev25Parser.COMMA, i);
		}
		public Exprs_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprs_; }
	}

	public final Exprs_Context exprs_() throws RecognitionException {
		Exprs_Context _localctx = new Exprs_Context(_ctx, getState());
		enterRule(_localctx, 46, RULE_exprs_);
		try {
			int _alt;
			setState(236);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case EXCL:
			case POW:
				enterOuterAlt(_localctx, 1);
				{
				setState(231);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(226);
						exprEntry();
						setState(227);
						match(COMMA);
						}
						} 
					}
					setState(233);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				}
				setState(234);
				exprEntry();
				}
				break;
			case RPARAN:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr7thContext extends ParserRuleContext {
		public PrimContext prim() {
			return getRuleContext(PrimContext.class,0);
		}
		public List<OperContext> oper() {
			return getRuleContexts(OperContext.class);
		}
		public OperContext oper(int i) {
			return getRuleContext(OperContext.class,i);
		}
		public Expr7thContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr7th; }
	}

	public final Expr7thContext expr7th() throws RecognitionException {
		Expr7thContext _localctx = new Expr7thContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_expr7th);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			prim();
			setState(242);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(239);
					oper();
					}
					} 
				}
				setState(244);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OperContext extends ParserRuleContext {
		public TerminalNode POW() { return getToken(Prev25Parser.POW, 0); }
		public TerminalNode DOT() { return getToken(Prev25Parser.DOT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(Prev25Parser.IDENTIFIER, 0); }
		public TerminalNode LPARAN() { return getToken(Prev25Parser.LPARAN, 0); }
		public Exprs_Context exprs_() {
			return getRuleContext(Exprs_Context.class,0);
		}
		public TerminalNode RPARAN() { return getToken(Prev25Parser.RPARAN, 0); }
		public OperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_oper; }
	}

	public final OperContext oper() throws RecognitionException {
		OperContext _localctx = new OperContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_oper);
		try {
			setState(252);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case POW:
				enterOuterAlt(_localctx, 1);
				{
				setState(245);
				match(POW);
				}
				break;
			case DOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(246);
				match(DOT);
				setState(247);
				match(IDENTIFIER);
				}
				break;
			case LPARAN:
				enterOuterAlt(_localctx, 3);
				{
				setState(248);
				match(LPARAN);
				setState(249);
				exprs_();
				setState(250);
				match(RPARAN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimContext extends ParserRuleContext {
		public TerminalNode LPARAN() { return getToken(Prev25Parser.LPARAN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPARAN() { return getToken(Prev25Parser.RPARAN, 0); }
		public TerminalNode LBRACE() { return getToken(Prev25Parser.LBRACE, 0); }
		public TerminalNode COLON() { return getToken(Prev25Parser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(Prev25Parser.RBRACE, 0); }
		public ExprEndContext exprEnd() {
			return getRuleContext(ExprEndContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(Prev25Parser.IDENTIFIER, 0); }
		public PrimContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prim; }
	}

	public final PrimContext prim() throws RecognitionException {
		PrimContext _localctx = new PrimContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_prim);
		try {
			setState(266);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPARAN:
				enterOuterAlt(_localctx, 1);
				{
				setState(254);
				match(LPARAN);
				setState(255);
				expr();
				setState(256);
				match(RPARAN);
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(258);
				match(LBRACE);
				setState(259);
				expr();
				setState(260);
				match(COLON);
				setState(261);
				type();
				setState(262);
				match(RBRACE);
				}
				break;
			case BOOL:
			case FALSE:
			case NULL:
			case SIZEOF:
			case TRUE:
			case CONSTNUM:
			case CONSTCHAR:
			case CONSTSTR:
				enterOuterAlt(_localctx, 3);
				{
				setState(264);
				exprEnd();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 4);
				{
				setState(265);
				match(IDENTIFIER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprPrioContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LBRCKT() { return getToken(Prev25Parser.LBRCKT, 0); }
		public TerminalNode RBRCKT() { return getToken(Prev25Parser.RBRCKT, 0); }
		public TerminalNode LPARAN() { return getToken(Prev25Parser.LPARAN, 0); }
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public ExprPrioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprPrio; }
	}

	public final ExprPrioContext exprPrio() throws RecognitionException {
		ExprPrioContext _localctx = new ExprPrioContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_exprPrio);
		try {
			setState(277);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(268);
				expr();
				setState(269);
				match(LBRCKT);
				setState(270);
				expr();
				setState(271);
				match(RBRCKT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(273);
				expr();
				setState(274);
				match(LPARAN);
				setState(275);
				functionCall();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public TerminalNode RPARAN() { return getToken(Prev25Parser.RPARAN, 0); }
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_functionCall);
		try {
			setState(284);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case EXCL:
			case POW:
				enterOuterAlt(_localctx, 1);
				{
				setState(279);
				expr();
				setState(280);
				match(COMMA);
				setState(281);
				functionCall();
				}
				break;
			case RPARAN:
				enterOuterAlt(_localctx, 2);
				{
				setState(283);
				match(RPARAN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprEntryContext exprEntry() {
			return getRuleContext(ExprEntryContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			exprEntry();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprEndContext extends ParserRuleContext {
		public TerminalNode CONSTCHAR() { return getToken(Prev25Parser.CONSTCHAR, 0); }
		public TerminalNode CONSTNUM() { return getToken(Prev25Parser.CONSTNUM, 0); }
		public TerminalNode CONSTSTR() { return getToken(Prev25Parser.CONSTSTR, 0); }
		public TerminalNode BOOL() { return getToken(Prev25Parser.BOOL, 0); }
		public TerminalNode FALSE() { return getToken(Prev25Parser.FALSE, 0); }
		public TerminalNode TRUE() { return getToken(Prev25Parser.TRUE, 0); }
		public TerminalNode NULL() { return getToken(Prev25Parser.NULL, 0); }
		public TerminalNode SIZEOF() { return getToken(Prev25Parser.SIZEOF, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExprEndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprEnd; }
	}

	public final ExprEndContext exprEnd() throws RecognitionException {
		ExprEndContext _localctx = new ExprEndContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_exprEnd);
		try {
			setState(297);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONSTCHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(288);
				match(CONSTCHAR);
				}
				break;
			case CONSTNUM:
				enterOuterAlt(_localctx, 2);
				{
				setState(289);
				match(CONSTNUM);
				}
				break;
			case CONSTSTR:
				enterOuterAlt(_localctx, 3);
				{
				setState(290);
				match(CONSTSTR);
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 4);
				{
				setState(291);
				match(BOOL);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 5);
				{
				setState(292);
				match(FALSE);
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 6);
				{
				setState(293);
				match(TRUE);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 7);
				{
				setState(294);
				match(NULL);
				}
				break;
			case SIZEOF:
				enterOuterAlt(_localctx, 8);
				{
				setState(295);
				match(SIZEOF);
				setState(296);
				type();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public Syn21Context syn21() {
			return getRuleContext(Syn21Context.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			syn21();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Syn21Context extends ParserRuleContext {
		public TerminalNode LT() { return getToken(Prev25Parser.LT, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(Prev25Parser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(Prev25Parser.IDENTIFIER, i);
		}
		public List<TerminalNode> COLON() { return getTokens(Prev25Parser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(Prev25Parser.COLON, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode GT() { return getToken(Prev25Parser.GT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(Prev25Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Prev25Parser.COMMA, i);
		}
		public TerminalNode LBRACE() { return getToken(Prev25Parser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(Prev25Parser.RBRACE, 0); }
		public TypeOverContext typeOver() {
			return getRuleContext(TypeOverContext.class,0);
		}
		public Syn21Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_syn21; }
	}

	public final Syn21Context syn21() throws RecognitionException {
		Syn21Context _localctx = new Syn21Context(_ctx, getState());
		enterRule(_localctx, 64, RULE_syn21);
		int _la;
		try {
			setState(332);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LT:
				enterOuterAlt(_localctx, 1);
				{
				setState(301);
				match(LT);
				setState(302);
				match(IDENTIFIER);
				setState(303);
				match(COLON);
				setState(304);
				type();
				setState(311);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(305);
					match(COMMA);
					setState(306);
					match(IDENTIFIER);
					setState(307);
					match(COLON);
					setState(308);
					type();
					}
					}
					setState(313);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(314);
				match(GT);
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(316);
				match(LBRACE);
				setState(317);
				match(IDENTIFIER);
				setState(318);
				match(COLON);
				setState(319);
				type();
				setState(326);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(320);
					match(COMMA);
					setState(321);
					match(IDENTIFIER);
					setState(322);
					match(COLON);
					setState(323);
					type();
					}
					}
					setState(328);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(329);
				match(RBRACE);
				}
				break;
			case POW:
			case LBRCKT:
			case INT:
			case BOOL:
			case CHAR:
			case VOID:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 3);
				{
				setState(331);
				typeOver();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeOverContext extends ParserRuleContext {
		public TerminalNode LBRCKT() { return getToken(Prev25Parser.LBRCKT, 0); }
		public TerminalNode CONSTNUM() { return getToken(Prev25Parser.CONSTNUM, 0); }
		public TerminalNode RBRCKT() { return getToken(Prev25Parser.RBRCKT, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode POW() { return getToken(Prev25Parser.POW, 0); }
		public Type2Context type2() {
			return getRuleContext(Type2Context.class,0);
		}
		public TypeOverContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeOver; }
	}

	public final TypeOverContext typeOver() throws RecognitionException {
		TypeOverContext _localctx = new TypeOverContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_typeOver);
		try {
			setState(341);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRCKT:
				enterOuterAlt(_localctx, 1);
				{
				setState(334);
				match(LBRCKT);
				setState(335);
				match(CONSTNUM);
				setState(336);
				match(RBRCKT);
				setState(337);
				type();
				}
				break;
			case POW:
				enterOuterAlt(_localctx, 2);
				{
				setState(338);
				match(POW);
				setState(339);
				type();
				}
				break;
			case INT:
			case BOOL:
			case CHAR:
			case VOID:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 3);
				{
				setState(340);
				type2();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Type2Context extends ParserRuleContext {
		public TerminalNode BOOL() { return getToken(Prev25Parser.BOOL, 0); }
		public TerminalNode INT() { return getToken(Prev25Parser.INT, 0); }
		public TerminalNode CHAR() { return getToken(Prev25Parser.CHAR, 0); }
		public TerminalNode VOID() { return getToken(Prev25Parser.VOID, 0); }
		public TerminalNode IDENTIFIER() { return getToken(Prev25Parser.IDENTIFIER, 0); }
		public Type2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type2; }
	}

	public final Type2Context type2() throws RecognitionException {
		Type2Context _localctx = new Type2Context(_ctx, getState());
		enterRule(_localctx, 68, RULE_type2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(343);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 175922799968256L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u00014\u015a\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0003\u0001N\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0003\u0002`\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0003\u0003l\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005v\b"+
		"\u0005\n\u0005\f\u0005y\t\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0005"+
		"\u0006~\b\u0006\n\u0006\f\u0006\u0081\t\u0006\u0001\u0006\u0003\u0006"+
		"\u0084\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007"+
		"\u008a\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0003\n\u009c\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0003\f\u00ab\b\f\u0001\r\u0001\r\u0001\r\u0005\r\u00b0\b\r\n\r\f\r"+
		"\u00b3\t\r\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u00b8\b\u000e"+
		"\n\u000e\f\u000e\u00bb\t\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0005\u000f\u00c1\b\u000f\n\u000f\f\u000f\u00c4\t\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011"+
		"\u00cc\b\u0011\n\u0011\f\u0011\u00cf\t\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u00d7\b\u0013\n"+
		"\u0013\f\u0013\u00da\t\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0005\u0017\u00e6\b\u0017\n\u0017\f\u0017\u00e9\t\u0017\u0001\u0017"+
		"\u0001\u0017\u0003\u0017\u00ed\b\u0017\u0001\u0018\u0001\u0018\u0005\u0018"+
		"\u00f1\b\u0018\n\u0018\f\u0018\u00f4\t\u0018\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u00fd"+
		"\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0003\u001a\u010b\b\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0003"+
		"\u001b\u0116\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0003\u001c\u011d\b\u001c\u0001\u001d\u0001\u001d\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0003\u001e\u012a\b\u001e\u0001\u001f\u0001\u001f\u0001"+
		" \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0005 \u0136\b \n "+
		"\f \u0139\t \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001"+
		" \u0001 \u0005 \u0145\b \n \f \u0148\t \u0001 \u0001 \u0001 \u0003 \u014d"+
		"\b \u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0003!\u0156\b!\u0001"+
		"\"\u0001\"\u0001\"\u0000\u0000#\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BD\u0000\u0005"+
		"\u0001\u0000\u0004\t\u0001\u0000\r\u000e\u0001\u0000\n\f\u0002\u0000\r"+
		"\u000f\u0011\u0011\u0003\u0000\u001b\u001d--//\u0161\u0000F\u0001\u0000"+
		"\u0000\u0000\u0002M\u0001\u0000\u0000\u0000\u0004_\u0001\u0000\u0000\u0000"+
		"\u0006k\u0001\u0000\u0000\u0000\bm\u0001\u0000\u0000\u0000\nq\u0001\u0000"+
		"\u0000\u0000\f\u0083\u0001\u0000\u0000\u0000\u000e\u0089\u0001\u0000\u0000"+
		"\u0000\u0010\u008b\u0001\u0000\u0000\u0000\u0012\u0090\u0001\u0000\u0000"+
		"\u0000\u0014\u009b\u0001\u0000\u0000\u0000\u0016\u009d\u0001\u0000\u0000"+
		"\u0000\u0018\u00aa\u0001\u0000\u0000\u0000\u001a\u00ac\u0001\u0000\u0000"+
		"\u0000\u001c\u00b4\u0001\u0000\u0000\u0000\u001e\u00bc\u0001\u0000\u0000"+
		"\u0000 \u00c5\u0001\u0000\u0000\u0000\"\u00c7\u0001\u0000\u0000\u0000"+
		"$\u00d0\u0001\u0000\u0000\u0000&\u00d2\u0001\u0000\u0000\u0000(\u00db"+
		"\u0001\u0000\u0000\u0000*\u00dd\u0001\u0000\u0000\u0000,\u00e0\u0001\u0000"+
		"\u0000\u0000.\u00ec\u0001\u0000\u0000\u00000\u00ee\u0001\u0000\u0000\u0000"+
		"2\u00fc\u0001\u0000\u0000\u00004\u010a\u0001\u0000\u0000\u00006\u0115"+
		"\u0001\u0000\u0000\u00008\u011c\u0001\u0000\u0000\u0000:\u011e\u0001\u0000"+
		"\u0000\u0000<\u0129\u0001\u0000\u0000\u0000>\u012b\u0001\u0000\u0000\u0000"+
		"@\u014c\u0001\u0000\u0000\u0000B\u0155\u0001\u0000\u0000\u0000D\u0157"+
		"\u0001\u0000\u0000\u0000FG\u0003\u0002\u0001\u0000GH\u0005\u0000\u0000"+
		"\u0001H\u0001\u0001\u0000\u0000\u0000IJ\u0003\u0004\u0002\u0000JK\u0003"+
		"\u0002\u0001\u0000KN\u0001\u0000\u0000\u0000LN\u0003\u0004\u0002\u0000"+
		"MI\u0001\u0000\u0000\u0000ML\u0001\u0000\u0000\u0000N\u0003\u0001\u0000"+
		"\u0000\u0000OP\u0005+\u0000\u0000PQ\u0005/\u0000\u0000QR\u0005\u0012\u0000"+
		"\u0000R`\u0003>\u001f\u0000ST\u0005,\u0000\u0000TU\u0005/\u0000\u0000"+
		"UV\u0005\u0013\u0000\u0000V`\u0003>\u001f\u0000WX\u0005\"\u0000\u0000"+
		"XY\u0005/\u0000\u0000YZ\u0005\u0017\u0000\u0000Z[\u0003\u0006\u0003\u0000"+
		"[\\\u0005\u0018\u0000\u0000\\]\u0003\b\u0004\u0000]`\u0001\u0000\u0000"+
		"\u0000^`\u0003:\u001d\u0000_O\u0001\u0000\u0000\u0000_S\u0001\u0000\u0000"+
		"\u0000_W\u0001\u0000\u0000\u0000_^\u0001\u0000\u0000\u0000`\u0005\u0001"+
		"\u0000\u0000\u0000ab\u0005/\u0000\u0000bc\u0005\u0013\u0000\u0000cd\u0003"+
		">\u001f\u0000de\u0005\u0014\u0000\u0000ef\u0003\u0006\u0003\u0000fl\u0001"+
		"\u0000\u0000\u0000gh\u0005/\u0000\u0000hi\u0005\u0013\u0000\u0000il\u0003"+
		">\u001f\u0000jl\u0001\u0000\u0000\u0000ka\u0001\u0000\u0000\u0000kg\u0001"+
		"\u0000\u0000\u0000kj\u0001\u0000\u0000\u0000l\u0007\u0001\u0000\u0000"+
		"\u0000mn\u0005\u0013\u0000\u0000no\u0003>\u001f\u0000op\u0003\n\u0005"+
		"\u0000p\t\u0001\u0000\u0000\u0000qr\u0005\u0012\u0000\u0000rw\u0003\u000e"+
		"\u0007\u0000st\u0005\u0014\u0000\u0000tv\u0003\u000e\u0007\u0000us\u0001"+
		"\u0000\u0000\u0000vy\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000"+
		"wx\u0001\u0000\u0000\u0000x\u000b\u0001\u0000\u0000\u0000yw\u0001\u0000"+
		"\u0000\u0000z\u007f\u0003\u000e\u0007\u0000{|\u0005\u0014\u0000\u0000"+
		"|~\u0003\u000e\u0007\u0000}{\u0001\u0000\u0000\u0000~\u0081\u0001\u0000"+
		"\u0000\u0000\u007f}\u0001\u0000\u0000\u0000\u007f\u0080\u0001\u0000\u0000"+
		"\u0000\u0080\u0084\u0001\u0000\u0000\u0000\u0081\u007f\u0001\u0000\u0000"+
		"\u0000\u0082\u0084\u0001\u0000\u0000\u0000\u0083z\u0001\u0000\u0000\u0000"+
		"\u0083\u0082\u0001\u0000\u0000\u0000\u0084\r\u0001\u0000\u0000\u0000\u0085"+
		"\u008a\u0003\u0010\b\u0000\u0086\u008a\u0003\u0012\t\u0000\u0087\u008a"+
		"\u0003\u0016\u000b\u0000\u0088\u008a\u0003\u0018\f\u0000\u0089\u0085\u0001"+
		"\u0000\u0000\u0000\u0089\u0086\u0001\u0000\u0000\u0000\u0089\u0087\u0001"+
		"\u0000\u0000\u0000\u0089\u0088\u0001\u0000\u0000\u0000\u008a\u000f\u0001"+
		"\u0000\u0000\u0000\u008b\u008c\u0005.\u0000\u0000\u008c\u008d\u0003:\u001d"+
		"\u0000\u008d\u008e\u0003\f\u0006\u0000\u008e\u008f\u0005 \u0000\u0000"+
		"\u008f\u0011\u0001\u0000\u0000\u0000\u0090\u0091\u0005#\u0000\u0000\u0091"+
		"\u0092\u0003:\u001d\u0000\u0092\u0093\u0005)\u0000\u0000\u0093\u0094\u0003"+
		"\f\u0006\u0000\u0094\u0095\u0003\u0014\n\u0000\u0095\u0013\u0001\u0000"+
		"\u0000\u0000\u0096\u009c\u0005 \u0000\u0000\u0097\u0098\u0005\u001f\u0000"+
		"\u0000\u0098\u0099\u0003\f\u0006\u0000\u0099\u009a\u0005 \u0000\u0000"+
		"\u009a\u009c\u0001\u0000\u0000\u0000\u009b\u0096\u0001\u0000\u0000\u0000"+
		"\u009b\u0097\u0001\u0000\u0000\u0000\u009c\u0015\u0001\u0000\u0000\u0000"+
		"\u009d\u009e\u0005%\u0000\u0000\u009e\u009f\u0003\u0002\u0001\u0000\u009f"+
		"\u00a0\u0005$\u0000\u0000\u00a0\u00a1\u0003\f\u0006\u0000\u00a1\u00a2"+
		"\u0005 \u0000\u0000\u00a2\u0017\u0001\u0000\u0000\u0000\u00a3\u00ab\u0003"+
		":\u001d\u0000\u00a4\u00a5\u0003:\u001d\u0000\u00a5\u00a6\u0005\u0012\u0000"+
		"\u0000\u00a6\u00a7\u0003:\u001d\u0000\u00a7\u00ab\u0001\u0000\u0000\u0000"+
		"\u00a8\u00a9\u0005\'\u0000\u0000\u00a9\u00ab\u0003:\u001d\u0000\u00aa"+
		"\u00a3\u0001\u0000\u0000\u0000\u00aa\u00a4\u0001\u0000\u0000\u0000\u00aa"+
		"\u00a8\u0001\u0000\u0000\u0000\u00ab\u0019\u0001\u0000\u0000\u0000\u00ac"+
		"\u00b1\u0003\u001c\u000e\u0000\u00ad\u00ae\u0005\u0003\u0000\u0000\u00ae"+
		"\u00b0\u0003\u001c\u000e\u0000\u00af\u00ad\u0001\u0000\u0000\u0000\u00b0"+
		"\u00b3\u0001\u0000\u0000\u0000\u00b1\u00af\u0001\u0000\u0000\u0000\u00b1"+
		"\u00b2\u0001\u0000\u0000\u0000\u00b2\u001b\u0001\u0000\u0000\u0000\u00b3"+
		"\u00b1\u0001\u0000\u0000\u0000\u00b4\u00b9\u0003\u001e\u000f\u0000\u00b5"+
		"\u00b6\u0005\u0002\u0000\u0000\u00b6\u00b8\u0003\u001e\u000f\u0000\u00b7"+
		"\u00b5\u0001\u0000\u0000\u0000\u00b8\u00bb\u0001\u0000\u0000\u0000\u00b9"+
		"\u00b7\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba"+
		"\u001d\u0001\u0000\u0000\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000\u00bc"+
		"\u00c2\u0003\"\u0011\u0000\u00bd\u00be\u0003 \u0010\u0000\u00be\u00bf"+
		"\u0003\"\u0011\u0000\u00bf\u00c1\u0001\u0000\u0000\u0000\u00c0\u00bd\u0001"+
		"\u0000\u0000\u0000\u00c1\u00c4\u0001\u0000\u0000\u0000\u00c2\u00c0\u0001"+
		"\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3\u001f\u0001"+
		"\u0000\u0000\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000\u00c5\u00c6\u0007"+
		"\u0000\u0000\u0000\u00c6!\u0001\u0000\u0000\u0000\u00c7\u00cd\u0003&\u0013"+
		"\u0000\u00c8\u00c9\u0003$\u0012\u0000\u00c9\u00ca\u0003&\u0013\u0000\u00ca"+
		"\u00cc\u0001\u0000\u0000\u0000\u00cb\u00c8\u0001\u0000\u0000\u0000\u00cc"+
		"\u00cf\u0001\u0000\u0000\u0000\u00cd\u00cb\u0001\u0000\u0000\u0000\u00cd"+
		"\u00ce\u0001\u0000\u0000\u0000\u00ce#\u0001\u0000\u0000\u0000\u00cf\u00cd"+
		"\u0001\u0000\u0000\u0000\u00d0\u00d1\u0007\u0001\u0000\u0000\u00d1%\u0001"+
		"\u0000\u0000\u0000\u00d2\u00d8\u0003*\u0015\u0000\u00d3\u00d4\u0003(\u0014"+
		"\u0000\u00d4\u00d5\u0003*\u0015\u0000\u00d5\u00d7\u0001\u0000\u0000\u0000"+
		"\u00d6\u00d3\u0001\u0000\u0000\u0000\u00d7\u00da\u0001\u0000\u0000\u0000"+
		"\u00d8\u00d6\u0001\u0000\u0000\u0000\u00d8\u00d9\u0001\u0000\u0000\u0000"+
		"\u00d9\'\u0001\u0000\u0000\u0000\u00da\u00d8\u0001\u0000\u0000\u0000\u00db"+
		"\u00dc\u0007\u0002\u0000\u0000\u00dc)\u0001\u0000\u0000\u0000\u00dd\u00de"+
		"\u0003,\u0016\u0000\u00de\u00df\u00030\u0018\u0000\u00df+\u0001\u0000"+
		"\u0000\u0000\u00e0\u00e1\u0007\u0003\u0000\u0000\u00e1-\u0001\u0000\u0000"+
		"\u0000\u00e2\u00e3\u0003\u001a\r\u0000\u00e3\u00e4\u0005\u0014\u0000\u0000"+
		"\u00e4\u00e6\u0001\u0000\u0000\u0000\u00e5\u00e2\u0001\u0000\u0000\u0000"+
		"\u00e6\u00e9\u0001\u0000\u0000\u0000\u00e7\u00e5\u0001\u0000\u0000\u0000"+
		"\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8\u00ea\u0001\u0000\u0000\u0000"+
		"\u00e9\u00e7\u0001\u0000\u0000\u0000\u00ea\u00ed\u0003\u001a\r\u0000\u00eb"+
		"\u00ed\u0001\u0000\u0000\u0000\u00ec\u00e7\u0001\u0000\u0000\u0000\u00ec"+
		"\u00eb\u0001\u0000\u0000\u0000\u00ed/\u0001\u0000\u0000\u0000\u00ee\u00f2"+
		"\u00034\u001a\u0000\u00ef\u00f1\u00032\u0019\u0000\u00f0\u00ef\u0001\u0000"+
		"\u0000\u0000\u00f1\u00f4\u0001\u0000\u0000\u0000\u00f2\u00f0\u0001\u0000"+
		"\u0000\u0000\u00f2\u00f3\u0001\u0000\u0000\u0000\u00f31\u0001\u0000\u0000"+
		"\u0000\u00f4\u00f2\u0001\u0000\u0000\u0000\u00f5\u00fd\u0005\u0011\u0000"+
		"\u0000\u00f6\u00f7\u0005\u0010\u0000\u0000\u00f7\u00fd\u0005/\u0000\u0000"+
		"\u00f8\u00f9\u0005\u0017\u0000\u0000\u00f9\u00fa\u0003.\u0017\u0000\u00fa"+
		"\u00fb\u0005\u0018\u0000\u0000\u00fb\u00fd\u0001\u0000\u0000\u0000\u00fc"+
		"\u00f5\u0001\u0000\u0000\u0000\u00fc\u00f6\u0001\u0000\u0000\u0000\u00fc"+
		"\u00f8\u0001\u0000\u0000\u0000\u00fd3\u0001\u0000\u0000\u0000\u00fe\u00ff"+
		"\u0005\u0017\u0000\u0000\u00ff\u0100\u0003:\u001d\u0000\u0100\u0101\u0005"+
		"\u0018\u0000\u0000\u0101\u010b\u0001\u0000\u0000\u0000\u0102\u0103\u0005"+
		"\u0015\u0000\u0000\u0103\u0104\u0003:\u001d\u0000\u0104\u0105\u0005\u0013"+
		"\u0000\u0000\u0105\u0106\u0003>\u001f\u0000\u0106\u0107\u0005\u0016\u0000"+
		"\u0000\u0107\u010b\u0001\u0000\u0000\u0000\u0108\u010b\u0003<\u001e\u0000"+
		"\u0109\u010b\u0005/\u0000\u0000\u010a\u00fe\u0001\u0000\u0000\u0000\u010a"+
		"\u0102\u0001\u0000\u0000\u0000\u010a\u0108\u0001\u0000\u0000\u0000\u010a"+
		"\u0109\u0001\u0000\u0000\u0000\u010b5\u0001\u0000\u0000\u0000\u010c\u010d"+
		"\u0003:\u001d\u0000\u010d\u010e\u0005\u0019\u0000\u0000\u010e\u010f\u0003"+
		":\u001d\u0000\u010f\u0110\u0005\u001a\u0000\u0000\u0110\u0116\u0001\u0000"+
		"\u0000\u0000\u0111\u0112\u0003:\u001d\u0000\u0112\u0113\u0005\u0017\u0000"+
		"\u0000\u0113\u0114\u00038\u001c\u0000\u0114\u0116\u0001\u0000\u0000\u0000"+
		"\u0115\u010c\u0001\u0000\u0000\u0000\u0115\u0111\u0001\u0000\u0000\u0000"+
		"\u01167\u0001\u0000\u0000\u0000\u0117\u0118\u0003:\u001d\u0000\u0118\u0119"+
		"\u0005\u0014\u0000\u0000\u0119\u011a\u00038\u001c\u0000\u011a\u011d\u0001"+
		"\u0000\u0000\u0000\u011b\u011d\u0005\u0018\u0000\u0000\u011c\u0117\u0001"+
		"\u0000\u0000\u0000\u011c\u011b\u0001\u0000\u0000\u0000\u011d9\u0001\u0000"+
		"\u0000\u0000\u011e\u011f\u0003\u001a\r\u0000\u011f;\u0001\u0000\u0000"+
		"\u0000\u0120\u012a\u00052\u0000\u0000\u0121\u012a\u00050\u0000\u0000\u0122"+
		"\u012a\u00053\u0000\u0000\u0123\u012a\u0005\u001c\u0000\u0000\u0124\u012a"+
		"\u0005!\u0000\u0000\u0125\u012a\u0005*\u0000\u0000\u0126\u012a\u0005&"+
		"\u0000\u0000\u0127\u0128\u0005(\u0000\u0000\u0128\u012a\u0003>\u001f\u0000"+
		"\u0129\u0120\u0001\u0000\u0000\u0000\u0129\u0121\u0001\u0000\u0000\u0000"+
		"\u0129\u0122\u0001\u0000\u0000\u0000\u0129\u0123\u0001\u0000\u0000\u0000"+
		"\u0129\u0124\u0001\u0000\u0000\u0000\u0129\u0125\u0001\u0000\u0000\u0000"+
		"\u0129\u0126\u0001\u0000\u0000\u0000\u0129\u0127\u0001\u0000\u0000\u0000"+
		"\u012a=\u0001\u0000\u0000\u0000\u012b\u012c\u0003@ \u0000\u012c?\u0001"+
		"\u0000\u0000\u0000\u012d\u012e\u0005\u0006\u0000\u0000\u012e\u012f\u0005"+
		"/\u0000\u0000\u012f\u0130\u0005\u0013\u0000\u0000\u0130\u0137\u0003>\u001f"+
		"\u0000\u0131\u0132\u0005\u0014\u0000\u0000\u0132\u0133\u0005/\u0000\u0000"+
		"\u0133\u0134\u0005\u0013\u0000\u0000\u0134\u0136\u0003>\u001f\u0000\u0135"+
		"\u0131\u0001\u0000\u0000\u0000\u0136\u0139\u0001\u0000\u0000\u0000\u0137"+
		"\u0135\u0001\u0000\u0000\u0000\u0137\u0138\u0001\u0000\u0000\u0000\u0138"+
		"\u013a\u0001\u0000\u0000\u0000\u0139\u0137\u0001\u0000\u0000\u0000\u013a"+
		"\u013b\u0005\u0007\u0000\u0000\u013b\u014d\u0001\u0000\u0000\u0000\u013c"+
		"\u013d\u0005\u0015\u0000\u0000\u013d\u013e\u0005/\u0000\u0000\u013e\u013f"+
		"\u0005\u0013\u0000\u0000\u013f\u0146\u0003>\u001f\u0000\u0140\u0141\u0005"+
		"\u0014\u0000\u0000\u0141\u0142\u0005/\u0000\u0000\u0142\u0143\u0005\u0013"+
		"\u0000\u0000\u0143\u0145\u0003>\u001f\u0000\u0144\u0140\u0001\u0000\u0000"+
		"\u0000\u0145\u0148\u0001\u0000\u0000\u0000\u0146\u0144\u0001\u0000\u0000"+
		"\u0000\u0146\u0147\u0001\u0000\u0000\u0000\u0147\u0149\u0001\u0000\u0000"+
		"\u0000\u0148\u0146\u0001\u0000\u0000\u0000\u0149\u014a\u0005\u0016\u0000"+
		"\u0000\u014a\u014d\u0001\u0000\u0000\u0000\u014b\u014d\u0003B!\u0000\u014c"+
		"\u012d\u0001\u0000\u0000\u0000\u014c\u013c\u0001\u0000\u0000\u0000\u014c"+
		"\u014b\u0001\u0000\u0000\u0000\u014dA\u0001\u0000\u0000\u0000\u014e\u014f"+
		"\u0005\u0019\u0000\u0000\u014f\u0150\u00050\u0000\u0000\u0150\u0151\u0005"+
		"\u001a\u0000\u0000\u0151\u0156\u0003>\u001f\u0000\u0152\u0153\u0005\u0011"+
		"\u0000\u0000\u0153\u0156\u0003>\u001f\u0000\u0154\u0156\u0003D\"\u0000"+
		"\u0155\u014e\u0001\u0000\u0000\u0000\u0155\u0152\u0001\u0000\u0000\u0000"+
		"\u0155\u0154\u0001\u0000\u0000\u0000\u0156C\u0001\u0000\u0000\u0000\u0157"+
		"\u0158\u0007\u0004\u0000\u0000\u0158E\u0001\u0000\u0000\u0000\u001aM_"+
		"kw\u007f\u0083\u0089\u009b\u00aa\u00b1\u00b9\u00c2\u00cd\u00d8\u00e7\u00ec"+
		"\u00f2\u00fc\u010a\u0115\u011c\u0129\u0137\u0146\u014c\u0155";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}