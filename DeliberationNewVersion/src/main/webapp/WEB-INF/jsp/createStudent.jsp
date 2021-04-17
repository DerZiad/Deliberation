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
				<h5 class="card-title">Création d'un étudiant</h5>
				<form class="" action="/student/create" method="POST">
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
								<jsp:include page="country-select.jsp"></jsp:include>
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
												class="custom-control-input"><label
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
								<label for="establishment" class="">établissement</label><input
									name="establishment" id="establishment" placeholder=""
									type="text" class="form-control">
							</div>
						</div>
					</div>
					<button class="mt-2 btn btn-primary col-md-12" type="submit">Valider</button>
				</form>
			</div>
		</div>

	</layout:put>
</layout:extends>