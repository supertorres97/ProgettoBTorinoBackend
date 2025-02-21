package com.betacom.pasticceria.services.interfaces;

import java.util.List;

import com.betacom.pasticceria.dto.OrdineDTO;
import com.betacom.pasticceria.model.Ordine;
import com.betacom.pasticceria.request.OrdineReq;

public interface OrdineService {
	
	Ordine create(OrdineReq req) throws Exception;
	void update(OrdineReq req) throws Exception;
	void logicalDelete(Integer id) throws Exception;
	List<OrdineDTO> listAll();
	OrdineDTO listByID(Integer id) throws Exception;
	List<OrdineDTO> listByUtente(Integer idUtente) throws Exception;
	boolean isOrderOwnedByUser(Integer orderId, Integer userId);
}
