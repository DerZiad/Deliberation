package com.ziad.advices;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
/*
@ControllerAdvice
public class IOExceptionAdvice {
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView ioexeptionDetect(MissingServletRequestParameterException exception) {
		ModelAndView model = new ModelAndView("403");
		model.addObject("exception", "Erreur du serveur,ressayer plut tard");
		return model;
	}
}
*/