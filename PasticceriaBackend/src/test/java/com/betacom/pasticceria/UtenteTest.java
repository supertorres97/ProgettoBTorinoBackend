package com.betacom.pasticceria;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
import com.betacom.pasticceria.dto.ProdottoDTO;
import com.betacom.pasticceria.dto.UtenteDTO;
import com.betacom.pasticceria.model.Credenziali;
import com.betacom.pasticceria.model.Prodotto;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.repositories.CredenzialiRepository;
import com.betacom.pasticceria.repositories.UtenteRepository;
import com.betacom.pasticceria.request.CredenzialiReq;
import com.betacom.pasticceria.request.ProdottoReq;
import com.betacom.pasticceria.request.UtenteReq;
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
	Logger log;
	
	@Test
	@Order(1)
	public void createProdottoTest() throws Exception{
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
}
