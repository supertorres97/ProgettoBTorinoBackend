package com.betacom.pasticceria.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ruoli")
public class Ruoli {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String descrizione;

    @OneToMany(mappedBy = "ruolo",
			fetch = FetchType.EAGER)
    private List<Credenziali> credenziali;

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

    public List<Credenziali> getCredenziali() {
        return credenziali;
    }

    public void setCredenziali(List<Credenziali> credenziali) {
        this.credenziali = credenziali;
    }

	@Override
	public String toString() {
		return "Ruoli [id=" + id + ", descrizione=" + descrizione + ", credenziali=" + credenziali + "]";
	}
    
}