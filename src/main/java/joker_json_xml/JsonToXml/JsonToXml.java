package joker_json_xml.JsonToXml;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class JsonToXml {


/*
{
    "description" : "An imaginary server config file",
    "logs" : {"level":"verbose", "dir":"/var/log"},
    "host" : "antlr.org",
    "admin": ["parrt", "tombu"],
    "aliases": []
}

to

<description>An imaginary server config file</description>
<logs>
    <level>verbose</level>
    <dir>/var/log</dir>
</logs>
<host>antlr.org</host>
<admin>
    <element>parrt</element> <!-- inexact -->
    <element>tombu</element>
</admin>
<aliases></aliases>
 */

    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream("{\n" +
                "    \"root\":{\n" +
                "        \"description\":\"An imaginary server config file\",\n" +
                "        \"logs\":{\n" +
                "            \"level\":\"verbose\",\n" +
                "            \"dir\":\"/var/log\"\n" +
                "        },\n" +
                "        \"host\":\"antlr.org\",\n" +
                "        \"admin\":[\n" +
                "            \"parrt\",\n" +
                "            \"tombu\"\n" +
                "        ]\n" +
                "    }");
        JsonToXmlLexer lexer = new JsonToXmlLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JsonToXmlParser parser = new JsonToXmlParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.json();
        // show tree in text form
        System.out.println(tree.toStringTree(parser));

        ParseTreeWalker walker = new ParseTreeWalker();
        XMLEmitter converter = new XMLEmitter();
        walker.walk(converter, tree);
        System.out.println(converter.xml.get(tree).render());
    }
}


