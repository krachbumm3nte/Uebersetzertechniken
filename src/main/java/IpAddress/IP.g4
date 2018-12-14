grammar IP;


IPADDRESS: IPBLOCK '.' IPBLOCK '.' IPBLOCK '.' IPBLOCK;



fragment IPBLOCK: [0-1]? [0-9]? [0-9] | '2' [0-4] [0-9] | '2' [0-5] [0-5];


WS: [ \n\t\r] -> skip;