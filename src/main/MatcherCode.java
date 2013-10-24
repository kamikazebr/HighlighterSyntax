package main;
public class MatcherCode {

	private int start = -1;
	private int end = -1;
	private int length = -1;
	private String code;
	private StyleHTML style;

	public StyleHTML getStyle() {
		return style;
	}

	public void setStyle(StyleHTML styleHTML) {
		this.style = styleHTML;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getCodeStylized() {
		return String.format("<span %s>%s</span>", getStyle(), getCode());
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getEnd() {
		return end;
	}
}
