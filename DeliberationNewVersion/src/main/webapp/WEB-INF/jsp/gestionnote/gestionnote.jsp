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
				<h5 class="card-title">Liste des inscriptions administratives
					des etudiants</h5>
				<form action="/admin/gestionnote" method="POST">
					<div class="col-md-6">
						<div class="position-relative form-group">
							<label for="Filiere" class="">Année universitaire</label> <select
								id="listannee" name="id_annee_academique" id="myInput"
								class="form-control">
								<c:forEach var="annee" items="${anneesAcademiques}">
									<option value="${annee.id_annee_academique }">${annee.annee_academique }</option>
								</c:forEach>

							</select>
						</div>
					</div>
					<div class="col-md-6">
						<div class="position-relative form-group">
							<label for="Filiere" class="">Filiere</label> <select
								id="listfiliere" name="id_filiere" class="form-control">
								<c:forEach var="filiere" items="${filieres}">
									<option value="${filiere.id_filiere }">${filiere.nom_filiere }</option>
								</c:forEach>

							</select>
						</div>
					</div>

					<div class="col-md-6">
						<div class="position-relative form-group">
							<label for="semestre" class="">Etape</label> <select
								id="listsemestre" name="id_etape" id="myInput"
								class="form-control">

							</select>
						</div>

					</div>

					<div class="col-md-6">
						<div class="position-relative form-group">
							<label for="semestre" class="">Semestre</label> <select
								id="listsemestre" name="id_semestre" id="myInput"
								class="form-control">

							</select>
						</div>

					</div>
					<div class="col-md-6">
						<div class="position-relative form-group">
							<label for="module" class="">Module</label> <select
								id="listmodule" name="id_module" id="myInput"
								class="form-control">

							</select>
						</div>

					</div>
					<div class="col-md-6">
						<div class="position-relative form-group">
							<label for="semestre" class="">Element</label> <select
								id="listsemestre" name="id_element" id="myInput"
								class="form-control">

							</select>
						</div>

					</div>
					<div class="col-md-9">
						<div class="position-relative form-group">
							<button class="btn btn-success">Valider</button>
						</div>

					</div>
				</form>

				<table class="mb-0 table table-hover" id="myTable">
					<thead>
						<tr>
							<th class="th-sm">Nom Etudiant</th>
							<th class="th-sm">Element</th>
							<th class="th-sm">Note</th>
							<th class="th-sm">Etat</th>
							<th class="th-sm">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="note" items="${notes}">
							<tr>
								<td><a style="color: black">
										${note.idCompose.etudiant.first_name_fr}
										${note.idCompose.etudiant.last_name_fr}</a></td>
								<td><a style="color: black">
										${note.idCompose.element.libelle_element}</a></td>
								<td><a style="color: black"> ${note.note_element}</a></td>
								<td><a style="color: black"> ${note.etat}</a></td>
								<td><a style="color: black"> ${note.etat}</a></td>
								<td><a class="btn btn-primary"
									href="/admin/gestionnote/edit/${note.idCompose.etudiant.id_etudiant }/${note.idCompose.element.id_element}"> Edit</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<script>
var elements = JSON.parse('${elementsjson}');
var modules = JSON.parse('${modulesjson}');
var semestres = JSON.parse('${semestresjson}');
var etapes = JSON.parse('${etapesjson}');

jQuery(document).ready(function(){
			$('select[name=id_filiere]').change(function(){
				filterEtape();
			});
		
			$('select[name=id_semestre]').change(function(){
				filterModule();
			});
			$('select[name=id_etape]').change(function(){
				filterSemestre();
			});

			$('select[name=id_module]').change(function(){
				filterElement();
			});
			filterEtape();

});

function filterEtape(){
	var filiere = $('select[name=id_filiere]').val();
	var chetape = "";
	for(let i=0;i<etapes.length;i++){
		if(etapes[i].id_filiere == filiere){
			chetape = chetape + '<option value="' + etapes[i].id_etape + '">' + etapes[i].libelle_etape + '</option>';
		}
	}
	$('select[name=id_etape]').html(chetape);
	filterSemestre();
}

function filterModule(){
			
		var semestre = $('select[name=id_semestre]').val();
		var chmodule = "";
		for(let i = 0;i<modules.length;i++){
				if(modules[i].id_semestre == semestre){
					chmodule = chmodule + '<option value="' + modules[i].id_module + '">' + modules[i].libelle_module + '</option>';
				}
		}
		$('select[name=id_module]').html(chmodule);
		filterElement();
}
		
function filterSemestre(){
			var etape = $('select[name=id_etape]').val();
			
			var chsemestre = "";
			for(let i = 0;i<semestres.length;i++){
					if(semestres[i].id_etape == etape){
						chsemestre = chsemestre + '<option value="' + semestres[i].id_semestre +'">' + semestres[i].libelle_semestre + '</option>';
					}
			}
			$('select[name=id_semestre]').html(chsemestre);
			filterModule();
}

function filterElement(){
			
			var modulee = $('select[name=id_module]').val();
			var chelement = "";
			for(let i = 0;i<elements.length;i++){
				if(elements[i].id_module == modulee){
					chelement = chelement + '<option value="' + elements[i].id_element + '">' + elements[i].libelle_element + '</option>';
				}
			}
			$('select[name=id_element]').html(chelement);
}
		</script>
	</layout:put>
</layout:extends>