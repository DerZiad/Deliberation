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
				<h5 class="card-title">Création d'un module</h5>
				<form class="" action="/admin/module/creer" method="POST">
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="name" class="">Nom du module</label><input
									name="name" id="name" placeholder="Nom du module" type="text"
									class="form-control">
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="semestre_id" class="">Semestre</label> <select
									name="semestre_id" id="exampleSelect" class="form-control">
									<c:forEach var="semestre" items="${semestres }">
										<option value="${semestre.id_semestre}">
											${semestre.libelle_semestre }
											${semestre.etape.filiere.nom_filiere}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-12">
							<div class="position-relative form-group">
								<label for="professeur_id" class="">Professeur
									résponsable:</label> <select name="professeur_id" id="exampleSelect"
									class="form-control">
									<c:forEach var="professeur" items="${professeurs }">
										<option value="${professeur.id_professeur}">
											${professeur.nom_professeur } ${professeur.prenom_professeur}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-12">
							<div class="position-relative form-group">
								<div class="form-check">
									<input type="checkbox" class="form-check-input"
										id="composotionmodule" name="checktheelement" value="1"> <label class="form-check-label"
										for="composotionmodule">Module se compose d'un seul element</label>
								</div>
							</div>
						</div>
					</div>
					<button class="mt-2 btn btn-primary col-md-6" type="submit">Valider</button>
				</form>
			</div>
		</div>
		

	</layout:put>
</layout:extends>