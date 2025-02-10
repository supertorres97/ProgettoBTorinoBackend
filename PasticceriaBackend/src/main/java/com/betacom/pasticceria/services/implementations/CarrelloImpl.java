package com.betacom.pasticceria.services.implementations;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.pasticceria.model.Carrello;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.repositories.CarrelloRepository;
import com.betacom.pasticceria.repositories.UtenteRepository;
import com.betacom.pasticceria.services.interfaces.CarrelloService;

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
