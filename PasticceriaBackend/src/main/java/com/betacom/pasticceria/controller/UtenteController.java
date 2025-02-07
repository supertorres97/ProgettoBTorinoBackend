package com.betacom.pasticceria.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.pasticceria.dto.UtenteDTO;
import com.betacom.pasticceria.request.UtenteCredenzialiReq;
import com.betacom.pasticceria.request.UtenteReq;
import com.betacom.pasticceria.response.ResponseBase;
import com.betacom.pasticceria.response.ResponseList;
import com.betacom.pasticceria.response.ResponseObject;
import com.betacom.pasticceria.services.interfaces.UtenteService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/utente")
public class UtenteController {

	private UtenteService utnS;
	private Logger log;
	
	@Autowired
	public UtenteController (UtenteService utnS, Logger log) {
		this.utnS = utnS;
		this.log = log;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody(required = true) UtenteCredenzialiReq req) {
		log.debug("create utente" + req.getUtenteReq());
		
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		
		try {
			utnS.create(req.getUtenteReq(), req.getCredenzialiReq());
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/update")
	public ResponseBase update (@RequestBody(required = true) UtenteReq req) {
		log.debug("update utente" + req);
		
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		
		try {
			utnS.update(req);
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/listAll")
	public ResponseList<UtenteDTO> listAll(){
		log.debug("listAll");
		
		ResponseList<UtenteDTO> rl = new ResponseList<UtenteDTO>();
		rl.setRc(true);
		
		try {
			rl.setDati(utnS.listAll());
		} catch (Exception e) {
			log.error(e.getMessage());
			rl.setRc(false);
			rl.setMsg(e.getMessage());
		}
		return rl;
	}
	
	@GetMapping("/listById")
	public ResponseObject<UtenteDTO> listById(Integer id){
		log.debug("list by id: " + id);
		
		ResponseObject<UtenteDTO> ro = new ResponseObject<UtenteDTO>();
		ro.setRc(true);
		
		try {
			ro.setDati(utnS.listById(id));
		} catch (Exception e) {
			log.error(e.getMessage());
			ro.setRc(false);
			ro.setMsg(e.getMessage());
		}
		return ro;
	}
}
