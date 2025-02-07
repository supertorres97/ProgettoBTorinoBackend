package com.betacom.pasticceria.services.implementations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import org.slf4j.Logger;
import com.betacom.pasticceria.model.Ordine;
import com.betacom.pasticceria.model.Status;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.repositories.OrdineRepository;
import com.betacom.pasticceria.repositories.UtenteRepository;
import com.betacom.pasticceria.request.OrdineReq;
import com.betacom.pasticceria.services.interfaces.OrdineService;
import static com.betacom.pasticceria.utils.Utilities.convertStringToDate;

public class OrdineImpl implements OrdineService{
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);
	String dataString = formatter.format(new Date());
	
	
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

		o.setUtente(utn.get());
		o.setTotale(req.getTotale());
		Date dataOrdine = convertStringToDate(dataString);
		o.setDataOrdine(dataOrdine);
		o.setIndirizzo(utn.get().getVia() + utn.get().getCAP() + utn.get().getCitta());
		o.setStatus(Status.Confermato);
		
		ordR.save(o);
	}

	@Override
	public void update(OrdineReq req) throws Exception {
	    Optional<Ordine> ord = ordR.findById(req.getId());
	    if (ord.isEmpty()) {
	        throw new Exception("Ordine non trovato");
	    }
	    
	    Ordine o = ord.get();
	    try {
	        Status nuovoStatus = Status.valueOf(req.getStatus());
	        o.setStatus(nuovoStatus);
	    } catch (Exception e) {
	        throw new Exception("Stato non valido: " + req.getStatus());
	    }
	    
	    ordR.save(o);
	}
	
	@Override
	public void logicalDelete(Integer id) throws Exception {
	    Optional<Ordine> ord = ordR.findById(id);
	    if (ord.isEmpty()) {
	        throw new Exception("Ordine non trovato");
	    }

	    Ordine o = ord.get();
	    if (o.getStatus() == Status.Annullato) {
	        throw new Exception("L'ordine è già annullato");
	    }
	    
	    o.setStatus(Status.Annullato);
	    ordR.save(o);
	}
	

}
