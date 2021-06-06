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
				<h5 class="card-title">Mes étudiants</h5>
				<form class="form-group"
					action="/professeur/listerElements/${element.id_element}"
					method="POST">
					<div class="col-md-6">
						<div class="position-relative form-group">
							<label for="name" class="">Années academiques</label> <select
								name="annee" id="exampleSelect" class="form-control">
								<c:forEach var="annee" items="${annees}">
									<option value="${annee.id_annee_academique }">${annee.annee_academique }</option>
								</c:forEach>
							</select>
						</div>
						<div class="position-relative form-group">
							<button class="btn btn-success">Download excel</button>
						</div>
					</div>


				</form>
				<table class="mb-0 table table-striped" id="myTable">
					<thead>
						<tr>
							<th class="th-sm">CNE</th>
							<th class="th-sm">Nom</th>
							<th class="th-sm">Prénom</th>
						</tr>
					</thead>
					<tbody id="etudiants">
						<c:forEach var="inscription" items="${inscriptions}">
							<tr>
								<td><a style="color: black" href="#">${inscription.etudiant.massar_edu}</a></td>
								<td><a style="color: black" href="#">${inscription.etudiant.last_name_fr}</a></td>
								<td><a style="color: black" href="#">${inscription.etudiant.first_name_fr}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<script>
var annees = JSON.parse('${anneesjson}');
var inscriptions_pedagogiques = JSON.parse('${inscriptionsjson}');
jQuery(document).ready(function(){
	$('select[name=annee]').change(function(){
		var list_value = $('select[name=annee]').val();
		var remplir = "";
		for (var i = 0;i<inscriptions_pedagogiques.length;i++) {
			if(inscriptions_pedagogiques[i].id_annee_academique == list_value){
				remplir = remplir + '<tr>' + '\n';
				remplir = remplir + '<td><a style="color: black" href="#">' + inscriptions_pedagogiques[i].massar + '</a></td> \n';
				remplir = remplir + '<td><a style="color: black" href="#">' + inscriptions_pedagogiques[i].nom_etudiant + '</a></td> \n';
				remplir = remplir + '<td><a style="color: black" href="#">' + inscriptions_pedagogiques[i].prenom_etudiant + '</a></td> \n';
				remplir = remplir + '</tr>' + '\n';
			}
		}
		$('tbody[id=etudiants]').html(remplir);
	});
});
</script>

	</layout:put>
</layout:extends>