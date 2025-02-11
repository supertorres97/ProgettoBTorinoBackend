package com.betacom.pasticceria.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.pasticceria.dto.FeedbackDTO;
import com.betacom.pasticceria.dto.OrdineDTO;
import com.betacom.pasticceria.request.FeedbackReq;
import com.betacom.pasticceria.request.OrdineReq;
import com.betacom.pasticceria.request.UtenteCredenzialiReq;
import com.betacom.pasticceria.response.ResponseBase;
import com.betacom.pasticceria.response.ResponseList;
import com.betacom.pasticceria.response.ResponseObject;
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
	
	@PostMapping("/update")
	public ResponseBase update(@RequestBody(required = true) OrdineReq req) {
		log.debug("Update Ordine: " + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			ordS.update(req);
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(Integer id) {
		log.debug("Delete Ordine: " + id);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			ordS.logicalDelete(id);
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listByID")
	public ResponseObject<OrdineDTO> listByID(Integer id){	
		ResponseObject<OrdineDTO> r = new ResponseObject<OrdineDTO>();
		r.setRc(true);
		try {
			r.setDati(ordS.listByID(id));
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listAll")
	public ResponseList<OrdineDTO> listAll() {
		log.debug("Lista Ordini: ");
		ResponseList<OrdineDTO> r = new ResponseList<OrdineDTO>();
		r.setRc(true);
		try {
			r.setDati(ordS.listAll());
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		
		return r;
	}
	
	@GetMapping("/listByUtente")
	public ResponseList<OrdineDTO> listByUtente(Integer idUtente) {
		log.debug("Lista Ordini dell'utente: " + idUtente);
		ResponseList<OrdineDTO> r = new ResponseList<OrdineDTO>();
		r.setRc(true);
		try {
			r.setDati(ordS.listByUtente(idUtente));
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		
		return r;
	}

}
