package main;
import java.awt.Color;
import java.awt.Font;

public class StyleHTML {

	private Color color;
	private int fontStyle = -1;
	private boolean underline;

	public StyleHTML() {
	}

	public void setColor(Color blue) {
		this.color = blue;
	}

	public Color getColor() {
		return color;
	}

	public void setFontStyle(int fontStyle) {
		this.fontStyle = fontStyle;
	}

	public int getFontStyle() {
		return fontStyle;
	}

	public void setUnderline(boolean underline) {
		this.underline = underline;
	}

	public boolean isUnderline() {
		return underline;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("style='");
		if (isUnderline()) {
			sb.append("text-decoration: underline;");
		}
		if (getColor() != null) {
			sb.append(String.format("color: rgb(%d,%d,%d);", getColor().getRed(), getColor().getGreen(), getColor().getBlue()));
		}
		if (getFontStyle() != -1) {
			sb.append("font-style: ");
			switch (getFontStyle()) {
			case Font.ITALIC:
				sb.append("italic");
				break;
			case Font.BOLD:
				sb.append("bold");
				break;
			case Font.PLAIN:
				sb.append("plain");
				break;
			}
			sb.append(";");
		}
		return sb.append("'").toString();
	}
}
