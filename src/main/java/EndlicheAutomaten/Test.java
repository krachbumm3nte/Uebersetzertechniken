package EndlicheAutomaten;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {

        CSVLexer lexer = new CSVLexer(new ANTLRFileStream("./src/main/java/EndlicheAutomaten/Table.csv"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CSVParser parser = new CSVParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.csvFile();
        // show tree in text form
        //System.out.println(tree.toStringTree(parser));

        ParseTreeWalker walker = new ParseTreeWalker();
        DeaCheck deaCheck = new DeaCheck();
        walker.walk(deaCheck, tree);
        System.out.println(deaCheck.check("01100"));
        deaCheck.stack.forEach(e -> System.out.println(e.render()));

    }
}
