<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="layout-prof.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Mes elements</h5>
				<table class="mb-0 table table-striped">
					<thead>
						<tr>
							<th>Nom</th>
							<th>Module</th>
							<th>Filière</th>
							<th>Semestre</th>
							<th>Etablissement</th>
							<th>Etudiants</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="element" items="${elements}">
							<tr>
								<td style="color: black">${element.libelle_element}</td>
								<td style="color: black">${element.libelle_element}</td>
								<td style="color: black">${element.module.semestre.etape.filiere.nom_filiere}</td>
								<td style="color: black">${element.module.semestre.libelle_semestre}</td>
								<td style="color: black"> ${element.module.semestre.etape.filiere.etablissement.nom_etablissement}</td>
								<td><a style="color: black" href="/professeur/listerElements/${element.id_element}">Etudiants</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</layout:put>
</layout:extends>