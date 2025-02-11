package com.betacom.pasticceria.services.implementations;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.betacom.pasticceria.model.Messaggio;
import com.betacom.pasticceria.repositories.MessaggioRepository;
import com.betacom.pasticceria.services.interfaces.MessaggioService;

@Service
public class MessaggioImpl implements MessaggioService {
	private MessaggioRepository messR;
	private Logger log;

	public MessaggioImpl(MessaggioRepository messR, Logger log) {
		this.messR = messR;
		this.log = log;
	}

	@Override
	public String getMessaggio(String codice) {
		Optional<Messaggio> msg = messR.findByCodice(codice);
		log.debug("codice: " +  codice + " messaggio: " + msg.get().getMessaggio());
		String res = null;
		if (msg.isEmpty()) {
			res = codice;
		} else {
			res = msg.get().getMessaggio();
		}
		return res;
	}

}
