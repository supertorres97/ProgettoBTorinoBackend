package com.betacom.pasticceria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class PasticceriaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasticceriaBackendApplication.class, args);
	}

	@Bean	
	@Scope("prototype")		
	Logger logger (InjectionPoint ip) {
		return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
	}
}
