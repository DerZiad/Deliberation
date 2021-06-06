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
				<h5 class="card-title">Inscription Administrative de l'Etudiant</h5>
				<form class=""
					action="/admin/inscription/InscriptionAdministrative/${inscriptionenligne}"
					method="Post" enctype="multipart/form-data">


					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="Filiere" class="">Filiere</label> <select
									name="filiere" id="exampleSelect" class="form-control" required>
									<c:forEach var="filiere" items="${filieres }">
										<option value="${filiere.getId_filiere()}">${filiere.getNom_filiere() }</option>
									</c:forEach>
								</select>
							</div>

						</div>

					</div>

					<div class="form-row">

						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Année academique</label>
								<select id="annee_academique" name="annee_academique" class="form-control" required>
									<c:forEach var="annee" items="${annees_academiques}">
										<option value="${annee.id_annee_academique }">${annee.annee_academique}/${annee.annee_academique + 1}
										</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Inserer photo</label><input
									name="photo" id="file" type="file" class="form-control-file">
							</div>
						</div>
						<div class="col-md-6" id="bac">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Inserer Bac</label><input
									name="bac" id="file" type="file" class="form-control-file">
							</div>
						</div>

						<div class="col-md-6" id="rn">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Inserer Relevé de
									note</label><input name="rn" id="file" type="file"
									class="form-control-file">
							</div>
						</div>
						<div class="col-md-6" id="an">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Inserer Acte de
									naissance</label><input name="an" id="file" type="file"
									class="form-control-file">
							</div>
						</div>
						<div class="col-md-6" id="cin">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Inserer Cin</label><input
									name="cin" id="file" type="file" class="form-control-file">
							</div>
						</div>
					</div>
					
					<button class="mt-2 btn btn-primary col-md-12" type="submit">Valider</button>
				</form>
			</div>
		</div>

		<script>
	function displayInput(idS){
		
		
		var x=document.getElementById(idS+"");
		if (x.style.display === "none") {
		    x.style.display = "block";
		  } else {
		    x.style.display = "none";
		  }
	}
	
	</script>

	</layout:put>
</layout:extends>