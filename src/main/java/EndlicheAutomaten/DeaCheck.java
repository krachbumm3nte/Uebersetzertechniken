package EndlicheAutomaten;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DeaCheck extends CSVBaseListener {
    String word;
    List<List<String>> table = new LinkedList();
    Stack<ST> stack = new Stack<>();
    STGroup template = new STGroupFile("./src/main/java/EndlicheAutomaten/Graphviz.stg");
    int i = -1;

    @Override
    public void exitField(CSVParser.FieldContext ctx) {
        //System.out.println(ctx.getText());
        table.get(i).add(ctx.getText());
    }

    @Override
    public void enterRow(CSVParser.RowContext ctx) {
        //System.out.println(this.table.toString());
        table.add(new LinkedList());
        i++;

        //System.out.println(ctx.getText());
        if (ctx.getText().contains("q")) { // step over first row
            String color = "";
            String fromName = ctx.children.get(0).getText();
            if (ctx.children.get(0).getText().contains("->")) {
                color = "green";
                fromName = fromName.replace("->", "");
            }
            if (ctx.children.get(0).getText().contains("*")) {
                color = "red";
                fromName = fromName.replace("*", "");
            }

            stack.push(template.getInstanceOf("addNode").add("name", fromName).add("color", color));
            stack.push(template.getInstanceOf("addTransition").add("from", fromName).add("to", ctx.children.get(2).getText()).add("label", 0));
            stack.push(template.getInstanceOf("addTransition").add("from", fromName).add("to", ctx.children.get(4).getText()).add("label", 1));
        }
    }

    public boolean check(String word) {
        int current = convertFromQtoInt("q0"); // starts with q0
        //System.out.println(table);
        for (String s : word.split("")) {
            int walk = Integer.parseInt(s);
            walk++; // fix table indexes
            String q = table.get(current).get(walk);
            current = convertFromQtoInt(q);
        }
        return table.get(current).get(0).contains("*");
    }

    private int convertFromQtoInt(String i) {
        return Integer.parseInt(i.replace("*", "").replace("->", "").replace("q", "")) + 1;
    }


    @Override
    public void enterCsvFile(CSVParser.CsvFileContext ctx) {
        stack.push(template.getInstanceOf("open"));
    }


    @Override
    public void exitCsvFile(CSVParser.CsvFileContext ctx) {
        stack.push(template.getInstanceOf("close"));
    }


}
