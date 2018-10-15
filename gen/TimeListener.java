// Generated from /home/johannes/IdeaProjects/uebsPr/Time.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TimeParser}.
 */
public interface TimeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TimeParser#timestamp}.
	 * @param ctx the parse tree
	 */
	void enterTimestamp(TimeParser.TimestampContext ctx);
	/**
	 * Exit a parse tree produced by {@link TimeParser#timestamp}.
	 * @param ctx the parse tree
	 */
	void exitTimestamp(TimeParser.TimestampContext ctx);
}