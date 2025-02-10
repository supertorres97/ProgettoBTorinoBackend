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

		public void setId(Integer id) {
			this.id = id;
		}

		public void setCarrello(CarrelloDTO carrello) {
			this.carrello = carrello;
		}

		public void setProdotto(ProdottoDTO prodotto) {
			this.prodotto = prodotto;
		}

		public void setQuantita(Integer quantita) {
			this.quantita = quantita;
		}
    }//Builder

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
