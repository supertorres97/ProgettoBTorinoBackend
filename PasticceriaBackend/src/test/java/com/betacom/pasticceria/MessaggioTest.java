package com.betacom.pasticceria;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.betacom.pasticceria.model.Messaggio;
import com.betacom.pasticceria.repositories.MessaggioRepository;
import com.betacom.pasticceria.services.interfaces.MessaggioService;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(value = false)
public class MessaggioTest {
	
	@Autowired
	MessaggioRepository msgR;
	
	@Autowired
    private MessaggioService msgS;
	
	/**
     * Testa il model Messaggio: salva un record e verifica il recupero tramite repository.
     */
//    @Test
//    @Order(1)
//    public void testMessaggioModel() {
//        // Crea e salva un nuovo Messaggio
//        Messaggio mess = new Messaggio();
//        mess.setCodice("MODEL_TEST");
//        mess.setMessaggio("Test message for model");
//        msgR.save(mess);
//        
//        // Recupera il messaggio tramite il codice
//        Optional<Messaggio> retrieved = msgR.findByCodice("MODEL_TEST");
//        assertTrue(retrieved.isPresent(), "Il messaggio deve essere presente nel repository");
//        assertEquals("Test message for model", retrieved.get().getMessaggio(), "Il messaggio recuperato deve corrispondere a quello salvato");
//    }
//    
//    /**
//     * Testa il metodo getMessaggio del service.
//     * Se il codice non esiste, il metodo deve restituire lo stesso codice passato;
//     * se il codice esiste, deve restituire il messaggio corrispondente.
//     */
//    @Test
//    @Order(2)
//    public void testGetMessaggioMethod() {
//        // Caso in cui il codice non esiste ancora nel repository
//        String nonExistent = msgS.getMessaggio("SERVICE_TEST");
//        assertEquals("SERVICE_TEST", nonExistent, "Per un codice inesistente il service deve restituire lo stesso codice");
//        
//        // Ora aggiunge un record e testa nuovamente
//        Messaggio mess = new Messaggio();
//        mess.setCodice("SERVICE_TEST");
//        mess.setMessaggio("Service test message");
//        msgR.save(mess);
//        
//        String existent = msgS.getMessaggio("SERVICE_TEST");
//        assertEquals("Service test message", existent, "Il service deve restituire il messaggio salvato per il codice esistente");
//    }

}
