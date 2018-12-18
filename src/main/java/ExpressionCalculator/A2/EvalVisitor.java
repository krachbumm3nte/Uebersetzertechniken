package ExpressionCalculator.A2;

import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends CalculatorBaseVisitor<Double> {
    /**
     * "memory" for our calculator; variable/value pairs go here
     */
    Map<String, Double> memory = new HashMap<String, Double>();

    /**
     * ID '=' expr NEWLINE
     */
    @Override
    public Double visitAssign(CalculatorParser.AssignContext ctx) {
        String id = ctx.ID().getText();  // id is left-hand side of '='
        double value = visit(ctx.expr());   // compute value of expression on right
        memory.put(id, value);           // store it in our memory
        return value;
    }

    /**
     * expr NEWLINE
     */
    @Override
    public Double visitPrintExpr(CalculatorParser.PrintExprContext ctx) {
        Double value = visit(ctx.expr()); // evaluate the expr child
        System.out.println(value);         // print the result
        return 0.0;                          // return dummy value
    }

    /**
     * INT
     */
    @Override
    public Double visitInt(CalculatorParser.IntContext ctx) {
        return Double.valueOf(ctx.INT().getText());
    }

    /**
     * ID
     */
    @Override
    public Double visitId(CalculatorParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) return memory.get(id);
        return 0.0;
    }

    /**
     * expr op=('*'|'/') expr
     */
    @Override
    public Double visitMulDiv(CalculatorParser.MulDivContext ctx) {
        double left = visit(ctx.expr(0));  // get value of left subexpression
        double right = visit(ctx.expr(1)); // get value of right subexpression
        if (ctx.op.getType() == CalculatorParser.MUL) return left * right;
        return left / right; // must be DIV
    }

    /**
     * expr op=('+'|'-') expr
     */
    @Override
    public Double visitAddSub(CalculatorParser.AddSubContext ctx) {
        double left = visit(ctx.expr(0));  // get value of left subexpression
        double right = visit(ctx.expr(1)); // get value of right subexpression
        if (ctx.op.getType() == CalculatorParser.ADD) return left + right;
        return left - right; // must be SUB
    }

    /**
     * '(' expr ')'
     */
    @Override
    public Double visitParens(CalculatorParser.ParensContext ctx) {
        return visit(ctx.expr()); // return child expr's value
    }
}
