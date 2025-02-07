package com.betacom.pasticceria.services.implementations;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.pasticceria.dto.DettagliOrdineDTO;
import com.betacom.pasticceria.model.Ordine;
import com.betacom.pasticceria.repositories.DettagliOrdineRepository;
import com.betacom.pasticceria.request.DettagliOrdineReq;
import com.betacom.pasticceria.services.interfaces.DettagliOrdineService;

@Service
public class DettagliOrdineImpl implements DettagliOrdineService{

	DettagliOrdineRepository detR;
	Logger log;
	
	@Autowired
	public DettagliOrdineImpl(DettagliOrdineRepository detR, Logger log) {
		super();
		this.detR = detR;
		this.log = log;
	}

	@Override
	public void create(DettagliOrdineReq req) throws Exception {
		//Optional<Ordine> ord =  
		
	}

	@Override
	public List<DettagliOrdineDTO> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DettagliOrdineDTO listByID(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
