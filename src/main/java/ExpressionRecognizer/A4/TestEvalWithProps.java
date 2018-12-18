package ExpressionRecognizer.A4;

import ExpressionCalculator.A2.CalculatorBaseListener;
import ExpressionCalculator.A2.CalculatorLexer;
import ExpressionCalculator.A2.CalculatorParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.IdentityHashMap;
import java.util.Map;

public class TestEvalWithProps extends CalculatorBaseListener {
    Map<ParseTree, Double> value = new IdentityHashMap<>();
    //ParseTreeProperty<Double> value = new ParseTreeProperty<>();

    TestEvalWithProps() {
    }

    public static void main(String[] args) {
        String s = "1+2";
        ANTLRInputStream input = new ANTLRInputStream(s);
        CalculatorLexer lexer = new CalculatorLexer(input);
        CalculatorParser parser = new CalculatorParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.stat();
        ParseTreeWalker walker = new ParseTreeWalker();
        TestEvalWithProps bla = new TestEvalWithProps();
        walker.walk(bla, tree);
        System.out.println(bla.getValue(tree));

    }

    public void exitInt(CalculatorParser.IntContext ctx) {
        String doubleText = ctx.INT().getText();
        setValue(ctx, Double.valueOf(doubleText));
    }

    public void exitAssign(CalculatorParser.AssignContext ctx) {
        System.out.println("assign");
    }

    /**
     * expr op=('+'|'-') expr
     */
    public void exitAddSub(CalculatorParser.AddSubContext ctx) {
        double left = getValue(ctx.expr(0));
        double right = getValue(ctx.expr(1));
        if (ctx.op.getType() == CalculatorParser.ADD) ctx.value = left + right;
        ctx.value = left - right; // must be sub
    }

    /**
     * expr op=('+'|'-') expr
     */
    public void exitMulDiv(CalculatorParser.MulDivContext ctx) {
        double left = getValue(ctx.expr(0));
        double right = getValue(ctx.expr(1));
        if (ctx.op.getType() == CalculatorParser.MUL) ctx.value = left * right;
        ctx.value = left / right; // must be div
    }

    public void exitPrintExpr(CalculatorParser.PrintExprContext ctx) {
        setValue(ctx, getValue(ctx.expr()));   //???????? PrintExprContext oder
    }

    public void setValue(ParseTree node, double value) {
        this.value.put(node, value);
    }

    public double getValue(ParseTree node) {
        System.out.println(value);
        return value.get(node);
    }
}
