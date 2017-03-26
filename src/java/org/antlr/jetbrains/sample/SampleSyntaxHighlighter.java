package org.antlr.jetbrains.sample;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.antlr.jetbrains.adaptor.lexer.ANTLRLexerAdaptor;
import org.antlr.jetbrains.adaptor.lexer.PSIElementTypeFactory;
import org.antlr.jetbrains.adaptor.lexer.TokenIElementType;
import org.antlr.jetbrains.sample.parser.SampleLanguageLexer;
import org.antlr.jetbrains.sample.parser.SampleLanguageParser;
import org.jetbrains.annotations.NotNull;
import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

/** A highlighter is really just a mapping from token type to
 *  some text attributes using {@link #getTokenHighlights(IElementType)}.
 *  The reason that it returns an array, TextAttributesKey[], is
 *  that you might want to mix the attributes of a few known highlighters.
 *  A {@link TextAttributesKey} is just a name for that a theme
 *  or IDE skin can set. For example, {@link com.intellij.openapi.editor.DefaultLanguageHighlighterColors#KEYWORD}
 *  is the key that maps to what identifiers look like in the editor.
 *  To change it, see dialog: Editor > Colors & Fonts > Language Defaults.
 *
 *  From <a href="http://www.jetbrains.org/intellij/sdk/docs/reference_guide/custom_language_support/syntax_highlighting_and_error_highlighting.html">doc</a>:
 *  "The mapping of the TextAttributesKey to specific attributes used
 *  in an editor is defined by the EditorColorsScheme class, and can
 *  be configured by the user if the plugin provides an appropriate
 *  configuration interface.
 *  ...
 *  The syntax highlighter returns the {@link TextAttributesKey}
 * instances for each token type which needs special highlighting.
 * For highlighting lexer errors, the standard TextAttributesKey
 * for bad characters HighlighterColors.BAD_CHARACTER can be used."
 */
