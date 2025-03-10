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
		RULE_source = 0, RULE_defs = 1, RULE_def = 2, RULE_args_ = 3, RULE_args__ = 4, 
		RULE_syn1 = 5, RULE_neki = 6, RULE_stmt_ = 7, RULE_stmt__ = 8, RULE_stmt = 9, 
		RULE_whileStmt = 10, RULE_ifStmt = 11, RULE_ifElseStmt = 12, RULE_letStmt = 13, 
		RULE_stmtBase = 14, RULE_exprEntry = 15, RULE_expr2nd = 16, RULE_expr3rd = 17, 
		RULE_comprOp = 18, RULE_expr4th = 19, RULE_additiveExpr = 20, RULE_expr5th = 21, 
		RULE_mulOps = 22, RULE_expr6th = 23, RULE_prefixOps = 24, RULE_exprs_ = 25, 
		RULE_exprs__ = 26, RULE_expr7th = 27, RULE_oper = 28, RULE_prim = 29, 
		RULE_functionCall = 30, RULE_expr = 31, RULE_exprEnd = 32, RULE_type = 33, 
		RULE_idType = 34, RULE_types2 = 35, RULE_types = 36, RULE_types3 = 37, 
		RULE_syn21 = 38, RULE_typeOver = 39, RULE_type2 = 40;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "defs", "def", "args_", "args__", "syn1", "neki", "stmt_", 
			"stmt__", "stmt", "whileStmt", "ifStmt", "ifElseStmt", "letStmt", "stmtBase", 
			"exprEntry", "expr2nd", "expr3rd", "comprOp", "expr4th", "additiveExpr", 
			"expr5th", "mulOps", "expr6th", "prefixOps", "exprs_", "exprs__", "expr7th", 
			"oper", "prim", "functionCall", "expr", "exprEnd", "type", "idType", 
			"types2", "types", "types3", "syn21", "typeOver", "type2"
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
			setState(82);
			defs();
			setState(83);
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
			setState(89);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(85);
				def();
				setState(86);
				defs();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(88);
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
		public DefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def; }
	}

	public final DefContext def() throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_def);
		try {
			setState(106);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYP:
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				match(TYP);
				setState(92);
				match(IDENTIFIER);
				setState(93);
				match(ASSIGN);
				setState(94);
				type();
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(95);
				match(VAR);
				setState(96);
				match(IDENTIFIER);
				setState(97);
				match(COLON);
				setState(98);
				type();
				}
				break;
			case FUN:
				enterOuterAlt(_localctx, 3);
				{
				setState(99);
				match(FUN);
				setState(100);
				match(IDENTIFIER);
				setState(101);
				match(LPARAN);
				setState(102);
				args_();
				setState(103);
				match(RPARAN);
				setState(104);
				syn1();
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
		public Args__Context args__() {
			return getRuleContext(Args__Context.class,0);
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
			setState(118);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				match(IDENTIFIER);
				setState(109);
				match(COLON);
				setState(110);
				type();
				setState(111);
				match(COMMA);
				setState(112);
				args__();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(114);
				match(IDENTIFIER);
				setState(115);
				match(COLON);
				setState(116);
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
	public static class Args__Context extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(Prev25Parser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(Prev25Parser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public Args__Context args__() {
			return getRuleContext(Args__Context.class,0);
		}
		public Args__Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args__; }
	}

	public final Args__Context args__() throws RecognitionException {
		Args__Context _localctx = new Args__Context(_ctx, getState());
		enterRule(_localctx, 8, RULE_args__);
		try {
			setState(129);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(120);
				match(IDENTIFIER);
				setState(121);
				match(COLON);
				setState(122);
				type();
				setState(123);
				match(COMMA);
				setState(124);
				args__();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(126);
				match(IDENTIFIER);
				setState(127);
				match(COLON);
				setState(128);
				type();
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
		enterRule(_localctx, 10, RULE_syn1);
		try {
			setState(137);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(131);
				match(COLON);
				setState(132);
				type();
				setState(133);
				neki();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				match(COLON);
				setState(136);
				type();
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
		enterRule(_localctx, 12, RULE_neki);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(ASSIGN);
			setState(140);
			stmt();
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(141);
				match(COMMA);
				setState(142);
				stmt();
				}
				}
				setState(147);
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
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public Stmt__Context stmt__() {
			return getRuleContext(Stmt__Context.class,0);
		}
		public Stmt_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt_; }
	}

	public final Stmt_Context stmt_() throws RecognitionException {
		Stmt_Context _localctx = new Stmt_Context(_ctx, getState());
		enterRule(_localctx, 14, RULE_stmt_);
		try {
			setState(154);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				stmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				stmt();
				setState(150);
				match(COMMA);
				setState(151);
				stmt__();
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
	public static class Stmt__Context extends ParserRuleContext {
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public Stmt__Context stmt__() {
			return getRuleContext(Stmt__Context.class,0);
		}
		public Stmt__Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt__; }
	}

	public final Stmt__Context stmt__() throws RecognitionException {
		Stmt__Context _localctx = new Stmt__Context(_ctx, getState());
		enterRule(_localctx, 16, RULE_stmt__);
		try {
			setState(161);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(156);
				stmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(157);
				stmt();
				setState(158);
				match(COMMA);
				setState(159);
				stmt__();
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
		enterRule(_localctx, 18, RULE_stmt);
		try {
			setState(167);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WHILE:
				enterOuterAlt(_localctx, 1);
				{
				setState(163);
				whileStmt();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(164);
				ifStmt();
				}
				break;
			case LET:
				enterOuterAlt(_localctx, 3);
				{
				setState(165);
				letStmt();
				}
				break;
			case PLUS:
			case MINUS:
			case EXCL:
			case POW:
			case LBRACE:
			case LPARAN:
			case BOOL:
			case FALSE:
			case NULL:
			case RETURN:
			case SIZEOF:
			case TRUE:
			case IDENTIFIER:
			case CONSTNUM:
			case CONSTCHAR:
			case CONSTSTR:
				enterOuterAlt(_localctx, 4);
				{
				setState(166);
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
		public TerminalNode DO() { return getToken(Prev25Parser.DO, 0); }
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
		enterRule(_localctx, 20, RULE_whileStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(WHILE);
			setState(170);
			expr();
			setState(171);
			match(DO);
			setState(172);
			stmt_();
			setState(173);
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
		enterRule(_localctx, 22, RULE_ifStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(IF);
			setState(176);
			expr();
			setState(177);
			match(THEN);
			setState(178);
			stmt_();
			setState(179);
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
		enterRule(_localctx, 24, RULE_ifElseStmt);
		try {
			setState(186);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case END:
				enterOuterAlt(_localctx, 1);
				{
				setState(181);
				match(END);
				}
				break;
			case ELSE:
				enterOuterAlt(_localctx, 2);
				{
				setState(182);
				match(ELSE);
				setState(183);
				stmt_();
				setState(184);
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
		enterRule(_localctx, 26, RULE_letStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(LET);
			setState(189);
			defs();
			setState(190);
			match(IN);
			setState(191);
			stmt_();
			setState(192);
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
		enterRule(_localctx, 28, RULE_stmtBase);
		try {
			setState(201);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(194);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(195);
				expr();
				setState(196);
				match(ASSIGN);
				setState(197);
				expr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(199);
				match(RETURN);
				setState(200);
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
		public Expr2ndContext expr2nd() {
			return getRuleContext(Expr2ndContext.class,0);
		}
		public ExprEntryContext exprEntry() {
			return getRuleContext(ExprEntryContext.class,0);
		}
		public TerminalNode OR() { return getToken(Prev25Parser.OR, 0); }
		public ExprEntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprEntry; }
	}

	public final ExprEntryContext exprEntry() throws RecognitionException {
		return exprEntry(0);
	}

	private ExprEntryContext exprEntry(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprEntryContext _localctx = new ExprEntryContext(_ctx, _parentState);
		ExprEntryContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_exprEntry, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(204);
			expr2nd(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(211);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprEntryContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_exprEntry);
					setState(206);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(207);
					match(OR);
					setState(208);
					expr2nd(0);
					}
					} 
				}
				setState(213);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr2ndContext extends ParserRuleContext {
		public Expr3rdContext expr3rd() {
			return getRuleContext(Expr3rdContext.class,0);
		}
		public Expr2ndContext expr2nd() {
			return getRuleContext(Expr2ndContext.class,0);
		}
		public TerminalNode AND() { return getToken(Prev25Parser.AND, 0); }
		public Expr2ndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr2nd; }
	}

	public final Expr2ndContext expr2nd() throws RecognitionException {
		return expr2nd(0);
	}

	private Expr2ndContext expr2nd(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr2ndContext _localctx = new Expr2ndContext(_ctx, _parentState);
		Expr2ndContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_expr2nd, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(215);
			expr3rd();
			}
			_ctx.stop = _input.LT(-1);
			setState(222);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr2ndContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expr2nd);
					setState(217);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(218);
					match(AND);
					setState(219);
					expr3rd();
					}
					} 
				}
				setState(224);
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
			unrollRecursionContexts(_parentctx);
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
		public ComprOpContext comprOp() {
			return getRuleContext(ComprOpContext.class,0);
		}
		public Expr3rdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr3rd; }
	}

	public final Expr3rdContext expr3rd() throws RecognitionException {
		Expr3rdContext _localctx = new Expr3rdContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_expr3rd);
		try {
			setState(230);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(225);
				expr4th(0);
				setState(226);
				comprOp();
				setState(227);
				expr4th(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(229);
				expr4th(0);
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
		enterRule(_localctx, 36, RULE_comprOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
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
		public Expr5thContext expr5th() {
			return getRuleContext(Expr5thContext.class,0);
		}
		public Expr4thContext expr4th() {
			return getRuleContext(Expr4thContext.class,0);
		}
		public AdditiveExprContext additiveExpr() {
			return getRuleContext(AdditiveExprContext.class,0);
		}
		public Expr4thContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr4th; }
	}

	public final Expr4thContext expr4th() throws RecognitionException {
		return expr4th(0);
	}

	private Expr4thContext expr4th(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr4thContext _localctx = new Expr4thContext(_ctx, _parentState);
		Expr4thContext _prevctx = _localctx;
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_expr4th, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(235);
			expr5th(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(243);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr4thContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expr4th);
					setState(237);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(238);
					additiveExpr();
					setState(239);
					expr5th(0);
					}
					} 
				}
				setState(245);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
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
		enterRule(_localctx, 40, RULE_additiveExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
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
		public Expr6thContext expr6th() {
			return getRuleContext(Expr6thContext.class,0);
		}
		public Expr5thContext expr5th() {
			return getRuleContext(Expr5thContext.class,0);
		}
		public MulOpsContext mulOps() {
			return getRuleContext(MulOpsContext.class,0);
		}
		public Expr5thContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr5th; }
	}

	public final Expr5thContext expr5th() throws RecognitionException {
		return expr5th(0);
	}

	private Expr5thContext expr5th(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr5thContext _localctx = new Expr5thContext(_ctx, _parentState);
		Expr5thContext _prevctx = _localctx;
		int _startState = 42;
		enterRecursionRule(_localctx, 42, RULE_expr5th, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(249);
			expr6th();
			}
			_ctx.stop = _input.LT(-1);
			setState(257);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr5thContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expr5th);
					setState(251);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(252);
					mulOps();
					setState(253);
					expr6th();
					}
					} 
				}
				setState(259);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
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
		enterRule(_localctx, 44, RULE_mulOps);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
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
		public Expr6thContext expr6th() {
			return getRuleContext(Expr6thContext.class,0);
		}
		public Expr6thContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr6th; }
	}

	public final Expr6thContext expr6th() throws RecognitionException {
		Expr6thContext _localctx = new Expr6thContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_expr6th);
		try {
			setState(266);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
			case LPARAN:
			case BOOL:
			case FALSE:
			case NULL:
			case SIZEOF:
			case TRUE:
			case IDENTIFIER:
			case CONSTNUM:
			case CONSTCHAR:
			case CONSTSTR:
				enterOuterAlt(_localctx, 1);
				{
				setState(262);
				expr7th(0);
				}
				break;
			case PLUS:
			case MINUS:
			case EXCL:
			case POW:
				enterOuterAlt(_localctx, 2);
				{
				setState(263);
				prefixOps();
				setState(264);
				expr6th();
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
		enterRule(_localctx, 48, RULE_prefixOps);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
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
		public ExprEntryContext exprEntry() {
			return getRuleContext(ExprEntryContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public Exprs__Context exprs__() {
			return getRuleContext(Exprs__Context.class,0);
		}
		public Exprs_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprs_; }
	}

	public final Exprs_Context exprs_() throws RecognitionException {
		Exprs_Context _localctx = new Exprs_Context(_ctx, getState());
		enterRule(_localctx, 50, RULE_exprs_);
		try {
			setState(276);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(270);
				exprEntry(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(271);
				exprEntry(0);
				setState(272);
				match(COMMA);
				setState(273);
				exprs__();
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
	public static class Exprs__Context extends ParserRuleContext {
		public ExprEntryContext exprEntry() {
			return getRuleContext(ExprEntryContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public Exprs__Context exprs__() {
			return getRuleContext(Exprs__Context.class,0);
		}
		public Exprs__Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprs__; }
	}

	public final Exprs__Context exprs__() throws RecognitionException {
		Exprs__Context _localctx = new Exprs__Context(_ctx, getState());
		enterRule(_localctx, 52, RULE_exprs__);
		try {
			setState(283);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(278);
				exprEntry(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(279);
				exprEntry(0);
				setState(280);
				match(COMMA);
				setState(281);
				exprs__();
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
	public static class Expr7thContext extends ParserRuleContext {
		public PrimContext prim() {
			return getRuleContext(PrimContext.class,0);
		}
		public Expr7thContext expr7th() {
			return getRuleContext(Expr7thContext.class,0);
		}
		public OperContext oper() {
			return getRuleContext(OperContext.class,0);
		}
		public Expr7thContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr7th; }
	}

	public final Expr7thContext expr7th() throws RecognitionException {
		return expr7th(0);
	}

	private Expr7thContext expr7th(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr7thContext _localctx = new Expr7thContext(_ctx, _parentState);
		Expr7thContext _prevctx = _localctx;
		int _startState = 54;
		enterRecursionRule(_localctx, 54, RULE_expr7th, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(286);
			prim();
			}
			_ctx.stop = _input.LT(-1);
			setState(292);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr7thContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expr7th);
					setState(288);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					{
					setState(289);
					oper();
					}
					}
					} 
				}
				setState(294);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
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
		public TerminalNode LBRCKT() { return getToken(Prev25Parser.LBRCKT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RBRCKT() { return getToken(Prev25Parser.RBRCKT, 0); }
		public OperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_oper; }
	}

	public final OperContext oper() throws RecognitionException {
		OperContext _localctx = new OperContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_oper);
		try {
			setState(306);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case POW:
				enterOuterAlt(_localctx, 1);
				{
				setState(295);
				match(POW);
				}
				break;
			case DOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(296);
				match(DOT);
				setState(297);
				match(IDENTIFIER);
				}
				break;
			case LPARAN:
				enterOuterAlt(_localctx, 3);
				{
				setState(298);
				match(LPARAN);
				setState(299);
				exprs_();
				setState(300);
				match(RPARAN);
				}
				break;
			case LBRCKT:
				enterOuterAlt(_localctx, 4);
				{
				setState(302);
				match(LBRCKT);
				setState(303);
				expr();
				setState(304);
				match(RBRCKT);
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
		enterRule(_localctx, 58, RULE_prim);
		try {
			setState(320);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPARAN:
				enterOuterAlt(_localctx, 1);
				{
				setState(308);
				match(LPARAN);
				setState(309);
				expr();
				setState(310);
				match(RPARAN);
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(312);
				match(LBRACE);
				setState(313);
				expr();
				setState(314);
				match(COLON);
				setState(315);
				type();
				setState(316);
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
				setState(318);
				exprEnd();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 4);
				{
				setState(319);
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
		enterRule(_localctx, 60, RULE_functionCall);
		try {
			setState(327);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case EXCL:
			case POW:
			case LBRACE:
			case LPARAN:
			case BOOL:
			case FALSE:
			case NULL:
			case SIZEOF:
			case TRUE:
			case IDENTIFIER:
			case CONSTNUM:
			case CONSTCHAR:
			case CONSTSTR:
				enterOuterAlt(_localctx, 1);
				{
				setState(322);
				expr();
				setState(323);
				match(COMMA);
				setState(324);
				functionCall();
				}
				break;
			case RPARAN:
				enterOuterAlt(_localctx, 2);
				{
				setState(326);
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
		enterRule(_localctx, 62, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(329);
			exprEntry(0);
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
		enterRule(_localctx, 64, RULE_exprEnd);
		try {
			setState(340);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONSTCHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(331);
				match(CONSTCHAR);
				}
				break;
			case CONSTNUM:
				enterOuterAlt(_localctx, 2);
				{
				setState(332);
				match(CONSTNUM);
				}
				break;
			case CONSTSTR:
				enterOuterAlt(_localctx, 3);
				{
				setState(333);
				match(CONSTSTR);
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 4);
				{
				setState(334);
				match(BOOL);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 5);
				{
				setState(335);
				match(FALSE);
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 6);
				{
				setState(336);
				match(TRUE);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 7);
				{
				setState(337);
				match(NULL);
				}
				break;
			case SIZEOF:
				enterOuterAlt(_localctx, 8);
				{
				setState(338);
				match(SIZEOF);
				setState(339);
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
		enterRule(_localctx, 66, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
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
	public static class IdTypeContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(Prev25Parser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(Prev25Parser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public IdTypeContext idType() {
			return getRuleContext(IdTypeContext.class,0);
		}
		public IdTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idType; }
	}

	public final IdTypeContext idType() throws RecognitionException {
		IdTypeContext _localctx = new IdTypeContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_idType);
		try {
			setState(353);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(344);
				match(IDENTIFIER);
				setState(345);
				match(COLON);
				setState(346);
				type();
				setState(347);
				match(COMMA);
				setState(348);
				idType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(350);
				match(IDENTIFIER);
				setState(351);
				match(COLON);
				setState(352);
				type();
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
	public static class Types2Context extends ParserRuleContext {
		public IdTypeContext idType() {
			return getRuleContext(IdTypeContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public Types2Context types2() {
			return getRuleContext(Types2Context.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Types2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_types2; }
	}

	public final Types2Context types2() throws RecognitionException {
		Types2Context _localctx = new Types2Context(_ctx, getState());
		enterRule(_localctx, 70, RULE_types2);
		try {
			setState(365);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(355);
				idType();
				setState(356);
				match(COMMA);
				setState(357);
				types2();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(359);
				type();
				setState(360);
				match(COMMA);
				setState(361);
				types2();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(363);
				type();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(364);
				idType();
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
	public static class TypesContext extends ParserRuleContext {
		public Types3Context types3() {
			return getRuleContext(Types3Context.class,0);
		}
		public TypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_types; }
	}

	public final TypesContext types() throws RecognitionException {
		TypesContext _localctx = new TypesContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_types);
		try {
			setState(369);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LT:
			case POW:
			case LBRACE:
			case LPARAN:
			case LBRCKT:
			case INT:
			case BOOL:
			case CHAR:
			case VOID:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(367);
				types3();
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
	public static class Types3Context extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public Types3Context types3() {
			return getRuleContext(Types3Context.class,0);
		}
		public Types3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_types3; }
	}

	public final Types3Context types3() throws RecognitionException {
		Types3Context _localctx = new Types3Context(_ctx, getState());
		enterRule(_localctx, 74, RULE_types3);
		try {
			setState(376);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(371);
				type();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(372);
				type();
				setState(373);
				match(COMMA);
				setState(374);
				types3();
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
	public static class Syn21Context extends ParserRuleContext {
		public TerminalNode LT() { return getToken(Prev25Parser.LT, 0); }
		public Types2Context types2() {
			return getRuleContext(Types2Context.class,0);
		}
		public TerminalNode GT() { return getToken(Prev25Parser.GT, 0); }
		public TerminalNode LBRACE() { return getToken(Prev25Parser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(Prev25Parser.RBRACE, 0); }
		public TerminalNode LPARAN() { return getToken(Prev25Parser.LPARAN, 0); }
		public TypesContext types() {
			return getRuleContext(TypesContext.class,0);
		}
		public TerminalNode RPARAN() { return getToken(Prev25Parser.RPARAN, 0); }
		public TerminalNode COLON() { return getToken(Prev25Parser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
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
		enterRule(_localctx, 76, RULE_syn21);
		try {
			setState(393);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LT:
				enterOuterAlt(_localctx, 1);
				{
				setState(378);
				match(LT);
				setState(379);
				types2();
				setState(380);
				match(GT);
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(382);
				match(LBRACE);
				setState(383);
				types2();
				setState(384);
				match(RBRACE);
				}
				break;
			case LPARAN:
				enterOuterAlt(_localctx, 3);
				{
				setState(386);
				match(LPARAN);
				setState(387);
				types();
				setState(388);
				match(RPARAN);
				setState(389);
				match(COLON);
				setState(390);
				type();
				}
				break;
			case POW:
			case LBRCKT:
			case INT:
			case BOOL:
			case CHAR:
			case VOID:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 4);
				{
				setState(392);
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
		enterRule(_localctx, 78, RULE_typeOver);
		try {
			setState(402);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRCKT:
				enterOuterAlt(_localctx, 1);
				{
				setState(395);
				match(LBRCKT);
				setState(396);
				match(CONSTNUM);
				setState(397);
				match(RBRCKT);
				setState(398);
				type();
				}
				break;
			case POW:
				enterOuterAlt(_localctx, 2);
				{
				setState(399);
				match(POW);
				setState(400);
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
				setState(401);
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
		enterRule(_localctx, 80, RULE_type2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(404);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 15:
			return exprEntry_sempred((ExprEntryContext)_localctx, predIndex);
		case 16:
			return expr2nd_sempred((Expr2ndContext)_localctx, predIndex);
		case 19:
			return expr4th_sempred((Expr4thContext)_localctx, predIndex);
		case 21:
			return expr5th_sempred((Expr5thContext)_localctx, predIndex);
		case 27:
			return expr7th_sempred((Expr7thContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean exprEntry_sempred(ExprEntryContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr2nd_sempred(Expr2ndContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr4th_sempred(Expr4thContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr5th_sempred(Expr5thContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr7th_sempred(Expr7thContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00014\u0197\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001Z\b\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0003\u0002k\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0003\u0003w\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003"+
		"\u0004\u0082\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0003\u0005\u008a\b\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0005\u0006\u0090\b\u0006\n\u0006\f\u0006\u0093\t\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007\u009b\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003"+
		"\b\u00a2\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u00a8\b\t\u0001\n"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0003\f\u00bb\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0003\u000e\u00ca\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u00d2\b\u000f\n\u000f"+
		"\f\u000f\u00d5\t\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0005\u0010\u00dd\b\u0010\n\u0010\f\u0010\u00e0"+
		"\t\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003"+
		"\u0011\u00e7\b\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u00f2"+
		"\b\u0013\n\u0013\f\u0013\u00f5\t\u0013\u0001\u0014\u0001\u0014\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0005\u0015\u0100\b\u0015\n\u0015\f\u0015\u0103\t\u0015\u0001\u0016\u0001"+
		"\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u010b"+
		"\b\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0115\b\u0019\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u011c\b\u001a\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0005\u001b\u0123"+
		"\b\u001b\n\u001b\f\u001b\u0126\t\u001b\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0003\u001c\u0133\b\u001c\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0003\u001d\u0141\b\u001d"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e"+
		"\u0148\b\u001e\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0001 \u0001"+
		" \u0001 \u0001 \u0001 \u0001 \u0003 \u0155\b \u0001!\u0001!\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0003\"\u0162"+
		"\b\"\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001"+
		"#\u0003#\u016e\b#\u0001$\u0001$\u0003$\u0172\b$\u0001%\u0001%\u0001%\u0001"+
		"%\u0001%\u0003%\u0179\b%\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001"+
		"&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0003&\u018a"+
		"\b&\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0003\'\u0193"+
		"\b\'\u0001(\u0001(\u0001(\u0000\u0005\u001e &*6)\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,."+
		"02468:<>@BDFHJLNP\u0000\u0005\u0001\u0000\u0004\t\u0001\u0000\r\u000e"+
		"\u0001\u0000\n\f\u0002\u0000\r\u000f\u0011\u0011\u0003\u0000\u001b\u001d"+
		"--//\u01a1\u0000R\u0001\u0000\u0000\u0000\u0002Y\u0001\u0000\u0000\u0000"+
		"\u0004j\u0001\u0000\u0000\u0000\u0006v\u0001\u0000\u0000\u0000\b\u0081"+
		"\u0001\u0000\u0000\u0000\n\u0089\u0001\u0000\u0000\u0000\f\u008b\u0001"+
		"\u0000\u0000\u0000\u000e\u009a\u0001\u0000\u0000\u0000\u0010\u00a1\u0001"+
		"\u0000\u0000\u0000\u0012\u00a7\u0001\u0000\u0000\u0000\u0014\u00a9\u0001"+
		"\u0000\u0000\u0000\u0016\u00af\u0001\u0000\u0000\u0000\u0018\u00ba\u0001"+
		"\u0000\u0000\u0000\u001a\u00bc\u0001\u0000\u0000\u0000\u001c\u00c9\u0001"+
		"\u0000\u0000\u0000\u001e\u00cb\u0001\u0000\u0000\u0000 \u00d6\u0001\u0000"+
		"\u0000\u0000\"\u00e6\u0001\u0000\u0000\u0000$\u00e8\u0001\u0000\u0000"+
		"\u0000&\u00ea\u0001\u0000\u0000\u0000(\u00f6\u0001\u0000\u0000\u0000*"+
		"\u00f8\u0001\u0000\u0000\u0000,\u0104\u0001\u0000\u0000\u0000.\u010a\u0001"+
		"\u0000\u0000\u00000\u010c\u0001\u0000\u0000\u00002\u0114\u0001\u0000\u0000"+
		"\u00004\u011b\u0001\u0000\u0000\u00006\u011d\u0001\u0000\u0000\u00008"+
		"\u0132\u0001\u0000\u0000\u0000:\u0140\u0001\u0000\u0000\u0000<\u0147\u0001"+
		"\u0000\u0000\u0000>\u0149\u0001\u0000\u0000\u0000@\u0154\u0001\u0000\u0000"+
		"\u0000B\u0156\u0001\u0000\u0000\u0000D\u0161\u0001\u0000\u0000\u0000F"+
		"\u016d\u0001\u0000\u0000\u0000H\u0171\u0001\u0000\u0000\u0000J\u0178\u0001"+
		"\u0000\u0000\u0000L\u0189\u0001\u0000\u0000\u0000N\u0192\u0001\u0000\u0000"+
		"\u0000P\u0194\u0001\u0000\u0000\u0000RS\u0003\u0002\u0001\u0000ST\u0005"+
		"\u0000\u0000\u0001T\u0001\u0001\u0000\u0000\u0000UV\u0003\u0004\u0002"+
		"\u0000VW\u0003\u0002\u0001\u0000WZ\u0001\u0000\u0000\u0000XZ\u0003\u0004"+
		"\u0002\u0000YU\u0001\u0000\u0000\u0000YX\u0001\u0000\u0000\u0000Z\u0003"+
		"\u0001\u0000\u0000\u0000[\\\u0005+\u0000\u0000\\]\u0005/\u0000\u0000]"+
		"^\u0005\u0012\u0000\u0000^k\u0003B!\u0000_`\u0005,\u0000\u0000`a\u0005"+
		"/\u0000\u0000ab\u0005\u0013\u0000\u0000bk\u0003B!\u0000cd\u0005\"\u0000"+
		"\u0000de\u0005/\u0000\u0000ef\u0005\u0017\u0000\u0000fg\u0003\u0006\u0003"+
		"\u0000gh\u0005\u0018\u0000\u0000hi\u0003\n\u0005\u0000ik\u0001\u0000\u0000"+
		"\u0000j[\u0001\u0000\u0000\u0000j_\u0001\u0000\u0000\u0000jc\u0001\u0000"+
		"\u0000\u0000k\u0005\u0001\u0000\u0000\u0000lm\u0005/\u0000\u0000mn\u0005"+
		"\u0013\u0000\u0000no\u0003B!\u0000op\u0005\u0014\u0000\u0000pq\u0003\b"+
		"\u0004\u0000qw\u0001\u0000\u0000\u0000rs\u0005/\u0000\u0000st\u0005\u0013"+
		"\u0000\u0000tw\u0003B!\u0000uw\u0001\u0000\u0000\u0000vl\u0001\u0000\u0000"+
		"\u0000vr\u0001\u0000\u0000\u0000vu\u0001\u0000\u0000\u0000w\u0007\u0001"+
		"\u0000\u0000\u0000xy\u0005/\u0000\u0000yz\u0005\u0013\u0000\u0000z{\u0003"+
		"B!\u0000{|\u0005\u0014\u0000\u0000|}\u0003\b\u0004\u0000}\u0082\u0001"+
		"\u0000\u0000\u0000~\u007f\u0005/\u0000\u0000\u007f\u0080\u0005\u0013\u0000"+
		"\u0000\u0080\u0082\u0003B!\u0000\u0081x\u0001\u0000\u0000\u0000\u0081"+
		"~\u0001\u0000\u0000\u0000\u0082\t\u0001\u0000\u0000\u0000\u0083\u0084"+
		"\u0005\u0013\u0000\u0000\u0084\u0085\u0003B!\u0000\u0085\u0086\u0003\f"+
		"\u0006\u0000\u0086\u008a\u0001\u0000\u0000\u0000\u0087\u0088\u0005\u0013"+
		"\u0000\u0000\u0088\u008a\u0003B!\u0000\u0089\u0083\u0001\u0000\u0000\u0000"+
		"\u0089\u0087\u0001\u0000\u0000\u0000\u008a\u000b\u0001\u0000\u0000\u0000"+
		"\u008b\u008c\u0005\u0012\u0000\u0000\u008c\u0091\u0003\u0012\t\u0000\u008d"+
		"\u008e\u0005\u0014\u0000\u0000\u008e\u0090\u0003\u0012\t\u0000\u008f\u008d"+
		"\u0001\u0000\u0000\u0000\u0090\u0093\u0001\u0000\u0000\u0000\u0091\u008f"+
		"\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092\r\u0001"+
		"\u0000\u0000\u0000\u0093\u0091\u0001\u0000\u0000\u0000\u0094\u009b\u0003"+
		"\u0012\t\u0000\u0095\u0096\u0003\u0012\t\u0000\u0096\u0097\u0005\u0014"+
		"\u0000\u0000\u0097\u0098\u0003\u0010\b\u0000\u0098\u009b\u0001\u0000\u0000"+
		"\u0000\u0099\u009b\u0001\u0000\u0000\u0000\u009a\u0094\u0001\u0000\u0000"+
		"\u0000\u009a\u0095\u0001\u0000\u0000\u0000\u009a\u0099\u0001\u0000\u0000"+
		"\u0000\u009b\u000f\u0001\u0000\u0000\u0000\u009c\u00a2\u0003\u0012\t\u0000"+
		"\u009d\u009e\u0003\u0012\t\u0000\u009e\u009f\u0005\u0014\u0000\u0000\u009f"+
		"\u00a0\u0003\u0010\b\u0000\u00a0\u00a2\u0001\u0000\u0000\u0000\u00a1\u009c"+
		"\u0001\u0000\u0000\u0000\u00a1\u009d\u0001\u0000\u0000\u0000\u00a2\u0011"+
		"\u0001\u0000\u0000\u0000\u00a3\u00a8\u0003\u0014\n\u0000\u00a4\u00a8\u0003"+
		"\u0016\u000b\u0000\u00a5\u00a8\u0003\u001a\r\u0000\u00a6\u00a8\u0003\u001c"+
		"\u000e\u0000\u00a7\u00a3\u0001\u0000\u0000\u0000\u00a7\u00a4\u0001\u0000"+
		"\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a7\u00a6\u0001\u0000"+
		"\u0000\u0000\u00a8\u0013\u0001\u0000\u0000\u0000\u00a9\u00aa\u0005.\u0000"+
		"\u0000\u00aa\u00ab\u0003>\u001f\u0000\u00ab\u00ac\u0005\u001e\u0000\u0000"+
		"\u00ac\u00ad\u0003\u000e\u0007\u0000\u00ad\u00ae\u0005 \u0000\u0000\u00ae"+
		"\u0015\u0001\u0000\u0000\u0000\u00af\u00b0\u0005#\u0000\u0000\u00b0\u00b1"+
		"\u0003>\u001f\u0000\u00b1\u00b2\u0005)\u0000\u0000\u00b2\u00b3\u0003\u000e"+
		"\u0007\u0000\u00b3\u00b4\u0003\u0018\f\u0000\u00b4\u0017\u0001\u0000\u0000"+
		"\u0000\u00b5\u00bb\u0005 \u0000\u0000\u00b6\u00b7\u0005\u001f\u0000\u0000"+
		"\u00b7\u00b8\u0003\u000e\u0007\u0000\u00b8\u00b9\u0005 \u0000\u0000\u00b9"+
		"\u00bb\u0001\u0000\u0000\u0000\u00ba\u00b5\u0001\u0000\u0000\u0000\u00ba"+
		"\u00b6\u0001\u0000\u0000\u0000\u00bb\u0019\u0001\u0000\u0000\u0000\u00bc"+
		"\u00bd\u0005%\u0000\u0000\u00bd\u00be\u0003\u0002\u0001\u0000\u00be\u00bf"+
		"\u0005$\u0000\u0000\u00bf\u00c0\u0003\u000e\u0007\u0000\u00c0\u00c1\u0005"+
		" \u0000\u0000\u00c1\u001b\u0001\u0000\u0000\u0000\u00c2\u00ca\u0003>\u001f"+
		"\u0000\u00c3\u00c4\u0003>\u001f\u0000\u00c4\u00c5\u0005\u0012\u0000\u0000"+
		"\u00c5\u00c6\u0003>\u001f\u0000\u00c6\u00ca\u0001\u0000\u0000\u0000\u00c7"+
		"\u00c8\u0005\'\u0000\u0000\u00c8\u00ca\u0003>\u001f\u0000\u00c9\u00c2"+
		"\u0001\u0000\u0000\u0000\u00c9\u00c3\u0001\u0000\u0000\u0000\u00c9\u00c7"+
		"\u0001\u0000\u0000\u0000\u00ca\u001d\u0001\u0000\u0000\u0000\u00cb\u00cc"+
		"\u0006\u000f\uffff\uffff\u0000\u00cc\u00cd\u0003 \u0010\u0000\u00cd\u00d3"+
		"\u0001\u0000\u0000\u0000\u00ce\u00cf\n\u0002\u0000\u0000\u00cf\u00d0\u0005"+
		"\u0003\u0000\u0000\u00d0\u00d2\u0003 \u0010\u0000\u00d1\u00ce\u0001\u0000"+
		"\u0000\u0000\u00d2\u00d5\u0001\u0000\u0000\u0000\u00d3\u00d1\u0001\u0000"+
		"\u0000\u0000\u00d3\u00d4\u0001\u0000\u0000\u0000\u00d4\u001f\u0001\u0000"+
		"\u0000\u0000\u00d5\u00d3\u0001\u0000\u0000\u0000\u00d6\u00d7\u0006\u0010"+
		"\uffff\uffff\u0000\u00d7\u00d8\u0003\"\u0011\u0000\u00d8\u00de\u0001\u0000"+
		"\u0000\u0000\u00d9\u00da\n\u0002\u0000\u0000\u00da\u00db\u0005\u0002\u0000"+
		"\u0000\u00db\u00dd\u0003\"\u0011\u0000\u00dc\u00d9\u0001\u0000\u0000\u0000"+
		"\u00dd\u00e0\u0001\u0000\u0000\u0000\u00de\u00dc\u0001\u0000\u0000\u0000"+
		"\u00de\u00df\u0001\u0000\u0000\u0000\u00df!\u0001\u0000\u0000\u0000\u00e0"+
		"\u00de\u0001\u0000\u0000\u0000\u00e1\u00e2\u0003&\u0013\u0000\u00e2\u00e3"+
		"\u0003$\u0012\u0000\u00e3\u00e4\u0003&\u0013\u0000\u00e4\u00e7\u0001\u0000"+
		"\u0000\u0000\u00e5\u00e7\u0003&\u0013\u0000\u00e6\u00e1\u0001\u0000\u0000"+
		"\u0000\u00e6\u00e5\u0001\u0000\u0000\u0000\u00e7#\u0001\u0000\u0000\u0000"+
		"\u00e8\u00e9\u0007\u0000\u0000\u0000\u00e9%\u0001\u0000\u0000\u0000\u00ea"+
		"\u00eb\u0006\u0013\uffff\uffff\u0000\u00eb\u00ec\u0003*\u0015\u0000\u00ec"+
		"\u00f3\u0001\u0000\u0000\u0000\u00ed\u00ee\n\u0002\u0000\u0000\u00ee\u00ef"+
		"\u0003(\u0014\u0000\u00ef\u00f0\u0003*\u0015\u0000\u00f0\u00f2\u0001\u0000"+
		"\u0000\u0000\u00f1\u00ed\u0001\u0000\u0000\u0000\u00f2\u00f5\u0001\u0000"+
		"\u0000\u0000\u00f3\u00f1\u0001\u0000\u0000\u0000\u00f3\u00f4\u0001\u0000"+
		"\u0000\u0000\u00f4\'\u0001\u0000\u0000\u0000\u00f5\u00f3\u0001\u0000\u0000"+
		"\u0000\u00f6\u00f7\u0007\u0001\u0000\u0000\u00f7)\u0001\u0000\u0000\u0000"+
		"\u00f8\u00f9\u0006\u0015\uffff\uffff\u0000\u00f9\u00fa\u0003.\u0017\u0000"+
		"\u00fa\u0101\u0001\u0000\u0000\u0000\u00fb\u00fc\n\u0002\u0000\u0000\u00fc"+
		"\u00fd\u0003,\u0016\u0000\u00fd\u00fe\u0003.\u0017\u0000\u00fe\u0100\u0001"+
		"\u0000\u0000\u0000\u00ff\u00fb\u0001\u0000\u0000\u0000\u0100\u0103\u0001"+
		"\u0000\u0000\u0000\u0101\u00ff\u0001\u0000\u0000\u0000\u0101\u0102\u0001"+
		"\u0000\u0000\u0000\u0102+\u0001\u0000\u0000\u0000\u0103\u0101\u0001\u0000"+
		"\u0000\u0000\u0104\u0105\u0007\u0002\u0000\u0000\u0105-\u0001\u0000\u0000"+
		"\u0000\u0106\u010b\u00036\u001b\u0000\u0107\u0108\u00030\u0018\u0000\u0108"+
		"\u0109\u0003.\u0017\u0000\u0109\u010b\u0001\u0000\u0000\u0000\u010a\u0106"+
		"\u0001\u0000\u0000\u0000\u010a\u0107\u0001\u0000\u0000\u0000\u010b/\u0001"+
		"\u0000\u0000\u0000\u010c\u010d\u0007\u0003\u0000\u0000\u010d1\u0001\u0000"+
		"\u0000\u0000\u010e\u0115\u0003\u001e\u000f\u0000\u010f\u0110\u0003\u001e"+
		"\u000f\u0000\u0110\u0111\u0005\u0014\u0000\u0000\u0111\u0112\u00034\u001a"+
		"\u0000\u0112\u0115\u0001\u0000\u0000\u0000\u0113\u0115\u0001\u0000\u0000"+
		"\u0000\u0114\u010e\u0001\u0000\u0000\u0000\u0114\u010f\u0001\u0000\u0000"+
		"\u0000\u0114\u0113\u0001\u0000\u0000\u0000\u01153\u0001\u0000\u0000\u0000"+
		"\u0116\u011c\u0003\u001e\u000f\u0000\u0117\u0118\u0003\u001e\u000f\u0000"+
		"\u0118\u0119\u0005\u0014\u0000\u0000\u0119\u011a\u00034\u001a\u0000\u011a"+
		"\u011c\u0001\u0000\u0000\u0000\u011b\u0116\u0001\u0000\u0000\u0000\u011b"+
		"\u0117\u0001\u0000\u0000\u0000\u011c5\u0001\u0000\u0000\u0000\u011d\u011e"+
		"\u0006\u001b\uffff\uffff\u0000\u011e\u011f\u0003:\u001d\u0000\u011f\u0124"+
		"\u0001\u0000\u0000\u0000\u0120\u0121\n\u0002\u0000\u0000\u0121\u0123\u0003"+
		"8\u001c\u0000\u0122\u0120\u0001\u0000\u0000\u0000\u0123\u0126\u0001\u0000"+
		"\u0000\u0000\u0124\u0122\u0001\u0000\u0000\u0000\u0124\u0125\u0001\u0000"+
		"\u0000\u0000\u01257\u0001\u0000\u0000\u0000\u0126\u0124\u0001\u0000\u0000"+
		"\u0000\u0127\u0133\u0005\u0011\u0000\u0000\u0128\u0129\u0005\u0010\u0000"+
		"\u0000\u0129\u0133\u0005/\u0000\u0000\u012a\u012b\u0005\u0017\u0000\u0000"+
		"\u012b\u012c\u00032\u0019\u0000\u012c\u012d\u0005\u0018\u0000\u0000\u012d"+
		"\u0133\u0001\u0000\u0000\u0000\u012e\u012f\u0005\u0019\u0000\u0000\u012f"+
		"\u0130\u0003>\u001f\u0000\u0130\u0131\u0005\u001a\u0000\u0000\u0131\u0133"+
		"\u0001\u0000\u0000\u0000\u0132\u0127\u0001\u0000\u0000\u0000\u0132\u0128"+
		"\u0001\u0000\u0000\u0000\u0132\u012a\u0001\u0000\u0000\u0000\u0132\u012e"+
		"\u0001\u0000\u0000\u0000\u01339\u0001\u0000\u0000\u0000\u0134\u0135\u0005"+
		"\u0017\u0000\u0000\u0135\u0136\u0003>\u001f\u0000\u0136\u0137\u0005\u0018"+
		"\u0000\u0000\u0137\u0141\u0001\u0000\u0000\u0000\u0138\u0139\u0005\u0015"+
		"\u0000\u0000\u0139\u013a\u0003>\u001f\u0000\u013a\u013b\u0005\u0013\u0000"+
		"\u0000\u013b\u013c\u0003B!\u0000\u013c\u013d\u0005\u0016\u0000\u0000\u013d"+
		"\u0141\u0001\u0000\u0000\u0000\u013e\u0141\u0003@ \u0000\u013f\u0141\u0005"+
		"/\u0000\u0000\u0140\u0134\u0001\u0000\u0000\u0000\u0140\u0138\u0001\u0000"+
		"\u0000\u0000\u0140\u013e\u0001\u0000\u0000\u0000\u0140\u013f\u0001\u0000"+
		"\u0000\u0000\u0141;\u0001\u0000\u0000\u0000\u0142\u0143\u0003>\u001f\u0000"+
		"\u0143\u0144\u0005\u0014\u0000\u0000\u0144\u0145\u0003<\u001e\u0000\u0145"+
		"\u0148\u0001\u0000\u0000\u0000\u0146\u0148\u0005\u0018\u0000\u0000\u0147"+
		"\u0142\u0001\u0000\u0000\u0000\u0147\u0146\u0001\u0000\u0000\u0000\u0148"+
		"=\u0001\u0000\u0000\u0000\u0149\u014a\u0003\u001e\u000f\u0000\u014a?\u0001"+
		"\u0000\u0000\u0000\u014b\u0155\u00052\u0000\u0000\u014c\u0155\u00050\u0000"+
		"\u0000\u014d\u0155\u00053\u0000\u0000\u014e\u0155\u0005\u001c\u0000\u0000"+
		"\u014f\u0155\u0005!\u0000\u0000\u0150\u0155\u0005*\u0000\u0000\u0151\u0155"+
		"\u0005&\u0000\u0000\u0152\u0153\u0005(\u0000\u0000\u0153\u0155\u0003B"+
		"!\u0000\u0154\u014b\u0001\u0000\u0000\u0000\u0154\u014c\u0001\u0000\u0000"+
		"\u0000\u0154\u014d\u0001\u0000\u0000\u0000\u0154\u014e\u0001\u0000\u0000"+
		"\u0000\u0154\u014f\u0001\u0000\u0000\u0000\u0154\u0150\u0001\u0000\u0000"+
		"\u0000\u0154\u0151\u0001\u0000\u0000\u0000\u0154\u0152\u0001\u0000\u0000"+
		"\u0000\u0155A\u0001\u0000\u0000\u0000\u0156\u0157\u0003L&\u0000\u0157"+
		"C\u0001\u0000\u0000\u0000\u0158\u0159\u0005/\u0000\u0000\u0159\u015a\u0005"+
		"\u0013\u0000\u0000\u015a\u015b\u0003B!\u0000\u015b\u015c\u0005\u0014\u0000"+
		"\u0000\u015c\u015d\u0003D\"\u0000\u015d\u0162\u0001\u0000\u0000\u0000"+
		"\u015e\u015f\u0005/\u0000\u0000\u015f\u0160\u0005\u0013\u0000\u0000\u0160"+
		"\u0162\u0003B!\u0000\u0161\u0158\u0001\u0000\u0000\u0000\u0161\u015e\u0001"+
		"\u0000\u0000\u0000\u0162E\u0001\u0000\u0000\u0000\u0163\u0164\u0003D\""+
		"\u0000\u0164\u0165\u0005\u0014\u0000\u0000\u0165\u0166\u0003F#\u0000\u0166"+
		"\u016e\u0001\u0000\u0000\u0000\u0167\u0168\u0003B!\u0000\u0168\u0169\u0005"+
		"\u0014\u0000\u0000\u0169\u016a\u0003F#\u0000\u016a\u016e\u0001\u0000\u0000"+
		"\u0000\u016b\u016e\u0003B!\u0000\u016c\u016e\u0003D\"\u0000\u016d\u0163"+
		"\u0001\u0000\u0000\u0000\u016d\u0167\u0001\u0000\u0000\u0000\u016d\u016b"+
		"\u0001\u0000\u0000\u0000\u016d\u016c\u0001\u0000\u0000\u0000\u016eG\u0001"+
		"\u0000\u0000\u0000\u016f\u0172\u0003J%\u0000\u0170\u0172\u0001\u0000\u0000"+
		"\u0000\u0171\u016f\u0001\u0000\u0000\u0000\u0171\u0170\u0001\u0000\u0000"+
		"\u0000\u0172I\u0001\u0000\u0000\u0000\u0173\u0179\u0003B!\u0000\u0174"+
		"\u0175\u0003B!\u0000\u0175\u0176\u0005\u0014\u0000\u0000\u0176\u0177\u0003"+
		"J%\u0000\u0177\u0179\u0001\u0000\u0000\u0000\u0178\u0173\u0001\u0000\u0000"+
		"\u0000\u0178\u0174\u0001\u0000\u0000\u0000\u0179K\u0001\u0000\u0000\u0000"+
		"\u017a\u017b\u0005\u0006\u0000\u0000\u017b\u017c\u0003F#\u0000\u017c\u017d"+
		"\u0005\u0007\u0000\u0000\u017d\u018a\u0001\u0000\u0000\u0000\u017e\u017f"+
		"\u0005\u0015\u0000\u0000\u017f\u0180\u0003F#\u0000\u0180\u0181\u0005\u0016"+
		"\u0000\u0000\u0181\u018a\u0001\u0000\u0000\u0000\u0182\u0183\u0005\u0017"+
		"\u0000\u0000\u0183\u0184\u0003H$\u0000\u0184\u0185\u0005\u0018\u0000\u0000"+
		"\u0185\u0186\u0005\u0013\u0000\u0000\u0186\u0187\u0003B!\u0000\u0187\u018a"+
		"\u0001\u0000\u0000\u0000\u0188\u018a\u0003N\'\u0000\u0189\u017a\u0001"+
		"\u0000\u0000\u0000\u0189\u017e\u0001\u0000\u0000\u0000\u0189\u0182\u0001"+
		"\u0000\u0000\u0000\u0189\u0188\u0001\u0000\u0000\u0000\u018aM\u0001\u0000"+
		"\u0000\u0000\u018b\u018c\u0005\u0019\u0000\u0000\u018c\u018d\u00050\u0000"+
		"\u0000\u018d\u018e\u0005\u001a\u0000\u0000\u018e\u0193\u0003B!\u0000\u018f"+
		"\u0190\u0005\u0011\u0000\u0000\u0190\u0193\u0003B!\u0000\u0191\u0193\u0003"+
		"P(\u0000\u0192\u018b\u0001\u0000\u0000\u0000\u0192\u018f\u0001\u0000\u0000"+
		"\u0000\u0192\u0191\u0001\u0000\u0000\u0000\u0193O\u0001\u0000\u0000\u0000"+
		"\u0194\u0195\u0007\u0004\u0000\u0000\u0195Q\u0001\u0000\u0000\u0000\u001e"+
		"Yjv\u0081\u0089\u0091\u009a\u00a1\u00a7\u00ba\u00c9\u00d3\u00de\u00e6"+
		"\u00f3\u0101\u010a\u0114\u011b\u0124\u0132\u0140\u0147\u0154\u0161\u016d"+
		"\u0171\u0178\u0189\u0192";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}