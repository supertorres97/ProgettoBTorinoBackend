package com.betacom.pasticceria.services.interfaces;

import java.util.List;

import com.betacom.pasticceria.dto.CarrelloProdottoDTO;
import com.betacom.pasticceria.request.CarrelloProdottoReq;
import com.betacom.pasticceria.request.CarrelloReq;

public interface CarrelloProdottoService {
	void create(CarrelloProdottoReq req) throws Exception;
	void remove(Integer idCarrelloProdotto) throws Exception;
	void update(CarrelloProdottoReq req) throws Exception;
	void acuqista(CarrelloReq cart) throws Exception;
	void svuotaCarrello(Integer cartId) throws Exception;
	List<CarrelloProdottoDTO> listByCarrello(Integer idC) throws Exception;
	List<CarrelloProdottoDTO> listByProdotto(Integer idProdotto) throws Exception;
}
