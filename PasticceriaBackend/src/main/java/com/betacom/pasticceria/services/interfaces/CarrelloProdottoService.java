package com.betacom.pasticceria.services.interfaces;

import com.betacom.pasticceria.model.Carrello;
import com.betacom.pasticceria.request.CarrelloProdottoReq;

public interface CarrelloProdottoService {
	void create(CarrelloProdottoReq req) throws Exception;
	void remove(CarrelloProdottoReq req) throws Exception;
	void update(CarrelloProdottoReq req) throws Exception;
	void acuqista(Carrello cart) throws Exception;
}
