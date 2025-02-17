package com.betacom.pasticceria.services.interfaces;

import java.util.List;

import com.betacom.pasticceria.dto.CarrelloDTO;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.request.CarrelloReq;

public interface CarrelloService {
	void create (Utente utente) throws Exception;
	List<CarrelloDTO> listAll() throws Exception;
}
