grammar recognizer1;

@parser::members {
    int eval(int left, int op, int right) {
        switch ( op ) {
            case ADD    : return left + right;
            case SUB    : return left - right;
            case MULT   : return left * right;
            case DIV    : return left / right;
        }
        return 0;
    }
}


stat returns [int v]: a=expr {$v = $a.v; System.out.println($v);};
expr returns [int v]: '(' e=expr ')' {$v = $e.v;}
    | a=expr op=('*'|'/') b=expr {$v = eval($a.v, $op.type, $b.v);}
    | a=expr op=('+'|'-') b=expr {$v = eval($a.v, $op.type, $b.v);}
    | d=INT {$v = $d.int;};

INT     :[0-9]+;

ADD     : '+';
SUB     : '-';
MULT    : '*';
DIV     : '/';

WS: [ \n\t\r] -> skip;