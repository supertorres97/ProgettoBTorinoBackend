package com.betacom.pasticceria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Permetti tutte le richieste agli endpoint
                        .allowedOrigins("http://localhost:4200") // Permetti richieste solo da Angular
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Metodi HTTP permessi
                        .allowedHeaders("*"); // Permetti tutti gli header
 //                       .allowCredentials(true); // Permetti cookie/autenticazione
                		
            }
        };
    }
}