public class SampleSyntaxHighlighter extends SyntaxHighlighterBase {
	private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];
	public static final TextAttributesKey ID =
		createTextAttributesKey("SAMPLE_ID", DefaultLanguageHighlighterColors.IDENTIFIER);
	public static final TextAttributesKey KEYWORD =
		createTextAttributesKey("SAMPLE_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
	public static final TextAttributesKey STRING =
		createTextAttributesKey("SAMPLE_STRING", DefaultLanguageHighlighterColors.STRING);
	public static final TextAttributesKey LINE_COMMENT =
		createTextAttributesKey("SAMPLE_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
	public static final TextAttributesKey BLOCK_COMMENT =
		createTextAttributesKey("SAMPLE_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);

	static {
		PSIElementTypeFactory.defineLanguageIElementTypes(SampleLanguage.INSTANCE,
		                                                  SampleLanguageParser.tokenNames,
		                                                  SampleLanguageParser.ruleNames);
	}

	@NotNull
	@Override
	public Lexer getHighlightingLexer() {
		SampleLanguageLexer lexer = new SampleLanguageLexer(null);
		return new ANTLRLexerAdaptor(SampleLanguage.INSTANCE, lexer);
	}

	@NotNull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
		if ( !(tokenType instanceof TokenIElementType) ) return EMPTY_KEYS;
		TokenIElementType myType = (TokenIElementType)tokenType;
		int ttype = myType.getANTLRTokenType();
		TextAttributesKey attrKey;
		switch ( ttype ) {
			case SampleLanguageLexer.ID :
				attrKey = ID;
				break;
			case SampleLanguageLexer.INT_LITERAL :
			case SampleLanguageLexer.LONG_LITERAL :
			case SampleLanguageLexer.FLOAT_LITERAL :
			case SampleLanguageLexer.DOUBLE_LITERAL:
			case SampleLanguageLexer.COL:
			
			case SampleLanguageLexer.SCOL: 
			case SampleLanguageLexer.DOT:
			case SampleLanguageLexer.OPEN_PAR:
			case SampleLanguageLexer.CLOSE_PAR:
			case SampleLanguageLexer.OPEN_SQARE_BRACKETS:
			case SampleLanguageLexer.CLOASE_SQARE_BRACKETS:
			case SampleLanguageLexer.COMMA:
			case SampleLanguageLexer.ASSIGN:
			case SampleLanguageLexer.STAR:
			case SampleLanguageLexer.PLUS:
			case SampleLanguageLexer.QUESTION:
			case SampleLanguageLexer.MINUS:
			case SampleLanguageLexer.DIV:
			case SampleLanguageLexer.MOD:
			case SampleLanguageLexer.LT:
			case SampleLanguageLexer.LT_EQ:
			case SampleLanguageLexer.GT:
			case SampleLanguageLexer.GT_EQ:
			case SampleLanguageLexer.EQ:
			case SampleLanguageLexer.NOT_EQ:
			case SampleLanguageLexer.AT_SYMBOL:
			case SampleLanguageLexer.FOLLOWED_BY:
			case SampleLanguageLexer.HASH:
			case SampleLanguageLexer.STREAM:
			case SampleLanguageLexer.DEFINE:
			case SampleLanguageLexer.FUNCTION:
			case SampleLanguageLexer.TRIGGER:
			case SampleLanguageLexer.TABLE:
			case SampleLanguageLexer.PLAN:
			case SampleLanguageLexer.FROM:
			case SampleLanguageLexer.PARTITION:
			case SampleLanguageLexer.WINDOW:
			case SampleLanguageLexer.SELECT:
			case SampleLanguageLexer.GROUP:
			case SampleLanguageLexer.BY:
			case SampleLanguageLexer.HAVING:
			case SampleLanguageLexer.INSERT:
			case SampleLanguageLexer.DELETE:
			case SampleLanguageLexer.UPDATE:
			case SampleLanguageLexer.RETURN:
			case SampleLanguageLexer.EVENTS:
			case SampleLanguageLexer.INTO:
			case SampleLanguageLexer.OUTPUT:
			case SampleLanguageLexer.EXPIRED:
			case SampleLanguageLexer.CURRENT:
			case SampleLanguageLexer.SNAPSHOT:
			case SampleLanguageLexer.FOR:
			case SampleLanguageLexer.RAW:
			case SampleLanguageLexer.OF:
			case SampleLanguageLexer.AS:
			case SampleLanguageLexer.AT:
			case SampleLanguageLexer.OR:
			case SampleLanguageLexer.AND:
			case SampleLanguageLexer.IN:
			case SampleLanguageLexer.ON:
			case SampleLanguageLexer.IS:
			case SampleLanguageLexer.NOT:
			case SampleLanguageLexer.WITHIN: 
			case SampleLanguageLexer.WITH:
			case SampleLanguageLexer.BEGIN:
			case SampleLanguageLexer.END:
			case SampleLanguageLexer.NULL:
			case SampleLanguageLexer.EVERY:
			case SampleLanguageLexer.LAST:
			case SampleLanguageLexer.ALL:
			case SampleLanguageLexer.FIRST:
			case SampleLanguageLexer.JOIN:
			case SampleLanguageLexer.INNER:
			case SampleLanguageLexer.OUTER:
			case SampleLanguageLexer.RIGHT:
			case SampleLanguageLexer.LEFT:
			case SampleLanguageLexer.FULL:
			case SampleLanguageLexer.UNIDIRECTIONAL: 
			case SampleLanguageLexer.YEARS:
			case SampleLanguageLexer.MONTHS:
			case SampleLanguageLexer.WEEKS:
			case SampleLanguageLexer.DAYS:
			case SampleLanguageLexer.HOURS:
			case SampleLanguageLexer.MINUTES:
			case SampleLanguageLexer.SECONDS:
			case SampleLanguageLexer.MILLISECONDS:
			case SampleLanguageLexer.INT:
			case SampleLanguageLexer.LONG:
			case SampleLanguageLexer.FLOAT:
			case SampleLanguageLexer.DOUBLE:
			case SampleLanguageLexer.BOOL:
			case SampleLanguageLexer.OBJECT:
			case SampleLanguageLexer.ID_QUOTES:
			case SampleLanguageLexer.STRING_LITERAL:
			case SampleLanguageLexer.SPACES:
			case SampleLanguageLexer.UNEXPECTED_CHAR:
			case SampleLanguageLexer.SCRIPT:

			
			case SampleLanguageLexer.TRUE :
			case SampleLanguageLexer.FALSE :
				attrKey = KEYWORD;
				break;
			case SampleLanguageLexer.STRING :
				attrKey = STRING;
				break;
			case SampleLanguageLexer.SINGLE_LINE_COMMENT :
				attrKey = LINE_COMMENT;
				break;
			case SampleLanguageLexer.MULTILINE_COMMENT :
				attrKey = BLOCK_COMMENT;
				break;
			default :
				return EMPTY_KEYS;
		}
		return new TextAttributesKey[] {attrKey};
	}
}
