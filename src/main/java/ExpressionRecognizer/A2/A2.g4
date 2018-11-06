grammar A2;

prog: stat+;
stat: expr NL?;
expr: term(('+' | '-')term)*;
term: fact (('*' | '/') fact)*;
fact: INT | ('+' | '-') fact | '(' expr ')';

NL: '\r'? '\n';
INT: [0-9]+;
WS: [ \t]+ -> skip;