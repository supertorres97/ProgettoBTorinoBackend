package com.betacom.pasticceria.dto;

public class CredenzialiDTO {
    private Integer id;
    private UtenteDTO idUtente;
    private String username;
    private String password;

    public static class Builder {
        private Integer id;
        private UtenteDTO idUtente;
        private String username;
        private String password;
        
        public CredenzialiDTO build() {
            CredenzialiDTO dto = new CredenzialiDTO();
            dto.id = this.id;
            dto.idUtente = this.idUtente;
            dto.username = this.username;
            dto.password = this.password;
            return dto;
        }
        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setIdUtente(UtenteDTO idUtente) {
            this.idUtente = idUtente;
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

    }
    
    public Integer getId() {
        return id;
    }
    public UtenteDTO getIdUtente() {
        return idUtente;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    
}