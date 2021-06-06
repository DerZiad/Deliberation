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
					<div class="card-body">L'etape suivant :
						${etape.libelle_etape}</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="main-card mb-3 card">
					<div class="card-body">
						<h5 class="card-title">Modification</h5>
						<form class=""
							action="/admin/filiere/profile/${semestre.etape.filiere.id_filiere}/etapes/semestres/modify/${semestre.id_semestre}"
							method="POST">
							<div class="form-row">
								<div class="col-md-6">
									<div class="position-relative form-group">
										<label for="name" class="">Libelle semestre</label><input
											name="libelle_semestre" id="name" placeholder="" type="text"
											value="${semestre.libelle_semestre}" class="form-control">
									</div>
								</div>
								<div class="col-md-6">
									<div class="position-relative form-group">
										<label for="name" class="">Validation</label><input
											name="validation" id="name" placeholder="Validation"
											value="${semestre.validation }" type="text"
											class="form-control">
									</div>
								</div>
								<div class="col-md-6">
									<div class="position-relative form-group">
										<label for="name" class="">Etapes</label><select name="etape"
											id="name" class="form-control">
											<c:forEach var="etape" items="${semestre.etape.filiere.etapes}">
												<option value="${etape.id_etape }"
													<c:if test="${semestre.etape.id_etape eq etape.id_etape}">selected</c:if>>${etape.libelle_etape }</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>

							<button class="mt-2 btn btn-primary col-md-12" type="submit">Valider</button>
							<button class="mt-2 btn btn-danger col-md-12"
								formaction="/admin/filiere/profile/${semestre.etape.filiere.id_filiere}/etapes/semestres/delete/${semestre.id_semestre}"
								formmethod="GET">Supprimer</button>
						</form>
					</div>
				</div>
			</div>
		</div>


	</layout:put>
</layout:extends>