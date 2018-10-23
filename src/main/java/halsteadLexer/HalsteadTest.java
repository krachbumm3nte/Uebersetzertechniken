package halsteadLexer;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;
import java.util.*;
import java.util.stream.Collectors;

public class HalsteadTest {

    public static void main(String[] args) throws Exception {
        CharStream input;
        // Pick an input stream (filename from commandline or stdin)
        if (args.length>0) input = new ANTLRFileStream(args[0]);
        else input = new ANTLRInputStream(System.in);

        HalsteadLexer lex = new HalsteadLexer(input);
        Token t;
        Map<String, Integer> operands = new HashMap<String, Integer>();
        Map<String, Integer> operators = new HashMap<>();
        Map<String, Integer> ignores = new HashMap<>();

        do {
            t = lex.nextToken();
            switch (t.getType()){
                case HalsteadLexer.OPERAND:
                    insertMultiSet(operands, t.getText());
                    break;
                case HalsteadLexer.IGNORE:
                    insertMultiSet(ignores, t.getText());
                    break;
                case HalsteadLexer.OPERATOR:
                    insertMultiSet(operators, t.getText());
                    break;

            }
        } while ( t.getType()!=Token.EOF );


        int N1 = operators.values().stream().reduce(0, Integer::sum);
        int N2 = operands.values().stream().reduce(0, Integer::sum);
        int n1 = operators.size();
        int n2 = operands.size();
        int N = N1 + N2;
        int n = n1 + n2;
        double V = N * (Math.log(n)/Math.log(2));
        double D = (double)(n1 * N2)/(2 * n2);
        double E = D * V;



        STGroup group = new STGroupDir("/home/johannes/IdeaProjects/uebsPr/src/main/java/halsteadLexer");
        ST st = group.getInstanceOf("halstead");


        st.add("operators", operators);
        st.add("operands", operands);
        st.add("N1", N1);
        st.add("N2", N2);
        st.add("n1", n1);
        st.add("n2", n2);
        st.add("N", N);
        st.add("n", n);
        st.add("V", V);
        st.add("D", D);
        st.add("E", E);

        System.out.println(st.render());



    }

    private static void insertMultiSet(Map<String, Integer> map, String s) {
        map.merge(s, 1, (a, b) -> a + b);
    }


}