<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="layout.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Liste des responsables</h5>
				<table class="mb-0 table table-striped">
					<thead>
						<tr>
							<th>Nom</th>
							<th>Prénom</th>
							<th>Email</th>
							<th>Filiere</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="responsable" items="${responsables}">
							<tr>
								<td><a style="color: black" href="/responsable/profile/${responsable.id_responsable}">${responsable.nom_responsable}</a></td>
								<td><a style="color: black" href="/responsable/profile/${responsable.id_responsable}">${responsable.prenom_responsable}</a></td>
								<td><a style="color: black" href="/responsable/profile/${responsable.id_responsable}">${responsable.email_responsable}</a></td>
								<td><a style="color: black" href="/responsable/profile/${responsable.id_responsable}">${responsable.filiere.nom_filiere}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</layout:put>
</layout:extends>