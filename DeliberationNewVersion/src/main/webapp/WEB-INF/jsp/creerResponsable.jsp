<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<layout:extends name="layout.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Ajouter un responsable</h5>
				<form class="" action="/responsable/creer" method="Post">

					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="nom_responsable" class="">Nom du Responsable</label><input
									name="nom_responsable" id="nom_responsable" placeholder="Nom du responsable"
									type="text" class="form-control" required>
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="prenom_responsable" class="">Prénom du Responsable</label><input
									name="prenom_responsable" id="prenom_responsable" placeholder="Prénom du responsable"
									type="text" class="form-control" required>
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="email_responsable" class="">Email du Responsable</label><input
									name="email_responsable" id="email_responsable" placeholder="Email du responsable"
									type="email" class="form-control" required>
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="filiere_id" class="">Filiere</label>
								<select name="filiere_id" id="exampleSelect" class="form-control">
									<c:forEach var="filiere" items="${filieres}">
										<option value="${filiere.id_filiere }">${filiere.nom_filiere }</option>
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