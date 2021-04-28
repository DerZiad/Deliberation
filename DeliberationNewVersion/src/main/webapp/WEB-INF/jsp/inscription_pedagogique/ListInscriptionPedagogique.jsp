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
							<th>Validé</th>
							<th>Année</th>
							<th>Action</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="inscription" items="${inscriptions}">
							<tr>
								<td>${inscription.element.libelle_element}</td>
								<td>${inscription.isValid() }</td>
								<td>${inscription.annee_academique.annee_academique }</td>
								<td><i class="fa fa-fw" aria-hidden="true"
									title="Copy to use pencil-square-o"><a
										href="/admin/inscriptionpedagogique/modifier/${inscription.etudiant.id_etudiant}/${inscription.element.id_element }"
										style="font-size: 20px;"></a></i> </td><td><i class="fa fa-fw"
									aria-hidden="true" title="Copy to use trash"> <a
										href="/admin/inscriptionpedagogique/suprimer/${inscription.etudiant.id_etudiant}/${inscription.element.id_element }"
										style="color: red; font-size: 20px;"></a>
								</i></td>
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