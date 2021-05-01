package com.ziad.professeurespace;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Element;
import com.ziad.models.Etudiant;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.ElementRepository;
import com.ziad.repositories.InscriptionPedagogiqueRepository;
import com.ziad.utilities.ExcelExport;
import com.ziad.utilities.JSONConverter;

@Service
@Primary
public class ProfesseurService implements ProfesseurInterface {
	@Autowired
	private AnnneAcademiqueRepository anneeAcademiqueRepository;
	@Autowired
	private ElementRepository elementRepository;
	@Autowired
	private InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository;
	@Autowired
	private JSONConverter converter;
	
	private ExcelExport generator_excel;
	
	public List<Element> listerElements() throws DataNotFoundExceptions {
		List<Element> elements = elementRepository.findAll();
		if (elements.size() == 0)
			throw new DataNotFoundExceptions("Vous n'avez pas encore une liste des élements");
		return elements;
	}

	public ArrayList<Object> listerEtudiants(Long id_element) throws DataNotFoundExceptions, EntityNotFoundException {
		Element element = elementRepository.getOne(id_element);
		List<InscriptionPedagogique> inscriptions_pedagogiques = inscriptionPedagogiqueRepository
				.getInscriptionsPedagogiqueByElement(element);
		if (inscriptions_pedagogiques.size() == 0)
			throw new DataNotFoundExceptions("La liste des étudiants est vide");
		ArrayList<Object> besoins = new ArrayList<Object>();
		besoins.add(inscriptions_pedagogiques);
		besoins.add(converter.convertInscriptionsPedagogiques(inscriptions_pedagogiques));
		besoins.add(anneeAcademiqueRepository.findAll());
		besoins.add(converter.convertAnneesAcademiques(anneeAcademiqueRepository.findAll()));
		besoins.add(element);
		return besoins;
	}

	@Override
	public void generateExcel(Long id_element, Long id_annee, String type,HttpServletResponse response) throws EntityNotFoundException,IOException {
		Element element = elementRepository.getOne(id_element);
		AnneeAcademique annee = anneeAcademiqueRepository.getOne(id_annee);
		List<Etudiant> etudiants = inscriptionPedagogiqueRepository.getEtudiantsByElementAndAnneeAcademique(element, annee);
		generator_excel = new ExcelExport(etudiants, type,element.getLibelle_element(),element.getId_element());
		generator_excel.export(response);
		
	}
	
	

}
