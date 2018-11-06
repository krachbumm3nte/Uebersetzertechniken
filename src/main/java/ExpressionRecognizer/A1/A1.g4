grammar A1;



p1
rog : expr+;

expr : expr ('*'|'/') expr
        | expr ('+'|'-') expr
        | '(' expr ')'
        | INT;

INT : [0-9]+;

WS :[\n\t\r ] -> skip;