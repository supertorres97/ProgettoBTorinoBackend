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
import com.betacom.pasticceria.model.Ruoli;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.repositories.CredenzialiRepository;
import com.betacom.pasticceria.repositories.RuoliRepository;
import com.betacom.pasticceria.repositories.UtenteRepository;
import com.betacom.pasticceria.request.CredenzialiReq;
import com.betacom.pasticceria.request.SignInReq;
import com.betacom.pasticceria.services.interfaces.CredenzialiService;
import com.betacom.pasticceria.services.interfaces.MessaggioService;
import com.betacom.pasticceria.utils.Utilities;

@Service
public class CredenzialiImpl implements CredenzialiService{
    private CredenzialiRepository credR;
    private UtenteRepository utnR;
    private RuoliRepository ruolR;
    private MessaggioService msgS;
    private Logger log;
    
    @Autowired
	public CredenzialiImpl(CredenzialiRepository credR,UtenteRepository utnR, Logger log, RuoliRepository ruolR, MessaggioService msgS) {
		super();
		this.credR = credR;
		this.utnR = utnR;
		this.ruolR = ruolR;
		this.msgS = msgS;
		this.log = log;
	}
    
    @Override
    public void create(CredenzialiReq req) throws Exception {
		Optional<Utente> utn = utnR.findById(req.getIdUtente());
		
		if(utn.isEmpty())
			throw new Exception();
		
		Credenziali c = new Credenziali();
        c.setUtente(utn.get());
		c.setUsername(req.getUsername());
        c.setPassword(req.getPassword());
        c.setAttivo(true);
        Ruoli r = ruolR.findById(2)
        		.orElseThrow(() -> new RuntimeException(msgS.getMessaggio("USER_NOT_FOUND")));
        c.setRuolo(r);
		credR.save(c);
		log.debug(msgS.getMessaggio("CREDENZIALI_INSERITE"));
	 
    }

    @Override
    public void update(CredenzialiReq req) throws Exception {
        log.debug("Update credenziali: " + req);

        Optional<Credenziali> cr = credR.findById(req.getId());
        if (cr.isEmpty()) 
            throw new Exception(msgS.getMessaggio("CREDENZIALI_NOT_FOUND"));

        Optional<Utente> utn = utnR.findById(req.getIdUtente());
        if(utn.isEmpty())
            throw new Exception(msgS.getMessaggio("UTENTE_INESISTENTE"));

        Credenziali c = cr.get();
        if(req.getPassword() != null)
            if(req.getPassword() != cr.get().getPassword())
                c.setPassword(req.getPassword());
            else
                throw new Exception(msgS.getMessaggio("PASSWORD_UGUALE_PRECEDENTE"));
        credR.save(c);

        msgS.getMessaggio("CREDENZIALI_AGGIORNATE");
    }

    @Override
    public void updateDaAdmin(CredenzialiReq req) throws Exception {
        log.debug("Update credenziali: " + req);

        Optional<Credenziali> cr = credR.findById(req.getId());
        if (cr.isEmpty()) 
            throw new Exception(msgS.getMessaggio("CREDENZIALI_NOT_FOUND"));

        Optional<Utente> utn = utnR.findById(req.getIdUtente());
        if(utn.isEmpty())
            throw new Exception(msgS.getMessaggio("UTENTE_INESISTENTE"));

        Credenziali c = cr.get();
        if(req.getUsername()!= null)
        	c.setUsername(req.getUsername());
        if(req.getPassword() != null)
            if(req.getPassword() != cr.get().getPassword())
                c.setPassword(req.getPassword());
            else
                throw new Exception(msgS.getMessaggio("PASSWORD_UGUALE_PRECEDENTE"));
        if(req.getAttivo() != null)
        	c.setAttivo(req.getAttivo());
        
        credR.save(c);

        msgS.getMessaggio("CREDENZIALI_AGGIORNATE");
    }
    
