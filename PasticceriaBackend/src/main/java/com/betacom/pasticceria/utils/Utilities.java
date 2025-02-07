package com.betacom.pasticceria.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.betacom.pasticceria.dto.TipoProdottoDTO;
import com.betacom.pasticceria.model.TipoProdotto;

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
	
	

	
}
