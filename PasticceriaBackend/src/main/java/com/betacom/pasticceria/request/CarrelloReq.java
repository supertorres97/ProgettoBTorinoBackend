package com.betacom.pasticceria.request;

public class CarrelloReq {

	private Integer id;
	private Integer utente;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUtente() {
		return utente;
	}
	public void setUtente(Integer utente) {
		this.utente = utente;
	}
	@Override
	public String toString() {
		return "CarrelloReq [id=" + id + ", utente=" + utente + "]";
	}
}
