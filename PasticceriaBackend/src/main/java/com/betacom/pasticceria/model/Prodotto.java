package com.betacom.pasticceria.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name="prodotto")
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_tipoProdotto", nullable= false)
    private TipoProdotto tipo;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private Double peso;

    @Column(nullable = false)
    private Double prezzo;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    public Boolean disponibile;

    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CarrelloProdotto> carrelliProdotti;
    
    @Column(nullable = false)
    public String img;
    
    public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoProdotto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProdotto tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getDisponibile() {
        return disponibile;
    }

    public void setDisponibile(Boolean disponibile) {
        this.disponibile = disponibile;
    }

    public List<CarrelloProdotto> getCarrelliProdotti() {
        return carrelliProdotti;
    }

    public void setCarrelliProdotti(List<CarrelloProdotto> carrelliProdotti) {
        this.carrelliProdotti = carrelliProdotti;
    }
}
