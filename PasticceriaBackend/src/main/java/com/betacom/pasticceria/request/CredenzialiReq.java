package com.betacom.pasticceria.request;

import com.betacom.pasticceria.model.Utente;

public class CredenzialiReq {
    private Integer id;
    private Integer idUtente;
    private String username;
    private String password;
    private Boolean attivo;
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setIdUtente(Integer idUtente) {
        this.idUtente = idUtente;
    }
   
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Integer getId() {
        return id;
    }
    
    public Integer getIdUtente() {
        return idUtente;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}
       
    
}
