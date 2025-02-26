package com.betacom.pasticceria.controller;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.betacom.pasticceria.dto.ProdottoDTO;
import com.betacom.pasticceria.request.ProdottoReq;
import com.betacom.pasticceria.response.ResponseBase;
import com.betacom.pasticceria.response.ResponseList;
import com.betacom.pasticceria.response.ResponseObject;
import com.betacom.pasticceria.services.interfaces.ProdottoService;

@RestController
@RequestMapping("/rest/prodotto")
public class ProdottoController {
	
	private ProdottoService prodS;
	private Logger log;
	

	public ProdottoController(ProdottoService prodS, Logger log) {
		super();
		this.prodS = prodS;
		this.log = log;
	}
	
//	@PostMapping("/create")
//	public ResponseBase create(@RequestBody(required = true) ProdottoReq req) {
//		log.debug("Create prodotto: " + req);
//		ResponseBase r = new ResponseBase();
//		r.setRc(true);
//		try {
//			prodS.create(req);
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			r.setMsg(e.getMessage());
//			r.setRc(false);
//		}
//		return r;
//	}
	
	@PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseBase> createProdotto(
	        @RequestPart("prodottoReq") ProdottoReq req,
	        @RequestPart("img") MultipartFile imgFile) {
	    ResponseBase response = new ResponseBase();
	    response.setRc(true);
	    try {
	        prodS.create(req, imgFile);
	        response.setMsg("Nuovo prodotto creato con successo");
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        response.setRc(false);
	        response.setMsg(e.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    }
	}

	
	@PostMapping("/update")
	public ResponseBase update(@RequestBody(required = true) ProdottoReq req) {
		log.debug("Update prodotto: " + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			prodS.update(req);
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody(required = true) ProdottoReq req) {
		log.debug("Delete prodotto: " + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			prodS.delete(req);
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listByID")
	public ResponseObject<ProdottoDTO> listByID(Integer id){	
		ResponseObject<ProdottoDTO> r = new ResponseObject<ProdottoDTO>();
		r.setRc(true);
		try {
			r.setDati(prodS.listByID(id));
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listAll")
	public ResponseList<ProdottoDTO> listAll() {
		log.debug("Lista Prodotto: ");
		ResponseList<ProdottoDTO> r = new ResponseList<ProdottoDTO>();
		r.setRc(true);
		try {
			r.setDati(prodS.listAll());
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		
		return r;
	}
	
	@GetMapping("/listByNome")
	public ResponseList<ProdottoDTO> listByNome(@RequestParam(required = false) String nome) {
		log.debug("Lista Prodotto: ");
		ResponseList<ProdottoDTO> r = new ResponseList<ProdottoDTO>();
		r.setRc(true);
		try {
			r.setDati(prodS.listByNome(nome));
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		
//		if(r.getRc() == false) 
//			r.setDati(prodS.listAll());)
		
		return r;
	}
	
	@GetMapping("/listByTipoProdotto")
	public ResponseList<ProdottoDTO> listByTipoProdotto(@RequestParam(required = false) Integer tipoProdotto) {
		log.debug("Lista Prodotto per tipo: ");
		ResponseList<ProdottoDTO> r = new ResponseList<ProdottoDTO>();
		r.setRc(true);
		try {
			r.setDati(prodS.listByTipoProdotto(tipoProdotto));
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		
//		if(r.getRc() == false) 
//			r.setDati(prodS.listAll());)
		
		return r;
	}
}
