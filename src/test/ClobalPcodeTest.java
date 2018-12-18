import SyntaxgesteuerteUebersetzung.ClobalLexer;
import SyntaxgesteuerteUebersetzung.ClobalParser;
import SyntaxgesteuerteUebersetzung.Converter;
import SyntaxgesteuerteUebersetzung.pCodeVM.Interpreter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.*;
import java.util.stream.Collectors;

import static SyntaxgesteuerteUebersetzung.pCodeVM.Interpreter.load;
import static org.junit.Assert.assertTrue;
import static org.stringtemplate.v4.Interpreter.trace;

@RunWith(JUnitParamsRunner.class)
public class ClobalPcodeTest {




    @Test
    @Parameters({"condExpr","condExpr1","for","forTwice","forTwice1","funcCall","ifElse","ifgt","iflt","neq","not","printf","uminus"})
    public void test1(String name) throws Exception {
        File file = new File(String.format("src/main/resources/ClobalProgs/%s.clobal", name));
        //print file name
        System.err.println(name + ".clobal");
        BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            System.out.print(sb.toString());
        } finally {
            br.close();
        }
        //System.out.println("\n");
        assertTrue(helper(file));

    }



    public boolean helper(File i) throws Exception {
        ClobalLexer lexer = new ClobalLexer(new ANTLRFileStream(i.getAbsolutePath()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ClobalParser parser = new ClobalParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.file();
        // show tree in text form
        //System.out.println(tree.toStringTree(parser));
        ParseTreeWalker walker = new ParseTreeWalker();

        Converter converter = new Converter();
        walker.walk(converter, tree);
        //System.out.println("\n\n\n");
        converter.sts.forEach(e -> System.out.println(e.render()));
        String initialString = converter.sts.stream().map(e -> e.render()).collect(Collectors.joining("\n"));

        InputStream input = new ByteArrayInputStream(initialString.getBytes());


        // PROCESS ARGS
        boolean dump = true;


        Interpreter interpreter = new Interpreter();
        load(interpreter, input);
        interpreter.exec();
        //if ( disassemble ) interpreter.disassemble();
        if (dump) interpreter.coredump();
        return dump;


    }


    @Test
    public void blaaskd() throws Exception {
        String string = "; int x,y\n" +
                ".globals 2 \n" +
                ".def main: args=0, locals=0\n" +
                "iconst 9\n" +
                "gstore 0\n" +
                "gload 0\n" +
                "gstore 1\n" +
                "gload 1\n" +
                "print\n" +
                "halt\n";


        //String initialString = converter.sts.stream().map(e -> e.render()).collect(Collectors.joining("\n"));

        InputStream input = new ByteArrayInputStream(string.getBytes());


        // PROCESS ARGS
        boolean dump = true;


        Interpreter interpreter = new Interpreter();
        load(interpreter, input);
        interpreter.exec();
        //if ( disassemble ) interpreter.disassemble();
        if (dump) interpreter.coredump();


    }
}
