package com.betacom.pasticceria.services.interfaces;

import java.util.List;

import com.betacom.pasticceria.dto.RuoliDTO;
import com.betacom.pasticceria.request.RuoliReq;

public interface RuoliService {
    void create(RuoliReq req) throws Exception;
	void update(RuoliReq req) throws Exception;
	void delete(RuoliReq req) throws Exception;
	List<RuoliDTO> listAll();
	RuoliDTO listByID (Integer id) throws Exception;
	RuoliDTO listByDescrizione(String desc) throws Exception;
}
