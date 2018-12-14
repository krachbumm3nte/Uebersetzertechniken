grammar Time;

/*
 *Parser Rules
*/

TIMESTAMP: HOUR SEPARATOR MINSEC  (SEPARATOR MINSEC)?;

HOUR: [0-1] [0-9]
       |'2' [0-3];

MINSEC: [0-5] [0-9];

SEPARATOR: ':';

/*
 *Lexer Rules
*/



fragment DIGIT   : [0-9] ;

WS: [ \n\t\r] -> skip;