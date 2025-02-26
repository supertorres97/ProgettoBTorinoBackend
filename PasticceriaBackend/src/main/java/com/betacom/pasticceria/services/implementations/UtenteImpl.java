package com.betacom.pasticceria.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.pasticceria.dto.UtenteDTO;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.repositories.UtenteRepository;
import com.betacom.pasticceria.request.CredenzialiReq;
import com.betacom.pasticceria.request.UtenteReq;
import com.betacom.pasticceria.services.interfaces.CarrelloService;
import com.betacom.pasticceria.services.interfaces.CredenzialiService;
import com.betacom.pasticceria.services.interfaces.MessaggioService;
import com.betacom.pasticceria.services.interfaces.UtenteService;

@Service
public class UtenteImpl implements UtenteService {
	private UtenteRepository utenteR;
	private CredenzialiService credS;
	private CarrelloService cartS;
	private MessaggioService msgS;
	private Logger log;

	public UtenteImpl(UtenteRepository utenteR, CredenzialiService credS, CarrelloService cartS, MessaggioService msgS,
			Logger log) {
		this.utenteR = utenteR;
		this.credS = credS;
		this.cartS = cartS;
		this.msgS = msgS;
		this.log = log;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void create(UtenteReq req, CredenzialiReq cReq) throws Exception {
		Optional<Utente> utn = utenteR.findByEmail(req.getEmail());

		if (utn.isPresent())
			throw new Exception(msgS.getMessaggio("EMAIL_GIA_ESISTENTE"));

		Utente u = new Utente();
		u.setNome(req.getNome());
		u.setCognome(req.getCognome());
		u.setEmail(req.getEmail());
		if (req.getcFiscale() != null)
			u.setcFiscale(req.getcFiscale());
		u.setCAP(req.getCap());
		u.setVia(req.getVia());
		u.setCitta(req.getCitta());

		u = utenteR.save(u);
		cReq.setIdUtente(u.getId());

		credS.create(cReq);
		cartS.create(u);
	}

	@Override
	public void update(UtenteReq req) throws Exception {
		Optional<Utente> utn = utenteR.findById(req.getId());

		if (utn.isEmpty())
			throw new Exception(msgS.getMessaggio("UTENTE_INESISTENTE"));

		Utente u = utn.get();

		if (req.getCap() != null)
			u.setCAP(req.getCap());

		if (req.getCitta() != null)
			u.setCitta(req.getCitta());

		if (req.getCognome() != null)
			u.setCognome(req.getCognome());

		if (req.getNome() != null)
			u.setNome(req.getNome());

		if (req.getEmail() != null) {
			utn = utenteR.findByEmail(req.getEmail());
			if (utn.isEmpty())
				u.setEmail(req.getEmail());
			else
				throw new Exception(msgS.getMessaggio("EMAIL_GIA_ESISTENTE"));
		}
		
		if (req.getcFiscale() != null) {
			if (u.getcFiscale().isBlank() || u.getcFiscale().isEmpty())
				u.setcFiscale(req.getcFiscale());
		}

		if (req.getVia() != null)
			u.setVia(req.getVia());

		utenteR.save(u);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void createAdmin(UtenteReq req, CredenzialiReq cReq) throws Exception {
		Optional<Utente> utn = utenteR.findByEmail(req.getEmail());

		if (utn.isPresent())
			throw new Exception(msgS.getMessaggio("EMAIL_GIA_ESISTENTE"));

		Utente u = new Utente();
		u.setNome(req.getNome());
		u.setCognome(req.getCognome());
		u.setEmail(req.getEmail());

		if (req.getcFiscale() != null)
			u.setcFiscale(req.getcFiscale());

		u.setCAP(req.getCap());
		u.setVia(req.getVia());
		u.setCitta(req.getCitta());

		u = utenteR.save(u);
		log.debug("Utente: " + u);
		cReq.setIdUtente(u.getId());

		credS.createAdmin(cReq);
		log.debug("Credenziali: " + cReq);
		cartS.create(u);
	}

	@Override
	public List<UtenteDTO> listAll() {
		List<Utente> lU = utenteR.findAll();

		return lU.stream()
				.map(u -> new UtenteDTO.Builder().setCap(u.getCAP()).setcFiscale(u.getcFiscale()).setCitta(u.getCitta())
						.setCognome(u.getCognome()).setNome(u.getNome()).setEmail(u.getEmail()).setId(u.getId())
						.setVia(u.getVia()).build())
				.collect(Collectors.toList());
	}

	@Override
	public UtenteDTO listById(Integer id) throws Exception {
		Optional<Utente> u = utenteR.findById(id);

		if (u.isEmpty())
			throw new Exception(msgS.getMessaggio("UTENTE_INESISTENTE"));

		return new UtenteDTO.Builder().setCap(u.get().getCAP()).setcFiscale(u.get().getcFiscale())
				.setCitta(u.get().getCitta()).setCognome(u.get().getCognome()).setNome(u.get().getNome())
				.setEmail(u.get().getEmail()).setId(u.get().getId()).setVia(u.get().getVia()).build();
	}

}
