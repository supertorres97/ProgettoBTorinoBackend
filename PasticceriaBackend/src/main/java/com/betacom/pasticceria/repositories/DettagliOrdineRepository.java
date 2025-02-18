package com.betacom.pasticceria.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.pasticceria.model.DettagliOrdine;
import com.betacom.pasticceria.model.Ordine;

public interface DettagliOrdineRepository extends JpaRepository<DettagliOrdine, Integer>{
	List<DettagliOrdine> findByOrdine_Id(Integer idOrdine);
    boolean existsByOrdine_IdAndProdotto_Id(Integer ordineId, Integer prodottoId);
    boolean existsByOrdineInAndProdotto_Id(List<Ordine> ordini, Integer prodottoId);
}
