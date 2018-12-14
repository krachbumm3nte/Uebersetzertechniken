package ExpressionRecognizer;

import ExpressionRecognizer.A1.A1Lexer;
import ExpressionRecognizer.A1.A1Parser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

public class ExpressionRecognizerTest1 {

    @Test
    public void A1Test() {
        ANTLRInputStream input = new ANTLRInputStream("10*2/2+10");
        A1Lexer lexer = new A1Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        A1Parser parser = new A1Parser(tokens);

        assert (parser.getNumberOfSyntaxErrors() == 0);
    }
}