package com.betacom.pasticceria;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.pasticceria.dto.CarrelloDTO;
import com.betacom.pasticceria.dto.UtenteDTO;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.services.interfaces.CarrelloService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarrelloTest {

	@Autowired
	CarrelloService cartS;
	
	@Test
	@Order(1)
	public void cartCreateErrors() throws Exception{
		Utente u = new Utente();
		
		u.setId(50);
		assertThrows(Exception.class, () -> {cartS.create(u);});
		
		u.setId(1);
		assertThrows(Exception.class, () -> {cartS.create(u);});
	}
	
	@Test
	@Order(2)
	public void listAll() throws Exception{
		List<CarrelloDTO> lC = cartS.listAll();
		Assertions.assertThat(lC.size()).isGreaterThan(0);
//		Assertions.assertThat(lP.size()).isEqualTo(3);
	}
	
}
