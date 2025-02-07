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

		/* Dotenv dotenv = Dotenv.configure().directory("C:/Users/messa/Desktop/archivio eclipse/ProgettoBTorinoBackend/PasticceriaBackend").filename("Variabili_ambiente.env").load();
        System.setProperty("db_url", dotenv.get("db_url"));
        System.setProperty("db_user", dotenv.get("db_user"));
        System.setProperty("db_pwd", dotenv.get("db_pwd")); */


		SpringApplication.run(PasticceriaBackendApplication.class, args);
	}
	@Bean					//logger definition
	@Scope("prototype")
	Logger logger(InjectionPoint ip) {
		return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
	}
}
