package com.betacom.pasticceria;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.pasticceria.controller.UtenteController;
import com.betacom.pasticceria.dto.CredenzialiDTO;
import com.betacom.pasticceria.dto.ProdottoDTO;
import com.betacom.pasticceria.dto.UtenteDTO;
import com.betacom.pasticceria.model.Carrello;
import com.betacom.pasticceria.model.Credenziali;
import com.betacom.pasticceria.model.Feedback;
import com.betacom.pasticceria.model.Prodotto;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.repositories.CredenzialiRepository;
import com.betacom.pasticceria.repositories.UtenteRepository;
import com.betacom.pasticceria.request.CredenzialiReq;
import com.betacom.pasticceria.request.ProdottoReq;
import com.betacom.pasticceria.request.SignUpReq;
import com.betacom.pasticceria.request.UtenteReq;
import com.betacom.pasticceria.response.ResponseBase;
import com.betacom.pasticceria.response.ResponseList;
import com.betacom.pasticceria.response.ResponseObject;
import com.betacom.pasticceria.services.interfaces.CredenzialiService;
import com.betacom.pasticceria.services.interfaces.UtenteService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtenteTest {
		
	@Autowired
	CredenzialiService credS;
	
	@Autowired
	CredenzialiRepository credRepo;	
		
	@Autowired
	UtenteRepository utRepo;
	
	@Autowired
	UtenteService utSer;
	
	@Autowired
	UtenteController utC;
		
	@Autowired
	Logger log;
	
	@Test
	@Order(1)
	public void createUtenteTest() throws Exception{
		UtenteReq ut = new UtenteReq();
		ut.setCognome("Gino");
		ut.setNome("Lucioni");
		ut.setcFiscale("LKSNZT84S29A664N");
		ut.setEmail("gino.frick@gmail.com");
		ut.setVia("Via Roma 3");
		ut.setCitta("Torino");
		ut.setCAP("10144");
		
		CredenzialiReq cr = new CredenzialiReq();
		cr.setUsername("gino90");
		cr.setPassword("castello90");
		
		utSer.create(ut, cr);
		log.debug("utente e credenziali create con successo");
		
		ut.setCognome("Laura");
		ut.setNome("Banderas");
		ut.setcFiscale("GVSHSM59D08M198L");
		ut.setEmail("laura.ban@gmail.com");
		ut.setVia("Via Napoli 124");
		ut.setCitta("Torino");
		ut.setCAP("10121");
		
		cr.setUsername("laura00");
		cr.setPassword("ponyta2001");
		
		utSer.create(ut, cr);
		log.debug("utente e credenziali create con successo");
		
		ut.setCognome("Marco");
		ut.setNome("Fistoni");
		ut.setcFiscale("YFLCVZ95C04C216D");
		ut.setEmail("fistoni.mark@gmail.com");
		ut.setVia("Corso Giulio Cesare 22");
		ut.setCitta("Torino");
		ut.setCAP("10140");
		
		cr.setUsername("mark45");
		cr.setPassword("intermerda45");
		
		utSer.create(ut, cr);
		log.debug("utente e credenziali create con successo");
		
		List<Utente> lP = utRepo.findAll(); 
		Assertions.assertThat(lP.size()).isGreaterThan(0);
		Assertions.assertThat(lP.size()).isEqualTo(3);
	}
		
	@Test
	@Order(2)
	public void createProdottoErrorTest() throws Exception{
		UtenteReq ut = new UtenteReq();
		ut.setCognome("Hans");
		ut.setNome("KLopp");
		ut.setcFiscale("LKSNZT84S29A664N");
		ut.setEmail("gino.frick@gmail.com");
		ut.setVia("Via Parma 3");
		ut.setCitta("Torino");
		ut.setCAP("10111");
		
		CredenzialiReq cr = new CredenzialiReq();
		cr.setUsername("gino90");
		cr.setPassword("castello90");
					
		assertThrows(Exception.class, () -> {utSer.create(ut, cr);}); 
		
		ut.setCognome("Gino");
		ut.setCAP("10144");
		ut.setcFiscale(null);
		ut.setEmail(null);
		ut.setVia(null);
		ut.setCitta(null);
		
		cr.setUsername("kiba89");
		cr.setPassword("castello90");
						
		assertThrows(Exception.class, () -> {utSer.create(ut, cr);}); 
	}

	@Test
	@Order(3)
	public void updateUtenteTest() throws Exception{
		UtenteReq ut = new UtenteReq();
		ut.setId(1);
		ut.setCognome("Mariucci");
		ut.setNome("Leonardo");
		ut.setcFiscale("MLSNZT99K29A664N");
		ut.setEmail("mariucci99@gmail.com");
		utSer.update(ut);
		
		ut.setId(3);
		ut.setCognome("Mara");
		ut.setNome("Boldi");
		ut.setEmail("mara.boldi@gmail.com");
		utSer.update(ut);
		
		Optional<Utente> utente = utRepo.findById(1);
		Assertions.assertThat(utente.get().getNome().equalsIgnoreCase("Leonardo"));
		Assertions.assertThat(utente.get().getCognome().equalsIgnoreCase("Mariucci"));
		utente = utRepo.findById(2);
		Assertions.assertThat(utente.get().getNome().equalsIgnoreCase("Mara"));
		Assertions.assertThat(utente.get().getNome().equalsIgnoreCase("Boldi"));
	}
	
	@Test
	@Order(4)
	public void updateUtenteErrorTest() throws Exception{
		UtenteReq pr = new UtenteReq();
		pr.setId(9);
		pr.setNome("Giuseepe");
		assertThrows(Exception.class, () -> {utSer.update(pr);}); 
	}
	
	@Test
	@Order(7)
	public void deleteLOgicCredenziali() throws Exception{
		CredenzialiReq cr = new CredenzialiReq();
		cr.setId(3);
		
		credS.delete(cr);
		log.debug("account disattivato con successo");
		
		List<CredenzialiDTO> lP = credS.listAll();
		Assertions.assertThat(lP.size()).isGreaterThan(0);
		Assertions.assertThat(lP.size()).isEqualTo(3);
		CredenzialiDTO dto = credS.listByID(3);
		Assertions.assertThat(dto.getAttivo() == false);

	}
	
	@Test
	@Order(5)
	public void listAll() throws Exception{
		List<UtenteDTO> lP = utSer.listAll();
		Assertions.assertThat(lP.size()).isGreaterThan(0);
		Assertions.assertThat(lP.size()).isEqualTo(3);
	}
	
	@Test
	@Order(6)
	public void listByIDTest() throws Exception{
		UtenteDTO lP = utSer.listById(2);
		Assertions.assertThat(lP.getId() == 2);
		Assertions.assertThat(lP != null);
	}
	
	//---------------------------------------------TEST MODEL UTENTE---------------------------------------------------------------------------
	@Test
    @Order(7)
    public void testGetterSetterUtente() {
        Utente utente = new Utente();
        
        utente.setId(1);
        utente.setNome("Mario");
        utente.setCognome("Rossi");
        utente.setcFiscale("MRORSS85M01H501L");
        utente.setEmail("mario.rossi@example.com");
        utente.setVia("Via Roma 12");
        utente.setCAP("10144");
        utente.setCitta("Torino");

        Credenziali credenziali = new Credenziali();
        credenziali.setId(10);
        utente.setCredenziali(credenziali);

        Carrello carrello = new Carrello();
        carrello.setId(20);
        utente.setCarrello(carrello);

        List<Feedback> feedbackList = new ArrayList<>();
        Feedback feedback = new Feedback();
        feedback.setId(100);
        feedbackList.add(feedback);
        utente.setFeedback(feedbackList);

        // Assertions per verificare ogni getter
        assertThat(utente.getId()).isEqualTo(1);
        assertThat(utente.getNome()).isEqualTo("Mario");
        assertThat(utente.getCognome()).isEqualTo("Rossi");
        assertThat(utente.getcFiscale()).isEqualTo("MRORSS85M01H501L");
        assertThat(utente.getEmail()).isEqualTo("mario.rossi@example.com");
        assertThat(utente.getVia()).isEqualTo("Via Roma 12");
        assertThat(utente.getCAP()).isEqualTo("10144");
        assertThat(utente.getCitta()).isEqualTo("Torino");
        assertThat(utente.getCredenziali()).isEqualTo(credenziali);
        assertThat(utente.getCarrello()).isEqualTo(carrello);
        assertThat(utente.getFeedback()).isEqualTo(feedbackList);
    }
	
	//-------------------------------------------TEST CONTROLLER UTENTE---------------------------------------------------------------	
	@Test
	@Order(8)
	public void listTestController() {
	    ResponseList<UtenteDTO> response = utC.listAll();

	    Assertions.assertThat(response.getRc()).isEqualTo(true);
	    Assertions.assertThat(response.getDati()).isNotEmpty();
	}

	@Test
	@Order(9)
	public void listByIdTestController() {
	    ResponseObject<UtenteDTO> response = utC.listByID(1);

	    Assertions.assertThat(response.getRc()).isEqualTo(true);
	    Assertions.assertThat(response.getDati()).isNotNull();
	}

	@Test
	@Order(10)
	public void listByIdErrorTestController() {
	    ResponseObject<UtenteDTO> response = utC.listByID(99);

	    Assertions.assertThat(response.getRc()).isEqualTo(false);
	    Assertions.assertThat(response.getMsg()).isNotNull();
	    Assertions.assertThat(response.getDati()).isNull();
	}

	@Test
	@Order(11)
	public void updateUtenteTestController() {
	    UtenteReq req = new UtenteReq();
	    req.setId(1);
	    req.setNome("Giovanni");

	    ResponseBase response = utC.update(req);
	    log.debug("Risposta update: " + response.getRc());
	    Assertions.assertThat(response.getRc()).isEqualTo(true);

	    ResponseObject<UtenteDTO> updatedResponse = utC.listByID(1);
	    Assertions.assertThat(updatedResponse.getRc()).isEqualTo(true);
	    Assertions.assertThat(updatedResponse.getDati().getNome()).isEqualTo("Giovanni");

	}
	
}
