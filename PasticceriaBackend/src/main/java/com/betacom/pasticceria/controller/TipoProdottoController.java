package com.betacom.pasticceria.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.pasticceria.dto.TipoProdottoDTO;
import com.betacom.pasticceria.request.TipoProdottoReq;
import com.betacom.pasticceria.response.ResponseBase;
import com.betacom.pasticceria.response.ResponseList;
import com.betacom.pasticceria.response.ResponseObject;
import com.betacom.pasticceria.services.interfaces.TipoProdottoService;

@RestController
@RequestMapping("/rest/tipoprodotto")
public class TipoProdottoController {
	private TipoProdottoService tPS;
	private Logger log;
	
	@Autowired
	public TipoProdottoController(TipoProdottoService tPS, Logger log) {
		super();
		this.tPS = tPS;
		this.log = log;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody(required = true) TipoProdottoReq req) {
		log.debug("Create prodotto: " + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			tPS.create(req);
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/update")
	public ResponseBase update(@RequestBody(required = true) TipoProdottoReq req) {
		log.debug("Update prodotto: " + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			tPS.update(req);
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody(required = true) TipoProdottoReq req) {
		log.debug("Delete prodotto: " + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			tPS.delete(req);
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listByID")
	public ResponseObject<TipoProdottoDTO> listByID(Integer id){	
		ResponseObject<TipoProdottoDTO> r = new ResponseObject<TipoProdottoDTO>();
		r.setRc(true);
		try {
			r.setDati(tPS.listByID(id));
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listAll")
	public ResponseList<TipoProdottoDTO> listAll() {
		log.debug("Lista tipo prodotti: ");
		ResponseList<TipoProdottoDTO> r = new ResponseList<TipoProdottoDTO>();
		r.setRc(true);
		try {
			r.setDati(tPS.listAll());
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		
		return r;
	}

}
