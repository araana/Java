
public class PasswordMap {
	private Long hash;
	private  String password;
	
	public PasswordMap(Long hash, String password) {
		this.hash = hash;
		this.password = password;
	}
	public PasswordMap(String password) {
		this.hash = 0L;
		this.password = password;
	}
	public Long getHash() {
		return hash;
	}
	public void setHash(Long hash) {
		this.hash = hash;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
