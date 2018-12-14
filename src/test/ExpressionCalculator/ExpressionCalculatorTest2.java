package ExpressionCalculator;

import ExpressionCalculator.A2.CalculatorLexer;
import ExpressionCalculator.A2.CalculatorParser;
import ExpressionCalculator.A2.EvalVisitor;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.junit.runner.RunWith;


import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;

@RunWith(JUnitParamsRunner.class)
public class ExpressionCalculatorTest2 {
    @Test
    @Parameters({"10*2/2+10", "10+2*4", "5+3*3"," 10 * 2 / 2 + 4"})
    public void A2Test(String s) {
        ANTLRInputStream input = new ANTLRInputStream(s);
        CalculatorLexer lexer = new CalculatorLexer(input);
        CalculatorParser parser = new CalculatorParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.stat();
        EvalVisitor eval = new EvalVisitor();
        System.out.println(s +" = " );
        eval.visit(tree);
        assert(parser.getNumberOfSyntaxErrors() == 0);

    }
}
