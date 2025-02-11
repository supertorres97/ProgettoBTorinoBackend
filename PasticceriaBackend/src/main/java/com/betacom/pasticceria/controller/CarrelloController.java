package com.betacom.pasticceria.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.pasticceria.dto.CarrelloDTO;
import com.betacom.pasticceria.request.CarrelloReq;
import com.betacom.pasticceria.response.ResponseBase;
import com.betacom.pasticceria.response.ResponseList;
import com.betacom.pasticceria.services.interfaces.CarrelloService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("rest/carrello")
public class CarrelloController {

	private CarrelloService cartS;
	private Logger log;
	
	@Autowired
	public CarrelloController(CarrelloService cartS, Logger log) {
		this.cartS = cartS;
		this.log = log;
	}
	
//	@PostMapping("/create")
//	public ResponseBase create(@RequestBody(required = true) CarrelloReq req) {
//		log.debug("create carrello: " + req);
//		ResponseBase r = new ResponseBase();
//		r.setRc(true);
//		
//		try {
//			cartS.create(req);
//		} catch (Exception e) {
//			r.setRc(false);
//			r.setMsg(e.getMessage());
//		}
//		return r;
//	}
	
	@GetMapping("/listAll")
	public ResponseList<CarrelloDTO> listAll(){
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
}
