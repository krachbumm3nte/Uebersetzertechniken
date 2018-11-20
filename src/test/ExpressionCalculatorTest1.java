import expressionCalculator.A1.recognizer1Lexer;
import expressionCalculator.A1.recognizer1Parser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import java.io.IOException;

public class ExpressionCalculatorTest1 {

    @Test
    public void A1Test() throws IOException {
        ANTLRInputStream input = new ANTLRInputStream("2+2");
        recognizer1Lexer lexer = new recognizer1Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        recognizer1Parser parser = new recognizer1Parser(tokens);
        parser.setBuildParseTree(false);
        recognizer1Parser.StatContext n = parser.stat();
        assert (parser.getNumberOfSyntaxErrors() == 0);
    }
}
