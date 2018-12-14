grammar Halstead;



OPERATOR: OP | SCSPEC | TYPE_QUAL | RESERVED;
OPERAND: TYPESPEC | IDENTIFIER;


fragment OP:
        '!'|'!='|'%'|'%='|'&'|'&&'|'&='|'('|'*'|'*='
        |'+'|'++'|'+='|','|'-'|'--'|'-='|'->'|'...'|'/'
        |'/='|'::'|'<'|'<<'|'<<='|'<='|'=='|'>'|'>='|'>>'
        |'>>='|'?'|'['|'^'|'^='|'{'|'||'|'='|'~'|';';

fragment SCSPEC:
        'auto'
        | 'extern'
        | 'inline'
        | 'register'
        | 'static'
        | 'typedef'
        | 'virtual'
        | 'mutable';

fragment TYPE_QUAL:
        'friend'
        | 'volatile,asm';

fragment RESERVED:
        'break'
        | 'case'
        | 'class'
        | 'continue'
        | 'default'
        | 'delete'
        | 'while('
        | 'else'
        | 'enum'
        | 'for('
        | 'goto'
        | 'if('
        | 'new'
        | 'operator'
        | 'private'
        | 'protected'
        | 'public'
        | 'return'
        | 'sizeof'
        | 'struct'
        | 'switch('
        | 'this'
        | 'union'
        | 'namespace'
        | 'using'
        | 'try'
        | 'catch'
        | 'throw'
        | 'const_cast'
        | 'static_cast'
        | 'dynamic_cast'
        | 'reinterpret_cast'
        | 'typeid'
        | 'template'
        | 'explicit'
        | 'true'
        | 'false'
        | 'typename';



fragment TYPESPEC:
    'bool'
    | 'char'
    | 'double'
    | 'float'
    | 'int'
    | 'long'
    | 'short'
    | 'signed'
    | 'unsigned'
    | 'void';

fragment IDENTIFIER:
    | LETTER (LETTER|DIGIT)*
    | '"' .*? '"'
    | '\'' .*? '\''
    | DIGIT+
    | DIGIT '.' DIGIT+;


IGNORE:  '//' .*? '\r' ? '\n'
      | '/*' .*? '*/'// no need for a NEWLINE at the end
      | ')'
      | '}'
      | ']'
      | ':'
      | 'do'
      | '#include' .*? '\r' ? '\n';


fragment LETTER: [a-zA-Z];

fragment DIGIT: [0-9];


WS: [ \r\n\t] -> skip;


