package com.betacom.pasticceria.request;

import com.betacom.pasticceria.model.Utente;

public class CredenzialiReq {

    private Integer id;
    private Utente idUtente;
    private String username;
    private String password;
    
    public void setId(Integer id) {
        this.id = id;
    }
    public void setIdUtente(Utente idUtente) {
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
    public Utente getIdUtente() {
        return idUtente;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    
    
    
}
