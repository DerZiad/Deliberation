<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<layout:extends name="../layout.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Création d'un module</h5>
				<form class="" action="/admin/module/creer" method="POST">
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="name" class="">Nom du module</label><input
									name="name" id="name" placeholder="Nom du module" type="text"
									class="form-control">
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="etablissement_id" class="">Etablissement</label> <select
									name="etablissement_id" id="exampleSelect" class="form-control">
								</select>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="filiere_id" class="">Filiere</label> <select
									name="filiere_id" id="exampleSelect" class="form-control">
								</select>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="etape_id" class="">Etape</label> <select
									name="etape_id" id="exampleSelect" class="form-control">
								</select>
							</div>
						</div>
					</div>

					<div class="form-row">
						<div class="position-relative form-group">
							<label for="semestre_id" class="">Semestre</label> <select
								name="semestre_id" id="exampleSelect" class="form-control">
							</select>
						</div>
					</div>

					<div class="form-row">
						<div class="col-md-12">
							<div class="position-relative form-group">
								<label for="professeur_id" class="">Professeur
									résponsable:</label> <select name="professeur_id" id="exampleSelect"
									class="form-control">
									<c:forEach var="professeur" items="${professeurs }">
										<option value="${professeur.id_professeur}">
											${professeur.nom_professeur } ${professeur.prenom_professeur}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-12">
							<div class="position-relative form-group">
								<div class="form-check">
									<input type="checkbox" class="form-check-input"
										id="composotionmodule" name="checktheelement" value="1">
									<label class="form-check-label" for="composotionmodule">Module
										se compose d'un seul element</label>
								</div>
							</div>
						</div>
					</div>
					<button class="mt-2 btn btn-primary col-md-6" type="submit">Valider</button>
				</form>
			</div>
		</div>
		<script>
var semestres = JSON.parse('${semestresjson}');
var etapes = JSON.parse('${etapesjson}');
var filieres = JSON.parse('${filieresjson}');
var etablissement = JSON.parse('${etablissementjson}');
jQuery(document).ready(function(){
	$('select[name=etablissement_id]').change(function(){
		filterFiliere();
	});
	$('select[name=filiere_id]').change(function(){
		filterEtape();
	});
	$('select[name=etape_id]').change(function(){
		filterSemestre();
	});
	
	filterEtablissement();
});

function filterSemestre(){
	var id_etape = $('select[name=etape_id]').val();
	var chsemestre = "";
	for(let i=0;i<semestres.length;i++){
		if(semestres[i].id_etape == id_etape){
			chsemestre = chsemestre + '<option value="' + semestres[i].id_semestre + '">' + semestres[i].libelle_semestre + '</option>';
		}
	}

	$('select[name=semestre_id').html(chsemestre);
}

function filterEtape(){
	var id_filiere = $('select[name=filiere_id]').val();
	var chetape = "";
	for (var i =0; i < etapes.length;i++){
		if(etapes[i].id_filiere == id_filiere){
			chetape = chetape + '<option value="' + etapes[i].id_etape + '">' + etapes[i].libelle_etape + '</option>';
		}
	}
	$('select[name=etape_id').html(chetape);
	filterSemestre();
}

function filterFiliere(){
	var id_etablissement = $('select[name=etablissement_id]').val();
	var chfiliere = "";
	for (var i =0; i < filieres.length;i++){
		if(filieres[i].id_etablissement == id_etablissement){
			chfiliere = chfiliere + '<option value="' + filieres[i].id_filiere + '">' + filieres[i].libelle_filiere + '</option>';
		}
	}
	$('select[name=filiere_id').html(chfiliere);
	filterEtape();
}

function filterEtablissement(){
	var chetablissement = "";
	for (var i =0; i < etablissement.length;i++){
		chetablissement = chetablissement + '<option value="' + etablissement[i].id_etablissement + '">' + etablissement[i].nom_etablissement + '</option>';
	}
	$('select[name=etablissement_id').html(chetablissement);
	filterFiliere();
}

		
	</script>

	</layout:put>
</layout:extends>