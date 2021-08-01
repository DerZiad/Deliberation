<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="${mero}">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Liste de déliberations</h5>
			</div>
			<div class="card-body">
				<table class="mb-0 table table-striped" id="myTable">
					<thead>
						<tr>
							<th class="th-sm">Deliberation</th>
							<th class="th-sm">Année academique</th>
							<th class="th-sm">Filiere</th>
							<th class="th-sm">Type déliberation</th>
							<th class="th-sm">Element</th>
							<th class="th-sm">Action</th>
						</tr>
					</thead>
					<tbody id="notes">
						<c:forEach var="deliberation" items="${deliberations}">
							<tr>
								<c:if test="${deliberation.element ne null}">
									<td style="color: black">${deliberation.delibered ? "Rattrapage":"Ordinaire"}</td>
								</c:if>
								<c:if test="${deliberation.element eq null}">
									<td>Deliberation</td>
								</c:if>
								<td style="color: black">${deliberation.anneeAcademique.annee_academique}</td>
								<c:if test="${deliberation.element ne null }">
									<td style="color: black">${deliberation.element.module.
									semestre.etape.filiere.nom_filiere}</td>
									<td style="color: black">Element</td>
									<td style="color: black">${deliberation.element.libelle_element }</td>

								</c:if>
								<c:if test="${deliberation.module ne null }">
									<td style="color: black">${deliberation.module.
									semestre.etape.filiere.nom_filiere}</td>
									<td style="color: black">Module</td>
									<td style="color: black">${deliberation.module.libelle_module }</td>

								</c:if>
								<c:if test="${deliberation.etape ne null }">
									<td style="color: black">${deliberation.etape
									.filiere.nom_filiere}</td>
									<td style="color: black">Etape</td>
									<td style="color: black">${deliberation.etape.libelle_etape}</td>
								</c:if>
								<c:if test="${deliberation.semestre ne null }">
									<td>${deliberation.semestre.etape.filiere.nom_filiere}</td>
									<td style="color: black">Semestre</td>
									<td style="color: black">${deliberation.semestre.libelle_semestre }</td>
								</c:if>
								<td><a
									href="/delib/listerDelib?id=${deliberation.idDeliberation }"
									class="btn btn-danger">View</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</layout:put>
</layout:extends>