package com.betacom.pasticceria.request;

public class SignInReq {
	private String username;
	private String pwd;
	private Integer idUtente;
	private String ruolo;
	
	@Override
	public String toString() {
		return "SignInReq [username=" + username + ", pwd=" + pwd + ", idUtente=" + idUtente + ", ruolo=" + ruolo + "]";
	}

	public Integer getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
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

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	
}
