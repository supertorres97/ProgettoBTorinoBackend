package com.betacom.pasticceria.services.implementations;

import static com.betacom.pasticceria.utils.Utilities.buildUtenteDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.models.UserAngular;
import com.betacom.pasticceria.dto.CredenzialiDTO;
import com.betacom.pasticceria.dto.SignInDTO;
import com.betacom.pasticceria.model.Credenziali;
import com.betacom.pasticceria.repositories.CredenzialiRepository;
import com.betacom.pasticceria.request.CredenzialiReq;
import com.betacom.pasticceria.request.SignInReq;
import com.betacom.pasticceria.services.interfaces.CredenzialiService;

@Service
public class CredenzialiImpl implements CredenzialiService{

    @Autowired
    private CredenzialiRepository credR;
    private Logger log;

	public CredenzialiImpl(CredenzialiRepository credR, Logger log) {
		super();
		this.credR = credR;
		this.log = log;
	}
    
    @Override
    public void create(CredenzialiReq req) throws Exception {

		log.debug("Create credenziali: " + req);
		
		Credenziali c = new Credenziali();

        c.setUtente(req.getIdUtente());
		c.setUsername(req.getUsername());
        c.setPassword(req.getPassword());
		
		credR.save(c);
		log.debug("Nuovo credenziali inserito");
	 
    }

    @Override
    public void update(CredenzialiReq req) throws Exception {
	
        log.debug("Update credenziali: " + req);

    Optional<Credenziali> cr = credR.findById(req.getId());
    if (cr.isPresent()) {
        Credenziali c = cr.get();
        if(req.getIdUtente() != null) 
			c.setUtente(req.getIdUtente());
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
		credR.delete(c);
		
		log.debug("Credenziali Eliminato");
		
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
				.build();
	}
    @Override
	public SignInDTO signIn(SignInReq req) {
		log.debug("signin" + req);
		SignInDTO resp = new SignInDTO();
		Optional<Credenziali> usr = credR.findByUserNameAndPwd(req.getUserName(), req.getPwd());
		if(usr.isEmpty())
			resp.setLogged(false);
		else {
			resp.setLogged(true);
			resp.setRole(usr.get().getRuoli().toString());
		}
		return resp;
	}

}
