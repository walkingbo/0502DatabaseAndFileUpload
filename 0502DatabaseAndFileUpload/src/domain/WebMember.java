package domain;

public class WebMember {
	private String id;
	private String pw;
	private String nickname;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "WebMember [id=" + id + ", pw=" + pw + ", nickname=" + nickname + "]";
	}
	
}
