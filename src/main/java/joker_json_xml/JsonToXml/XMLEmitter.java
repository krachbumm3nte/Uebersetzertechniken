package joker_json_xml.JsonToXml;

import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class XMLEmitter extends JsonToXmlBaseListener {
    public ParseTreeProperty<ST> xml = new ParseTreeProperty<ST>();
    STGroup templates = new STGroupFile("/home/alex/FHL-Workspace/uebs-1819/src/main/java/de/tetrisiq/uebs/Jocker/JsonToXml/XML.stg");

    public static String stripQuotes(String s) {
        if (s == null || s.charAt(0) != '"') return s;
        return s.substring(1, s.length() - 1);
    }

    @Override
    public void exitJson(JsonToXmlParser.JsonContext ctx) {
        xml.put(ctx, xml.get(ctx.getChild(0)));
    }

    @Override
    public void exitEmptyObject(JsonToXmlParser.EmptyObjectContext ctx) {
        xml.put(ctx, templates.getInstanceOf("empty"));
    }

    @Override
    public void exitAnObject(JsonToXmlParser.AnObjectContext ctx) {
        ST objectST = templates.getInstanceOf("object");
        for (JsonToXmlParser.PairContext pctx : ctx.pair()) {
            objectST.add("fields", xml.get(pctx));
        }
        xml.put(ctx, objectST);
    }

    @Override
    public void exitEmptyArray(JsonToXmlParser.EmptyArrayContext ctx) {
        xml.put(ctx, templates.getInstanceOf("empty"));
    }

    @Override
    public void exitArrayOfValues(JsonToXmlParser.ArrayOfValuesContext ctx) {
        ST arrayST = templates.getInstanceOf("array");
        for (JsonToXmlParser.ValueContext vctx : ctx.value()) {
            arrayST.add("values", xml.get(vctx));
        }
        xml.put(ctx, arrayST);
    }

    @Override
    public void exitPair(JsonToXmlParser.PairContext ctx) {
        String name = stripQuotes(ctx.STRING().getText());
        JsonToXmlParser.ValueContext vctx = ctx.value();
        ST tag = templates.getInstanceOf("tag");
        tag.add("name", name);
        tag.add("content", xml.get(vctx));
        xml.put(ctx, tag);
    }

    @Override
    public void exitAtom(JsonToXmlParser.AtomContext ctx) {
        xml.put(ctx, value(ctx.start.getText()));
    }

    @Override
    public void exitObjectValue(JsonToXmlParser.ObjectValueContext ctx) {
        xml.put(ctx, xml.get(ctx.object()));
    }

    @Override
    public void exitArrayValue(JsonToXmlParser.ArrayValueContext ctx) {
        JsonToXmlParser.ArrayContext array = ctx.array();
        xml.put(ctx, xml.get(array));
    }

    @Override
    public void exitString(JsonToXmlParser.StringContext ctx) {
        xml.put(ctx, value(stripQuotes(ctx.start.getText())));
    }

    ST value(Object x) {
        ST st = templates.getInstanceOf("value");
        st.add("x", x);
        return st;
    }
}

