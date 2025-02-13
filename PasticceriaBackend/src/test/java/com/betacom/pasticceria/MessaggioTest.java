package com.betacom.pasticceria;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.betacom.pasticceria.model.Messaggio;
import com.betacom.pasticceria.repositories.MessaggioRepository;
import com.betacom.pasticceria.services.interfaces.MessaggioService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(value = false)
public class MessaggioTest {
	
	@Autowired
	MessaggioRepository msgR;
	
	@Autowired
    private MessaggioService msgS;
	
	@Test
    @Order(1)
    public void testGetMessaggioFound() {
        // Preparazione dati
        Messaggio msg = new Messaggio();
        msg.setCodice("MSG001");
        msg.setMessaggio("Messaggio di test");
        msgR.save(msg);

        // Esecuzione del metodo
        String result = msgS.getMessaggio("MSG001");

        // Verifica del risultato
        Assertions.assertThat(result).isEqualTo("Messaggio di test");
    }
	
	//-------------------------------TEST MODEL PRODOTTO---------------------------------------------------------------------------------------------------
	@Test
	@Order(2)
	public void testGetterSetter() {
	    Messaggio msg = new Messaggio();
	    msg.setId(1);
	    msg.setCodice("MSG002");
	    msg.setMessaggio("Altro messaggio di test");

	    Assertions.assertThat(msg.getId()).isEqualTo(1);
	    Assertions.assertThat(msg.getCodice()).isEqualTo("MSG002");
	    Assertions.assertThat(msg.getMessaggio()).isEqualTo("Altro messaggio di test");
	}
	
}
