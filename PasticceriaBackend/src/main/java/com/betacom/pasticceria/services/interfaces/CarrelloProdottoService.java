package com.betacom.pasticceria.services.interfaces;

import java.util.List;

import com.betacom.pasticceria.dto.CarrelloProdottoDTO;
import com.betacom.pasticceria.model.Carrello;
import com.betacom.pasticceria.model.CarrelloProdotto;
import com.betacom.pasticceria.request.CarrelloProdottoReq;

public interface CarrelloProdottoService {
	void create(CarrelloProdottoReq req) throws Exception;
	void remove(CarrelloProdottoReq req) throws Exception;
	void update(CarrelloProdottoReq req) throws Exception;
	void acuqista(Carrello cart) throws Exception;
	List<CarrelloProdottoDTO> listByCarrello(Integer idC) throws Exception;
	List<CarrelloProdottoDTO> listByProdotto(Integer idProdotto) throws Exception;
}
