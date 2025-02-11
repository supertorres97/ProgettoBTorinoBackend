package com.betacom.pasticceria.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.pasticceria.dto.ProdottoDTO;
import com.betacom.pasticceria.model.Prodotto;
import com.betacom.pasticceria.model.TipoProdotto;
import com.betacom.pasticceria.repositories.ProdottoRepository;
import com.betacom.pasticceria.repositories.TipoProdottoRepository;
import com.betacom.pasticceria.request.ProdottoReq;
import com.betacom.pasticceria.services.interfaces.ProdottoService;
import com.betacom.pasticceria.utils.Utilities;

@Service
public class ProdottoImpl implements ProdottoService{
	
	ProdottoRepository prodR;
	TipoProdottoRepository tPR;
	Logger log;
	
	@Autowired
	public ProdottoImpl(ProdottoRepository prodR, Logger log, TipoProdottoRepository tPR) {
		super();
		this.prodR = prodR;
		this.log = log;
		this.tPR = tPR;
	}

	@Override
	public void create(ProdottoReq req) throws Exception {
		log.debug("Create prodotto: " + req);

		Optional<Prodotto> pr = prodR.findByNome(req.getNome());
		if(pr.isPresent())
			throw new Exception("Prodotto già esistente");
		
		Optional<TipoProdotto> tP = tPR.findById(req.getTipo());
		if(tP.isEmpty())
			throw new Exception("Tipo di Prodotto non trovato");

		Prodotto p = new Prodotto();
		p.setTipo(tP.get());
		p.setNome(req.getNome());
		p.setDescrizione(req.getDescrizione());
		p.setPeso(req.getPeso());
		p.setPrezzo(req.getPrezzo());
		p.setStock(req.getStock());
		p.setDisponibile(req.getDisponibile());
		
		prodR.save(p);
		log.debug("Nuovo prodotto inserito");
		
	}

	@Override
	public void update(ProdottoReq req) throws Exception {
		log.debug("Update prodotto: " + req);
		Optional<Prodotto> pr = prodR.findById(req.getId());
		if(pr.isEmpty())
			throw new Exception("Prodotto non esistente");
		
		Prodotto p = pr.get();
		
		if(req.getTipo() != null) {
			Optional<TipoProdotto> tP = tPR.findById(req.getTipo());
			if(tP.isEmpty())
				throw new Exception("Tipo di Prodotto non trovato");	
			p.setTipo(tP.get());
		}
		
		if(req.getNome() != null)
			p.setNome(req.getNome());
		if(req.getDescrizione() != null)
			p.setDescrizione(req.getDescrizione());
		if(req.getPeso() != null)
			p.setPeso(req.getPeso());
		if(req.getPeso() != null)
			p.setPrezzo(req.getPrezzo());
		if(req.getStock() != null)
			p.setStock(req.getStock());
		if(req.getDisponibile() != null)
			p.setDisponibile(req.getDisponibile());
		
		prodR.save(p);
		log.debug("Prodotto Modificato");		
	}

	@Override
	public void delete(ProdottoReq req) throws Exception {
		log.debug("Delete prodotto: " + req);
		Optional<Prodotto> pr = prodR.findById(req.getId());
		if(pr.isEmpty())
			throw new Exception("Prodotto non esistente");
		
		Prodotto p = pr.get();		
		prodR.delete(p);
		
		log.debug("Prodotto Eliminato");	
	}

	@Override
	public List<ProdottoDTO> listAll() {
		List<Prodotto> lP = prodR.findAll();
		return lP.stream()
				.map(p -> new ProdottoDTO.Builder()
				.setId(p.getId())
				.setTipo(Utilities.buildTipoProdottoDTO(p.getTipo()))
				.setNome(p.getNome())
				.setDescrizione(p.getDescrizione())
				.setPeso(p.getPeso())
				.setPrezzo(p.getPrezzo())
				.setStock(p.getStock())
				.setDisponibile(p.getDisponibile())
				.build())
				.collect(Collectors.toList());
	}

	@Override
	public ProdottoDTO listByID(Integer id) throws Exception{
		Optional<Prodotto> pr = prodR.findById(id);
		if(pr.isEmpty())
			throw new Exception("Prodotto non esistente");
		
		return new ProdottoDTO.Builder()
				.setId(pr.get().getId())
				.setTipo(Utilities.buildTipoProdottoDTO(pr.get().getTipo()))
				.setNome(pr.get().getNome())
				.setDescrizione(pr.get().getDescrizione())
				.setPeso(pr.get().getPeso())
				.setPrezzo(pr.get().getPrezzo())
				.setStock(pr.get().getStock())
				.setDisponibile(pr.get().getDisponibile())
				.build();
	}

}
