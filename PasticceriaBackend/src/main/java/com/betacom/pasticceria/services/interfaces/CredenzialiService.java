package com.betacom.pasticceria.services.interfaces;

import java.util.List;

import com.betacom.pasticceria.dto.CredenzialiDTO;
import com.betacom.pasticceria.dto.SignInDTO;
import com.betacom.pasticceria.request.CredenzialiReq;
import com.betacom.pasticceria.request.SignInReq;

public interface CredenzialiService {

    void create(CredenzialiReq req) throws Exception;
	void update(CredenzialiReq req) throws Exception;
	void delete(CredenzialiReq req) throws Exception;
	List<CredenzialiDTO> listAll();
	CredenzialiDTO listByID (Integer id) throws Exception;
	SignInDTO signIn(SignInReq req) throws Exception;
	void createAdmin(CredenzialiReq req) throws Exception;
	void removeRole(Integer idCredenziali, String ruoloDaRimuovere) throws Exception;
	void changeRole(Integer idCredenziali, List<String> newRuoli) throws Exception;
    CredenzialiDTO getCredenzialiByUtente(Integer idUtente) throws Exception;
    void updateDaAdmin(CredenzialiReq req) throws Exception;

}




    