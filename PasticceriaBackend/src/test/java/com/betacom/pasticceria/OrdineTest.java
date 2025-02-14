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

import com.betacom.pasticceria.controller.OrdineController;
import com.betacom.pasticceria.dto.OrdineDTO;
import com.betacom.pasticceria.model.Ordine;
import com.betacom.pasticceria.model.Status;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.repositories.OrdineRepository;
import com.betacom.pasticceria.repositories.UtenteRepository;
import com.betacom.pasticceria.request.OrdineReq;
import com.betacom.pasticceria.response.ResponseBase;
import com.betacom.pasticceria.response.ResponseList;
import com.betacom.pasticceria.response.ResponseObject;
import com.betacom.pasticceria.services.interfaces.OrdineService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrdineTest {

	@Autowired
	private OrdineService ordS;

	@Autowired
	private OrdineRepository ordR;

	@Autowired
	private UtenteRepository utR;

	@Autowired
	private OrdineController ordC;

	@Autowired
	private Logger log;

	@Test
	@Order(1)
	public void createOrdineTest() throws Exception {

		Utente u = new Utente();
		u.setEmail("ordine_create@test.com");
		u.setVia("Via Roma ");
		u.setCAP("00100");
		u.setCitta("Roma");
		u.setNome("Test");
		u.setCognome("User");
		u = utR.save(u);

		OrdineReq req = new OrdineReq();
		req.setUtente(u.getId());
		req.setTotale(100.0);

		ordS.create(req);
		log.debug("Ordine creato con successo");

		List<Ordine> orders = ordR.findAll();
		Assertions.assertThat(orders.size()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void createOrdineErrorTest() throws Exception {
		OrdineReq req = new OrdineReq();
		req.setUtente(9999);
		req.setTotale(50.0);

		assertThrows(Exception.class, () -> {
			ordS.create(req);
		});
	}

	@Test
	@Order(3)
	public void updateOrdineTest() throws Exception {
		Utente u = new Utente();
		u.setEmail("ordine_update@test.com");
		u.setVia("Via Roma ");
		u.setCAP("00100");
		u.setCitta("Roma");
		u.setNome("Test");
		u.setCognome("User");
		u = utR.save(u);

		OrdineReq req = new OrdineReq();
		req.setUtente(u.getId());
		req.setTotale(150.0);
		ordS.create(req);

		List<Ordine> orders = ordR.findByUtente_Id(u.getId());
		Ordine ordineToUpdate = orders.get(orders.size() - 1);

		OrdineReq updateReq = new OrdineReq();
		updateReq.setId(ordineToUpdate.getId());
		updateReq.setStatus("Annullato");
		updateReq.setTotale(ordineToUpdate.getTotale());
		ordS.update(updateReq);
		log.debug("Ordine aggiornato con successo");

		Optional<Ordine> updatedOrder = ordR.findById(ordineToUpdate.getId());
		Assertions.assertThat(updatedOrder.get().getStatus()).isEqualTo(Status.Annullato);
	}

	@Test
	@Order(4)
	public void updateOrdineErrorTest() throws Exception {
		OrdineReq req = new OrdineReq();
		req.setId(9999);
		req.setStatus("Inesistente");
		assertThrows(Exception.class, () -> {
			ordS.update(req);
		});
	}

	@Test
	@Order(16)
	public void deleteOrdineTest() throws Exception {
		Utente u = new Utente();
		u.setEmail("ordine_delete@test.com");
		u.setVia("Via Roma ");
		u.setCAP("00100");
		u.setCitta("Roma");
		u.setNome("Test");
		u.setCognome("User");
		u = utR.save(u);

		OrdineReq req = new OrdineReq();
		req.setUtente(u.getId());
		req.setTotale(200.0);
		ordS.create(req);

		List<Ordine> orders = ordR.findByUtente_Id(u.getId());
		Ordine ordineToDelete = orders.get(orders.size() - 1);

		ordS.logicalDelete(ordineToDelete.getId());
		log.debug("Ordine eliminato logicamente con successo");

		Optional<Ordine> deletedOrder = ordR.findById(ordineToDelete.getId());
		Assertions.assertThat(deletedOrder.get().getStatus()).isEqualTo(Status.Annullato);
	}

	@Test
	@Order(6)
	public void listAllOrdineTest() throws Exception {
		List<OrdineDTO> list = ordS.listAll();
		Assertions.assertThat(list.size()).isGreaterThan(0);
	}

	@Test
	@Order(7)
	public void listByIDTest() throws Exception {
		Utente u = new Utente();
		u.setEmail("ordine_listbyid@test.com");
		u.setVia("Via Roma ");
		u.setCAP("00100");
		u.setCitta("Roma");
		u.setNome("Test");
		u.setCognome("User");
		u = utR.save(u);

		OrdineReq req = new OrdineReq();
		req.setUtente(u.getId());
		req.setTotale(120.0);
		ordS.create(req);

		List<Ordine> orders = ordR.findByUtente_Id(u.getId());
		Ordine lastOrder = orders.get(orders.size() - 1);

		OrdineDTO dto = ordS.listByID(lastOrder.getId());
		Assertions.assertThat(dto.getId()).isEqualTo(lastOrder.getId());
	}

	@Test
	@Order(8)
	public void listByUtenteTest() throws Exception {
		Utente u = new Utente();
		u.setEmail("ordine_listbyutente@test.com");
		u.setVia("Via Roma ");
		u.setCAP("00100");
		u.setCitta("Roma");
		u.setNome("Test");
		u.setCognome("User");
		u = utR.save(u);

		OrdineReq req = new OrdineReq();
		req.setUtente(u.getId());
		req.setTotale(120.0);
		ordS.create(req);

		List<OrdineDTO> list = ordS.listByUtente(u.getId());
		Assertions.assertThat(list.size()).isGreaterThan(0);
	}

	// ------------------------------------TEST ORDINE CONTROLLER------------------------------------------------
	@Test
	@Order(9)
	public void createOrdineTestController() {
		OrdineReq req = new OrdineReq();
		req.setUtente(1);
		req.setTotale(100.0);

		ResponseBase r = ordC.create(req);

		Assertions.assertThat(r.getRc()).isEqualTo(true);
	}

	@Test
	@Order(10)
	public void listAllOrdiniTestController() {
		ResponseList<OrdineDTO> r = ordC.listAll();

		Assertions.assertThat(r.getRc()).isEqualTo(true);
		Assertions.assertThat(r.getDati()).isNotEmpty();
	}

	@Test
	@Order(11)
	public void listByIDOrdineTestController() {
		ResponseObject<OrdineDTO> r = ordC.listByID(1);

		Assertions.assertThat(r.getRc()).isEqualTo(true);
		Assertions.assertThat(r.getDati().getId()).isEqualTo(1);
	}

	@Test
	@Order(12)
	public void listByIDOrdineErrorTestController() {
		ResponseObject<OrdineDTO> r = ordC.listByID(999);

		Assertions.assertThat(r.getRc()).isEqualTo(false);
		Assertions.assertThat(r.getMsg()).isEqualTo("Id ordine non trovato");
	}

	@Test
	@Order(13)
	public void updateOrdineTestController() {
		OrdineReq req = new OrdineReq();
		req.setId(1);
		req.setTotale(150.0);
		req.setStatus("Annullato");

		ResponseBase r = ordC.update(req);

		Assertions.assertThat(r.getRc()).isEqualTo(true);

		ResponseObject<OrdineDTO> updatedOrdine = ordC.listByID(1);
		Assertions.assertThat(updatedOrdine.getRc()).isEqualTo(true);
		Assertions.assertThat(updatedOrdine.getDati().getTotale()).isEqualTo(150.0);
		Assertions.assertThat(updatedOrdine.getDati().getStatus()).isEqualTo("Annullato");
	}

	@Test
	@Order(14)
	public void deleteOrdineTestController() {
		Utente u = new Utente();
		u.setEmail("ordine_delete_logic@test.com");
		u.setVia("Via Roma ");
		u.setCAP("00100");
		u.setCitta("Roma");
		u.setNome("Test");
		u.setCognome("User");
		u = utR.save(u);

		OrdineReq req = new OrdineReq();
		req.setUtente(u.getId());
		req.setTotale(250.0);
		try {
			ordS.create(req);
		} catch (Exception e) {
			log.debug(e.getMessage());
		}

		List<Ordine> orders = ordR.findByUtente_Id(u.getId());
		Ordine ordineToDelete = orders.get(orders.size() - 1);

		ResponseBase r = ordC.delete(ordineToDelete.getId());
		Assertions.assertThat(r.getRc()).isEqualTo(true);

		Optional<Ordine> deletedOrdine = ordR.findById(ordineToDelete.getId());
		Assertions.assertThat(deletedOrdine.isPresent()).isTrue();
		Assertions.assertThat(deletedOrdine.get().getStatus()).isEqualTo(Status.Annullato);
	}

	@Test
	@Order(15)
	public void updateOrdineErrorTestController() {
		OrdineReq req = new OrdineReq();
		req.setId(999); // ID non esistente
		req.setStatus("Inesistente");

		ResponseBase r = ordC.update(req);

		Assertions.assertThat(r.getRc()).isEqualTo(false);
		Assertions.assertThat(r.getMsg()).isEqualTo("Ordine non trovato");
	}

}
