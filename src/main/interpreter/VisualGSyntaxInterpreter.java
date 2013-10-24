package main.interpreter;
import java.awt.Color;
import java.awt.Font;

import main.StyleHTML;
import main.WordsWithStyle;
import main.annotation.Extensions;


/**
 * 
 * @author Kamikaze [Felipe Novaes F. da Rocha]
 * 
 */
@Extensions(".alg")
public class VisualGSyntaxInterpreter extends AbstractSyntaxInterpreter {

	@Override
	protected void initConfig(WordsWithStyle wordsReservedLanguague) {
		/***********************************************************/
		StyleHTML styleHTML = new StyleHTML();
		styleHTML.setColor(Color.BLUE);
		styleHTML.setFontStyle(Font.ITALIC);
		styleHTML.setUnderline(true);
		wordsReservedLanguague.put("\\balgoritmo\\b", styleHTML);
		wordsReservedLanguague.put("\\bfimalgoritmo\\b", styleHTML);
		wordsReservedLanguague.put("\\bvar\\b", styleHTML);
		wordsReservedLanguague.put("\\bdos\\b", styleHTML);
		wordsReservedLanguague.put("\\binicio\\b", styleHTML);
		wordsReservedLanguague.put("\\bprocedimento\\b", styleHTML);
		wordsReservedLanguague.put("\\bfimprocedimento\\b", styleHTML);
		wordsReservedLanguague.put("\\bfuncao\\b", styleHTML);
		wordsReservedLanguague.put("\\bfimfuncao\\b", styleHTML);
		/***********************************************************/
		StyleHTML styleComments = new StyleHTML();
		styleComments.setColor(Color.GREEN.darker().darker());
		styleComments.setFontStyle(Font.ITALIC);
		wordsReservedLanguague.put("(?m)//.*$", styleComments);
		/***********************************************************/
		StyleHTML styleCommentsMultine = new StyleHTML();
		styleCommentsMultine.setColor(Color.BLUE.brighter());
		styleCommentsMultine.setFontStyle(Font.BOLD);
		wordsReservedLanguague.put("(?m)\\/\\*[\\s\\S]*?\\*\\/", styleCommentsMultine);
		/***********************************************************/
		StyleHTML styleString = new StyleHTML();
		styleString.setColor(Color.RED);
		wordsReservedLanguague.put("\\\"(?:\\.|(\\\\\\\")|[^\\\"\\\"\n])*\\\"", styleString);
		wordsReservedLanguague.put("\\'(?:\\.|(\\\\\\')|[^\\'\\'\n])*\\'", styleString);
		/***********************************************************/
		StyleHTML styleNumber = new StyleHTML();
		styleNumber.setColor(Color.RED);
		wordsReservedLanguague.put("\\b([\\d]+(\\.[\\d]+)?|0x[a-f0-9]+)\\b", styleNumber);
		wordsReservedLanguague.put("\\bfalso\\b", styleNumber);
		wordsReservedLanguague.put("\\bverdadeiro\\b", styleNumber);
		/***********************************************************/
		StyleHTML styleTypes = new StyleHTML();
		styleTypes.setColor(Color.RED.darker().darker().darker());
		styleTypes.setUnderline(true);
		wordsReservedLanguague.put("\\binteiro\\b", styleTypes);
		wordsReservedLanguague.put("\\breal\\b", styleTypes);
		wordsReservedLanguague.put("\\bcaractere\\b", styleTypes);
		wordsReservedLanguague.put("\\bcaracter\\b", styleTypes);
		wordsReservedLanguague.put("\\blogico\\b", styleTypes);
		wordsReservedLanguague.put("\\bvetor\\b", styleTypes);
		/***********************************************************/
		StyleHTML styleCode = new StyleHTML();
		styleCode.setColor(Color.BLUE.darker().darker());
		wordsReservedLanguague.put("\\bescreva\\b", styleCode);
		wordsReservedLanguague.put("\\bescreval\\b", styleCode);
		wordsReservedLanguague.put("\\bleia\\b", styleCode);
		wordsReservedLanguague.put("\\bse\\b", styleCode);
		wordsReservedLanguague.put("\\bentao\\b", styleCode);
		wordsReservedLanguague.put("\\bsenao\\b", styleCode);
		wordsReservedLanguague.put("\\bfimse\\b", styleCode);
		wordsReservedLanguague.put("\\bescolha\\b", styleCode);
		wordsReservedLanguague.put("\\bfimescolha\\b", styleCode);
		wordsReservedLanguague.put("\\bcaso\\b", styleCode);
		wordsReservedLanguague.put("\\boutrocaso\\b", styleCode);
		wordsReservedLanguague.put("\\bate\\b", styleCode);
		wordsReservedLanguague.put("\\bde\\b", styleCode);
		wordsReservedLanguague.put("\\bfaca\\b", styleCode);
		wordsReservedLanguague.put("\\bpara\\b", styleCode);
		wordsReservedLanguague.put("\\bfimpara\\b", styleCode);
		wordsReservedLanguague.put("\\bdiv\\b", styleCode);
		wordsReservedLanguague.put("\\brepita\\b", styleCode);
		/***********************************************************/
	}
}
