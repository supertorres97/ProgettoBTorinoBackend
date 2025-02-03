package com.betacom.pasticceria.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "carrello")
public class Carrello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "carrello", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CarrelloProdotto> prodottiCarrello;

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
}
