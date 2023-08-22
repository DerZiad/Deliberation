<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<layout:extends name="${mero}">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Déliberation par semstre</h5>
				<form class="" action="/delib/deliberationsemestre" method="POST">
					<input type="hidden" name="type" value="parsemestre" />
					<c:if test=""></c:if>
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
								<label for="name" class="">Année académique</label> <select
									name="annee" id="exampleSelect" class="form-control">
									<option value="${annee.id_annee_academique }">${annee.annee_academique }</option>
								</select>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="element-class" class="element-class">Semestre</label>
								<select name=element id="exampleSelect" class="form-control">
									<c:forEach items="${semestres }" var="semestre">
										<option value="${semestre.id_semestre }">${semestre.libelle_semestre }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<button class="mt-2 btn btn-primary col-md-12" type="submit">Valider</button>
					</div>
				</form>
				<c:if test="${error ne null }">
					<br>
					<br>
					<div class="card alert alert-danger" role="alert">
						<div class="card-body">
							<h5 class="card-title">${error.title }</h5>
							<p class="card-text">${error.getMessage() }</p>
						</div>
					</div>
				</c:if>
			</div>
		</div>
		<script>
var semestres = JSON.parse('${semestresjson}');

jQuery(document).ready(function(){
	$('select[name=filiere]').change(function(){
		filter();
	});	
	filter();
});
function filter(){
	var filiere = $('select[name=filiere]').val();
		var chsemestre = "";
		for(let i = 0;i<semestres.length;i++){
			if(semestres[i].id_filiere == filiere){
				chsemestre = chsemestre + '<option value="' + semestres[i].id_semestre +'">' + semestres[i].libelle_semestre + '</option>';
			}
		}
		$('select[name=element]').html(chsemestre);
}
	</script>
	</layout:put>
</layout:extends>