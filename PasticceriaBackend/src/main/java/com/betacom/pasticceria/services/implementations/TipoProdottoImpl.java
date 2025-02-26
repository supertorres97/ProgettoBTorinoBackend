package com.betacom.pasticceria.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.betacom.pasticceria.dto.TipoProdottoDTO;
import com.betacom.pasticceria.model.TipoProdotto;
import com.betacom.pasticceria.repositories.ProdottoRepository;
import com.betacom.pasticceria.repositories.TipoProdottoRepository;
import com.betacom.pasticceria.request.TipoProdottoReq;
import com.betacom.pasticceria.services.interfaces.MessaggioService;
import com.betacom.pasticceria.services.interfaces.TipoProdottoService;

@Service
public class TipoProdottoImpl implements TipoProdottoService {
	TipoProdottoRepository tPR;
	ProdottoRepository prodR;
	private MessaggioService msgS;
	Logger log;

	public TipoProdottoImpl(TipoProdottoRepository tPR, MessaggioService msgS, Logger log, ProdottoRepository prodR) {
		super();
		this.tPR = tPR;
		this.msgS = msgS;
		this.log = log;
		this.prodR = prodR;
	}

	@Override
	public void create(TipoProdottoReq req) throws Exception {
		log.debug("Create tipo prodotto: " + req);
		Optional<TipoProdotto> tP = tPR.findByDescrizione(req.getDescrizione());
		if (tP.isPresent())
			throw new Exception(msgS.getMessaggio("TIPO_PRODOTTO_GIA_ESISTENTE"));

		TipoProdotto p = new TipoProdotto();
		p.setDescrizione(req.getDescrizione());

		tPR.save(p);
		msgS.getMessaggio("TIPO_PRODOTTO_NEW");
	}

	@Override
	public void update(TipoProdottoReq req) throws Exception {
		log.debug("Update tipo prodotto: " + req);

		Optional<TipoProdotto> tP = tPR.findByDescrizione(req.getDescrizione());
		if (tP.isPresent())
			throw new Exception(msgS.getMessaggio("TIPO_PRODOTTO_GIA_ESISTENTE"));

		Optional<TipoProdotto> tPID = tPR.findById(req.getId());
		if (tPID.isEmpty())
			throw new Exception(msgS.getMessaggio("TIPO_PRODOTTO_NOT_FOUND"));

		tPID.get().setDescrizione(req.getDescrizione());
		tPR.save(tPID.get());
		msgS.getMessaggio("TIPO_PRODOTTO_MODIFICATO");
	}

	@Override
	public void delete(TipoProdottoReq req) throws Exception {
		log.debug("Delete tipo prodotto: " + req);

		Optional<TipoProdotto> tP = tPR.findById(req.getId());
		if (tP.isEmpty()) {
			throw new Exception(msgS.getMessaggio("TIPO_PRODOTTO_NOT_FOUND"));
		}

		boolean isPresent = prodR.existsByTipoId(req.getId());

		if (isPresent) {
			throw new Exception(msgS.getMessaggio("TIPO_PRODOTTO_COLLEGATO"));
		}

		tPR.delete(tP.get());

		log.info(msgS.getMessaggio("TIPO_PRODOTTO_DELETED"));
	}

	@Override
	public List<TipoProdottoDTO> listAll() {
		List<TipoProdotto> lTP = tPR.findAll();
		return lTP.stream()
				.map(tp -> new TipoProdottoDTO.Builder().setId(tp.getId()).setDescrizione(tp.getDescrizione()).build())
				.collect(Collectors.toList());
	}

	@Override
	public TipoProdottoDTO listByID(Integer id) throws Exception {
		Optional<TipoProdotto> tP = tPR.findById(id);
		return new TipoProdottoDTO.Builder().setId(tP.get().getId()).setDescrizione(tP.get().getDescrizione()).build();
	}

}
