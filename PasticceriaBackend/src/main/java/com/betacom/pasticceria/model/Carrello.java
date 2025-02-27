package com.betacom.pasticceria.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "carrello")
public class Carrello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "carrello", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CarrelloProdotto> prodottiCarrello;
    
    @OneToOne
	@JoinColumn(name = "id_utente", referencedColumnName = "id", nullable = false, unique = true)
	private Utente utente;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CarrelloProdotto> getProdottiCarrello() {
        return prodottiCarrello;
    }

    public void setProdottiCarrello(List<CarrelloProdotto> prodottiCarrello) {
        this.prodottiCarrello = prodottiCarrello;
    }

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
}
