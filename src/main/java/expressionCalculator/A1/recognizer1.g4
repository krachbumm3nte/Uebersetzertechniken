grammar recognizer1;
@parser::members {
    Double eval(Double left, int op, Double right) {
        switch ( op ) {
            case ADD    :
                System.out.println("+");
                return left + right;
            case SUB    :
                System.out.println("-");
                return left - right;
            case MULT   :
                System.out.println("*");
                return left * right;
            case DIV    :
                System.out.println("/");
                return left / right;
            case POT1:
                System.out.println("^");
                return Math.pow(left,right);
            case POT2:
                System.out.println("**");
                return Math.pow(left,right);
            case LT:
                System.out.println("<");
                return left < right? 1.0:0;
            case GT:
                System.out.println(">");
                return left > right? 1.0:0;
            case LE:
                System.out.println("<=");
                return left <= right? 1.0:0;
            case GE:
                System.out.println(">=");
                return left >= right? 1.0:0;
            case EQ:
                System.out.println("==");
                return left == right? 1.0:0;
        }
        System.out.println("returning zero");
        return 0.0;
    }
}


stat returns [Double v]: a=term {$v = $a.v; System.out.println("returns: " + $a.v);} #St;
term returns [Double v]: a=expr op=('<'|'>'|'<='|'>='|'=') b=expr {$v = eval($a.v, $op.type, $b.v);}
                        | a=expr {$v = $a.v;};
expr returns [Double v]: '(' e=expr ')' {$v = $e.v;}   #Express
    | a=expr op=('^'|'**') b=expr {$v = eval($a.v, $op.type, $b.v);} #Potency
    | a=expr op=('*'|'/') b=expr {$v = eval($a.v, $op.type, $b.v);} #MultDiv
    | a=expr op=('+'|'-') b=expr {$v = eval($a.v, $op.type, $b.v);} #AddSub
    | DOUBLE {$v = Double.valueOf($DOUBLE.getText()); System.out.println(Double.valueOf($DOUBLE.getText()));} #Doub;

DOUBLE     :DIGIT ('.' DIGIT)?;

DIGIT : [0-9]+;

POT1    : '^';
POT2    :'**';
ADD     : '+';
SUB     : '-';
MULT    : '*';
DIV     : '/';
LT      : '<';
GT      : '>';
LE      : '<=';
GE      : '>=';
EQ      : '=';

WS: [ \n\t\r] -> skip;