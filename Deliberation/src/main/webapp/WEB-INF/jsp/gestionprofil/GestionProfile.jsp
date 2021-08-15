<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="${mero}">
	<layout:put block="content" type="REPLACE">


		<div class="row">
			<div class="col-md-9">
				<div class="main-card mb-3 card">
					<div class="card-body">
						<h5 class="card-title">Modification de profil</h5>
						<form action="/gestioncompte" method="POST" class="alert alert-primary">
							<input type="hidden" name="action" value="changepassword" />
							<div class="position-relative form-group">
								<label for="name" class="">Mot de passe: </label><input
									name="password" type="password" class="form-control">
							</div>
							<div class="position-relative form-group">
								<label for="name" class="">Confirmer mot de passe: </label><input
									name="cfpassword" type="password" class="form-control">
							</div>
							<c:if test="${message ne null }">
								<div class="alert alert-succes" role="alert">
									<c:out value="${message}" />
								</div>
							</c:if>
							<c:if test="${error ne null }">
								<div class="alert alert-danger" role="alert">
									<c:out value="${error}" />
								</div>
							</c:if>
							<button class="mt-1 btn btn-primary" type="submit">Modifier</button>
						</form>
						<c:if test="${not user.isAdministrator() }">
							<form action="/gestioncompte" method="POST" class="alert alert-primary">
								<input type="hidden" name="action" value="changeemail" />
								<div class="position-relative form-group">
									<label for="name" class="">Email: </label><input name="email"
										type="text" class="form-control" value="${user.username }">
								</div>
								<c:if test="${message ne null }">
									<div class="alert alert-succes" role="alert">
										<c:out value="${message}" />
									</div>
								</c:if>
								<c:if test="${error ne null }">
									<div class="alert alert-danger" role="alert">
										<c:out value="${error}" />
									</div>
								</c:if>
								<button class="mt-1 btn btn-primary" type="submit">Modifier</button>
							</form>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</layout:put>
</layout:extends>