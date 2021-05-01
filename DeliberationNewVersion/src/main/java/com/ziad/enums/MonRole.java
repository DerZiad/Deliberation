package com.ziad.enums;

/**
 * 
 * @author Ziad Bougrine
 *
 */
public enum MonRole {
	ROLEADMIN("ADMIN", "/admin"), ROLEPROFESSEUR("PROF", "/professeur"),
	ROLERESPONSABLEFILIERE("RESPONSABLEFILIERE", "/professeur/responsablefiliere"),
	ROLERESPONSABLEMODULE("RESPONSABLEMODULE", "/professeur/responsablemodule"), ROLEETUDIANT("ETUDIANT", "/etudiant");

	private String role;
	private String espace;

	private MonRole(String role, String espace) {
		this.role = role;
		this.espace = espace;
	}

	public String getEspace() {
		return espace;
	}

	public String getRole() {
		return role;
	}
}
