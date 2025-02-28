package com.betacom.pasticceria.dto;

public class CarrelloProdottoDTO {
	
	private Integer id;
    private CarrelloDTO carrello;
    private ProdottoDTO prodotto;
    private Integer quantita;
    
    public CarrelloProdottoDTO() { }
    
    public static class Builder{
    	
    	private Integer id;
        private CarrelloDTO carrello;
        private ProdottoDTO prodotto;
        private Integer quantita;
        
        public Builder() { }
        
        public CarrelloProdottoDTO build() {
        	CarrelloProdottoDTO dto = new CarrelloProdottoDTO();
        	
        	dto.id = this.id;
        	dto.carrello = this.carrello;
        	dto.prodotto = this.prodotto;
        	dto.quantita = this.quantita;
        	
        	return dto;
        }

		public Builder setId(Integer id) {
			this.id = id;
			return this;
		}

		public Builder setCarrello(CarrelloDTO carrello) {
			this.carrello = carrello;
			return this;
		}

		public Builder setProdotto(ProdottoDTO prodotto) {
			this.prodotto = prodotto;
			return this;
		}

		public Builder setQuantita(Integer quantita) {
			this.quantita = quantita;
			return this;
		}
    }

	public Integer getId() {
		return id;
	}

	public CarrelloDTO getCarrello() {
		return carrello;
	}

	public ProdottoDTO getProdotto() {
		return prodotto;
	}

	public Integer getQuantita() {
		return quantita;
	}

	@Override
	public String toString() {
		return "CarrelloProdottoDTO [id=" + id + ", carrello=" + carrello + ", prodotto=" + prodotto + ", quantita="
				+ quantita + "]";
	}
}
