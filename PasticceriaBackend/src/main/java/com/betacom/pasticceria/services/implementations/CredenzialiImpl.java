package com.betacom.pasticceria.services.implementations;

import static com.betacom.pasticceria.utils.Utilities.buildUtenteDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.pasticceria.dto.CredenzialiDTO;
import com.betacom.pasticceria.dto.SignInDTO;
import com.betacom.pasticceria.dto.UtenteDTO;
import com.betacom.pasticceria.model.Credenziali;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.repositories.CredenzialiRepository;
import com.betacom.pasticceria.repositories.UtenteRepository;
import com.betacom.pasticceria.request.CredenzialiReq;
import com.betacom.pasticceria.request.SignInReq;
import com.betacom.pasticceria.services.interfaces.CredenzialiService;
import com.betacom.pasticceria.utils.Utilities;

@Service
public class CredenzialiImpl implements CredenzialiService{
    private CredenzialiRepository credR;
    private UtenteRepository utnR;
    private Logger log;
    UtenteRepository utRe;
    
    @Autowired
	public CredenzialiImpl(CredenzialiRepository credR,UtenteRepository utnR, Logger log) {
		super();
		this.credR = credR;
		this.utnR = utnR;
		this.log = log;
	}
    
    @Override
    public void create(CredenzialiReq req) throws Exception {
		log.debug("Create credenziali: " + req);
		Optional<Utente> utn = utnR.findById(req.getIdUtente());
		
		if(utn.isEmpty())
			throw new Exception("Utente inesistente!");
		
		Credenziali c = new Credenziali();
        c.setUtente(utn.get());
		c.setUsername(req.getUsername());
        c.setPassword(req.getPassword());
        c.setAttivo(true);
		
		credR.save(c);
		log.debug("Nuovo credenziali inserito");
	 
    }

    @Override
    public void update(CredenzialiReq req) throws Exception {
        log.debug("Update credenziali: " + req);
        
        Optional<Utente> utn = utnR.findById(req.getIdUtente());
		
		if(utn.isEmpty())
			throw new Exception("Utente inesistente!");

        Optional<Credenziali> cr = credR.findById(req.getId());
        if (cr.isPresent()) {
        	Credenziali c = cr.get();
        	if(req.getIdUtente() != null) 
        		c.setUtente(utn.get());
        	if(req.getUsername() != null)
        		c.setUsername(req.getUsername());
        	if(req.getPassword() != null)
        		c.setPassword(req.getPassword());
        	credR.save(c);
        log.debug("Credenziali aggiornate");
        } else {
        	throw new Exception("Credenziali non trovate");
    }

	}

    @Override
    public void delete(CredenzialiReq req) throws Exception {
		log.debug("Delete credenziali: " + req);
		
		Optional<Credenziali> cr = credR.findById(req.getId());
		if(cr.isEmpty())
			throw new Exception("Credenziali non esistente");
		
		Credenziali c = cr.get();		
		c.setAttivo(false); //cancellazione logica, NON fisica!!
		credR.save(c);
		
		log.debug("Credenziali Eliminate");
	}

    @Override
    public List<CredenzialiDTO> listAll() {
		List<Credenziali> lC = credR.findAll();
		return lC.stream()
				.map(c -> new CredenzialiDTO.Builder()
				.setId(c.getId())
				.setIdUtente(buildUtenteDTO(c.getUtente()))
                .setUsername(c.getUsername())
                .setPassword(c.getPassword())
                .setAttivo(c.getAttivo())
				.build())
				.collect(Collectors.toList());
	}

    @Override
    public CredenzialiDTO listByID(Integer id) throws Exception{
		Optional<Credenziali> cr = credR.findById(id);
		if(cr.isEmpty())
			throw new Exception("Credenziali non esistente");
		
		return new CredenzialiDTO.Builder()
				.setId(cr.get().getId())
				.setIdUtente(buildUtenteDTO(cr.get().getUtente()))
                .setUsername(cr.get().getUsername())
                .setPassword(cr.get().getPassword())
                .setAttivo(cr.get().getAttivo())
				.build();
	}
    @Override
	public SignInDTO signIn(SignInReq req) {
		log.debug("signin" + req);
		SignInDTO resp = new SignInDTO();
		Optional<Credenziali> usr = credR.findByUsernameAndPassword(req.getUsername(), req.getPwd());
		if(usr.isEmpty())
			resp.setLogged(false);
		if(!usr.get().getAttivo())
			resp.setLogged(false);
		else {
			resp.setLogged(true);
			resp.setRole(usr.get().getRuoli().toString());
		}
		return resp;
	}
    
    public UtenteDTO getUtenteByCredenziali(CredenzialiReq req) {
    	Optional<Credenziali> cred = credR.findByUsernameAndPassword(req.getUsername(), req.getPassword());
    	Optional<Utente> ut = utnR.findById(cred.get().getUtente().getId());
    	return Utilities.buildUtenteDTO(ut.get());
    }

}
