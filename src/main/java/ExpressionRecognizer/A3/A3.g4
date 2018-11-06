grammar A3;

r: expr+;
expr: expr ('*'|'/') expr
    | expr ('+'|'-') expr
    | INT;

INT: [0-9]+;
WS: [ \t\n\r] -> skip;