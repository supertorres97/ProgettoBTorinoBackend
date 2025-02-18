package com.betacom.pasticceria.services.interfaces;

import java.util.List;

import com.betacom.pasticceria.dto.DettagliOrdineDTO;
import com.betacom.pasticceria.request.DettagliOrdineReq;

public interface DettagliOrdineService {
	void create(DettagliOrdineReq req) throws Exception;
    void update(DettagliOrdineReq req) throws Exception;
    void delete(DettagliOrdineReq req) throws Exception;
    List<DettagliOrdineDTO> listAll();
    DettagliOrdineDTO listByID(Integer id) throws Exception;
    List<DettagliOrdineDTO> listByOrderID(Integer id) throws Exception;
}
