// Generated from Prev25Parser.g4 by ANTLR 4.13.2


	package compiler.phase.synan;
	
	import java.util.*;
	import compiler.common.report.*;
	import compiler.phase.lexan.*;
	import compiler.phase.abstr.*;

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
		RULE_source = 0, RULE_defs = 1, RULE_def = 2, RULE_myArg = 3, RULE_args1 = 4, 
		RULE_args_ = 5, RULE_args__ = 6, RULE_syn1 = 7, RULE_stmt_ = 8, RULE_stmt_2 = 9, 
		RULE_stmt__ = 10, RULE_stmt = 11, RULE_whileStmt = 12, RULE_ifStmt = 13, 
		RULE_ifElseStmt = 14, RULE_letStmt = 15, RULE_stmtBase = 16, RULE_exprEntry = 17, 
		RULE_expr2nd = 18, RULE_expr3rd = 19, RULE_expr4th = 20, RULE_expr5th = 21, 
		RULE_expr6th = 22, RULE_expr7th = 23, RULE_prim = 24, RULE_exprs_ = 25, 
		RULE_exprs_2 = 26, RULE_exprs__ = 27, RULE_expr = 28, RULE_exprEnd = 29, 
		RULE_idColonType = 30, RULE_typeTuple = 31, RULE_typeTuple_ = 32, RULE_functionCallNoParams = 33, 
		RULE_functionCall = 34, RULE_functionCall_ = 35, RULE_type1 = 36, RULE_syn21 = 37, 
		RULE_type1Over = 38, RULE_atomType = 39;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "defs", "def", "myArg", "args1", "args_", "args__", "syn1", 
			"stmt_", "stmt_2", "stmt__", "stmt", "whileStmt", "ifStmt", "ifElseStmt", 
			"letStmt", "stmtBase", "exprEntry", "expr2nd", "expr3rd", "expr4th", 
			"expr5th", "expr6th", "expr7th", "prim", "exprs_", "exprs_2", "exprs__", 
			"expr", "exprEnd", "idColonType", "typeTuple", "typeTuple_", "functionCallNoParams", 
			"functionCall", "functionCall_", "type1", "syn21", "type1Over", "atomType"
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
		public AST.Nodes<AST.FullDefn> ast;
		public DefsContext c;
		public TerminalNode EOF() { return getToken(Prev25Parser.EOF, 0); }
		public DefsContext defs() {
			return getRuleContext(DefsContext.class,0);
		}
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
			setState(80);
			((SourceContext)_localctx).c = defs();
			setState(81);
			match(EOF);
			((SourceContext)_localctx).ast =  new AST.Nodes<AST.FullDefn>(); _localctx.ast.add(0,((SourceContext)_localctx).c.ast);
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
		public List<AST.FullDefn> ast;
		public DefContext a;
		public DefContext d;
		public DefsContext b;
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
			setState(91);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(84);
				((DefsContext)_localctx).a = def();
				 ((DefsContext)_localctx).ast =  new ArrayList<AST.FullDefn>(); _localctx.ast.addLast(((DefsContext)_localctx).a.ast);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(87);
				((DefsContext)_localctx).d = def();
				setState(88);
				((DefsContext)_localctx).b = defs();
				((DefsContext)_localctx).ast =  ((DefsContext)_localctx).b.ast; _localctx.ast.add(0, ((DefsContext)_localctx).d.ast);
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
		public AST.FullDefn ast;
		public Token TYP;
		public Token IDENTIFIER;
		public Type1Context t;
		public Token VAR;
		public Type1Context type1;
		public Token FUN;
		public Args1Context args1;
		public Syn1Context impl;
		public TerminalNode TYP() { return getToken(Prev25Parser.TYP, 0); }
		public TerminalNode IDENTIFIER() { return getToken(Prev25Parser.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(Prev25Parser.ASSIGN, 0); }
		public Type1Context type1() {
			return getRuleContext(Type1Context.class,0);
		}
		public TerminalNode VAR() { return getToken(Prev25Parser.VAR, 0); }
		public TerminalNode COLON() { return getToken(Prev25Parser.COLON, 0); }
		public TerminalNode FUN() { return getToken(Prev25Parser.FUN, 0); }
		public TerminalNode LPARAN() { return getToken(Prev25Parser.LPARAN, 0); }
		public Args1Context args1() {
			return getRuleContext(Args1Context.class,0);
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
			setState(115);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYP:
				enterOuterAlt(_localctx, 1);
				{
				setState(93);
				((DefContext)_localctx).TYP = match(TYP);
				setState(94);
				((DefContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(95);
				match(ASSIGN);
				setState(96);
				((DefContext)_localctx).t = type1();
				((DefContext)_localctx).ast =  new AST.TypDefn(loc(((DefContext)_localctx).TYP, ((DefContext)_localctx).t.ast), ((DefContext)_localctx).IDENTIFIER.getText(), ((DefContext)_localctx).t.ast);
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				((DefContext)_localctx).VAR = match(VAR);
				setState(100);
				((DefContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(101);
				match(COLON);
				setState(102);
				((DefContext)_localctx).type1 = type1();
				((DefContext)_localctx).ast =  new AST.VarDefn(loc(((DefContext)_localctx).VAR, ((DefContext)_localctx).type1.ast), ((DefContext)_localctx).IDENTIFIER.getText(), ((DefContext)_localctx).type1.ast);
				}
				break;
			case FUN:
				enterOuterAlt(_localctx, 3);
				{
				setState(105);
				((DefContext)_localctx).FUN = match(FUN);
				setState(106);
				((DefContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(107);
				match(LPARAN);
				setState(108);
				((DefContext)_localctx).args1 = args1();
				setState(109);
				match(RPARAN);
				setState(110);
				match(COLON);
				setState(111);
				((DefContext)_localctx).type1 = type1();
				setState(112);
				((DefContext)_localctx).impl = syn1(((DefContext)_localctx).FUN, ((DefContext)_localctx).IDENTIFIER, ((DefContext)_localctx).args1.ast, ((DefContext)_localctx).type1.ast);
				((DefContext)_localctx).ast = ((DefContext)_localctx).impl.ast;
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
	public static class MyArgContext extends ParserRuleContext {
		public AST.ParDefn ast;
		public Token IDENTIFIER;
		public Type1Context type1;
		public TerminalNode IDENTIFIER() { return getToken(Prev25Parser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(Prev25Parser.COLON, 0); }
		public Type1Context type1() {
			return getRuleContext(Type1Context.class,0);
		}
		public MyArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_myArg; }
	}

	public final MyArgContext myArg() throws RecognitionException {
		MyArgContext _localctx = new MyArgContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_myArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			((MyArgContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(118);
			match(COLON);
			setState(119);
			((MyArgContext)_localctx).type1 = type1();
			((MyArgContext)_localctx).ast =  new AST.ParDefn(loc(((MyArgContext)_localctx).IDENTIFIER, ((MyArgContext)_localctx).type1.ast), ((MyArgContext)_localctx).IDENTIFIER.getText(), ((MyArgContext)_localctx).type1.ast);
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
	public static class Args1Context extends ParserRuleContext {
		public List<AST.ParDefn> ast;
		public Args_Context args_;
		public Args_Context args_() {
			return getRuleContext(Args_Context.class,0);
		}
		public Args1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args1; }
	}

	public final Args1Context args1() throws RecognitionException {
		Args1Context _localctx = new Args1Context(_ctx, getState());
		enterRule(_localctx, 8, RULE_args1);
		try {
			setState(126);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				((Args1Context)_localctx).args_ = args_();
				((Args1Context)_localctx).ast =  ((Args1Context)_localctx).args_.ast;
				}
				break;
			case RPARAN:
				enterOuterAlt(_localctx, 2);
				{
				((Args1Context)_localctx).ast = new ArrayList<AST.ParDefn>();
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
		public List<AST.ParDefn> ast;
		public MyArgContext myArg;
		public Args__Context args__;
		public MyArgContext myArg() {
			return getRuleContext(MyArgContext.class,0);
		}
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
		enterRule(_localctx, 10, RULE_args_);
		try {
			setState(135);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(128);
				((Args_Context)_localctx).myArg = myArg();
				setState(129);
				((Args_Context)_localctx).args__ = args__();
				((Args_Context)_localctx).ast =  ((Args_Context)_localctx).args__.ast; _localctx.ast.add(0, ((Args_Context)_localctx).myArg.ast);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(132);
				((Args_Context)_localctx).myArg = myArg();
				((Args_Context)_localctx).ast =  new ArrayList<AST.ParDefn>(); _localctx.ast.addLast(((Args_Context)_localctx).myArg.ast);
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
		public List<AST.ParDefn> ast;
		public Args_Context a;
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public Args_Context args_() {
			return getRuleContext(Args_Context.class,0);
		}
		public Args__Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args__; }
	}

	public final Args__Context args__() throws RecognitionException {
		Args__Context _localctx = new Args__Context(_ctx, getState());
		enterRule(_localctx, 12, RULE_args__);
		try {
			setState(142);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				match(COMMA);
				setState(138);
				((Args__Context)_localctx).a = args_();
				((Args__Context)_localctx).ast =  ((Args__Context)_localctx).a.ast;
				}
				break;
			case RPARAN:
				enterOuterAlt(_localctx, 2);
				{
				((Args__Context)_localctx).ast =  new ArrayList<AST.ParDefn>();
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
	public static class Syn1Context extends ParserRuleContext {
		public Token fun;
		public Token id;
		public List<AST.ParDefn> args;
		public AST.Type type;
		public AST.FunDefn ast;
		public Stmt_Context stmt_;
		public TerminalNode ASSIGN() { return getToken(Prev25Parser.ASSIGN, 0); }
		public Stmt_Context stmt_() {
			return getRuleContext(Stmt_Context.class,0);
		}
		public Syn1Context(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Syn1Context(ParserRuleContext parent, int invokingState, Token fun, Token id, List<AST.ParDefn> args, AST.Type type) {
			super(parent, invokingState);
			this.fun = fun;
			this.id = id;
			this.args = args;
			this.type = type;
		}
		@Override public int getRuleIndex() { return RULE_syn1; }
	}

	public final Syn1Context syn1(Token fun,Token id,List<AST.ParDefn> args,AST.Type type) throws RecognitionException {
		Syn1Context _localctx = new Syn1Context(_ctx, getState(), fun, id, args, type);
		enterRule(_localctx, 14, RULE_syn1);
		try {
			setState(149);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ASSIGN:
				enterOuterAlt(_localctx, 1);
				{
				setState(144);
				match(ASSIGN);
				setState(145);
				((Syn1Context)_localctx).stmt_ = stmt_();
				((Syn1Context)_localctx).ast =  new AST.DefFunDefn(loc(_localctx.fun, ((Syn1Context)_localctx).stmt_.ast.getLast()), _localctx.id.getText(), _localctx.args, _localctx.type, ((Syn1Context)_localctx).stmt_.ast);
				}
				break;
			case EOF:
			case FUN:
			case IN:
			case TYP:
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				((Syn1Context)_localctx).ast =  new AST.ExtFunDefn(loc(_localctx.fun, _localctx.type), _localctx.id.getText(), _localctx.args, _localctx.type);
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
	public static class Stmt_Context extends ParserRuleContext {
		public List<AST.Stmt> ast;
		public Stmt_2Context stmt_2() {
			return getRuleContext(Stmt_2Context.class,0);
		}
		public Stmt_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt_; }
	}

	public final Stmt_Context stmt_() throws RecognitionException {
		Stmt_Context _localctx = new Stmt_Context(_ctx, getState());
		enterRule(_localctx, 16, RULE_stmt_);
		try {
			setState(153);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case EXCL:
			case POW:
			case LBRACE:
			case LPARAN:
			case FALSE:
			case IF:
			case LET:
			case NULL:
			case RETURN:
			case SIZEOF:
			case TRUE:
			case WHILE:
			case IDENTIFIER:
			case CONSTNUM:
			case CONSTCHAR:
			case CONSTSTR:
				enterOuterAlt(_localctx, 1);
				{
				setState(151);
				stmt_2();
				}
				break;
			case EOF:
			case ELSE:
			case END:
			case FUN:
			case IN:
			case TYP:
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				((Stmt_Context)_localctx).ast =  new ArrayList<AST.Stmt>();
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
	public static class Stmt_2Context extends ParserRuleContext {
		public List<AST.Stmt> ast;
		public StmtContext stmt;
		public Stmt__Context stmt__;
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public Stmt__Context stmt__() {
			return getRuleContext(Stmt__Context.class,0);
		}
		public Stmt_2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt_2; }
	}

	public final Stmt_2Context stmt_2() throws RecognitionException {
		Stmt_2Context _localctx = new Stmt_2Context(_ctx, getState());
		enterRule(_localctx, 18, RULE_stmt_2);
		try {
			setState(162);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(155);
				((Stmt_2Context)_localctx).stmt = stmt();
				setState(156);
				((Stmt_2Context)_localctx).stmt__ = stmt__();
				((Stmt_2Context)_localctx).ast = ((Stmt_2Context)_localctx).stmt__.ast; _localctx.ast.add(0, ((Stmt_2Context)_localctx).stmt.ast);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				((Stmt_2Context)_localctx).stmt = stmt();
				((Stmt_2Context)_localctx).ast = new ArrayList<AST.Stmt>(); _localctx.ast.addLast(((Stmt_2Context)_localctx).stmt.ast);
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
		public List<AST.Stmt> ast;
		public Stmt_2Context a;
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public Stmt_2Context stmt_2() {
			return getRuleContext(Stmt_2Context.class,0);
		}
		public Stmt__Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt__; }
	}

	public final Stmt__Context stmt__() throws RecognitionException {
		Stmt__Context _localctx = new Stmt__Context(_ctx, getState());
		enterRule(_localctx, 20, RULE_stmt__);
		try {
			setState(169);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(164);
				match(COMMA);
				setState(165);
				((Stmt__Context)_localctx).a = stmt_2();
				((Stmt__Context)_localctx).ast =  ((Stmt__Context)_localctx).a.ast;
				}
				break;
			case EOF:
			case ELSE:
			case END:
			case FUN:
			case IN:
			case TYP:
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				((Stmt__Context)_localctx).ast =  new ArrayList<AST.Stmt>();
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
		public AST.Stmt ast;
		public WhileStmtContext whileStmt;
		public IfStmtContext ifStmt;
		public LetStmtContext letStmt;
		public StmtBaseContext stmtBase;
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
		enterRule(_localctx, 22, RULE_stmt);
		try {
			setState(183);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WHILE:
				enterOuterAlt(_localctx, 1);
				{
				setState(171);
				((StmtContext)_localctx).whileStmt = whileStmt();
				((StmtContext)_localctx).ast = ((StmtContext)_localctx).whileStmt.ast;
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(174);
				((StmtContext)_localctx).ifStmt = ifStmt();
				((StmtContext)_localctx).ast = ((StmtContext)_localctx).ifStmt.ast;
				}
				break;
			case LET:
				enterOuterAlt(_localctx, 3);
				{
				setState(177);
				((StmtContext)_localctx).letStmt = letStmt();
				((StmtContext)_localctx).ast =  ((StmtContext)_localctx).letStmt.ast;
				}
				break;
			case PLUS:
			case MINUS:
			case EXCL:
			case POW:
			case LBRACE:
			case LPARAN:
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
				setState(180);
				((StmtContext)_localctx).stmtBase = stmtBase();
				((StmtContext)_localctx).ast =  ((StmtContext)_localctx).stmtBase.ast;
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
		public AST.WhileStmt ast;
		public Token WHILE;
		public ExprContext a;
		public Stmt_Context b;
		public Token END;
		public TerminalNode WHILE() { return getToken(Prev25Parser.WHILE, 0); }
		public TerminalNode DO() { return getToken(Prev25Parser.DO, 0); }
		public TerminalNode END() { return getToken(Prev25Parser.END, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Stmt_Context stmt_() {
			return getRuleContext(Stmt_Context.class,0);
		}
		public WhileStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStmt; }
	}

	public final WhileStmtContext whileStmt() throws RecognitionException {
		WhileStmtContext _localctx = new WhileStmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_whileStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			((WhileStmtContext)_localctx).WHILE = match(WHILE);
			setState(186);
			((WhileStmtContext)_localctx).a = expr();
			setState(187);
			match(DO);
			setState(188);
			((WhileStmtContext)_localctx).b = stmt_();
			setState(189);
			((WhileStmtContext)_localctx).END = match(END);
			((WhileStmtContext)_localctx).ast =  new AST.WhileStmt(loc(((WhileStmtContext)_localctx).WHILE, ((WhileStmtContext)_localctx).END), ((WhileStmtContext)_localctx).a.ast, ((WhileStmtContext)_localctx).b.ast);
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
		public AST.Stmt ast;
		public Token IF;
		public ExprContext a;
		public Token THEN;
		public Stmt_Context b;
		public IfElseStmtContext ifElseStmt;
		public TerminalNode IF() { return getToken(Prev25Parser.IF, 0); }
		public TerminalNode THEN() { return getToken(Prev25Parser.THEN, 0); }
		public IfElseStmtContext ifElseStmt() {
			return getRuleContext(IfElseStmtContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Stmt_Context stmt_() {
			return getRuleContext(Stmt_Context.class,0);
		}
		public IfStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStmt; }
	}

	public final IfStmtContext ifStmt() throws RecognitionException {
		IfStmtContext _localctx = new IfStmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_ifStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			((IfStmtContext)_localctx).IF = match(IF);
			setState(193);
			((IfStmtContext)_localctx).a = expr();
			setState(194);
			((IfStmtContext)_localctx).THEN = match(THEN);
			setState(195);
			((IfStmtContext)_localctx).b = stmt_();
			setState(196);
			((IfStmtContext)_localctx).ifElseStmt = ifElseStmt(((IfStmtContext)_localctx).IF, ((IfStmtContext)_localctx).a.ast, ((IfStmtContext)_localctx).THEN, ((IfStmtContext)_localctx).b.ast);
			((IfStmtContext)_localctx).ast =  ((IfStmtContext)_localctx).ifElseStmt.ast;
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
		public Token start;
		public AST.Expr cond;
		public Token endExpr;
		public List<AST.Stmt> statements;
		public AST.Stmt ast;
		public Token END;
		public Stmt_Context stmt_;
		public TerminalNode END() { return getToken(Prev25Parser.END, 0); }
		public TerminalNode ELSE() { return getToken(Prev25Parser.ELSE, 0); }
		public Stmt_Context stmt_() {
			return getRuleContext(Stmt_Context.class,0);
		}
		public IfElseStmtContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public IfElseStmtContext(ParserRuleContext parent, int invokingState, Token start, AST.Expr cond, Token endExpr, List<AST.Stmt> statements) {
			super(parent, invokingState);
			this.start = start;
			this.cond = cond;
			this.endExpr = endExpr;
			this.statements = statements;
		}
		@Override public int getRuleIndex() { return RULE_ifElseStmt; }
	}

	public final IfElseStmtContext ifElseStmt(Token start,AST.Expr cond,Token endExpr,List<AST.Stmt> statements) throws RecognitionException {
		IfElseStmtContext _localctx = new IfElseStmtContext(_ctx, getState(), start, cond, endExpr, statements);
		enterRule(_localctx, 28, RULE_ifElseStmt);
		try {
			setState(206);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case END:
				enterOuterAlt(_localctx, 1);
				{
				setState(199);
				((IfElseStmtContext)_localctx).END = match(END);
				((IfElseStmtContext)_localctx).ast =  new AST.IfThenStmt(loc(start, ((IfElseStmtContext)_localctx).END), _localctx.cond, statements);
				}
				break;
			case ELSE:
				enterOuterAlt(_localctx, 2);
				{
				setState(201);
				match(ELSE);
				setState(202);
				((IfElseStmtContext)_localctx).stmt_ = stmt_();
				setState(203);
				((IfElseStmtContext)_localctx).END = match(END);
				 ((IfElseStmtContext)_localctx).ast =  new AST.IfThenElseStmt(loc(start, ((IfElseStmtContext)_localctx).END), _localctx.cond, statements, ((IfElseStmtContext)_localctx).stmt_.ast);
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
		public AST.LetStmt ast;
		public Token LET;
		public DefsContext defs;
		public Stmt_Context stmt_;
		public Token END;
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
		enterRule(_localctx, 30, RULE_letStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			((LetStmtContext)_localctx).LET = match(LET);
			setState(209);
			((LetStmtContext)_localctx).defs = defs();
			setState(210);
			match(IN);
			setState(211);
			((LetStmtContext)_localctx).stmt_ = stmt_();
			setState(212);
			((LetStmtContext)_localctx).END = match(END);
			 ((LetStmtContext)_localctx).ast =  new AST.LetStmt(loc(((LetStmtContext)_localctx).LET, ((LetStmtContext)_localctx).END), ((LetStmtContext)_localctx).defs.ast, ((LetStmtContext)_localctx).stmt_.ast);
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
		public AST.Stmt ast;
		public ExprContext a;
		public ExprContext aa;
		public ExprContext bb;
		public Token RETURN;
		public ExprContext expr;
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
		enterRule(_localctx, 32, RULE_stmtBase);
		try {
			setState(227);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(215);
				((StmtBaseContext)_localctx).a = expr();
				((StmtBaseContext)_localctx).ast =  new AST.ExprStmt(loc(((StmtBaseContext)_localctx).a.ast), ((StmtBaseContext)_localctx).a.ast);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(218);
				((StmtBaseContext)_localctx).aa = expr();
				setState(219);
				match(ASSIGN);
				setState(220);
				((StmtBaseContext)_localctx).bb = expr();
				((StmtBaseContext)_localctx).ast =  new AST.AssignStmt(loc(((StmtBaseContext)_localctx).aa.ast, ((StmtBaseContext)_localctx).bb.ast), ((StmtBaseContext)_localctx).aa.ast, ((StmtBaseContext)_localctx).bb.ast);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(223);
				((StmtBaseContext)_localctx).RETURN = match(RETURN);
				setState(224);
				((StmtBaseContext)_localctx).expr = expr();
				((StmtBaseContext)_localctx).ast =  new AST.ReturnStmt(loc(((StmtBaseContext)_localctx).RETURN, ((StmtBaseContext)_localctx).expr.ast), ((StmtBaseContext)_localctx).expr.ast);
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
		public AST.Expr ast;
		public ExprEntryContext e;
		public Expr2ndContext expr2nd;
		public Expr2ndContext expr2nd() {
			return getRuleContext(Expr2ndContext.class,0);
		}
		public TerminalNode OR() { return getToken(Prev25Parser.OR, 0); }
		public ExprEntryContext exprEntry() {
			return getRuleContext(ExprEntryContext.class,0);
		}
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
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_exprEntry, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(230);
			((ExprEntryContext)_localctx).expr2nd = expr2nd(0);
			((ExprEntryContext)_localctx).ast =  ((ExprEntryContext)_localctx).expr2nd.ast;
			}
			_ctx.stop = _input.LT(-1);
			setState(240);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprEntryContext(_parentctx, _parentState);
					_localctx.e = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_exprEntry);
					setState(233);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(234);
					match(OR);
					setState(235);
					((ExprEntryContext)_localctx).expr2nd = expr2nd(0);
					((ExprEntryContext)_localctx).ast =  new AST.BinExpr(loc(((ExprEntryContext)_localctx).e.ast, ((ExprEntryContext)_localctx).expr2nd.ast), AST.BinExpr.Oper.OR, ((ExprEntryContext)_localctx).e.ast, ((ExprEntryContext)_localctx).expr2nd.ast);
					}
					} 
				}
				setState(242);
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
	public static class Expr2ndContext extends ParserRuleContext {
		public AST.Expr ast;
		public Expr2ndContext e;
		public Expr3rdContext expr3rd;
		public Expr3rdContext expr3rd() {
			return getRuleContext(Expr3rdContext.class,0);
		}
		public TerminalNode AND() { return getToken(Prev25Parser.AND, 0); }
		public Expr2ndContext expr2nd() {
			return getRuleContext(Expr2ndContext.class,0);
		}
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
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_expr2nd, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(244);
			((Expr2ndContext)_localctx).expr3rd = expr3rd();
			((Expr2ndContext)_localctx).ast =  ((Expr2ndContext)_localctx).expr3rd.ast;
			}
			_ctx.stop = _input.LT(-1);
			setState(254);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr2ndContext(_parentctx, _parentState);
					_localctx.e = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_expr2nd);
					setState(247);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(248);
					match(AND);
					setState(249);
					((Expr2ndContext)_localctx).expr3rd = expr3rd();
					((Expr2ndContext)_localctx).ast =  new AST.BinExpr(loc(((Expr2ndContext)_localctx).e.ast, ((Expr2ndContext)_localctx).expr3rd.ast), AST.BinExpr.Oper.AND, ((Expr2ndContext)_localctx).e.ast, ((Expr2ndContext)_localctx).expr3rd.ast);
					}
					} 
				}
				setState(256);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
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
		public AST.Expr ast;
		public Expr4thContext a;
		public Expr4thContext b;
		public Expr4thContext expr4th;
		public TerminalNode EQU() { return getToken(Prev25Parser.EQU, 0); }
		public List<Expr4thContext> expr4th() {
			return getRuleContexts(Expr4thContext.class);
		}
		public Expr4thContext expr4th(int i) {
			return getRuleContext(Expr4thContext.class,i);
		}
		public TerminalNode NEQ() { return getToken(Prev25Parser.NEQ, 0); }
		public TerminalNode LT() { return getToken(Prev25Parser.LT, 0); }
		public TerminalNode GT() { return getToken(Prev25Parser.GT, 0); }
		public TerminalNode LEQ() { return getToken(Prev25Parser.LEQ, 0); }
		public TerminalNode GEQ() { return getToken(Prev25Parser.GEQ, 0); }
		public Expr3rdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr3rd; }
	}

	public final Expr3rdContext expr3rd() throws RecognitionException {
		Expr3rdContext _localctx = new Expr3rdContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_expr3rd);
		try {
			setState(290);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(257);
				((Expr3rdContext)_localctx).a = expr4th(0);
				setState(258);
				match(EQU);
				setState(259);
				((Expr3rdContext)_localctx).b = expr4th(0);
				((Expr3rdContext)_localctx).ast =  new AST.BinExpr(loc(((Expr3rdContext)_localctx).a.ast, ((Expr3rdContext)_localctx).b.ast), AST.BinExpr.Oper.EQU, ((Expr3rdContext)_localctx).a.ast, ((Expr3rdContext)_localctx).b.ast);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(262);
				((Expr3rdContext)_localctx).a = expr4th(0);
				setState(263);
				match(NEQ);
				setState(264);
				((Expr3rdContext)_localctx).b = expr4th(0);
				((Expr3rdContext)_localctx).ast =  new AST.BinExpr(loc(((Expr3rdContext)_localctx).a.ast, ((Expr3rdContext)_localctx).b.ast), AST.BinExpr.Oper.NEQ, ((Expr3rdContext)_localctx).a.ast, ((Expr3rdContext)_localctx).b.ast);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(267);
				((Expr3rdContext)_localctx).a = expr4th(0);
				setState(268);
				match(LT);
				setState(269);
				((Expr3rdContext)_localctx).b = expr4th(0);
				((Expr3rdContext)_localctx).ast =  new AST.BinExpr(loc(((Expr3rdContext)_localctx).a.ast, ((Expr3rdContext)_localctx).b.ast), AST.BinExpr.Oper.LTH, ((Expr3rdContext)_localctx).a.ast, ((Expr3rdContext)_localctx).b.ast);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(272);
				((Expr3rdContext)_localctx).a = expr4th(0);
				setState(273);
				match(GT);
				setState(274);
				((Expr3rdContext)_localctx).b = expr4th(0);
				((Expr3rdContext)_localctx).ast =  new AST.BinExpr(loc(((Expr3rdContext)_localctx).a.ast, ((Expr3rdContext)_localctx).b.ast), AST.BinExpr.Oper.GTH, ((Expr3rdContext)_localctx).a.ast, ((Expr3rdContext)_localctx).b.ast);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(277);
				((Expr3rdContext)_localctx).a = expr4th(0);
				setState(278);
				match(LEQ);
				setState(279);
				((Expr3rdContext)_localctx).b = expr4th(0);
				((Expr3rdContext)_localctx).ast =  new AST.BinExpr(loc(((Expr3rdContext)_localctx).a.ast, ((Expr3rdContext)_localctx).b.ast), AST.BinExpr.Oper.LEQ, ((Expr3rdContext)_localctx).a.ast, ((Expr3rdContext)_localctx).b.ast);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(282);
				((Expr3rdContext)_localctx).a = expr4th(0);
				setState(283);
				match(GEQ);
				setState(284);
				((Expr3rdContext)_localctx).b = expr4th(0);
				((Expr3rdContext)_localctx).ast =  new AST.BinExpr(loc(((Expr3rdContext)_localctx).a.ast, ((Expr3rdContext)_localctx).b.ast), AST.BinExpr.Oper.GEQ, ((Expr3rdContext)_localctx).a.ast, ((Expr3rdContext)_localctx).b.ast);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(287);
				((Expr3rdContext)_localctx).expr4th = expr4th(0);
				((Expr3rdContext)_localctx).ast =  ((Expr3rdContext)_localctx).expr4th.ast;
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
	public static class Expr4thContext extends ParserRuleContext {
		public AST.Expr ast;
		public Expr4thContext a;
		public Expr5thContext expr5th;
		public Expr5thContext b;
		public Expr5thContext expr5th() {
			return getRuleContext(Expr5thContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(Prev25Parser.PLUS, 0); }
		public Expr4thContext expr4th() {
			return getRuleContext(Expr4thContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(Prev25Parser.MINUS, 0); }
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
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_expr4th, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(293);
			((Expr4thContext)_localctx).expr5th = expr5th(0);
			((Expr4thContext)_localctx).ast =  ((Expr4thContext)_localctx).expr5th.ast;
			}
			_ctx.stop = _input.LT(-1);
			setState(308);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(306);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new Expr4thContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr4th);
						setState(296);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(297);
						match(PLUS);
						setState(298);
						((Expr4thContext)_localctx).b = ((Expr4thContext)_localctx).expr5th = expr5th(0);
						((Expr4thContext)_localctx).ast =  new AST.BinExpr(loc(((Expr4thContext)_localctx).a.ast, ((Expr4thContext)_localctx).b.ast), AST.BinExpr.Oper.ADD, ((Expr4thContext)_localctx).a.ast, ((Expr4thContext)_localctx).b.ast);
						}
						break;
					case 2:
						{
						_localctx = new Expr4thContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr4th);
						setState(301);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(302);
						match(MINUS);
						setState(303);
						((Expr4thContext)_localctx).b = ((Expr4thContext)_localctx).expr5th = expr5th(0);
						((Expr4thContext)_localctx).ast =  new AST.BinExpr(loc(((Expr4thContext)_localctx).a.ast, ((Expr4thContext)_localctx).b.ast), AST.BinExpr.Oper.SUB, ((Expr4thContext)_localctx).a.ast, ((Expr4thContext)_localctx).b.ast);
						}
						break;
					}
					} 
				}
				setState(310);
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
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr5thContext extends ParserRuleContext {
		public AST.Expr ast;
		public Expr5thContext a;
		public Expr6thContext expr6th;
		public Expr6thContext b;
		public Expr6thContext expr6th() {
			return getRuleContext(Expr6thContext.class,0);
		}
		public TerminalNode MUL() { return getToken(Prev25Parser.MUL, 0); }
		public Expr5thContext expr5th() {
			return getRuleContext(Expr5thContext.class,0);
		}
		public TerminalNode DIV() { return getToken(Prev25Parser.DIV, 0); }
		public TerminalNode MOD() { return getToken(Prev25Parser.MOD, 0); }
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
			setState(312);
			((Expr5thContext)_localctx).expr6th = expr6th();
			((Expr5thContext)_localctx).ast =  ((Expr5thContext)_localctx).expr6th.ast;
			}
			_ctx.stop = _input.LT(-1);
			setState(332);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(330);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new Expr5thContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr5th);
						setState(315);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(316);
						match(MUL);
						setState(317);
						((Expr5thContext)_localctx).b = ((Expr5thContext)_localctx).expr6th = expr6th();
						((Expr5thContext)_localctx).ast =  new AST.BinExpr(loc(((Expr5thContext)_localctx).a.ast, ((Expr5thContext)_localctx).b.ast), AST.BinExpr.Oper.MUL, ((Expr5thContext)_localctx).a.ast, ((Expr5thContext)_localctx).b.ast);
						}
						break;
					case 2:
						{
						_localctx = new Expr5thContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr5th);
						setState(320);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(321);
						match(DIV);
						setState(322);
						((Expr5thContext)_localctx).b = ((Expr5thContext)_localctx).expr6th = expr6th();
						((Expr5thContext)_localctx).ast =  new AST.BinExpr(loc(((Expr5thContext)_localctx).a.ast, ((Expr5thContext)_localctx).b.ast), AST.BinExpr.Oper.DIV, ((Expr5thContext)_localctx).a.ast, ((Expr5thContext)_localctx).b.ast);
						}
						break;
					case 3:
						{
						_localctx = new Expr5thContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr5th);
						setState(325);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(326);
						match(MOD);
						setState(327);
						((Expr5thContext)_localctx).b = ((Expr5thContext)_localctx).expr6th = expr6th();
						((Expr5thContext)_localctx).ast =  new AST.BinExpr(loc(((Expr5thContext)_localctx).a.ast, ((Expr5thContext)_localctx).b.ast), AST.BinExpr.Oper.MOD, ((Expr5thContext)_localctx).a.ast, ((Expr5thContext)_localctx).b.ast);
						}
						break;
					}
					} 
				}
				setState(334);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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
	public static class Expr6thContext extends ParserRuleContext {
		public AST.Expr ast;
		public Expr7thContext expr7th;
		public Token PLUS;
		public Expr6thContext expr6th;
		public Token MINUS;
		public Token EXCL;
		public Token POW;
		public Expr7thContext expr7th() {
			return getRuleContext(Expr7thContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(Prev25Parser.PLUS, 0); }
		public Expr6thContext expr6th() {
			return getRuleContext(Expr6thContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(Prev25Parser.MINUS, 0); }
		public TerminalNode EXCL() { return getToken(Prev25Parser.EXCL, 0); }
		public TerminalNode POW() { return getToken(Prev25Parser.POW, 0); }
		public Expr6thContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr6th; }
	}

	public final Expr6thContext expr6th() throws RecognitionException {
		Expr6thContext _localctx = new Expr6thContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_expr6th);
		try {
			setState(354);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
			case LPARAN:
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
				setState(335);
				((Expr6thContext)_localctx).expr7th = expr7th(0);
				((Expr6thContext)_localctx).ast =  ((Expr6thContext)_localctx).expr7th.ast;
				}
				break;
			case PLUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(338);
				((Expr6thContext)_localctx).PLUS = match(PLUS);
				setState(339);
				((Expr6thContext)_localctx).expr6th = expr6th();
				((Expr6thContext)_localctx).ast =  new AST.PfxExpr(loc(((Expr6thContext)_localctx).PLUS, ((Expr6thContext)_localctx).expr6th.ast), AST.PfxExpr.Oper.ADD, ((Expr6thContext)_localctx).expr6th.ast);
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 3);
				{
				setState(342);
				((Expr6thContext)_localctx).MINUS = match(MINUS);
				setState(343);
				((Expr6thContext)_localctx).expr6th = expr6th();
				((Expr6thContext)_localctx).ast =  new AST.PfxExpr(loc(((Expr6thContext)_localctx).MINUS, ((Expr6thContext)_localctx).expr6th.ast), AST.PfxExpr.Oper.SUB, ((Expr6thContext)_localctx).expr6th.ast);
				}
				break;
			case EXCL:
				enterOuterAlt(_localctx, 4);
				{
				setState(346);
				((Expr6thContext)_localctx).EXCL = match(EXCL);
				setState(347);
				((Expr6thContext)_localctx).expr6th = expr6th();
				((Expr6thContext)_localctx).ast =  new AST.PfxExpr(loc(((Expr6thContext)_localctx).EXCL, ((Expr6thContext)_localctx).expr6th.ast), AST.PfxExpr.Oper.NOT, ((Expr6thContext)_localctx).expr6th.ast);
				}
				break;
			case POW:
				enterOuterAlt(_localctx, 5);
				{
				setState(350);
				((Expr6thContext)_localctx).POW = match(POW);
				setState(351);
				((Expr6thContext)_localctx).expr6th = expr6th();
				((Expr6thContext)_localctx).ast =  new AST.PfxExpr(loc(((Expr6thContext)_localctx).POW, ((Expr6thContext)_localctx).expr6th.ast), AST.PfxExpr.Oper.PTR, ((Expr6thContext)_localctx).expr6th.ast);
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
		public AST.Expr ast;
		public Expr7thContext e;
		public PrimContext prim;
		public Token POW;
		public Token IDENTIFIER;
		public Exprs_Context exprs_;
		public Token RPARAN;
		public ExprContext expr;
		public Token RBRCKT;
		public PrimContext prim() {
			return getRuleContext(PrimContext.class,0);
		}
		public TerminalNode POW() { return getToken(Prev25Parser.POW, 0); }
		public Expr7thContext expr7th() {
			return getRuleContext(Expr7thContext.class,0);
		}
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
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_expr7th, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(357);
			((Expr7thContext)_localctx).prim = prim();
			((Expr7thContext)_localctx).ast =  ((Expr7thContext)_localctx).prim.ast;
			}
			_ctx.stop = _input.LT(-1);
			setState(381);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(379);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
					case 1:
						{
						_localctx = new Expr7thContext(_parentctx, _parentState);
						_localctx.e = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr7th);
						setState(360);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(361);
						((Expr7thContext)_localctx).POW = match(POW);
						 ((Expr7thContext)_localctx).ast =  new AST.SfxExpr(loc(((Expr7thContext)_localctx).e.ast, ((Expr7thContext)_localctx).POW), AST.SfxExpr.Oper.PTR, ((Expr7thContext)_localctx).e.ast);
						}
						break;
					case 2:
						{
						_localctx = new Expr7thContext(_parentctx, _parentState);
						_localctx.e = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr7th);
						setState(363);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(364);
						match(DOT);
						setState(365);
						((Expr7thContext)_localctx).IDENTIFIER = match(IDENTIFIER);
						 ((Expr7thContext)_localctx).ast =  new AST.CompExpr(loc(((Expr7thContext)_localctx).e.ast, ((Expr7thContext)_localctx).IDENTIFIER), ((Expr7thContext)_localctx).e.ast, ((Expr7thContext)_localctx).IDENTIFIER.getText());
						}
						break;
					case 3:
						{
						_localctx = new Expr7thContext(_parentctx, _parentState);
						_localctx.e = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr7th);
						setState(367);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(368);
						match(LPARAN);
						setState(369);
						((Expr7thContext)_localctx).exprs_ = exprs_();
						setState(370);
						((Expr7thContext)_localctx).RPARAN = match(RPARAN);
						 ((Expr7thContext)_localctx).ast =  new AST.CallExpr(loc(((Expr7thContext)_localctx).e.ast, ((Expr7thContext)_localctx).RPARAN), ((Expr7thContext)_localctx).e.ast, ((Expr7thContext)_localctx).exprs_.ast);
						}
						break;
					case 4:
						{
						_localctx = new Expr7thContext(_parentctx, _parentState);
						_localctx.e = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr7th);
						setState(373);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(374);
						match(LBRCKT);
						setState(375);
						((Expr7thContext)_localctx).expr = expr();
						setState(376);
						((Expr7thContext)_localctx).RBRCKT = match(RBRCKT);
						 ((Expr7thContext)_localctx).ast =  new AST.ArrExpr(loc(((Expr7thContext)_localctx).e.ast, ((Expr7thContext)_localctx).RBRCKT), ((Expr7thContext)_localctx).e.ast, ((Expr7thContext)_localctx).expr.ast);
						}
						break;
					}
					} 
				}
				setState(383);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
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
	public static class PrimContext extends ParserRuleContext {
		public AST.Expr ast;
		public ExprContext expr;
		public Token LBRACE;
		public Type1Context type1;
		public Token RBRACE;
		public ExprEndContext exprEnd;
		public Token IDENTIFIER;
		public Token SIZEOF;
		public TerminalNode LPARAN() { return getToken(Prev25Parser.LPARAN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPARAN() { return getToken(Prev25Parser.RPARAN, 0); }
		public TerminalNode LBRACE() { return getToken(Prev25Parser.LBRACE, 0); }
		public TerminalNode COLON() { return getToken(Prev25Parser.COLON, 0); }
		public Type1Context type1() {
			return getRuleContext(Type1Context.class,0);
		}
		public TerminalNode RBRACE() { return getToken(Prev25Parser.RBRACE, 0); }
		public ExprEndContext exprEnd() {
			return getRuleContext(ExprEndContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(Prev25Parser.IDENTIFIER, 0); }
		public TerminalNode SIZEOF() { return getToken(Prev25Parser.SIZEOF, 0); }
		public PrimContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prim; }
	}

	public final PrimContext prim() throws RecognitionException {
		PrimContext _localctx = new PrimContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_prim);
		try {
			setState(405);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPARAN:
				enterOuterAlt(_localctx, 1);
				{
				setState(384);
				match(LPARAN);
				setState(385);
				((PrimContext)_localctx).expr = expr();
				setState(386);
				match(RPARAN);
				((PrimContext)_localctx).ast =  ((PrimContext)_localctx).expr.ast;
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(389);
				((PrimContext)_localctx).LBRACE = match(LBRACE);
				setState(390);
				((PrimContext)_localctx).expr = expr();
				setState(391);
				match(COLON);
				setState(392);
				((PrimContext)_localctx).type1 = type1();
				setState(393);
				((PrimContext)_localctx).RBRACE = match(RBRACE);
				((PrimContext)_localctx).ast =  new AST.CastExpr(loc(((PrimContext)_localctx).LBRACE, ((PrimContext)_localctx).RBRACE), ((PrimContext)_localctx).type1.ast, ((PrimContext)_localctx).expr.ast);
				}
				break;
			case FALSE:
			case NULL:
			case TRUE:
			case CONSTNUM:
			case CONSTCHAR:
			case CONSTSTR:
				enterOuterAlt(_localctx, 3);
				{
				setState(396);
				((PrimContext)_localctx).exprEnd = exprEnd();
				((PrimContext)_localctx).ast =  ((PrimContext)_localctx).exprEnd.ast;
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 4);
				{
				setState(399);
				((PrimContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				((PrimContext)_localctx).ast =  new AST.NameExpr(loc(((PrimContext)_localctx).IDENTIFIER), ((PrimContext)_localctx).IDENTIFIER.getText());
				}
				break;
			case SIZEOF:
				enterOuterAlt(_localctx, 5);
				{
				setState(401);
				((PrimContext)_localctx).SIZEOF = match(SIZEOF);
				setState(402);
				((PrimContext)_localctx).type1 = type1();
				((PrimContext)_localctx).ast =  new AST.SizeExpr(loc(((PrimContext)_localctx).SIZEOF, ((PrimContext)_localctx).type1.ast), ((PrimContext)_localctx).type1.ast);
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
	public static class Exprs_Context extends ParserRuleContext {
		public List<AST.Expr> ast;
		public Exprs_2Context exprs_2;
		public Exprs_2Context exprs_2() {
			return getRuleContext(Exprs_2Context.class,0);
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
			setState(411);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case EXCL:
			case POW:
			case LBRACE:
			case LPARAN:
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
				setState(407);
				((Exprs_Context)_localctx).exprs_2 = exprs_2();
				((Exprs_Context)_localctx).ast =  ((Exprs_Context)_localctx).exprs_2.ast;
				}
				break;
			case RPARAN:
				enterOuterAlt(_localctx, 2);
				{
				((Exprs_Context)_localctx).ast =  new ArrayList<AST.Expr>();
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
	public static class Exprs_2Context extends ParserRuleContext {
		public List<AST.Expr> ast;
		public ExprEntryContext exprEntry;
		public Exprs__Context exprs__;
		public ExprEntryContext exprEntry() {
			return getRuleContext(ExprEntryContext.class,0);
		}
		public Exprs__Context exprs__() {
			return getRuleContext(Exprs__Context.class,0);
		}
		public Exprs_2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprs_2; }
	}

	public final Exprs_2Context exprs_2() throws RecognitionException {
		Exprs_2Context _localctx = new Exprs_2Context(_ctx, getState());
		enterRule(_localctx, 52, RULE_exprs_2);
		try {
			setState(420);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(413);
				((Exprs_2Context)_localctx).exprEntry = exprEntry(0);
				setState(414);
				((Exprs_2Context)_localctx).exprs__ = exprs__();
				((Exprs_2Context)_localctx).ast =  ((Exprs_2Context)_localctx).exprs__.ast; _localctx.ast.add(0, ((Exprs_2Context)_localctx).exprEntry.ast);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(417);
				((Exprs_2Context)_localctx).exprEntry = exprEntry(0);
				((Exprs_2Context)_localctx).ast =  new ArrayList<AST.Expr>(); _localctx.ast.add(0,((Exprs_2Context)_localctx).exprEntry.ast);
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
		public List<AST.Expr> ast;
		public Exprs_Context exprs_;
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public Exprs_Context exprs_() {
			return getRuleContext(Exprs_Context.class,0);
		}
		public Exprs__Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprs__; }
	}

	public final Exprs__Context exprs__() throws RecognitionException {
		Exprs__Context _localctx = new Exprs__Context(_ctx, getState());
		enterRule(_localctx, 54, RULE_exprs__);
		try {
			setState(427);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(422);
				match(COMMA);
				setState(423);
				((Exprs__Context)_localctx).exprs_ = exprs_();
				((Exprs__Context)_localctx).ast =  ((Exprs__Context)_localctx).exprs_.ast;
				}
				break;
			case RPARAN:
				enterOuterAlt(_localctx, 2);
				{
				((Exprs__Context)_localctx).ast =  new ArrayList<AST.Expr>();
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
		public AST.Expr ast;
		public ExprEntryContext exprEntry;
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
		enterRule(_localctx, 56, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
			((ExprContext)_localctx).exprEntry = exprEntry(0);
			((ExprContext)_localctx).ast =  ((ExprContext)_localctx).exprEntry.ast;
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
		public AST.Expr ast;
		public Token CONSTCHAR;
		public Token CONSTNUM;
		public Token CONSTSTR;
		public Token FALSE;
		public Token TRUE;
		public Token NULL;
		public TerminalNode CONSTCHAR() { return getToken(Prev25Parser.CONSTCHAR, 0); }
		public TerminalNode CONSTNUM() { return getToken(Prev25Parser.CONSTNUM, 0); }
		public TerminalNode CONSTSTR() { return getToken(Prev25Parser.CONSTSTR, 0); }
		public TerminalNode FALSE() { return getToken(Prev25Parser.FALSE, 0); }
		public TerminalNode TRUE() { return getToken(Prev25Parser.TRUE, 0); }
		public TerminalNode NULL() { return getToken(Prev25Parser.NULL, 0); }
		public ExprEndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprEnd; }
	}

	public final ExprEndContext exprEnd() throws RecognitionException {
		ExprEndContext _localctx = new ExprEndContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_exprEnd);
		try {
			setState(444);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONSTCHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(432);
				((ExprEndContext)_localctx).CONSTCHAR = match(CONSTCHAR);
				((ExprEndContext)_localctx).ast =  new AST.AtomExpr(loc(((ExprEndContext)_localctx).CONSTCHAR), AST.AtomExpr.Type.CHAR, ((ExprEndContext)_localctx).CONSTCHAR.getText());
				}
				break;
			case CONSTNUM:
				enterOuterAlt(_localctx, 2);
				{
				setState(434);
				((ExprEndContext)_localctx).CONSTNUM = match(CONSTNUM);
				((ExprEndContext)_localctx).ast =  new AST.AtomExpr(loc(((ExprEndContext)_localctx).CONSTNUM), AST.AtomExpr.Type.INT, ((ExprEndContext)_localctx).CONSTNUM.getText());
				}
				break;
			case CONSTSTR:
				enterOuterAlt(_localctx, 3);
				{
				setState(436);
				((ExprEndContext)_localctx).CONSTSTR = match(CONSTSTR);
				((ExprEndContext)_localctx).ast =  new AST.AtomExpr(loc(((ExprEndContext)_localctx).CONSTSTR), AST.AtomExpr.Type.STR, ((ExprEndContext)_localctx).CONSTSTR.getText());
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 4);
				{
				setState(438);
				((ExprEndContext)_localctx).FALSE = match(FALSE);
				((ExprEndContext)_localctx).ast =  new AST.AtomExpr(loc(((ExprEndContext)_localctx).FALSE), AST.AtomExpr.Type.CHAR, ((ExprEndContext)_localctx).FALSE.getText());
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 5);
				{
				setState(440);
				((ExprEndContext)_localctx).TRUE = match(TRUE);
				((ExprEndContext)_localctx).ast =  new AST.AtomExpr(loc(((ExprEndContext)_localctx).TRUE), AST.AtomExpr.Type.CHAR, ((ExprEndContext)_localctx).TRUE.getText());
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 6);
				{
				setState(442);
				((ExprEndContext)_localctx).NULL = match(NULL);
				((ExprEndContext)_localctx).ast =  new AST.AtomExpr(loc(((ExprEndContext)_localctx).NULL), AST.AtomExpr.Type.PTR, "0");
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
	public static class IdColonTypeContext extends ParserRuleContext {
		public AST.CompDefn ast;
		public Token IDENTIFIER;
		public Type1Context a;
		public TerminalNode IDENTIFIER() { return getToken(Prev25Parser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(Prev25Parser.COLON, 0); }
		public Type1Context type1() {
			return getRuleContext(Type1Context.class,0);
		}
		public IdColonTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idColonType; }
	}

	public final IdColonTypeContext idColonType() throws RecognitionException {
		IdColonTypeContext _localctx = new IdColonTypeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_idColonType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(446);
			((IdColonTypeContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(447);
			match(COLON);
			setState(448);
			((IdColonTypeContext)_localctx).a = type1();
			((IdColonTypeContext)_localctx).ast =  new AST.CompDefn(loc(((IdColonTypeContext)_localctx).IDENTIFIER, ((IdColonTypeContext)_localctx).a.ast), ((IdColonTypeContext)_localctx).IDENTIFIER.getText(), ((IdColonTypeContext)_localctx).a.ast);
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
	public static class TypeTupleContext extends ParserRuleContext {
		public List<AST.CompDefn> ast;
		public IdColonTypeContext a;
		public TypeTuple_Context b;
		public IdColonTypeContext idColonType() {
			return getRuleContext(IdColonTypeContext.class,0);
		}
		public TypeTuple_Context typeTuple_() {
			return getRuleContext(TypeTuple_Context.class,0);
		}
		public TypeTupleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeTuple; }
	}

	public final TypeTupleContext typeTuple() throws RecognitionException {
		TypeTupleContext _localctx = new TypeTupleContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_typeTuple);
		try {
			setState(458);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(451);
				((TypeTupleContext)_localctx).a = idColonType();
				setState(452);
				((TypeTupleContext)_localctx).b = typeTuple_();
				((TypeTupleContext)_localctx).ast =  ((TypeTupleContext)_localctx).b.ast; _localctx.ast.add(0, ((TypeTupleContext)_localctx).a.ast);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(455);
				((TypeTupleContext)_localctx).a = idColonType();
				((TypeTupleContext)_localctx).ast =  new ArrayList<AST.CompDefn>(); _localctx.ast.add(0, ((TypeTupleContext)_localctx).a.ast);
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
	public static class TypeTuple_Context extends ParserRuleContext {
		public List<AST.CompDefn> ast;
		public TypeTupleContext a;
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public TypeTupleContext typeTuple() {
			return getRuleContext(TypeTupleContext.class,0);
		}
		public TypeTuple_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeTuple_; }
	}

	public final TypeTuple_Context typeTuple_() throws RecognitionException {
		TypeTuple_Context _localctx = new TypeTuple_Context(_ctx, getState());
		enterRule(_localctx, 64, RULE_typeTuple_);
		try {
			setState(465);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(460);
				match(COMMA);
				setState(461);
				((TypeTuple_Context)_localctx).a = typeTuple();
				((TypeTuple_Context)_localctx).ast =  ((TypeTuple_Context)_localctx).a.ast;
				}
				break;
			case GT:
			case RBRACE:
				enterOuterAlt(_localctx, 2);
				{
				((TypeTuple_Context)_localctx).ast =  new ArrayList<AST.CompDefn>();
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
	public static class FunctionCallNoParamsContext extends ParserRuleContext {
		public List<AST.Type> ast;
		public FunctionCallContext a;
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public FunctionCallNoParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCallNoParams; }
	}

	public final FunctionCallNoParamsContext functionCallNoParams() throws RecognitionException {
		FunctionCallNoParamsContext _localctx = new FunctionCallNoParamsContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_functionCallNoParams);
		try {
			setState(471);
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
				setState(467);
				((FunctionCallNoParamsContext)_localctx).a = functionCall();
				((FunctionCallNoParamsContext)_localctx).ast =  ((FunctionCallNoParamsContext)_localctx).a.ast;
				}
				break;
			case RPARAN:
				enterOuterAlt(_localctx, 2);
				{
				((FunctionCallNoParamsContext)_localctx).ast =  new ArrayList<AST.Type>();
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
		public List<AST.Type> ast;
		public Type1Context a;
		public FunctionCall_Context b;
		public Type1Context type1() {
			return getRuleContext(Type1Context.class,0);
		}
		public FunctionCall_Context functionCall_() {
			return getRuleContext(FunctionCall_Context.class,0);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_functionCall);
		try {
			setState(480);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(473);
				((FunctionCallContext)_localctx).a = type1();
				((FunctionCallContext)_localctx).ast =  new ArrayList<AST.Type>(); _localctx.ast.add(0, ((FunctionCallContext)_localctx).a.ast);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(476);
				((FunctionCallContext)_localctx).a = type1();
				setState(477);
				((FunctionCallContext)_localctx).b = functionCall_();
				((FunctionCallContext)_localctx).ast =  ((FunctionCallContext)_localctx).b.ast; _localctx.ast.add(0, ((FunctionCallContext)_localctx).a.ast);
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
	public static class FunctionCall_Context extends ParserRuleContext {
		public List<AST.Type> ast;
		public FunctionCallContext a;
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public FunctionCall_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall_; }
	}

	public final FunctionCall_Context functionCall_() throws RecognitionException {
		FunctionCall_Context _localctx = new FunctionCall_Context(_ctx, getState());
		enterRule(_localctx, 70, RULE_functionCall_);
		try {
			setState(487);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(482);
				match(COMMA);
				setState(483);
				((FunctionCall_Context)_localctx).a = functionCall();
				((FunctionCall_Context)_localctx).ast =  ((FunctionCall_Context)_localctx).a.ast;
				}
				break;
			case RPARAN:
				enterOuterAlt(_localctx, 2);
				{
				((FunctionCall_Context)_localctx).ast =  new ArrayList<AST.Type>();
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
	public static class Type1Context extends ParserRuleContext {
		public AST.Type ast;
		public Syn21Context a;
		public Syn21Context syn21() {
			return getRuleContext(Syn21Context.class,0);
		}
		public Type1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type1; }
	}

	public final Type1Context type1() throws RecognitionException {
		Type1Context _localctx = new Type1Context(_ctx, getState());
		enterRule(_localctx, 72, RULE_type1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(489);
			((Type1Context)_localctx).a = syn21();
			((Type1Context)_localctx).ast =  ((Type1Context)_localctx).a.ast;
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
		public AST.Type ast;
		public Token LT;
		public TypeTupleContext a;
		public Token GT;
		public Token LBRACE;
		public TypeTupleContext b;
		public Token RBRACE;
		public Token LPARAN;
		public FunctionCallNoParamsContext c;
		public Type1Context d;
		public Type1OverContext e;
		public TerminalNode LT() { return getToken(Prev25Parser.LT, 0); }
		public TerminalNode GT() { return getToken(Prev25Parser.GT, 0); }
		public TypeTupleContext typeTuple() {
			return getRuleContext(TypeTupleContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(Prev25Parser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(Prev25Parser.RBRACE, 0); }
		public TerminalNode LPARAN() { return getToken(Prev25Parser.LPARAN, 0); }
		public TerminalNode RPARAN() { return getToken(Prev25Parser.RPARAN, 0); }
		public TerminalNode COLON() { return getToken(Prev25Parser.COLON, 0); }
		public FunctionCallNoParamsContext functionCallNoParams() {
			return getRuleContext(FunctionCallNoParamsContext.class,0);
		}
		public Type1Context type1() {
			return getRuleContext(Type1Context.class,0);
		}
		public Type1OverContext type1Over() {
			return getRuleContext(Type1OverContext.class,0);
		}
		public Syn21Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_syn21; }
	}

	public final Syn21Context syn21() throws RecognitionException {
		Syn21Context _localctx = new Syn21Context(_ctx, getState());
		enterRule(_localctx, 74, RULE_syn21);
		try {
			setState(512);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LT:
				enterOuterAlt(_localctx, 1);
				{
				setState(492);
				((Syn21Context)_localctx).LT = match(LT);
				setState(493);
				((Syn21Context)_localctx).a = typeTuple();
				setState(494);
				((Syn21Context)_localctx).GT = match(GT);
				((Syn21Context)_localctx).ast =  new AST.StrType(loc(((Syn21Context)_localctx).LT, ((Syn21Context)_localctx).GT), ((Syn21Context)_localctx).a.ast);
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(497);
				((Syn21Context)_localctx).LBRACE = match(LBRACE);
				setState(498);
				((Syn21Context)_localctx).b = typeTuple();
				setState(499);
				((Syn21Context)_localctx).RBRACE = match(RBRACE);
				((Syn21Context)_localctx).ast =  new AST.UniType(loc(((Syn21Context)_localctx).LBRACE, ((Syn21Context)_localctx).RBRACE), ((Syn21Context)_localctx).b.ast);
				}
				break;
			case LPARAN:
				enterOuterAlt(_localctx, 3);
				{
				setState(502);
				((Syn21Context)_localctx).LPARAN = match(LPARAN);
				setState(503);
				((Syn21Context)_localctx).c = functionCallNoParams();
				setState(504);
				match(RPARAN);
				setState(505);
				match(COLON);
				setState(506);
				((Syn21Context)_localctx).d = type1();
				((Syn21Context)_localctx).ast =  new AST.FunType(loc(((Syn21Context)_localctx).LPARAN, ((Syn21Context)_localctx).d.ast), ((Syn21Context)_localctx).c.ast, ((Syn21Context)_localctx).d.ast);
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
				setState(509);
				((Syn21Context)_localctx).e = type1Over();
				((Syn21Context)_localctx).ast =  ((Syn21Context)_localctx).e.ast;
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
	public static class Type1OverContext extends ParserRuleContext {
		public AST.Type ast;
		public Token LBRCKT;
		public Token CONSTNUM;
		public Type1Context a;
		public Token POW;
		public AtomTypeContext ab;
		public TerminalNode LBRCKT() { return getToken(Prev25Parser.LBRCKT, 0); }
		public TerminalNode CONSTNUM() { return getToken(Prev25Parser.CONSTNUM, 0); }
		public TerminalNode RBRCKT() { return getToken(Prev25Parser.RBRCKT, 0); }
		public Type1Context type1() {
			return getRuleContext(Type1Context.class,0);
		}
		public TerminalNode POW() { return getToken(Prev25Parser.POW, 0); }
		public AtomTypeContext atomType() {
			return getRuleContext(AtomTypeContext.class,0);
		}
		public Type1OverContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type1Over; }
	}

	public final Type1OverContext type1Over() throws RecognitionException {
		Type1OverContext _localctx = new Type1OverContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_type1Over);
		try {
			setState(527);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRCKT:
				enterOuterAlt(_localctx, 1);
				{
				setState(514);
				((Type1OverContext)_localctx).LBRCKT = match(LBRCKT);
				setState(515);
				((Type1OverContext)_localctx).CONSTNUM = match(CONSTNUM);
				setState(516);
				match(RBRCKT);
				setState(517);
				((Type1OverContext)_localctx).a = type1();
				((Type1OverContext)_localctx).ast =  new AST.ArrType(loc(((Type1OverContext)_localctx).LBRCKT, ((Type1OverContext)_localctx).a.ast), ((Type1OverContext)_localctx).a.ast, ((Type1OverContext)_localctx).CONSTNUM.getText());
				}
				break;
			case POW:
				enterOuterAlt(_localctx, 2);
				{
				setState(520);
				((Type1OverContext)_localctx).POW = match(POW);
				setState(521);
				((Type1OverContext)_localctx).a = type1();
				((Type1OverContext)_localctx).ast =  new AST.PtrType(loc(((Type1OverContext)_localctx).POW, ((Type1OverContext)_localctx).a.ast), ((Type1OverContext)_localctx).a.ast);
				}
				break;
			case INT:
			case BOOL:
			case CHAR:
			case VOID:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 3);
				{
				setState(524);
				((Type1OverContext)_localctx).ab = atomType();
				((Type1OverContext)_localctx).ast =  ((Type1OverContext)_localctx).ab.ast;
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
	public static class AtomTypeContext extends ParserRuleContext {
		public AST.Type ast;
		public Token BOOL;
		public Token INT;
		public Token CHAR;
		public Token VOID;
		public Token IDENTIFIER;
		public TerminalNode BOOL() { return getToken(Prev25Parser.BOOL, 0); }
		public TerminalNode INT() { return getToken(Prev25Parser.INT, 0); }
		public TerminalNode CHAR() { return getToken(Prev25Parser.CHAR, 0); }
		public TerminalNode VOID() { return getToken(Prev25Parser.VOID, 0); }
		public TerminalNode IDENTIFIER() { return getToken(Prev25Parser.IDENTIFIER, 0); }
		public AtomTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomType; }
	}

	public final AtomTypeContext atomType() throws RecognitionException {
		AtomTypeContext _localctx = new AtomTypeContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_atomType);
		try {
			setState(539);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
				enterOuterAlt(_localctx, 1);
				{
				setState(529);
				((AtomTypeContext)_localctx).BOOL = match(BOOL);
				((AtomTypeContext)_localctx).ast =  new AST.AtomType(loc(((AtomTypeContext)_localctx).BOOL), AST.AtomType.Type.BOOL);
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(531);
				((AtomTypeContext)_localctx).INT = match(INT);
				((AtomTypeContext)_localctx).ast =  new AST.AtomType(loc(((AtomTypeContext)_localctx).INT), AST.AtomType.Type.INT); 
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(533);
				((AtomTypeContext)_localctx).CHAR = match(CHAR);
				((AtomTypeContext)_localctx).ast =  new AST.AtomType(loc(((AtomTypeContext)_localctx).CHAR), AST.AtomType.Type.CHAR); 
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 4);
				{
				setState(535);
				((AtomTypeContext)_localctx).VOID = match(VOID);
				((AtomTypeContext)_localctx).ast =  new AST.AtomType(loc(((AtomTypeContext)_localctx).VOID), AST.AtomType.Type.VOID); 
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 5);
				{
				setState(537);
				((AtomTypeContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				((AtomTypeContext)_localctx).ast =  new AST.NameType(loc(((AtomTypeContext)_localctx).IDENTIFIER), ((AtomTypeContext)_localctx).IDENTIFIER.getText());
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 17:
			return exprEntry_sempred((ExprEntryContext)_localctx, predIndex);
		case 18:
			return expr2nd_sempred((Expr2ndContext)_localctx, predIndex);
		case 20:
			return expr4th_sempred((Expr4thContext)_localctx, predIndex);
		case 21:
			return expr5th_sempred((Expr5thContext)_localctx, predIndex);
		case 23:
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
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr5th_sempred(Expr5thContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 4);
		case 5:
			return precpred(_ctx, 3);
		case 6:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr7th_sempred(Expr7thContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7:
			return precpred(_ctx, 5);
		case 8:
			return precpred(_ctx, 4);
		case 9:
			return precpred(_ctx, 3);
		case 10:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00014\u021e\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001\\\b"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002t\b"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u007f\b\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0003\u0005\u0088\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0003\u0006\u008f\b\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0096\b\u0007\u0001\b\u0001"+
		"\b\u0003\b\u009a\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0003\t\u00a3\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00aa"+
		"\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0003\u000b\u00b8\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0003\u000e\u00cf\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00e4\b\u0010"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u00ef\b\u0011\n\u0011"+
		"\f\u0011\u00f2\t\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012"+
		"\u00fd\b\u0012\n\u0012\f\u0012\u0100\t\u0012\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0003\u0013\u0123\b\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u0133"+
		"\b\u0014\n\u0014\f\u0014\u0136\t\u0014\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u014b\b\u0015"+
		"\n\u0015\f\u0015\u014e\t\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0163\b\u0016\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u017c"+
		"\b\u0017\n\u0017\f\u0017\u017f\t\u0017\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0003\u0018\u0196\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0003\u0019\u019c\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u01a5\b\u001a\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u01ac\b\u001b"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0003\u001d\u01bd\b\u001d\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0003\u001f"+
		"\u01cb\b\u001f\u0001 \u0001 \u0001 \u0001 \u0001 \u0003 \u01d2\b \u0001"+
		"!\u0001!\u0001!\u0001!\u0003!\u01d8\b!\u0001\"\u0001\"\u0001\"\u0001\""+
		"\u0001\"\u0001\"\u0001\"\u0003\"\u01e1\b\"\u0001#\u0001#\u0001#\u0001"+
		"#\u0001#\u0003#\u01e8\b#\u0001$\u0001$\u0001$\u0001%\u0001%\u0001%\u0001"+
		"%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001"+
		"%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0003%\u0201\b%\u0001&\u0001"+
		"&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001"+
		"&\u0001&\u0003&\u0210\b&\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0001\'\u0001\'\u0001\'\u0003\'\u021c\b\'\u0001\'\u0000\u0005"+
		"\"$(*.(\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLN\u0000\u0000\u0234\u0000P\u0001"+
		"\u0000\u0000\u0000\u0002[\u0001\u0000\u0000\u0000\u0004s\u0001\u0000\u0000"+
		"\u0000\u0006u\u0001\u0000\u0000\u0000\b~\u0001\u0000\u0000\u0000\n\u0087"+
		"\u0001\u0000\u0000\u0000\f\u008e\u0001\u0000\u0000\u0000\u000e\u0095\u0001"+
		"\u0000\u0000\u0000\u0010\u0099\u0001\u0000\u0000\u0000\u0012\u00a2\u0001"+
		"\u0000\u0000\u0000\u0014\u00a9\u0001\u0000\u0000\u0000\u0016\u00b7\u0001"+
		"\u0000\u0000\u0000\u0018\u00b9\u0001\u0000\u0000\u0000\u001a\u00c0\u0001"+
		"\u0000\u0000\u0000\u001c\u00ce\u0001\u0000\u0000\u0000\u001e\u00d0\u0001"+
		"\u0000\u0000\u0000 \u00e3\u0001\u0000\u0000\u0000\"\u00e5\u0001\u0000"+
		"\u0000\u0000$\u00f3\u0001\u0000\u0000\u0000&\u0122\u0001\u0000\u0000\u0000"+
		"(\u0124\u0001\u0000\u0000\u0000*\u0137\u0001\u0000\u0000\u0000,\u0162"+
		"\u0001\u0000\u0000\u0000.\u0164\u0001\u0000\u0000\u00000\u0195\u0001\u0000"+
		"\u0000\u00002\u019b\u0001\u0000\u0000\u00004\u01a4\u0001\u0000\u0000\u0000"+
		"6\u01ab\u0001\u0000\u0000\u00008\u01ad\u0001\u0000\u0000\u0000:\u01bc"+
		"\u0001\u0000\u0000\u0000<\u01be\u0001\u0000\u0000\u0000>\u01ca\u0001\u0000"+
		"\u0000\u0000@\u01d1\u0001\u0000\u0000\u0000B\u01d7\u0001\u0000\u0000\u0000"+
		"D\u01e0\u0001\u0000\u0000\u0000F\u01e7\u0001\u0000\u0000\u0000H\u01e9"+
		"\u0001\u0000\u0000\u0000J\u0200\u0001\u0000\u0000\u0000L\u020f\u0001\u0000"+
		"\u0000\u0000N\u021b\u0001\u0000\u0000\u0000PQ\u0003\u0002\u0001\u0000"+
		"QR\u0005\u0000\u0000\u0001RS\u0006\u0000\uffff\uffff\u0000S\u0001\u0001"+
		"\u0000\u0000\u0000TU\u0003\u0004\u0002\u0000UV\u0006\u0001\uffff\uffff"+
		"\u0000V\\\u0001\u0000\u0000\u0000WX\u0003\u0004\u0002\u0000XY\u0003\u0002"+
		"\u0001\u0000YZ\u0006\u0001\uffff\uffff\u0000Z\\\u0001\u0000\u0000\u0000"+
		"[T\u0001\u0000\u0000\u0000[W\u0001\u0000\u0000\u0000\\\u0003\u0001\u0000"+
		"\u0000\u0000]^\u0005+\u0000\u0000^_\u0005/\u0000\u0000_`\u0005\u0012\u0000"+
		"\u0000`a\u0003H$\u0000ab\u0006\u0002\uffff\uffff\u0000bt\u0001\u0000\u0000"+
		"\u0000cd\u0005,\u0000\u0000de\u0005/\u0000\u0000ef\u0005\u0013\u0000\u0000"+
		"fg\u0003H$\u0000gh\u0006\u0002\uffff\uffff\u0000ht\u0001\u0000\u0000\u0000"+
		"ij\u0005\"\u0000\u0000jk\u0005/\u0000\u0000kl\u0005\u0017\u0000\u0000"+
		"lm\u0003\b\u0004\u0000mn\u0005\u0018\u0000\u0000no\u0005\u0013\u0000\u0000"+
		"op\u0003H$\u0000pq\u0003\u000e\u0007\u0000qr\u0006\u0002\uffff\uffff\u0000"+
		"rt\u0001\u0000\u0000\u0000s]\u0001\u0000\u0000\u0000sc\u0001\u0000\u0000"+
		"\u0000si\u0001\u0000\u0000\u0000t\u0005\u0001\u0000\u0000\u0000uv\u0005"+
		"/\u0000\u0000vw\u0005\u0013\u0000\u0000wx\u0003H$\u0000xy\u0006\u0003"+
		"\uffff\uffff\u0000y\u0007\u0001\u0000\u0000\u0000z{\u0003\n\u0005\u0000"+
		"{|\u0006\u0004\uffff\uffff\u0000|\u007f\u0001\u0000\u0000\u0000}\u007f"+
		"\u0006\u0004\uffff\uffff\u0000~z\u0001\u0000\u0000\u0000~}\u0001\u0000"+
		"\u0000\u0000\u007f\t\u0001\u0000\u0000\u0000\u0080\u0081\u0003\u0006\u0003"+
		"\u0000\u0081\u0082\u0003\f\u0006\u0000\u0082\u0083\u0006\u0005\uffff\uffff"+
		"\u0000\u0083\u0088\u0001\u0000\u0000\u0000\u0084\u0085\u0003\u0006\u0003"+
		"\u0000\u0085\u0086\u0006\u0005\uffff\uffff\u0000\u0086\u0088\u0001\u0000"+
		"\u0000\u0000\u0087\u0080\u0001\u0000\u0000\u0000\u0087\u0084\u0001\u0000"+
		"\u0000\u0000\u0088\u000b\u0001\u0000\u0000\u0000\u0089\u008a\u0005\u0014"+
		"\u0000\u0000\u008a\u008b\u0003\n\u0005\u0000\u008b\u008c\u0006\u0006\uffff"+
		"\uffff\u0000\u008c\u008f\u0001\u0000\u0000\u0000\u008d\u008f\u0006\u0006"+
		"\uffff\uffff\u0000\u008e\u0089\u0001\u0000\u0000\u0000\u008e\u008d\u0001"+
		"\u0000\u0000\u0000\u008f\r\u0001\u0000\u0000\u0000\u0090\u0091\u0005\u0012"+
		"\u0000\u0000\u0091\u0092\u0003\u0010\b\u0000\u0092\u0093\u0006\u0007\uffff"+
		"\uffff\u0000\u0093\u0096\u0001\u0000\u0000\u0000\u0094\u0096\u0006\u0007"+
		"\uffff\uffff\u0000\u0095\u0090\u0001\u0000\u0000\u0000\u0095\u0094\u0001"+
		"\u0000\u0000\u0000\u0096\u000f\u0001\u0000\u0000\u0000\u0097\u009a\u0003"+
		"\u0012\t\u0000\u0098\u009a\u0006\b\uffff\uffff\u0000\u0099\u0097\u0001"+
		"\u0000\u0000\u0000\u0099\u0098\u0001\u0000\u0000\u0000\u009a\u0011\u0001"+
		"\u0000\u0000\u0000\u009b\u009c\u0003\u0016\u000b\u0000\u009c\u009d\u0003"+
		"\u0014\n\u0000\u009d\u009e\u0006\t\uffff\uffff\u0000\u009e\u00a3\u0001"+
		"\u0000\u0000\u0000\u009f\u00a0\u0003\u0016\u000b\u0000\u00a0\u00a1\u0006"+
		"\t\uffff\uffff\u0000\u00a1\u00a3\u0001\u0000\u0000\u0000\u00a2\u009b\u0001"+
		"\u0000\u0000\u0000\u00a2\u009f\u0001\u0000\u0000\u0000\u00a3\u0013\u0001"+
		"\u0000\u0000\u0000\u00a4\u00a5\u0005\u0014\u0000\u0000\u00a5\u00a6\u0003"+
		"\u0012\t\u0000\u00a6\u00a7\u0006\n\uffff\uffff\u0000\u00a7\u00aa\u0001"+
		"\u0000\u0000\u0000\u00a8\u00aa\u0006\n\uffff\uffff\u0000\u00a9\u00a4\u0001"+
		"\u0000\u0000\u0000\u00a9\u00a8\u0001\u0000\u0000\u0000\u00aa\u0015\u0001"+
		"\u0000\u0000\u0000\u00ab\u00ac\u0003\u0018\f\u0000\u00ac\u00ad\u0006\u000b"+
		"\uffff\uffff\u0000\u00ad\u00b8\u0001\u0000\u0000\u0000\u00ae\u00af\u0003"+
		"\u001a\r\u0000\u00af\u00b0\u0006\u000b\uffff\uffff\u0000\u00b0\u00b8\u0001"+
		"\u0000\u0000\u0000\u00b1\u00b2\u0003\u001e\u000f\u0000\u00b2\u00b3\u0006"+
		"\u000b\uffff\uffff\u0000\u00b3\u00b8\u0001\u0000\u0000\u0000\u00b4\u00b5"+
		"\u0003 \u0010\u0000\u00b5\u00b6\u0006\u000b\uffff\uffff\u0000\u00b6\u00b8"+
		"\u0001\u0000\u0000\u0000\u00b7\u00ab\u0001\u0000\u0000\u0000\u00b7\u00ae"+
		"\u0001\u0000\u0000\u0000\u00b7\u00b1\u0001\u0000\u0000\u0000\u00b7\u00b4"+
		"\u0001\u0000\u0000\u0000\u00b8\u0017\u0001\u0000\u0000\u0000\u00b9\u00ba"+
		"\u0005.\u0000\u0000\u00ba\u00bb\u00038\u001c\u0000\u00bb\u00bc\u0005\u001e"+
		"\u0000\u0000\u00bc\u00bd\u0003\u0010\b\u0000\u00bd\u00be\u0005 \u0000"+
		"\u0000\u00be\u00bf\u0006\f\uffff\uffff\u0000\u00bf\u0019\u0001\u0000\u0000"+
		"\u0000\u00c0\u00c1\u0005#\u0000\u0000\u00c1\u00c2\u00038\u001c\u0000\u00c2"+
		"\u00c3\u0005)\u0000\u0000\u00c3\u00c4\u0003\u0010\b\u0000\u00c4\u00c5"+
		"\u0003\u001c\u000e\u0000\u00c5\u00c6\u0006\r\uffff\uffff\u0000\u00c6\u001b"+
		"\u0001\u0000\u0000\u0000\u00c7\u00c8\u0005 \u0000\u0000\u00c8\u00cf\u0006"+
		"\u000e\uffff\uffff\u0000\u00c9\u00ca\u0005\u001f\u0000\u0000\u00ca\u00cb"+
		"\u0003\u0010\b\u0000\u00cb\u00cc\u0005 \u0000\u0000\u00cc\u00cd\u0006"+
		"\u000e\uffff\uffff\u0000\u00cd\u00cf\u0001\u0000\u0000\u0000\u00ce\u00c7"+
		"\u0001\u0000\u0000\u0000\u00ce\u00c9\u0001\u0000\u0000\u0000\u00cf\u001d"+
		"\u0001\u0000\u0000\u0000\u00d0\u00d1\u0005%\u0000\u0000\u00d1\u00d2\u0003"+
		"\u0002\u0001\u0000\u00d2\u00d3\u0005$\u0000\u0000\u00d3\u00d4\u0003\u0010"+
		"\b\u0000\u00d4\u00d5\u0005 \u0000\u0000\u00d5\u00d6\u0006\u000f\uffff"+
		"\uffff\u0000\u00d6\u001f\u0001\u0000\u0000\u0000\u00d7\u00d8\u00038\u001c"+
		"\u0000\u00d8\u00d9\u0006\u0010\uffff\uffff\u0000\u00d9\u00e4\u0001\u0000"+
		"\u0000\u0000\u00da\u00db\u00038\u001c\u0000\u00db\u00dc\u0005\u0012\u0000"+
		"\u0000\u00dc\u00dd\u00038\u001c\u0000\u00dd\u00de\u0006\u0010\uffff\uffff"+
		"\u0000\u00de\u00e4\u0001\u0000\u0000\u0000\u00df\u00e0\u0005\'\u0000\u0000"+
		"\u00e0\u00e1\u00038\u001c\u0000\u00e1\u00e2\u0006\u0010\uffff\uffff\u0000"+
		"\u00e2\u00e4\u0001\u0000\u0000\u0000\u00e3\u00d7\u0001\u0000\u0000\u0000"+
		"\u00e3\u00da\u0001\u0000\u0000\u0000\u00e3\u00df\u0001\u0000\u0000\u0000"+
		"\u00e4!\u0001\u0000\u0000\u0000\u00e5\u00e6\u0006\u0011\uffff\uffff\u0000"+
		"\u00e6\u00e7\u0003$\u0012\u0000\u00e7\u00e8\u0006\u0011\uffff\uffff\u0000"+
		"\u00e8\u00f0\u0001\u0000\u0000\u0000\u00e9\u00ea\n\u0002\u0000\u0000\u00ea"+
		"\u00eb\u0005\u0003\u0000\u0000\u00eb\u00ec\u0003$\u0012\u0000\u00ec\u00ed"+
		"\u0006\u0011\uffff\uffff\u0000\u00ed\u00ef\u0001\u0000\u0000\u0000\u00ee"+
		"\u00e9\u0001\u0000\u0000\u0000\u00ef\u00f2\u0001\u0000\u0000\u0000\u00f0"+
		"\u00ee\u0001\u0000\u0000\u0000\u00f0\u00f1\u0001\u0000\u0000\u0000\u00f1"+
		"#\u0001\u0000\u0000\u0000\u00f2\u00f0\u0001\u0000\u0000\u0000\u00f3\u00f4"+
		"\u0006\u0012\uffff\uffff\u0000\u00f4\u00f5\u0003&\u0013\u0000\u00f5\u00f6"+
		"\u0006\u0012\uffff\uffff\u0000\u00f6\u00fe\u0001\u0000\u0000\u0000\u00f7"+
		"\u00f8\n\u0002\u0000\u0000\u00f8\u00f9\u0005\u0002\u0000\u0000\u00f9\u00fa"+
		"\u0003&\u0013\u0000\u00fa\u00fb\u0006\u0012\uffff\uffff\u0000\u00fb\u00fd"+
		"\u0001\u0000\u0000\u0000\u00fc\u00f7\u0001\u0000\u0000\u0000\u00fd\u0100"+
		"\u0001\u0000\u0000\u0000\u00fe\u00fc\u0001\u0000\u0000\u0000\u00fe\u00ff"+
		"\u0001\u0000\u0000\u0000\u00ff%\u0001\u0000\u0000\u0000\u0100\u00fe\u0001"+
		"\u0000\u0000\u0000\u0101\u0102\u0003(\u0014\u0000\u0102\u0103\u0005\u0004"+
		"\u0000\u0000\u0103\u0104\u0003(\u0014\u0000\u0104\u0105\u0006\u0013\uffff"+
		"\uffff\u0000\u0105\u0123\u0001\u0000\u0000\u0000\u0106\u0107\u0003(\u0014"+
		"\u0000\u0107\u0108\u0005\u0005\u0000\u0000\u0108\u0109\u0003(\u0014\u0000"+
		"\u0109\u010a\u0006\u0013\uffff\uffff\u0000\u010a\u0123\u0001\u0000\u0000"+
		"\u0000\u010b\u010c\u0003(\u0014\u0000\u010c\u010d\u0005\u0006\u0000\u0000"+
		"\u010d\u010e\u0003(\u0014\u0000\u010e\u010f\u0006\u0013\uffff\uffff\u0000"+
		"\u010f\u0123\u0001\u0000\u0000\u0000\u0110\u0111\u0003(\u0014\u0000\u0111"+
		"\u0112\u0005\u0007\u0000\u0000\u0112\u0113\u0003(\u0014\u0000\u0113\u0114"+
		"\u0006\u0013\uffff\uffff\u0000\u0114\u0123\u0001\u0000\u0000\u0000\u0115"+
		"\u0116\u0003(\u0014\u0000\u0116\u0117\u0005\b\u0000\u0000\u0117\u0118"+
		"\u0003(\u0014\u0000\u0118\u0119\u0006\u0013\uffff\uffff\u0000\u0119\u0123"+
		"\u0001\u0000\u0000\u0000\u011a\u011b\u0003(\u0014\u0000\u011b\u011c\u0005"+
		"\t\u0000\u0000\u011c\u011d\u0003(\u0014\u0000\u011d\u011e\u0006\u0013"+
		"\uffff\uffff\u0000\u011e\u0123\u0001\u0000\u0000\u0000\u011f\u0120\u0003"+
		"(\u0014\u0000\u0120\u0121\u0006\u0013\uffff\uffff\u0000\u0121\u0123\u0001"+
		"\u0000\u0000\u0000\u0122\u0101\u0001\u0000\u0000\u0000\u0122\u0106\u0001"+
		"\u0000\u0000\u0000\u0122\u010b\u0001\u0000\u0000\u0000\u0122\u0110\u0001"+
		"\u0000\u0000\u0000\u0122\u0115\u0001\u0000\u0000\u0000\u0122\u011a\u0001"+
		"\u0000\u0000\u0000\u0122\u011f\u0001\u0000\u0000\u0000\u0123\'\u0001\u0000"+
		"\u0000\u0000\u0124\u0125\u0006\u0014\uffff\uffff\u0000\u0125\u0126\u0003"+
		"*\u0015\u0000\u0126\u0127\u0006\u0014\uffff\uffff\u0000\u0127\u0134\u0001"+
		"\u0000\u0000\u0000\u0128\u0129\n\u0003\u0000\u0000\u0129\u012a\u0005\r"+
		"\u0000\u0000\u012a\u012b\u0003*\u0015\u0000\u012b\u012c\u0006\u0014\uffff"+
		"\uffff\u0000\u012c\u0133\u0001\u0000\u0000\u0000\u012d\u012e\n\u0002\u0000"+
		"\u0000\u012e\u012f\u0005\u000e\u0000\u0000\u012f\u0130\u0003*\u0015\u0000"+
		"\u0130\u0131\u0006\u0014\uffff\uffff\u0000\u0131\u0133\u0001\u0000\u0000"+
		"\u0000\u0132\u0128\u0001\u0000\u0000\u0000\u0132\u012d\u0001\u0000\u0000"+
		"\u0000\u0133\u0136\u0001\u0000\u0000\u0000\u0134\u0132\u0001\u0000\u0000"+
		"\u0000\u0134\u0135\u0001\u0000\u0000\u0000\u0135)\u0001\u0000\u0000\u0000"+
		"\u0136\u0134\u0001\u0000\u0000\u0000\u0137\u0138\u0006\u0015\uffff\uffff"+
		"\u0000\u0138\u0139\u0003,\u0016\u0000\u0139\u013a\u0006\u0015\uffff\uffff"+
		"\u0000\u013a\u014c\u0001\u0000\u0000\u0000\u013b\u013c\n\u0004\u0000\u0000"+
		"\u013c\u013d\u0005\n\u0000\u0000\u013d\u013e\u0003,\u0016\u0000\u013e"+
		"\u013f\u0006\u0015\uffff\uffff\u0000\u013f\u014b\u0001\u0000\u0000\u0000"+
		"\u0140\u0141\n\u0003\u0000\u0000\u0141\u0142\u0005\u000b\u0000\u0000\u0142"+
		"\u0143\u0003,\u0016\u0000\u0143\u0144\u0006\u0015\uffff\uffff\u0000\u0144"+
		"\u014b\u0001\u0000\u0000\u0000\u0145\u0146\n\u0002\u0000\u0000\u0146\u0147"+
		"\u0005\f\u0000\u0000\u0147\u0148\u0003,\u0016\u0000\u0148\u0149\u0006"+
		"\u0015\uffff\uffff\u0000\u0149\u014b\u0001\u0000\u0000\u0000\u014a\u013b"+
		"\u0001\u0000\u0000\u0000\u014a\u0140\u0001\u0000\u0000\u0000\u014a\u0145"+
		"\u0001\u0000\u0000\u0000\u014b\u014e\u0001\u0000\u0000\u0000\u014c\u014a"+
		"\u0001\u0000\u0000\u0000\u014c\u014d\u0001\u0000\u0000\u0000\u014d+\u0001"+
		"\u0000\u0000\u0000\u014e\u014c\u0001\u0000\u0000\u0000\u014f\u0150\u0003"+
		".\u0017\u0000\u0150\u0151\u0006\u0016\uffff\uffff\u0000\u0151\u0163\u0001"+
		"\u0000\u0000\u0000\u0152\u0153\u0005\r\u0000\u0000\u0153\u0154\u0003,"+
		"\u0016\u0000\u0154\u0155\u0006\u0016\uffff\uffff\u0000\u0155\u0163\u0001"+
		"\u0000\u0000\u0000\u0156\u0157\u0005\u000e\u0000\u0000\u0157\u0158\u0003"+
		",\u0016\u0000\u0158\u0159\u0006\u0016\uffff\uffff\u0000\u0159\u0163\u0001"+
		"\u0000\u0000\u0000\u015a\u015b\u0005\u000f\u0000\u0000\u015b\u015c\u0003"+
		",\u0016\u0000\u015c\u015d\u0006\u0016\uffff\uffff\u0000\u015d\u0163\u0001"+
		"\u0000\u0000\u0000\u015e\u015f\u0005\u0011\u0000\u0000\u015f\u0160\u0003"+
		",\u0016\u0000\u0160\u0161\u0006\u0016\uffff\uffff\u0000\u0161\u0163\u0001"+
		"\u0000\u0000\u0000\u0162\u014f\u0001\u0000\u0000\u0000\u0162\u0152\u0001"+
		"\u0000\u0000\u0000\u0162\u0156\u0001\u0000\u0000\u0000\u0162\u015a\u0001"+
		"\u0000\u0000\u0000\u0162\u015e\u0001\u0000\u0000\u0000\u0163-\u0001\u0000"+
		"\u0000\u0000\u0164\u0165\u0006\u0017\uffff\uffff\u0000\u0165\u0166\u0003"+
		"0\u0018\u0000\u0166\u0167\u0006\u0017\uffff\uffff\u0000\u0167\u017d\u0001"+
		"\u0000\u0000\u0000\u0168\u0169\n\u0005\u0000\u0000\u0169\u016a\u0005\u0011"+
		"\u0000\u0000\u016a\u017c\u0006\u0017\uffff\uffff\u0000\u016b\u016c\n\u0004"+
		"\u0000\u0000\u016c\u016d\u0005\u0010\u0000\u0000\u016d\u016e\u0005/\u0000"+
		"\u0000\u016e\u017c\u0006\u0017\uffff\uffff\u0000\u016f\u0170\n\u0003\u0000"+
		"\u0000\u0170\u0171\u0005\u0017\u0000\u0000\u0171\u0172\u00032\u0019\u0000"+
		"\u0172\u0173\u0005\u0018\u0000\u0000\u0173\u0174\u0006\u0017\uffff\uffff"+
		"\u0000\u0174\u017c\u0001\u0000\u0000\u0000\u0175\u0176\n\u0002\u0000\u0000"+
		"\u0176\u0177\u0005\u0019\u0000\u0000\u0177\u0178\u00038\u001c\u0000\u0178"+
		"\u0179\u0005\u001a\u0000\u0000\u0179\u017a\u0006\u0017\uffff\uffff\u0000"+
		"\u017a\u017c\u0001\u0000\u0000\u0000\u017b\u0168\u0001\u0000\u0000\u0000"+
		"\u017b\u016b\u0001\u0000\u0000\u0000\u017b\u016f\u0001\u0000\u0000\u0000"+
		"\u017b\u0175\u0001\u0000\u0000\u0000\u017c\u017f\u0001\u0000\u0000\u0000"+
		"\u017d\u017b\u0001\u0000\u0000\u0000\u017d\u017e\u0001\u0000\u0000\u0000"+
		"\u017e/\u0001\u0000\u0000\u0000\u017f\u017d\u0001\u0000\u0000\u0000\u0180"+
		"\u0181\u0005\u0017\u0000\u0000\u0181\u0182\u00038\u001c\u0000\u0182\u0183"+
		"\u0005\u0018\u0000\u0000\u0183\u0184\u0006\u0018\uffff\uffff\u0000\u0184"+
		"\u0196\u0001\u0000\u0000\u0000\u0185\u0186\u0005\u0015\u0000\u0000\u0186"+
		"\u0187\u00038\u001c\u0000\u0187\u0188\u0005\u0013\u0000\u0000\u0188\u0189"+
		"\u0003H$\u0000\u0189\u018a\u0005\u0016\u0000\u0000\u018a\u018b\u0006\u0018"+
		"\uffff\uffff\u0000\u018b\u0196\u0001\u0000\u0000\u0000\u018c\u018d\u0003"+
		":\u001d\u0000\u018d\u018e\u0006\u0018\uffff\uffff\u0000\u018e\u0196\u0001"+
		"\u0000\u0000\u0000\u018f\u0190\u0005/\u0000\u0000\u0190\u0196\u0006\u0018"+
		"\uffff\uffff\u0000\u0191\u0192\u0005(\u0000\u0000\u0192\u0193\u0003H$"+
		"\u0000\u0193\u0194\u0006\u0018\uffff\uffff\u0000\u0194\u0196\u0001\u0000"+
		"\u0000\u0000\u0195\u0180\u0001\u0000\u0000\u0000\u0195\u0185\u0001\u0000"+
		"\u0000\u0000\u0195\u018c\u0001\u0000\u0000\u0000\u0195\u018f\u0001\u0000"+
		"\u0000\u0000\u0195\u0191\u0001\u0000\u0000\u0000\u01961\u0001\u0000\u0000"+
		"\u0000\u0197\u0198\u00034\u001a\u0000\u0198\u0199\u0006\u0019\uffff\uffff"+
		"\u0000\u0199\u019c\u0001\u0000\u0000\u0000\u019a\u019c\u0006\u0019\uffff"+
		"\uffff\u0000\u019b\u0197\u0001\u0000\u0000\u0000\u019b\u019a\u0001\u0000"+
		"\u0000\u0000\u019c3\u0001\u0000\u0000\u0000\u019d\u019e\u0003\"\u0011"+
		"\u0000\u019e\u019f\u00036\u001b\u0000\u019f\u01a0\u0006\u001a\uffff\uffff"+
		"\u0000\u01a0\u01a5\u0001\u0000\u0000\u0000\u01a1\u01a2\u0003\"\u0011\u0000"+
		"\u01a2\u01a3\u0006\u001a\uffff\uffff\u0000\u01a3\u01a5\u0001\u0000\u0000"+
		"\u0000\u01a4\u019d\u0001\u0000\u0000\u0000\u01a4\u01a1\u0001\u0000\u0000"+
		"\u0000\u01a55\u0001\u0000\u0000\u0000\u01a6\u01a7\u0005\u0014\u0000\u0000"+
		"\u01a7\u01a8\u00032\u0019\u0000\u01a8\u01a9\u0006\u001b\uffff\uffff\u0000"+
		"\u01a9\u01ac\u0001\u0000\u0000\u0000\u01aa\u01ac\u0006\u001b\uffff\uffff"+
		"\u0000\u01ab\u01a6\u0001\u0000\u0000\u0000\u01ab\u01aa\u0001\u0000\u0000"+
		"\u0000\u01ac7\u0001\u0000\u0000\u0000\u01ad\u01ae\u0003\"\u0011\u0000"+
		"\u01ae\u01af\u0006\u001c\uffff\uffff\u0000\u01af9\u0001\u0000\u0000\u0000"+
		"\u01b0\u01b1\u00052\u0000\u0000\u01b1\u01bd\u0006\u001d\uffff\uffff\u0000"+
		"\u01b2\u01b3\u00050\u0000\u0000\u01b3\u01bd\u0006\u001d\uffff\uffff\u0000"+
		"\u01b4\u01b5\u00053\u0000\u0000\u01b5\u01bd\u0006\u001d\uffff\uffff\u0000"+
		"\u01b6\u01b7\u0005!\u0000\u0000\u01b7\u01bd\u0006\u001d\uffff\uffff\u0000"+
		"\u01b8\u01b9\u0005*\u0000\u0000\u01b9\u01bd\u0006\u001d\uffff\uffff\u0000"+
		"\u01ba\u01bb\u0005&\u0000\u0000\u01bb\u01bd\u0006\u001d\uffff\uffff\u0000"+
		"\u01bc\u01b0\u0001\u0000\u0000\u0000\u01bc\u01b2\u0001\u0000\u0000\u0000"+
		"\u01bc\u01b4\u0001\u0000\u0000\u0000\u01bc\u01b6\u0001\u0000\u0000\u0000"+
		"\u01bc\u01b8\u0001\u0000\u0000\u0000\u01bc\u01ba\u0001\u0000\u0000\u0000"+
		"\u01bd;\u0001\u0000\u0000\u0000\u01be\u01bf\u0005/\u0000\u0000\u01bf\u01c0"+
		"\u0005\u0013\u0000\u0000\u01c0\u01c1\u0003H$\u0000\u01c1\u01c2\u0006\u001e"+
		"\uffff\uffff\u0000\u01c2=\u0001\u0000\u0000\u0000\u01c3\u01c4\u0003<\u001e"+
		"\u0000\u01c4\u01c5\u0003@ \u0000\u01c5\u01c6\u0006\u001f\uffff\uffff\u0000"+
		"\u01c6\u01cb\u0001\u0000\u0000\u0000\u01c7\u01c8\u0003<\u001e\u0000\u01c8"+
		"\u01c9\u0006\u001f\uffff\uffff\u0000\u01c9\u01cb\u0001\u0000\u0000\u0000"+
		"\u01ca\u01c3\u0001\u0000\u0000\u0000\u01ca\u01c7\u0001\u0000\u0000\u0000"+
		"\u01cb?\u0001\u0000\u0000\u0000\u01cc\u01cd\u0005\u0014\u0000\u0000\u01cd"+
		"\u01ce\u0003>\u001f\u0000\u01ce\u01cf\u0006 \uffff\uffff\u0000\u01cf\u01d2"+
		"\u0001\u0000\u0000\u0000\u01d0\u01d2\u0006 \uffff\uffff\u0000\u01d1\u01cc"+
		"\u0001\u0000\u0000\u0000\u01d1\u01d0\u0001\u0000\u0000\u0000\u01d2A\u0001"+
		"\u0000\u0000\u0000\u01d3\u01d4\u0003D\"\u0000\u01d4\u01d5\u0006!\uffff"+
		"\uffff\u0000\u01d5\u01d8\u0001\u0000\u0000\u0000\u01d6\u01d8\u0006!\uffff"+
		"\uffff\u0000\u01d7\u01d3\u0001\u0000\u0000\u0000\u01d7\u01d6\u0001\u0000"+
		"\u0000\u0000\u01d8C\u0001\u0000\u0000\u0000\u01d9\u01da\u0003H$\u0000"+
		"\u01da\u01db\u0006\"\uffff\uffff\u0000\u01db\u01e1\u0001\u0000\u0000\u0000"+
		"\u01dc\u01dd\u0003H$\u0000\u01dd\u01de\u0003F#\u0000\u01de\u01df\u0006"+
		"\"\uffff\uffff\u0000\u01df\u01e1\u0001\u0000\u0000\u0000\u01e0\u01d9\u0001"+
		"\u0000\u0000\u0000\u01e0\u01dc\u0001\u0000\u0000\u0000\u01e1E\u0001\u0000"+
		"\u0000\u0000\u01e2\u01e3\u0005\u0014\u0000\u0000\u01e3\u01e4\u0003D\""+
		"\u0000\u01e4\u01e5\u0006#\uffff\uffff\u0000\u01e5\u01e8\u0001\u0000\u0000"+
		"\u0000\u01e6\u01e8\u0006#\uffff\uffff\u0000\u01e7\u01e2\u0001\u0000\u0000"+
		"\u0000\u01e7\u01e6\u0001\u0000\u0000\u0000\u01e8G\u0001\u0000\u0000\u0000"+
		"\u01e9\u01ea\u0003J%\u0000\u01ea\u01eb\u0006$\uffff\uffff\u0000\u01eb"+
		"I\u0001\u0000\u0000\u0000\u01ec\u01ed\u0005\u0006\u0000\u0000\u01ed\u01ee"+
		"\u0003>\u001f\u0000\u01ee\u01ef\u0005\u0007\u0000\u0000\u01ef\u01f0\u0006"+
		"%\uffff\uffff\u0000\u01f0\u0201\u0001\u0000\u0000\u0000\u01f1\u01f2\u0005"+
		"\u0015\u0000\u0000\u01f2\u01f3\u0003>\u001f\u0000\u01f3\u01f4\u0005\u0016"+
		"\u0000\u0000\u01f4\u01f5\u0006%\uffff\uffff\u0000\u01f5\u0201\u0001\u0000"+
		"\u0000\u0000\u01f6\u01f7\u0005\u0017\u0000\u0000\u01f7\u01f8\u0003B!\u0000"+
		"\u01f8\u01f9\u0005\u0018\u0000\u0000\u01f9\u01fa\u0005\u0013\u0000\u0000"+
		"\u01fa\u01fb\u0003H$\u0000\u01fb\u01fc\u0006%\uffff\uffff\u0000\u01fc"+
		"\u0201\u0001\u0000\u0000\u0000\u01fd\u01fe\u0003L&\u0000\u01fe\u01ff\u0006"+
		"%\uffff\uffff\u0000\u01ff\u0201\u0001\u0000\u0000\u0000\u0200\u01ec\u0001"+
		"\u0000\u0000\u0000\u0200\u01f1\u0001\u0000\u0000\u0000\u0200\u01f6\u0001"+
		"\u0000\u0000\u0000\u0200\u01fd\u0001\u0000\u0000\u0000\u0201K\u0001\u0000"+
		"\u0000\u0000\u0202\u0203\u0005\u0019\u0000\u0000\u0203\u0204\u00050\u0000"+
		"\u0000\u0204\u0205\u0005\u001a\u0000\u0000\u0205\u0206\u0003H$\u0000\u0206"+
		"\u0207\u0006&\uffff\uffff\u0000\u0207\u0210\u0001\u0000\u0000\u0000\u0208"+
		"\u0209\u0005\u0011\u0000\u0000\u0209\u020a\u0003H$\u0000\u020a\u020b\u0006"+
		"&\uffff\uffff\u0000\u020b\u0210\u0001\u0000\u0000\u0000\u020c\u020d\u0003"+
		"N\'\u0000\u020d\u020e\u0006&\uffff\uffff\u0000\u020e\u0210\u0001\u0000"+
		"\u0000\u0000\u020f\u0202\u0001\u0000\u0000\u0000\u020f\u0208\u0001\u0000"+
		"\u0000\u0000\u020f\u020c\u0001\u0000\u0000\u0000\u0210M\u0001\u0000\u0000"+
		"\u0000\u0211\u0212\u0005\u001c\u0000\u0000\u0212\u021c\u0006\'\uffff\uffff"+
		"\u0000\u0213\u0214\u0005\u001b\u0000\u0000\u0214\u021c\u0006\'\uffff\uffff"+
		"\u0000\u0215\u0216\u0005\u001d\u0000\u0000\u0216\u021c\u0006\'\uffff\uffff"+
		"\u0000\u0217\u0218\u0005-\u0000\u0000\u0218\u021c\u0006\'\uffff\uffff"+
		"\u0000\u0219\u021a\u0005/\u0000\u0000\u021a\u021c\u0006\'\uffff\uffff"+
		"\u0000\u021b\u0211\u0001\u0000\u0000\u0000\u021b\u0213\u0001\u0000\u0000"+
		"\u0000\u021b\u0215\u0001\u0000\u0000\u0000\u021b\u0217\u0001\u0000\u0000"+
		"\u0000\u021b\u0219\u0001\u0000\u0000\u0000\u021cO\u0001\u0000\u0000\u0000"+
		"#[s~\u0087\u008e\u0095\u0099\u00a2\u00a9\u00b7\u00ce\u00e3\u00f0\u00fe"+
		"\u0122\u0132\u0134\u014a\u014c\u0162\u017b\u017d\u0195\u019b\u01a4\u01ab"+
		"\u01bc\u01ca\u01d1\u01d7\u01e0\u01e7\u0200\u020f\u021b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}