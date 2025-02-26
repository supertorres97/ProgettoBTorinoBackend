package com.betacom.pasticceria.services.implementations;

import static com.betacom.pasticceria.utils.Utilities.buildUtenteDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.pasticceria.dto.CarrelloDTO;
import com.betacom.pasticceria.model.Carrello;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.repositories.CarrelloRepository;
import com.betacom.pasticceria.repositories.UtenteRepository;
import com.betacom.pasticceria.services.interfaces.CarrelloService;
import com.betacom.pasticceria.services.interfaces.MessaggioService;

@Service
public class CarrelloImpl implements CarrelloService {

	private CarrelloRepository cartR;
	private UtenteRepository utnR;
	private MessaggioService msgS;

	public CarrelloImpl(CarrelloRepository cartR, UtenteRepository utnR, MessaggioService msgS) {
		this.cartR = cartR;
		this.utnR = utnR;
		this.msgS = msgS;
	}

	@Override
	public List<CarrelloDTO> listAll() throws Exception {
		List<Carrello> lC = cartR.findAll();

		return lC.stream()
				.map(c -> new CarrelloDTO.Builder().setId(c.getId()).setUtente(buildUtenteDTO(c.getUtente())).build())
				.collect(Collectors.toList());

	}

	@Override
	public void create(Utente utente) throws Exception {
		Optional<Utente> utn = utnR.findById(utente.getId());
		if (utn.isEmpty())
			throw new Exception(msgS.getMessaggio("UTENTE_INESISTENTE"));
		Optional<Carrello> cart = cartR.findByUtente(utn.get());
		if (cart.isPresent())
			throw new Exception(msgS.getMessaggio("UTENTE_CARRELLO"));

		Carrello c = new Carrello();
		c.setUtente(utente);

		cartR.save(c);
	}

	@Override
	public CarrelloDTO listByUtente(Integer idUtente) throws Exception {
		Optional<Carrello> cart = cartR.findByUtente_Id(idUtente);
		if (cart.isEmpty())
			throw new Exception(msgS.getMessaggio("CARRELLO_INESISTENTE"));

		return new CarrelloDTO.Builder().setId(cart.get().getId()).setUtente(buildUtenteDTO(cart.get().getUtente()))
				.build();

	}
}
