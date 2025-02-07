package com.betacom.pasticceria.services.implementations;

import java.util.Optional;
import org.slf4j.Logger;
import com.betacom.pasticceria.model.Ordine;
import com.betacom.pasticceria.model.Status;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.repositories.OrdineRepository;
import com.betacom.pasticceria.request.OrdineReq;
import com.betacom.pasticceria.services.interfaces.OrdineService;

public class OrdineImpl implements OrdineService{
	
	private OrdineRepository ordR;
	private UtenteRepository utnR;
	private Logger log;
	
	public OrdineImpl(OrdineRepository ordR, UtenteRepository utnR, Logger log) {
		this.log = log;
		this.ordR= ordR;
		this.utnR = utnR;
	}


	@Override
	public void create(OrdineReq req) throws Exception {
		Optional<Utente> utn = utnR.findById(req.getUtente());
		Optional<Ordine> ord = ordR.findById(req.getId());
		if(ord.isPresent()) {
			throw new Exception("Ordine gia presente");
		}
		if(utn.isEmpty()) {
			throw new Exception("Nessun utente ha fatto ordine");
		}
		
		Ordine o = new Ordine();
		o.setStatus(Status.Confermato);
//		o.setIndirizzo();
		o.setUtente(utn.get());
		o.setTotale(req.getTotale());
		o.setIndirizzo(utn.get().getVia() + utn.get().getCAP() + utn.get().getCitta());
//		NON RICORDO COME METTERE LA DATA CORRENTE E SE LA DEVO METTERE (MATTER)	
		
		ordR.save(o);
	}

	@Override
	public void update(OrdineReq req) throws Exception {
		
		
	}

}
