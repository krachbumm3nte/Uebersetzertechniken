import ExpressionRecognizer.A3.A3Lexer;
import ExpressionRecognizer.A3.A3Parser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

public class ExpressionRecognizerTestt3 {

    @Test
    public void A1Test() {
        ANTLRInputStream input = new ANTLRInputStream("10*2/2+10");
        A3Lexer lexer = new A3Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        A3Parser parser = new A3Parser(tokens);

        assert (parser.getNumberOfSyntaxErrors() == 0);
    }
}