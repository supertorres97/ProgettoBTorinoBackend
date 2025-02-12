package com.betacom.pasticceria.services.implementations;

import static com.betacom.pasticceria.utils.Utilities.buildCarrelloDTO;
import static com.betacom.pasticceria.utils.Utilities.buildProdottoDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.pasticceria.dto.CarrelloProdottoDTO;
import com.betacom.pasticceria.model.Carrello;
import com.betacom.pasticceria.model.CarrelloProdotto;
import com.betacom.pasticceria.model.Ordine;
import com.betacom.pasticceria.model.Prodotto;
import com.betacom.pasticceria.repositories.CarrelloProdottoRepository;
import com.betacom.pasticceria.repositories.CarrelloRepository;
import com.betacom.pasticceria.repositories.ProdottoRepository;
import com.betacom.pasticceria.request.CarrelloProdottoReq;
import com.betacom.pasticceria.request.DettagliOrdineReq;
import com.betacom.pasticceria.request.OrdineReq;
import com.betacom.pasticceria.services.interfaces.CarrelloProdottoService;
import com.betacom.pasticceria.services.interfaces.DettagliOrdineService;
import com.betacom.pasticceria.services.interfaces.OrdineService;

@Service
public class CarrelloProdottoImpl implements CarrelloProdottoService{
	
	private CarrelloProdottoRepository cpR;
	private ProdottoRepository prodR;
	private OrdineService orderS;
	private CarrelloRepository cartR;
	private DettagliOrdineService doS;
	private Logger log;
	
	@Autowired
	public CarrelloProdottoImpl(CarrelloProdottoRepository cpR, ProdottoRepository prodR, OrdineService orderS, CarrelloRepository cartR, DettagliOrdineService doS, Logger log) {
		this.cpR = cpR;
		this.prodR = prodR;
		this.orderS = orderS;
		this.cartR = cartR;
		this.doS = doS;
		this.log = log;
	}
	
