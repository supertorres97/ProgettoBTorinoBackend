package com.betacom.pasticceria.dto;

public class CarrelloDTO {
	
	private Integer id;
	private UtenteDTO utente;
	
	private CarrelloDTO() { }
	
	public static class Builder{
		
		private Integer id;
		private UtenteDTO utente;
		
		public Builder() { }
		
		public CarrelloDTO build() {
			CarrelloDTO dto = new CarrelloDTO();
			
			dto.id = this.id;
			dto.utente = this.utente;
			
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

	}

	public Integer getId() {
		return id;
	}

	public UtenteDTO getUtente() {
		return utente;
	}

	@Override
	public String toString() {
		return "CarrelloDTO [id=" + id + ", utente=" + utente + "]";
	}
}
