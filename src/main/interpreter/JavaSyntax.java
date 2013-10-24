package main.interpreter;

import java.awt.Color;
import java.awt.Font;

import main.StyleHTML;
import main.WordsWithStyle;
import main.annotation.Extensions;
@Extensions(".java")
public class JavaSyntax extends AbstractSyntaxInterpreter {

	@Override
	protected void initConfig(WordsWithStyle wordsReservedLanguague) {
		StyleHTML value = new StyleHTML();
		value.setColor(Color.MAGENTA.darker().darker());
		value.setFontStyle(Font.BOLD);

		String[] lista = { "package", "import", "public", "class", "extends", "implements" };

		for (String word : lista) {
			wordsReservedLanguague.put("\\b" + word + "\\b", value);
		}
	}

}
