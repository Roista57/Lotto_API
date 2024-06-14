package com.lotto.dto;

public class UserDTO {
	private int no;
	private String id;
	private String pw;
	private String salt;
	private String username;
	private String email;
	private String tel;

	public UserDTO() {
		super();
	}

	public UserDTO(int no, String id, String pw, String salt, String username, String email, String tel) {
		super();
		this.no = no;
		this.id = id;
		this.pw = pw;
		this.salt = salt;
		this.username = username;
		this.email = email;
		this.tel = tel;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}
