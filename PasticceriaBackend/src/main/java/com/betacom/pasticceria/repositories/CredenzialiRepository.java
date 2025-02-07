package com.betacom.pasticceria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.pasticceria.model.Credenziali;

public interface CredenzialiRepository extends JpaRepository<Credenziali, Integer> {
    
}
