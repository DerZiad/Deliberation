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
									type="text" class="form-control"
									value="<c:out value="${ia.composite_association_id.etudiant.first_name_fr }"/>">
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="last_name_ar" class="">Nom en arabe</label><input
									name="last_name_ar" id="last_name_ar" placeholder=""
									type="text" class="form-control"
									value="<c:out value="${ia.composite_association_id.etudiant.first_name_ar }" />" />
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="first_name_fr" class="">Prénom en français</label><input
									name="first_name_fr" id="first_name_fr" placeholder=""
									type="text" class="form-control"
									value="<c:out value="${ia.composite_association_id.etudiant.last_name_fr }"/>" />
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="first_name_ar" class="">Prénom en arabe</label><input
									name="first_name_ar" id="first_name_ar" placeholder=""
									type="text" class="form-control"
									value="<c:out value="${ia.composite_association_id.etudiant.last_name_ar }"/>" />
							</div>
						</div>
					</div>

					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="massar_edu" class="">Code Massar</label><input
									name="massar_edu" id="massar_edu" placeholder="" type="text"
									class="form-control"
									value="<c:out value="${ia.composite_association_id.etudiant.massar_edu }"/>" />
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="cne" class="">CNE</label><input name="cne" id="cne"
									placeholder="" type="text" class="form-control"
									value="<c:out value="${ia.composite_association_id.etudiant.cne }"/>" />
							</div>
						</div>
					</div>

					<div class="form-row">
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="nationality" class="">Nationalité</label> <select
									name="nationality" id="exampleSelect" class="form-control">
									<c:forEach var="country" items="${countries}">
										<option value="${country.keyCountry}"
											<c:if test="${country eq ia.composite_association_id.etudiant.nationality}">selected</c:if>><c:out
												value="${country.valueCountry}" /></option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="city" class="">Ville</label><input name="city"
									id="city" placeholder="" type="text" class="form-control"
									value="<c:out value="${ia.composite_association_id.etudiant.city }"/>" />
							</div>
						</div>

						<div class="col-md-6">

							<div class="card-body">
								<h5 class="card-title">Sexe</h5>
								<div class="position-relative form-group">
									<div>
										<div class="custom-radio custom-control">
											<input type="radio" id="male" value="HOMME" name="gender"
												class="custom-control-input" checked><label
												class="custom-control-label" for="male">Homme</label>
										</div>
										<div class="custom-radio custom-control">
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
									name="birth_date" id="birth_date" type="date"
									class="form-control"
									value="<c:out value="${ia.composite_association_id.etudiant.birth_date.toString().substring(0,10)}"/>" />
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="birth_place" class="">Lieu de naissance</label><input
									name="birth_place" id="birth_place" placeholder="" type="text"
									class="form-control"
									value="<c:out value="${ia.composite_association_id.etudiant.birth_place}"/>" />
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="province" class="">Province</label><input
									name="province" id="province" placeholder="" type="text"
									value="<c:out value="${ia.composite_association_id.etudiant.province}"/>"
									class="form-control" />
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="bac_year" class="">Année bac</label><input
									name="bac_year" id="bac_year" placeholder="" type="number"
									value="<c:out value="${ia.composite_association_id.etudiant.bac_year}"/>"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="bac_type" class="">Type du bac</label><input
									name="bac_type" id="bac_type" placeholder="" type="text"
									value="<c:out value="${ia.composite_association_id.etudiant.bac_type}"/>"
									class="form-control" />
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="mention" class="">Mention</label><input
									name="mention" id="mention" placeholder="" type="text"
									value="<c:out value="${ia.composite_association_id.etudiant.mention}"/>"
									class="form-control" />
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="high_school" class="">Lycée</label><input
									name="high_school" id="high_school" placeholder="" type="text"
									value="<c:out value="${ia.composite_association_id.etudiant.high_school}"/>"
									class="form-control" />
							</div>
						</div>
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="bac_place" class="">Lieu d'obtention du bac</label><input
									name="bac_place" id="bac_place" placeholder="" type="text"
									value="<c:out value="${ia.composite_association_id.etudiant.bac_place}"/>"
									class="form-control" />
							</div>
						</div>
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="academy" class="">Académie</label><input
									name="academy" id="academy" placeholder="" type="text"
									value="<c:out value="${ia.composite_association_id.etudiant.academy}"/>"
									class="form-control">
							</div>
						</div>
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="email" class="">Email</label><input name="email"
									id="email" placeholder="" type="text" class="form-control"
									value="<c:out value="${ia.composite_association_id.etudiant.email}"/>"
									disabled>
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
									value="<c:out value="${ia.date_valid_inscription.toString().substring(0,10)}"/>"
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
						<div class="col-md-6" id="cin">
							<div class="position-relative form-group alert alert-primary">
								<div class="row">
									<div class="col-md-6">
										<img style='display: block; width: 100px; height: 100px;'
											id='base64image'
											src='data:image/jpeg;base64,${ia.encodedPhoto}' />
									</div>
									<div class="col-md-6">
										<div class="col-md-6">
											<label for="photo" class="">Inserer Photo</label> <input
												name="photo" id="file" type="file" />
										</div>
									</div>

								</div>

							</div>
						</div>

						<div class="col-md-6" id="cin">
							<div class="position-relative form-group alert alert-primary">
								<div class="row">
									<div class="col-md-6">
										<img style='display: block; width: 100px; height: 100px;'
											id='base64image'
											src='data:image/jpeg;base64,${ia.encodedBac}' />
									</div>
									<div class="col-md-6">
										<div class="col-md-6">
											<label for="file" class="">Inserer Bac</label> <input
												class="form-control-file" name="bac" id="file" type="file" />
										</div>
									</div>

								</div>

							</div>
						</div>

						<div class="col-md-6" id="cin">
							<div class="position-relative form-group alert alert-primary">
								<div class="row">
									<div class="col-md-6">
										<img style='display: block; width: 100px; height: 100px;'
											id='base64image'
											src='data:image/jpeg;base64,${ia.encodedAn}' />
									</div>
									<div class="col-md-6">
										<div class="col-md-6">
											<label for="photo" class="">Inserer Acte de naissance</label>
											<input name="an" id="file" type="file" />
										</div>
									</div>

								</div>

							</div>
						</div>


						<div class="col-md-6" id="cin">
							<div class="position-relative form-group alert alert-primary">
								<div class="row">
									<div class="col-md-6">
										<img style='display: block; width: 100px; height: 100px;'
											id='base64image'
											src='data:image/jpeg;base64,${ia.encodedRv}' />
									</div>
									<div class="col-md-6">
										<div class="col-md-6">
											<label for="file" class="">Inserer Relevé de note</label> <input
												name="rn" id="file" type="file" />
										</div>
									</div>

								</div>

							</div>
						</div>


						<div class="col-md-6" id="cin">
							<div class="position-relative form-group alert alert-primary">
								<div class="row">
									<div class="col-md-6">
										<img style='display: block; width: 100px; height: 100px;'
											id='base64image'
											src='data:image/jpeg;base64,${ia.encodedCin}' />
									</div>
									<div class="col-md-6">
										<div class="col-md-6">
											<label for="cin" class="">Inserer Cin</label> <input
												name="cin" id="file" type="file" />
										</div>
									</div>

								</div>

							</div>
						</div>
					</div>



					<button class="mt-2 btn btn-primary col-md-12" type="submit">Valider</button>

				</form>

			</div>
		</div>
	</layout:put>
</layout:extends>