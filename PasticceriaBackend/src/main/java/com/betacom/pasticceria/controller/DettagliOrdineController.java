package com.betacom.pasticceria.controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.pasticceria.dto.DettagliOrdineDTO;
import com.betacom.pasticceria.request.DettagliOrdineReq;
import com.betacom.pasticceria.response.ResponseBase;
import com.betacom.pasticceria.response.ResponseList;
import com.betacom.pasticceria.response.ResponseObject;
import com.betacom.pasticceria.services.interfaces.DettagliOrdineService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/dettagli-ordine")
public class DettagliOrdineController {

    private DettagliOrdineService detS;
    private Logger log;

    public DettagliOrdineController(DettagliOrdineService detS, Logger log) {
        super();
        this.detS = detS;
        this.log = log;
    }

    @PostMapping("/create")
    public ResponseBase create(@RequestBody(required = true) DettagliOrdineReq req) {
        log.debug("Create dettagli ordine: " + req);
        ResponseBase r = new ResponseBase();
        r.setRc(true);
        try {
            detS.create(req);
        } catch (Exception e) {
            log.error(e.getMessage());
            r.setMsg(e.getMessage());
            r.setRc(false);
        }
        return r;
    }

    @PostMapping("/update")
    public ResponseBase update(@RequestBody(required = true) DettagliOrdineReq req) {
        log.debug("Update dettagli ordine: " + req);
        ResponseBase r = new ResponseBase();
        r.setRc(true);
        try {
            detS.update(req);
        } catch (Exception e) {
            log.error(e.getMessage());
            r.setMsg(e.getMessage());
            r.setRc(false);
        }
        return r;
    }

    @PostMapping("/delete")
    public ResponseBase delete(@RequestBody(required = true) DettagliOrdineReq req) {
        log.debug("Delete dettagli ordine: " + req);
        ResponseBase r = new ResponseBase();
        r.setRc(true);
        try {
            detS.delete(req);
        } catch (Exception e) {
            log.error(e.getMessage());
            r.setMsg(e.getMessage());
            r.setRc(false);
        }
        return r;
    }

    @GetMapping("/listByID")
    public ResponseObject<DettagliOrdineDTO> listByID(Integer id) {
        ResponseObject<DettagliOrdineDTO> r = new ResponseObject<DettagliOrdineDTO>();
        r.setRc(true);
        try {
            r.setDati(detS.listByID(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            r.setMsg(e.getMessage());
            r.setRc(false);
        }
        return r;
    }

    @GetMapping("/listAll")
    public ResponseList<DettagliOrdineDTO> listAll() {
        log.debug("Lista dettagli ordine: ");
        ResponseList<DettagliOrdineDTO> r = new ResponseList<DettagliOrdineDTO>();
        r.setRc(true);
        try {
            r.setDati(detS.listAll());
        } catch (Exception e) {
            log.error(e.getMessage());
            r.setMsg(e.getMessage());
            r.setRc(false);
        }

        return r;
    }
    
    @GetMapping("/listByOrdineID")
    public ResponseList<DettagliOrdineDTO> listByOrdineID(Integer id) {
    	ResponseList<DettagliOrdineDTO> r = new ResponseList<DettagliOrdineDTO>();
        r.setRc(true);
        try {
            r.setDati(detS.listByOrderID(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            r.setMsg(e.getMessage());
            r.setRc(false);
        }
        return r;
    }
}