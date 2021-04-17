<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="layout.jsp">
	<layout:put block="content" type="REPLACE">


		<div class="row">
			<div class="col-md-3">
				<div class="main-card mb-3 card">
					<div class="card-body">
						<h5 class="card-title">Responsable: ${responsable.prenom_responsable } ${responsable.nom_responsable}</h5>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="main-card mb-3 card">
					<div class="card-body">
						<h5 class="card-title">Modification</h5>
						<form action="/responsable/modifier/${responsable.id_responsable}"
							method="POST">
							<div class="position-relative form-group">
								<label for="nom_responsable" class="">Nom: </label><input name="nom_responsable"
									id="nom_responsable" placeholder="Nom du responsable" type="text"
									class="form-control" value="${responsable.nom_responsable }">
							</div>
							<div class="position-relative form-group">
								<label for="prenom_responsable" class="">Prénom: </label><input name="prenom_responsable"
									id="prenom_responsable" placeholder="Prénom du responsable" type="text"
									class="form-control" value="${responsable.prenom_responsable }">
							</div>
							<div class="position-relative form-group">
								<label for="email_responsable" class="">Email: </label><input name="email_responsable"
									id="email_responsable" placeholder="Email du responsable" type="email"
									class="form-control" value="${responsable.email_responsable }">
							</div>
							<div class="position-relative form-group">
								<label for="username" class="">Nom d'utilisateur: </label><input name="username"
									id="username" placeholder="Nom d'utilisateur" type="text"
									class="form-control" value="${responsable.user.username }">
							</div>
							<div class="position-relative form-group">
								<label for="password" class="">Mot de passe: </label><input name="password"
									id="password" placeholder="Mot de passe" type="password"
									class="form-control" value="${responsable.user.password }" disabled>
									<button type="button" onclick="enable_pass_change()" class="btn btn-danger">Changer le mot de passe</button>
							</div>
							<div class="position-relative form-group" id="div_password_conf" style="display: none;">
								<label for="password" class="">Retapper le mot de passe: </label><input name="password_conf"
									id="password_conf" placeholder="Confirmation du Mot de passe" type="password"
									class="form-control" value="">
							</div>
							<button class="mt-1 btn btn-primary" type="submit">Modifier</button>
							<button class="mt-1 btn btn-danger"
								formaction="/responsable/supprimer/${professeur.id_professeur}" formmethod="POST">Supprimer</button>

						</form>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			function enable_pass_change(){
				document.getElementById("password").disabled = false;
				document.getElementById("div_password_conf").style.display = "block";
			}
		</script>
	</layout:put>
</layout:extends>