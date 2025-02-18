package com.betacom.pasticceria.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.pasticceria.dto.DettagliOrdineDTO;
import com.betacom.pasticceria.model.DettagliOrdine;
import com.betacom.pasticceria.model.Ordine;
import com.betacom.pasticceria.model.Prodotto;
import com.betacom.pasticceria.repositories.DettagliOrdineRepository;
import com.betacom.pasticceria.repositories.OrdineRepository;
import com.betacom.pasticceria.repositories.ProdottoRepository;
import com.betacom.pasticceria.request.DettagliOrdineReq;
import com.betacom.pasticceria.services.interfaces.DettagliOrdineService;
import com.betacom.pasticceria.services.interfaces.MessaggioService;
import com.betacom.pasticceria.utils.Utilities;

@Service
public class DettagliOrdineImpl implements DettagliOrdineService {
    private DettagliOrdineRepository detR;
    private OrdineRepository ordineR;
    private ProdottoRepository prodottoR;
    private MessaggioService msgS;
    private Logger log;

    @Autowired
    public DettagliOrdineImpl(DettagliOrdineRepository detR, OrdineRepository ordineR, ProdottoRepository prodottoR, MessaggioService msgS, Logger log) {
        super();
        this.detR = detR;
        this.ordineR = ordineR;
        this.prodottoR = prodottoR;
        this.msgS = msgS;
        this.log = log;
    }

    @Override
    public void create(DettagliOrdineReq req) throws Exception {
        log.debug("Create dettagli ordine: " + req);

        DettagliOrdine d = new DettagliOrdine();
        Optional<Ordine> ordine = ordineR.findById(req.getOrdine());
        Optional<Prodotto> prodotto = prodottoR.findById(req.getProdotto());

        if (ordine.isPresent() && prodotto.isPresent()) {
            d.setOrdine(ordine.get());
            d.setProdotto(prodotto.get());
            d.setPrezzoTotale(req.getPrezzoTotale());
            d.setQuantitaFinale(req.getQuantitaFinale());
            detR.save(d);
            
            msgS.getMessaggio("NUOVO_DETTAGLI_ORDINE");
        } else {
            throw new Exception(msgS.getMessaggio("ORDINE/PRODOTTO_NOT_FOUND"));
        }
    }

    @Override
    public void update(DettagliOrdineReq req) throws Exception {
        log.debug("Update dettagli ordine: " + req);

        Optional<DettagliOrdine> dr = detR.findById(req.getId());
        if (dr.isPresent()) {
            DettagliOrdine d = dr.get();
            if (req.getOrdine() != null) {
                Optional<Ordine> ordine = ordineR.findById(req.getOrdine());
                if (ordine.isPresent()) {
                    d.setOrdine(ordine.get());
                } else {
                    throw new Exception(msgS.getMessaggio("ORDINE_NOT_FOUND"));
                }
            }
            
            if (req.getProdotto() != null) {
                Optional<Prodotto> prodotto = prodottoR.findById(req.getProdotto());
                if (prodotto.isPresent()) {
                    d.setProdotto(prodotto.get());
                } else {
                    throw new Exception(msgS.getMessaggio("PRODOTTO_INESISTENTE"));
                }
            }
            
            if (req.getPrezzoTotale() != null)
                d.setPrezzoTotale(req.getPrezzoTotale());
            
            if (req.getQuantitaFinale() != null)
                d.setQuantitaFinale(req.getQuantitaFinale());
            detR.save(d);
            
            msgS.getMessaggio("DETTAGLI_ORDINE_AGGIORNATO");
        } else {
            throw new Exception(msgS.getMessaggio("DETTAGLI_ORDINE_NOT_FOUND"));
        }
    }

    @Override
    public void delete(DettagliOrdineReq req) throws Exception {
        log.debug("Delete dettagli ordine: " + req);
        
        Optional<DettagliOrdine> dr = detR.findById(req.getId());
        if (dr.isEmpty())
            throw new Exception(msgS.getMessaggio("DETTAGLI_ORDINE_NOT_FOUND"));

        DettagliOrdine d = dr.get();
        detR.delete(d);

        msgS.getMessaggio("DETTAGLI_ORDINE_ELIMINATO");
    }

    @Override
    public List<DettagliOrdineDTO> listAll() {
        List<DettagliOrdine> ld = detR.findAll();
        return ld.stream()
                .map(d -> new DettagliOrdineDTO.Builder()
                        .setId(d.getId())
                        .setOrdine(Utilities.buildOrdineDTO(d.getOrdine()))
                        .setProdotto(Utilities.buildProdottoDTO(d.getProdotto()))
                        .setPrezzoTotale(d.getPrezzoTotale())
                        .setQuantitaFinale(d.getQuantitaFinale())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public DettagliOrdineDTO listByID(Integer id) throws Exception {
        Optional<DettagliOrdine> dr = detR.findById(id);
        if (dr.isEmpty())
            throw new Exception(msgS.getMessaggio("DETTAGLI_ORDINE_NOT_FOUND"));
        
        Optional<Ordine> ordine = ordineR.findById(dr.get().getOrdine().getId());
        Optional<Prodotto> prodotto = prodottoR.findById(dr.get().getProdotto().getId());
        
        return new DettagliOrdineDTO.Builder()
                .setId(dr.get().getId())
                .setOrdine(Utilities.buildOrdineDTO(ordine.get()))
                .setProdotto(Utilities.buildProdottoDTO(prodotto.get()))
                .setPrezzoTotale(dr.get().getPrezzoTotale())
                .setQuantitaFinale(dr.get().getQuantitaFinale())
                .build();
    }
    
    @Override
    public List<DettagliOrdineDTO> listByOrderID(Integer id) throws Exception {
    	 
    	List<DettagliOrdine> dettagliOrdini = detR.findByOrdine_Id(id);
    	    
    	if (dettagliOrdini.isEmpty()) {
    		throw new Exception(msgS.getMessaggio("DETTAGLI_ORDINE_NOT_FOUND"));
    	}
    	    
    	List<DettagliOrdineDTO> dettagliOrdineDTO = new ArrayList<>();
    	    
    	for (DettagliOrdine dr : dettagliOrdini) {
    		Optional<Ordine> ordine = ordineR.findById(dr.getOrdine().getId());
    	    Optional<Prodotto> prodotto = prodottoR.findById(dr.getProdotto().getId());
    	    
    	    if (ordine.isPresent() && prodotto.isPresent()) {
    	    	DettagliOrdineDTO detOrdDTO = new DettagliOrdineDTO.Builder()
    	                    .setId(dr.getId())
    	                    .setOrdine(Utilities.buildOrdineDTO(ordine.get())) 
    	                    .setProdotto(Utilities.buildProdottoDTO(prodotto.get()))
    	                    .setPrezzoTotale(dr.getPrezzoTotale())
    	                    .setQuantitaFinale(dr.getQuantitaFinale())
    	                    .build();
    	    	dettagliOrdineDTO.add(detOrdDTO);
    	    } else {
    	            // Se ordine o prodotto non sono presenti, lancia un'eccezione (opzionale)
    	            throw new Exception(msgS.getMessaggio("ORDINE/PRODOTTO_NOT_FOUND_CON_ID") + dr.getOrdine().getId());
    	    }
    	}
    	    
    	return dettagliOrdineDTO;
    }
}
