package com.betacom.pasticceria.services.interfaces;

import java.util.List;

import com.betacom.pasticceria.dto.TipoProdottoDTO;
import com.betacom.pasticceria.request.TipoProdottoReq;

public interface TipoProdottoService {
	void create(TipoProdottoReq req) throws Exception;
	void update(TipoProdottoReq req) throws Exception;
	void delete(TipoProdottoReq req) throws Exception;
	List<TipoProdottoDTO> listAll();
	TipoProdottoDTO listByID (Integer id) throws Exception;
}
