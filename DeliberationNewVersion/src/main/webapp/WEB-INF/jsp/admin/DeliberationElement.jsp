<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<layout:extends name="${mero }">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Déliberation d'element</h5>
				<form class="" action="/delib/deliberationelement" method="POST"
					enctype="multipart/form-data">
					<input type="hidden" name="type" value="parmodule" />
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<input type="hidden" name="typedeliberation" value="ORDINAIRE" />
								<label for="name" class="">Année académique</label> <select
									name="annee" id="exampleSelect" class="form-control" required>
									<option value="${annee.id_annee_academique }">${annee}</option>
								</select>
							</div>
						</div>
						<c:if
							test="${utilisateur.isResponsableFiliere() or utilisateur.isAdministrator()}">
							<div class="col-md-6">
								<div class="position-relative form-group">

									<label for="filiere" class="">Filiere</label> <select
										name="filiere" id="exampleSelect" class="form-control"
										required>
										<c:forEach var="filiere" items="${filieres}">
											<option value="${filiere.id_filiere }">${filiere.nom_filiere}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="col-md-6">
								<div class="position-relative form-group">
									<label for="element-class" class="element-class">Semestre</label>
									<select name=semestre id="exampleSelect" class="form-control"
										required>
										<c:forEach items="${semestres }" var="semestre">
											<option value="${semestre.id_semestre }">${semestre.libelle_semestre }</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</c:if>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="element-class" class="element-class">Module</label>
								<select name="module" id="exampleSelect" class="form-control"
									required>
								</select>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="element-class" class="element-class">Element</label>
								<select name="element" id="exampleSelect" class="form-control"
									required>
								</select>
							</div>
						</div>

						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="element-class" class="element-class">Upload
									notes</label> <input type="file" name="file" id="exampleSelect"
									class="form-control">
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="element-class" class="element-class">Coefficient
									Controle </label> <input type="number" name="coefficientControl"
									id="exampleSelect" class="form-control">
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="element-class" class="element-class">Coefficient
									Tp </label> <input type="number" name="coefficientTp"
									id="exampleSelect" class="form-control">
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="element-class" class="element-class">Coefficient
									Exam </label> <input type="number" name="coefficentExam"
									id="exampleSelect" class="form-control">
							</div>
						</div>
						<button formmethod="GET" formaction="/delib/downloadordinaire"
							class="mt-2 btn btn-primary col-md-12" type="submit">Download</button>
						<button class="mt-2 btn btn-primary col-md-12" type="submit">Valider</button>
					</div>
				</form>
				<c:if test="${error ne null }">
					<br>
					<br>
					<div class="card alert alert-danger" role="alert">
						<div class="card-body">
							<h5 class="card-title">${error.getMessage() }</h5>
							<c:forEach var="iter" items="${error.erreurs}">
								<p class="card-text">${iter.key} ${iter.value }</p>
							</c:forEach>

						</div>
					</div>
				</c:if>
			</div>
		</div>
		<div class="main-card mb-3">
			<div class="card-body">
				<h5 class="card-title">Liste des étudiants</h5>
				<table class="mb-0 table table-striped">
					<thead>
						<tr>
							<th>Massar</th>
							<th>Nom</th>
							<th>Prenom</th>
						</tr>
					</thead>
					<tbody id="ds">

					</tbody>
				</table>
			</div>
		</div>
		<script>
var modules = JSON.parse('${modulesjson}');
var elements = JSON.parse('${elementsjson}');
var semestres = JSON.parse('${semestresjson}');

jQuery(document).ready(function(){
	$('select[name=filiere]').change(function(){
		filterSemestre();
		changeEtudiants();
	});

	$('select[name=semestre]').change(function(){
		filterModule();
		changeEtudiants();
	});
	
	$('select[name=module]').change(function(){
		filterElement();
		changeEtudiants();
	});
	
	filterSemestre();
	changeEtudiants();
});

function filterModule(){
	
	var semestre = $('select[name=semestre]').val();
	
	
	<c:if test="${utilisateur.isResponsableModule()}">
		var chmodule = "";
		for(let i = 0;i<modules.length;i++){
				chmodule = chmodule + '<option value="' + modules[i].id_module + '">' + modules[i].libelle_module + '</option>';
		}
		
	</c:if>
	
	<c:if test="${not utilisateur.isResponsableModule()}">
			var chmodule = "";
			
			for(let i = 0;i<modules.length;i++){
				if(modules[i].id_semestre == semestre){
					chmodule = chmodule + '<option value="' + modules[i].id_module + '">' + modules[i].libelle_module + '</option>';
				}
			}
	</c:if>
	$('select[name=module]').html(chmodule);
	filterElement();

}

function filterElement(){
	var  chelement = "";
	var selectedModule = $('select[name=module]').val();
	for(let i = 0;i<elements.length;i++){
		if(elements[i].id_module == selectedModule){
					chelement = chelement + '<option value="' + elements[i].id_element + '">' + elements[i].libelle_element + '</option>';
		}
	}
	$('select[name=element]').html(chelement);
	
}

function filterSemestre(){
	var filiere = $('select[name=filiere]').val();

	var chsemestre = "";
	for(let i = 0;i<semestres.length;i++){
			if(semestres[i].id_filiere == filiere){
			chsemestre = chsemestre + '<option value="' + semestres[i].id_semestre +'">' + semestres[i].libelle_semestre + '</option>';
		}
	}
	$('select[name=semestre]').html(chsemestre);
	filterModule();
}

function changeEtudiants(){
		let idelement = $('select[name=element]').val();
		let idannee = $('select[name=annee]').val();

		var ch ="";
		$.ajax({
			url: '/delib/listeretudiants',
			type: 'get',
			data: {
				annee: idannee,
				element: idelement
				},
				success: function(response) {
					var data = JSON.parse(response);
					for(let i = 0;i<data.length;i++){
						ch = ch + "<tr>";
						ch = ch + "<td>" + data[i].massar + "</td>";
						ch = ch + "<td>" + data[i].nom_etudiant + "</td>";
						ch = ch + "<td>" + data[i].prenom_etudiant + "</td>";
						ch = ch + "</tr>";
					}
					$('tbody[id=ds]').html(ch);
				}
		});
			
}



		</script>
	</layout:put>
</layout:extends>