package com.betacom.pasticceria.services.implementations;

import java.util.Optional;
import com.betacom.pasticceria.model.Messaggio;
import com.betacom.pasticceria.repositories.MessaggioRepository;
import com.betacom.pasticceria.services.interfaces.MessaggioService;

public class MessaggioImpl implements MessaggioService {

	private MessaggioRepository messR;

	public MessaggioImpl(MessaggioRepository messR) {

		this.messR = messR;

	}

	@Override
	public String getMessaggio(String codice) {
		Optional<Messaggio> msg = messR.findByCodice(codice);
		String res = null;
		if (msg.isEmpty()) {
			res = codice;
		} else {
			res = msg.get().getMessaggio();
		}
		return res;
	}

}
