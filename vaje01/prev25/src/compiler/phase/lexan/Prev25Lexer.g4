lexer grammar Prev25Lexer;

@header {
	package compiler.phase.lexan;

	import compiler.common.report.*;
}

@members {
    @Override
	public LexAn.LocLogToken nextToken() {
		return (LexAn.LocLogToken) super.nextToken();
	}
}

WHITESPACE      : [ \n\r]+ -> skip ;
AND		: '&';
OR		: '|';
EQU		: '==';
NEQ		: '!=';
LT		: '<';
GT		: '>';
LEQ		: '<=';
GEQ		: '>=';
MUL		: '*';
DIV		: '/';
MOD		: '%';
PLUS	: '+';
MINUS	: '-';
EXCL	: '!';
DOT		: '.';
POW		: '^';
ASSIGN	: '=';
COLON	: ':';
COMMA	: ',';
LBRACE	: '{';
RBRACE	: '}';
LPARAN	: '(';
RPARAN	: ')';
LBRCKT  : '[';
RBRCKT	: ']';
INT     : 'int';
BOOL    : 'bool';
CHAR    : 'char';
DO      : 'do';
ELSE    : 'else';
END     : 'end';
FALSE   : 'false';
FUN     : 'fun';
IF      : 'if';
IN      : 'in';
LET     : 'let';
NULL    : 'null';
RETURN  : 'return';
SIZEOF  : 'sizeof';
THEN    : 'then';
TRUE    : 'true';
TYP     : 'typ';
VAR     : 'var';
VOID    : 'void';
WHILE   : 'while';
IDENTIFIER	: [A-Za-z][A-Za-z0-9_]*;
CONSTNUM	: [0-9]+;
COMMENT	: '#'~[\r\n]*;
CONSTCHAR	: '\''( PRVI_DEL | DRUGI_DEL | ZADNJI_DEL | DOUBLE_SLASH | CHARESCAPE | HEX )'\'';
fragment PRVI_DEL	: [\u001f-\u0026];
fragment DRUGI_DEL	: [\u0028-\u005b];
fragment ZADNJI_DEL	: [\u005d-\u007f];
fragment PRVI_DEL_STR	: [\u001f-\u0021];
fragment DRUGI_DEL_STR	: [\u0023-\u005b];
fragment DOUBLE_SLASH	: '\u005c\u005c';
fragment CHARESCAPE	: '\u005c\u0027';
fragment STRINGESCAPE	: '\u005c\u0022';
fragment HEX	: '\\0x'[A-Fa-f0-9][A-Fa-f0-9];
CONSTSTR	: '"'(PRVI_DEL_STR | DRUGI_DEL_STR | ZADNJI_DEL | DOUBLE_SLASH | STRINGESCAPE | HEX)+'"';
ERROR	: . ;