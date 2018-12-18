package SyntaxgesteuerteUebersetzung;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {

        //ClobalLexer lexer = new ClobalLexer(new ANTLRFileStream("/home/alex/FHL-Workspace/uebs-1819/src/main/java/de/tetrisiq/uebs/A7/res/condExpr.clobal"));
        ClobalLexer lexer = new ClobalLexer(new ANTLRInputStream("" +
                "int a; int b; " +
                "int main() {" +
                "a=5;" +
                "b=9;" +
                "if (a<b) {" +
                "printf(a);" +
                "}" +
                "}"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ClobalParser parser = new ClobalParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.file();
        // show tree in text form
        System.out.println(tree.toStringTree(parser));
        ParseTreeWalker walker = new ParseTreeWalker();

        Converter converter = new Converter();
        walker.walk(converter, tree);
        //System.out.println("\n\n\n");

        //converter.sts.forEach(e -> System.out.println(e.render()));

    }
}