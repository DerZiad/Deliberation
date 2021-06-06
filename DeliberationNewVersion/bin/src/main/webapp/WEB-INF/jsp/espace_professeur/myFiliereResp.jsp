<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="layout-resp.jsp">
	<layout:put block="content" type="REPLACE">


		<div class="row">
			<div class="col-md-3">
				<div class="main-card mb-3 card">
					<div class="card-body">
						<h5 class="card-title">filière: ${filiere.nom_filiere }</h5>
						<a href="/filiere/profile/${filiere.id_filiere }/etudiant/liste"><button class="mb-2 mr-2 btn btn-warning btn-block">Les étudiants de la filière</button></a>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="main-card mb-3 card">
					<div class="card-body">
						<h5 class="card-title">Modification</h5>
							<div class="position-relative form-group">
								<label for="name" class="">Nom: </label><input name="name"
									id="name" placeholder="Nom de la filière" type="text"
									class="form-control" value="${filiere.nom_filiere }">
							</div>
							<div class="position-relative form-group">
								<label for="name" class="">Établissement: </label><input name="name"
									id="name" placeholder="Nom de la filière" type="text"
									class="form-control" value="${filiere.etablissement.name }">
							</div>
							<div class="position-relative form-group">
								<label for="semester_number" class="">Nombre de semestres: </label><input name="semester_number"
									id="semester_number" placeholder="Nombre de semestres" type="number"
									class="form-control" value="${semester_number }">
							</div>
					</div>
				</div>
			</div>
		</div>
		
	</layout:put>
</layout:extends>