package com.ziad.gestioncompte;

import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ziad.models.User;
import com.ziad.repositories.UserRepository;

@Service
@Primary
public class GestionCompteService implements GestionCompteInterface{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public User getAccount() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userRepository.getUserByUsername(username);
		return user;
	}

	@Override
	public void changePassword(String password, String cfpassword) throws InvalidCredentialsException{
		User user = getAccount();
		if(password.equals(cfpassword)) {
			user.setPassword(encoder.encode(password));
		}else {
			throw new InvalidCredentialsException("Le mot de passe est different de la confirmation");
		}
	}

}
