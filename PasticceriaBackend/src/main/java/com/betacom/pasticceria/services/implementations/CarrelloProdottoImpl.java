package com.betacom.pasticceria.services.implementations;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.pasticceria.model.Carrello;
import com.betacom.pasticceria.model.CarrelloProdotto;
import com.betacom.pasticceria.model.Ordine;
import com.betacom.pasticceria.model.Prodotto;
import com.betacom.pasticceria.repositories.CarrelloProdottoRepository;
import com.betacom.pasticceria.repositories.CarrelloRepository;
import com.betacom.pasticceria.repositories.OrdineRepository;
import com.betacom.pasticceria.repositories.ProdottoRepository;
import com.betacom.pasticceria.request.CarrelloProdottoReq;
import com.betacom.pasticceria.request.CarrelloReq;
import com.betacom.pasticceria.request.OrdineReq;
import com.betacom.pasticceria.services.interfaces.CarrelloProdottoService;
import com.betacom.pasticceria.services.interfaces.OrdineService;

@Service
public class CarrelloProdottoImpl implements CarrelloProdottoService{
	
	private CarrelloProdottoRepository cpR;
	private ProdottoRepository prodR;
	private OrdineService orderS;
	private CarrelloRepository cartR;
	private Logger log;
	
	@Autowired
	public CarrelloProdottoImpl(CarrelloProdottoRepository cpR, ProdottoRepository prodR, OrdineService orderS, CarrelloRepository cartR, Logger log) {
		this.cpR = cpR;
		this.prodR = prodR;
		this.orderS = orderS;
		this.cartR = cartR;
		this.log = log;
	}
	
	@Override
	public void create(CarrelloProdottoReq req) throws Exception {
		//fai un find by prodotto e se gia esiste si aumenta la sua quantita
		log.debug("Create carrelloProdotto..");
		Optional<Prodotto> prod = prodR.findById(req.getProdotto());
		if(prod.isEmpty())
			throw new Exception("Prodotto inesistente");
		
		Optional<Carrello> cart = cartR.findById(req.getCarrello());
		if(cart.isEmpty())
			throw new Exception("Carrello inesistente");
		
		CarrelloProdotto cp = new CarrelloProdotto();
		Optional<CarrelloProdotto> cprod = cpR.findByProdottoAndCarrello(prod.get(), cart.get());
		if(cprod.isPresent()) {
			cp = cprod.get();
			cp.setQuantita(cp.getQuantita() + 1);
			cp.setPrezzoTotale(cp.getPrezzoTotale() + prod.get().getPrezzo());
		}else {
			cp.setCarrello(cart.get());
			cp.setProdotto(prod.get());
			cp.setQuantita(1);
			cp.setPrezzoTotale(prod.get().getPrezzo());
		}
		cpR.save(cp);
	}
	
	
	//RIMUOVE IL PRODOTTO DAL CARRELLO (CARRELLOPRODOTTO) INDIPENDENTEMENTE DALLA QUANTITA
	@Override
	public void remove(CarrelloProdottoReq req) throws Exception {
//		Optional<Carrello> cart = cartR.findById(req.getCarrello());
//		if(cart.isEmpty())
//			throw new Exception("Carrello inesistente");
//		
//		Optional<Prodotto> prod = prodR.findById(req.getProdotto());
//		if(prod.isEmpty())
//			throw new Exception("Ordine inesistente");
//		
//		Optional<CarrelloProdotto> cartP = crR.findByProdottoAndCarrello(prod.get(), cart.get());
//		if(cartP.isEmpty())
//			throw new Exception("Prodotto non presente nel carrello");
		
		Optional<CarrelloProdotto> cartP = cpR.findById(req.getId());
		if(cartP.isEmpty())
			throw new Exception("CarrelloProdotto inesistente");
		
		cpR.delete(cartP.get());
	}

	//AGGIORNA QUANTITA (SE = 0 RMUOVE PRODOTTO)
	@Override
	public void update(CarrelloProdottoReq req) throws Exception {
		Optional<Prodotto> prod = prodR.findById(req.getProdotto());
		if(prod.isEmpty())
			throw new Exception("Prodotto inesistente");
		
		Optional<CarrelloProdotto> cartP = cpR.findById(req.getId());
		if(cartP.isEmpty())
			throw new Exception("CarrelloProdotto inesistente");
		
		CarrelloProdotto cp = cartP.get();
		cp.setQuantita(req.getQuantita());
		if(cp.getQuantita() <= 0)
			cpR.delete(cp);
		else {
			cp.setPrezzoTotale(prod.get().getPrezzo() * req.getQuantita());
			cpR.save(cp);
		}
	}

	@Override
	public void acuqista(Carrello cart) throws Exception {
		Optional<Carrello> carr = cartR.findById(cart.getId());
		if(carr.isEmpty())
			throw new Exception("Carrello insesistente");
		
		List<CarrelloProdotto> lCP = cpR.findAllByCarrello(cart);
		if(lCP.isEmpty())
			throw new Exception("Carrello vuoto");
		
		OrdineReq oReq = new OrdineReq();
		oReq.setUtente(cart.getUtente().getId());
		orderS.create(oReq);
		
		
		
	}
	
	
	
	
}
