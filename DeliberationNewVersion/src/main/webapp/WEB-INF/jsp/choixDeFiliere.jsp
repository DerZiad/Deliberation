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
				<h5 class="card-title">Inscription Administrative de l'Etudiant</h5>
				<form class="" action="/inscription/PageInscriptionPedagogique${module }" method="Post">


					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="Filiere" class="">Filiere</label>
								<select name="filiere" id="filiere" class="form-control" >
								<option default disabled>Choisir la filiére</option>
								<c:forEach var="f" items="${f }">
								<option value="${f.getId_filiere()}">${f.getNom_filiere() }</option>
								</c:forEach>
								</select>
							</div>
						</div>
						</div>
						<c:if test="${m}">
						<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="Filiere" class="">Semsetre</label>
								<select name="semestre" id="filiere" class="form-control" >
								<option default disabled>Choisir la filiére</option>
								<option value="Semestre1">S1</option>
								<option value="Semestre2">S2</option>
								<option value="Semestre3">S3</option>
								<option value="Semestre4">S4</option>
								<option value="Semestre5">S5</option>
								</select>
							</div>
						</div>
						</div>
						
						</c:if>
						
					<button class="mt-2 btn btn-primary col-md-12" type="submit">Voir La liste</button>
				</form>
				
			</div>
		</div>
	</layout:put>
</layout:extends>