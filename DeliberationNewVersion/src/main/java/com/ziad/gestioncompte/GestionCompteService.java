package com.ziad.gestioncompte;

import javax.mail.MessagingException;

import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ziad.models.Confirmator;
import com.ziad.models.Etudiant;
import com.ziad.models.Professeur;
import com.ziad.models.User;
import com.ziad.repositories.ConfirmatorRepository;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.ProfesseurRepository;
import com.ziad.repositories.UserRepository;
import com.ziad.utilities.SendEmailService;
import com.ziad.utilities.beans.ContinueMessage;

@Service
@Primary
public class GestionCompteService implements GestionCompteInterface{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private ProfesseurRepository professeurRepository;
	
	@Autowired
	private EtudiantRepository etudiantRepository;
	
	@Autowired
	private SendEmailService emailSend;
	
	@Autowired
	private ConfirmatorRepository confirmatorRepository;
	
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
			userRepository.save(user);
		}else {
			throw new InvalidCredentialsException("Le mot de passe est different de la confirmation");
		}
	}

	@Override
	public void changeEmail(String email) throws MessagingException {
		String name = "";
		User user = getAccount();
		if(user.isProfesseur()) {
			Professeur prof= professeurRepository.getProfesseurByUser(user);
			name = prof.getNom_professeur() + " " + prof.getPrenom_professeur();
		}else if(user.isStudent()) {
			Etudiant etudiant = etudiantRepository.getEtudiantByUser(user);
			name = etudiant.getLast_name_fr() + " " +  etudiant.getFirst_name_fr();
		}
		Confirmator confirmator = new Confirmator();
		confirmator.setUser(user);
		confirmator.setCode(Confirmator.generateRandomCode());
		confirmator.setEmail(email);
		confirmatorRepository.save(confirmator);
		
		ContinueMessage message = new ContinueMessage(email, "Veuillez confirmer votre email afin de le modifier", "", name, "/gestioncompte/confirmer?id="+confirmator.getIdConfirmator()+"&code="+confirmator.getCode());
		emailSend.sendEmail(message);
	}

	@Override
	public void confirmerEmail(Long idConfirmation, String code) throws InvalidCredentialsException{
		Confirmator confirmation = confirmatorRepository.getOne(idConfirmation);
		if(code.equals(confirmation.getCode())) {
			User user = getAccount();
			if(user.isProfesseur()) {
				Professeur prof= professeurRepository.getProfesseurByUser(user);
				prof.setEmail_professeur(confirmation.getEmail());
				professeurRepository.save(prof);
			}else if(user.isStudent()) {
				Etudiant etudiant = etudiantRepository.getEtudiantByUser(user);
				etudiant.setEmail(confirmation.getEmail());
				etudiant.getUser().setUsername(confirmation.getEmail());
				etudiantRepository.save(etudiant);
			}
		}else {
			throw new InvalidCredentialsException("La confirmation est incorrecte");
		}
		
	}
}
