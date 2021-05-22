<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="../layout.jsp">
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
							<th class="th-sm">Type déliberation</th>
							<th class="th-sm">Action</th>
						</tr>
					</thead>
					<tbody id="notes">
						<c:forEach var="deliberation" items="${deliberations}">
							<tr>
								<td style="color: black">${deliberation.delibered ? "Rattrapage":"Ordinaire"}</td>
								<td style="color: black">${deliberation.anneeAcademique.annee_academique}</td>
								<c:if test="${deliberation.module ne null }"><td><a style="color: black" href="/delib/listerDelib?id=${deliberation.idDeliberation }">Module</a></td></c:if>
								<c:if test="${deliberation.etape ne null }"><td><a style="color: black" href="/delib/listerDelib?id=${deliberation.idDeliberation }">Etape</a></td></c:if>
								<c:if test="${deliberation.semestre ne null }"><td><a style="color: black" href="/delib/listerDelib?id=${deliberation.idDeliberation }">Semestre</a></td></c:if>
								<td><a href="/delib/listerDelib?id=${deliberation.idDeliberation }" class="btn btn-danger">View</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</layout:put>
</layout:extends>