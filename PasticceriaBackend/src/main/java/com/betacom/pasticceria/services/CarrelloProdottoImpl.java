package com.betacom.pasticceria.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.pasticceria.repositories.CarrelloProdottoRepository;
import com.betacom.pasticceria.services.interfaces.CarrelloProdottoService;

@Service
public class CarrelloProdottoImpl implements CarrelloProdottoService{
	
	private CarrelloProdottoRepository crR;
	private Logger log;
	
	@Autowired
	public CarrelloProdottoImpl(CarrelloProdottoRepository crR, Logger log) {
		this.crR = crR;
		this.log = log;
	}
	
	
}
