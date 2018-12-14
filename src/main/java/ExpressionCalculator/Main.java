package ExpressionCalculator;


import ExpressionCalculator.A1.recognizer1Lexer;
import ExpressionCalculator.A1.recognizer1Parser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws Exception{
        String inputfile = null;
        if ( args.length>0) inputfile = args[0];
        InputStream is = System.in;
        if ( inputfile != null) is = new FileInputStream(inputfile);
        ANTLRInputStream input = new ANTLRInputStream("2+2\n" +
                "15**2\n" +
                "13==57\n" +
                "50>23" +
                "14**2\n" +
                "a = 5\n" +
                "a\n" +
                "b = 3\n" +
                "a+b\n" +
                "c=b\n" +
                "a+c\n" +
                "clear \n" +
                "a\n" +
                "3<5\n" +
                "a=3>=5?13:23\n");
                recognizer1Lexer lexer = new recognizer1Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        recognizer1Parser parser = new recognizer1Parser(tokens);
        ParseTree tree = parser.prog();
        System.out.println(tree.toStringTree(parser));
    }
}
