import SyntaxgesteuerteUebersetzung.ClobalLexer;
import SyntaxgesteuerteUebersetzung.ClobalParser;
import SyntaxgesteuerteUebersetzung.Converter;
import SyntaxgesteuerteUebersetzung.pCodeVM.Interpreter;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.stream.Collectors;

import static SyntaxgesteuerteUebersetzung.pCodeVM.Interpreter.load;
import static org.stringtemplate.v4.Interpreter.trace;

public class ClobalPcodeTest {

    public File folder = new File("./src/main/resources/ClobalProgs");
    final File[] listOfFiles = folder.listFiles();
    int counter = 0;



    @Test
    public void test() throws Exception {
        assert listOfFiles != null;
        ClobalLexer lexer = new ClobalLexer(new ANTLRFileStream(listOfFiles[counter++].getAbsolutePath()));
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
        interpreter.exec();
        //if ( disassemble ) interpreter.disassemble();
        if (dump) interpreter.coredump();

    }

}
