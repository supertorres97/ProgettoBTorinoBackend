package com.betacom.pasticceria;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@Suite
@SelectClasses({
	TipoProdottoTest.class,
	ProdottoTest.class,
	UtenteTest.class,
	CredenzialiTest.class,
	CarrelloTest.class,
	OrdineTest.class,
	DettagliOrdineTest.class,
	FeedbackTest.class,
	RuoliTest.class,
	CarrelloProdottoTest.class
})

@SpringBootTest
class PasticceriaBackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
