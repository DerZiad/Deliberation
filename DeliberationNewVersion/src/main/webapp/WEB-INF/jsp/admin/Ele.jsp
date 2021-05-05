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
				<h5 class="card-title">Déliberation</h5>
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
								<label for="box" class="">Type de déliberation</label>
								<div class="row">
									<div class="col-md-4">
										<input type="radio" id="parmodule" name="type" value="parmodule">
									</div>
									<div class="col-md-4">
										<label for="box">Déliberation par module</label>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4">
										<input type="radio" id="parsemestre" name="type" value="parsemestre"
											checked>
									</div>
									<div class="col-md-4">
										<label for="box">Déliberation par Semestre</label>
									</div>

								</div>
								<div class="row">
									<div class="col-md-4">
										<input type="radio" id="paretape" name="type" value="paretape">
									</div>
									<div class="col-md-4">
										<label for="box">Déliberation par Etape</label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="element-class" class="element-class">Semestre</label>
								<select name="element" id="exampleSelect" class="form-control">
									<c:forEach var="semestre" items="${semestres}">
										<option value="${semestre.id_semestre }">${semestre.libelle_semestre }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="typedeliberation" class="">Type de déliberation</label>
								<div class="row">
									<div class="col-md-4">
										<input type="radio" id="ordinaire" name="typedeliberation" value="ordinaire">
									</div>
									<div class="col-md-4">
										<label for="box">Déliberation ordinaire</label>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4">
										<input type="radio" id="rattrapage" name="typedeliberation" value="rattrapage"
											checked>
									</div>
									<div class="col-md-4">
										<label for="box">Déliberation rattrapage</label>
									</div>

								</div>
							</div>
						</div>
						<div class="col-md-6" id="noterattrapage">
							<div class="position-relative form-group">
								<div class="row">
									<div class="col-md-4">
										<input type="checkbox" name="consideration" value="1">
									</div>
									<div class="col-md-4">
										<label for="box">La note de rattrapage sera calculé en prennant considération des autres notes</label>
									</div>
								</div>
							</div>
						</div>

						<button class="mt-2 btn btn-primary col-md-12" type="submit">Valider</button>
					</div>
				</form>
			</div>
		</div>
		<script>
		var modules = JSON.parse('${modulesjson}');
		var etapes = JSON.parse('${etapesjson}');
		var semestres = JSON.parse('${semestresjson}');
		jQuery(document).ready(function(){
			$('input[id=parmodule]').click(function(){
				var filiereId = $('select[name=filiere]').val();
				var remplir = "";
				for (var i = 0; i < modules.length; i++) {
						if(modules[i].id_filiere == filiereId){
							remplir = remplir + '<option value="' + modules[i].id_module + '">' + modules[i].libelle_module + '</option> \n';
						}
				}
				$('select[name=element]').html(remplir);
			});

			$('input[id=parsemestre]').click(function(){
				var filiereId = $('select[name=filiere]').val();
				var choice = $('input[name=type]').val();
				var remplir = "";
				for (var i = 0; i < semestres.length; i++) {
						if(semestres[i].id_filiere == filiereId){
							remplir = remplir + '<option value="' + semestres[i].id_semestre + '">' + semestres[i].libelle_semestre+ '</option> \n';
						}
				}
				$('select[name=element]').html(remplir);
			});
			$('input[id=paretape]').click(function(){
				var filiereId = $('select[name=filiere]').val();
				var remplir = "";
				for (var i = 0; i < etapes.length; i++) {
						if(etapes[i].id_filiere == filiereId){
							remplir = remplir + '<option value="' + etapes[i].id_etape + '">' + etapes[i].libelle_etape+ '</option> \n';
						}
				}
				$('select[name=element]').html(remplir);
			});
			
			$('input[id=ordinaire]').click(function(){
				$('div[id=noterattrapage]').hide();
			});
			
			$('input[id=rattrapage]').click(function(){
				$('div[id=noterattrapage]').show();
			});
				
		
});
		</script>
	</layout:put>
</layout:extends>