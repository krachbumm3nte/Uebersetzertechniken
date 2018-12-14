grammar recognizer1;

@header {
import java.util.*;
}

@parser::members {
    Map<String, Double> memory = new HashMap<String, Double>();

    Double eval(Double left, int op, Double right) {
        switch ( op ) {
            case ADD:   return left + right;
            case SUB:   return left - right;
            case MULT:  return left * right;
            case DIV:   return left / right;
            case POT1:  return Math.pow(left,right);
            case POT2:  return Math.pow(left,right);
            case LT:    return left < right? 1.0:0;
            case GT:    return left > right? 1.0:0;
            case LE:    return left <= right? 1.0:0;
            case GE:    return left >= right? 1.0:0;
            case EQ:    return left == right? 1.0:0;
        }
        System.out.println("returning zero");
        return 0.0;
    }
}

prog: stat+;

stat:
     CLEAR NEWLINE {memory = new HashMap<String, Double>(); System.out.println("memory wiped.");} #ForRealThough
    | a=term NEWLINE{System.out.println($a.v);} #St
    | ID '=' term NEWLINE {memory.put($ID.text, $term.v); System.out.println("added "+ $ID.text+ "=" + $term.v + " to memory.");} #Identifier2
    | expr NEWLINE {System.out.println($expr.v);} #FuckNamingThese;

term returns [Double v]:
    x=term '?' y=term ':' z=expr {
             if ($x.v != 0) {
                 $v = $y.v;
             } else $v = $z.v;
    }
    | a=expr op=('<'|'>'|'<='|'>='|'==') b=expr {$v = eval($a.v, $op.type, $b.v);}
    | a=expr {$v = $a.v;};

expr returns [Double v]:
      '(' e=expr ')' {$v = $e.v;}   #Express
    | a=expr op=('^'|'**') b=expr {$v = eval($a.v, $op.type, $b.v);} #Potency
    | a=expr op=('*'|'/') b=expr {$v = eval($a.v, $op.type, $b.v);} #MultDiv
    | a=expr op=('+'|'-') b=expr {$v = eval($a.v, $op.type, $b.v);} #AddSub
    | DOUBLE {$v = Double.valueOf($DOUBLE.getText());} #Doub
    | ID
    {
    String id = $ID.text;
    $v = memory.containsKey(id) ? memory.get(id) : 0.0;
    } #Identifier
    | '(' expr ')' {$v = $expr.v;} #Brackets
    ;

CLEAR   : 'clear';
DOUBLE     :INT ('.' INT)?;
INT : [0-9]+;
ID: [a-z] [a-zA-Z0-9]*;
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
EQ      : '==';


NEWLINE: '\r'? '\n';
WS : [ \t] -> skip;