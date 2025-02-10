package com.betacom.pasticceria.dto;

import java.util.Date;

import com.betacom.pasticceria.model.Ordine;
import com.betacom.pasticceria.model.Prodotto;
import com.betacom.pasticceria.model.Utente;
import com.betacom.pasticceria.model.Voto;

public class FeedbackDTO {
	private Integer id;
	private Utente utente;
	private Prodotto prodotto;
	private Ordine ordine;
	private String descrizione;
	private Voto voto;
    private Date dataFeedback;
    
}
