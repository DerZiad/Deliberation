<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<layout:extends name="layout.jsp">
	<layout:put block="content" type="REPLACE">
		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Etudiants</h5>
				<form action="/etudiant/consulter"
					method="POST">
					<div class="col-md-6">
						<div class="position-relative form-group">
							<input type="checkbox" class="form-class" name="checkfiliere"
								checked /> <label for="Filiere" class="">Semestres</label> <select
								id="listfiliere" name="id_semestre" class="form-control">
								<c:forEach var="semestre" items="${semestres}">
									<option value="${semestre.id_semestre }">${semestre.libelle_semestre }</option>
								</c:forEach>

							</select>
						</div>
					</div>
					<div class="col-md-9">
						<div class="position-relative form-group">
							<button class="btn btn-success">Valider</button>
						</div>

					</div>
					<div class="col-md-9">
						<div class="position-relative form-group">
							<a id="download" href="" class="btn btn-success">Download Releve note</a>
						</div>

					</div>
				</form>

				<table class="mb-0 table table-hover" id="myTable">
					<thead>
						<tr>
							<th class="th-sm">Massar</th>
							<th class="th-sm">Nom Etudiant</th>
							<th class="th-sm">Module</th>
							<th class="th-sm">Note</th>
							<th class="th-sm">Etat</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="note" items="${notes}">
							<tr>
								<td><a style="color: black">
										${note.idComposed.etudiant.massar_edu}</a></td>
								<td><a style="color: black">
										${note.idComposed.etudiant.first_name_fr}
										${note.idComposed.etudiant.last_name_fr}</a>
								</td>
								<td><a style="color: black">
										${note.idComposed.module.libelle_module}</a>
								</td>
								<td><a style="color: black">
										${note.note}</a>
								</td>
								<td><a style="color: black">
										${note.etat}</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
jQuery(document).ready(function(){
	$('select[name=id_semestre]').click(){
		var idSemestre = $('select[name=id_semestre]').val();
		url = "/consulter/download?id_semestre=" + idSemestre;
		$('a[id=download]').attr("href",url);
	}
});
	</layout:put>
</layout:extends>