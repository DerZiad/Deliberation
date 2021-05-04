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
				<h5 class="card-title">Création d'une filière</h5>
				<form class="" action="/admin/filiere/creer" method="POST">
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
								<label for="box" class="">Type de déliberation</label> <input
									type="radio" id="box" name="type" value="parmodule" checked>
								<label for="box">Déliberation par module</label> <input
									type="radio" id="box" name="type" value="parsemestre" checked>
								<label for="box">Déliberation par Semestre</label> <input
									type="radio" id="box" name="type" value="paretape" checked>
								<label for="box">Déliberation par Etape</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="semester_number" class="">Semestre</label> <select
									name="annee" id="exampleSelect" class="form-control">
									<c:forEach var="semestre" items="${semestres}">
										<option value="${semestre.id_semestre }">${semestre.libelle_semestre }</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<button class="mt-2 btn btn-primary col-md-12" type="submit">Valider</button>
				</form>
			</div>
		</div>

	</layout:put>
</layout:extends>