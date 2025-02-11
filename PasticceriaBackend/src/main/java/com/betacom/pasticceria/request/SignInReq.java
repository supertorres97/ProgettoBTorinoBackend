package com.betacom.pasticceria.request;

public class SignInReq {
	private String username;
	private String pwd;
	
	@Override
	public String toString() {
		return "SignInReq [username=" + username + ", pwd=" + pwd + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
