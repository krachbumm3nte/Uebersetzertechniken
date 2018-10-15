package ipAddress;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import time.TimeLexer;


public class Test {
    public static void main(String[] args) throws Exception {
        CharStream input = null;
        // Pick an input stream (filename from commandline or stdin)
        if (args.length>0) input = new ANTLRFileStream(args[0]);
        else input = new ANTLRInputStream(System.in);

        IPLexer lex = new IPLexer(input);
        Token t=null;
        do {
            t = lex.nextToken();
            if(t.getType() == IPLexer.IPADDRESS) {
                System.out.println(t.getText()+ " is a valid ip address\n");
            }
        } while ( t.getType()!=Token.EOF );
    }
}
