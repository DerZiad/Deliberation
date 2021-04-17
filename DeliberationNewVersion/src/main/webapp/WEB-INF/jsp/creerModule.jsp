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
				<h5 class="card-title">Créer module</h5>
				<form class="" action="/module/creer" method="Post">

					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="Module" class="">Nom du Module</label><input
									name="libelle_module" id="libelle_module" placeholder="Nom du module"
									type="text" class="form-control" required>
							</div>
						</div>
					</div>
					
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="Semestre_Etape_Filiere" class="">Semestre - Etape - Filiere</label>
								<select name="id_semestre" id="semestre" class="form-control" >
								<option default disabled>Choisir le semestre</option>
								<c:forEach var="filiere" items="${filieres}">
									<c:forEach var="etape" items="${filiere.etapes }">
										<c:forEach var="semestre" items="${etape.getSemestres() }">
											<option value="${semestre.id_semestre}">${semestre.libelle_semestre} - ${etape.libelle_etape} - ${filiere.nom_filiere}</option>
										</c:forEach>
									</c:forEach>
								</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="bac_year" class="">Nombre d'element de module</label><input
									name="element_number" id="bac_year" placeholder=""
									type="number" class="form-control" required>
							</div>
							</div>
						</div>
					<button class="mt-2 btn btn-primary col-md-12" type="submit">Valider</button>
				</form>
				
			</div>
		</div>
	</layout:put>
</layout:extends>