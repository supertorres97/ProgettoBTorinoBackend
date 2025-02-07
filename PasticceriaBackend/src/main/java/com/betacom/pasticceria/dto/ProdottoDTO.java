package com.betacom.pasticceria.dto;

public class ProdottoDTO {
	private Integer id;
    private TipoProdottoDTO tipo;
    private String nome;
    private String descrizione;
    private Double peso;
    private Double prezzo;
    private Integer stock;
    public Boolean disponibile;
    
    public ProdottoDTO() {super();}
    
    public static class Builder {
		private Integer id;
	    private TipoProdottoDTO tipo;
	    private String nome;
	    private String descrizione;
	    private Double peso;
	    private Double prezzo;
	    private Integer stock;
	    public Boolean disponibile;
	
	    public Builder() {}

		public ProdottoDTO build() {
	    	ProdottoDTO dto = new ProdottoDTO();
	    	dto.id = this.id;
	    	dto.tipo = this.tipo;
	    	dto.nome = this.nome;
	    	dto.descrizione = this.descrizione;
	    	dto.peso = this.peso;
	    	dto.prezzo = this.prezzo;
	    	dto.stock = this.stock;
	    	dto.disponibile = this.disponibile;
	    	
	    	return dto;
	    }

		public Builder setId(Integer id) {
			this.id = id;
			return this;
		}

		public Builder setTipo(TipoProdottoDTO tipo) {
			this.tipo = tipo;
			return this;
		}

		public Builder setNome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder setDescrizione(String descrizione) {
			this.descrizione = descrizione;
			return this;
		}

		public Builder setPeso(Double peso) {
			this.peso = peso;
			return this;
		}

		public Builder setPrezzo(Double prezzo) {
			this.prezzo = prezzo;
			return this;
		}

		public Builder setStock(Integer stock) {
			this.stock = stock;
			return this;
		}

		public Builder setDisponibile(Boolean disponibile) {
			this.disponibile = disponibile;
			return this;
		}
	    
    }

	public Integer getId() {
		return id;
	}

	public TipoProdottoDTO getTipo() {
		return tipo;
	}

	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public Double getPeso() {
		return peso;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public Integer getStock() {
		return stock;
	}

	public Boolean getDisponibile() {
		return disponibile;
	}

	@Override
	public String toString() {
		return "ProdottoDTO [id=" + id + ", tipo=" + tipo + ", nome=" + nome + ", descrizione=" + descrizione
				+ ", peso=" + peso + ", prezzo=" + prezzo + ", stock=" + stock + ", disponibile=" + disponibile + "]";
	}

}