	@Override
	public void create(CarrelloProdottoReq req) throws Exception {
		//fai un find by prodotto e se gia esiste si aumenta la sua quantita
		log.debug("Create carrelloProdotto..");
		
		Optional<Prodotto> prod = prodR.findById(req.getProdotto());
		if(prod.isEmpty())
			throw new Exception("Prodotto inesistente");
		if(!prod.get().disponibile) {
			log.error("disponibilita prodotto: " + prod.get().getDisponibile());
			throw new Exception("Prodotto non disponibile");
		}
		
		Optional<Carrello> cart = cartR.findById(req.getCarrello());
		if(cart.isEmpty())
			throw new Exception("Carrello inesistente");
		
		CarrelloProdotto cp = new CarrelloProdotto();
		Optional<CarrelloProdotto> cprod = cpR.findByProdottoAndCarrello(prod.get(), cart.get());
		if(cprod.isPresent()) {
			cp = cprod.get();
			if(req.getQuantita() != null)
				cp = addProduct(prod.get(), cp, req.getQuantita());
			else
				cp = addProduct(prod.get(), cp, 1);
			cp.setPrezzoTotale(cp.getPrezzoTotale() + prod.get().getPrezzo());
		}else {
			log.debug("QUANTITA " + req.getQuantita());
			cp.setCarrello(cart.get());
			cp.setProdotto(prod.get());
			cp.setPrezzoTotale(prod.get().getPrezzo());
			if(req.getQuantita() != null)
				cp = addProduct(prod.get(), cp, req.getQuantita());
			else
			cp = addProduct(prod.get(), cp, 1);
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
//		cp.setQuantita(req.getQuantita());
		
		cp = addProduct(prod.get(), cp, req.getQuantita());
		
		if(cp.getQuantita() <= 0)
			cpR.delete(cp);
		else {
			cp.setPrezzoTotale(prod.get().getPrezzo() * req.getQuantita());
			cpR.save(cp);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void acuqista(CarrelloProdottoReq req) throws Exception {
		Optional<Carrello> cart = cartR.findById(req.getCarrello());
		if(cart.isEmpty())
			throw new Exception("Carrello insesistente");
		
		List<CarrelloProdotto> lCP = cpR.findAllByCarrello(cart.get());
		if(lCP.isEmpty())
			throw new Exception("Carrello vuoto");
		
		OrdineReq oReq = new OrdineReq();
		oReq.setUtente(cart.get().getUtente().getId());
		Ordine o = orderS.create(oReq);
		
		DettagliOrdineReq doReq = new DettagliOrdineReq();
		Double totale = 0.0;
		
		for(CarrelloProdotto cp : lCP) {
			doReq.setOrdine(o.getId());
			doReq.setProdotto(cp.getProdotto().getId());
			doReq.setQuantitaFinale(cp.getQuantita());
			doReq.setPrezzoTotale(cp.getPrezzoTotale());
			totale = totale + cp.getPrezzoTotale();
			
			doS.create(doReq);
		}
		
		oReq.setTotale(totale);
		oReq.setId(o.getId());
		orderS.update(oReq);
		
		svuotaCarrello(cart.get().getId());
	}
	
	
	
	@Override
	public List<CarrelloProdottoDTO> listByCarrello(Integer idC) throws Exception {
		
		Optional<Carrello> cart = cartR.findById(idC);
		if(cart.isEmpty())
			throw new Exception("Carrello insesistente");
		
		List<CarrelloProdotto> lCP = cpR.findAllByCarrello(cart.get());
		
		return lCP.stream()
				.map(cp -> new CarrelloProdottoDTO.Builder()
						.setId(cp.getId())
						.setQuantita(cp.getQuantita())
						.setProdotto(buildProdottoDTO(cp.getProdotto()))
						.setCarrello(buildCarrelloDTO(cp.getCarrello()))
						.build())
				.collect(Collectors.toList());
	}
	
	@Override
	public List<CarrelloProdottoDTO> listByProdotto(Integer idProdotto) throws Exception {
		Optional<Prodotto> prod = prodR.findById(idProdotto);
		if(prod.isEmpty())
			throw new Exception("Prodotto insesistente");
		
		List<CarrelloProdotto> lP = cpR.findAllByProdotto(prod.get());
		
		return lP.stream()
				.map(cp -> new CarrelloProdottoDTO.Builder()
						.setId(cp.getId())
						.setQuantita(cp.getQuantita())
						.setProdotto(buildProdottoDTO(cp.getProdotto()))
						.setCarrello(buildCarrelloDTO(cp.getCarrello()))
						.build())
				.collect(Collectors.toList());
	}
	
	
	//METODO AUSILIARIO PRIVATO
	private CarrelloProdotto addProduct(Prodotto prodotto, CarrelloProdotto cartProd, Integer quantita) throws Exception {
		Optional<Prodotto> prod = prodR.findById(prodotto.getId());
		
		CarrelloProdotto cp = cartProd;
		
		if(prod.get().getStock() == 0) {
			prod.get().setDisponibile(false);
			log.error("Stock vuoto" + prod.get().getStock());
			throw new Exception("Stock esaurito");
		}
		
		if((prod.get().getStock() - quantita) < 0) {
			log.error("quantita inserita supera limiti stock quantita: " + quantita + " stock: " + prod.get().getStock());
			throw new Exception("quantita supera lo stock");
		}
		log.debug("cp quantita: " + cp.getQuantita());
		if(cp.getQuantita() != null)
			cp.setQuantita(cp.getQuantita() + quantita);
		else
			cp.setQuantita(quantita);
		
		log.debug("QUANTITA FINALE " + cp.getQuantita());
		prod.get().setStock(prod.get().getStock() - quantita);
		
		if(prod.get().getStock() == 0)
			prod.get().setDisponibile(false);
		
		return cp;
	}

	@Override
	public void svuotaCarrello(Integer cartId) throws Exception {
		Optional<Carrello> c = cartR.findById(cartId);
		if(c.isEmpty())
			throw new Exception("Carrello insesistente");
		
		List<CarrelloProdotto> lCP = cpR.findAllByCarrello(c.get());
		
		for(CarrelloProdotto cp : lCP) {
			cpR.delete(cp);
		}
	}
	
	
	
	
//	STAVA NELL'IF DI CREATE	(CPROD IS PRESENT)
//	if(cp.getQuantita() >= cp.getProdotto().getStock()) {
//		log.error("Superti lo stock (esaurito): " + cp.getProdotto().getStock());
//		throw new Exception("Stock esaurito");
//	}
//	
//	cp.setQuantita(cp.getQuantita() + 1);
}
