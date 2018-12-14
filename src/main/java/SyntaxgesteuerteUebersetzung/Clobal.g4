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

ifStat: 'if' '('expr ')' stat ('else' stat)? ;

forStat: 'for' '(' assignStat ';' expr ';' assignStat ')' block ;

returnStat: 'return' expr ;

printStat: 'printf' '(' expr ')';

varDecl:   type ID ';' ;

type:   'int'  ;  

expr:   '-' expr       
    |   '!' expr           
    |   expr ('*'|'/') expr
    |   expr ('+'|'-') expr
    |   expr ('=='|'!='|'<'|'>') expr 
    |   expr '?' expr ':' expr     
    |   ID                
    |   INT               
    |   '(' expr ')'         
    |   ID '(' ')'         
    ;

INT: [0-9]+;
ID:     [a-z] [a-zA-Z0-9]*;
WS: [ \n\r\t] -> skip;

