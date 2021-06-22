package com.ziad.exceptions;

import com.ziad.models.Modulee;

public class DeliberationSemestreNotAllowed extends Exception {

	private Modulee module;
	private static final long serialVersionUID = 1L;

	public DeliberationSemestreNotAllowed(Modulee module, String msg) {
		super(msg);
		this.module = module;
	}

	public Modulee getModule() {
		return module;
	}

	public void setModule(Modulee module) {
		this.module = module;
	}

}
