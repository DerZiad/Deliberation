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
					
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="last_name_fr" class="">Nom en français</label><input
									name="last_name_fr" id="last_name_fr" placeholder=""
									type="text" class="form-control">
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="last_name_ar" class="">Nom en arabe</label><input
									name="last_name_ar" id="last_name_ar" placeholder=""
									type="text" class="form-control">
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="first_name_fr" class="">Prénom en français</label><input
									name="first_name_fr" id="first_name_fr" placeholder=""
									type="text" class="form-control">
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="first_name_ar" class="">Prénom en arabe</label><input
									name="first_name_ar" id="first_name_ar" placeholder=""
									type="text" class="form-control">
							</div>
						</div>
					</div>

					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="massar_edu" class="">Code Massar</label><input
									name="massar_edu" id="massar_edu" placeholder="" type="text"
									class="form-control">
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="cne" class="">CNE</label><input name="cne" id="cne"
									placeholder="" type="text" class="form-control">
							</div>
						</div>
					</div>

					<div class="form-row">
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="nationality" class="">Nationalité</label>
								<jsp:include page="../etudiant/country-select.jsp"></jsp:include>
							</div>
						</div>
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="city" class="">Ville</label><input name="city"
									id="city" placeholder="" type="text" class="form-control">
							</div>
						</div>

						<div class="col-md-6">

							<div class="card-body">
								<h5 class="card-title">Sexe</h5>
								<div class="position-relative form-group">
									<div>
										<div
											class="custom-radio custom-control">
											<input type="radio" id="male" value="HOMME" name="gender"
												class="custom-control-input" checked><label
												class="custom-control-label" for="male">Homme</label>
										</div>
										<div
											class="custom-radio custom-control">
											<input type="radio" id="female" value="FEMME" name="gender"
												class="custom-control-input"><label
												class="custom-control-label" for="female">Femme</label>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
					
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="birth_date" class="">Date de naissance</label><input
									name="birth_date" id="birth_date"
									type="date" class="form-control">
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="birth_place" class="">Lieu de naissance</label><input
									name="birth_place" id="birth_place" placeholder=""
									type="text" class="form-control">
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="province" class="">Province</label><input
									name="province" id="province" placeholder=""
									type="text" class="form-control">
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="bac_year" class="">Année bac</label><input
									name="bac_year" id="bac_year" placeholder=""
									type="number" class="form-control">
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="bac_type" class="">Type du bac</label><input
									name="bac_type" id="bac_type" placeholder=""
									type="text" class="form-control">
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="mention" class="">Mention</label><input
									name="mention" id="mention" placeholder=""
									type="text" class="form-control">
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="high_school" class="">Lycée</label><input
									name="high_school" id="high_school" placeholder=""
									type="text" class="form-control">
							</div>
						</div>
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="bac_place" class="">Lieu d'obtention du bac</label><input
									name="bac_place" id="bac_place" placeholder=""
									type="text" class="form-control">
							</div>
						</div>
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="academy" class="">Académie</label><input
									name="academy" id="academy" placeholder=""
									type="text" class="form-control">
							</div>
						</div>
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="email" class="">Email</label><input
									name="email" id="email" placeholder=""
									type="text" class="form-control" disabled>
							</div>
						</div>
					</div>
	
										
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


			<img style='display:block; width:100px;height:100px;' id='base64image'                 
       src='data:image/jpeg;base64,${ia.encodedPhoto}' />
			
			
			<img src="data:image/jpeg;base64, LzlqLzRBQ...<!-- base64 data -->" class="rounded mx-auto d-block" alt="...">
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