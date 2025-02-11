package com.betacom.pasticceria.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.pasticceria.dto.OrdineDTO;
import com.betacom.pasticceria.model.Ordine;
import com.betacom.pasticceria.model.Status;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.repositories.OrdineRepository;
import com.betacom.pasticceria.repositories.UtenteRepository;
import com.betacom.pasticceria.request.OrdineReq;
import com.betacom.pasticceria.services.interfaces.OrdineService;
import com.betacom.pasticceria.utils.Utilities;

@Service
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
	@Transactional(rollbackFor = Exception.class)
	public Ordine create(OrdineReq req) throws Exception {
		Optional<Ordine> ord = ordR.findById(req.getId());
		if(ord.isPresent()) {
			throw new Exception("Ordine gia presente");
		}
		
		Optional<Utente> utn = utnR.findById(req.getUtente());
		if(utn.isEmpty()) {
			throw new Exception("Nessun utente ha fatto ordine");
		}
		
		Ordine o = new Ordine();
		o.setUtente(utn.get());
		o.setTotale(req.getTotale());
		o.setIndirizzo(utn.get().getVia() + utn.get().getCAP() + utn.get().getCitta());
		o.setStatus(Status.Confermato);
	
		return ordR.save(o);
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


	@Override
	public List<OrdineDTO> listAll() {
		List<Ordine> lO = ordR.findAll();
		return lO.stream()
				.map(o -> new OrdineDTO.Builder()
						.setId(o.getId())
						.setUtente(Utilities.buildUtenteDTO(o.getUtente()))
						.setIndirizzo(o.getIndirizzo())
						.setTotale(o.getTotale())
						.setStatus(o.getStatus().toString())
						.setDataOrdine(o.getDataOrdine()).build())
				.collect(Collectors.toList());
	}


	@Override
	public OrdineDTO listByID(Integer id) throws Exception {
		Optional<Ordine> o = ordR.findById(id);
		if(o.isEmpty())
			throw new Exception("Id ordine non trovato");
		
		return new OrdineDTO.Builder()
						.setId(o.get().getId())
						.setUtente(Utilities.buildUtenteDTO(o.get().getUtente()))
						.setIndirizzo(o.get().getIndirizzo())
						.setTotale(o.get().getTotale())
						.setStatus(o.get().getStatus().toString())
						.setDataOrdine(o.get().getDataOrdine()).build();
	}


	@Override
	public List<OrdineDTO> listByUtente(Integer idUtente) throws Exception {
		List<Ordine> lO = ordR.findByUtente(idUtente);
		if(lO.isEmpty())
			throw new Exception("Utente non trovato");
		return lO.stream()
				.map(o -> new OrdineDTO.Builder()
						.setId(o.getId())
						.setUtente(Utilities.buildUtenteDTO(o.getUtente()))
						.setIndirizzo(o.getIndirizzo())
						.setTotale(o.getTotale())
						.setStatus(o.getStatus().toString())
						.setDataOrdine(o.getDataOrdine()).build())
				.collect(Collectors.toList());
	}
	

}