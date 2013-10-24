package main.interpreter;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.HighlighterSyntax;
import main.MatcherCode;
import main.MatcherCodeList;
import main.StyleHTML;
import main.WordsWithStyle;
import main.annotation.Extensions;

@Extensions("")
public abstract class AbstractSyntaxInterpreter {

	public WordsWithStyle wordsReservedLanguague = null;
	private String fileString;
	private String pathFile;
	private List<String> extensions;

	public AbstractSyntaxInterpreter() {
		wordsReservedLanguague = new WordsWithStyle();
		initConfig(wordsReservedLanguague);
	}

	protected abstract void initConfig(WordsWithStyle wordsReservedLanguague);

	protected String makeHightlighterSyntax(String fileString) {
		MatcherCodeList codeList = new MatcherCodeList();
		Set<Entry<String, StyleHTML>> entrySet = getWordsReservedLanguague()
				.entrySet();
		Iterator<Entry<String, StyleHTML>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, StyleHTML> entry = (Map.Entry<String, StyleHTML>) iterator
					.next();
			String wordRegEx = String.format("(%s)", entry.getKey());
			Matcher matcher = Pattern.compile(wordRegEx).matcher(fileString);
			while (matcher.find()) {
				int start = matcher.start();
				int end = matcher.end();
				MatcherCode matcherCode = new MatcherCode();
				matcherCode.setStart(start);
				matcherCode.setEnd(end);
				matcherCode.setLength(end - start);
				matcherCode.setCode(matcher.group(1));
				matcherCode.setStyle(entry.getValue());
				codeList.add(matcherCode);
			}
		}
		codeList.sort();
		codeList.removeIntersection();

		StringBuffer codeNew = new StringBuffer();

		for (int i = 0; i < codeList.size() - 1; i++) {
			MatcherCode matcher = codeList.get(i);
			MatcherCode matcherNext = codeList.get(i + 1);

			int end = matcher.getEnd();
			String codeStylized = matcher.getCodeStylized();

			codeNew.append(codeStylized);
			codeNew.append(fileString.substring(end, matcherNext.getStart()));
		}
		MatcherCode matcherNext = codeList.get(codeList.size()-1);
		if(matcherNext.getEnd() <fileString.length()){
			codeNew.append(matcherNext.getCodeStylized());
			codeNew.append(fileString.substring(matcherNext.getEnd(), fileString.length()));
		}
		System.out.println(codeNew);

		return codeNew.toString().trim();
	}

	public void initHightlighterSyntax(String pathFile) {
		this.pathFile = pathFile;
		fileString = readFile(pathFile);
		String makeHightlighterSyntax = makeHightlighterSyntax(fileString);

		makeHightlighterSyntax = "<pre>".concat(makeHightlighterSyntax.trim())
				.concat("</pre>");

		String newPathName = String.format(
				"%s.html",
				getPathFile().replaceAll(HighlighterSyntax.regexMatchExtension,
						"$1"));
		System.out.println(newPathName);
		File savedFile = saveFile(newPathName, makeHightlighterSyntax);

		if (savedFile != null) {
			try {
				Desktop.getDesktop().open(savedFile);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnsupportedOperationException e) {
				e.printStackTrace();
			}
		}
	}

	public String getFileString() {
		return fileString;
	}

	public String getPathFile() {
		return pathFile;
	}

	public WordsWithStyle getWordsReservedLanguague() {
		return wordsReservedLanguague;
	}

	protected String readFile(String pathFile) {
		String fileString = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(pathFile)));

			StringBuffer sb = new StringBuffer();

			while (br.ready()) {
				sb.append(br.readLine() + (char) Character.LINE_SEPARATOR);
			}
			fileString = sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileString;
	}

	protected File saveFile(String pathFile, String contents) {
		File file = null;
		FileWriter fw = null;
		try {
			file = new File(pathFile);
			fw = new FileWriter(file);
			fw.write(contents);
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fw != null)
					fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	public void setExtensions(List<String> extensions) {
		this.extensions = extensions;
	}

	public List<String> getExtensions() {
		return extensions;
	}

	public int getVersionNumber() {
		return 1;
	}

}
