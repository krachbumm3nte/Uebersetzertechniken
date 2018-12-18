grammar Clobal;
@header {
}

file:   (functionDecl | varDecl)+  ; 

functionDecl
    :   type ID '('')' block 
    ;

block:  '{' stat* '}' ;  

stat:   block             
    |   ifStat
    |   forStat
    |   returnStat ';'
    |   assignStat  ';'
    |   printStat  ';'
    |   expr ';'
    ;

assignStat:  ID '=' expr ;

ifStat: 'if' '('a=expr ')' b=stat ('else' elsestat=stat)? ;

forStat: 'for' '(' assignStat ';' expr ';' assignStat ')' block ;

returnStat: 'return' expr ;

printStat: 'printf' '(' expr ')';

varDecl:   type ID ';' ;

type:   'int'  ;

expr:   '-' expr                        #Neg
    |   '!' expr                        #Not
    |   expr ('*') expr                    #Mult
    |   expr op=('+'|'-') expr             #AddSub
    |   expr op=('=='|'!='|'<'|'>') expr   #Comp
    |   expr '?' expr ':' expr          #CondOp
    |   ID                              #IdExpr
    |   INT                             #IntExpr
    |   '(' expr ')'                    #Brackets
    |   ID '(' ')'                      #IdBrack
    ;

ID: [a-z] [a-zA-Z0-9]*;
INT: [0-9]*;
WS: [ \n\r\t] -> skip;