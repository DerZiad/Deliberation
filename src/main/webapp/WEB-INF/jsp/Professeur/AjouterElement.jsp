<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="../layout.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Ajout d'un élement</h5>
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="name" class="">Elements</label><input
									name="name" id="name" placeholder="Nom d'element" type="text"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-12">
							<div class="position-relative form-group">
								<label for="filiere" class="">Filieres</label> <select
									name="filiere" id="filiere" class="form-control">
									<c:forEach var="filiere" items="${filieres}">
										<option value="${filiere.id_filiere}">
											${filiere.nom_filiere }</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="name" class="">Semestres</label><input
									name="name" id="name" placeholder="Nom d'element" type="text"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-12">
							<div class="position-relative form-group">
								<label for="semestre" class="">Professeur</label> <select
									name="semestre" id="semestre" class="form-control">
									<c:forEach var="semestre" items="${semestres}">
										<option value="${semestre.id_semestre}">
											${semestre.libelle_semestre }</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<button class="mt-2 btn btn-primary col-md-6" type="submit">Enregistrer</button>
			</div>
			<div class="card-body">
				<h5 class="card-title">Liste des elements</h5>
				<table class="mb-0 table table-striped">
					<thead>
						<tr>
							<th>Nom</th>
							<th>Module</th>
							<th>Etablissement</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="element" items="${elements}">
							<tr>
								<td>${element.libelle_element}</td>
								<td>${element.module.libelle_module}</td>
								<td>${element.module.semestre.etape.filiere.nom_filiere}</td>
								<td><a href="/admin/professeur/profile/${professeur.id_professeur}/elements/addelement/${element.id_element}">Suprimer</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</layout:put>
</layout:extends>