package com.betacom.pasticceria.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.pasticceria.dto.UtenteDTO;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.repositories.UtenteRepository;
import com.betacom.pasticceria.request.UtenteReq;
import com.betacom.pasticceria.services.interfaces.UtenteService;

@Service
public class UtenteImpl implements UtenteService{

	private UtenteRepository utenteR;
//	private CredenzialiService credS;
//	private CarrelloService cartS;
	private Logger log;
	
	@Autowired
	public UtenteImpl(UtenteRepository utenteR,/* CredenzialiService credS, CarrelloService cartS,*/ Logger log) {
		this.utenteR = utenteR;
//		this.credS = credS;
//		this.cartS = cartS;
		this.log = log;
	}
	
//	@Override
//	public void create(UtenteReq req, CredenzialiReq cReq) throws Exception {
//		Optional<Utente> utn = utenteR.findByEmail(req.getEmail());
//		
//		if(utn.isPresent())
//			throw new Exception("email gia esistente");
//		
//		Utente u = new Utente();
//		u.setNome(req.getNome());
//		u.setCognome(req.getCognome());
//		u.setEmail(req.getEmail());
//		if(req.getcFiscale() != null)
//			u.setcFiscale(req.getcFiscale());
//		u.setCAP(req.getCAP());
//		u.setVia(req.getVia());
//		u.setCitta(req.getCitta());
//		u,setAttivo(true);
//		
//		u = utenteR.save(u);
//		
//		cReq.setUtenteId(u);
//		
//		credS.create(cReq);
//		
//		cartS.create(u);
//	}

	@Override
	public void update(UtenteReq req) throws Exception {
		Optional<Utente> utn = utenteR.findById(req.getId());
		
		if(utn.isEmpty())
			throw new Exception("Utente inesistente");
		
		Utente u = utn.get();
		
		if(req.getCAP() != null)
			u.setCAP(req.getCAP());
		if(req.getCitta() != null)
			u.setCitta(req.getCitta());
		if(req.getCognome() != null)
			u.setCognome(req.getCognome());
		if(req.getNome() != null)
			u.setNome(req.getNome());
		if(req.getEmail() != null) {
			utn = utenteR.findByEmail(req.getEmail());
			if(utn.isPresent())
				throw new Exception("email gia presente");
			u.setEmail(req.getEmail());
		}
		if(req.getVia() != null)
			u.setVia(req.getVia());
		
		utenteR.save(u);
	}

//	@Override
//	public void delete(UtenteReq req, CredenzialiReq cReq) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public List<UtenteDTO> listAll() {
		List<Utente> lU = utenteR.findAll();
		
		return lU.stream()
				.map(u -> new UtenteDTO.Builder()
					.setAttivo(u.getAttivo())
					.setCAP(u.getCAP())
					.setcFiscale(u.getcFiscale())
					.setCitta(u.getCitta())
					.setCognome(u.getCognome())
					.setNome(u.getNome())
					.setEmail(u.getEmail())
					.setId(u.getId())
					.setVia(u.getVia())
					.build())
				.collect(Collectors.toList());		
	}

	@Override
	public UtenteDTO findById(Integer id) throws Exception {
		Optional<Utente> u = utenteR.findById(id);
		
		if(u.isEmpty())
			throw new Exception("utente inesistente");
		
		return new UtenteDTO.Builder()
				.setAttivo(u.get().getAttivo())
				.setCAP(u.get().getCAP())
				.setcFiscale(u.get().getcFiscale())
				.setCitta(u.get().getCitta())
				.setCognome(u.get().getCognome())
				.setNome(u.get().getNome())
				.setEmail(u.get().getEmail())
				.setId(u.get().getId())
				.setVia(u.get().getVia())
				.build();
	}

	
}
