package com.betacom.pasticceria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.pasticceria.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer>{
	
	Optional<Utente> findByEmail(String email);
}
