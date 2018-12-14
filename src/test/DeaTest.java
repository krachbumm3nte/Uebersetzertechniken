import EndlicheAutomaten.CSVLexer;
import EndlicheAutomaten.CSVParser;
import EndlicheAutomaten.DeaCheck;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class DeaTest {

    DeaCheck deaCheck;

    @Before
    public void before() throws IOException {
        CSVLexer lexer = new CSVLexer(new ANTLRFileStream("./src/main/java/EndlicheAutomaten/Table.csv"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CSVParser parser = new CSVParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.csvFile();
        // show tree in text form

        ParseTreeWalker walker = new ParseTreeWalker();
        deaCheck = new DeaCheck();
        walker.walk(deaCheck, tree);

    }

    @Test
    @Parameters({"01001101", "00001", "01001"})
    public void test(String input) {
        assertTrue(deaCheck.check(input));
    }

    @Test
    @Parameters({"0100111", "000011", "01100"})
    public void testAssertFalse(String input) {
        assertFalse(deaCheck.check(input));
    }
}
