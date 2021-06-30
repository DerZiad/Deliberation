package com.ziad.controllers;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.enums.MonRole;
import com.ziad.exceptions.AnonymousException;

@Controller
public class PrincipalControllers {
	public static final String URL_LOGIN = "";
	
	
	
	@RequestMapping("/")
	public ModelAndView filterspaces(HttpServletRequest request) {
		ModelAndView model = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			AnonymousException.isAnonymous(authentication);
			Collection<? extends GrantedAuthority> autorities = authentication.getAuthorities();
			for (MonRole role : MonRole.values()) {
				if (autorities.contains(new SimpleGrantedAuthority("ROLE_" + role.getRole()))) {
					System.out.println("redirect:" + role.getEspace());
					model = new ModelAndView("redirect:" + role.getEspace());
				}
			}
		} catch (AnonymousException e) {
			model = new ModelAndView("home");
		}
		return model;
	}

	@GetMapping("/login")
	public ModelAndView getLoginPage(HttpServletRequest request) {
		ModelAndView model = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			AnonymousException.isAnonymous(authentication);
			model = new ModelAndView("redirect:/");
		} catch (AnonymousException e) {
			model = new ModelAndView("login");
			String err = request.getParameter("error");
			if (err != null && err.equals("true")) {
				model.addObject("err", "Vos identifiants sont incorrects.");
			}
		}
		return model;
	}
	
	@GetMapping("/user/forgotpwd")
	public ModelAndView forgotPwd(HttpServletRequest request) {
		ModelAndView model = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			AnonymousException.isAnonymous(authentication);
			model = new ModelAndView("redirect:/");
		} catch (AnonymousException e) {
			model = new ModelAndView("forgotpwd");
		}
		return model;
	}
	
	@PostMapping("/user/forgotpwd")
	public ModelAndView forgotPwdConfirm(@RequestParam("email")String email) {
		ModelAndView model = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			AnonymousException.isAnonymous(authentication);
			model = new ModelAndView("redirect:/");
		} catch (AnonymousException e) {
			model = new ModelAndView("login");
		}
		return model;
	}
}
