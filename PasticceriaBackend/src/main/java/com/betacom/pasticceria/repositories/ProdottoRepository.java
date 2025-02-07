package com.betacom.pasticceria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.pasticceria.model.Prodotto;

public interface ProdottoRepository extends JpaRepository<Prodotto, Integer>{
	Optional<Prodotto> findByNome(String nome);
}
