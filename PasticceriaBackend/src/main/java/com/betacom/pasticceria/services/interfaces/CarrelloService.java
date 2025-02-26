package com.betacom.pasticceria.services.interfaces;

import java.util.List;

import com.betacom.pasticceria.dto.CarrelloDTO;
import com.betacom.pasticceria.model.Utente;

public interface CarrelloService {
	void create (Utente utente) throws Exception;
	List<CarrelloDTO> listAll() throws Exception;
	CarrelloDTO listByUtente(Integer idUtente) throws Exception;
}
