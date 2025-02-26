package com.betacom.pasticceria.services.implementations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.pasticceria.dto.OrdineDTO;
import com.betacom.pasticceria.model.Ordine;
import com.betacom.pasticceria.model.Status;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.repositories.OrdineRepository;
import com.betacom.pasticceria.repositories.UtenteRepository;
import com.betacom.pasticceria.request.OrdineReq;
import com.betacom.pasticceria.services.interfaces.MessaggioService;
import com.betacom.pasticceria.services.interfaces.OrdineService;
import com.betacom.pasticceria.utils.Utilities;

@Service
public class OrdineImpl implements OrdineService {
	private OrdineRepository ordR;
	private UtenteRepository utnR;
	private MessaggioService msgS;

	public OrdineImpl(OrdineRepository ordR, UtenteRepository utnR, MessaggioService msgS) {
		this.ordR = ordR;
		this.utnR = utnR;
		this.msgS = msgS;
	}

	@Override
	public Ordine create(OrdineReq req) throws Exception {

		Optional<Utente> utn = utnR.findById(req.getUtente());
		if (utn.isEmpty()) {
			throw new Exception(msgS.getMessaggio("NO_UTENTE_NO_ORDINE"));
		}

		Ordine o = new Ordine();
		o.setUtente(utn.get());
		o.setTotale(req.getTotale());
		o.setIndirizzo(utn.get().getVia() + utn.get().getCAP() + utn.get().getCitta());
		o.setStatus(Status.Confermato);
		o.setDataOrdine(Utilities.convertStringToDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date())));

		return ordR.save(o);
	}

	@Override
	public void update(OrdineReq req) throws Exception {
		Optional<Ordine> ord = ordR.findById(req.getId());
		if (ord.isEmpty()) {
			throw new Exception(msgS.getMessaggio("ORDINE_NOT_FOUND"));
		}

		Ordine o = ord.get();
		if (req.getStatus() != null) {
			try {
				Status nuovoStatus = Status.valueOf(req.getStatus());
				o.setStatus(nuovoStatus);
			} catch (Exception e) {
				throw new Exception("Stato non valido: " + req.getStatus());
			}
		}
		if (req.getTotale() != null)
			o.setTotale(req.getTotale());

		ordR.save(o);
	}

	@Override
	public void logicalDelete(Integer id) throws Exception {
		Optional<Ordine> ord = ordR.findById(id);
		if (ord.isEmpty()) {
			throw new Exception(msgS.getMessaggio("ORDINE_NOT_FOUND"));
		}

		Ordine o = ord.get();
		if (o.getStatus() == Status.Annullato) {
			throw new Exception(msgS.getMessaggio("ORDINE_GIA_ANNULLATO"));
		}

		if (o.getStatus() == Status.Consegnato || o.getStatus() == Status.Spedito) {
			throw new Exception(msgS.getMessaggio("ORDINE_GIA_ANNULLATO"));
		}

		o.setStatus(Status.Annullato);
		ordR.save(o);
	}

	@Override
	public List<OrdineDTO> listAll() {
		List<Ordine> lO = ordR.findAll();
		return lO.stream()
				.map(o -> new OrdineDTO.Builder().setId(o.getId()).setUtente(Utilities.buildUtenteDTO(o.getUtente()))
						.setIndirizzo(o.getIndirizzo()).setTotale(o.getTotale()).setStatus(o.getStatus().toString())
						.setDataOrdine(o.getDataOrdine()).build())
				.collect(Collectors.toList());
	}

	@Override
	public OrdineDTO listByID(Integer id) throws Exception {
		Optional<Ordine> o = ordR.findById(id);
		if (o.isEmpty())
			throw new Exception(msgS.getMessaggio("ID_ORDINE_NOT_FOUND"));

		return new OrdineDTO.Builder().setId(o.get().getId()).setUtente(Utilities.buildUtenteDTO(o.get().getUtente()))
				.setIndirizzo(o.get().getIndirizzo()).setTotale(o.get().getTotale())
				.setStatus(o.get().getStatus().toString()).setDataOrdine(o.get().getDataOrdine()).build();
	}

	@Override
	public List<OrdineDTO> listByUtente(Integer idUtente) throws Exception {
		List<Ordine> lO = ordR.findByUtente_Id(idUtente);
		if (lO.isEmpty())
			throw new Exception(msgS.getMessaggio("ORDINE_NOT_FOUND"));
		return lO.stream()
				.map(o -> new OrdineDTO.Builder().setId(o.getId()).setUtente(Utilities.buildUtenteDTO(o.getUtente()))
						.setIndirizzo(o.getIndirizzo()).setTotale(o.getTotale()).setStatus(o.getStatus().toString())
						.setDataOrdine(o.getDataOrdine()).build())
				.collect(Collectors.toList());
	}

	public boolean isOrderOwnedByUser(Integer orderId, Integer userId) {
		return ordR.existsByIdAndUtente_Id(orderId, userId);
	}
}