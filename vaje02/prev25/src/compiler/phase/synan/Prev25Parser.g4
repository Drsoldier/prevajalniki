parser grammar Prev25Parser;

@header {

	package compiler.phase.synan;
	
	import java.util.*;
	import compiler.common.report.*;
	import compiler.phase.lexan.*;

}

@members {

	private Location loc(Token tok) { return new Location((LexAn.LocLogToken)tok); }
	private Location loc(Token     tok1, Token     tok2) { return new Location((LexAn.LocLogToken)tok1, (LexAn.LocLogToken)tok2); }
	private Location loc(Token     tok1, Locatable loc2) { return new Location((LexAn.LocLogToken)tok1, loc2); }
	private Location loc(Locatable loc1, Token     tok2) { return new Location(loc1, (LexAn.LocLogToken)tok2); }
	private Location loc(Locatable loc1, Locatable loc2) { return new Location(loc1, loc2); }

}

options{
    tokenVocab=Prev25Lexer;
}

source
	: defs EOF
	;
defs
	: def defs
	| def;

def
	: TYP IDENTIFIER ASSIGN type
	| VAR IDENTIFIER COLON type
	| FUN IDENTIFIER LPARAN syn4;

syn4
	: RPARAN COLON type neki
	| COMMA IDENTIFIER COLON type syn4;

neki 
	: ASSIGN defs_
	| ;

defs_
	: def
	| def COMMA defs_;

type
	: BOOL
	| INT
	| CHAR
	| VOID
	;
