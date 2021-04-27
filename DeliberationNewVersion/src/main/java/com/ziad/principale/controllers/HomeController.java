package com.ziad.principale.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.exceptions.AnonymousException;

@Controller
@RequestMapping("/admin")
public class HomeController {
		
	@GetMapping("")
	public ModelAndView getAdministrateurHomePage() throws AnonymousException{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication instanceof AnonymousAuthenticationToken) throw new AnonymousException("Acces Denied");
		System.out.println("I m here");
		ModelAndView model = new ModelAndView("index-admin");
		model.addObject("dashboard", "mm-active");
		model.addObject("message", "HELLO WORLD");
		return model;
	}
}
