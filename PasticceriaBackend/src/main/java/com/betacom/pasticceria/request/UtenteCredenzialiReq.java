package com.betacom.pasticceria.request;

public class UtenteCredenzialiReq {
	private UtenteReq utenteReq;
	private CredenzialiReq credenzialiReq;
	
	public UtenteReq getUtenteReq() {
		return utenteReq;
	}
	
	public void setUtenteReq(UtenteReq utenteReq) {
		this.utenteReq = utenteReq;
	}
	
	public CredenzialiReq getCredenzialiReq() {
		return credenzialiReq;
	}
	
	public void setCredenzialiReq(CredenzialiReq credenzialiReq) {
		this.credenzialiReq = credenzialiReq;
	}
	
	@Override
	public String toString() {
		return "UtenteCredenzialiReq [utenteReq=" + utenteReq + "]";
	}
}
