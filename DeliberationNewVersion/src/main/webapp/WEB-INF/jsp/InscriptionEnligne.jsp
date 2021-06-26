<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<layout:extends name="layout.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Liste des inscriptions en lignes</h5>				
				<div class="row">
					<div class="col-md-12">
						<label>Afficher que les inscriptions acceptées</label>
						<input type="checkbox" id="filtercheck" name="accepter" value="1"/>	
					</div>
				</div>				
				<table class="mb-0 table table-striped" id="myTable">
					<thead>
						<tr>
							<th class="th-sm">CNE</th>
							<th class="th-sm">Nom</th>
							<th class="th-sm">Prénom</th>
							<th class="th-sm">Sexe</th>
							<th class="th-sm">Nationalité</th>
							<th class="th-sm">Date de naissance</th>
							<th class="th-sm">Type du Bac</th>
							<th class="th-sm">Mention</th>
							<th class="th-sm">Accepté</th>
							<th class="th-sm">Inscription Administrative</th>
							<th class="th-sm">Action</th>
						</tr>
					</thead>
					<tbody id="inscriptions">

					</tbody>
				</table>
			</div>
		</div>
		<script>
var inscriptionsJson = JSON.parse('${inscriptions}');
jQuery(document).ready(function(){

$('input[id=filtercheck]').click(function(){
	filter();
});
filter();
});

function filter(){

	var chaine = "";
	let inscriptionAccepted = 1;
	
	if (!$('input[id=filtercheck]').is(':checked')) {
		inscriptionAccepted = 0;
	}
	console.log(inscriptionAccepted);
	chaine = chaine + "<tr>\n";
	for (var i = 0; i < inscriptionsJson.length; i++) {
		if(inscriptionAccepted == 1){
			if(inscriptionsJson[i].acceptedParAdmin == 1){
				chaine = chaine + '<td><a style="color: black" href="#">' + inscriptionsJson[i].cne + '</a></td>';
				chaine = chaine + '<td><a style="color: black" href="#">' + inscriptionsJson[i].last_name_fr + '</a></td>';
				chaine = chaine + '<td><a style="color: black" href="#">' + inscriptionsJson[i].first_name_fr + '</a></td>';
				chaine = chaine + '<td><a style="color: black" href="#">' + inscriptionsJson[i].gender+ '</a></td>';
				chaine = chaine + '<td><a style="color: black" href="#">' + inscriptionsJson[i].nationality.valueCountry + '</a></td>';
				chaine = chaine + '<td><a style="color: black" href="#">' + inscriptionsJson[i].birth_date + '</a></td>';
				chaine = chaine + '<td><a style="color: black" href="#">' + inscriptionsJson[i].bac_type + '</a></td>';
				chaine = chaine + '<td><a style="color: black" href="#">' + inscriptionsJson[i].mention + '</a></td>';
				chaine = chaine + '<td style="color: black">Accepté</td>';
				chaine = chaine + '<td><a href="/admin/inscription/InscriptionAdministrative/' + inscriptionsJson[i].id_inscription_en_ligne + '" style="font-size: 20px;"><i class="fas fa-plus-square" aria-hidden="true" title="edit"></i></a></td>';
			}
		}else{
				chaine = chaine + '<td><a style="color: black" href="#">' + inscriptionsJson[i].cne + '</a></td>';
				chaine = chaine + '<td><a style="color: black" href="#">' + inscriptionsJson[i].last_name_fr + '</a></td>';
				chaine = chaine + '<td><a style="color: black" href="#">' + inscriptionsJson[i].first_name_fr + '</a></td>';
				chaine = chaine + '<td><a style="color: black" href="#">' + inscriptionsJson[i].gender+ '</a></td>';
				chaine = chaine + '<td><a style="color: black" href="#">' + inscriptionsJson[i].nationality.valueCountry + '</a></td>';
				chaine = chaine + '<td><a style="color: black" href="#">' + inscriptionsJson[i].birth_date + '</a></td>';
				chaine = chaine + '<td><a style="color: black" href="#">' + inscriptionsJson[i].bac_type + '</a></td>';
				chaine = chaine + '<td><a style="color: black" href="#">' + inscriptionsJson[i].mention + '</a></td>';
				chaine = chaine + '<td style="color: black">';
				if(inscriptionsJson[i].acceptedParAdmin == 1){
					chaine = chaine + 'Accepté</td>';
				}else{
					chaine = chaine + 'Non accepté</td>'
				}
				chaine = chaine + '<td><a href="/admin/inscription/InscriptionAdministrative/' + inscriptionsJson[i].id_inscription_en_ligne + '" style="font-size: 20px;"><i class="fas fa-plus-square" aria-hidden="true" title="edit"></i></a></td>';
				if(inscriptionsJson[i].acceptedParAdmin == 0){
					chaine = chaine + '<td><a href="/admin/inscriptionenligne/accept/' + inscriptionsJson[i].id_inscription_en_ligne + '"><i class="far fa-check-circle"></i></a></td>';
					chaine = chaine + '<td><a href="/admin/inscriptionenligne/delete/' + inscriptionsJson[i].id_inscription_en_ligne + '"><i class="fas fa-backspace"></i></a></td>';
		
				}
		}
	}
	chaine = chaine + "</tr>\n";
	$('tbody[id=inscriptions]').html(chaine);
}
		</script>

	</layout:put>
</layout:extends>