package com.ziad.administrateur.etablissement;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DataNotFoundAdvice {
	@ExceptionHandler(DataNotFoundExceptions.class)
	public ModelAndView surveillerAnonymousAcess(DataNotFoundExceptions exception){
		ModelAndView model = new ModelAndView("403");
		model.addObject("exception", "Sorry No Data found");
		return model;
	}
}
