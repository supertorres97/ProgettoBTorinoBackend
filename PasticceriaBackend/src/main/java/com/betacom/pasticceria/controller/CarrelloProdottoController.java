package com.betacom.pasticceria.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.pasticceria.dto.CarrelloProdottoDTO;
import com.betacom.pasticceria.request.CarrelloProdottoReq;
import com.betacom.pasticceria.request.CarrelloReq;
import com.betacom.pasticceria.response.ResponseBase;
import com.betacom.pasticceria.response.ResponseList;
import com.betacom.pasticceria.services.interfaces.CarrelloProdottoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/carrelloProdotto")
public class CarrelloProdottoController {

	private CarrelloProdottoService cartS;
	private Logger log;
	
	@Autowired
	public CarrelloProdottoController (CarrelloProdottoService cartS, Logger log) {
		this.cartS = cartS;
		this.log = log;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody(required = true) CarrelloProdottoReq req) {
		
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		
		try {
			cartS.create(req);
		} catch (Exception e) {
			log.error("errore creazione carrello prodotto");
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/remove")
	public ResponseBase remove(@RequestBody(required = true) CarrelloProdottoReq req) {
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		
		try {
			cartS.remove(req);
		} catch (Exception e) {
			log.error("errore rimozione carrello prodotto");
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/update")
	public ResponseBase update(@RequestBody(required = true) CarrelloProdottoReq req) {
		log.debug("Update carrello prodotto (aggiorna solo la quantita)");
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		
		try {
			cartS.update(req);
		} catch (Exception e) {
			log.error("errore update carrello prodotto");
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/acquista")
	public ResponseBase acquista(@RequestBody(required = true) CarrelloReq req) {
		log.debug("acquista..... carrello: " + req.getId());
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		
		try {
			cartS.acuqista(req);
		} catch (Exception e) {
			log.error("errore acquista carrello prodotto");
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/listByCarrello")
	public ResponseList<CarrelloProdottoDTO> listByCarello(Integer idCart) {
		log.debug("list by CartId: " + idCart);
		
		ResponseList<CarrelloProdottoDTO> r = new ResponseList<CarrelloProdottoDTO>();
		r.setRc(true);
		
		try {
			r.setDati(cartS.listByCarrello(idCart));
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/listByProdotto")
	public ResponseList<CarrelloProdottoDTO> listByProdotto(Integer idProd) {
		log.debug("list by idProd: " + idProd);
		
		ResponseList<CarrelloProdottoDTO> r = new ResponseList<CarrelloProdottoDTO>();
		r.setRc(true);
		
		try {
			r.setDati(cartS.listByProdotto(idProd));
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/svuotaCarrello")
	public ResponseBase svuotaCarrello(Integer idCart) {
		log.debug("svuotamento carrello: " + idCart);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		
		try {
			cartS.svuotaCarrello(idCart);
		} catch (Exception e) {
			log.error("errore svuotamento carrello prodotto");
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	
}
