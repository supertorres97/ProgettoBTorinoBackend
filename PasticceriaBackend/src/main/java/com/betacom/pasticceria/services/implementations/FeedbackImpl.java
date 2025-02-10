package com.betacom.pasticceria.services.implementations;

import static com.betacom.pasticceria.utils.Utilities.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.pasticceria.dto.CredenzialiDTO;
import com.betacom.pasticceria.dto.FeedbackDTO;
import com.betacom.pasticceria.model.Feedback;
import com.betacom.pasticceria.model.Ordine;
import com.betacom.pasticceria.model.Prodotto;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.model.Voto;
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
			throw new Exception("Prodotto da recensire non esistente.");
		
		Optional<Utente> ut = utR.findById(req.getUtente());
		if(ut.isEmpty())
			throw new Exception("Utente non trovato.");
		
		Optional<Ordine> ord = ordR.findById(req.getOrdine());
		if(ut.isEmpty())
			throw new Exception("Utente non trovato.");
		
		Feedback f = new Feedback();
		f.setDescrizione(req.getDescrizione());
		f.setProdotto(prod.get());
		f.setOrdine(ord.get());
		f.setUtente(ut.get());
		Voto voto =  Voto.valueOf(req.getVoto().toUpperCase());
		f.setVoto(voto);
		//prima di salvare, effettuare il controllo del prodotto su id_ordine e id_prodotto di dettagli ordine
		f.setDataFeedback(req.getDataFeedback());
		feedR.save(f);
	}



	@Override
	public void update(FeedbackReq req) throws Exception {
		Optional<Feedback> feed = feedR.findById(req.getId());
		if (feed.isEmpty()) 
			throw new Exception("Feedback non trovato.");
		
		Optional<Prodotto> prod = prodR.findById(req.getProdotto());
		if (prod.isEmpty()) 
			throw new Exception("Prodotto da recensire non esistente.");
		
		Optional<Utente> ut = utR.findById(req.getUtente());
		if(ut.isEmpty())
			throw new Exception("Utente non trovato.");
		
		Optional<Ordine> ord = ordR.findById(req.getOrdine());
		if(ut.isEmpty())
			throw new Exception("Utente non trovato.");
		Boolean mod = false;
		
		Feedback f = feed.get();
		if(req.getDescrizione() != null) {
			f.setDescrizione(req.getDescrizione());
			mod = true;
		}
			
		if(req.getVoto() != null) {
			Voto voto =  Voto.valueOf(req.getVoto().toUpperCase());
			f.setVoto(voto);
			mod = true;
		}
		
		if(mod) {
			f.setDataFeedback(req.getDataFeedback());
			feedR.save(f);
		}
		
	}

	@Override
	public void delete(FeedbackReq req) throws Exception {
		Optional<Feedback> feed = feedR.findById(req.getId());
		if(feed.isEmpty())
			throw new Exception("Feedeback non trovato");
		
		Feedback f = feed.get();
		feedR.delete(f);
	}

	@Override
	public List<FeedbackDTO> listAll() {
		List<Feedback> lF = feedR.findAll();
		return lF.stream()
				.map(f -> new FeedbackDTO.Builder()
						.setId(f.getId())
						.setUtente(buildUtenteDTO(f.getUtente()))
						.setOrdine(buildOrdineDTO(f.getOrdine()))
						.setProdotto(buildProdottoDTO(f.getProdotto()))
						.setDataFeedback(f.getDataFeedback())
						.setVoto(f.getVoto())
						.setDescrizione(f.getDescrizione())
						.build())
				.collect(Collectors.toList());
	}

	@Override
	public FeedbackDTO listByID(Integer id) throws Exception {
		Optional<Feedback> f = feedR.findById(id);
		return new FeedbackDTO.Builder()
						.setId(f.get().getId())
						.setUtente(buildUtenteDTO(f.get().getUtente()))
						.setOrdine(buildOrdineDTO(f.get().getOrdine()))
						.setProdotto(buildProdottoDTO(f.get().getProdotto()))
						.setDataFeedback(f.get().getDataFeedback())
						.setVoto(f.get().getVoto())
						.setDescrizione(f.get().getDescrizione())
						.build();
	}

}
