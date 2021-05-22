package com.ziad.deliberation;

import com.ziad.models.Semestre;

public class DeliberationEtapeNotAllowed extends Exception {

	private static final long serialVersionUID = 1L;
	private Semestre semestre;
	private String msg;

	public DeliberationEtapeNotAllowed(Semestre semestre, String msg) {
		super(msg);
		this.semestre = semestre;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
