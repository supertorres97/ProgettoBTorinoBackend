package com.betacom.pasticceria.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.betacom.pasticceria.dto.OrdineDTO;
import com.betacom.pasticceria.dto.ProdottoDTO;
import com.betacom.pasticceria.dto.TipoProdottoDTO;
import com.betacom.pasticceria.dto.UtenteDTO;
import com.betacom.pasticceria.model.Ordine;
import com.betacom.pasticceria.model.Prodotto;
import com.betacom.pasticceria.model.Status;
import com.betacom.pasticceria.model.TipoProdotto;
import com.betacom.pasticceria.model.Utente;

public class Utilities {
	private final static String PATTERN_DATE = "dd/MM/yyyy";
	
	public static Date convertStringToDate(String dataString) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATE, Locale.ITALY);
		return formatter.parse(dataString);
	}
	
	public static TipoProdottoDTO buildTipoProdottoDTO(TipoProdotto tP){	
		return new TipoProdottoDTO.Builder()
				.setId(tP.getId())
				.setDescrizione(tP.getDescrizione()).build();
	}
	
	public static UtenteDTO buildUtenteDTO(Utente u){	
		return new UtenteDTO.Builder()
				.setCAP(u.getCAP())
				.setcFiscale(u.getcFiscale())
				.setCitta(u.getCitta())
				.setCognome(u.getCognome())
				.setNome(u.getNome())
				.setEmail(u.getEmail())
				.setId(u.getId())
				.setVia(u.getVia())
				.build();
	}
	
	public static OrdineDTO buildOrdineDTO(Ordine o){
		return new OrdineDTO.Builder()
				.setId(o.getId())
				.setUtente(buildUtenteDTO(o.getUtente()))
				.setIndirizzo(o.getIndirizzo())
				.setStatus(o.getStatus().toString())
				.setTotale(o.getTotale())
				.setDataOrdine(o.getDataOrdine())
				.build();
	}
	
	public static ProdottoDTO buildProdottoDTO(Prodotto p){
		return new ProdottoDTO.Builder()
				.setId(p.getId())
				.setDescrizione(p.getDescrizione())
				.setNome(p.getNome())
				.setPeso(p.getPeso())
				.setPrezzo(p.getPrezzo())
				.setStock(p.getStock())
				.setTipo(buildTipoProdottoDTO(p.getTipo()))
				.setDisponibile(p.getDisponibile())
				.build();
	}

	
}
