package com.betacom.pasticceria.request;

public class SignInReq {
	private String userName;
	private String pwd;
	
	@Override
	public String toString() {
		return "SignInReq [username=" + userName + ", pwd=" + pwd + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
	
}
