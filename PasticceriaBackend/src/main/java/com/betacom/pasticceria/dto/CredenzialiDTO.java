package com.betacom.pasticceria.dto;

public class CredenzialiDTO {
    private Integer id;
    private UtenteDTO idUtente;
    private String username;
    private String password;
    private Boolean attivo;
    private RuoliDTO ruolo;

    public static class Builder {
        private Integer id;
        private UtenteDTO idUtente;
        private String username;
        private String password;
        private Boolean attivo;
        private RuoliDTO ruolo;

        public CredenzialiDTO build() {
            CredenzialiDTO dto = new CredenzialiDTO();
            dto.id = this.id;
            dto.idUtente = this.idUtente;
            dto.username = this.username;
            dto.password = this.password;
            dto.attivo = this.attivo;
            dto.ruolo = this.ruolo;
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
		
		public Builder setAttivo(Boolean attivo) {
			this.attivo = attivo;
			return this;
		}
		
		public Builder setRuolo(RuoliDTO ruolo) {
			this.ruolo = ruolo;
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
    
    public Boolean getAttivo() {
		return attivo;
	}
	
	public RuoliDTO getRuolo() {
		return ruolo;
	}

	@Override
	public String toString() {
		return "CredenzialiDTO [id=" + id + ", idUtente=" + idUtente + ", username=" + username + ", password="
				+ password + ", attivo=" + attivo + "]";
	}
    
}