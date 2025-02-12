package com.betacom.pasticceria;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
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

import com.betacom.pasticceria.controller.FeedbackController;
import com.betacom.pasticceria.dto.FeedbackDTO;
import com.betacom.pasticceria.model.DettagliOrdine;
import com.betacom.pasticceria.model.Feedback;
import com.betacom.pasticceria.model.Ordine;
import com.betacom.pasticceria.model.Prodotto;
import com.betacom.pasticceria.model.Status;
import com.betacom.pasticceria.model.TipoProdotto;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.request.FeedbackReq;
import com.betacom.pasticceria.response.ResponseBase;
import com.betacom.pasticceria.response.ResponseList;
import com.betacom.pasticceria.response.ResponseObject;
import com.betacom.pasticceria.services.interfaces.FeedbackService;
import com.betacom.pasticceria.repositories.FeedbackRepository;
import com.betacom.pasticceria.repositories.OrdineRepository;
import com.betacom.pasticceria.repositories.ProdottoRepository;
import com.betacom.pasticceria.repositories.TipoProdottoRepository;
import com.betacom.pasticceria.repositories.UtenteRepository;
import com.betacom.pasticceria.repositories.DettagliOrdineRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FeedbackTest {

    @Autowired
    FeedbackService feedS;
    
    @Autowired
    FeedbackRepository feedR;
    
    @Autowired
    OrdineRepository ordR;
    
    @Autowired
    UtenteRepository utR;
    
    @Autowired
    ProdottoRepository prodR;
    
    @Autowired
    TipoProdottoRepository tipoProdR;
    
    @Autowired
    DettagliOrdineRepository detR;
    
    @Autowired
    FeedbackController fedC;
    
    @Autowired
    Logger log;
    
    @Test
    @Order(1)
    public void createFeedbackTest() throws Exception {
        // Creazione di un Utente con email unica
        Utente u = new Utente();
        u.setEmail("feedback_create@test.com");
        u.setVia("Via Milano");
        u.setCAP("20100");
        u.setCitta("Milano");
        u.setNome("Feedback");
        u.setCognome("User");
        u = utR.save(u);
        
        // Creazione di un Ordine per l'utente
        Ordine o = new Ordine();
        o.setUtente(u);
        o.setTotale(100.0);
        o.setIndirizzo(u.getVia() + " " + u.getCAP() + " " + u.getCitta());
        o.setDataOrdine(new Date());
        o.setStatus(Status.Confermato);
        o = ordR.save(o);
        
        // Creazione di un TipoProdotto
        TipoProdotto t = new TipoProdotto();
        t.setDescrizione("Tipo Feedback");
        t = tipoProdR.save(t);
        
        // Creazione di un Prodotto
        Prodotto p = new Prodotto();
        p.setDisponibile(true);
        p.setTipo(t);
        p.setNome("ProdottoFeedback");
        p.setDescrizione("Prodotto per feedback test");
        p.setPeso(1.0);
        p.setPrezzo(20.0);
        p.setStock(50);
        p = prodR.save(p);
        
        // Creazione di un DettagliOrdine per far superare il checkOrderedProduct
        DettagliOrdine d = new DettagliOrdine();
        d.setOrdine(o);
        d.setProdotto(p);
        d.setPrezzoTotale(20.0);
        d.setQuantitaFinale(1);
        detR.save(d);
        
        // Creazione di un FeedbackReq
        FeedbackReq req = new FeedbackReq();
        req.setOrdine(o.getId());
        req.setProdotto(p.getId());
        req.setUtente(u.getId());
        req.setDescrizione("Feedback positivo");
        req.setVoto("CINQUE"); // Assumendo che l'enum Voto contenga "CINQUE"
        req.setDataFeedback(new Date());
        
        // Creazione del Feedback
        feedS.create(req);
        log.debug("Feedback creato con successo");
        
        List<Feedback> list = feedR.findAll();
        Assertions.assertThat(list.size()).isGreaterThan(0);
    }
    
    @Test
    @Order(2)
    public void createFeedbackErrorTest() throws Exception {
        // Tentativo di creare un feedback senza che l'utente abbia acquistato il prodotto.
        // Creazione di un Utente
        Utente u = new Utente();
        u.setEmail("feedback_error@test.com");
        u.setVia("Via Napoli");
        u.setCAP("80100");
        u.setCitta("Napoli");
        u.setNome("Feedback");
        u.setCognome("UserError");
        u = utR.save(u);
        
        // Creazione di un Ordine per l'utente, MA NON verrà creato un record di DettagliOrdine
        Ordine o = new Ordine();
        o.setUtente(u);
        o.setTotale(80.0);
        o.setIndirizzo(u.getVia() + " " + u.getCAP() + " " + u.getCitta());
        o.setDataOrdine(new Date());
        o.setStatus(Status.Confermato);
        o = ordR.save(o);
        
        // Creazione di un TipoProdotto
        TipoProdotto t = new TipoProdotto();
        t.setDescrizione("Tipo Feedback Error");
        t = tipoProdR.save(t);
        
        // Creazione di un Prodotto
        Prodotto p = new Prodotto();
        p.setDisponibile(true);
        p.setTipo(t);
        p.setNome("ProdottoFeedbackError");
        p.setDescrizione("Prodotto per feedback error test");
        p.setPeso(1.5);
        p.setPrezzo(25.0);
        p.setStock(30);
        p = prodR.save(p);
        
        // Non creiamo un DettagliOrdine: quindi checkOrderedProduct restituirà false
        
        FeedbackReq req = new FeedbackReq();
        req.setOrdine(o.getId());
        req.setProdotto(p.getId());
        req.setUtente(u.getId());
        req.setDescrizione("Feedback negativo");
        req.setVoto("DUE");
        req.setDataFeedback(new Date());
        
        assertThrows(Exception.class, () -> { feedS.create(req); });
    }
    
    @Test
    @Order(3)
    public void updateFeedbackTest() throws Exception {
        // Creazione di un Utente
        Utente u = new Utente();
        u.setEmail("feedback_update@test.com");
        u.setVia("Via Torino");
        u.setCAP("10100");
        u.setCitta("Torino");
        u.setNome("Feedback");
        u.setCognome("UserUpdate");
        u = utR.save(u);
        
        // Creazione di un Ordine per l'utente
        Ordine o = new Ordine();
        o.setUtente(u);
        o.setTotale(120.0);
        o.setIndirizzo(u.getVia() + " " + u.getCAP() + " " + u.getCitta());
        o.setDataOrdine(new Date());
        o.setStatus(Status.Confermato);
        o = ordR.save(o);
        
        // Creazione di un TipoProdotto
        TipoProdotto t = new TipoProdotto();
        t.setDescrizione("Tipo Feedback Update");
        t = tipoProdR.save(t);
        
        // Creazione di un Prodotto
        Prodotto p = new Prodotto();
        p.setDisponibile(true);
        p.setTipo(t);
        p.setNome("ProdottoFeedbackUpdate");
        p.setDescrizione("Prodotto per feedback update test");
        p.setPeso(2.0);
        p.setPrezzo(30.0);
        p.setStock(40);
        p = prodR.save(p);
        
        // Creazione di un DettagliOrdine
        DettagliOrdine d = new DettagliOrdine();
        d.setOrdine(o);
        d.setProdotto(p);
        d.setPrezzoTotale(30.0);
        d.setQuantitaFinale(2);
        detR.save(d);
        
        // Creazione di un Feedback
        FeedbackReq req = new FeedbackReq();
        req.setOrdine(o.getId());
        req.setProdotto(p.getId());
        req.setUtente(u.getId());
        req.setDescrizione("Feedback iniziale");
        req.setVoto("TRE");
        req.setDataFeedback(new Date());
        feedS.create(req);
        
        // Recupero del Feedback creato
        List<Feedback> list = feedR.findAll();
        Feedback feedbackCreato = list.get(list.size() - 1);
        
        // Aggiornamento: modifica della descrizione e del voto
        FeedbackReq updateReq = new FeedbackReq();
        updateReq.setId(feedbackCreato.getId());
        updateReq.setOrdine(feedbackCreato.getOrdine().getId());  // Aggiunto
        updateReq.setProdotto(feedbackCreato.getProdotto().getId());  // Aggiunto
        updateReq.setUtente(feedbackCreato.getUtente().getId());  // Aggiunto
        updateReq.setDescrizione("Feedback aggiornato");
        updateReq.setVoto("CINQUE");
        updateReq.setDataFeedback(new Date());
        
        // Chiamata del metodo update
        feedS.update(updateReq);
        log.debug("Feedback aggiornato con successo");
        
        // Verifica dell'aggiornamento
        Optional<Feedback> updated = feedR.findById(feedbackCreato.getId());
        Assertions.assertThat(updated.get().getDescrizione()).isEqualTo("Feedback aggiornato");
        Assertions.assertThat(updated.get().getVoto().toString()).isEqualTo("CINQUE");
    }

    
    @Test
    @Order(4)
    public void updateFeedbackErrorTest() throws Exception {
        FeedbackReq updateReq = new FeedbackReq();
        updateReq.setId(9999); // id inesistente
        updateReq.setDescrizione("Feedback error update");
        updateReq.setVoto("TRE");
        updateReq.setDataFeedback(new Date());
        assertThrows(Exception.class, () -> { feedS.update(updateReq); });
    }
    
    @Test
    @Order(5)
    public void deleteFeedbackTest() throws Exception {
        // Creazione di un Utente
        Utente u = new Utente();
        u.setEmail("feedback_delete@test.com");
        u.setVia("Via Palermo");
        u.setCAP("90100");
        u.setCitta("Palermo");
        u.setNome("Feedback");
        u.setCognome("UserDelete");
        u = utR.save(u);
        
        // Creazione di un Ordine per l'utente
        Ordine o = new Ordine();
        o.setUtente(u);
        o.setTotale(110.0);
        o.setIndirizzo(u.getVia() + " " + u.getCAP() + " " + u.getCitta());
        o.setDataOrdine(new Date());
        o.setStatus(Status.Confermato);
        o = ordR.save(o);
        
        // Creazione di un TipoProdotto
        TipoProdotto t = new TipoProdotto();
        t.setDescrizione("Tipo Feedback Delete");
        t = tipoProdR.save(t);
        
        // Creazione di un Prodotto
        Prodotto p = new Prodotto();
        p.setDisponibile(true);
        p.setTipo(t);
        p.setNome("ProdottoFeedbackDelete");
        p.setDescrizione("Prodotto per feedback delete test");
        p.setPeso(1.5);
        p.setPrezzo(15.0);
        p.setStock(60);
        p = prodR.save(p);
        
        // Creazione di un DettagliOrdine
        DettagliOrdine d = new DettagliOrdine();
        d.setOrdine(o);
        d.setProdotto(p);
        d.setPrezzoTotale(15.0);
        d.setQuantitaFinale(1);
        detR.save(d);
        
        // Creazione di un Feedback
        FeedbackReq req = new FeedbackReq();
        req.setOrdine(o.getId());
        req.setProdotto(p.getId());
        req.setUtente(u.getId());
        req.setDescrizione("Feedback da eliminare");
        req.setVoto("QUATTRO");
        req.setDataFeedback(new Date());
        feedS.create(req);
        
        List<Feedback> list = feedR.findAll();
        Feedback feedbackCreato = list.get(list.size() - 1);
        
        // Eliminazione
        FeedbackReq deleteReq = new FeedbackReq();
        deleteReq.setId(feedbackCreato.getId());
        feedS.delete(deleteReq);
        log.debug("Feedback eliminato con successo");
        
        Optional<Feedback> deleted = feedR.findById(feedbackCreato.getId());
        Assertions.assertThat(deleted).isNotPresent();
    }
    
    @Test
    @Order(6)
    public void listAllFeedbackTest() throws Exception {
        List<FeedbackDTO> list = feedS.listAll();
        Assertions.assertThat(list.size()).isGreaterThan(0);
    }
    
    @Test
    @Order(7)
    public void listByIDFeedbackTest() throws Exception {
        // Creazione di un Utente
        Utente u = new Utente();
        u.setEmail("feedback_listbyid@test.com");
        u.setVia("Via Venezia");
        u.setCAP("30100");
        u.setCitta("Venezia");
        u.setNome("Feedback");
        u.setCognome("UserListByID");
        u = utR.save(u);
        
        // Creazione di un Ordine per l'utente
        Ordine o = new Ordine();
        o.setUtente(u);
        o.setTotale(130.0);
        o.setIndirizzo(u.getVia() + " " + u.getCAP() + " " + u.getCitta());
        o.setDataOrdine(new Date());
        o.setStatus(Status.Confermato);
        o = ordR.save(o);
        
        // Creazione di un TipoProdotto
        TipoProdotto t = new TipoProdotto();
        t.setDescrizione("Tipo Feedback ListByID");
        t = tipoProdR.save(t);
        
        // Creazione di un Prodotto
        Prodotto p = new Prodotto();
        p.setDisponibile(true);
        p.setTipo(t);
        p.setNome("ProdottoFeedbackListByID");
        p.setDescrizione("Prodotto per feedback listByID test");
        p.setPeso(2.5);
        p.setPrezzo(35.0);
        p.setStock(70);
        p = prodR.save(p);
        
        // Creazione di un DettagliOrdine
        DettagliOrdine d = new DettagliOrdine();
        d.setOrdine(o);
        d.setProdotto(p);
        d.setPrezzoTotale(35.0);
        d.setQuantitaFinale(2);
        detR.save(d);
        
        // Creazione di un Feedback
        FeedbackReq req = new FeedbackReq();
        req.setOrdine(o.getId());
        req.setProdotto(p.getId());
        req.setUtente(u.getId());
        req.setDescrizione("Feedback per listByID");
        req.setVoto("TRE");
        req.setDataFeedback(new Date());
        feedS.create(req);
        
        List<Feedback> list = feedR.findAll();
        Feedback feedbackCreato = list.get(list.size() - 1);
        
        FeedbackDTO dto = feedS.listByID(feedbackCreato.getId());
        Assertions.assertThat(dto).isNotNull();
        Assertions.assertThat(dto.getId()).isEqualTo(feedbackCreato.getId());
    }
    
    //-------------------------------------------------------------------------------------TEST CONTROLLER FEEDBACK-----------------------------------------------------------------------------
    
    @Test
    @Order(8)
    public void listAllTestController() {
        log.debug("Running listAllTest...");
        ResponseList<FeedbackDTO> r = fedC.listAll();
        
        Assertions.assertThat(r.getRc()).isTrue();
        Assertions.assertThat(r.getDati()).isNotEmpty();
    }
    
    @Test
    @Order(9)
    public void listByIdTestController() {
        log.debug("Running listByIdTest...");
        ResponseObject<FeedbackDTO> r = fedC.listByID(1);
        
        Assertions.assertThat(r.getRc()).isTrue();
        Assertions.assertThat(r.getDati().getId()).isEqualTo(1);
        Assertions.assertThat(r.getDati().getDescrizione()).isNotNull();
    }
    
    @Test
    @Order(10)
    public void listByIdErrorTestController() {
        log.debug("Running listByIdErrorTest...");
        ResponseObject<FeedbackDTO> r = fedC.listByID(99); // Assume che 99 sia un ID inesistente
        
        Assertions.assertThat(r.getRc()).isFalse();
        Assertions.assertThat(r.getMsg()).isEqualTo("No value present");  // Messaggio reale restituito dal controller
    }
        
    @Test
    @Order(11)
    public void deleteFeedbackTestController() {
        log.debug("Running deleteFeedbackTest...");
        FeedbackReq req = new FeedbackReq();
        req.setId(1);  // Assume che l'ID 1 esista
        
        ResponseBase r = fedC.delete(req);
        
        Assertions.assertThat(r.getRc()).isTrue();
        
        ResponseObject<FeedbackDTO> r1 = fedC.listByID(1);
        Assertions.assertThat(r1.getRc()).isFalse();  // Dopo la cancellazione, il feedback non dovrebbe esistere
    }
}
