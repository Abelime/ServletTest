package com.mvc.web.entity.user;

public class Login {
	private String id;
	private String pass;
	
	
	
	public Login(String id, String pass) {
		this.id = id;
		this.pass = pass;
	}
	
	@Override
	public String toString() {
		return "Login [id=" + id + ", pass=" + pass + "]";
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
