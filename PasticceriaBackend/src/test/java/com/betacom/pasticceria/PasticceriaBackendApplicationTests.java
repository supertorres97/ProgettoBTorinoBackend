package com.betacom.pasticceria;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@Suite
@SelectClasses({
	MessaggioTest.class,
	RuoliTest.class,
	UtenteTest.class,
	CredenzialiTest.class,
	CarrelloTest.class,
	TipoProdottoTest.class,
	ProdottoTest.class,
	OrdineTest.class,
	DettagliOrdineTest.class,
	CarrelloProdottoTest.class,
	FeedbackTest.class
	
})

@SpringBootTest
class PasticceriaBackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
