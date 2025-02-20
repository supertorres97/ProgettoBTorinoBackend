package com.betacom.pasticceria.request;

import java.util.Date;

public class FeedbackReq {
	private Integer id;
	private Integer utente;
	private Integer prodotto;
	private String descrizione;
	private String voto;
//    private String dataFeedback;
    
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
	
//	public Date getDataFeedback() {
//		return dataFeedback;
//	}
//	
//	public void setDataFeedback(Date dataFeedback) {
//		this.dataFeedback = dataFeedback;
//	}

	@Override
	public String toString() {
		return "FeedbackReq [id=" + id + ", utente=" + utente + ", prodotto=" + prodotto + ", descrizione="
				+ descrizione + ", voto=" + voto + "]";
	}
    

}
