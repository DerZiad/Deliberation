package com.ziad.utilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ziad.models.AnneeAcademique;
import com.ziad.repositories.AnnneAcademiqueRepository;
@Service
public class Algorithms {
	
	@Autowired
	private AnnneAcademiqueRepository anneeAcademiqueRepository;
	
	@SuppressWarnings("deprecation")
	public AnneeAcademique grabAnneeAcademiqueActuel() {
	    Date date = new Date();
	    Integer year = java.time.LocalDate.now().getYear();
	    if(date.getMonth() <= 7){
	    	year = year - 1;
	    }
		AnneeAcademique annee = anneeAcademiqueRepository.getAnneeAcademique(year);
		return annee;
	}
	
	public List<AnneeAcademique> getNextFiveYear(){
		AnneeAcademique annee = grabAnneeAcademiqueActuel();
		ArrayList<AnneeAcademique> annees = new ArrayList<AnneeAcademique>();
		annees.add(annee);
		int i = annee.getAnnee_academique() + 1;
		int end = i + 5;
		for(int j = i;j<=end;j++) {
			AnneeAcademique anneeTemp = anneeAcademiqueRepository.getAnneeAcademique(j);
			annees.add(anneeTemp);
		}
		return annees;
	}
	
	public static String convertChaineMajiscule(String chaine) {
		String ch1 = "";
		String ch2 = "";
		int nombre = 0;
		for(int i =0;i<chaine.length();i++) {
			if(nombre == 0) {
				ch1 = (chaine.charAt(i)+"").toUpperCase();
			}else {
				ch2 = ch2 + chaine.charAt(i);
			}
			nombre++;
		}
		return ch1 + ch2.toLowerCase();
	}
	
}
