package com.betacom.pasticceria.services.interfaces;

import java.util.List;

import com.betacom.pasticceria.dto.FeedbackDTO;
import com.betacom.pasticceria.request.FeedbackReq;

public interface FeedbackService {
    void create(FeedbackReq req) throws Exception;
	void update(FeedbackReq req) throws Exception;
	void delete(FeedbackReq req) throws Exception;
	List<FeedbackDTO> listAll();
	FeedbackDTO listByID (Integer id) throws Exception;
}
