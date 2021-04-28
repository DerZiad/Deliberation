package com.ziad.professeurespace;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class ProfesseurController {
	
	public ModelAndView listerEtudiants(@PathVariable("id_professeur")Long id_professseur) {
		ModelAndView model = new ModelAndView("");
		
		return model;
	}
}
