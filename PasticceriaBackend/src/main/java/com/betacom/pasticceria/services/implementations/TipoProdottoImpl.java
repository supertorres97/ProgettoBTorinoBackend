package com.betacom.pasticceria.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.pasticceria.dto.TipoProdottoDTO;
import com.betacom.pasticceria.model.TipoProdotto;
import com.betacom.pasticceria.repositories.TipoProdottoRepository;
import com.betacom.pasticceria.request.TipoProdottoReq;
import com.betacom.pasticceria.services.interfaces.TipoProdottoService;

@Service
public class TipoProdottoImpl implements TipoProdottoService{
	TipoProdottoRepository tPR;
	Logger log;
	
	@Autowired
	public TipoProdottoImpl(TipoProdottoRepository tPR, Logger log) {
		super();
		this.tPR = tPR;
		this.log = log;
	}
	
	@Override
	public void create(TipoProdottoReq req) throws Exception {
		log.debug("Create tipo prodotto: " + req);
		Optional<TipoProdotto> tP = tPR.findByDescrizione(req.getDescrizione());
		if(tP.isPresent())
			throw new Exception("Tipo di Prodotto già esistente");

		TipoProdotto p = new TipoProdotto();
		p.setDescrizione(req.getDescrizione());
		
		tPR.save(p);
		log.debug("Nuovo tipo prodotto inserito");
	}

	@Override
	public void update(TipoProdottoReq req) throws Exception {
		log.debug("Update tipo prodotto: " + req);
		
		Optional<TipoProdotto> tP = tPR.findByDescrizione(req.getDescrizione());
		if(tP.isPresent())
			throw new Exception("Tipo di Prodotto già esistente");
		
		Optional<TipoProdotto> tPID = tPR.findById(req.getId());
		if(tP.isEmpty())
			throw new Exception("Tipo di Prodotto non trovato");

		tPID.get().setDescrizione(req.getDescrizione());
		tPR.save(tPID.get());
		log.debug("Tipo prodotto modificato");		
	}

	@Override
	public void delete(TipoProdottoReq req) throws Exception {
		log.debug("Create tipo prodotto: " + req);
		Optional<TipoProdotto> tP = tPR.findById(req.getId());
		if(tP.isEmpty())
			throw new Exception("Tipo di Prodotto non trovato");

		tPR.delete(tP.get());
		log.debug("Nuovo tipo prodotto inserito");
	}

	@Override
	public List<TipoProdottoDTO> listAll() {
		List<TipoProdotto> lTP = tPR.findAll();
		return lTP.stream()
				.map(tp -> new TipoProdottoDTO.Builder()
						.setId(tp.getId())
						.setDescrizione(tp.getDescrizione())
						.build())
				.collect(Collectors.toList());
	}

	@Override
	public TipoProdottoDTO listByID(Integer id) throws Exception {
		Optional<TipoProdotto> tP = tPR.findById(id);
		return new TipoProdottoDTO.Builder()
						.setId(tP.get().getId())
						.setDescrizione(tP.get().getDescrizione())
						.build();
	}
	
}
