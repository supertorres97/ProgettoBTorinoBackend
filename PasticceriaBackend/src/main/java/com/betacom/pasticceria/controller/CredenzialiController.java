package com.betacom.pasticceria.controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.pasticceria.dto.CredenzialiDTO;
import com.betacom.pasticceria.request.CredenzialiReq;
import com.betacom.pasticceria.response.ResponseBase;
import com.betacom.pasticceria.response.ResponseList;
import com.betacom.pasticceria.response.ResponseObject;
import com.betacom.pasticceria.services.interfaces.CredenzialiService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/rest/credenziali")
public class CredenzialiController {
    
    private CredenzialiService credS;
	private Logger log;


    
	public CredenzialiController(CredenzialiService credS, Logger log) {
		super();
		this.credS = credS;
		this.log = log;
	}

    
    @PostMapping("/create")
	public ResponseBase create(@RequestBody(required = true) CredenzialiReq req) {
		log.debug("Create credenziali: " + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			credS.create(req);
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

    @PostMapping("/update")
	public ResponseBase update(@RequestBody(required = true) CredenzialiReq req) {
		log.debug("Update credenziali: " + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			credS.update(req);
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody(required = true) CredenzialiReq req) {
		log.debug("Delete credenziali: " + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			credS.delete(req);
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listByID")
	public ResponseObject<CredenzialiDTO> listByID(Integer id){	
		ResponseObject<CredenzialiDTO> r = new ResponseObject<CredenzialiDTO>();
		r.setRc(true);
		try {
			r.setDati(credS.listByID(id));
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listAll")
	public ResponseList<CredenzialiDTO> listAll() {
		log.debug("Lista credenziali: ");
		ResponseList<CredenzialiDTO> r = new ResponseList<CredenzialiDTO>();
		r.setRc(true);
		try {
			r.setDati(credS.listAll());
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		
		return r;
	}
    


}
