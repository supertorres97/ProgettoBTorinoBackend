package com.betacom.pasticceria.controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.pasticceria.dto.CarrelloDTO;
import com.betacom.pasticceria.response.ResponseList;
import com.betacom.pasticceria.response.ResponseObject;
import com.betacom.pasticceria.services.interfaces.CarrelloService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("rest/carrello")
public class CarrelloController {

	private CarrelloService cartS;
	private Logger log;

	public CarrelloController(CarrelloService cartS, Logger log) {
		this.cartS = cartS;
		this.log = log;
	}

	@GetMapping("/listAll")
	public ResponseList<CarrelloDTO> listAll() {
		log.debug("listAll carrello");
		ResponseList<CarrelloDTO> r = new ResponseList<CarrelloDTO>();
		r.setRc(true);

		try {
			r.setDati(cartS.listAll());
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	@GetMapping("/listByUtente")
	public ResponseObject<CarrelloDTO> listByUtente(@RequestParam(required = false) Integer idUtente) {
		ResponseObject<CarrelloDTO> cart = new ResponseObject<CarrelloDTO>();
		cart.setRc(true);

		try {
			cart.setDati(cartS.listByUtente(idUtente));
		} catch (Exception e) {
			cart.setRc(false);
			cart.setMsg(e.getMessage());
		}
		return cart;
	}
}
