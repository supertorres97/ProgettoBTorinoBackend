package com.betacom.pasticceria.services.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.betacom.pasticceria.dto.ProdottoDTO;
import com.betacom.pasticceria.request.ProdottoReq;

public interface ProdottoService {
	void create(ProdottoReq req, MultipartFile imgFile) throws Exception;
	void update(ProdottoReq req) throws Exception;
	void delete(ProdottoReq req) throws Exception;
	List<ProdottoDTO> listAll();
	ProdottoDTO listByID (Integer id) throws Exception;
	List<ProdottoDTO> listByNome (String nome) throws Exception;
	List<ProdottoDTO> listByTipoProdotto (Integer tipoProdotto) throws Exception;
}
