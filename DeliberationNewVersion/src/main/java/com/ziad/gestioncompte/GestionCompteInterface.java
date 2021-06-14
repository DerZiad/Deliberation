package com.ziad.gestioncompte;

import javax.persistence.EntityNotFoundException;

import org.apache.http.auth.InvalidCredentialsException;

import com.ziad.models.User;

public interface GestionCompteInterface {

	public User getAccount();
	
	public void changePassword(String password, String cfpassword) throws InvalidCredentialsException;
}
