parser grammar Prev25Parser;

@header {

	package compiler.phase.synan;
	
	import java.util.*;
	import compiler.common.report.*;
	import compiler.phase.lexan.*;
	import compiler.phase.abstr.*;
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

source returns [AST.Nodes<AST.FullDefn> ast]
	: c=defs EOF {$ast = new AST.Nodes<AST.FullDefn>($c.ast); }
	;
defs returns [List<AST.FullDefn> ast]
	: a=def { $ast = new ArrayList<AST.FullDefn>(); $ast.addLast($a.ast);}
	| d=def b=defs {$ast = $b.ast; $ast.add(0, $d.ast);}
	;

def returns [AST.FullDefn ast]
	: TYP IDENTIFIER ASSIGN t=type1 {$ast = new AST.TypDefn(loc($TYP, $t.ast), $IDENTIFIER.getText(), $t.ast);}
	| VAR IDENTIFIER COLON type1 {$ast = new AST.VarDefn(loc($VAR, $type1.ast), $IDENTIFIER.getText(), $type1.ast);}
	| FUN IDENTIFIER LPARAN args1 RPARAN COLON type1 impl=syn1 [$FUN, $IDENTIFIER, $args1.ast, $type1.ast] {$ast=$impl.ast;}
    ;
	

myArg returns [AST.ParDefn ast]
    : IDENTIFIER COLON type1 {$ast = new AST.ParDefn(loc($IDENTIFIER, $type1.ast), $IDENTIFIER.getText(), $type1.ast);}
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
	: COMMA a=args_ {$ast = $a.ast;}
	| {$ast = new ArrayList<AST.ParDefn>();}
    ;

syn1 [Token fun, Token id, List<AST.ParDefn> args, AST.Type type] returns [AST.FunDefn ast]
	: ASSIGN stmt_ {$ast = new AST.DefFunDefn(loc($fun, $stmt_.ast.getLast()), $id.getText(), $args, $type, $stmt_.ast);}
	| {$ast = new AST.ExtFunDefn(loc($fun, $type), $id.getText(), $args, $type);}
    ;

stmt_ returns [List<AST.Stmt> ast]
	: a=stmt_2 {$ast = $a.ast;}
	| {$ast = new ArrayList<AST.Stmt>();}
    ; 

stmt_2 returns [List<AST.Stmt> ast]
    : a=stmt b=stmt__ {$ast=$b.ast; $ast.add(0, $a.ast);}
    | a=stmt {$ast=new ArrayList<AST.Stmt>(); $ast.addLast($a.ast);}
    ;

stmt__ returns [List<AST.Stmt> ast]
	: COMMA a=stmt_2 {$ast = $a.ast;}
	| {$ast = new ArrayList<AST.Stmt>();}
    ;

stmt returns [AST.Stmt ast]
	: whileStmt {$ast=$whileStmt.ast;}
	| ifStmt {$ast=$ifStmt.ast;}
	| letStmt {$ast = $letStmt.ast;}
	| stmtBase {$ast = $stmtBase.ast;}
    ;

whileStmt returns [AST.WhileStmt ast]
	: WHILE a=expr DO b=stmt_ END {$ast = new AST.WhileStmt(loc($WHILE, $END), $a.ast, $b.ast);};

ifStmt returns [AST.Stmt ast]
	: IF a=expr THEN b=stmt_ ifElseStmt [$IF, $a.ast, $THEN, $b.ast] {$ast = $ifElseStmt.ast;}
    ;

ifElseStmt [Token start, AST.Expr cond, Token endExpr, List<AST.Stmt> statements] returns [AST.Stmt ast]
	: END {$ast = new AST.IfThenStmt(loc(start, $END), $cond, statements);}
	| ELSE stmt_ END { $ast = new AST.IfThenElseStmt(loc(start, $END), $cond, statements, $stmt_.ast);}
    ;

letStmt returns [AST.LetStmt ast]
	: LET defs IN stmt_ END { $ast = new AST.LetStmt(loc($LET, $END), $defs.ast, $stmt_.ast);}
    ;


stmtBase returns [AST.Stmt ast]
	: a=expr {$ast = new AST.ExprStmt(loc($a.ast, $a.ast), $a.ast);}
	| aa=expr ASSIGN bb=expr {$ast = new AST.AssignStmt(loc($aa.ast, $bb.ast), $aa.ast, $bb.ast);}
	| RETURN expr {$ast = new AST.ReturnStmt(loc($RETURN, $expr.ast), $expr.ast);}
    ;


exprEntry returns [AST.Expr ast]
	: e=exprEntry OR expr2nd {$ast = new AST.BinExpr(loc($e.ast, $expr2nd.ast), AST.BinExpr.Oper.OR, $e.ast, $expr2nd.ast);}
	| expr2nd {$ast = $expr2nd.ast;}
    ;

expr2nd returns [AST.Expr ast]
	: e=expr2nd AND expr3rd {$ast = new AST.BinExpr(loc($e.ast, $expr3rd.ast), AST.BinExpr.Oper.AND, $e.ast, $expr3rd.ast);}
	| expr3rd {$ast = $expr3rd.ast;}
    ;


expr3rd returns [AST.Expr ast]
	: a=expr4th EQU b=expr4th {$ast = new AST.BinExpr(loc($a.ast, $b.ast), AST.BinExpr.Oper.EQU, $a.ast, $b.ast);}
	| a=expr4th NEQ b=expr4th {$ast = new AST.BinExpr(loc($a.ast, $b.ast), AST.BinExpr.Oper.NEQ, $a.ast, $b.ast);}
	| a=expr4th LT b=expr4th {$ast = new AST.BinExpr(loc($a.ast, $b.ast), AST.BinExpr.Oper.LTH, $a.ast, $b.ast);}
	| a=expr4th GT b=expr4th {$ast = new AST.BinExpr(loc($a.ast, $b.ast), AST.BinExpr.Oper.GTH, $a.ast, $b.ast);}
	| a=expr4th LEQ b=expr4th {$ast = new AST.BinExpr(loc($a.ast, $b.ast), AST.BinExpr.Oper.LEQ, $a.ast, $b.ast);}
	| a=expr4th GEQ b=expr4th {$ast = new AST.BinExpr(loc($a.ast, $b.ast), AST.BinExpr.Oper.GEQ, $a.ast, $b.ast);}
	| expr4th {$ast = $expr4th.ast;}
    ;


expr4th returns [AST.Expr ast]
	: a=expr4th PLUS b=expr5th {$ast = new AST.BinExpr(loc($a.ast, $b.ast), AST.BinExpr.Oper.ADD, $a.ast, $b.ast);}
	| a=expr4th MINUS b=expr5th {$ast = new AST.BinExpr(loc($a.ast, $b.ast), AST.BinExpr.Oper.SUB, $a.ast, $b.ast);}
	| expr5th {$ast = $expr5th.ast;};



expr5th returns [AST.Expr ast]
	: a=expr5th MUL b=expr6th {$ast = new AST.BinExpr(loc($a.ast, $b.ast), AST.BinExpr.Oper.MUL, $a.ast, $b.ast);}
	| a=expr5th DIV b=expr6th {$ast = new AST.BinExpr(loc($a.ast, $b.ast), AST.BinExpr.Oper.DIV, $a.ast, $b.ast);}
	| a=expr5th MOD b=expr6th {$ast = new AST.BinExpr(loc($a.ast, $b.ast), AST.BinExpr.Oper.MOD, $a.ast, $b.ast);}
	| expr6th {$ast = $expr6th.ast;} 
	;

expr6th returns [AST.Expr ast]
	: expr7th {$ast = $expr7th.ast;}
	| PLUS expr6th {$ast = new AST.PfxExpr(loc($PLUS, $expr6th.ast), AST.PfxExpr.Oper.ADD, $expr6th.ast);}
	| MINUS expr6th {$ast = new AST.PfxExpr(loc($MINUS, $expr6th.ast), AST.PfxExpr.Oper.SUB, $expr6th.ast);}
	| EXCL expr6th {$ast = new AST.PfxExpr(loc($EXCL, $expr6th.ast), AST.PfxExpr.Oper.NOT, $expr6th.ast);}
	| POW expr6th {$ast = new AST.PfxExpr(loc($POW, $expr6th.ast), AST.PfxExpr.Oper.PTR, $expr6th.ast);}
	;

expr7th returns [AST.Expr ast]
	: e=expr7th POW { $ast = new AST.SfxExpr(loc($e.ast, $POW), AST.SfxExpr.Oper.PTR, $e.ast);}
	| e=expr7th DOT IDENTIFIER { $ast = new AST.CompExpr(loc($e.ast, $IDENTIFIER), $e.ast, $IDENTIFIER.getText());} 
	| e=expr7th LPARAN exprs_ RPARAN { $ast = new AST.CallExpr(loc($e.ast, $RPARAN), $e.ast, $exprs_.ast);}
	| e=expr7th LBRCKT expr RBRCKT { $ast = new AST.ArrExpr(loc($e.ast, $RBRCKT), $e.ast, $expr.ast);}
	| prim {$ast = $prim.ast;}
	;


prim returns [AST.Expr ast]
	: LPARAN expr RPARAN {$ast = $expr.ast; $ast.relocate(loc($LPARAN, $RPARAN));}
	| LBRACE expr COLON type1 RBRACE {$ast = new AST.CastExpr(loc($LBRACE, $RBRACE), $type1.ast, $expr.ast);}
	| exprEnd {$ast = $exprEnd.ast;}
	| IDENTIFIER {$ast = new AST.NameExpr(loc($IDENTIFIER), $IDENTIFIER.getText());}
	| SIZEOF type1 {$ast = new AST.SizeExpr(loc($SIZEOF, $type1.ast), $type1.ast);}
	;

exprs_ returns [List<AST.Expr> ast]
	: exprs_2 {$ast = $exprs_2.ast;}
	| {$ast = new ArrayList<AST.Expr>();}
	;

exprs_2 returns [List<AST.Expr> ast]
	: exprEntry exprs__ {$ast = $exprs__.ast; $ast.add(0, $exprEntry.ast);}
	| exprEntry {$ast = new ArrayList<AST.Expr>(); $ast.add(0,$exprEntry.ast);};

exprs__ returns [List<AST.Expr> ast]
	: COMMA exprs_ {$ast = $exprs_.ast;}
	| {$ast = new ArrayList<AST.Expr>();}
	;

expr returns [AST.Expr ast]
	: exprEntry {$ast = $exprEntry.ast;};

exprEnd returns [AST.Expr ast]
	: CONSTCHAR {$ast = new AST.AtomExpr(loc($CONSTCHAR), AST.AtomExpr.Type.CHAR, $CONSTCHAR.getText());}
	| CONSTNUM {$ast = new AST.AtomExpr(loc($CONSTNUM), AST.AtomExpr.Type.INT, $CONSTNUM.getText());}
	| CONSTSTR {$ast = new AST.AtomExpr(loc($CONSTSTR), AST.AtomExpr.Type.STR, $CONSTSTR.getText());}
	| FALSE {$ast = new AST.AtomExpr(loc($FALSE), AST.AtomExpr.Type.CHAR, $FALSE.getText());}
	| TRUE {$ast = new AST.AtomExpr(loc($TRUE), AST.AtomExpr.Type.CHAR, $TRUE.getText());}
	| NULL {$ast = new AST.AtomExpr(loc($NULL), AST.AtomExpr.Type.PTR, "0");}
	;

idColonType returns [AST.CompDefn ast]
	: IDENTIFIER COLON a=type1 {$ast = new AST.CompDefn(loc($IDENTIFIER, $a.ast), $IDENTIFIER.getText(), $a.ast);};

typeTuple returns [List<AST.CompDefn> ast]
	: a=idColonType b=typeTuple_ {$ast = $b.ast; $ast.add(0, $a.ast);}
	| a=idColonType {$ast = new ArrayList<AST.CompDefn>(); $ast.add(0, $a.ast);}
	;

typeTuple_ returns [List<AST.CompDefn> ast]
	: COMMA a=typeTuple {$ast = $a.ast;}
	| {$ast = new ArrayList<AST.CompDefn>();}
	;


functionCallNoParams returns [List<AST.Type> ast]
	: a=functionCall {$ast = $a.ast;}
	| {$ast = new ArrayList<AST.Type>();};

functionCall returns [List<AST.Type> ast]
	: a=type1 {$ast = new ArrayList<AST.Type>(); $ast.add(0, $a.ast);}
	| a=type1 b=functionCall_ {$ast = $b.ast; $ast.add(0, $a.ast);}
	;

functionCall_ returns [List<AST.Type> ast]
	: COMMA a=functionCall {$ast = $a.ast;} 
	| {$ast = new ArrayList<AST.Type>();}
	;

type1 returns [AST.Type ast]
	: a=syn21 {$ast = $a.ast;}
	;	

syn21 returns [AST.Type ast]
	: LT a=typeTuple GT {$ast = new AST.StrType(loc($LT, $GT), $a.ast);} //Struct
	| LBRACE b=typeTuple RBRACE {$ast = new AST.UniType(loc($LBRACE, $RBRACE), $b.ast);}
	| LPARAN c=functionCallNoParams RPARAN COLON d=type1 {$ast = new AST.FunType(loc($LPARAN, $d.ast), $c.ast, $d.ast);}
	| e=type1Over {$ast = $e.ast;};

type1Over returns [AST.Type ast]
	: LBRCKT CONSTNUM RBRCKT a=type1 {$ast = new AST.ArrType(loc($LBRCKT, $a.ast), $a.ast, $CONSTNUM.getText());}
	| POW a=type1 {$ast = new AST.PtrType(loc($POW, $a.ast), $a.ast);}
	| ab=atomType {$ast = $ab.ast;}
	;

atomType returns [AST.Type ast]
	: BOOL {$ast = new AST.AtomType(loc($BOOL), AST.AtomType.Type.BOOL);}
	| INT {$ast = new AST.AtomType(loc($INT), AST.AtomType.Type.INT); }
	| CHAR {$ast = new AST.AtomType(loc($CHAR), AST.AtomType.Type.CHAR); }
	| VOID {$ast = new AST.AtomType(loc($VOID), AST.AtomType.Type.VOID); }
	| IDENTIFIER {$ast = new AST.NameType(loc($IDENTIFIER), $IDENTIFIER.getText());}
	;