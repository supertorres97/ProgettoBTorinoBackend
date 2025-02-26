package com.betacom.pasticceria.services.implementations;

import static com.betacom.pasticceria.utils.Utilities.buildCarrelloDTO;
import static com.betacom.pasticceria.utils.Utilities.buildProdottoDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.pasticceria.dto.CarrelloProdottoDTO;
import com.betacom.pasticceria.model.Carrello;
import com.betacom.pasticceria.model.CarrelloProdotto;
import com.betacom.pasticceria.model.Ordine;
import com.betacom.pasticceria.model.Prodotto;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.repositories.CarrelloProdottoRepository;
import com.betacom.pasticceria.repositories.CarrelloRepository;
import com.betacom.pasticceria.repositories.ProdottoRepository;
import com.betacom.pasticceria.repositories.UtenteRepository;
import com.betacom.pasticceria.request.CarrelloProdottoReq;
import com.betacom.pasticceria.request.CarrelloReq;
import com.betacom.pasticceria.request.DettagliOrdineReq;
import com.betacom.pasticceria.request.OrdineReq;
import com.betacom.pasticceria.services.interfaces.CarrelloProdottoService;
import com.betacom.pasticceria.services.interfaces.DettagliOrdineService;
import com.betacom.pasticceria.services.interfaces.MessaggioService;
import com.betacom.pasticceria.services.interfaces.OrdineService;

@Service
public class CarrelloProdottoImpl implements CarrelloProdottoService {

	private CarrelloProdottoRepository cpR;
	private ProdottoRepository prodR;
	private OrdineService orderS;
	private CarrelloRepository cartR;
	private DettagliOrdineService doS;
	private UtenteRepository utnR;
	private MessaggioService msgS;
	private Logger log;

	public CarrelloProdottoImpl(CarrelloProdottoRepository cpR, ProdottoRepository prodR, OrdineService orderS,
			CarrelloRepository cartR, DettagliOrdineService doS, UtenteRepository utnR, MessaggioService msgS,
			Logger log) {
		this.cpR = cpR;
		this.prodR = prodR;
		this.orderS = orderS;
		this.cartR = cartR;
		this.doS = doS;
		this.utnR = utnR;
		this.msgS = msgS;
		this.log = log;
	}

	@Override
	public void create(CarrelloProdottoReq req) throws Exception {
		Optional<Prodotto> prod = prodR.findById(req.getProdotto());
		if (prod.isEmpty())
			throw new Exception(msgS.getMessaggio("PRODOTTO_INESISTENTE"));
		log.debug("prodotto: " + prod.get().getId() + " disponibile: " + prod.get().getDisponibile());
		if (!prod.get().getDisponibile()) {
			log.error("disponibilita prodotto: " + prod.get().getDisponibile());
			throw new Exception(msgS.getMessaggio("PRODOTTO_NON_DISPONIBILE"));
		}

		Optional<Carrello> cart = cartR.findById(req.getCarrello());
		if (cart.isEmpty())
			throw new Exception(msgS.getMessaggio("CARRELLO_INESISTENTE"));

		CarrelloProdotto cp = new CarrelloProdotto();
		Optional<CarrelloProdotto> cprod = cpR.findByProdottoAndCarrello(prod.get(), cart.get());
		if (cprod.isPresent()) {
			cp = cprod.get();
			if (req.getQuantita() != null)
				cp = addProduct(prod.get(), cp, req.getQuantita());
			else
				cp = addProduct(prod.get(), cp, 1);
			cp.setPrezzoTotale(cp.getPrezzoTotale() + prod.get().getPrezzo());
		} else {
			log.debug("QUANTITA " + req.getQuantita());
			cp.setCarrello(cart.get());
			cp.setProdotto(prod.get());
			cp.setPrezzoTotale(prod.get().getPrezzo());
			if (req.getQuantita() != null)
				cp = addProduct(prod.get(), cp, req.getQuantita());
			else
				cp = addProduct(prod.get(), cp, 1);
		}
		cpR.save(cp);
	}

	// RIMUOVE IL PRODOTTO DAL CARRELLO (CARRELLOPRODOTTO) INDIPENDENTEMENTE DALLA
	// QUANTITA
	@Override
	public void remove(Integer idCarrelloProdotto) throws Exception {
		Optional<CarrelloProdotto> cartP = cpR.findById(idCarrelloProdotto);
		if (cartP.isEmpty())
			throw new Exception(msgS.getMessaggio("CARRELLOPRODOTTO_INESISTENTE"));
		CarrelloProdotto c = cartP.get();
		c = addProduct(c.getProdotto(), c, c.getQuantita());
		cpR.delete(cartP.get());
	}

