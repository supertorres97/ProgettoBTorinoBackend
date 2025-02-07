package com.betacom.pasticceria;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.pasticceria.dto.TipoProdottoDTO;
import com.betacom.pasticceria.request.TipoProdottoReq;
import com.betacom.pasticceria.services.interfaces.TipoProdottoService;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TipoProdottoTest {
	
	@Autowired
	TipoProdottoService tiPS;
	
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
		
	}
	
	@Test
	@Order(2)
	public void createTipoProdottoErrorTest() throws Exception{
		TipoProdottoReq tP = new TipoProdottoReq();
		
		tP.setDescrizione("Lievitati");
		tiPS.create(tP);
		tP.setDescrizione("Lievitati");
		
		assertThrows(Exception.class, () -> {tiPS.create(tP);}); 
	}
	

	@Test
	@Order(3)
	public void updateBiciTest() throws Exception{
		TipoProdottoReq tP = new TipoProdottoReq();
		tP.setId(1);
		tP.setDescrizione("Semifreddi");
		tiPS.create(tP);
		log.debug("tipo prodotto creato con successo");
		
		tiPS.update(tP);
		
		TipoProdottoDTO t = tiPS.listByID(1);
		Assertions.assertThat(t.getDescrizione()).isEqualTo("Semifreddi");

		tP.setId(4);
		tP.setDescrizione("Salati");
		tiPS.create(tP);
		Assertions.assertThat(t.getDescrizione()).isEqualTo("Salati");
	}
	
	@Test
	@Order(4)
	public void removeMotoTest() throws Exception{
		TipoProdottoReq tP = new TipoProdottoReq();
		tP.setId(3);	
		tiPS.delete(tP);
		log.debug("Veicolo rimosso con successo");
		
		List<TipoProdottoDTO> lB = tiPS.listAll();
		
		Assertions.assertThat(lB.size()).isEqualTo(3);
		Assertions.assertThat(lB.size()).isLessThan(4);
	}

}
