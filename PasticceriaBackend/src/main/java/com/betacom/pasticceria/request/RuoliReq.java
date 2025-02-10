package com.betacom.pasticceria.request;

public class RuoliReq {
    
    private Integer id;
    private String descrizione;

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

}
