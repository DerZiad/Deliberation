<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="../layout.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<form action="/admin/filiere/profile/${filiere.id_filiere}/etapes" method="POST">
					<input type="hidden" name="id_filiere" value="${filiere.id_filiere }"/>
					<h5 class="card-title">Liste des etapes</h5>
					<table class="mb-0 table table-striped">
						<thead>
							<tr>
								<th>Etape</th>
								<th>Semestre</th>
								<th>Diplomante</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="etape" items="${etapes}">
								<tr>
									<td>${etape.libelle_etape}</td>
									<td><c:forEach var="semestre" items="${etape.semestres}">
									${semestre.libelle_semestre}
								</c:forEach></td>
									<td><input class="" type="checkbox"
										name="${etape.id_etape }" value="1" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<input class="" type="submit" value="valider"/>
				</form>
			</div>
		</div>

	</layout:put>
</layout:extends>