    @Override
    public void delete(CredenzialiReq req) throws Exception {
		log.debug("Delete credenziali: " + req);
		
		Optional<Credenziali> cr = credR.findById(req.getId());
		if(cr.isEmpty())
			throw new Exception(msgS.getMessaggio("CREDENZIALI_NOT_FOUND"));
		
		Credenziali c = cr.get();		
		c.setAttivo(false); //cancellazione logica, NON fisica!!
		credR.save(c);
		
		msgS.getMessaggio("CREDENZIALI_ELIMINATE");
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
			throw new Exception(msgS.getMessaggio("CREDENZIALI_NOT_FOUND"));
		
		return new CredenzialiDTO.Builder()
				.setId(cr.get().getId())
				.setIdUtente(buildUtenteDTO(cr.get().getUtente()))
                .setUsername(cr.get().getUsername())
                .setPassword(cr.get().getPassword())
                .setAttivo(cr.get().getAttivo())
				.build();
	}
    @Override
    public SignInDTO signIn(SignInReq req) throws Exception{
        log.debug("signin " + req);
        SignInDTO resp = new SignInDTO();
        Optional<Credenziali> usr = credR.findByUsernameAndPassword(req.getUsername(), req.getPwd());
        
        if (usr.isEmpty() || !usr.get().getAttivo()) {
            resp.setLogged(false);
            resp.setIdUtente(null); // 👈 Evitiamo un errore se non esiste
        } else {
            resp.setLogged(true);
            resp.setRole(usr.get().getRuolo().toString());

            // 👇 Controlliamo se l'utente esiste prima di prendere l'ID
            if (usr.get().getUtente() != null) {
                resp.setIdUtente(usr.get().getUtente().getId());
            } else {
            	Optional<Utente> utente = utnR.findById(usr.get().getUtente().getId());
            	if(utente.isEmpty())
            		throw new Exception(msgS.getMessaggio("UTENTE_NON_ASSOCIATO_CREDENZIALI"));
                resp.setIdUtente(utente.get().getId());
                msgS.getMessaggio("UTENTE_NON_ASSOCIATO_CREDENZIALI");
            }
        }
        
        return resp;
    }

    
    public UtenteDTO getUtenteByCredenziali(CredenzialiReq req) {
    	Optional<Credenziali> cred = credR.findByUsernameAndPassword(req.getUsername(), req.getPassword());
    	Optional<Utente> ut = utnR.findById(cred.get().getUtente().getId());
    	return Utilities.buildUtenteDTO(ut.get());
    }
    
    @Override
    public CredenzialiDTO getCredenzialiByUtente(Integer idUtente) throws Exception{
    	Optional<Credenziali> cr = credR.findByUtente_Id(idUtente);
    	if(cr.isEmpty())
    		throw new Exception("Nessuna credenziale trovata per l'utente con id: " + idUtente);
    	
    	return new CredenzialiDTO.Builder()
				.setId(cr.get().getId())
				.setIdUtente(buildUtenteDTO(cr.get().getUtente()))
                .setUsername(cr.get().getUsername())
                .setPassword(cr.get().getPassword())
                .setAttivo(cr.get().getAttivo())
				.build();    
    }
    
    @Override
    public void createAdmin(CredenzialiReq req) throws Exception {
    	log.debug("Create credenziali: " + req);
		Optional<Utente> utn = utnR.findById(req.getIdUtente());
		
		if(utn.isEmpty())
			throw new Exception(msgS.getMessaggio("UTENTE_INESISTENTE"));
		
		Credenziali c = new Credenziali();
        c.setUtente(utn.get());
		c.setUsername(req.getUsername());
        c.setPassword(req.getPassword());
        c.setAttivo(true);
        Ruoli r = ruolR.findById(1)
        		.orElseThrow(() -> new RuntimeException(msgS.getMessaggio("ADMIN_NOT_FOUND")));
        c.setRuolo(r);
        credR.save(c);
		log.debug(msgS.getMessaggio("CREDENZIALI_INSERITE"));
    }


}
