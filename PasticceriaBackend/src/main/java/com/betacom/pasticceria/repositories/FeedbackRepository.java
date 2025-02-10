package com.betacom.pasticceria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.pasticceria.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

}
