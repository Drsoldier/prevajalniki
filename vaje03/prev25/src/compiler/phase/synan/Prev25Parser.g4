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

source returns [AST.Nodes<FullDefn> ast]
	: defs EOF
	{ $ast = new AST.Nodes<AST.FullDefn>($defs.ast);}
	;
defs returns [List<AST.FullDefn> ast]
	: def { $ast = new List<AST.FullDefn>(); $ast.addLast($def.ast);}
	| d=def defs {$ast = $d.ast; $ast.addLast($def.ast);}
	;

def returns [AST.Nodes<FullDefn> ast]
	: TYP IDENTIFIER ASSIGN type {$ast = new AST.TypDefn((LexAn.LocLogToken.getLocation()), $type.name, $type.ast)}
	| VAR IDENTIFIER COLON type {$ast = new VarDef(loc($VAR, $type.ast), $IDENTIFIER.text(), $type.ast);}
	| FUN IDENTIFIER LPARAN entryArgs RPARAN COLON type impl=syn1 [$FUN, $ID, $args1.ast, $type.ast] {$ast=implementation.ast;}
	;

entryArgs 
	: args 
	| ;	
args returns [List<AST.ParDefn> ast]
	: arg args_ {$ast=$ast2.ast; $ast.addLast($arg.ast);};

args_
	: COMMA args
	| ;

arg returns [AST.ParDefn ast]
	: IDENTIFIER COLON type {$ast = new ParDefn(loc($IDENTIFIER, $type.ast), $IDENTIFIER.text(), $type.ast);};

syn1
	: ASSIGN neki
	| ;
neki 
	: stmt neki2;

neki2
	: COMMA stmt neki2
	| ;


stmt_
	: stmt
	| stmt COMMA stmt__
	| ; 

stmt__
	: stmt
	| stmt COMMA stmt__;

stmt
	: whileStmt
	| ifStmt
	| letStmt
	| stmtBase;

whileStmt
	: WHILE expr DO stmt_ END;

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
	: exprEntry OR expr2nd
	| expr2nd;

expr2nd
	: expr2nd AND expr3rd
	| expr3rd;


expr3rd
	: expr4th comprOp expr4th
	| expr4th;

comprOp
	: EQU
	| NEQ
	| LT
	| GT
	| LEQ
	| GEQ;


expr4th
	: expr4th additiveExpr expr5th
	| expr5th;

additiveExpr
	: PLUS
	| MINUS;

expr5th
	: expr5th mulOps expr6th
	| expr6th;

mulOps
	: MUL
	| DIV
	| MOD;

expr6th
	: expr7th
	| prefixOps expr6th;

prefixOps
	: PLUS
	| MINUS
	| EXCL
	| POW;	

exprs_
	: exprEntry
	| exprEntry COMMA exprs__
	|;

exprs__
	: exprEntry
	| exprEntry COMMA exprs__ ;

expr7th
	: expr7th (oper)
	| prim;
oper
	: POW
	| DOT IDENTIFIER
	| LPARAN exprs_ RPARAN
	| LBRCKT expr RBRCKT;

prim
	: LPARAN expr RPARAN
	| LBRACE expr COLON type RBRACE
	| exprEnd
	| IDENTIFIER;


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

idType
	: IDENTIFIER COLON type COMMA idType
	| IDENTIFIER COLON type;

types2
	: idType COMMA types2
	| type COMMA types2
	| type
	| idType;

types
	: types3
	| ;

types3
	: type
	| type COMMA types3;

syn21
	: LT types2 GT
	| LBRACE types2 RBRACE
	| LPARAN types RPARAN COLON type
	| typeOver;

typeOver
	: LBRCKT CONSTNUM RBRCKT type 
	| POW type
	| type2;

type2 returns [Type ast]
	: BOOL {$ast = new AtomType(loc($BOOL), AtomType.Type.BOOL);}
	| INT {$ast = new AtomType(loc($INT), AtomType.Type.INT);}
	| CHAR {$ast = new AtomType(loc($CHAR), AtomType.Type.CHAR);}
	| VOID {$ast = new AtomType(loc($VOID), AtomType.Type.VOID);}
	| IDENTIFIER;
