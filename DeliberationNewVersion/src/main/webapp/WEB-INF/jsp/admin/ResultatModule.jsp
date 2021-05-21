<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="../layout.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Filter</h5>
				<form class="form-group"
					action="/professeur/listerElements/${element.id_element}"
					method="POST">
					<div class="col-md-6">
						<div class="position-relative form-group">
							<label for="name" class="">Années academiques</label> <select
								name="annee" id="exampleSelect" class="form-control">
								<c:forEach var="annee" items="${annees}">
									<option value="${annee.id_annee_academique }">${annee.annee_academique }</option>
								</c:forEach>
							</select>
						</div>
						<div class="position-relative form-group">
							<button class="btn btn-success">Download excel</button>
						</div>
					</div>


				</form>
			</div>
			<div class="card-body">
				<table class="mb-0 table table-striped" id="myTable">
					<thead>
						<tr>
							<th class="th-sm">Massar</th>
							<th class="th-sm">Nom</th>
							<th class="th-sm">Prénom</th>
							<th class="th-sm">Note</th>
							<th class="th-sm">Statut</th>
						</tr>
					</thead>
					<tbody id="etudiants">
						<c:forEach var="note" items="${deliberation.notesModule}">
							<tr>
								<td style="color: black">${note.etudiant.massar_edu}</td>
								<td style="color: black">${note.etudiant.last_name_fr}</td>
								<td style="color: black">${note.etudiant.first_name_fr}</td>
								<td style="color: black">${note.note}</td>
								<td style="color: black">${note.etat}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</layout:put>
</layout:extends>