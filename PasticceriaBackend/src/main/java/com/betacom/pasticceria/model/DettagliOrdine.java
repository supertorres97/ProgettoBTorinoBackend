package com.betacom.pasticceria.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dettagli_ordine")
public class DettagliOrdine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_ordine", nullable = false)
	private Ordine ordine;
	
	@ManyToOne
	@JoinColumn(name = "id_prodotto", nullable = false)
	private Prodotto prodotto;
	
	@Column(nullable = false)
	private Double prezzoTotale;

	@Column(nullable = false)
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

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
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

	
	
}
