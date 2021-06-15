package com.ziad.gestioncompte;

import javax.mail.MessagingException;

import org.apache.http.auth.InvalidCredentialsException;

import com.ziad.models.User;

public interface GestionCompteInterface {

	public User getAccount();
	
	public void changeEmail(String email) throws MessagingException;
	
	public void confirmerEmail(Long idConfirmation,String code) throws InvalidCredentialsException;
	
	public void changePassword(String password, String cfpassword) throws InvalidCredentialsException;

}
