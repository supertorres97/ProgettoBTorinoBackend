package com.betacom.pasticceria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.pasticceria.model.Carrello;
import com.betacom.pasticceria.model.Utente;

public interface CarrelloRepository extends JpaRepository<Carrello, Integer>{
	Optional<Carrello> findByUtente(Utente utente);
	Optional<Carrello> findByUtente_Id(Integer idUtente);
}
