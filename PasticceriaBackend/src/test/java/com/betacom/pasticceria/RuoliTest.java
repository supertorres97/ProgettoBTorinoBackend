package com.betacom.pasticceria;

import com.betacom.pasticceria.controller.RuoliController;
import com.betacom.pasticceria.dto.RuoliDTO;
import com.betacom.pasticceria.model.Ruoli;
import com.betacom.pasticceria.repositories.RuoliRepository;
import com.betacom.pasticceria.request.RuoliReq;
import com.betacom.pasticceria.response.ResponseBase;
import com.betacom.pasticceria.response.ResponseList;
import com.betacom.pasticceria.response.ResponseObject;
import com.betacom.pasticceria.services.implementations.RuoliImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class RuoliTest {

    @Autowired
    private RuoliRepository ruoliR;

    @Autowired
    private RuoliImpl ruoliService;
    
    @Autowired
    private RuoliController ruoC;
    
    @Autowired
    private Logger log;

    @Test
    @Order(1)
    public void createRuoloTest() throws Exception {
        RuoliReq req = new RuoliReq();
        req.setDescrizione("Amministratore");

        ruoliService.create(req);

        List<Ruoli> ruoli = ruoliR.findAll();
        Assertions.assertThat(ruoli).isNotEmpty();
        Assertions.assertThat(ruoli.get(ruoli.size() - 1).getDescrizione()).isEqualTo("Amministratore");
    }

    @Test
    @Order(2)
    public void updateRuoloTest() throws Exception {
        // Recupera l'ultimo ruolo inserito
        List<Ruoli> ruoli = ruoliR.findAll();
        Ruoli ultimoRuolo = ruoli.get(ruoli.size() - 1);

        RuoliReq req = new RuoliReq();
        req.setId(ultimoRuolo.getId());
        req.setDescrizione("Super Amministratore");

        ruoliService.update(req);

        Optional<Ruoli> ruoloAggiornato = ruoliR.findById(ultimoRuolo.getId());
        Assertions.assertThat(ruoloAggiornato).isPresent();
        Assertions.assertThat(ruoloAggiornato.get().getDescrizione()).isEqualTo("Super Amministratore");
    }

    @Test
    @Order(3)
    public void listAllRuoliTest() {
        List<RuoliDTO> ruoliList = ruoliService.listAll();
        Assertions.assertThat(ruoliList).isNotEmpty();
        Assertions.assertThat(ruoliList.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void listRuoloByIdTest() throws Exception {
        // Recupera l'ultimo ruolo inserito
        List<Ruoli> ruoli = ruoliR.findAll();
        Ruoli ultimoRuolo = ruoli.get(ruoli.size() - 1);

        RuoliDTO ruolo = ruoliService.listByID(ultimoRuolo.getId());
        Assertions.assertThat(ruolo).isNotNull();
        Assertions.assertThat(ruolo.getDescrizione()).isEqualTo("Super Amministratore");
    }

    @Test
    @Order(5)
    public void deleteRuoloTest() throws Exception {
        // Recupera l'ultimo ruolo inserito
        List<Ruoli> ruoli = ruoliR.findAll();
        Ruoli ultimoRuolo = ruoli.get(ruoli.size() - 1);

        RuoliReq req = new RuoliReq();
        req.setId(ultimoRuolo.getId());

        ruoliService.delete(req);

        Optional<Ruoli> ruoloCancellato = ruoliR.findById(ultimoRuolo.getId());
        Assertions.assertThat(ruoloCancellato).isEmpty();
    }
    
//------------------------------------------------------------------------------------TEST RUOLI CONTROLLER---------------------------------------------------------------------------------------------------    
    
    @Test
    @Order(6)
    public void createRuoloControllerTest() {
        log.debug("Running createRuoloControllerTest...");

        RuoliReq req = new RuoliReq();
        req.setDescrizione("Ruolo Controller Test");

        ResponseBase response = ruoC.create(req);
        log.debug("Response after create: {}", response);

        Assertions.assertThat(response.getRc()).isTrue()
            .withFailMessage("Creazione Ruolo tramite Controller fallita. Response msg: " + response.getMsg());

        List<Ruoli> ruoli = ruoliR.findAll();
        Assertions.assertThat(ruoli).isNotEmpty();
        Assertions.assertThat(ruoli.get(ruoli.size() - 1).getDescrizione()).isEqualTo("Ruolo Controller Test");
    }

    @Test
    @Order(7)
    public void updateRuoloControllerTest() {
        log.debug("Running updateRuoloControllerTest...");

        // Recupera l'ultimo ruolo inserito
        List<Ruoli> ruoli = ruoliR.findAll();
        Ruoli ultimoRuolo = ruoli.get(ruoli.size() - 1);

        RuoliReq req = new RuoliReq();
        req.setId(ultimoRuolo.getId());
        req.setDescrizione("Ruolo Aggiornato Controller");

        ResponseBase response = ruoC.update(req);
        log.debug("Response after update: {}", response);

        Assertions.assertThat(response.getRc()).isTrue()
            .withFailMessage("Aggiornamento Ruolo tramite Controller fallito. Response msg: " + response.getMsg());

        Optional<Ruoli> ruoloAggiornato = ruoliR.findById(ultimoRuolo.getId());
        Assertions.assertThat(ruoloAggiornato).isPresent();
        Assertions.assertThat(ruoloAggiornato.get().getDescrizione()).isEqualTo("Ruolo Aggiornato Controller");
    }

    @Test
    @Order(8)
    public void listRuoloByIdControllerTest() {
        log.debug("Running listRuoloByIdControllerTest...");

        // Recupera l'ultimo ruolo inserito
        List<Ruoli> ruoli = ruoliR.findAll();
        Ruoli ultimoRuolo = ruoli.get(ruoli.size() - 1);

        ResponseObject<RuoliDTO> response = ruoC.listByID(ultimoRuolo.getId());
        log.debug("Response after listByID: {}", response);

        Assertions.assertThat(response.getRc()).isTrue();
        Assertions.assertThat(response.getDati()).isNotNull();
        Assertions.assertThat(response.getDati().getDescrizione()).isEqualTo(ultimoRuolo.getDescrizione());
    }

    @Test
    @Order(9)
    public void listAllRuoliControllerTest() {
        log.debug("Running listAllRuoliControllerTest...");

        ResponseList<RuoliDTO> response = ruoC.listAll();
        log.debug("Response after listAll: {}", response);

        Assertions.assertThat(response.getRc()).isTrue();
        Assertions.assertThat(response.getDati()).isNotEmpty();
        Assertions.assertThat(response.getDati().size()).isGreaterThan(0);
    }

    @Test
    @Order(10)
    public void deleteRuoloControllerTest() {
        log.debug("Running deleteRuoloControllerTest...");

        // Recupera l'ultimo ruolo inserito
        List<Ruoli> ruoli = ruoliR.findAll();
        Ruoli ultimoRuolo = ruoli.get(ruoli.size() - 1);

        RuoliReq req = new RuoliReq();
        req.setId(ultimoRuolo.getId());

        ResponseBase response = ruoC.delete(req);
        log.debug("Response after delete: {}", response);

        Assertions.assertThat(response.getRc()).isTrue()
            .withFailMessage("Cancellazione Ruolo tramite Controller fallita. Response msg: " + response.getMsg());

        Optional<Ruoli> ruoloCancellato = ruoliR.findById(ultimoRuolo.getId());
        Assertions.assertThat(ruoloCancellato).isEmpty();
    }
    
}
