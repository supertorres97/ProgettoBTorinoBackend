package com.betacom.pasticceria.dto;

import com.betacom.pasticceria.model.Utente;

public class CredenzialiDTO {

    private Integer id;
    private Utente id_utente;
    private String username;
    private String password;

    // Costruttore privato per il builder
    private CredenzialiDTO(Builder builder) {
        this.id = builder.id;
        this.id_utente = builder.id_utente;
        this.username = builder.username;
        this.password = builder.password;
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

    public static class Builder {
        private Integer id;
        private Utente id_utente;
        private String username;
        private String password;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setId_utente(Utente id_utente) {
            this.id_utente = id_utente;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public CredenzialiDTO build() {
            return new CredenzialiDTO(this);
        }
    }
}