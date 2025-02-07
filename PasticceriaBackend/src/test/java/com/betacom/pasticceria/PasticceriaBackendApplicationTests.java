package com.betacom.pasticceria;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@Suite
@SelectClasses({
	TipoProdottoTest.class
})

@SpringBootTest
class PasticceriaBackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
