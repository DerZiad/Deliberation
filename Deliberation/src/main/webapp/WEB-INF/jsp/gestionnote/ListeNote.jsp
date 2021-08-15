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
				<h5 class="card-title">Liste des notes</h5>
				<div class="col-md-6">


					<form action="/gestionnote" method="POST">
						<input type="hidden" id="listannee" name="action" id="myInput"
							value="discuss" class="form-control"> <input
							value="${id_annee_academique }" type="hidden" id="listannee"
							name="id_annee_academique" id="myInput" class="form-control">

						<input type="hidden" value="${id_filiere }" id="listfiliere"
							name="id_filiere" class="form-control"> <input
							type="hidden" id="listetape" name="id_etape" id="myInput"
							class="form-control" value="${id_etape }"> <input
							type="hidden" id="listsemestre" name="id_semestre" id="myInput"
							class="form-control" value="${id_semestre }" /> <input
							type="hidden" id="listmodule" value="${id_module }"
							name="id_module" id="myInput" class="form-control" /> <input
							type="hidden" id="listelement" name="id_element" id="myInput"
							class="form-control" value="${id_element }" />

						<div class="col-md-9">
							<div class="position-relative form-group">
								<button class="btn btn-success">Discuss</button>
							</div>

						</div>
					</form>
				</div>
				<table class="mb-0 table table-hover" id="myTable">
					<thead>
						<tr>
							<th class="th-sm">Nom Etudiant</th>
							<th class="th-sm">Element</th>
							<th class="th-sm">Type</th>
							<th class="th-sm">Note</th>
							<th class="th-sm">Etat</th>
							<th class="th-sm">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="note" items="${notes}">
							<tr id="notes">
								<td><a style="color: black">
										${note.getEtudiant().first_name_fr}
										${note.getEtudiant().last_name_fr}</a></td>
								<td><a style="color: black">
										${note.getElement().getName()}</a></td>
								<td><a style="color: black">
										${note.getElement().getType()}</a></td>
								<td><a style="color: black"> ${note.getNote()}</a></td>
								<td><a style="color: black"> ${note.getEtat()}</a></td>

								<c:url var="url"
									value="/gestionnote/edit/${note.getEtudiant().id_etudiant }/${note.getElement().getId()}">
									<c:param name="type">${note.getElement().getType()}</c:param>
								</c:url>
								<td><a class="btn btn-primary" href="${url }"> Edit</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</layout:put>
</layout:extends>