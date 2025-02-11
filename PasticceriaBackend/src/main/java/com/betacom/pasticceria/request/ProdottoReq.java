package com.betacom.pasticceria.request;

public class ProdottoReq {
	private Integer id;
    private Integer tipo;
    private String nome;
    private String descrizione;
    private Double peso;
    private Double prezzo;
    private Integer stock;
    public Boolean disponibile;
    
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getTipo() {
		return tipo;
	}
	
	public void setTipo(Integer tipo) {
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

	@Override
	public String toString() {
		return "ProdottoReq [id=" + id + ", tipo=" + tipo + ", nome=" + nome + ", descrizione=" + descrizione
				+ ", peso=" + peso + ", prezzo=" + prezzo + ", stock=" + stock + ", disponibile=" + disponibile + "]";
	}

}
