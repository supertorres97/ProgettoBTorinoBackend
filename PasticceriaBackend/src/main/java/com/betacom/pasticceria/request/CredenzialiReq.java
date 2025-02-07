package com.betacom.pasticceria.request;

import com.betacom.pasticceria.model.Utente;

public class CredenzialiReq {

    private Integer id;
    private Utente id_utente;
    private String username;
    private String password;
    
    public void setId(Integer id) {
        this.id = id;
    }
    public void setId_utente(Utente id_utente) {
        this.id_utente = id_utente;
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
    public Utente getId_utente() {
        return id_utente;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    
    
    
}
