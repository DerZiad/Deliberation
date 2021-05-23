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
				<h5 class="card-title">Déliberation par etape</h5>
				<input type="hidden" name="type" value="paretape"/>
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
								<label for="element-class" class="element-class">Etapes</label>
								<select name=etape id="exampleSelect" class="form-control">
									<c:forEach items="${etapes }" var="etape"> 
										<option value="${etape.id_etape }">${etape.libelle_etape }</option>
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
								<label for="name" class=""></label>
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

jQuery(document).ready(function(){
	$('select[name=filiere]').change(function(){
		var filiere = $('select[name=filiere]').val();
		var chetape = "";
		for(let i = 0;i<etapes.length;i++){
			if(semestres[i].id_filiere == filiere){
				chetape = chetape + '<option value="' + etapes[i].id_etape +'">' + etapes[i].libelle_etape + '</option>';
			}
		}
		$('select[name=etape]').html(chetape);
	});	
});
	</script>
	</layout:put>
</layout:extends>