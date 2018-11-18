import expressionCalculator.A1.recognizer1Lexer;
import expressionCalculator.A1.recognizer1Parser;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

public class ExpressionCalculatorTest1 {

    @Test
    public void A1Test() {
        ANTLRInputStream input = new ANTLRInputStream("10*2/2+10");
        recognizer1Lexer lexer = new recognizer1Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        recognizer1Parser parser = new recognizer1Parser(tokens);
        recognizer1Parser.StatContext n = parser.stat();
        System.out.println(n.toString());
        assert (parser.getNumberOfSyntaxErrors() == 0);
    }
}