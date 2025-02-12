package com.betacom.pasticceria;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.pasticceria.request.CarrelloProdottoReq;
import com.betacom.pasticceria.services.interfaces.CarrelloProdottoService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarrelloProdottoTest {
	
	@Autowired
	CarrelloProdottoService cpS;
	
	@Test
	@Order(1)
	public void createCarrelloProdotto() throws Exception {
		CarrelloProdottoReq req = new CarrelloProdottoReq();
		
		req.setCarrello(1);
		req.setProdotto(1);
		cpS.create(req);
		
		req.setCarrello(1);
		req.setProdotto(1);
		cpS.create(req);
	}
}
