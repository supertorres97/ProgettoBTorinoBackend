package com.betacom.pasticceria.services.implementations;

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
import com.betacom.pasticceria.utils.Utilities;

@Service
public class DettagliOrdineImpl implements DettagliOrdineService {

    @Autowired
    private DettagliOrdineRepository detR;
    @Autowired
    private OrdineRepository ordineR;
    @Autowired
    private ProdottoRepository prodottoR;
    private Logger log;

    @Autowired
    public DettagliOrdineImpl(DettagliOrdineRepository detR, OrdineRepository ordineR, ProdottoRepository prodottoR, Logger log) {
        super();
        this.detR = detR;
        this.ordineR = ordineR;
        this.prodottoR = prodottoR;
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
            log.debug("Nuovo dettagli ordine inserito");
        } else {
            throw new Exception("Ordine o Prodotto non trovato");
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
                    throw new Exception("Ordine non trovato");
                }
            }
            if (req.getProdotto() != null) {
                Optional<Prodotto> prodotto = prodottoR.findById(req.getProdotto());
                if (prodotto.isPresent()) {
                    d.setProdotto(prodotto.get());
                } else {
                    throw new Exception("Prodotto non trovato");
                }
            }
            if (req.getPrezzoTotale() != null)
                d.setPrezzoTotale(req.getPrezzoTotale());
            if (req.getQuantitaFinale() != null)
                d.setQuantitaFinale(req.getQuantitaFinale());
            detR.save(d);
            log.debug("Dettagli ordine aggiornato");
        } else {
            throw new Exception("Dettagli ordine non trovato");
        }
    }

    @Override
    public void delete(DettagliOrdineReq req) throws Exception {
        log.debug("Delete dettagli ordine: " + req);
        Optional<DettagliOrdine> dr = detR.findById(req.getId());
        if (dr.isEmpty())
            throw new Exception("Dettagli ordine non esistente");

        DettagliOrdine d = dr.get();
        detR.delete(d);

        log.debug("Dettagli ordine eliminato");
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
            throw new Exception("Dettagli ordine non esistente");
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
}
