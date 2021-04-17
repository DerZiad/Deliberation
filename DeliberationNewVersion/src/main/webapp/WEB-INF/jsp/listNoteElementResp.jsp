<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<layout:extends name="layout-resp.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Liste des inscriptions d'étudiants</h5>
				<div class="form-row">
					<input name="element" id="element" value="${element.id_element}"
						type="hidden" class="form-control" readonly>


					
				</div>
				<table class="mb-0 table table-hover">
					<thead>
						<tr>
							<th>Nom</th>
							<th class="th-sm">Prénom</th>
							<th class="th-sm">cne</th>
							<th class="th-sm">Moyenne</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="i" items="${resultats}">
							<tr>
								<td><a style="color: black">${i.inscription_pedagogique.etudiant.last_name_fr}</a></td>
								<td><a style="color: black">${i.inscription_pedagogique.etudiant.first_name_fr}</a></td>
								<td><a style="color: black">${i.inscription_pedagogique.etudiant.cne}</a></td>
								<td><a style="color: black">${i.note_element}</a></td>


							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>

	</layout:put>
</layout:extends>