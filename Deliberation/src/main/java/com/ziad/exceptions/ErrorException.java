package com.ziad.exceptions;

public class ErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;

	public ErrorException(String title, String msg) {
		super(msg);
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
