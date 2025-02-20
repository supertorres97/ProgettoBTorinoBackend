package com.betacom.pasticceria.services.implementations;

import static com.betacom.pasticceria.utils.Utilities.buildProdottoDTO;
import static com.betacom.pasticceria.utils.Utilities.buildUtenteDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.pasticceria.dto.FeedbackDTO;
import com.betacom.pasticceria.model.DettagliOrdine;
import com.betacom.pasticceria.model.Feedback;
import com.betacom.pasticceria.model.Ordine;
import com.betacom.pasticceria.model.Prodotto;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.model.Voto;
import com.betacom.pasticceria.repositories.DettagliOrdineRepository;
import com.betacom.pasticceria.repositories.FeedbackRepository;
import com.betacom.pasticceria.repositories.OrdineRepository;
import com.betacom.pasticceria.repositories.ProdottoRepository;
import com.betacom.pasticceria.repositories.UtenteRepository;
import com.betacom.pasticceria.request.FeedbackReq;
import com.betacom.pasticceria.services.interfaces.FeedbackService;
import com.betacom.pasticceria.services.interfaces.MessaggioService;
import com.betacom.pasticceria.utils.Utilities;

@Service
public class FeedbackImpl implements FeedbackService{
	
	FeedbackRepository feedR;
	OrdineRepository ordR;
	UtenteRepository utR;
	ProdottoRepository prodR;
	DettagliOrdineRepository detR;
	private MessaggioService msgS;
	Logger log;
	
	@Autowired
	public FeedbackImpl(FeedbackRepository feedR, OrdineRepository ordR, UtenteRepository utR, 
			ProdottoRepository prodR, DettagliOrdineRepository detR, MessaggioService msgS, Logger log) {
		super();
		this.feedR = feedR;
		this.ordR = ordR;
		this.utR = utR;
		this.log = log;
		this.prodR = prodR;
		this.detR = detR;
		this.msgS = msgS;
		
	}

	@Override
	public void create(FeedbackReq req) throws Exception {
		log.debug("creazione feedback: "+ req);
		Boolean ordinato = recensibile(req.getUtente(), req.getProdotto());
		if(!ordinato) {
			log.error("Prodotto non acquistato!" + req.getProdotto() + ordinato);
			throw new Exception(msgS.getMessaggio("PRODOTTO_NON_ACQUISTATO_NO_RECENSIONE"));
		}
		
		Optional<Prodotto> prod = prodR.findById(req.getProdotto());
		if (prod.isEmpty()) { 
			log.error("Prodotto inesistente!");
			throw new Exception(msgS.getMessaggio("NO_RECENSIONE_PRODOTTO_INESISTENTE"));
		}
		
		Optional<Utente> ut = utR.findById(req.getUtente());
		if(ut.isEmpty()) {
			log.error("Utente inesistente");
			throw new Exception(msgS.getMessaggio("UTENTE_INESISTENTE"));
		}
		
		Feedback f = new Feedback();
		f.setDescrizione(req.getDescrizione());
		f.setProdotto(prod.get());
		f.setUtente(ut.get());
		
		Voto voto =  Voto.valueOf(req.getVoto().toUpperCase());
		f.setVoto(voto);
		f.setDataFeedback(Utilities.convertStringToDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
		feedR.save(f);
	}
	
	public boolean checkOrderedProduct(FeedbackReq req) {
	    List<Ordine> ordiniUtente = ordR.findByUtente_Id(req.getUtente());

	    // Se l'utente non ha ordini, restituisce false
	    if (ordiniUtente.isEmpty()) {
	        return false;
	    }
	    //effettua il controllo se il prodotto si trova in almeno un ordine dell'utente
	    return detR.existsByOrdineInAndProdotto_Id(ordiniUtente, req.getProdotto());
	}


	@Override
	public void update(FeedbackReq req) throws Exception {
		Optional<Feedback> feed = feedR.findById(req.getId());
		if (feed.isEmpty()) 
			throw new Exception(msgS.getMessaggio("FEEDBACK_NOT_FOUND"));
		
		Optional<Prodotto> prod = prodR.findById(req.getProdotto());
		if (prod.isEmpty()) 
			throw new Exception(msgS.getMessaggio("NO_RECENSIONE_PRODOTTO_INESISTENTE"));
		
		Optional<Utente> ut = utR.findById(req.getUtente());
		if(ut.isEmpty())
			throw new Exception(msgS.getMessaggio("UTENTE_INESISTENTE"));
		
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
			f.setDataFeedback(Utilities.convertStringToDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
			feedR.save(f);
		}
		
	}

	@Override
	public void delete(FeedbackReq req) throws Exception {
		Optional<Feedback> feed = feedR.findById(req.getId());
		if(feed.isEmpty())
			throw new Exception(msgS.getMessaggio("FEEDBACK_NOT_FOUND"));
		
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
						.setProdotto(buildProdottoDTO(f.get().getProdotto()))
						.setDataFeedback(f.get().getDataFeedback())
						.setVoto(f.get().getVoto())
						.setDescrizione(f.get().getDescrizione())
						.build();
	}

	@Override
	public List<FeedbackDTO> listByProdottoID(Integer id) throws Exception {
		Optional<Prodotto> oP = prodR.findById(id);
		if(oP.isEmpty())
			throw new Exception("Prodotto non presente! -feedback");
		
		List<Feedback> lF = feedR.findByProdotto_id(oP.get().getId());
		return lF.stream()
				.map(f -> new FeedbackDTO.Builder()
						.setId(f.getId())
						.setUtente(buildUtenteDTO(f.getUtente()))
						.setProdotto(buildProdottoDTO(f.getProdotto()))
						.setDataFeedback(f.getDataFeedback())
						.setVoto(f.getVoto())
						.setDescrizione(f.getDescrizione())
						.build())
				.collect(Collectors.toList());
	}
	
	private Boolean recensibile(Integer id, Integer idProd) throws Exception {
		
		Optional<Utente> utO = utR.findById(id);
		if(utO.isEmpty())
			throw new Exception("Utente non trovato");
		
		Optional<Prodotto> prO = prodR.findById(idProd);
		if(prO.isEmpty())
			throw new Exception("Prodotto non trovato");
		
		
		List<Ordine> orL = ordR.findByUtente_Id(utO.get().getId());
		if(orL.isEmpty())
			throw new Exception("Utente senza ordini");
		
		log.debug("Num ordini:" + orL.size());
		
		for(Ordine o : orL) {
			log.debug("o: " + o);
			if(o.getStatus().toString().equalsIgnoreCase("Consegnato")) {
				List<DettagliOrdine> dlL = detR.findByOrdine_Id(o.getId());
				log.debug("IF RAGGIUNTO!");
				for(DettagliOrdine d : dlL) {
					log.debug("prodotto dettagli: " + d.getProdotto().getId());
					
					if(d.getProdotto().getId() == prO.get().getId()) {
						return true;
					}
				}
			}
		}
		
		return false;
	}

}
