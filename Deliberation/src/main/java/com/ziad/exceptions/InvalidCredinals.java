package com.ziad.exceptions;

import java.util.HashMap;

public class InvalidCredinals extends Exception {

	/**
	 * 
	 */

	private HashMap<String, String> erreurs = new HashMap<String, String>();
	private static final long serialVersionUID = 1L;

	public InvalidCredinals(String msg) {
		super(msg);
	}

	public InvalidCredinals() {

	}

	public HashMap<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(HashMap<String, String> erreurs) {
		this.erreurs = erreurs;
	}
	
	public String getErreur(String key) {
		return erreurs.get(key);
	}
	
	public void addErreur(String key,String value) {
		erreurs.put(key, value);
	}
	
	public boolean allow() {
		return erreurs.size() == 0;
	}
	
	public String toString() {
		String erreurs = "";
		for (String key : this.erreurs.keySet()) {
			erreurs = erreurs + key + " " + this.erreurs.get(key) + "\n";
		}
		return erreurs;
	}
	
}
