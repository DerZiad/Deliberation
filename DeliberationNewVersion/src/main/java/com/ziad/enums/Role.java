package com.ziad.enums;

public enum Role {
	RESPONSABLE_FILIERE("RESPONSABLE_FILIERE"), RESPONSABLE_MODULE("RESPONSABLE_MODULE"), PROFESSEUR("PROFESSEUR"),
	ETUDIANT("ETUDIANT"),ADMINISTRATEUR("ADMIN");

	private String role;

	private Role(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
}
