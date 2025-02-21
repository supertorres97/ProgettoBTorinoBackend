package com.betacom.pasticceria.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
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
	public ResponseEntity<?> signin(@RequestBody SignInReq req) {
	    try {
	        SignInDTO response = credS.signIn(req);
	        return ResponseEntity.ok(response);
	    } catch (InvalidCredentialsException e) { // Se le credenziali sono errate
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(Map.of("message", "Credenziali non valide"));
	    } catch (Exception e) { // Per errori generici
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(Map.of("message", "Errore interno, riprova più tardi"));
	    }
	}

	
	@RequestMapping(value = "/signup", method = RequestMethod.OPTIONS)
	public ResponseEntity<?> handleOptions2() {
		return ResponseEntity.ok().build();
	}
    
	@PostMapping("/signup")
	public ResponseEntity<Map<String, Object>> signup(@RequestBody SignUpReq req) {
	    log.debug(req.getUtenteReq().toString());
	    log.debug(req.getCredenzialiReq().toString());
	    ResponseBase r = new ResponseBase();
	    r.setRc(true);

	    try {
	        userService.create(req.getUtenteReq(), req.getCredenzialiReq());
	    } catch (Exception e) {
	        log.error(e.getMessage());
	        r.setMsg(e.getMessage());
	        r.setRc(false);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("success", r.getRc());
	    response.put("message", r.getRc() ? "Registrazione avvenuta con successo" : r.getMsg());

	    if (r.getRc()) {
	        return ResponseEntity.ok(response);
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
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
    
    @PostMapping("/updateCredenziali")
   	public ResponseBase updateCredenziali(@RequestBody(required = true) CredenzialiReq req) {
   		log.debug("Update credenziali da admin: " + req);
   		ResponseBase r = new ResponseBase();
   		r.setRc(true);
   		try {
   			credS.updateDaAdmin(req);
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
	
	@GetMapping("/listByIdUtente")
	public ResponseObject<CredenzialiDTO> listByIdUtente(Integer id){	
		log.debug("credenziali by id utente");
		ResponseObject<CredenzialiDTO> r = new ResponseObject<CredenzialiDTO>();
		r.setRc(true);
		try {
			r.setDati(credS.getCredenzialiByUtente(id));
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
