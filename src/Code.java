
public class Code {
	Character ch;
	String code;
	
	public Code(Character ch, String hcode) {
		this.ch = ch;
		this.code = hcode;
	}

	public Character getCh() {
		return ch;
	}

	public void setCh(Character ch) {
		this.ch = ch;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String hcode) {
		this.code = hcode;
	}

	@Override
	public String toString() {
		return "Code [ch=" + ch + ", code=" + code + "]";
	}
	
	
}
