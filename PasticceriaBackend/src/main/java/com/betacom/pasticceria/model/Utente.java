package com.betacom.pasticceria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "utente")
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cognome;
	private String cFiscale;
	private String indirizzo;
	private String email;
	
	@OneToOne
	@JoinColumn(name = "id_carrello", referencedColumnName = "id", nullable = true)
	private Carrello carrello;
}
