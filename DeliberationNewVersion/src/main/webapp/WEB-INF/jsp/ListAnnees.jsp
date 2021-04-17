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
				<h5 class="card-title">Liste des inscriptions d'étudiants</h5>
				<div class="tab">
				<c:forEach var="f" items="${f}">
 					 <button class="mb-2 mr-2 btn btn-success" onclick="openCity(event, '${f.nom_filiere}')">${f.nom_filiere}</button>
  				</c:forEach>
				</div>
				<form method="POST" action="/inscription/ModifierAnneeDiplomante">
				<div class="tab" id="myDiv">
				<input name="id_ip" id="ok" type="text" style="display: none">
				</div>
				<c:forEach var="f" items="${f}">

	<div id="${f.nom_filiere}" class="tabcontent">
				<table class="mb-0 table table-hover">
					<thead>
						<tr>
							<th class="th-sm">Etape</th>
							<th class="th-sm">Filiere</th>
							<th class="th-sm" colspan="2">Semestre</th>
							<th class="th-sm">Diplomante</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="e" items="${etape}">
							<tr>
							<c:if test="${f.id_filiere==e.filiere.id_filiere }">
								<td><a style="color: black">${e.libelle_etape}</a></td>
								<td><a style="color: black">${e.filiere.nom_filiere }</a></td>
								<c:forEach var="s" items="${semestres }">
								<c:if test="${s.etape== e}">
								<td><a style="color: black" href="#linktobottom" onclick="tableauModule(${s.id_semestre})">${s.libelle_semestre}</a></td>
								</c:if>
								</c:forEach>
								<td>
								<c:if test="${e.diplomante==1 }">
								<input type="checkbox" id="lesIds" name="${e.id_etape }" onchange="envoi_form()" checked>
								</c:if>
								<c:if test="${e.diplomante==0 }">
								<input type="checkbox" id="lesIds" name="${e.id_etape }" onchange="envoi_form()">
								</c:if>
								</td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				</c:forEach>
				<button class="mt-2 btn btn-primary col-md-12" id="valider" type="submit" disabled>Valider</button>
				</form>
			</div>
		</div>
		
	<c:forEach var="s" items="${semestres }">
		<div class="main-card mb-3 card" id="${s.id_semestre}" style="display:none">
			<div class="card-body">
				<h5 class="card-title">Liste des modules du ${s.libelle_semestre } filiére ${s.filiere.nom_filiere }</h5>
				
				<table class="mb-0 table table-hover">
					<thead>
						<tr>
						<c:forEach var="m" items="${modules}">
							<c:if test="${m.semestre==s }">
								<th class="th-sm"></th>
							</c:if>
						</c:forEach>
						</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach var="m" items="${modules}">
						<c:if test="${m.semestre==s }">
						<td><a style="color: black" name="linktobottom">${m.libelle_module}</a></td>
						</c:if>
						</c:forEach>
					</tr>
				</tbody>
				</table>
				
			</div>
		</div>
	</c:forEach>
		
		
		<script>
		
		function tableauModule(idS){
			var x=document.getElementById(idS+"");
			if (x.style.display === "none") {
			    x.style.display = "block";
			  } else {
			    x.style.display = "none";
			  }
		}
		
		function envoi_form(){
			var button=document.getElementById("valider");
			button.disabled=false;
			var element = document.getElementById("myDiv");
			var child=document.getElementById("ok");
			element.removeChild(child);
			
			const checkboxes = document.querySelectorAll('input[id="lesIds"]:checked');
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
			console.log(value);
			console.log(parseInt(value.length/2,10)+1);
			var input = document.createElement("input");

			input.setAttribute("type", "hidden");

			input.setAttribute("name", "id_ip");
			
			input.setAttribute("id", "ok");

			input.setAttribute("value", value);

			//append to form element that you want .
			document.getElementById("myDiv").appendChild(input);
			
		}
		function openCity(evt, cityName) {
			  // Declare all variables
			  var i, tabcontent, tablinks;

			  // Get all elements with class="tabcontent" and hide them
			  tabcontent = document.getElementsByClassName("tabcontent");
			  for (i = 0; i < tabcontent.length; i++) {
			    tabcontent[i].style.display = "none";
			  }

			  // Get all elements with class="tablinks" and remove the class "active"
			  tablinks = document.getElementsByClassName("mb-2 mr-2 btn btn-success");
			  for (i = 0; i < tablinks.length; i++) {
			    tablinks[i].className = tablinks[i].className.replace(" active", "");
			  }

			  // Show the current tab, and add an "active" class to the link that opened the tab
			  document.getElementById(cityName).style.display = "block";
			  evt.currentTarget.className += " active";
			}
		</script>
	</layout:put>
</layout:extends>