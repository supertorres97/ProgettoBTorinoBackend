package com.betacom.pasticceria.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_utente", nullable = false)
	private Utente utente;
	
	@ManyToOne
	@JoinColumn(name = "id_prodotto", nullable = false)
	private Prodotto prodotto;
	
	@ManyToOne
	@JoinColumn(name = "id_ordine", nullable = false)
	private Ordine ordine;
	
	@Column(nullable = false)
	private String descrizione;
	
	@Column(nullable = false)
	
	@Enumerated(EnumType.STRING)
	private Voto voto;
	
	@Column(nullable = false)
    private Date dataFeedback;
    
	public Date getDataFeedback() {
		return dataFeedback;
	}

	public void setDataFeedback(Date dataFeedback) {
		this.dataFeedback = dataFeedback;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Voto getVoto() {
		return voto;
	}

	public void setVoto(Voto voto) {
		this.voto = voto;
	}
	
}
