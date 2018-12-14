parser grammar XmlToJsonParser;
options { tokenVocab=XmlToJsonLexer; }

document:       prolog? misc* element misc*;

prolog:         EMLDeclOpen attribute* SPECIAL_CLOSE;

content:        chardata? ((element | reference | CDATA | PI | COMMENT) chardata?)*;

element:        '<' Name attribute* '>' content '<' '/' Name '>'
                | '<' Name attribute* '/>';

reference:      EntityRef | CharRef;

attribute:      Name '=' STRING;

chardata:       TEXT | SEA_WS;

misc:           COMMENT | PI | SEA_WS;