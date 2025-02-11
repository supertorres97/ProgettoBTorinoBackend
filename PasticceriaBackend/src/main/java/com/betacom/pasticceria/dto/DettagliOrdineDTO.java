package com.betacom.pasticceria.dto;

public class DettagliOrdineDTO {
	private Integer id;
	private OrdineDTO ordine;
	private ProdottoDTO prodotto;
	private Double prezzoTotale;
	private Integer quantitaFinale;
		
	public static class Builder {
		private Integer id;
		private OrdineDTO ordine;
		private ProdottoDTO prodotto;
		private Double prezzoTotale;
		private Integer quantitaFinale;
	        
	    public DettagliOrdineDTO build() {
        	DettagliOrdineDTO dto = new DettagliOrdineDTO();
	        dto.id = this.id;
	        dto.ordine = this.ordine;
	        dto.prodotto = this.prodotto;
	        dto.prezzoTotale = this.prezzoTotale;
	        dto.quantitaFinale = this.quantitaFinale;
	        return dto;
	    }

		public Builder setId(Integer id) {
			this.id = id;
			return this;
		}

		public Builder setOrdine(OrdineDTO ordine) {
			this.ordine = ordine;
			return this;
		}

		public Builder setProdotto(ProdottoDTO prodotto) {
			this.prodotto = prodotto;
			return this;
		}

		public Builder setPrezzoTotale(Double prezzoTotale) {
			this.prezzoTotale = prezzoTotale;
			return this;
		}

		public Builder setQuantitaFinale(Integer quantitaFinale) {
			this.quantitaFinale = quantitaFinale;
			return this;
		}
		
	}
		public Integer getId() {
			return id;
		}

		public OrdineDTO getOrdine() {
			return ordine;
		}

		public ProdottoDTO getProdotto() {
			return prodotto;
		}

		public Double getPrezzoTotale() {
			return prezzoTotale;
		}

		public Integer getQuantitaFinale() {
			return quantitaFinale;
		}

		@Override
		public String toString() {
			return "DettagliOrdineReq [id=" + id + ", ordine=" + ordine + ", prodotto=" + prodotto + ", prezzoTotale="
					+ prezzoTotale + ", quantitaFinale=" + quantitaFinale + "]";
		}
}
