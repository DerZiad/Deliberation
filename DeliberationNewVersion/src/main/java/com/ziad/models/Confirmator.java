package com.ziad.models;

import java.io.Serializable;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Confirmator")
public class Confirmator implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idConfirmator;

	private String code;

	private String email;

	private String target;

	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST })
	private User user;

	public Confirmator() {

	}

	public Confirmator(Long idConfirmator, String code, String email, String target, User user) {
		this(code, email, target, user);
		this.idConfirmator = idConfirmator;
	}

	public Confirmator(String code, String email, String target, User user) {
		super();
		this.code = code;
		this.email = email;
		this.user = user;
		this.target = target;
	}

	public Long getIdConfirmator() {
		return idConfirmator;
	}

	public void setIdConfirmator(Long idConfirmator) {
		this.idConfirmator = idConfirmator;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public static String generateRandomCode() {
		Random random = new Random();
		String code = "";
		for (int i = 0; i < 6; i++) {
			code = code + random.nextInt(10);
		}
		return code;
	}
}
