package com.ziad.deliberation;

import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import com.ziad.exceptions.DataNotFoundExceptions;

public interface DeliberationInterface {

	
	public ArrayList<Object> getPageBesoin() throws DataNotFoundExceptions,EntityNotFoundException;
	public void deliberer(Long idFilire,Long idAnneeAcademique,HttpServletRequest request) throws DataNotFoundExceptions,EntityNotFoundException;

}
