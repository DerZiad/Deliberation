<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<layout:extends name="../layout.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Création d'une Etape</h5>
				<form class=""
					action="/admin/filiere/profile/${filiere.id_filiere}/etapes/semestres/create"
					method="POST">
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="name" class="">Libelle semestre</label><input
									name="libelle_semestre" id="name" placeholder="" type="text"
									class="form-control">
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="name" class="">Validation</label><input
									name="validation" id="name" placeholder="Validation" type="text"
									class="form-control">
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
									<label for="name" class="">Etapes</label><select name="etape"
											id="name" class="form-control">
											<c:forEach var="etape" items="${filiere.etapes}">
												<option value="${etape.id_etape }">${etape.libelle_etape }</option>
											</c:forEach>
								</select>
							</div>
						</div>
					</div>
					
					<button class="mt-2 btn btn-primary col-md-12" type="submit">Valider</button>
				</form>
			</div>
		</div>

	</layout:put>
</layout:extends>