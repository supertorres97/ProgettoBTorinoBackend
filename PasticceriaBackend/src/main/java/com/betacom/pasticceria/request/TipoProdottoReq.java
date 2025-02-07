package com.betacom.pasticceria.request;

public class TipoProdottoReq {
	private Integer id;
	private String descrizione;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public String toString() {
		return "TipoProdottoReq [id=" + id + ", descrizione=" + descrizione + "]";
	}
	
	
}
