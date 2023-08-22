<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="../layout.jsp">
	<layout:put block="content" type="REPLACE">


		<div class="row">
			<div class="col-md-9">
				<div class="main-card mb-3 card">
					<div class="card-body">
						<h5 class="card-title">Modification d'une note</h5>
						<c:url var="url" value="/gestionnote/edit/${note.getEtudiant().id_etudiant }/${note.getElement().getId()}">
							<c:param name="type">${note.getElement().getType()}</c:param>
						</c:url>
						<form
							action="${url}"
							method="POST">
							<div class="col-md-6">
								<div class="position-relative form-group">
									<label for="name" class="">Note </label> <input name="note"
										value="${note.getNote() }" id="exampleSelect"
										class="form-control">
								</div>
							</div>
							<div class="col-md-3"></div>
							<div class="col-md-3"></div>
							<div class="col-md-6">
								<button class="mt-1 btn btn-primary" type="submit">Modifier</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>


	</layout:put>
</layout:extends>