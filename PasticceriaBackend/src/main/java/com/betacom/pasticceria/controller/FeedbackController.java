package com.betacom.pasticceria.controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.pasticceria.dto.FeedbackDTO;
import com.betacom.pasticceria.request.FeedbackReq;
import com.betacom.pasticceria.response.ResponseBase;
import com.betacom.pasticceria.response.ResponseList;
import com.betacom.pasticceria.response.ResponseObject;
import com.betacom.pasticceria.services.interfaces.FeedbackService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/feedback")
public class FeedbackController {
	private FeedbackService feedS;
	private Logger log;
	
	public FeedbackController(FeedbackService feedS, Logger log) {
		super();
		this.feedS = feedS;
		this.log = log;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody(required = true) FeedbackReq req) {
		log.debug("Create Feedback: " + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			feedS.create(req);
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/update")
	public ResponseBase update(@RequestBody(required = true) FeedbackReq req) {
		log.debug("Update Feedback: " + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			feedS.update(req);
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody(required = true) FeedbackReq req) {
		log.debug("Delete Feedback: " + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			feedS.delete(req);
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listByID")
	public ResponseObject<FeedbackDTO> listByID(Integer id){	
		ResponseObject<FeedbackDTO> r = new ResponseObject<FeedbackDTO>();
		r.setRc(true);
		try {
			r.setDati(feedS.listByID(id));
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listByProdottoID")
	public ResponseList<FeedbackDTO> listByProdottoID(Integer id) {
		log.debug("Lista Feedback: ");
		ResponseList<FeedbackDTO> r = new ResponseList<FeedbackDTO>();
		r.setRc(true);
		try {
			r.setDati(feedS.listByProdottoID(id));
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		
		return r;
	}
	
	
	@GetMapping("/listAll")
	public ResponseList<FeedbackDTO> listAll() {
		log.debug("Lista Feedback: ");
		ResponseList<FeedbackDTO> r = new ResponseList<FeedbackDTO>();
		r.setRc(true);
		try {
			r.setDati(feedS.listAll());
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		
		return r;
	}
	
	@GetMapping("/getByUtenteProdotto")
	public ResponseObject<FeedbackDTO> getByUtenteProdotto(Integer utente, Integer prodotto){
		ResponseObject<FeedbackDTO> r = new ResponseObject<FeedbackDTO>();
		r.setRc(true);
		try {
			r.setDati(feedS.findByUtenteAndProdotto(utente, prodotto));
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
}
