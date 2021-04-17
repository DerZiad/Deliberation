<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="layout-prof.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Choix des notes de chaque element</h5>
				<table class="mb-0 table table-striped">
					<thead>
						<tr>
							<th>Nom Element</th>
							<th>Module</th>
							<th>Semestre</th>
							<th>Filiere</th>
							<th>Etablissement</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="element" items="${elements}">
							<tr>
								<td><a style="color: black" href="/note/list/${element.id_element}">${element.libelle_element}</a></td>
								<td><a style="color: black" href="/note/list/${element.id_element}">${element.module.libelle_module}</a></td>
								<td><a style="color: black" href="/note/list/${element.id_element}">${element.module.semestre.libelle_semestre}</a></td>
								<td><a style="color: black" href="/note/list/${element.id_element}">${element.module.semestre.etape.filiere.nom_filiere}</a></td>
								<td><a style="color: black" href="/note/list/${element.id_element}">${element.module.semestre.etape.filiere.etablissement.name}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</layout:put>
</layout:extends>