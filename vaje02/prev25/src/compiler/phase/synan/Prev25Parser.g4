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
	| FUN IDENTIFIER LPARAN args_ RPARAN syn1
	| expr;

args_
	: IDENTIFIER COLON type COMMA args_
	| IDENTIFIER COLON type
	| ;

syn1
	: COLON type neki;
neki 
	: ASSIGN stmt (COMMA stmt)*; 


stmt_
	: stmt (COMMA stmt)*
	| ; 

stmt
	: whileStmt
	| ifStmt
	| letStmt
	| stmtBase;

whileStmt
	: WHILE expr stmt_ END;

ifStmt
	: IF expr THEN stmt_ ifElseStmt;

ifElseStmt
	: END
	| ELSE stmt_ END;

letStmt
	: LET defs IN stmt_ END;


stmtBase
	: expr
	| expr ASSIGN expr
	| RETURN expr;


exprEntry
	: expr2nd (OR expr2nd)*;

expr2nd
	//: exprEnd;
	: expr3rd (AND expr3rd)*;


expr3rd
	: expr4th (comprOp expr4th)*;
	//| expr4th;

comprOp
	: EQU
	| NEQ
	| LT
	| GT
	| LEQ
	| GEQ;


expr4th
	: expr5th (additiveExpr expr5th)*;
additiveExpr
	: PLUS
	| MINUS;

expr5th
	: expr6th (mulOps expr6th)*;

mulOps
	: MUL
	| DIV
	| MOD;

expr6th
	: (prefixOps) expr7th;
prefixOps
	: PLUS
	| MINUS
	| EXCL
	| POW;	

exprs_
	:(exprEntry COMMA)* exprEntry
	| ;

expr7th
	: prim (oper)*;
oper
	: POW
	| DOT IDENTIFIER
	| LPARAN exprs_ RPARAN;

prim
	: LPARAN expr RPARAN
	| LBRACE expr COLON type RBRACE
	| exprEnd
	| IDENTIFIER;


exprPrio
	: expr LBRCKT expr RBRCKT
	| expr LPARAN functionCall;

functionCall
	: expr COMMA functionCall
	| RPARAN;

expr
	: exprEntry;

exprEnd
	: CONSTCHAR
	| CONSTNUM
	| CONSTSTR
	| BOOL
	| FALSE
	| TRUE
	| NULL
	| SIZEOF type;

type:syn21;	

syn21
	: LT IDENTIFIER COLON type (COMMA IDENTIFIER COLON type)* GT
	| LBRACE IDENTIFIER COLON type (COMMA IDENTIFIER COLON type)* RBRACE
	| typeOver;

typeOver
	: LBRCKT CONSTNUM RBRCKT type
	| POW type
	| type2;

type2
	: BOOL
	| INT
	| CHAR
	| VOID
	| IDENTIFIER;
