package com.betacom.pasticceria.dto;

public class SignInDTO {
	private Boolean logged;
	private String role;
	public Integer idUtente;
	
	public Integer getIdUtente() {
		return idUtente;
	}
	
	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}
	
	public Boolean getLogged() {
		return logged;
	}
	
	public void setLogged(Boolean logged) {
		this.logged = logged;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
}
