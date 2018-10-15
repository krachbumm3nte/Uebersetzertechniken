grammar IP;



IPADDRESS: IPBLOCK '.' IPBLOCK '.' IPBLOCK '.' IPBLOCK;






fragment IPBLOCK: DIGIT DIGIT? DIGIT?;

fragment DIGIT: [0-9];

WS: [ \n\t\r] -> skip;