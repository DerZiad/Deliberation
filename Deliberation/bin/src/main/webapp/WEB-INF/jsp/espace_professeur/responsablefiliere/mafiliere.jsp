<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="../layout-prof.jsp">
	<layout:put block="content" type="REPLACE">


		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Mes modules</h5>
				<div class="col-md-6">
					<div class="position-relative form-group">
						<label for="name" class="">Filieres</label> <select name="filiere"
							id="exampleSelect" class="form-control">
							<c:forEach var="filiere" items="${filieres}">
								<option value="${filiere.id_filiere }">${filiere.nom_filiere }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<p>Modules</p>
				<table class="mb-0 table table-striped">
					<thead>
						<tr>
							<th>Nom</th>
							<th>Coefficient</th>
						</tr>
					</thead>
					<tbody id="modules">

					</tbody>
				</table>
			</div>
		</div>
		<script>
var modules = JSON.parse('${modulesjson}');

jQuery(document).ready(function(){
	$('select[name=filiere]').change(function(){
		filter();
	});
	filter();
});
function filter(){
	var filiere = $('select[name=filiere]').val();

	var chaine = "";
	for(let i = 0;i<modules.length;i++){
		if(modules[i].id_filiere == filiere){
			chaine = chaine + "<tr>\n";
			chaine = chaine + '<td><a style="color: black" href="/professeur/responsablefiliere/listermodules/'+ modules[i].id_module + '">' + modules[i].libelle_module + '</a></td>';
			chaine = chaine + '<td style="color: black">' +modules[i].coefficient+'</td>';
			chaine = chaine + "</tr>\n";
		}
	}
	$('tbody[id=modules]').html(chaine);
}



		</script>

	</layout:put>
</layout:extends>