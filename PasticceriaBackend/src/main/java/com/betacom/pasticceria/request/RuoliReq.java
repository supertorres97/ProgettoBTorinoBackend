package com.betacom.pasticceria.request;

public class RuoliReq {
    private Integer id;
    private String descrizione;
    private Integer idCredenziali;
    
    public Integer getIdCredenziali() {
		return idCredenziali;
	}

	public void setIdCredenziali(Integer idCredenziali) {
		this.idCredenziali = idCredenziali;
	}

	public void setId(Integer id) {
        this.id = id;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Integer getId() {
        return id;
    }

    public String getDescrizione() {
        return descrizione;
    }

	@Override
	public String toString() {
		return "RuoliReq [id=" + id + ", descrizione=" + descrizione + ", idCredenziali=" + idCredenziali + "]";
	}
    
    

}
