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
		RULE_source = 0, RULE_defs = 1, RULE_type = 2, RULE_def = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "defs", "type", "def"
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
			setState(8);
			defs();
			setState(9);
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
			enterOuterAlt(_localctx, 1);
			{
			setState(11);
			def();
			setState(12);
			defs();
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
		public TerminalNode BOOL() { return getToken(Prev25Parser.BOOL, 0); }
		public TerminalNode INT() { return getToken(Prev25Parser.INT, 0); }
		public TerminalNode CHAR() { return getToken(Prev25Parser.CHAR, 0); }
		public TerminalNode VOID() { return getToken(Prev25Parser.VOID, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 35185311612928L) != 0)) ) {
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
	public static class DefContext extends ParserRuleContext {
		public TerminalNode TYP() { return getToken(Prev25Parser.TYP, 0); }
		public TerminalNode IDENTIFIER() { return getToken(Prev25Parser.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(Prev25Parser.ASSIGN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public DefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def; }
	}

	public final DefContext def() throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			match(TYP);
			setState(17);
			match(IDENTIFIER);
			setState(18);
			match(ASSIGN);
			setState(19);
			type();
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
		"\u0004\u00014\u0016\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0000"+
		"\u0000\u0004\u0000\u0002\u0004\u0006\u0000\u0001\u0002\u0000\u001b\u001d"+
		"--\u0011\u0000\b\u0001\u0000\u0000\u0000\u0002\u000b\u0001\u0000\u0000"+
		"\u0000\u0004\u000e\u0001\u0000\u0000\u0000\u0006\u0010\u0001\u0000\u0000"+
		"\u0000\b\t\u0003\u0002\u0001\u0000\t\n\u0005\u0000\u0000\u0001\n\u0001"+
		"\u0001\u0000\u0000\u0000\u000b\f\u0003\u0006\u0003\u0000\f\r\u0003\u0002"+
		"\u0001\u0000\r\u0003\u0001\u0000\u0000\u0000\u000e\u000f\u0007\u0000\u0000"+
		"\u0000\u000f\u0005\u0001\u0000\u0000\u0000\u0010\u0011\u0005+\u0000\u0000"+
		"\u0011\u0012\u0005/\u0000\u0000\u0012\u0013\u0005\u0012\u0000\u0000\u0013"+
		"\u0014\u0003\u0004\u0002\u0000\u0014\u0007\u0001\u0000\u0000\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}