package com.betacom.pasticceria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.betacom.pasticceria.model.Messaggio;

public interface MessaggioRepository extends JpaRepository<Messaggio, Integer>{
	Optional<Messaggio> findByCodice(String codice);

}
