package com.betacom.pasticceria.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.pasticceria.dto.CarrelloDTO;
import com.betacom.pasticceria.model.Carrello;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.repositories.CarrelloRepository;
import com.betacom.pasticceria.repositories.UtenteRepository;
import com.betacom.pasticceria.request.CarrelloReq;
import com.betacom.pasticceria.services.interfaces.CarrelloService;
import static com.betacom.pasticceria.utils.Utilities.buildUtenteDTO;

@Service
public class CarrelloImpl implements CarrelloService{

	private CarrelloRepository cartR;
	private UtenteRepository utnR;
	private Logger log;
	
	@Autowired
	public CarrelloImpl (CarrelloRepository cartR, UtenteRepository utnR, Logger log) {
		this.cartR = cartR;
		this.utnR = utnR;
		this.log = log;
	}
	
//	@Override
//	public void create(CarrelloReq req) throws Exception {
//		Optional<Utente> utn = utnR.findById(req.getUtente());
//		if(utn.isEmpty())
//			throw new Exception("Utente inesistente");
//		Optional<Carrello> cart = cartR.findByUtente(utn.get());
//		if(cart.isPresent())
//			throw new Exception("Utente gia associato a un carrello");
//		
//		Carrello c = new Carrello();
//		c.setUtente(utn.get());
//		
//		cartR.save(c);
//	}

	@Override
	public List<CarrelloDTO> listAll() throws Exception {
		List<Carrello> lC = cartR.findAll();
		
		return lC.stream()
				.map(c -> new CarrelloDTO.Builder()
						.setId(c.getId())
						.setUtente(buildUtenteDTO(c.getUtente()))
						.build())
				.collect(Collectors.toList());
						
	}

	@Override
	public void create(Utente utente) throws Exception {
		Optional<Utente> utn = utnR.findById(utente.getId());
		if(utn.isEmpty())
			throw new Exception("Utente inesistente");
		Optional<Carrello> cart = cartR.findByUtente(utn.get());
		if(cart.isPresent())
			throw new Exception("Utente gia associato a un carrello");
		
		Carrello c = new Carrello();
		c.setUtente(utente);
		
		cartR.save(c);
	}
}
