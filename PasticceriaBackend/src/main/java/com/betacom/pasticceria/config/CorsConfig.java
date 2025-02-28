package com.betacom.pasticceria.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class CorsConfig implements Filter {
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        // Imposta le CORS sempre
        res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        res.setHeader("Access-Control-Allow-Credentials", "true");

        try {
            chain.doFilter(request, response);
        } catch (MaxUploadSizeExceededException e) {
            // Gestione specifica per file troppo grande
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.setContentType("application/json");
            res.getWriter().write("{\"message\":\"Il file è troppo pesante. Carica un'immagine inferiore a 2MB.\"}");
            res.getWriter().flush();
        } catch (Exception e) {
            // Altri errori non gestiti
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res.setContentType("application/json");
            res.getWriter().write("{\"message\":\"Errore interno nel server.\"}");
            res.getWriter().flush();
        }
    }
}
