package com.betacom.pasticceria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.pasticceria.model.DettagliOrdine;

public interface DettagliOrdineRepository extends JpaRepository<DettagliOrdine, Integer>{
	Optional<DettagliOrdine> findByOrdine_Id(String idOrdine);
}
