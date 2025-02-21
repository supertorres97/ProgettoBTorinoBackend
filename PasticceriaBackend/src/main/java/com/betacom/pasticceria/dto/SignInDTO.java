package com.betacom.pasticceria.dto;

public class SignInDTO {
	private Boolean logged;
	private RuoliDTO ruolo;
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

	public RuoliDTO getRuolo() {
		return ruolo;
	}

	public void setRuolo(RuoliDTO ruolo) {
		this.ruolo = ruolo;
	}

	@Override
	public String toString() {
		return "SignInDTO [logged=" + logged + ", ruolo=" + ruolo + ", idUtente=" + idUtente + "]";
	}
	
}
