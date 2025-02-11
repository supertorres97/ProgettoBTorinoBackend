package com.betacom.pasticceria;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.pasticceria.model.Prodotto;

@Suite
@SelectClasses({
	TipoProdottoTest.class,
	ProdottoTest.class
})

@SpringBootTest
class PasticceriaBackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
