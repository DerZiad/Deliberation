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
					action="/admin/inscription/ModifierInscriptionAdministrative/${ia.composite_association_id.filiere.id_filiere}/${ia.composite_association_id.etudiant.id_etudiant}"
					method="POST" enctype="multipart/form-data">

					<div class="col-md-6">
						<div class="position-relative form-group">
							<label for="Filiere" class="">Filiere</label> <select
								name="filiere" id="exampleSelect" class="form-control">
								<c:forEach var="filiere" items="${filieres }">
									<option value="${filiere.id_filiere}">${filiere.nom_filiere}</option>
								</c:forEach>
							</select>
						</div>

					</div>

					<div class="form-row">
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="date_pre_inscription" class="">Date de
									preinscription</label><input name="date_pre_inscription"
									id="date_pre_inscription"
									value="${ia.date_pre_inscription.toString().substring(0,10)}"
									type="date" class="form-control">
							</div>
						</div>
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="date_valid_inscription" class="">Date
									Validation d'inscription</label><input name="date_valid_inscription"
									id="date_valid_inscription"
									value="${ia.date_valid_inscription.toString().substring(0,10)}"
									type="date" class="form-control">
							</div>
						</div>
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Année academique</label>
								<select id="annee_academique" name="annee_academique"
									class="form-control" required>
									<c:forEach var="annee" items="${annees_academiques}">
										<option value="${annee.id_annee_academique }">${annee.annee_academique}/${annee.annee_academique + 1}
										</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>


					<div class="form-row">
						<div class="col-md-6" id="photo" style="display: none">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Inserer photo</label>
								<c:if test="${ia.photo != null }">
									<i class="fa fa-fw" aria-hidden="true"
										title="Copy to use check-circle"></i>
								</c:if>
								<c:if test="${ia.photo == null }">
									<i class="fa fa-fw" aria-hidden="true"
										title="Copy to use times-circle"></i>
								</c:if>
								<input name="photo" id="file" type="file"
									class="form-control-file">
							</div>
						</div>
						<div class="col-md-6" id="bac" style="display: none">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Inserer Bac</label>
								<c:if test="${ia.bac != null }">
									<i class="fa fa-fw" aria-hidden="true"
										title="Copy to use check-circle"></i>
								</c:if>
								<c:if test="${ia.bac == null }">
									<i class="fa fa-fw" aria-hidden="true"
										title="Copy to use times-circle"></i>
								</c:if>
								<input name="bac" id="file" type="file"
									class="form-control-file">
							</div>
						</div>

						<div class="col-md-6" id="rn" style="display: none">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Inserer Relevé de
									note</label>
								<c:if test="${ia.releve_note != null }">
									<i class="fa fa-fw" aria-hidden="true"
										title="Copy to use check-circle"></i>
								</c:if>
								<c:if test="${ia.releve_note == null }">
									<i class="fa fa-fw" aria-hidden="true"
										title="Copy to use times-circle"></i>
								</c:if>
								<input name="rn" id="file" type="file" class="form-control-file">
							</div>
						</div>
						<div class="col-md-6" id="an" style="display: none">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Inserer Acte de
									naissance</label>
								<c:if test="${ia.acte_naissance != null }">
									<i class="fa fa-fw" aria-hidden="true"
										title="Copy to use check-circle"></i>
								</c:if>
								<c:if test="${ia.acte_naissance == null }">
									<i class="fa fa-fw" aria-hidden="true"
										title="Copy to use times-circle"></i>
								</c:if>
								<input name="an" id="file" type="file" class="form-control-file">
							</div>
						</div>
						<div class="col-md-6" id="cin" style="display: none">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Inserer Cin</label>
								<c:if test="${ia.cin != null }">
									<i class="fa fa-fw" aria-hidden="true"
										title="Copy to use check-circle"></i>
								</c:if>
								<c:if test="${ia.cin == null }">
									<i class="fa fa-fw" aria-hidden="true"
										title="Copy to use times-circle"></i>
								</c:if>
								<input name="cin" id="file" type="file"
									class="form-control-file">
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