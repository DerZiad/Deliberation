<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<layout:extends name="layout.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
			<form method="POST" action="/inscription/createANewInscriptionPedagogique" id="myForm">
				<h5 class="card-title">Inscription Pédagogique de l'Etudiant</h5>
				


					<div class="form-row" id="myDiv">

						<input name="id_ias" id="ok" type="text" style="display: none">
							<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="Filiere" class="">Année universitaire</label>
								<select name="annee" id="myInput" class="form-control" onchange="myFunction()">
								<option default disabled>Choisir l'année universitaire</option>
								<option value="${annee-1}/${annee}">${annee-1}/${annee}</option>
								<option value="${annee-2}/${annee-1}">${annee-2}/${annee-1}</option>
								<option value="${annee-3}/${annee-2}">${annee-3}/${annee-2}</option>
								<option value="${annee-4}/${annee-3}">${annee-4}/${annee-3}</option>
								<option value="${annee-5}/${annee-4}">${annee-5}/${annee-4}</option>
								<option value="${annee-6}/${annee-5}">${annee-6}/${annee-5}</option>
								</select>
							</div>
				
						</div>

					</div>
					
				
			<div  class="tabcontent">
				<table class="mb-0 table table-hover" id="myTable">
					<thead>
						<tr>
							<th class="th-sm">Nom Etudiant</th>
							<th class="th-sm">Année universitaire</th>
							<th class="th-sm">Filiere</th>
							<th class="th-sm">Selectionner</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="i" items="${Inscription}" >
							<tr>
								<td onclick="window.location.href = '/inscription/PageInscriptionPedagogiqueIndividuelle?id=${i.id_ia}'"><a style="color: black">${i.etudiant.first_name_fr} ${i.etudiant.last_name_fr}</a></td>
								<td onclick="window.location.href = '/inscription/PageInscriptionPedagogiqueIndividuelle?id=${i.id_ia}'"><a style="color: black">${i.annee_academique}</a></td>
								<td onclick="window.location.href = '/inscription/PageInscriptionPedagogiqueIndividuelle?id=${i.id_ia}'"><a style="color: black">${i.filieres.nom_filiere}</a></td>
								<td><input type="checkbox" name="${i.id_ia }" id="${i.id_ia }" onchange="envoi_form()"
										value="yes"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="Filiere" class="">Semestre</label>
								<select name="filiere" id="filiere" class="form-control" >
								<option default disabled>Choisir semestre d'inscription</option>
								<c:forEach items="${semestre}" var="s">
								<option value="${s.id_semestre}">${s.libelle_semestre}</option>
								</c:forEach>
								</select>
							</div>
						</div>
						</div>
						
					<button class="mt-2 btn btn-primary col-md-12" type="submit">Inscrire</button>
				</form>
			</div>
		</div>
<script src="/plugins/jquery/jquery.min.js"></script>
		<script>
		
		
		//------------------------------------------------------------------------------------//
		function envoi_form(){
			
			var element = document.getElementById("myDiv");
			var child=document.getElementById("ok");
			element.removeChild(child);
			
			const checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
			var names= new Array(checkboxes.length+1);
			var i;
			for(i=0 ; i<checkboxes.length ; i++){
				names[i]=checkboxes[i].name;
			}
			var value=names[0]+"";
			var j;
			for(j=1 ; j<checkboxes.length ; j++){
				value=value+","+names[j];
			}
			
			var input = document.createElement("input");

			input.setAttribute("type", "hidden");

			input.setAttribute("name", "id_ias");
			
			input.setAttribute("id", "ok");

			input.setAttribute("value", value);

			//append to form element that you want .
			document.getElementById("myDiv").appendChild(input);
			
		}
		//-------------------------------------------------------------------------------//
		function myFunction() {
			  // Declare variables
			  var input, filter, table, tr, td, i, txtValue;
			  input = document.getElementById("myInput");
			  filter = input.value.toUpperCase();
			  table = document.getElementById("myTable");
			  tr = table.getElementsByTagName("tr");
			  // Loop through all table rows, and hide those who don't match the search query
			  for (i = 0; i < tr.length; i++) {
			    td = tr[i].getElementsByTagName("td")[1];
			    if (td) {
			      txtValue = td.textContent || td.innerText;
			      if (txtValue.toUpperCase().indexOf(filter) > -1) {
			        tr[i].style.display = "";
			      } else {
			        tr[i].style.display = "none";
			      }
			    }
			  }
			}
		
</script>
	</layout:put>
</layout:extends>