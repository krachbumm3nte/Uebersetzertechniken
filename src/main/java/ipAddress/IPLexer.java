// Generated from /home/johannes/IdeaProjects/uebsPr/src/main/java/ipAddress/IP.g4 by ANTLR 4.7
package ipAddress;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IPLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IPADDRESS=1, WS=2;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"IPADDRESS", "IPBLOCK", "DIGIT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "IPADDRESS", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public IPLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "IP.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\4 \b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\5\3\26"+
		"\n\3\3\3\5\3\31\n\3\3\4\3\4\3\5\3\5\3\5\3\5\2\2\6\3\3\5\2\7\2\t\4\3\2"+
		"\4\3\2\62;\5\2\13\f\17\17\"\"\2\37\2\3\3\2\2\2\2\t\3\2\2\2\3\13\3\2\2"+
		"\2\5\23\3\2\2\2\7\32\3\2\2\2\t\34\3\2\2\2\13\f\5\5\3\2\f\r\7\60\2\2\r"+
		"\16\5\5\3\2\16\17\7\60\2\2\17\20\5\5\3\2\20\21\7\60\2\2\21\22\5\5\3\2"+
		"\22\4\3\2\2\2\23\25\5\7\4\2\24\26\5\7\4\2\25\24\3\2\2\2\25\26\3\2\2\2"+
		"\26\30\3\2\2\2\27\31\5\7\4\2\30\27\3\2\2\2\30\31\3\2\2\2\31\6\3\2\2\2"+
		"\32\33\t\2\2\2\33\b\3\2\2\2\34\35\t\3\2\2\35\36\3\2\2\2\36\37\b\5\2\2"+
		"\37\n\3\2\2\2\5\2\25\30\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}