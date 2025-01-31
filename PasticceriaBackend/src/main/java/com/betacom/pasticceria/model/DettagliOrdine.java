package com.betacom.pasticceria.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dettagli_ordine")
public class DettagliOrdine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	private Ordine ordine;
	
	@ManyToOne
	@JoinColumn(name = "id_prodotto", nullable = false)
	private List<Prodotto> prodotti;
	
	@Column(nullable = false)
	private Double totale;

	@Column(nullable = false)
	private Integer quantita;
}
