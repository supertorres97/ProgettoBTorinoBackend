package com.betacom.pasticceria.dto;

public class CarrelloDTO {
	
	private Integer id;
	private UtenteDTO utente;
//	private List<CarrelloProdottoDTO> prodottiCarrello;
	
	private CarrelloDTO() { }
	
	public static class Builder{
		
		private Integer id;
		private UtenteDTO utente;
//		private List<CarrelloProdottoDTO> prodottiCarrello;
		
		public Builder() { }
		
		public CarrelloDTO build() {
			CarrelloDTO dto = new CarrelloDTO();
			
			dto.id = this.id;
			dto.utente = this.utente;
//			dto.prodottiCarrello = this.prodottiCarrello;
			
			return dto;
		}

		public Builder setId(Integer id) {
			this.id = id;
			return this;
		}

		public Builder setUtente(UtenteDTO utente) {
			this.utente = utente;
			return this;
		}

//		public Builder setProdottiCarrello(List<CarrelloProdottoDTO> prodottiCarrello) {
//			this.prodottiCarrello = prodottiCarrello;
//			return this;
//		}
	}//Builder

	public Integer getId() {
		return id;
	}

	public UtenteDTO getUtente() {
		return utente;
	}

//	public List<CarrelloProdottoDTO> getProdottiCarrello() {
//		return prodottiCarrello;
//	}

	@Override
	public String toString() {
		return "CarrelloDTO [id=" + id + ", utente=" + utente + "]";
	}
}
