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
				<h5 class="card-title">Déliberation par module</h5>
				<form class="" action="/delib/" method="POST">
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="filiere" class="">Filiere</label> <select
									name="filiere" id="exampleSelect" class="form-control">
									<c:forEach var="filiere" items="${filieres}">
										<option value="${filiere.id_filiere }">${filiere.nom_filiere}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="name" class="">Années académiques</label> <select
									name="annee" id="exampleSelect" class="form-control">
									<c:forEach var="annee" items="${annees}">
										<option value="${annee.id_annee_academique }">${annee.annee_academique }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="element-class" class="element-class">Semestre</label>
								<select name=semestre id="exampleSelect" class="form-control">
									<c:forEach items="${semestres }" var="semestre"> 
										<option value="${semestre.id_semestre }">${semestre.libelle_semestre }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<button class="mt-2 btn btn-primary col-md-12" type="submit">Valider</button>
					</div>
				</form>
			</div>
		</div>
		<script>
var modules = JSON.parse('${modulesjson}');
var semestres = JSON.parse('${semestresjson}');

jQuery(document).ready(function(){
	$('select[name=filiere]').change(function(){
		filiterSemestre();
	});

	$('select[name=semestre]').change(function(){
		filterModule();
	});
	
	$('input[id=ordinaire]').click(function(){
				$('div[id=noterattrapage]').hide();
	});
			
	$('input[id=rattrapage]').click(function(){
			$('div[id=noterattrapage]').show();
	});
	
	filterModule();
});
function filterModule(){
	var semestre = $('select[name=semestre]').val();

	var chmodule = "";
	for(let i = 0;i<modules.length;i++){
		if(modules[i].id_semestre == semestre){
			chmodule = chmodule + '<option value="' + modules[i].id_module + '">' + modules[i].libelle_module + '</option>';
		}
	}
	$('select[name=module]').html(chmodule);
}

function filiterSemestre(){
	var filiere = $('select[name=filiere]').val();

	var chsemestre = "";
	for(let i = 0;i<semestres.length;i++){
		if(semestres[i].id_filiere == filiere){
			chsemestre = chsemestre + '<option value="' + semestres[i].id_semestre +'">' + semestres[i].libelle_semestre + '</option>';
		}
	}
	$('select[name=semestre]').html(chsemestre);
}


		</script>
	</layout:put>
</layout:extends>