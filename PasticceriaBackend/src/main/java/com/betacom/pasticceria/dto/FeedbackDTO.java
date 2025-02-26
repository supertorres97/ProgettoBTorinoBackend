package com.betacom.pasticceria.dto;

import java.util.Date;

import com.betacom.pasticceria.model.Voto;

public class FeedbackDTO {
	private Integer id;
	private UtenteDTO utente;
	private ProdottoDTO prodotto;
	private String descrizione;
	private Voto voto;
    private Date dataFeedback;
    
    public static class Builder {
    	private Integer id;
    	private UtenteDTO utente;
    	private ProdottoDTO prodotto;
    	private String descrizione;
    	private Voto voto;
        private Date dataFeedback;
        
        public FeedbackDTO build() {
        	FeedbackDTO dto = new FeedbackDTO();
            dto.id = this.id;
            dto.utente = this.utente;
            dto.prodotto = this.prodotto;
            dto.descrizione = this.descrizione;
            dto.voto = this.voto;
            dto.dataFeedback = this.dataFeedback;
            
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

		public Builder setProdotto(ProdottoDTO prodotto) {
			this.prodotto = prodotto;
			return this;
		}

		public Builder setDescrizione(String descrizione) {
			this.descrizione = descrizione;
			return this;
		}

		public Builder setVoto(Voto voto) {
			this.voto = voto;
			return this;
		}

		public Builder setDataFeedback(Date dataFeedback) {
			this.dataFeedback = dataFeedback;
			return this;
		}
        
    }

	public Integer getId() {
		return id;
	}

	
	public UtenteDTO getUtente() {
		return utente;
	}


	public ProdottoDTO getProdotto() {
		return prodotto;
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
