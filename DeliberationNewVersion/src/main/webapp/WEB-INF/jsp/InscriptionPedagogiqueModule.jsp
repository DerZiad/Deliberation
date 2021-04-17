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
				<h5 class="card-title">Inscription Pedagogique de l'Etudiant</h5>
				<form class="" action="/inscription/createANewInscriptionPedagogiqueModule" method="POST">


				
			<div  class="tabcontent" id="myDiv">
			<input name="id_ip" id="ok" type="text" style="display: none">
				<table class="mb-0 table table-hover" id="tableAInscrire">
					<thead>
						<tr>
							<th class="th-sm">Nom Etudiant</th>
							<th class="th-sm">Année universitaire</th>
							<th class="th-sm">Filiere</th>
							<th class="th-sm">Semestre</th>
							<th class="th-sm">Selectionner</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="i" items="${Inscription}">
							<tr>
								<td><a style="color: black">${i.etudiant.first_name_fr} ${i.etudiant.last_name_fr}</a></td>
								<td><a style="color: black">${i.annee_academique}</a></td>
								<td><a style="color: black">${i.semestre.filiere.nom_filiere}</a></td>
								<td><a style="color: black">${i.semestre.libelle_semestre}</a></td>
								<td><input type="checkbox" id="lesIps" name="${i.id_ip }" onchange="envoi_form()"
										value="yes"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				
					<div class="form-row" id="myDivModule">
					<input name="modules" id="modules" type="text" style="display: none">
						<div class="col-md-8">
							<div class="position-relative form-group">
								<br><label for="Filiere" class=""><h6>Choisir module d'inscription :</h6></label><br>
								<c:forEach items="${module}" var="m">
								<input type="checkbox" name="${m.id_module }" id="lesModules" value="${m.id_module }" style="margin-right:5px" onchange="envoi_formModule()"> ${m.libelle_module }<br>   
								</c:forEach>
							</div>
						</div>
						</div>
						
					<button class="mt-2 btn btn-primary col-md-12" type="submit">Inscrire</button>
				</form>
				
			</div>
		</div>
		<script type="text/javascript" src="webjars/jquery/3.2.0/jquery.min.js"></script>
	<script>
	//------------------------------------------------------------------------------------//
	function envoi_formModule(){
		
		var element = document.getElementById("myDivModule");
		var child=document.getElementById("modules");
		element.removeChild(child);
		
		const checkboxesModule = document.querySelectorAll('input[id="lesModules"]:checked');
		var modules= new Array(checkboxesModule.length+1);
		var i;
		for(i=0 ; i<checkboxesModule.length ; i++){
			modules[i]=checkboxesModule[i].name;
		}
		var valueModule=modules[0]+"";
		var k;
		for(k=1 ; k<checkboxesModule.length ; k++){
			valueModule=valueModule+","+modules[k];
		}
		var inputModule = document.createElement("input");

		inputModule.setAttribute("type", "hidden");

		inputModule.setAttribute("name", "modules");
		
		inputModule.setAttribute("id", "modules");

		inputModule.setAttribute("value", valueModule);

		//append to form element that you want .
		document.getElementById("myDivModule").appendChild(inputModule);
	}
		//--------------------------------------------------------------//
		function envoi_form(){
		var element = document.getElementById("myDiv");
		var child=document.getElementById("ok");
		element.removeChild(child);
		
		const checkboxes = document.querySelectorAll('input[id="lesIps"]:checked');
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

		input.setAttribute("name", "id_ip");
		
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