<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="../layout.jsp">
	<layout:put block="content" type="REPLACE">


		<div class="row">

			<div class="col-md-3">
				<div class="row-md-6">
					<div class="main-card mb-3 card">
						<div class="card-body">
							<h5 class="card-title">filière: ${filiere.nom_filiere }</h5>
							<a href="/filiere/profile/${filiere.id_filiere }/etudiant/liste"><button
									class="mb-2 mr-2 btn btn-warning btn-block">Les
									étudiants de la filière</button></a>
						</div>
					</div>
				</div>
				<div class="row-md-6">
					<div class="row-md-6">
						<div class="main-card mb-3 card">
							<div class="card-body">
								<a href="/admin/filiere/profile/${filiere.id_filiere }/etapes"><button
										class="mb-2 mr-2 btn btn-warning btn-block">Les étapes de la filieres</button></a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="main-card mb-3 card">
					<div class="card-body">
						<h5 class="card-title">Modification</h5>
						<form action="/admin/filiere/profile/${filiere.id_filiere}"
							method="POST">
							<div class="position-relative form-group">
								<label for="name" class="">Nom: </label><input name="name"
									id="name" placeholder="Nom de la filière" type="text"
									class="form-control" value="${filiere.nom_filiere }">
							</div>
							<div class="position-relative form-group">
								<label for="name" class="">Établissement: </label> <select
									name="etablissement_id" id="exampleSelect" class="form-control">
									<c:forEach var="etablissement" items="${etablissements}">
										<option value="${etablissement.id_etablissement}"
											<c:if test="${etablissement.id_etablissement eq etab_fili }"> selected</c:if>>
											${etablissement.nom_etablissement }</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-6">
								<div class="position-relative form-group">
									<label for="name" class="">Responsable filiere</label> <select
										name="professeur" id="exampleSelect" class="form-control">
										<c:forEach var="professeur" items="${professeurs}">
											<option value="${professeur.id_professeur }">${professeur.nom_professeur }
												${professeur.prenom_professeur }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="position-relative form-group">
								<label for="semester_number" class="">Nombre de
									semestres: </label><input name="semester_number" id="semester_number"
									placeholder="Nombre de semestres" type="number"
									class="form-control" value="${semester_number }">
							</div>
							<button class="mt-1 btn btn-primary" type="submit">Modifier</button>
							<button class="mt-1 btn btn-danger"
								formaction="/admin/filiere/supprimer/${filiere.id_filiere}"
								formmethod="POST">Supprimer</button>

						</form>
					</div>
				</div>
			</div>
		</div>
		

	</layout:put>
</layout:extends>