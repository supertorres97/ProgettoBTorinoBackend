package com.betacom.pasticceria.request;

public class CarrelloProdottoReq {

	private Integer id;
    private Integer carrello;
    private Integer prodotto;
    private Integer quantita;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCarrello() {
		return carrello;
	}
	public void setCarrello(Integer carrello) {
		this.carrello = carrello;
	}
	public Integer getProdotto() {
		return prodotto;
	}
	public void setProdotto(Integer prodotto) {
		this.prodotto = prodotto;
	}
	public Integer getQuantita() {
		return quantita;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	@Override
	public String toString() {
		return "CarrelloProdottoReq [id=" + id + ", carrello=" + carrello + ", prodotto=" + prodotto + ", quantita="
				+ quantita + "]";
	}
}