	// AGGIORNA QUANTITA (SE = 0 RMUOVE PRODOTTO)
	@Override
	public void update(CarrelloProdottoReq req) throws Exception {
		Optional<Prodotto> prod = prodR.findById(req.getProdotto());
		if (prod.isEmpty())
			throw new Exception(msgS.getMessaggio("PRODOTTO_INESISTENTE"));

		Optional<CarrelloProdotto> cartP = cpR.findById(req.getId());
		if (cartP.isEmpty())
			throw new Exception(msgS.getMessaggio("CARRELLOPRODOTTO_INESISTENTE"));

		CarrelloProdotto cp = cartP.get();

		cp = addProduct(prod.get(), cp, req.getQuantita());

		if (cp.getQuantita() <= 0)
			cpR.delete(cp);
		else {
			cp.setPrezzoTotale(prod.get().getPrezzo() * req.getQuantita());
			cpR.save(cp);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void acuqista(CarrelloReq req) throws Exception {
		Optional<Carrello> cart = cartR.findById(req.getId());
		if (cart.isEmpty())
			throw new Exception(msgS.getMessaggio("CARRELLO_INESISTENTE"));

		Optional<Utente> utn = utnR.findById(cart.get().getUtente().getId());
		if (utn.isEmpty())
			throw new Exception(msgS.getMessaggio("UTENTE_INESISTENTE"));

		List<CarrelloProdotto> lCP = cpR.findAllByCarrello(cart.get());
		if (lCP.isEmpty())
			throw new Exception(msgS.getMessaggio("CARRELLO_VUOTO"));

		OrdineReq oReq = new OrdineReq();
		oReq.setUtente(utn.get().getId());
		oReq.setTotale(0.0);
		log.debug("utente: " + utn.get());
		Ordine o = orderS.create(oReq);

		DettagliOrdineReq doReq = new DettagliOrdineReq();
		Double totale = 0.0;

		for (CarrelloProdotto cp : lCP) {
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
		if (cart.isEmpty())
			throw new Exception(msgS.getMessaggio("CARRELLO_INESISTENTE"));

		List<CarrelloProdotto> lCP = cpR.findAllByCarrello(cart.get());

		return lCP.stream()
				.map(cp -> new CarrelloProdottoDTO.Builder().setId(cp.getId()).setQuantita(cp.getQuantita())
						.setProdotto(buildProdottoDTO(cp.getProdotto())).setCarrello(buildCarrelloDTO(cp.getCarrello()))
						.build())
				.collect(Collectors.toList());
	}

	@Override
	public List<CarrelloProdottoDTO> listByProdotto(Integer idProdotto) throws Exception {
		Optional<Prodotto> prod = prodR.findById(idProdotto);
		if (prod.isEmpty())
			throw new Exception(msgS.getMessaggio("PRODOTTO_INESISTENTE"));

		List<CarrelloProdotto> lP = cpR.findAllByProdotto(prod.get());

		return lP.stream()
				.map(cp -> new CarrelloProdottoDTO.Builder().setId(cp.getId()).setQuantita(cp.getQuantita())
						.setProdotto(buildProdottoDTO(cp.getProdotto())).setCarrello(buildCarrelloDTO(cp.getCarrello()))
						.build())
				.collect(Collectors.toList());
	}

	// METODO AUSILIARIO PRIVATO
	private CarrelloProdotto addProduct(Prodotto prodotto, CarrelloProdotto cartProd, Integer quantita)
			throws Exception {
		Optional<Prodotto> prod = prodR.findById(prodotto.getId());
		CarrelloProdotto cp = cartProd;

		if (prod.get().getStock() == 0) {
			prod.get().setDisponibile(false);
			log.error("Stock vuoto" + prod.get().getStock());
			throw new Exception(msgS.getMessaggio("STOCK_ESAURITO"));
		}

		if (cp.getQuantita() != null)
			quantita = quantita - cp.getQuantita();

		if ((prod.get().getStock() - quantita) < 0) {
			log.error(msgS.getMessaggio("QUANTITA>STOCK") + quantita + " stock: " + prod.get().getStock());
			throw new Exception(msgS.getMessaggio("QUANTITA>STOCK"));
		}
		log.debug("cp quantita: " + cp.getQuantita());
		if (cp.getQuantita() != null) {
			cp.setQuantita(cp.getQuantita() + quantita);
			cp.setPrezzoTotale(prod.get().getPrezzo() * cp.getQuantita());
		} else {
			cp.setQuantita(quantita);
			cp.setPrezzoTotale(prod.get().getPrezzo() * cp.getQuantita());
		}

		if (cp.getQuantita() < 0) {
			cp.setQuantita(0);
			cp.setPrezzoTotale(prod.get().getPrezzo() * cp.getQuantita());
		}

		log.debug("QUANTITA FINALE " + cp.getQuantita());
		prod.get().setStock(prod.get().getStock() - quantita);

		if (prod.get().getStock() == 0)
			prod.get().setDisponibile(false);

		return cp;
	}

	@Override
	public void svuotaCarrello(Integer cartId) throws Exception {
		Optional<Carrello> c = cartR.findById(cartId);
		if (c.isEmpty())
			throw new Exception(msgS.getMessaggio("CARRELLO_INESISTENTE"));

		List<CarrelloProdotto> lCP = cpR.findAllByCarrello(c.get());

		for (CarrelloProdotto cp : lCP) {
			cpR.delete(cp);
		}
	}
}
