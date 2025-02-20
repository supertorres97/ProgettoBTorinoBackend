package com.betacom.pasticceria.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.pasticceria.model.Feedback;
import com.betacom.pasticceria.model.Prodotto;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

	List<Feedback> findByProdotto_id(Integer id);

}
