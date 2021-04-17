<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="../layout.jsp">
	<layout:put block="content" type="REPLACE">


		<div class="row">
			<div class="col-md-3">
				<div class="main-card mb-3 card">
					<div class="card-body">
						<h5 class="card-title">module: ${module.libelle_module }</h5>
						<a href="/admin/module/profile/${module.id_module}/element/create"><button class="mb-2 mr-2 btn btn-primary btn-block">Les éléments du module</button></a>

					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="main-card mb-3 card">
					<div class="card-body">
						<h5 class="card-title">Modification</h5>
						<form action="/admin/module/modifer/${module.id_module}"
							method="POST">
							<div class="position-relative form-group">
								<label for="name" class="">Nom: </label><input name="name"
									id="name" placeholder="Nom de l'établissement" type="text"
									class="form-control" value="${module.libelle_module }">
							</div>
							<div class="position-relative form-group">
								<label for="semestre_id" class="">Semestre:</label>
								<select name="semestre_id" id="exampleSelect" class="form-control">
									<c:forEach var="semestre" items="${semestres }">
										<option value="${semestre.id_semestre}" <c:if test="${module.semestre.id_semestre eq semestre.id_semestre }"> selected</c:if>> ${semestre.libelle_semestre } ${semestre.etape.filiere.nom_filiere}</option>
									</c:forEach>
								</select>
							</div>
							<div class="position-relative form-group">
								<label for="professeur_id" class="">Professeur Responsable:</label>
								<select name="professeur_id" id="exampleSelect" class="form-control">
									<c:forEach var="professeur" items="${professeurs }">
										<option value="${professeur.id_professeur}" <c:if test="${module.responsable_module.id_professeur eq professeur.id_professeur }"> selected</c:if>> ${professeur.nom_professeur } ${professeur.prenom_professeur}</option>
									</c:forEach>
								</select>
							</div>
							<button class="mt-1 btn btn-primary"
								formaction="/admin/module/profile/${module.id_module}" formmethod="POST">Modifier</button>
							<button class="mt-1 btn btn-danger"
								formaction="/admin/module/supprimer/${module.id_module}" formmethod="GET">Supprimer</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		
	</layout:put>
</layout:extends>