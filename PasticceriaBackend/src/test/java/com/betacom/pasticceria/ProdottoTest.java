package com.betacom.pasticceria;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.betacom.pasticceria.dto.ProdottoDTO;
import com.betacom.pasticceria.model.CarrelloProdotto;
import com.betacom.pasticceria.model.Prodotto;
import com.betacom.pasticceria.model.TipoProdotto;
import com.betacom.pasticceria.repositories.ProdottoRepository;
import com.betacom.pasticceria.repositories.TipoProdottoRepository;
import com.betacom.pasticceria.request.ProdottoReq;
import com.betacom.pasticceria.services.interfaces.ProdottoService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProdottoTest {

	@Autowired
	ProdottoService prodS;
	
	@Autowired
	ProdottoRepository prodRepo;	
		
	@Autowired
	TipoProdottoRepository tpR;
		
	@Autowired
	Logger log;
		
	@Test
	@Order(1)
	public void createProdottoTest() throws Exception{
		ProdottoReq pr = new ProdottoReq();
		pr.setDescrizione("Ricotta, farina");
		pr.setNome("Cannolo");
		pr.setPeso(50.0);
		pr.setStock(5);
		pr.setTipo(2);
		pr.setPrezzo(2.50);
		pr.setDisponibile(true);
		prodS.create(pr);
		log.debug("prodotto creato con successo");
		
		pr.setDescrizione("Riso, zafferano, ragù");
		pr.setNome("Arancina");
		pr.setPeso(100.0);
		pr.setStock(10);
		pr.setTipo(1);
		pr.setPrezzo(3.50);
		pr.setDisponibile(true);
		prodS.create(pr);
		log.debug("prodotto creato con successo");
		
		pr.setDescrizione("Sfoglia, farino, burro, crema alla vaniglia");
		pr.setNome("Sfogliatella");
		pr.setPeso(80.0);
		pr.setStock(5);
		pr.setTipo(3);
		pr.setPrezzo(2.50);
		pr.setDisponibile(true);
		prodS.create(pr);
		log.debug("prodotto creato con successo");
			
		List<Prodotto> lP = prodRepo.findAll(); 
		Assertions.assertThat(lP.size()).isGreaterThan(0);
		Assertions.assertThat(lP.size()).isEqualTo(3);
	}
		
	@Test
	@Order(2)
	public void createProdottoErrorTest() throws Exception{
		ProdottoReq pr = new ProdottoReq();
		pr.setDescrizione("Ricotta, farina");
		pr.setNome("Cannolo");
		pr.setStock(5);
		pr.setPrezzo(2.50);
		pr.setDisponibile(true);
				
		assertThrows(Exception.class, () -> {prodS.create(pr);}); 
	}

	@Test
	@Order(3)
	public void updateProdottoTest() throws Exception{
		ProdottoReq pr = new ProdottoReq();
		pr.setId(1);
		pr.setNome("Iris");
		prodS.update(pr);
		log.debug("prodotto modificato con successo");
		
		pr.setId(2);
		pr.setNome("Arancine");
		prodS.update(pr);
		log.debug("prodotto creato con successo");
		
		Optional<Prodotto> prodotto = prodRepo.findById(1);
		Assertions.assertThat(prodotto.get().getNome().equalsIgnoreCase("Iris"));
		prodotto = prodRepo.findById(2);
		Assertions.assertThat(prodotto.get().getNome().equalsIgnoreCase("Arancine"));
	}
	@Test
	@Order(4)
	public void updateProdottoErrorTest() throws Exception{
		ProdottoReq pr = new ProdottoReq();
		pr.setId(9);
		pr.setNome("Panino con mortadella");
		assertThrows(Exception.class, () -> {prodS.update(pr);}); 
	}
	
	@Test
	@Order(5)
	public void deleteProdottoTest() throws Exception{
		ProdottoReq pr = new ProdottoReq();
		pr.setId(3);
		
		prodS.delete(pr);
		log.debug("tipo prodotto eliminato con successo");
		
		List<ProdottoDTO> lP = prodS.listAll();
		Assertions.assertThat(lP.size()).isGreaterThan(0);
		Assertions.assertThat(lP.size()).isGreaterThan(1);
		Assertions.assertThat(lP.size()).isEqualTo(2);
		Assertions.assertThat(lP.size()).isLessThan(3);
	}
	
	@Test
	@Order(5)
	public void listAll() throws Exception{
		List<ProdottoDTO> lP = prodS.listAll();
		Assertions.assertThat(lP.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(7)
	public void listByIDTest() throws Exception{
		ProdottoDTO lP = prodS.listByID(2);
		Assertions.assertThat(lP.getId() == 2);
		Assertions.assertThat(lP != null);
	}
	
	//----------------------------------------------------TEST MODEL PRODOTTO---------------------------------------------------------
	@Test
    @Order(8)
    public void testGetterSetterProdotto() {
        Prodotto prodotto = new Prodotto();
        
        // Settaggio dei valori
        prodotto.setId(1);
        
        TipoProdotto tipoProdotto = new TipoProdotto();
        tipoProdotto.setId(100);
        tipoProdotto.setDescrizione("Dolce");
        prodotto.setTipo(tipoProdotto);
        
        prodotto.setNome("Torta al cioccolato");
        prodotto.setDescrizione("Deliziosa torta al cioccolato con crema.");
        prodotto.setPeso(1.5);
        prodotto.setPrezzo(20.0);
        prodotto.setStock(10);
        prodotto.setDisponibile(true);

        List<CarrelloProdotto> carrelloProdotti = new ArrayList<>();
        CarrelloProdotto carrelloProdotto = new CarrelloProdotto();
        carrelloProdotto.setId(200);
        carrelloProdotti.add(carrelloProdotto);
        prodotto.setCarrelliProdotti(carrelloProdotti);

        // Verifica dei valori tramite i getter
        assertThat(prodotto.getId()).isEqualTo(1);
        assertThat(prodotto.getTipo()).isEqualTo(tipoProdotto);
        assertThat(prodotto.getNome()).isEqualTo("Torta al cioccolato");
        assertThat(prodotto.getDescrizione()).isEqualTo("Deliziosa torta al cioccolato con crema.");
        assertThat(prodotto.getPeso()).isEqualTo(1.5);
        assertThat(prodotto.getPrezzo()).isEqualTo(20.0);
        assertThat(prodotto.getStock()).isEqualTo(10);
        assertThat(prodotto.getDisponibile()).isTrue();
        assertThat(prodotto.getCarrelliProdotti()).isEqualTo(carrelloProdotti);
    }

}
