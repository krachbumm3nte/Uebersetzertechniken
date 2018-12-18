package joker_json_xml.XmlToJson.XmlToJson;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
/*
        STGroup template = new STGroupFile("/home/alex/FHL-Workspace/uebs-1819/src/main/java/de/tetrisiq/uebs/Jocker/XmlToJson/JSON.stg");
        ST st = template.getInstanceOf("openL");
        st.add("k","test");
        System.out.println(st.render());

*/

        //XmlToJsonLexer lexer = new XmlToJsonLexer(new ANTLRFileStream("/home/alex/FHL-Workspace/uebs-1819/src/main/java/de/tetrisiq/uebs/Jocker/example.xml"));
        XmlToJsonLexer lexer = new XmlToJsonLexer(new ANTLRInputStream("<root>\n" +
                "<description>An imaginary server config file</description>\n" +
                "<logs>\n" +
                "<level>verbose</level>\n" +
                "<dir>/var/log</dir>\n" +
                "</logs>\n" +
                "<host>antlr.org</host>\n" +
                "<admin>\n" +
                "<element>parrt</element>\n" +
                "<element>tombu</element>\n" +
                "</admin>\n" +
                "</root>"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        XmlToJsonParser parser = new XmlToJsonParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.document();
        // show tree in text form
        System.out.println(tree.toStringTree(parser));

        ParseTreeWalker walker = new ParseTreeWalker();
        XmlToJsonWalker converter = new XmlToJsonWalker();
        walker.walk(converter, tree);
        //System.out.println("\n\n\n");
        converter.json.forEach(e -> System.out.print(e.render()));


    }
}
