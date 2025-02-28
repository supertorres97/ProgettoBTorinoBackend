package com.betacom.pasticceria;

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

import com.betacom.pasticceria.dto.TipoProdottoDTO;
import com.betacom.pasticceria.model.Prodotto;
import com.betacom.pasticceria.model.TipoProdotto;
import com.betacom.pasticceria.repositories.TipoProdottoRepository;
import com.betacom.pasticceria.request.TipoProdottoReq;
import com.betacom.pasticceria.services.interfaces.TipoProdottoService;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TipoProdottoTest {
	
	@Autowired
	TipoProdottoService tiPS;
	
	@Autowired
	TipoProdottoRepository tpR;
	
	@Autowired
	Logger log;
	
	@Test
	@Order(1)
	public void createTipoProdottoTest() throws Exception{
		TipoProdottoReq tP = new TipoProdottoReq();
		
		tP.setDescrizione("Lievitati");
		tiPS.create(tP);
		log.debug("tipo prodotto creato con successo");
		
		tP.setDescrizione("Sfogliati");
		tiPS.create(tP);
		log.debug("tipo prodotto creato con successo");
		
		tP.setDescrizione("Dolci al cucchiaio");
		tiPS.create(tP);
		log.debug("tipo prodotto creato con successo");
		
		tP.setDescrizione("Monoporzione");
		tiPS.create(tP);
		log.debug("tipo prodotto creato con successo");
		
		List<TipoProdottoDTO> lT = tiPS.listAll();
		Assertions.assertThat(lT.size()).isGreaterThan(0);
		Assertions.assertThat(lT.size()).isEqualTo(4);
		
	}
	
	@Test
	@Order(2)
	public void createTipoProdottoErrorTest() throws Exception{
		TipoProdottoReq tP = new TipoProdottoReq();
		
		tP.setDescrizione("Tavola Calda");
		tiPS.create(tP);
		tP.setDescrizione("Tavola Calda");
		
		assertThrows(Exception.class, () -> {tiPS.create(tP);}); 
	}

	@Test
	@Order(3)
	public void updateTipoProdottoTest() throws Exception{
		TipoProdottoReq tP = new TipoProdottoReq();
		tP.setId(1);
		tP.setDescrizione("Dolcetti");
		tiPS.update(tP);
		log.debug("tipo prodotto modificato con successo");
		
		tP.setId(2);
		tP.setDescrizione("Mono");
		tiPS.update(tP);
		log.debug("tipo prodotto creato con successo");
		Optional<TipoProdotto> tipoProd = tpR.findById(1);
		Assertions.assertThat(tipoProd.get().getDescrizione().equalsIgnoreCase("Dolcetti"));
		tipoProd = tpR.findById(2);
		Assertions.assertThat(tipoProd.get().getDescrizione().equalsIgnoreCase("Mono"));
	}
	@Test
	@Order(4)
	public void updateTipoProdottoErrorTest() throws Exception{
		TipoProdottoReq tP = new TipoProdottoReq();
		tP.setId(9);
		tP.setDescrizione("Panuozzo");
		assertThrows(Exception.class, () -> {tiPS.update(tP);}); 
	}
	
	@Test
	@Order(5)
	public void deleteTipoProdottoTest() throws Exception{
		TipoProdottoReq tP = new TipoProdottoReq();
		tP.setId(4);
		
		tiPS.delete(tP);
		log.debug("tipo prodotto eliminato con successo");
		
		List<TipoProdottoDTO> lT = tiPS.listAll();
		Assertions.assertThat(lT.size()).isGreaterThan(0);
		Assertions.assertThat(lT.size()).isGreaterThan(2);
		Assertions.assertThat(lT.size()).isEqualTo(4);
		Assertions.assertThat(lT.size()).isLessThan(5);
	}
	
	@Test
	@Order(6)
	public void deleteTipoProdottoErrorsTest() throws Exception{
		TipoProdottoReq tP = new TipoProdottoReq();
		tP.setId(400);
		
		assertThrows(Exception.class, () -> {tiPS.delete(tP);});
	}
	
	@Test
	@Order(9)
	public void listAll() throws Exception{
		List<TipoProdottoDTO> lT = tiPS.listAll();
		Assertions.assertThat(lT.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(7)
	public void listByIDTest() throws Exception{
		TipoProdottoDTO lT = tiPS.listByID(3);
		Assertions.assertThat(lT.getId() == 3);
		Assertions.assertThat(lT != null);
	}
	
	@Test
	@Order(8)
	public void testGetterSetterTipoProdotto() {
	    log.debug("Running testGetterSetterTipoProdotto...");

	    TipoProdotto tipoProdotto = new TipoProdotto();
	    tipoProdotto.setId(1);
	    tipoProdotto.setDescrizione("Test Tipo Prodotto");

	    Assertions.assertThat(tipoProdotto.getId()).isEqualTo(1);
	    Assertions.assertThat(tipoProdotto.getDescrizione()).isEqualTo("Test Tipo Prodotto");

	    List<Prodotto> prodottiList = new ArrayList<>();
	    tipoProdotto.setProdotti(prodottiList);
	    Assertions.assertThat(tipoProdotto.getProdotti()).isEqualTo(prodottiList);

	    log.debug("Getter and Setter tests for TipoProdotto passed successfully.");
	}
	
}
