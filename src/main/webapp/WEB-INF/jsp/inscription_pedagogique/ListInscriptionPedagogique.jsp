<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<layout:extends name="../layout.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Liste des inscriptions d'étudiants</h5>
				<table class="mb-0 table table-striped">
					<thead>
						<tr>
							<th>Element</th>
							<th>Année</th>
							<th>Module</th>
							<th>Semestre</th>
							<th>Etape</th>
							<th>Filiere</th>
							<th>Etablissement</th>
							<th>Type Inscription</th>
							<th>Action</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="inscription" items="${inscriptions}">
							<tr>
								<td>${inscription.element.libelle_element}</td>
								<td>${inscription.annee_academique }</td>
								<td>${inscription.id_inscription_pedagogique.element.module.libelle_module}</td>
								<td>${inscription.id_inscription_pedagogique.element.module.semestre.libelle_semestre }</td>
								<td>${inscription.id_inscription_pedagogique.element.module.semestre.etape.libelle_etape }</td>
								<td>${inscription.id_inscription_pedagogique.element.module.semestre.etape.filiere.nom_filiere }</td>
								<td>${inscription.id_inscription_pedagogique.element.module.semestre.etape.filiere.etablissement.nom_etablissement }</td>
								<td>${inscription.type_inscription }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>
		<script>

		
		</script>
	</layout:put>
</layout:extends>