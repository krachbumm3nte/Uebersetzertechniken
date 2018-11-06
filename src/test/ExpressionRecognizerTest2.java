import ExpressionRecognizer.A2.A2Lexer;
import ExpressionRecognizer.A2.A2Parser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

public class ExpressionRecognizerTest2 {

    @Test
    public void A1Test() {
        ANTLRInputStream input = new ANTLRInputStream("10*2/2+10");
        A2Lexer lexer = new A2Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        A2Parser parser = new A2Parser(tokens);

        assert (parser.getNumberOfSyntaxErrors() == 0);
    }
}