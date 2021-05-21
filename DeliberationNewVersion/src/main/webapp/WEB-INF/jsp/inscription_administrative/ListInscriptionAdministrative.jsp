<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<layout:extends name="../layout.jsp">
	<layout:put block="content" type="REPLACE">
		<script>
jQuery(document).ready(function(){
		$("input[name=checkfiliere]").click(function(){
				enable_cb($('input[name=checkfiliere]'),"select[id=listfiliere]","id_filiere");
		});
		
		$("input[name=checkannee]").click(function(){
				enable_cb($('input[name=checkannee]'),"select[id=listannee]","id_annee_academique");
		});
		
		$("input[name=checksemestre]").click(function(){
				enable_cb($('input[name=checksemestre]'),"select[id=listsemestre]","id_semestre");
		});
		
		$("input[name=checkmodule]").click(function(){
				enable_cb($('input[name=checkmodule]'),"select[id=listmodule]","id_module");
		});
});


function enable_cb(checkboxobject,select,oldname) {
	if (checkboxobject.is(':checked')) {
		$(select).attr("name",oldname);
		$(select).removeAttr("disabled");
	} else {
		console.log("je suis non checked");
		$(select).attr("disabled", true);
		$(select).attr("name","auto");
	}
}
		</script>
		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Liste des inscriptions administratives
					des etudiants</h5>
				<form action="/admin/inscription/ListInscriptionAdministrative"
					method="POST">
					<div class="col-md-6">
						<div class="position-relative form-group">
							<input type="checkbox" class="form-class" name="checkfiliere" checked/>
							<label for="Filiere" class="">Filiere</label> <select
								id="listfiliere" name="id_filiere" class="form-control">
								<c:forEach var="filiere" items="${filieres}">
									<option value="${filiere.id_filiere }">${filiere.nom_filiere }</option>
								</c:forEach>

							</select>
						</div>
					</div>
					<div class="col-md-6">
						<div class="position-relative form-group">
							<input type="checkbox" class="form-class" name="checkannee" checked/> <label
								for="Filiere" class="">Année universitaire</label> <select id="listannee"
								name="id_annee_academique" id="myInput" class="form-control">
								<c:forEach var="annee" items="${annees_academiques}" >
									<option value="${annee.id_annee_academique }">${annee.annee_academique }</option>
								</c:forEach>

							</select>
						</div>
					</div>
					<div class="col-md-6">
						<div class="position-relative form-group">
							<input type="checkbox" class="form-class" name="checksemestre" checked/>
							<label for="semestre" class="">Semestre</label> <select id="listsemestre"
								name="id_semestre" id="myInput" class="form-control">
								<c:forEach var="semestre" items="${semestres}">
									<option value="${semestre.id_semestre }">${semestre.libelle_semestre }</option>
								</c:forEach>

							</select>
						</div>

					</div>
					<div class="col-md-6">
						<div class="position-relative form-group">
							<input type="checkbox" class="form-class" name="checkmodule" checked/>
							<label for="module" class="">Module</label> <select id="listmodule"
								name="id_module" id="myInput" class="form-control">
								<c:forEach var="module" items="${modules}">
									<option value="${module.id_module }">${module.libelle_module }</option>
								</c:forEach>

							</select>
						</div>

					</div>
					<div class="col-md-9">
						<div class="position-relative form-group">
							<button class="btn btn-success">Valider</button>
						</div>

					</div>
				</form>

				<table class="mb-0 table table-hover" id="myTable">
					<thead>
						<tr>
							<th class="th-sm">Massar</th>
							<th class="th-sm">Nom Etudiant</th>
							<th class="th-sm">Date de preinscription</th>
							<th class="th-sm">Date Validation d'inscription</th>
							<th class="th-sm">Inscription administrative</th>
							<th class="th-sm">Inscription pédagogique</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="inscription" items="${InscriptionAssociative}">
							<tr>
								<td><a style="color: black">
										${inscription.composite_association_id.etudiant.massar_edu}</a></td>
								<td><a style="color: black">
										${inscription.composite_association_id.etudiant.first_name_fr}
										${inscription.composite_association_id.etudiant.last_name_fr}</a>
								</td>
								<td><a style="color: black">
										${inscription.date_pre_inscription.toString().substring(0,10)}</a>
								</td>
								<td><a style="color: black">
										${inscription.date_valid_inscription.toString().substring(0,10)}</a>
								</td>

								<td><i class="fa fa-fw" aria-hidden="true"
									title="Copy to use pencil-square-o"><a
										href="PageModifierInscriptionAdministrative/${inscription.composite_association_id.filiere.id_filiere}/${inscription.composite_association_id.etudiant.id_etudiant }"
										style="font-size: 20px;"></a></i> <i class="fa fa-fw"
									aria-hidden="true" title="Copy to use trash"> <a
										href="SupprimerInscriptionAdministrative/${inscription.composite_association_id.filiere.id_filiere}/${inscription.composite_association_id.etudiant.id_etudiant }"
										style="color: red; font-size: 20px;"></a>
								</i></td>

								<td><a
									href="/admin/inscriptionpedagogique/creer/${ inscription.filiere.id_filiere}/${inscription.etudiant.id_etudiant}"
									style="font-size: 20px;"><i class="fas fa-plus-square"
										aria-hidden="true" title="edit"></i></a> <a
									href="/admin/inscriptionpedagogique/listerinscriptions/${inscription.etudiant.id_etudiant}"
									style="color: red; font-size: 20px;"><i class="fas fa-list"
										aria-hidden="true" title="list"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</layout:put>
</layout:extends>