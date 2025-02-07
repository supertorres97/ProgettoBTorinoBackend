package com.betacom.pasticceria.request;

import com.betacom.pasticceria.model.Ordine;

public class DettagliOrdineReq {
	private Integer id;
	private Ordine ordine;
	private Integer prodotto;
	private Double prezzoTotale;
	private Integer quantitaFinale;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Ordine getOrdine() {
		return ordine;
	}
	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}
	public Integer getProdotto() {
		return prodotto;
	}
	public void setProdotto(Integer prodotto) {
		this.prodotto = prodotto;
	}
	public Double getPrezzoTotale() {
		return prezzoTotale;
	}
	public void setPrezzoTotale(Double prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}
	public Integer getQuantitaFinale() {
		return quantitaFinale;
	}
	public void setQuantitaFinale(Integer quantitaFinale) {
		this.quantitaFinale = quantitaFinale;
	}
	@Override
	public String toString() {
		return "DettagliOrdineReq [id=" + id + ", ordine=" + ordine + ", prodotto=" + prodotto + ", prezzoTotale="
				+ prezzoTotale + ", quantitaFinale=" + quantitaFinale + "]";
	}
	
	
}
