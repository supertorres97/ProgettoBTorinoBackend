package com.betacom.pasticceria.dto;

public class TipoProdottoDTO {
	private Integer id;
	private String descrizione;
	
	 public static class Builder {
			private Integer id;
		    private String descrizione;
		
		    public Builder() {}

			public TipoProdottoDTO build() {
		    	TipoProdottoDTO dto = new TipoProdottoDTO();
		    	dto.id = this.id;
		    	dto.descrizione = this.descrizione;
		    	
		    	return dto;
		    }

			public Builder setId(Integer id) {
				this.id = id;
				return this;
			}

			public Builder setDescrizione(String descrizione) {
				this.descrizione = descrizione;
				return this;
			}
	    }
	 
	
	public Integer getId() {
		return id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	@Override
	public String toString() {
		return "TipoProdottoDTO [id=" + id + ", descrizione=" + descrizione + "]";
	}
	
}
