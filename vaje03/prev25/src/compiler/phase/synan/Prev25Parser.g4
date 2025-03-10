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
	: defs EOF {$ast = new AST.Nodes<AST.FullDefn>($defs.ast);}
	;
defs returns [List<AST.FullDefn> ast]
	: def { $ast = new ArrayList<AST.FullDefn>($defs.ast);}
	| d=def defs {$ast = $d.ast; $ast.AddLast($def.ast);};

def returns [AST.Nodes<FullDefn> ast]
	: TYP IDENTIFIER ASSIGN type1 {$ast = new AST.type1Defn((LexAn.LocLogToken.getLocation), $type1.name, $type1.ast)}
	| VAR IDENTIFIER COLON type1 {$ast = new VarDef(loc($VAR, $type1.ast), $IDENTIFIER.text(), $type1.ast);}
	| FUN IDENTIFIER LPARAN args1 RPARAN COLON type1 impl=syn1 [$FUN, $ID, $args1.ast, $type1.ast] {$ast=$impl.ast;}
    ;
	

myArg returns [AST.ParDefn ast]
    : IDENTIFIER COLON type1 {$ast = new ParDefn(loc($IDENTIFIER, $type1.ast), $IDENTIFER.ast, $type1.ast);}
    ;
args1 returns [List<AST.ParDefn> ast]
    : args_ {$ast = $args_.ast;}
    | {$ast=new ArrayList<AST.ParDefn>();}
    ;
args_ returns [List<AST.ParDefn> ast]
	: myArg args__ {$ast = $args__.ast; $ast.add(0, $myArg.ast);}
	| myArg {$ast = new ArrayList<AST.ParDefn>(); $ast.addLast($myArg.ast);}
    ;
args__ returns [List<AST.ParDefn> ast]
	: COMMA args_ {$ast = $args__.ast}
	| {$ast = new ArrayList<AST.ParDefn>();}
    ;

syn1 [LexAn.LocLogToken fun, LexAn.LocLogToken id, List<ParDefn> args, Type type] returns [AST.FunDefn ast]
	: ASSIGN stmt_ {$ast = new DefFunDef(loc(fun, $stmt_.ast), id.text(), args, type, $stmt_.ast);}
	| {$ast = mew FunDefn(loc(fun, type), id.text(), args, type);}
    ;

stmt_ returns [List<Stmt> ast]
	: stmt_2
	| {$ast = new ArrayList<Stmt>();}
    ; 

stmt_2 returns [List<Stmt> ast]
    : stmt stmt__ {$ast=$stmt__.ast; $ast.add(0, $stmt);}
    | stmt {$ast=new ArrayList<Stmt>(); $ast.addLast($stmt.ast);}
    ;

stmt__ returns [List<Stmt> ast]
	: COMMA stmt_2 {$ast = $stmt_.ast}
	| {$ast = new ArrayList<Stmt>();}
    ;

stmt returns [Stmt ast]
	: whileStmt {$ast=$whileStmt.ast;}
	| ifStmt {$ast=$ifStmt.ast;}
	| letStmt {$ast = $letStmt.ast;}
	| stmtBase {$ast = $stmtBase.ast;}
    ;

whileStmt returns [WhileStmt ast]
	: WHILE expr DO stmt_ END {$ast = new WhileStmt(loc($WHILE, $END), $expr.ast, $stmt_.sat);};

ifStmt returns [Stmt ast]
	: IF expr THEN stmt_ ifElseStmt [$IF, $expr.ast, $THEN, $stmt_.ast] {$ast = $ifElseStmt.ast;}
    ;

ifElseStmt [Token start, Expr cond, Token endExpr, List<Stmt> statements] returns [Stmt ast]
	: END {$ast = new IfThenStmt(loc(start, $END), cond, statements);}
	| ELSE stmt_ END { $ast = new IfThenElseStmt(loc(start, $END), cond, statements, $stmt_.ast);}
    ;

letStmt returns [LetStmt ast]
	: LET defs IN stmt_ END { $ast = new LetStmt(loc($LET, $END), $defs.ast, $stmt_.ast);}
    ;


stmtBase returns [Stmt ast]
	: expr {$ast = new ExprStmt(loc(expr), $expr.ast);}
	| aa=expr ASSIGN bb=expr {$ast = new AssignStmt(loc(aa, bb), $aa.ast, $bb.ast);}
	| RETURN expr {$ast = new ReturnStmt(loc($RETURN, $expr.ast), $expr.ast);}
    ;


exprEntry returns [Expr ast]
	: exprEntry OR expr2nd {$ast = new BinExpr(loc($exprEntry.ast, $expr2nd.ast), BinExpr.Oper.OR, $exprEntry.ast, $expr2nd.ast);}
	| expr2nd {$ast = $expr2nd.ast;}
    ;

expr2nd returns [Expr ast]
	: expr2nd AND expr3rd {$ast = new BinExpr(loc($expr2nd.ast, $expr3rd.ast), BinExpr.Oper.AND, $expr2nd.ast, $expr3rd.ast);}
	| expr3rd {$ast = $expr3rd.ast;}
    ;


expr3rd returns [Expr ast]
	: a=expr4th comprOp [$a, $b] b=expr4th {$ast = $comprOp.ast;}
	| expr4th {$ast = $expr4th.ast;}
    ;

comprOp [Expr a, Expr b] returns [Expr ast]
	: EQU {$ast = new BinExpr(loc($a.ast, $b.ast), BinExpr.Oper.EQU, $a.ast, $b.ast);}
	| NEQ {$ast = new BinExpr(loc($a.ast, $b.ast), BinExpr.Oper.NEQ, $a.ast, $b.ast);}
	| LT {$ast = new BinExpr(loc($a.ast, $b.ast), BinExpr.Oper.LTH, $a.ast, $b.ast);}
	| GT {$ast = new BinExpr(loc($a.ast, $b.ast), BinExpr.Oper.GTH, $a.ast, $b.ast);}
	| LEQ {$ast = new BinExpr(loc($a.ast, $b.ast), BinExpr.Oper.LEQ, $a.ast, $b.ast);}
	| GEQ {$ast = new BinExpr(loc($a.ast, $b.ast), BinExpr.Oper.GEQ, $a.ast, $b.ast);}
    ;


expr4th returns [Expr ast]
	: a=expr4th additiveExpr b=expr5th
	| expr5th {$ast = $expr5th.ast};

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
	| LBRACE expr COLON type1 RBRACE
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
	| SIZEOF type1;

type1:syn21;	

idtype1
	: IDENTIFIER COLON type1 COMMA idtype1
	| IDENTIFIER COLON type1;

type1s2
	: idtype1 COMMA type1s2
	| type1 COMMA type1s2
	| type1
	| idtype1;

type1s
	: type1s3
	| ;

type1s3
	: type1
	| type1 COMMA type1s3;

syn21
	: LT type1s2 GT
	| LBRACE type1s2 RBRACE
	| LPARAN type1s RPARAN COLON type1
	| type1Over;

type1Over
	: LBRCKT CONSTNUM RBRCKT type1
	| POW type1
	| type12;

type12 returns [Type ast]
	: BOOL {$ast = new Atomtype1(loc($BOOL), Atomtype.type.BOOL);}
	| INT {$ast = new Atomtype1(loc($INT), Atomtype.type.INT);}
	| CHAR {$ast = new Atomtype1(loc($CHAR), Atomtype.type.CHAR);}
	| VOID {$ast = new Atomtype1(loc($VOID), Atomtype.type.VOID);}
	| IDENTIFIER;