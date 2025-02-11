package com.betacom.pasticceria.dto;

import java.util.Date;

public class OrdineDTO {	
	private Integer id;
	private UtenteDTO utente;
	private Double totale;
	private String indirizzo;
	private String status;
	private Date dataOrdine;
	
	private OrdineDTO() {}
	
	public static class Builder {	
		private Integer id;
		private UtenteDTO utente;
		private Double totale;
		private String indirizzo;
		private String status;
		private Date dataOrdine;
		
		public Builder() {
			
		}
		
		public OrdineDTO build() {
			OrdineDTO dto = new OrdineDTO();
			dto.id= this.id;
			dto.utente= this.utente;
			dto.totale= this.totale;
			dto.indirizzo= this.indirizzo;
			dto.status= this.status;
			dto.dataOrdine= this.dataOrdine;
			
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

		public Builder setTotale(Double totale) {
			this.totale = totale;
			return this;
		}

		public Builder setIndirizzo(String indirizzo) {
			this.indirizzo = indirizzo;
			return this;
		}

		public Builder setStatus(String status) {
			this.status = status;
			return this;
		}

		public Builder setDataOrdine(Date dataOrdine) {
			this.dataOrdine = dataOrdine;
			return this;
		}		
	}

	public Integer getId() {
		return id;
	}

	public UtenteDTO getUtente() {
		return utente;
	}

	public Double getTotale() {
		return totale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getStatus() {
		return status;
	}

	public Date getDataOrdine() {
		return dataOrdine;
	}

	@Override
	public String toString() {
		return "OrdineDTO [id=" + id + ", totale=" + totale + ", indirizzo=" + indirizzo + ", status=" + status
				+ ", dataOrdine=" + dataOrdine + "]";
	}
	
	
	
	
	
	
	
}
