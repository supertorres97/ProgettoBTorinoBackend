package com.betacom.pasticceria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.betacom.pasticceria.model.Ordine;

public interface OrdineRepository extends JpaRepository<Ordine, Integer>{

}
