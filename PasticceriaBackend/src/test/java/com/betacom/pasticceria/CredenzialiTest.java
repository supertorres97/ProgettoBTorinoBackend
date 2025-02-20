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

import com.betacom.pasticceria.dto.CredenzialiDTO;
import com.betacom.pasticceria.dto.UtenteDTO;
import com.betacom.pasticceria.model.Credenziali;
import com.betacom.pasticceria.model.Ruoli;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.repositories.CredenzialiRepository;
import com.betacom.pasticceria.repositories.UtenteRepository;
import com.betacom.pasticceria.request.CredenzialiReq;
import com.betacom.pasticceria.request.UtenteReq;
import com.betacom.pasticceria.services.interfaces.CredenzialiService;
import com.betacom.pasticceria.services.interfaces.UtenteService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CredenzialiTest {
	@Autowired
	CredenzialiService credS;
	
	@Autowired
	CredenzialiRepository credRepo;	
		
	@Autowired
	UtenteRepository utRepo;
	
	@Autowired
	UtenteService utSer;
		
	@Autowired
	Logger log;
	
	@Test
	@Order(1)
	public void updateCredenzialiTest() throws Exception{
		CredenzialiReq cred = new CredenzialiReq();
		cred.setId(3);
		cred.setIdUtente(3);
		cred.setPassword("update1");
		credS.update(cred);
		
		cred.setId(2);
		cred.setIdUtente(2);
		cred.setPassword("update2");
		credS.update(cred);
		
		Optional<Credenziali> credenziali = credRepo.findById(1);
		Assertions.assertThat(credenziali.get().getPassword().equalsIgnoreCase("update1"));
		credenziali = credRepo.findById(2);
		Assertions.assertThat(credenziali.get().getPassword().equalsIgnoreCase("update2"));
	}
	
	@Test
	@Order(2)
	public void updateUtenteErrorTest() throws Exception{
		CredenzialiReq cred = new CredenzialiReq();
		cred.setId(9);
		cred.setPassword("update1");
		assertThrows(Exception.class, () -> {credS.update(cred);}); 
	}
	
	@Test
	@Order(3)
	public void deleteLogicalCredenziali() throws Exception{
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
	@Order(4)
	public void listAll() throws Exception{
		List<CredenzialiDTO> lP = credS.listAll();
		Assertions.assertThat(lP.size()).isGreaterThan(0);
		Assertions.assertThat(lP.size()).isEqualTo(3);
	}
	
	@Test
	@Order(5)
	public void listByIDTest() throws Exception{
		CredenzialiDTO lP = credS.listByID(2);
		Assertions.assertThat(lP.getId() == 2);
		Assertions.assertThat(lP != null);
	}
	
	//-------------------------------------TEST MODEL CREDENZIALI----------------------------------------------------------------------------
	@Test
    @Order(6)
    public void testGetterSetterCredenziali() {
        log.debug("Running testGetterSetterCredenziali...");

        Credenziali credenziali = new Credenziali();
        credenziali.setId(1);
        credenziali.setUsername("test_user");
        credenziali.setPassword("password123");
        
        Utente utente = new Utente();
        utente.setId(10);
        credenziali.setUtente(utente);

        Ruoli ruolo = new Ruoli();
        ruolo.setId(100);
        ruolo.setDescrizione("ADMIN");
        credenziali.setRuolo(ruolo);

        credenziali.setAttivo(true);

        // Assertions
        assertThat(credenziali.getId()).isEqualTo(1);
        assertThat(credenziali.getUsername()).isEqualTo("test_user");
        assertThat(credenziali.getPassword()).isEqualTo("password123");
        assertThat(credenziali.getUtente()).isEqualTo(utente);
        assertThat(credenziali.getRuolo()).isEqualTo(ruolo);
        assertThat(credenziali.getAttivo()).isTrue();

        log.debug("Getter and Setter tests for Credenziali passed successfully.");
    }
}
