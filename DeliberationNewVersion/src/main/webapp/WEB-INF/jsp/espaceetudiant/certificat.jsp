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
				<form action="/etudiant/certificatscolarite/download"
					method="GET">
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
				</form>
			</div>
		</div>
	</layout:put>
</layout:extends>