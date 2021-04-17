package com.ziad.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.principale.controllers.AnonymousException;

@ControllerAdvice
public class AnonymousAcces {
	@ExceptionHandler(AnonymousException.class)
	public ModelAndView surveillerAnonymousAcess(AnonymousException anonymous){
		ModelAndView model = new ModelAndView("403");
		model.addObject("exception", anonymous.getMessage());
		return model;
	}
}
