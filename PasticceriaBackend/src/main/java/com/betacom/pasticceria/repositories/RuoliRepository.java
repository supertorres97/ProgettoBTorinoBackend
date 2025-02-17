package com.betacom.pasticceria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.pasticceria.model.Ruoli;

public interface RuoliRepository extends JpaRepository<Ruoli, Integer> {    
    Optional<Ruoli> findByDescrizione(String descrizione);
}
