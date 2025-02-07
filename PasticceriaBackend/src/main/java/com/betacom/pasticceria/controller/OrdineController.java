package com.betacom.pasticceria.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.pasticceria.request.OrdineReq;
import com.betacom.pasticceria.request.UtenteCredenzialiReq;
import com.betacom.pasticceria.response.ResponseBase;
import com.betacom.pasticceria.services.interfaces.OrdineService;
import com.betacom.pasticceria.services.interfaces.UtenteService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/ordine")
public class OrdineController {
	
	private OrdineService ordS;
	private Logger log;
	
	public OrdineController (OrdineService ordS, Logger log) {
		this.ordS = ordS;
		this.log = log;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody(required = true) OrdineReq req) {
		log.debug("create utente" + req);
		
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		
		try {
			ordS.create(req);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return r;
	}

}
