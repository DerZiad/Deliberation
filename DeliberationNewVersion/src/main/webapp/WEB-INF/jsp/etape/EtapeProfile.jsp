<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="../layout.jsp">
	<layout:put block="content" type="REPLACE">


		<div class="row">
			<div class="col-md-3">
				<div class="main-card mb-3 card">
					<div class="card-body">L'etape suivant : ${etape.libelle_etape}</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="main-card mb-3 card">
					<div class="card-body">
						<h5 class="card-title">Modification</h5>
						<form action="/admin/filiere/profile/${etape.filiere.id_filiere}/etapes/modify/${etape.id_etape}"
							method="POST">
							<div class="position-relative form-group">
								<label for="name" class="">Nom: </label><input name="libelle_etape"
									id="name" placeholder="Nom de la filière" type="text"
									class="form-control" value="${etape.libelle_etape }">
							</div>
							<div class="position-relative form-group">
								<label for="name" class="">Validation: </label><input name="validation"
									id="name" placeholder="note de validation" type="text"
									class="form-control" value="${etape.validation }">
							</div>
							
							<button class="mt-1 btn btn-primary" type="submit">Modifier</button>
						</form>
					</div>
				</div>
			</div>
		</div>


	</layout:put>
</layout:extends>