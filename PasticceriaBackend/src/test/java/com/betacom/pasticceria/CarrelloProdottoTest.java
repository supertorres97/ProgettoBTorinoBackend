package com.betacom.pasticceria;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.pasticceria.dto.CarrelloDTO;
import com.betacom.pasticceria.dto.CarrelloProdottoDTO;
import com.betacom.pasticceria.model.Prodotto;
import com.betacom.pasticceria.model.TipoProdotto;
import com.betacom.pasticceria.repositories.ProdottoRepository;
import com.betacom.pasticceria.repositories.TipoProdottoRepository;
import com.betacom.pasticceria.request.CarrelloProdottoReq;
import com.betacom.pasticceria.request.CarrelloReq;
import com.betacom.pasticceria.request.ProdottoReq;
import com.betacom.pasticceria.services.interfaces.CarrelloProdottoService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarrelloProdottoTest {
	
	@Autowired
	CarrelloProdottoService cpS;
	
	@Autowired
	ProdottoRepository prodR;
	
	@Autowired
	TipoProdottoRepository tpR;
	
	@Test
	@Order(1)
	public void createCarrelloProdotto() throws Exception {
		CarrelloProdottoReq req = new CarrelloProdottoReq();
		
		req.setCarrello(1);
		req.setProdotto(1);
		cpS.create(req);
		
		req.setCarrello(1);
		req.setProdotto(1);
		cpS.create(req);
		
		req.setCarrello(1);
		req.setProdotto(2);
		cpS.create(req);
		
		req.setCarrello(2);
		req.setProdotto(1);
		req.setQuantita(1);
		cpS.create(req);
		
		req.setCarrello(2);
		req.setProdotto(1);
		req.setQuantita(1);
		cpS.create(req);
		
	}
	
	@Test
	@Order(2)
	public void createCarrelloProdottoErrors() throws Exception {
		CarrelloProdottoReq req = new CarrelloProdottoReq();
		
		//PRODOTTO INESISTENTE
		req.setCarrello(1);
		req.setProdotto(15);
		assertThrows(Exception.class, () -> {
			cpS.create(req);
		});
		
		//CARRELLO INESISTENTE
		req.setCarrello(1000);
		req.setProdotto(1);
		assertThrows(Exception.class, () -> {
			cpS.create(req);
		});
		
		TipoProdotto tp = new TipoProdotto();
		tp.setDescrizione("aa");
		tpR.save(tp);
		
		Prodotto prod = new Prodotto();
		prod.setDescrizione("cane e gatto");
		prod.setDisponibile(false);
		prod.setNome("albero");
		prod.setPeso(20.1);
		prod.setPrezzo(1000.0);
		prod.setStock(10);
		prod.setTipo(tp);
		prod.setImg("ciao");
		prodR.save(prod);
		
		//PRODOTTO NON DISPONIBILE
		req.setCarrello(1);
		req.setProdotto(prod.getId());
		assertThrows(Exception.class, () -> {
			cpS.create(req);
		});
		
		Prodotto prod1 = new Prodotto();
		prod1.setDescrizione("cane e gatto");
		prod1.setDisponibile(true);
		prod1.setNome("albero");
		prod1.setPeso(20.1);
		prod1.setPrezzo(1000.0);
		prod1.setStock(10);
		prod1.setTipo(tp);
		prod1.setImg("ciao");
		prodR.save(prod1);
		
		req.setCarrello(1);
		req.setProdotto(prod1.getId());
		req.setQuantita(11);
		assertThrows(Exception.class, () -> {
			cpS.create(req);
		});
		
		req.setCarrello(1);
		req.setProdotto(prod1.getId());
		req.setQuantita(10);
		cpS.create(req);
		
		Prodotto prod2 = new Prodotto();
		prod2.setDescrizione("cane e gatto");
		prod2.setDisponibile(true);
		prod2.setNome("albero");
		prod2.setPeso(20.1);
		prod2.setPrezzo(1000.0);
		prod2.setStock(0);
		prod2.setTipo(tp);
		prod2.setImg("ciao");
		prodR.save(prod2);
		
		req.setCarrello(1);
		req.setProdotto(prod2.getId());
		req.setQuantita(1);
		assertThrows(Exception.class, () -> {
			cpS.create(req);
		});
	}
	
	@Test
	@Order(3)
	public void removeCarrelloProdottoAndError() throws Exception {
		CarrelloProdottoReq req = new CarrelloProdottoReq();
		
		req.setCarrello(1);
		req.setProdotto(1);
		req.setId(1);
		//cpS.remove(req);
		
		req.setCarrello(1);
		req.setProdotto(1);
		req.setId(50);
		assertThrows(Exception.class, () -> {
			//cpS.remove(req);
		});
	}
	
	@Test
	@Order(4)
	public void updateCarrelloProdotto() throws Exception {
		CarrelloProdottoReq req = new CarrelloProdottoReq();
		
		req.setCarrello(1);
		req.setProdotto(1);
		req.setId(2);
		req.setQuantita(2);
		cpS.update(req);
		
		//QUANTITA 0
		req.setCarrello(1);
		req.setProdotto(1);
		req.setId(2);
		req.setQuantita(-22);
		cpS.update(req);
	}
	
	@Test
	@Order(5)
	public void updateCarrelloProdottoErrors() throws Exception {
		CarrelloProdottoReq req = new CarrelloProdottoReq();
		
		//PRODOTTO INESISTENTE
		req.setCarrello(1);
		req.setProdotto(15);
		req.setId(2);
		assertThrows(Exception.class, () -> {
			cpS.update(req);
		});
		
		//CARRELLOPRODOTTO INESISTENTE
		req.setCarrello(1);
		req.setProdotto(1);
		req.setId(22);
		assertThrows(Exception.class, () -> {
			cpS.update(req);
		});
	}
	
	
	@Test
	@Order(6)
	public void acquista() throws Exception {
		CarrelloReq req = new CarrelloReq();
		
		req.setId(1);
		req.setUtente(1);
		cpS.acuqista(req);
	}
	
	@Test
	@Order(7)
	public void acquistaErrors() throws Exception {
		CarrelloReq req = new CarrelloReq();
		
		//UTENTE INESISTENTE
		req.setId(1);
		req.setUtente(50);
		assertThrows(Exception.class, () -> {
			cpS.acuqista(req);
		});
		
		//CARRELLO INESISTENTE
		req.setId(100);
		req.setUtente(1);
		assertThrows(Exception.class, () -> {
			cpS.acuqista(req);
		});
		
		//CARRELLO VUOTO
		req.setId(3);
		req.setUtente(1);
		assertThrows(Exception.class, () -> {
			cpS.acuqista(req);
		});
	}
	
	@Test
	@Order(8)
	public void svuotaCarrelloError() throws Exception {
		
		assertThrows(Exception.class, () -> {
			cpS.svuotaCarrello(50);
		});
	}
	
	@Test
	@Order(9)
	public void listByCarrello() throws Exception{
		List<CarrelloProdottoDTO> lCP = cpS.listByCarrello(2);
		Assertions.assertThat(lCP.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(10)
	public void listByProdotto() throws Exception{
		List<CarrelloProdottoDTO> lCP = cpS.listByProdotto(1);
		Assertions.assertThat(lCP.size()).isGreaterThan(0);
	}
	
	
	@Test
	@Order(11)
	public void listeErrors() throws Exception {
		
		//CARRELLO INSESISTENTE
		assertThrows(Exception.class, () -> {
			List<CarrelloProdottoDTO> lCP = cpS.listByCarrello(50);
		});
		
		//PRODOTTO INSESISTENTE
		assertThrows(Exception.class, () -> {
			List<CarrelloProdottoDTO> lCP = cpS.listByProdotto(50);
		});
	}
//	req.setCarrello(2);
//	req.setProdotto(1);
//	req.setQuantita(10);
//	cpS.create(req);
}
