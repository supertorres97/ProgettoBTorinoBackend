package com.betacom.pasticceria.dto;

import java.util.List;

import com.betacom.pasticceria.model.Carrello;
import com.betacom.pasticceria.model.Credenziali;

public class UtenteDTO {

	/*
	 * SO TROPPO FUSO PER CAPIRE SE CARRELLO E CREDENZIALI VANADANO QUA DENTRO
	 */
	
	
	private Integer id;
	private String nome;
	private String cognome;
	private String cFiscale;
	private String email;
	private String via;
	private String CAP;
	private String citta; 
//    private CarrelloDTO carrello;
//    private CredenzialiDTO credenziali;
    private List<FeedbackDTO> feedbackUtente;
    
    private UtenteDTO() {}
    
    public static class Builder{
    	
    	private Integer id;
    	private String nome;
    	private String cognome;
    	private String cFiscale;
    	private String email;
    	private String via;
    	private String CAP;
    	private String citta; 
//        private CarrelloDTO carrello;
//        private CredenzialiDTO credenziali;
        private List<FeedbackDTO> feedbackUtente;
        
        public Builder() {}
        
        public UtenteDTO build() {
        	UtenteDTO dto = new UtenteDTO();
        	
        	dto.id = this.id;
        	dto.nome = this.nome;
        	dto.cognome = this.cognome;
        	dto.cFiscale = this.cFiscale;
        	dto.email = this.email;
        	dto.via = this.via;
        	dto.CAP = this.CAP;
        	dto.citta = this.citta;
//        	dto.carrello = this.carrello;
//        	dto.credenziali = this.credenziali;
        	dto.feedbackUtente = this.feedbackUtente;
        	
        	return dto;
        }//build

		public Builder setId(Integer id) {
			this.id = id;
			return this;
		}

		public Builder setNome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder setCognome(String cognome) {
			this.cognome = cognome;
			return this;
		}

		public Builder setcFiscale(String cFiscale) {
			this.cFiscale = cFiscale;
			return this;
		}

		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder setVia(String via) {
			this.via = via;
			return this;
		}

		public Builder setCAP(String cAP) {
			CAP = cAP;
			return this;
		}

		public Builder setCitta(String citta) {
			this.citta = citta;
			return this;
		}
//		public void setCarrello(CarrelloDTO carrello) {
//			this.carrello = carrello;
//		}

//		public void setCredenziali(CredenzialiDTO credenziali) {
//			this.credenziali = credenziali;
//		}

		public void setFeedbackUtente(List<FeedbackDTO> feedbackUtente) {
			this.feedbackUtente = feedbackUtente;
		}
    }//Builder

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getcFiscale() {
		return cFiscale;
	}

	public String getEmail() {
		return email;
	}

	public String getVia() {
		return via;
	}

	public String getCAP() {
		return CAP;
	}

	public String getCitta() {
		return citta;
	}

//	public CarrelloDTO getCarrello() {
//		return carrello;
//	}
//
//	public CredenzialiDTO getCredenziali() {
//		return credenziali;
//	}

	public List<FeedbackDTO> getFeedbackUtente() {
		return feedbackUtente;
	}
	

	@Override
	public String toString() {
		return "UtenteDTO [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", cFiscale=" + cFiscale + ", email="
				+ email + ", via=" + via + ", CAP=" + CAP + ", citta=" + citta + "]";
	}
}
