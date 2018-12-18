package SyntaxgesteuerteUebersetzung;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.*;




public class Converter extends ClobalBaseListener {
    public final String ELSEBRANCH = "elsebr";
    public final String ENDIF = "endif";


    Map<String, Integer> globals = new HashMap<>();
    public Stack<ST> sts = new Stack<ST>();
    int branchCounter = 0;
    STGroup template = new STGroupFile("./src/main/java/SyntaxgesteuerteUebersetzung/pcode.stg");
    boolean isElseBlock= false;

    public void swapLastTemplatesOnStack() throws Exception {
        if(sts.size() > 2) {
            throw new Exception("attempted to swap elements on a too small stack!");
        } else {
            ST val1 = sts.pop();
            ST val2 = sts.pop();
            sts.push(val1);
            sts.push(val2);
        }
    }

    @Override
    public void exitVarDecl(ClobalParser.VarDeclContext ctx) {
        String name = ctx.children.get(1).getText();
        globals.put(name, globals.size());
        //System.out.println(globals);
        if (!sts.isEmpty()) {
            sts.pop();
        }
        sts.push(template.getInstanceOf("global").add("val", globals.size())); //TODO: nicht hier !!!

    }

    @Override
    public void enterFunctionDecl(ClobalParser.FunctionDeclContext ctx) {
        String name = ctx.getChild(1).getText();
        sts.push(template.getInstanceOf("funkDec").add("name", name));
    }

    @Override
    public void exitAssignStat(ClobalParser.AssignStatContext ctx) {
        int name = globals.get(ctx.getChild(0).getText());
        sts.push(template.getInstanceOf("assignStat").add("name",name));
        /*sts.push(template.getInstanceOf("assignStat").add("name", name).add("val", ctx.getChild(2).getChild(0).getText()));*/
    }

    @Override
    public void exitPrintStat(ClobalParser.PrintStatContext ctx) {
        sts.push(template.getInstanceOf("printStat"));
    }

    @Override
    public void enterIfStat(ClobalParser.IfStatContext ctx) {
        if(ctx.elsestat != null) isElseBlock = true;
    }

    @Override
    public void exitIfStat(ClobalParser.IfStatContext ctx) {
        if(isElseBlock) {
            sts.push(template.getInstanceOf("else")).add("cont", ENDIF + branchCounter);
        } else {
            sts.push(template.getInstanceOf("else")).add("cont", ELSEBRANCH + branchCounter);
        }
        isElseBlock = false;
    }

    @Override public void exitReturnStat(ClobalParser.ReturnStatContext ctx) {
        sts.push(template.getInstanceOf("ReturnStat"));
     /*   if(ifContext) {
            branch.push(template.getInstanceOf("ReturnStat"));
        } else {
            sts.push(template.getInstanceOf("ReturnStat"));
        } */
    }


    @Override
    public void enterStat(ClobalParser.StatContext ctx) {
        try{
            if(isElseBlock && ctx.parent.getChild(6) == ctx) {
                sts.push(template.getInstanceOf("br")).add("br", ENDIF + branchCounter);
                sts.push(template.getInstanceOf("else")).add("cont", ELSEBRANCH + branchCounter);
            }
        } catch (NullPointerException e) {}



    }

    @Override public void exitIntExpr(ClobalParser.IntExprContext ctx) {
        sts.push(template.getInstanceOf("loadConst").add("a",ctx.getText()));
    }

    @Override public void exitIdExpr(ClobalParser.IdExprContext ctx) {
        sts.push(template.getInstanceOf("loadVar").add("a",globals.get(ctx.getText())));

    }


    @Override
    public void exitAddSub(ClobalParser.AddSubContext ctx) {
        if(ctx.op.getText() == "+") {
            sts.push(template.getInstanceOf("add"));
        } else {
            sts.push(template.getInstanceOf("sub"));
        }
    }

    @Override
    public void exitMult(ClobalParser.MultContext ctx) {
        sts.push(template.getInstanceOf("mult"));
    }

    @Override
    public void enterIdBrack(ClobalParser.IdBrackContext ctx) {
        sts.push(template.getInstanceOf("call")).add("func", ctx.getChild(0).getText()+ "()");
    }

    @Override
    public void exitNeg(ClobalParser.NegContext ctx) {
        sts.push(template.getInstanceOf("loadConst").add("a", -1));
        sts.push(template.getInstanceOf("mult"));
    }


    @Override public void exitComp(ClobalParser.CompContext ctx) {
        String branchname = ELSEBRANCH + (++branchCounter);
        switch (ctx.op.getText()) {
            case "==":
                sts.push(template.getInstanceOf("ieq"));
                sts.push(template.getInstanceOf("brf").add("br", branchname));
                break;
            case "!=":
                sts.push(template.getInstanceOf("ieq"));
                sts.push(template.getInstanceOf("brt").add("br", branchname));
                break;
            case "<":
                sts.push(template.getInstanceOf("ilt"));
                sts.push(template.getInstanceOf("brf").add("br", branchname));
                break;
            case ">":
                sts.push(template.getInstanceOf("ilt"));
                sts.push(template.getInstanceOf("brt").add("br", branchname));
                break;


        }
    }

}
