package com.betacom.pasticceria.controller;

import org.slf4j.Logger;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.pasticceria.request.SignInReq;
import com.betacom.pasticceria.request.SignUpReq;
import com.betacom.pasticceria.dto.CredenzialiDTO;
import com.betacom.pasticceria.dto.SignInDTO;
import com.betacom.pasticceria.request.CredenzialiReq;
import com.betacom.pasticceria.response.ResponseBase;
import com.betacom.pasticceria.response.ResponseList;
import com.betacom.pasticceria.response.ResponseObject;
import com.betacom.pasticceria.services.interfaces.CredenzialiService;
import com.betacom.pasticceria.services.interfaces.UtenteService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/credenziali")
public class CredenzialiController {    
    private CredenzialiService credS;
	private Logger log;
    private UtenteService userService;

	public CredenzialiController(CredenzialiService credS, Logger log, UtenteService userService) {
		super();
		this.credS = credS;
		this.log = log;
		this.userService = userService;
	}
	
	@RequestMapping(value = "/signin", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }
	
	@PostMapping("/signin")
	public SignInDTO signin(@RequestBody(required = true) SignInReq req) throws Exception {
		log.debug("Signin: ");		
		return credS.signIn(req);
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions2() {
        return ResponseEntity.ok().build();
    }
    
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody SignUpReq req) {
		ResponseBase r = new ResponseBase();
	    r.setRc(true);
	 	try {
	 		userService.create(req.getUtenteReq(), req.getCredenzialiReq());
	 	} catch (Exception e) {
	 		log.error(e.getMessage());
	 		r.setMsg(e.getMessage());
	 		r.setRc(false);
	 	}
	 	
	    if (r.getRc()) {
	    	return ResponseEntity.ok("Registrazione avvenuta con successo");
	    } else {
	        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body("Errore nella registrazione");
	    }
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
