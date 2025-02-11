package com.betacom.pasticceria.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.pasticceria.model.Carrello;
import com.betacom.pasticceria.model.CarrelloProdotto;
import com.betacom.pasticceria.model.Prodotto;

public interface CarrelloProdottoRepository extends JpaRepository<CarrelloProdotto, Integer>{
	Optional<CarrelloProdotto> findByProdottoAndCarrello(Prodotto prod, Carrello cart);
	List<CarrelloProdotto> findAllByCarrello(Carrello cart);
}
