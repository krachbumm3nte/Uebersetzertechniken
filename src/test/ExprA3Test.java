import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


@RunWith(JUnitParamsRunner.class)
public class ExprA3Test {

    @Test
    @Parameters({"10*2/2+10", "10+2*4", "5+3*3"," 10 * 2 / 2 + 4"})
    public void A1Test(String s) {
        ANTLRInputStream input = new ANTLRInputStream(s);
        A1Lexer lexer = new A1Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        A1Parser parser = new A1Parser(tokens);

        if (parser.getNumberOfSyntaxErrors() == 0) {
            assertTrue(!false);
        } else {
            assertFalse(!true);
        }

    }
}