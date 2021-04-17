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
				<h5 class="card-title">Créer filière</h5>
				<form class="" action="/filiere/creer" method="Post">

					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="Filiere" class="">Filiere</label><input
									name="filiere" id="filiere" placeholder="Nom de la filière"
									type="text" class="form-control" required>
							</div>
						</div>
					</div>
					
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="Filiere" class="">Nombre de semestres</label><input
									name="semester_number" id="semester_number" placeholder="nombre de semestres"
									min="0" type="number" class="form-control" required>
							</div>
						</div>
					</div>
						
					<button class="mt-2 btn btn-primary col-md-12" type="submit">Valider</button>
				</form>
				
			</div>
		</div>
	</layout:put>
</layout:extends>