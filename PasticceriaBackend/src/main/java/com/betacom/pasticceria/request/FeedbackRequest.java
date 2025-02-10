package com.betacom.pasticceria.request;

import java.util.Date;

public class FeedbackRequest {
	private Integer id;
	private Integer utente;
	private Integer prodotto;
	private Integer ordine;
	private String descrizione;
	private String voto;
    private Date dataFeedback;
    
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
	
	public Integer getProdotto() {
		return prodotto;
	}
	
	public void setProdotto(Integer prodotto) {
		this.prodotto = prodotto;
	}
	
	public Integer getOrdine() {
		return ordine;
	}
	
	public void setOrdine(Integer ordine) {
		this.ordine = ordine;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getVoto() {
		return voto;
	}
	
	public void setVoto(String voto) {
		this.voto = voto;
	}
	
	public Date getDataFeedback() {
		return dataFeedback;
	}
	
	public void setDataFeedback(Date dataFeedback) {
		this.dataFeedback = dataFeedback;
	}
    

}
