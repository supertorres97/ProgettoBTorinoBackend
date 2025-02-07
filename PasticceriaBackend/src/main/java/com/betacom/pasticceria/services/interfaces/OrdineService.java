package com.betacom.pasticceria.services.interfaces;

import com.betacom.pasticceria.request.OrdineReq;

public interface OrdineService {
	
	void create(OrdineReq req) throws Exception;
	
	void update(OrdineReq req) throws Exception;

	void logicalDelete(Integer id) throws Exception;
	
	// TODO: listAll e listById

}
