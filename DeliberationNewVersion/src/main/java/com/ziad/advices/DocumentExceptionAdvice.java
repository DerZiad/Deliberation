package com.ziad.advices;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DocumentExceptionAdvice {

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView downloadDocumentError(MissingServletRequestParameterException exception) {
		ModelAndView model = new ModelAndView("403");
		model.addObject("exception", "Erreur dans le telechargement, ressayer plut tard");
		return model;
	}

}
