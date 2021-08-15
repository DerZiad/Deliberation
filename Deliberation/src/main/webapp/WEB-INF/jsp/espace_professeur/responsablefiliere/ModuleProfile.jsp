<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="../layout-prof.jsp">
	<layout:put block="content" type="REPLACE">
		<div class="row">
			<div class="col-md-3">
				<div class="main-card mb-3 card">
					<div class="card-body">
						<h5 class="card-title">Module: ${module.libelle_module }</h5>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="main-card mb-3 card">
					<div class="card-body">
						<h5 class="card-title">Modification</h5>
						<form
							action="/professeur/responsablefiliere/listermodules/${module.id_module}"
							method="POST">
							<div class="position-relative form-group">
								<p>${module.libelle_module }</p>
							</div>
							<div class="position-relative form-group">
								<label for="coefficient" class="">Coefficient: </label><input
									name="coefficient" id="coeficient" placeholder="Coefficient"
									type="text" class="form-control" value="${module.coeficient }">
							</div>
							<button class="mt-1 btn btn-primary">Modifier</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</layout:put>
</layout:extends>