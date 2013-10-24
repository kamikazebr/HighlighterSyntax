package main;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileFilterDynamic extends FileFilter {

	private String[] extensions;

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String extension = f.getAbsolutePath().replaceAll(HighlighterSyntax.regexMatchExtension, "$2");

		for (String ext : extensions) {
			if (extension.equals(ext)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String getDescription() {
		StringBuffer sb = new StringBuffer(" ");
		for (String ext : extensions) {
			sb.append(ext).append(";");
		}
		return sb.deleteCharAt(sb.length()-1).toString();
	}

	public void setExtensions(String[] extensions) {
		this.extensions = extensions;
	}

}
