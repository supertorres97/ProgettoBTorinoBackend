package com.betacom.pasticceria;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
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

import com.betacom.pasticceria.dto.DettagliOrdineDTO;
import com.betacom.pasticceria.model.DettagliOrdine;
import com.betacom.pasticceria.model.Ordine;
import com.betacom.pasticceria.model.Prodotto;
import com.betacom.pasticceria.model.Status;
import com.betacom.pasticceria.model.TipoProdotto;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.repositories.DettagliOrdineRepository;
import com.betacom.pasticceria.repositories.OrdineRepository;
import com.betacom.pasticceria.repositories.ProdottoRepository;
import com.betacom.pasticceria.repositories.TipoProdottoRepository;
import com.betacom.pasticceria.repositories.UtenteRepository;
import com.betacom.pasticceria.request.DettagliOrdineReq;
import com.betacom.pasticceria.services.interfaces.DettagliOrdineService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DettagliOrdineTest {

	@Autowired
	DettagliOrdineService detOrdineS;

	@Autowired
	DettagliOrdineRepository detOrdineRepo;

	@Autowired
	OrdineRepository ordineRepo;

	@Autowired
	ProdottoRepository prodottoRepo;

	@Autowired
	UtenteRepository utenteRepo;

	@Autowired
	TipoProdottoRepository tipoProdRepo;

	@Autowired
	Logger log;

	@Test
	@Order(1)
	public void createDettagliOrdineTest() throws Exception {

		Utente u = new Utente();
		u.setEmail("testDettOrd1@test.com");
		u.setVia("Via Test 1");
		u.setCAP("00100");
		u.setCitta("Roma");
		u.setNome("Test");
		u.setCognome("User");
		u = utenteRepo.save(u);

		Ordine o = new Ordine();
		o.setUtente(u);
		o.setTotale(100.0);
		o.setIndirizzo(u.getVia() + " " + u.getCAP() + " " + u.getCitta());
		o.setDataOrdine(new Date());
		o.setStatus(Status.Confermato);
		o = ordineRepo.save(o);

		TipoProdotto t = new TipoProdotto();
		t.setDescrizione("Tipo Test");
		t = tipoProdRepo.save(t);

		Prodotto p = new Prodotto();
		p.setDisponibile(true);
		p.setTipo(t);
		p.setPeso(1.0);
		p.setPrezzo(10.0);
		p.setStock(100);
		p.setDescrizione("Prodotto Test");
		p.setNome("ProdottoTest");
		p = prodottoRepo.save(p);

		DettagliOrdineReq req = new DettagliOrdineReq();
		req.setOrdine(o.getId());
		req.setProdotto(p.getId());
		req.setPrezzoTotale(20.0);
		req.setQuantitaFinale(2);
		detOrdineS.create(req);
		log.debug("Dettagli ordine creato con successo");

		List<DettagliOrdine> list = detOrdineRepo.findAll();
		Assertions.assertThat(list.size()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void createDettagliOrdineErrorTest() throws Exception {

		DettagliOrdineReq req = new DettagliOrdineReq();
		req.setOrdine(9999);
		req.setProdotto(9999);
		req.setPrezzoTotale(15.0);
		req.setQuantitaFinale(1);
		assertThrows(Exception.class, () -> {
			detOrdineS.create(req);
		});
	}

	@Test
	@Order(3)
	public void updateDettagliOrdineTest() throws Exception {
		Utente u = new Utente();
		u.setEmail("testDettOrd2@test.com");
		u.setVia("Via Test 2");
		u.setCAP("00200");
		u.setCitta("Milano");
		u.setNome("Test2");
		u.setCognome("User2");
		u = utenteRepo.save(u);

		Ordine o = new Ordine();
		o.setUtente(u);
		o.setTotale(150.0);
		o.setIndirizzo(u.getVia() + " " + u.getCAP() + " " + u.getCitta());
		o.setDataOrdine(new Date());
		o.setStatus(Status.Confermato);
		o = ordineRepo.save(o);

		TipoProdotto t = new TipoProdotto();
		t.setDescrizione("Tipo Test 2");
		t = tipoProdRepo.save(t);

		Prodotto p = new Prodotto();
		p.setDisponibile(true);
		p.setTipo(t);
		p.setPeso(2.0);
		p.setPrezzo(20.0);
		p.setStock(50);
		p.setDescrizione("Prodotto Test 2");
		p.setNome("ProdottoTest2");
		p = prodottoRepo.save(p);

		DettagliOrdineReq req = new DettagliOrdineReq();
		req.setOrdine(o.getId());
		req.setProdotto(p.getId());
		req.setPrezzoTotale(30.0);
		req.setQuantitaFinale(3);
		detOrdineS.create(req);

		List<DettagliOrdine> list = detOrdineRepo.findAll();
		DettagliOrdine d = list.get(list.size() - 1);

		DettagliOrdineReq updateReq = new DettagliOrdineReq();
		updateReq.setId(d.getId());
		updateReq.setPrezzoTotale(35.0);
		updateReq.setQuantitaFinale(4);
		detOrdineS.update(updateReq);
		log.debug("Dettagli ordine aggiornato con successo");

		Optional<DettagliOrdine> updated = detOrdineRepo.findById(d.getId());
		Assertions.assertThat(updated.get().getPrezzoTotale()).isEqualTo(35.0);
		Assertions.assertThat(updated.get().getQuantitaFinale()).isEqualTo(4);
	}

	@Test
	@Order(4)
	public void updateDettagliOrdineErrorTest() throws Exception {
		DettagliOrdineReq updateReq = new DettagliOrdineReq();
		updateReq.setId(9999);
		updateReq.setPrezzoTotale(40.0);
		updateReq.setQuantitaFinale(5);
		assertThrows(Exception.class, () -> {
			detOrdineS.update(updateReq);
		});
	}

	@Test
	@Order(5)
	public void deleteDettagliOrdineTest() throws Exception {

		Utente u = new Utente();
		u.setEmail("testDettOrd3@test.com");
		u.setVia("Via Test 3");
		u.setCAP("00300");
		u.setCitta("Napoli");
		u.setNome("Test3");
		u.setCognome("User3");
		u = utenteRepo.save(u);

		Ordine o = new Ordine();
		o.setUtente(u);
		o.setTotale(200.0);
		o.setIndirizzo(u.getVia() + " " + u.getCAP() + " " + u.getCitta());
		o.setDataOrdine(new Date());
		o.setStatus(Status.Confermato);
		o = ordineRepo.save(o);

		TipoProdotto t = new TipoProdotto();
		t.setDescrizione("Tipo Test 3");
		t = tipoProdRepo.save(t);

		Prodotto p = new Prodotto();
		p.setDisponibile(true);
		p.setTipo(t);
		p.setPeso(3.0);
		p.setPrezzo(30.0);
		p.setStock(40);
		p.setDescrizione("Prodotto Test 3");
		p.setNome("ProdottoTest3");
		p = prodottoRepo.save(p);

		DettagliOrdineReq req = new DettagliOrdineReq();
		req.setOrdine(o.getId());
		req.setProdotto(p.getId());
		req.setPrezzoTotale(45.0);
		req.setQuantitaFinale(2);
		detOrdineS.create(req);

		List<DettagliOrdine> list = detOrdineRepo.findAll();
		DettagliOrdine d = list.get(list.size() - 1);

		DettagliOrdineReq deleteReq = new DettagliOrdineReq();
		deleteReq.setId(d.getId());
		detOrdineS.delete(deleteReq);
		log.debug("Dettagli ordine eliminato con successo");

		Optional<DettagliOrdine> deleted = detOrdineRepo.findById(d.getId());
		Assertions.assertThat(deleted).isNotPresent();
	}

	@Test
	@Order(6)
	public void listAllDettagliOrdineTest() throws Exception {
		List<DettagliOrdineDTO> list = detOrdineS.listAll();
		Assertions.assertThat(list.size()).isGreaterThan(0);
	}

	@Test
	@Order(7)
	public void listByIDDettagliOrdineTest() throws Exception {

		Utente u = new Utente();
		u.setEmail("testDettOrd4@test.com");
		u.setVia("Via Test 4");
		u.setCAP("00400");
		u.setCitta("Torino");
		u.setNome("Test4");
		u.setCognome("User4");
		u = utenteRepo.save(u);

		Ordine o = new Ordine();
		o.setUtente(u);
		o.setTotale(250.0);
		o.setIndirizzo(u.getVia() + " " + u.getCAP() + " " + u.getCitta());
		o.setDataOrdine(new Date());
		o.setStatus(Status.Confermato);
		o = ordineRepo.save(o);

		TipoProdotto t = new TipoProdotto();
		t.setDescrizione("Tipo Test 4");
		t = tipoProdRepo.save(t);

		Prodotto p = new Prodotto();
		p.setDisponibile(true);
		p.setTipo(t);
		p.setPeso(4.0);
		p.setPrezzo(40.0);
		p.setStock(30);
		p.setDescrizione("Prodotto Test 4");
		p.setNome("ProdottoTest4");
		p = prodottoRepo.save(p);

		DettagliOrdineReq req = new DettagliOrdineReq();
		req.setOrdine(o.getId());
		req.setProdotto(p.getId());
		req.setPrezzoTotale(55.0);
		req.setQuantitaFinale(3);
		detOrdineS.create(req);

		List<DettagliOrdine> list = detOrdineRepo.findAll();
		DettagliOrdine d = list.get(list.size() - 1);

		DettagliOrdineDTO dto = detOrdineS.listByID(d.getId());
		Assertions.assertThat(dto).isNotNull();
		Assertions.assertThat(dto.getId()).isEqualTo(d.getId());
	}
}
