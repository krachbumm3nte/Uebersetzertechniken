package expressionCalculator;


import expressionCalculator.A1.recognizer1Lexer;
import expressionCalculator.A1.recognizer1Parser;
import expressionCalculator.A2.recognizer2Lexer;
import expressionCalculator.A2.recognizer2Parser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.util.Scanner;

public class asdf {
    public static void main(String[] args){
        ANTLRInputStream input = new ANTLRInputStream("2+2**3*4");
        recognizer1Lexer lexer = new recognizer1Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        recognizer1Parser parser = new recognizer1Parser(tokens);
        parser.setBuildParseTree(false);
        recognizer1Parser.StatContext n = parser.stat();
        assert (parser.getNumberOfSyntaxErrors() == 0);
    }
}
