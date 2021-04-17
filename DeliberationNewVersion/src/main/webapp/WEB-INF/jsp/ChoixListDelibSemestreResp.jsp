<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="layout-resp.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Liste des semestres pour déliberation par semestre</h5>
				<table class="mb-0 table table-striped">
					<thead>
						<tr>
							<th>Nom Semestre</th>
							<th>Filiere</th>
							<th>Etablissement</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="semestre" items="${semestres}">
							<tr>
								<td><a style="color: black" href="/delib/listSemestre/${semestre.id_semestre}">${semestre.libelle_semestre}</a></td>
								<td><a style="color: black" href="/delib/listSemestre/${semestre.id_semestre}">${semestre.etape.filiere.nom_filiere}</a></td>
								<td><a style="color: black" href="/delib/listSemestre/${semestre.id_semestre}">${semestre.etape.filiere.etablissement.name}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</layout:put>
</layout:extends>