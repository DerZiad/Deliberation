package com.ziad.exceptions;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;


public class AnonymousException extends Exception {

	/**
	 *  Class si l'utilisateur n'est pas connecté
	 */
	private static final long serialVersionUID = 1L;

	public AnonymousException(String message) {
		super(message);
	}

	public AnonymousException() {

	}
	
	public static void isAnonymous(Authentication authentication) throws AnonymousException {
		if(authentication instanceof AnonymousAuthenticationToken) 
			throw new AnonymousException();
	}
}
