grammar Calculator;

stat: expr                  # printExpr
    | ID '=' expr           # assign
    ;

expr: expr op=('*'|'/') expr    # MulDiv
    | expr op=('+'|'-') expr    # AddSub
    | INT                       # int
    | ID                        # id
    | '(' expr ')'              # parens
    ;


MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';

WS: [ \n\t\r] -> skip;
INT:[0-9]+;
