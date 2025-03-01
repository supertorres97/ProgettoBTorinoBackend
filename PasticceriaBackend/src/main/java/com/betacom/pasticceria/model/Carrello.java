package com.betacom.pasticceria.model;

import java.util.List;

import com.betacom.jpa.models.Attivita;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrello")
public class Carrello {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name="abbonamento_attivita",
			joinColumns = @JoinColumn(name="id_carrello"),
			inverseJoinColumns = @JoinColumn(name = "id_prodotto"))
	private List<Prodotto> prodotti;
}
