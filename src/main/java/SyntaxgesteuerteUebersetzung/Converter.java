package SyntaxgesteuerteUebersetzung;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Converter extends ClobalBaseListener {
    public Stack<ST> sts = new Stack<ST>();
    Map<String, Integer> globals = new HashMap<>();
    Stack<String> last = new Stack<>();
    int counter = 0;
    STGroup template = new STGroupFile("./src/main/java/SyntaxgesteuerteUebersetzung/pcode.stg");


    @Override
    public void exitVarDecl(ClobalParser.VarDeclContext ctx) {
        String name = ctx.children.get(1).getText();
        globals.put(name, globals.size());
        //System.out.println(globals);
    }

    @Override
    public void enterFunctionDecl(ClobalParser.FunctionDeclContext ctx) {
        //Print globals
        sts.push(template.getInstanceOf("global").add("val", globals.size()));
        String name = ctx.getChild(1).getText();
        sts.push(template.getInstanceOf("funkDec").add("name", name));
    }

    @Override
    public void exitAssignStat(ClobalParser.AssignStatContext ctx) {
        int name = globals.get(ctx.getChild(0).getText());
        sts.push(template.getInstanceOf("assignStat").add("name", name).add("val", ctx.getChild(2).getChild(0).getText()));
    }

    @Override
    public void exitPrintStat(ClobalParser.PrintStatContext ctx) {
        sts.push(template.getInstanceOf("printStat"));
    }


    @Override
    public void enterIfStat(ClobalParser.IfStatContext ctx) {
        last.push("cont" + counter++);
        Integer a = globals.get(ctx.getChild(2).getChild(0).getChild(0).getText());
        Integer b = globals.get(ctx.getChild(2).getChild(2).getChild(0).getText());
        String cond = ctx.getChild(2).getChild(1).getText();
        String brf = "brf";
        switch (cond) {
            case ">":
                cond = "ilt";
                Integer tmp = a;
                a = b;
                b = tmp;
                break;
            case "<":
                cond = "ilt";
                break;
            case "==":
                cond = "ieq";
                break;
            case "!=":
                cond = "ieq";
                brf = "brt";
                break;
        }
        sts.push(template.getInstanceOf("ifStat").add("cond", cond).add("a", a).add("b", b).add("cont", last.peek()).add("brf", brf));
    }

    @Override
    public void enterStat(ClobalParser.StatContext ctx) {
        System.out.println("Enter Stat");
        try {
            System.out.println(ctx.parent.getChild(5).getText());
            if (ctx.parent.getChild(5).getText().contains("else")) {
                // else block
                sts.push(template.getInstanceOf("else").add("cont", last.peek()));
                last.pop();
            }

        } catch (NullPointerException e) {

        }
    }


}
