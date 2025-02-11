package com.betacom.pasticceria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.pasticceria.model.DettagliOrdine;

public interface DettagliOrdineRepository extends JpaRepository<DettagliOrdine, Integer>{
	Optional<DettagliOrdine> findByOrdine_Id(Integer idOrdine);
}
