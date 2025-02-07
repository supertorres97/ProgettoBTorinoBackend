package com.betacom.pasticceria.services.interfaces;

import java.util.List;

import com.betacom.pasticceria.dto.ProdottoDTO;
import com.betacom.pasticceria.request.ProdottoReq;

public interface ProdottoService {
	void create(ProdottoReq req) throws Exception;
	void update(ProdottoReq req) throws Exception;
	void delete(ProdottoReq req) throws Exception;
	List<ProdottoDTO> listAll();
	ProdottoDTO listByID (Integer id) throws Exception;
}
