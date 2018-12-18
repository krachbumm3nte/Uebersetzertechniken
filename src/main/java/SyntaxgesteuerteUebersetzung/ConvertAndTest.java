package SyntaxgesteuerteUebersetzung;

import SyntaxgesteuerteUebersetzung.pCodeVM.Interpreter;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.stream.Collectors;

import static SyntaxgesteuerteUebersetzung.pCodeVM.Interpreter.load;
import static org.stringtemplate.v4.Interpreter.trace;

public class ConvertAndTest {
    public static void main(String[] args) throws Exception {
        //ClobalLexer lexer = new ClobalLexer(new ANTLRFileStream("/home/alex/FHL-Workspace/uebs-1819/src/main/java/de/tetrisiq/uebs/A7/res/condExpr.clobal"));
        ClobalLexer lexer = new ClobalLexer(new ANTLRInputStream("" +
                "int n;\n" +
                "int i;\n" +
                "int main() {\n" +
                "  n = 1;\n" +
                "  i = 4;\n" +
                "  if (i>n)\n" +
                "    printf(n);\n" +
                "  else \n" +
                "    printf(i);\n" +
                "  return 0;\n" +
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

        String initialString = converter.sts.stream().map(e -> e.render()).collect(Collectors.joining("\n"));

        InputStream input = new ByteArrayInputStream(initialString.getBytes());


        // PROCESS ARGS
        boolean dump = true;


        Interpreter interpreter = new Interpreter();
        load(interpreter, input);
        interpreter.trace = trace;
        interpreter.exec();
        //if ( disassemble ) interpreter.disassemble();
        if (dump) interpreter.coredump();


    }
}
