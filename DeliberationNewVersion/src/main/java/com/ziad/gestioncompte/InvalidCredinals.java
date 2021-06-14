package com.ziad.gestioncompte;

import java.util.HashMap;

public class InvalidCredinals extends Exception {
	
	private HashMap<String, String> erreurs;
	
	public InvalidCredinals(String msg,HashMap<String, String> erreurs) {
		super(msg);
		this.erreurs = erreurs;
	}
	
	public InvalidCredinals() {
		
	}
	
	
	
}
