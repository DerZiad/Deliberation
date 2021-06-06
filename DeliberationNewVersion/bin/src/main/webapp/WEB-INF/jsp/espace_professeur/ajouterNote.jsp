<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<layout:extends name="layout-prof.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Notes de l'élément</h5>
				<form method="POST" action="/professeur/note/ajouter"
					enctype="multipart/form-data">
					<div class="form-row">
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="type" class="">Type</label>
								<c:forEach var="type" items="${types}">
									<div>
										<input type="radio" id="box" name="type" value="${type}"
											checked> <label for="box">${type}</label>
									</div>
								</c:forEach>
							</div>
							<div class="position-relative form-group">
								<label for="type" class="">Excel</label>
								<div>
									<input type="file" id="file" name="file" value="">
								</div>
							</div>
							<div class="position-relative form-group">
								<label for="name" class="">Coefficient: </label><input
									name="coeficient" id="name" placeholder="Coefficient"
									type="number" class="form-control" value="">
							</div>
						</div>
						<button class="mt-2 btn btn-primary col-md-12" id="valider"
							type="submit">Valider</button>
					</div>
				</form>
			</div>
		</div>
	</layout:put>
</layout:extends>