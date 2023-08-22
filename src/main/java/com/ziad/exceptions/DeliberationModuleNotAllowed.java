package com.ziad.exceptions;

import com.ziad.models.Modulee;

public class DeliberationModuleNotAllowed extends Exception {

	private Modulee module;
	private static final long serialVersionUID = 1L;

	public DeliberationModuleNotAllowed(Modulee module, String msg) {
		super(msg);
		this.module = module;
	}

	public DeliberationModuleNotAllowed() {

	}

	public Modulee getModule() {
		return module;
	}

	public void setModule(Modulee module) {
		this.module = module;
	}

}
