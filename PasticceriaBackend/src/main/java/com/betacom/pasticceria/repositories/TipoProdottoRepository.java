package com.betacom.pasticceria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.pasticceria.model.TipoProdotto;

public interface TipoProdottoRepository extends JpaRepository<TipoProdotto, Integer>{
	Optional<TipoProdotto> findByDescrizione(String nome);

}
