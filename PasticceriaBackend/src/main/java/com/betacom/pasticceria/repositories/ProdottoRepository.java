package com.betacom.pasticceria.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.pasticceria.model.Prodotto;

public interface ProdottoRepository extends JpaRepository<Prodotto, Integer>{
	Optional<Prodotto> findByNome(String nome);
	
	@Query("SELECT p FROM Prodotto p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
	List<Prodotto> findByNomeContaining(@Param("nome") String nome);
}
