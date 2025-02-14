package com.betacom.pasticceria.request;

public class UtenteReq {
	private Integer id;
	private String nome;
	private String cognome;
	private String cFiscale;
	private String email;
	private String via;
	private String cap;
	private String citta; 
    
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getcFiscale() {
		return cFiscale;
	}
	
	public void setcFiscale(String cFiscale) {
		this.cFiscale = cFiscale;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getVia() {
		return via;
	}
	
	public void setVia(String via) {
		this.via = via;
	}
	
	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCitta() {
		return citta;
	}
	
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	@Override
	public String toString() {
		return "UtenteReq [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", cFiscale=" + cFiscale + ", email="
				+ email + ", via=" + via + ", CAP=" + CAP + ", citta=" + citta + "]";
	}
}
