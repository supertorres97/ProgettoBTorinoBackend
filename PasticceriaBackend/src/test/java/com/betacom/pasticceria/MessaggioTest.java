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
		
		Messaggio msg1 = new Messaggio();
        msg1.setCodice("UTENTE_INESISTENTE");
        msg1.setMessaggio("L'utente non esiste!");
        msgR.save(msg1);
        
        Messaggio msg2 = new Messaggio();
        msg2.setCodice("EMAIL_GIA_ESISTENTE");
        msg2.setMessaggio("La mail è già presente!");
        msgR.save(msg2);
        
        Messaggio msg3 = new Messaggio();
        msg3.setCodice("UTENTE_NON_ASSOCIATO_CREDENZIALI");
        msg3.setMessaggio("Errore: l'utente non è associato alle credenziali!");
        msgR.save(msg3);
        
        Messaggio msg4 = new Messaggio();
        msg4.setCodice("CREDENZIALI_INSERITE");
        msg4.setMessaggio("Nuove credenziali inserite con successo!");
        msgR.save(msg4);
        
        Messaggio msg5 = new Messaggio();
        msg5.setCodice("CREDENZIALI_AGGIORNATE");
        msg5.setMessaggio("Credenziali aggiornate con successo!");
        msgR.save(msg5);
        
        Messaggio msg6 = new Messaggio();
        msg6.setCodice("CREDENZIALI_ELIMINATE");
        msg6.setMessaggio("Credenziali eliminate con successo!");
        msgR.save(msg6);
        
        Messaggio msg7 = new Messaggio();
        msg7.setCodice("CREDENZIALI_NOT_FOUND");
        msg7.setMessaggio("Credenziali non trovate!");
        msgR.save(msg7);
        
        Messaggio msg8 = new Messaggio();
        msg8.setCodice("PASSWORD_UGUALE_PRECEDENTE");
        msg8.setMessaggio("La password non può essere uguale alla precedente!");
        msgR.save(msg8);
        
        Messaggio msg9 = new Messaggio();
        msg9.setCodice("USER_NOT_FOUND");
        msg9.setMessaggio("Ruolo USER non trovato!");
        msgR.save(msg9);
        
        Messaggio msg10 = new Messaggio();
        msg10.setCodice("ADMIN_NOT_FOUND");
        msg10.setMessaggio("Ruolo ADMIN non trovato!");
        msgR.save(msg10);
        
        Messaggio msg11 = new Messaggio();
        msg11.setCodice("NEW_RUOLO");
        msg11.setMessaggio("Nuovo ruolo è stato inserito!");
        msgR.save(msg11);
        
        Messaggio msg12 = new Messaggio();
        msg12.setCodice("RUOLO_NOT_FOUND");
        msg12.setMessaggio("Il ruolo non è stato trovato/inesistente!");
        msgR.save(msg12);
        
        Messaggio msg13 = new Messaggio();
        msg13.setCodice("RUOLO_AGGIORNATO");
        msg13.setMessaggio("Il ruolo è stato aggiornato!");
        msgR.save(msg13);
        
        Messaggio msg14 = new Messaggio();
        msg14.setCodice("RUOLO_ELIMINATO");
        msg14.setMessaggio("Il ruolo è stato eliminato!");
        msgR.save(msg14);
        
        Messaggio msg15 = new Messaggio();
        msg15.setCodice("UTENTE_ALL_RUOLI");
        msg15.setMessaggio("L'utente ha già tutti i ruoli che vuoi aggiungere!");
        msgR.save(msg15);
        
        Messaggio msg16 = new Messaggio();
        msg16.setCodice("RUOLO_NON_ASSEGNATO");
        msg16.setMessaggio("L'utente non ha il ruolo specificato!");
        msgR.save(msg16);
        
        Messaggio msg17 = new Messaggio();
        msg17.setCodice("CARRELLO_INESISTENTE");
        msg17.setMessaggio("Il carrello non esiste!");
        msgR.save(msg17);
        
        Messaggio msg18 = new Messaggio();
        msg18.setCodice("CARRELLO_VUOTO");
        msg18.setMessaggio("Il carrello è vuoto!");
        msgR.save(msg18);
        
        Messaggio msg19 = new Messaggio();
        msg19.setCodice("UTENTE_CARRELLO");
        msg19.setMessaggio("L'utente è già associato ad un carrello!");
        msgR.save(msg19);
        
        Messaggio msg20 = new Messaggio();
        msg20.setCodice("CARRELLOPRODOTTO_INESISTENTE");
        msg20.setMessaggio("CarrelloProdotto non esiste!");
        msgR.save(msg20);
        
        Messaggio msg21 = new Messaggio();
        msg21.setCodice("PRODOTTO_INESISTENTE");
        msg21.setMessaggio("Il prodotto è inesistente!");
        msgR.save(msg21);
        
        Messaggio msg22 = new Messaggio();
        msg22.setCodice("PRODOTTO_NON_DISPONIBILE");
        msg22.setMessaggio("Il prodotto non è disponibile");
        msgR.save(msg22);
        
        Messaggio msg23 = new Messaggio();
        msg23.setCodice("PRODOTTO_ELIMINATO");
        msg23.setMessaggio("Il prodotto è stato eliminato!");
        msgR.save(msg23);
        
        Messaggio msg24 = new Messaggio();
        msg24.setCodice("PRODOTTO_GIA_ESISTENTE");
        msg24.setMessaggio("Prodotto già esistente!");
        msgR.save(msg24);
        
        Messaggio msg25 = new Messaggio();
        msg25.setCodice("NEW_PRODOTTO");
        msg25.setMessaggio("Nuovo prodotto è stato inserito!");
        msgR.save(msg25);
        
        Messaggio msg26 = new Messaggio();
        msg26.setCodice("PRODOTTO_MODIFICATO");
        msg26.setMessaggio("Il prodotto è stato modificato!");
        msgR.save(msg26);
        
        Messaggio msg27 = new Messaggio();
        msg27.setCodice("TIPO_PRODOTTO_NOT_FOUND");
        msg27.setMessaggio("Tipo prodotto non è stato trovato!");
        msgR.save(msg27);
        
        Messaggio msgg = new Messaggio();
        msgg.setCodice("TIPO_PRODOTTO_DELETED");
        msgg.setMessaggio("Tipo prodotto non è stato trovato!");
        msgR.save(msgg);
        
        Messaggio msg28 = new Messaggio();
        msg28.setCodice("TIPO_PRODOTTO_GIA_ESISTENTE");
        msg28.setMessaggio("Il Tipo Prodotto è già esistente!");
        msgR.save(msg28);
        
        Messaggio msg29 = new Messaggio();
        msg29.setCodice("TIPO_PRODOTTO_NEW");
        msg29.setMessaggio("Nuovo Tipo Prodotto inserito con successo!");
        msgR.save(msg29);
        
        Messaggio msg30 = new Messaggio();
        msg30.setCodice("TIPO_PRODOTTO_MODIFICATO");
        msg30.setMessaggio("Il Tipo Prodotto è stato modificato con successo!");
        msgR.save(msg30);
        
        Messaggio msg31 = new Messaggio();
        msg31.setCodice("ORDINE_NOT_FOUND");
        msg31.setMessaggio("L'ordine non è stato trovato!");
        msgR.save(msg31);
        
        Messaggio msg32 = new Messaggio();
        msg32.setCodice("ORDINE_GIA_ANNULLATO");
        msg32.setMessaggio("L'ordine è già stato annullato!");
        msgR.save(msg32);
        
        Messaggio msg33 = new Messaggio();
        msg33.setCodice("ORDINE/PRODOTTO_NOT_FOUND");
        msg33.setMessaggio("L'ordine o prodotto non trovato!");
        msgR.save(msg33);
        
        Messaggio msg34 = new Messaggio();
        msg34.setCodice("ORDINE/PRODOTTO_NOT_FOUND_CON_ID");
        msg34.setMessaggio("Ordine o prodotto non trovato con l'ID:");
        msgR.save(msg34);
        
        Messaggio msg35 = new Messaggio();
        msg35.setCodice("ID_ORDINE_NOT_FOUND");
        msg35.setMessaggio("L'id ordine non è stato trovato!");
        msgR.save(msg35);
        
        Messaggio msg36 = new Messaggio();
        msg36.setCodice("NUOVO_DETTAGLI_ORDINE");
        msg36.setMessaggio("Nuovo dettagli ordine è stato inserito!");
        msgR.save(msg36);
        
        Messaggio msg37 = new Messaggio();
        msg37.setCodice("DETTAGLI_ORDINE_AGGIORNATO");
        msg37.setMessaggio("Dettagli ordine è stato aggiornato con successo!");
        msgR.save(msg37);
        
        Messaggio msg38 = new Messaggio();
        msg38.setCodice("DETTAGLI_ORDINE_ELIMINATO");
        msg38.setMessaggio("Dettagli ordine è stato eliminato con successo!");
        msgR.save(msg38);
        
        Messaggio msg39 = new Messaggio();
        msg39.setCodice("DETTAGLI_ORDINE_NOT_FOUND");
        msg39.setMessaggio("Dettagli ordine non è stato trovato!");
        msgR.save(msg39);
        
        Messaggio msg40 = new Messaggio();
        msg40.setCodice("NO_RECENSIONE_PRODOTTO_INESISTENTE");
        msg40.setMessaggio("Il prodotto che vuoi recensire non esiste!");
        msgR.save(msg40);
        
        Messaggio msg41 = new Messaggio();
        msg41.setCodice("PRODOTTO_NON_ACQUISTATO_NO_RECENSIONE");
        msg41.setMessaggio("Il prodotto che vuoi recensire non è stato acquistato!");
        msgR.save(msg41);
        
        Messaggio msg42 = new Messaggio();
        msg42.setCodice("FEEDBACK_NOT_FOUND");
        msg42.setMessaggio("Il feedback non è stato trovato!");
        msgR.save(msg42);
        
        Messaggio msg43 = new Messaggio();
        msg43.setCodice("NO_UTENTE_NO_ORDINE");
        msg43.setMessaggio("Nessun utente ha fatto un ordine!");
        msgR.save(msg43);
        
        Messaggio msg44 = new Messaggio();
        msg44.setCodice("QUANTITA>STOCK");
        msg44.setMessaggio("La quantità inserita supera lo stock disponibile!");
        msgR.save(msg44);
        
        Messaggio msg45 = new Messaggio();
        msg45.setCodice("STOCK_ESAURITO");
        msg45.setMessaggio("Lo stock è esaurito!");
        msgR.save(msg45);
		
        Messaggio msg = new Messaggio();
        msg.setCodice("MSG001");
        msg.setMessaggio("Messaggio di test");
        msgR.save(msg);

        String result = msgS.getMessaggio("MSG001");

        Assertions.assertThat(result).isEqualTo("Messaggio di test");
    }
	
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
