package com.betacom.pasticceria.services.interfaces;

import java.util.List;

import com.betacom.pasticceria.dto.UtenteDTO;
import com.betacom.pasticceria.request.UtenteReq;

public interface UtenteService{
//	void create (UtenteReq req, CredenzialiReq cReq) throws Exception;
	void update (UtenteReq req) throws Exception;
//	void delete (UtenteReq req, CredenzialiReq cReq) throws Exception;
	List<UtenteDTO> listAll ();
	UtenteDTO findById (Integer id) throws Exception;
}
