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
//    private List<FeedbackDTO> feedbackUtente;
    
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
//        private List<FeedbackDTO> feedbackUtente;
        
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
//       	dto.feedbackUtente = this.feedbackUtente;
        	
        	return dto;
        }//build

		public void setId(Integer id) {
			this.id = id;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public void setCognome(String cognome) {
			this.cognome = cognome;
		}

		public void setcFiscale(String cFiscale) {
			this.cFiscale = cFiscale;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void setVia(String via) {
			this.via = via;
		}

		public void setCAP(String cAP) {
			CAP = cAP;
		}

		public void setCitta(String citta) {
			this.citta = citta;
		}

//		public void setCarrello(CarrelloDTO carrello) {
//			this.carrello = carrello;
//		}

//		public void setCredenziali(CredenzialiDTO credenziali) {
//			this.credenziali = credenziali;
//		}

//		public void setFeedbackUtente(List<FeedbackDTO> feedbackUtente) {
//			this.feedbackUtente = feedbackUtente;
//		}
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

//	public List<FeedbackDTO> getFeedbackUtente() {
//		return feedbackUtente;
//	}
//	
	
	//DA FARE IN ATTESA DELLE CLASSI DTO MANCANTI
	@Override
	public String toString() {
		return "UtenteDTO [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", cFiscale=" + cFiscale + ", email="
				+ email + ", via=" + via + ", CAP=" + CAP + ", citta=" + citta + "]";
	}
}
