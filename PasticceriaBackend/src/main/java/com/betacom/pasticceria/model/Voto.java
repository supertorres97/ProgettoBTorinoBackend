package com.betacom.pasticceria.model;

public enum Voto {
	 UNO(1),
	 DUE(2),
	 TRE(3),
	 QUATTRO(4),
	 CINQUE(5);
	
	private final int valore;
    
	 Voto(int valore) {
	     this.valore = valore;
	 }

	 public int getValore() {
		 return valore;
	 }

}
