package com.ziad.advices;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.administrateur.etablissement.DataNotFoundExceptions;

@ControllerAdvice
public class DataNotFoundAdvice {
	@ExceptionHandler(DataNotFoundExceptions.class)
	public ModelAndView surveillerAnonymousAcess(DataNotFoundExceptions exception){
		ModelAndView model = new ModelAndView("403");
		model.addObject("exception", "Sorry No Data found");
		return model;
	}
}
