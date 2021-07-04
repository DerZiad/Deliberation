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
				<form class="from-group" action="/admin/module/profile/${module.id_module}/element/create" method="POST">
					<h5 class="card-title">Création d'un element</h5>

					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="name" class="">Nom d'element</label><input
									name="name" id="name" placeholder="Nom d'element" type="text"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-12">
							<div class="position-relative form-group">
								<label for="professeur_id" class="">Professeur</label> <select
									name="professeur_id" id="exampleSelect" class="form-control">
									<c:forEach var="professeur" items="${professeurs}">
										<option value="${professeur.id_professeur}">
											${professeur.nom_professeur } ${professeur.prenom_professeur}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-4">
							<div class="position-relative form-group">
								<label for="coefficient_id" class="">Coefficient</label> <input
									id="coefficient_id" class="form-control" name="coefficient" />
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-4">
							<div class="position-relative form-group">
								<label for="validation_id" class="">Validation</label> <input
									id="validation_id" class="form-control" name="validation" />
							</div>
						</div>
					</div>
					<button class="mt-2 btn btn-primary col-md-6" type="submit">Enregistrer</button>
				</form>
			</div>
			<div class="main-card mb-3">
				<div class="card-body">
					<h5 class="card-title">Liste des modules</h5>
					<table class="mb-0 table table-striped">
						<thead>
							<tr>
								<th>Nom</th>
								<th>Coefficient</th>
								<th>Validation</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="element" items="${elements}">
								<tr>
									<td><a style="color: black"
										href="/admin/module/profile/${element.module.id_module}/element/modify/${element.id_element}">
											${element.libelle_element}</a></td>
									<td style="color: black">${element.coeficient}</td>
									<td style="color: black">${element.validation}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</layout:put>
</layout:extends>