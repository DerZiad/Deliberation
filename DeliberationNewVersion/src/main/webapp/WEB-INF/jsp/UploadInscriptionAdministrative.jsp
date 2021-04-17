<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<layout:extends name="layout.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Inscription Administrative de l'Etudiant</h5>
			<form action="/inscription/UploadInscriptionAdministrative" method="post" enctype="multipart/form-data">					

					

					
					<div class="form-row">
						
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Inserer fichier</label><input name="file" id="file" type="file" class="form-control-file">
							</div>
						</div>
					</div>
						
					<button class="mt-2 btn btn-primary col-md-12" type="submit">Valider</button>
				</form>
			</div>
		</div>

	</layout:put>
</layout:extends>