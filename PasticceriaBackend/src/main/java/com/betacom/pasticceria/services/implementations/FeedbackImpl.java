package com.betacom.pasticceria.services.implementations;

import java.lang.classfile.instruction.NewMultiArrayInstruction;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.pasticceria.dto.FeedbackDTO;
import com.betacom.pasticceria.model.Feedback;
import com.betacom.pasticceria.model.Ordine;
import com.betacom.pasticceria.model.Prodotto;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.repositories.FeedbackRepository;
import com.betacom.pasticceria.repositories.OrdineRepository;
import com.betacom.pasticceria.repositories.ProdottoRepository;
import com.betacom.pasticceria.repositories.UtenteRepository;
import com.betacom.pasticceria.request.FeedbackReq;
import com.betacom.pasticceria.services.interfaces.FeedbackService;

@Service
public class FeedbackImpl implements FeedbackService{
	
	FeedbackRepository feedR;
	OrdineRepository ordR;
	UtenteRepository utR;
	ProdottoRepository prodR;
	Logger log;
	
	@Autowired
	public FeedbackImpl(FeedbackRepository feedR, OrdineRepository ordR, UtenteRepository utR, ProdottoRepository prodR, Logger log) {
		super();
		this.feedR = feedR;
		this.ordR = ordR;
		this.utR = utR;
		this.log = log;
		this.prodR = prodR;
	}


	@Override
	public void create(FeedbackReq req) throws Exception {
		log.debug("creazione feedback: "+ req);
		Optional<Prodotto> prod = prodR.findById(req.getProdotto());
		if (prod.isEmpty()) 
			throw new Exception("Prodotto da recensire non inserito.");
		Optional<Utente> ut = utR.findById(req.getUtente());
		if(ut.isEmpty())
			throw new Exception("Utente non inserito.");
		Optional<Ordine> ord = ordR.findById(req.getOrdine());
		if(ut.isEmpty())
			throw new Exception("Utente non inserito.");
		
		Feedback f = new Feedback();
		f.setDescrizione(req.getDescrizione());
	
		f.setOrdine(ord.get());
		
		feedR.save(f);
	}



	@Override
	public void update(FeedbackReq req) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(FeedbackReq req) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<FeedbackDTO> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FeedbackDTO listByID(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
