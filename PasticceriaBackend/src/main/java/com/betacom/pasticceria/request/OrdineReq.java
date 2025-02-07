package com.betacom.pasticceria.request;

public class OrdineReq {
	
	private Integer id;
	private Integer utente;
	private Double totale;
	private String indirizzo;
	private String status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
	}
	public Integer getUtente() {
		return utente;
	}
	public void setUtente(Integer utente) {
		this.utente = utente;
	}
	public Double getTotale() {
		return totale;
	}
	public void setTotale(Double totale) {
		this.totale = totale;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "OrdineReq [id=" + id + ", utente=" + utente + ", totale=" + totale + ", indirizzo=" + indirizzo
				+ ", status=" + status + "]";
	}
	
	

}
