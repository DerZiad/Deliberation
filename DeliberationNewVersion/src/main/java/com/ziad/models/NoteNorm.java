package com.ziad.models;

public interface NoteNorm {

	public Long getIdStudent();
	
	public Double getNote();

	public void setNote(Double note);

	public boolean isValid();

	public void setValid(boolean isValid);

	public String getEtat();

	public void setEtat(String etat);
	
	public void calculState();
	
	public Etudiant getEtudiant();
	
	public ElementNorm getElement();

}
