package com.betacom.pasticceria.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.pasticceria.dto.RuoliDTO;
import com.betacom.pasticceria.model.Ruoli;
import com.betacom.pasticceria.repositories.RuoliRepository;
import com.betacom.pasticceria.request.RuoliReq;
import com.betacom.pasticceria.services.interfaces.MessaggioService;
import com.betacom.pasticceria.services.interfaces.RuoliService;

@Service
public class RuoliImpl implements RuoliService {

	@Autowired
	private RuoliRepository ruoliR;
	private MessaggioService msgS;
	private Logger log;

	public RuoliImpl(RuoliRepository ruoliR, Logger log, MessaggioService msgS) {
		super();
		this.ruoliR = ruoliR;
		this.msgS = msgS;
		this.log = log;
	}

	@Override
	public void create(RuoliReq req) throws Exception {
		log.debug("Create ruoli: " + req);

		Ruoli r = new Ruoli();
		r.setDescrizione(req.getDescrizione());

		ruoliR.save(r);
		msgS.getMessaggio("NEW_RUOLO");
	}

	@Override
	public void update(RuoliReq req) throws Exception {
		log.debug("Update ruoli: " + req);
		Optional<Ruoli> rr = ruoliR.findById(req.getId());

		if (rr.isPresent()) {
			Ruoli r = rr.get();
			if (req.getDescrizione() != null)
				r.setDescrizione(req.getDescrizione());
			ruoliR.save(r);
			msgS.getMessaggio("RUOLO_AGGIORNATO");
		} else {
			throw new Exception(msgS.getMessaggio("RUOLO_NOT_FOUND"));
		}
	}

	@Override
	public void delete(RuoliReq req) throws Exception {
		log.debug("Delete ruoli: " + req);
		Optional<Ruoli> rr = ruoliR.findById(req.getId());
		if (rr.isEmpty())
			throw new Exception(msgS.getMessaggio("RUOLO_NOT_FOUND"));

		Ruoli r = rr.get();
		ruoliR.delete(r);

		msgS.getMessaggio("RUOLO_ELIMINATO");
	}

	@Override
	public List<RuoliDTO> listAll() {
		List<Ruoli> lr = ruoliR.findAll();
		return lr.stream().map(r -> new RuoliDTO.Builder().setId(r.getId()).setDescrizione(r.getDescrizione()).build())
				.collect(Collectors.toList());
	}

	@Override
	public RuoliDTO listByID(Integer id) throws Exception {
		Optional<Ruoli> rr = ruoliR.findById(id);
		if (rr.isEmpty())
			throw new Exception(msgS.getMessaggio("RUOLO_NOT_FOUND"));

		return new RuoliDTO.Builder().setId(rr.get().getId()).setDescrizione(rr.get().getDescrizione()).build();
	}

	@Override
	public RuoliDTO listByDescrizione(String desc) throws Exception {
		Optional<Ruoli> rr = ruoliR.findByDescrizione(desc);
		if (rr.isEmpty())
			throw new Exception(msgS.getMessaggio("RUOLO_NOT_FOUND"));

		return new RuoliDTO.Builder().setId(rr.get().getId()).setDescrizione(rr.get().getDescrizione()).build();
	}
}