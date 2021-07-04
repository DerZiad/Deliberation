package com.ziad.exceptions;

import com.ziad.models.Element;

public class DeliberationElementNotAllowed extends Exception {

	private static final long serialVersionUID = 1L;
	private Element element;

	public DeliberationElementNotAllowed(Element element, String msg) {
		super(msg);
		this.element = element;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

}
