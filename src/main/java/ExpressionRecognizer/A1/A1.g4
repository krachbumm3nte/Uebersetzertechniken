grammar A1;



prog : expr+;

expr : expr ('*'|'/') expr
        | expr ('+'|'-') expr
        | '(' expr ')'
        | INT;

INT : [0-9]+;

WS :[\n\t\r ] -> skip;