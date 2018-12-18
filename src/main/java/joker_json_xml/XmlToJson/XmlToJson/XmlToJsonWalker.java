package joker_json_xml.XmlToJson.XmlToJson;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.Stack;


public class XmlToJsonWalker extends XmlToJsonParserBaseListener {
    public Stack<ST> json = new Stack<>();
    STGroup template = new STGroupFile("/home/johannes/IdeaProjects/uebsPr/src/main/java/joker_json_xml/XmlToJson/XmlToJson/JSON.stg");
    private boolean list = false;

    public void enterDocument(XmlToJsonParser.DocumentContext ctx) {
        ST st = template.getInstanceOf("open");
        json.push(st);

    }

    public void exitDocument(XmlToJsonParser.DocumentContext ctx) {
        ST st = template.getInstanceOf("close");
        json.push(st);
    }

    public void enterElement(XmlToJsonParser.ElementContext ctx) {
        String v = ctx.content().getText();
        String k = ctx.Name().toString().substring(1).split(",")[0];
        ST st;
        if (openKomplex(v)) {
            st = template.getInstanceOf("openK");
            st.add("k", k);
            json.push(st);
            return;
        }
        if (openList(v)) {
            st = template.getInstanceOf("openL").add("k", k);
            json.push(st);
            list = true;
            return;
        }


    }


    public void exitElement(XmlToJsonParser.ElementContext ctx) {
        String v = ctx.content().getText();
        String k = ctx.Name().toString().substring(1).split(",")[0];
        ST st;
        if (openKomplex(v)) { // to close the tag
            if (json.peek().getName().contains("sep")) {
                //last elemet was an seperator and now we want to close a tag
                json.pop();
            }
            st = template.getInstanceOf("close");
            json.push(st);
            return;
        }
        if (openList(v)) {
            if (json.peek().getName().contains("sep")) {
                //last elemet was an seperator and now we want to close a tag
                json.pop();
            }
            st = template.getInstanceOf("closeL");
            json.push(st);
            return;
        }
        if (list) {
            //add list element
            st = template.getInstanceOf("addlist");
            st.add("v", v);
            json.push(st);
            json.push(template.getInstanceOf("sep"));

        } else {
            if (json.peek().getName().contains("close")) {
                //last elemet was an close element
                json.push(template.getInstanceOf("sep"));
            }
            st = template.getInstanceOf("kv");
            st.add("k", k);
            st.add("v", v);
            json.push(st);
            json.push(template.getInstanceOf("sep"));

        }

    }


    private boolean openList(String v) {
        boolean test = openKomplex(v);
        v = v.replace("\n", "").trim();
        Stack<String> tmp = new Stack<>();
        for (String s : v.split("<")) {
            tmp.push(s.split(">")[0]);
        }
        if (tmp.size() == 1) return false; //only one element without lists
        boolean ret = false;
        String last = null;
        for (String s : tmp) {
            if (last == null) last = s;
            if (last.length() == 0) {
                last = null;
                continue;
            }

            ret = s.contains(last);
        }
        return ret;
    }

    private boolean openKomplex(String v) {
        v = v.replace("\n", "").trim();
        Stack<String> tmp = new Stack<>();
        for (String s : v.split("<")) {
            tmp.push(s.split(">")[0]);
        }
        boolean ret = false;
        String last = null;
        for (String s : tmp) {
            if (last == null) last = s;
            ret = !s.contains(last);
            if (last.length() == 0) last = s;
        }
        return ret;
    }


}
