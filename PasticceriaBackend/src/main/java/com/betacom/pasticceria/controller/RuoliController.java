package com.betacom.pasticceria.controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.pasticceria.dto.RuoliDTO;
import com.betacom.pasticceria.request.RuoliReq;
import com.betacom.pasticceria.response.ResponseBase;
import com.betacom.pasticceria.response.ResponseList;
import com.betacom.pasticceria.response.ResponseObject;
import com.betacom.pasticceria.services.interfaces.RuoliService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/ruoli")
public class RuoliController {

    private RuoliService ruoliS;
    private Logger log;

    public RuoliController(RuoliService ruoliS, Logger log) {
        super();
        this.ruoliS = ruoliS;
        this.log = log;
    }

    @PostMapping("/create")
    public ResponseBase create(@RequestBody(required = true) RuoliReq req) {
        log.debug("Create ruoli: " + req);
        ResponseBase r = new ResponseBase();
        r.setRc(true);
        try {
            ruoliS.create(req);
        } catch (Exception e) {
            log.error(e.getMessage());
            r.setMsg(e.getMessage());
            r.setRc(false);
        }
        return r;
    }

    @PostMapping("/update")
    public ResponseBase update(@RequestBody(required = true) RuoliReq req) {
        log.debug("Update ruoli: " + req);
        ResponseBase r = new ResponseBase();
        r.setRc(true);
        try {
            ruoliS.update(req);
        } catch (Exception e) {
            log.error(e.getMessage());
            r.setMsg(e.getMessage());
            r.setRc(false);
        }
        return r;
    }

    @PostMapping("/delete")
    public ResponseBase delete(@RequestBody(required = true) RuoliReq req) {
        log.debug("Delete ruoli: " + req);
        ResponseBase r = new ResponseBase();
        r.setRc(true);
        try {
            ruoliS.delete(req);
        } catch (Exception e) {
            log.error(e.getMessage());
            r.setMsg(e.getMessage());
            r.setRc(false);
        }
        return r;
    }

    @GetMapping("/listByID")
    public ResponseObject<RuoliDTO> listByID(Integer id) {
        ResponseObject<RuoliDTO> r = new ResponseObject<RuoliDTO>();
        r.setRc(true);
        try {
            r.setDati(ruoliS.listByID(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            r.setMsg(e.getMessage());
            r.setRc(false);
        }
        return r;
    }

    @GetMapping("/listAll")
    public ResponseList<RuoliDTO> listAll() {
        log.debug("Lista ruoli: ");
        ResponseList<RuoliDTO> r = new ResponseList<RuoliDTO>();
        r.setRc(true);
        try {
            r.setDati(ruoliS.listAll());
        } catch (Exception e) {
            log.error(e.getMessage());
            r.setMsg(e.getMessage());
            r.setRc(false);
        }

        return r;
    }
}