package com.ziad.advices;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
/*
@ControllerAdvice
public class FluxDonneeAdvice {
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView surveillerInvalidDatas(MissingServletRequestParameterException exception) {
		ModelAndView model = new ModelAndView("403");
		model.addObject("exception", "Erreur du Serveur , veuillez ressayer plus tard");
		return model;
	}
}
*/