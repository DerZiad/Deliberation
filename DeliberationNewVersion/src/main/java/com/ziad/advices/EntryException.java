package com.ziad.advices;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class EntryException {
	@ExceptionHandler(EntityNotFoundException.class)
	public ModelAndView surveillerInvalidDatas(EntityNotFoundException exception) {
		ModelAndView model = new ModelAndView("403");
		model.addObject("exception", "Veuillez ne pas modifier l'id");
		return model;
	}
}