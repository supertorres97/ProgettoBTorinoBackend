package com.betacom.pasticceria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.betacom.pasticceria.model.Ordine;

public interface OrdineRepository extends JpaRepository<Ordine, Integer>{
	List<Ordine> findByUtente_Id(Integer idUtente);
    boolean existsByIdAndUtente_Id(Integer idOrdine, Integer idUtente);
}
