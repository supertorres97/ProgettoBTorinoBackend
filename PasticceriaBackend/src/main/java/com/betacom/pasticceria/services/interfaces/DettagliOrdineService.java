package com.betacom.pasticceria.services.interfaces;

import java.util.List;

import com.betacom.pasticceria.dto.DettagliOrdineDTO;
import com.betacom.pasticceria.request.DettagliOrdineReq;

public interface DettagliOrdineService {
	void create(DettagliOrdineReq req) throws Exception;
	List<DettagliOrdineDTO> listAll();
	DettagliOrdineDTO listByID(Integer id) throws Exception;

}
