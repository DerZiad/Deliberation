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
				<h5 class="card-title">Création d'un établissement</h5>
				<form class="" action="/admin/etablissement/creer" method="POST">
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="name" class="">Nom de l'établissement</label><input
									name="name" id="name" placeholder="Nom de l'établissement"
									type="text" class="form-control">
							</div>
						</div>
					</div>
					<button class="mt-2 btn btn-primary col-md-6" type="submit">Valider</button>
				</form>
			</div>
		</div>

	</layout:put>
</layout:extends>