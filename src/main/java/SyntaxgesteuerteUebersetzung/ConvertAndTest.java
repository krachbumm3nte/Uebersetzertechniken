package SyntaxgesteuerteUebersetzung;

import SyntaxgesteuerteUebersetzung.pCodeVM.Interpreter;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class ConvertAndTest {
    public static void main(String[] args) throws IOException {

        ClobalLexer lexer = new ClobalLexer(new ANTLRFileStream("./src/main/java/SyntaxgesteuerteUebersetzung/pcode.stg"));
/*ClobalLexer lexer = new ClobalLexer(new ANTLRInputStream("int n;\n" +
        "int i;\n" +
        "int main() {\n" +
        "  n = 1;\n" +
        "  i = 4;\n" +
        "printf(i);" +
        "  if(n>i) {\n" +
        "    printf(n);\n" +
        "  } else\n" +
        "  { \n" +
        "  printf(i);\n" +
        "  }\n" +
        "  return 0;\n" +
        "}"));*/
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

        converter.sts.forEach(e -> System.out.println(e.render()));

    }
}


