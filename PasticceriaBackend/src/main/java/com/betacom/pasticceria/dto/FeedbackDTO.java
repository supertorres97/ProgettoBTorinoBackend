package com.betacom.pasticceria.dto;

import java.util.Date;

import com.betacom.pasticceria.dto.CredenzialiDTO.Builder;
import com.betacom.pasticceria.model.Ordine;
import com.betacom.pasticceria.model.Prodotto;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.model.Voto;

public class FeedbackDTO {
	private Integer id;
	private Utente utente;
	private Prodotto prodotto;
	private Ordine ordine;
	private String descrizione;
	private Voto voto;
    private Date dataFeedback;
    
    public static class Builder {
    	private Integer id;
    	private Utente utente;
    	private Prodotto prodotto;
    	private Ordine ordine;
    	private String descrizione;
    	private Voto voto;
        private Date dataFeedback;
        
        public FeedbackDTO build() {
        	FeedbackDTO dto = new FeedbackDTO();
            dto.id = this.id;
            dto.utente = this.utente;
            dto.prodotto = this.prodotto;
            dto.ordine = this.ordine;
            dto.descrizione = this.descrizione;
            dto.voto = this.voto;
            dto.dataFeedback = this.dataFeedback;
            
            return dto;
        }

		public void setId(Integer id) {
			this.id = id;
		}

		public void setUtente(Utente utente) {
			this.utente = utente;
		}

		public void setProdotto(Prodotto prodotto) {
			this.prodotto = prodotto;
		}

		public void setOrdine(Ordine ordine) {
			this.ordine = ordine;
		}

		public void setDescrizione(String descrizione) {
			this.descrizione = descrizione;
		}

		public void setVoto(Voto voto) {
			this.voto = voto;
		}

		public void setDataFeedback(Date dataFeedback) {
			this.dataFeedback = dataFeedback;
		}
        
    }

	public Integer getId() {
		return id;
	}

	public Utente getUtente() {
		return utente;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public Voto getVoto() {
		return voto;
	}

	public Date getDataFeedback() {
		return dataFeedback;
	}
    
}
