<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<layout:extends name="../layout.jsp">
	<layout:put block="content" type="REPLACE">
		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Liste des inscriptions pédagogiques</h5>
				<form action="/admin/inscription/ListInscriptionAdministrative"
					method="POST">
					<div class="col-md-6">
						<div class="position-relative form-group">
							<input type="checkbox" class="form-class" name="checkfiliere"
								checked /> <label for="Filiere" class="">Filiere</label> <select
								id="listfiliere" name="id_filiere" class="form-control">
								<c:forEach var="filiere" items="${filieres}">
									<option value="${filiere.id_filiere }">${filiere.nom_filiere }</option>
								</c:forEach>

							</select>
						</div>
					</div>
					<div class="col-md-6">
						<div class="position-relative form-group">
							<input type="checkbox" class="form-class" name="checkannee"
								checked /> <label for="Filiere" class="">Année
								universitaire</label> <select id="listannee" name="id_annee_academique"
								id="myInput" class="form-control">
								<c:forEach var="annee" items="${annees_academiques}">
									<option value="${annee.id_annee_academique }">${annee.annee_academique }</option>
								</c:forEach>

							</select>
						</div>
					</div>
					<div class="col-md-6">
						<div class="position-relative form-group">
							<input type="checkbox" class="form-class" name="checksemestre"
								checked /> <label for="semestre" class="">Semestre</label> <select
								id="listsemestre" name="id_semestre" id="myInput"
								class="form-control">

							</select>
						</div>

					</div>
					<div class="col-md-6">
						<div class="position-relative form-group">
							<input type="checkbox" class="form-class" name="checkmodule"
								checked /> <label for="module" class="">Module</label> <select
								id="listmodule" name="id_module" id="myInput"
								class="form-control">

							</select>
						</div>

					</div>
					<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="typedeliberation" class="">Type de
									inscriptions</label>
								<div class="row">
									<div class="col-md-4">
										<input type="radio" id="semestre" name="typeinscription"
											value="semestre" checked>
									</div>
									<div class="col-md-4">
										<label for="box">Inscription par semestre</label>
									</div>

								</div>
								<div class="row">
									<div class="col-md-4">
										<input type="radio" id="module" name="typeinscription"
											value="module">
									</div>
									<div class="col-md-4">
										<label for="box">Inscription par module</label>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4">
										<input type="radio" id="element" name="typeinscription"
											value="element" checked>
									</div>
									<div class="col-md-4">
										<label for="box">Inscription par element</label>
									</div>

								</div>
							</div>
						</div>
					<div class="col-md-9">
						<div class="position-relative form-group">
							<button class="btn btn-success">Valider</button>
						</div>

					</div>
				</form>

				<table class="mb-0 table table-striped">
					<thead>
						<tr>
							<th>Element</th>
							<th>Année</th>
							<th>Module</th>
							<th>Semestre</th>
							<th>Etape</th>
							<th>Filiere</th>
							<th>Etablissement</th>
							<th>Type Inscription</th>
							<th>Action</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="inscription" items="${inscriptions}">
							<tr>
								<td>${inscription.element.libelle_element}</td>
								<td>${inscription.annee_academique }</td>
								<td>${inscription.id_inscription_pedagogique.element.module.libelle_module}</td>
								<td>${inscription.id_inscription_pedagogique.element.module.semestre.libelle_semestre }</td>
								<td>${inscription.id_inscription_pedagogique.element.module.semestre.etape.libelle_etape }</td>
								<td>${inscription.id_inscription_pedagogique.element.module.semestre.etape.filiere.nom_filiere }</td>
								<td>${inscription.id_inscription_pedagogique.element.module.semestre.etape.filiere.etablissement.nom_etablissement }</td>
								<td>${inscription.type_inscription }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<script>
var modules = JSON.parse('${modulesjson}');
var semestres = JSON.parse('${semestresjson}');
		
jQuery(document).ready(function(){
			$('select[name=id_filiere]').change(function(){
				filiterSemestre();
			});
		
			$('select[name=id_semestre]').change(function(){
				filterModule();
			});
			

			$("input[name=checkfiliere]").click(function(){
				enable_cb($("input[name=checkfiliere]"),'select[id=listfiliere]',"id_filiere ");		
				if ($('input[name=checkfiliere]').is(':checked')) {
					$('select[id=listfiliere]').attr("name","id_filiere");
					$('select[id=listfiliere]').removeAttr("disabled");
					filiterSemestre();
				} else {
					$(select).attr("disabled", true);
					$(select).attr("name","auto");
					filiterSemestre();
				}
			});
		
			$("input[name=checkannee]").click(function(){
				enable_cb($('input[name=checkannee]'),"select[id=listannee]","id_annee_academique");
			});
		
			$("input[name=checksemestre]").click(function(){
				enable_cb($('input[name=checksemestre]'),"select[id=listsemestre]","id_semestre",filterModule());
			});
		
			$("input[name=checkmodule]").click(function(){
				enable_cb($('input[name=checkmodule]'),"select[id=listmodule]","id_module");
			});
			filiterSemestre();
});

function enable_cb(checkboxobject,select,oldname,functionto) {
	if (checkboxobject.is(':checked')) {
		$(select).attr("name",oldname);
		$(select).removeAttr("disabled");
		functionto();
	} else {
		$(select).attr("disabled", true);
		$(select).attr("name","auto");
		functionto();
	}
}

function filterModule(){
			
			var semestre = $('select[name=id_semestre]').val();
			var filiere = $('select[name=id_filiere]').val();
			
			var chmodule = "";
			var check  = false;
			for(let i = 0;i<modules.length;i++){
				check  = false;
				
				if($("input[name=checkfiliere]").is(':checked')){
					if(modules[i].id_filiere == filiere){
						check = true;
					}
				}
				
				if($("input[name=checksemestre]").is(':checked')){
					check = false;
					if(modules[i].id_semestre == semestre){
						check = true;
					}
				}
				

				
				if(check){
					chmodule = chmodule + '<option value="' + modules[i].id_module + '">' + modules[i].libelle_module + '</option>';
				}
			}
			$('select[name=id_module]').html(chmodule);
}
		
function filiterSemestre(){
			var filiere = $('select[name=id_filiere]').val();
			
			var chsemestre = "";
			for(let i = 0;i<semestres.length;i++){
				if($("input[name=checkfiliere]").is(':checked')){
					if(semestres[i].id_filiere == filiere){
						chsemestre = chsemestre + '<option value="' + semestres[i].id_semestre +'">' + semestres[i].libelle_semestre + '</option>';
					}
				}
			}
			$('select[name=id_semestre]').html(chsemestre);
			filterModule();
}
		</script>
	</layout:put>
</layout:extends>