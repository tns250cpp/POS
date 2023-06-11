package com.pos.web.entity;

public class Member {

	private String id;							// 아이디
	private String password;					// 비밀번호
	private String role;						// 직급
	
	public Member(String id, String password, String role) {
		this.id = id;
		this.password = password;
		this.role = role;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
