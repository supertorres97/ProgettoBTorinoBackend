package com.betacom.pasticceria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.pasticceria.model.Credenziali;

public interface CredenzialiRepository extends JpaRepository<Credenziali, Integer> {
	Optional<Credenziali> findByUserNameAndPwd(String userName, String pwd);
}
