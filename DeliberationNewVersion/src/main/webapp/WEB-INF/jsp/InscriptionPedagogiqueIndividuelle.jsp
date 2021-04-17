<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<layout:extends name="layout.jsp">
	<layout:put block="content" type="REPLACE">
		<form method="Post" action="/inscription/CreateNewInscriptionPedagogiqueIndividuelle">
			<div class="main-card mb-3 card">
				<div class="card-body">
					<h5 class="card-title">Inscription pédagogique d'etudiant
						"${ia.etudiant.first_name_fr} ${ia.etudiant.last_name_fr}"</h5>

					<h6 class="card-title">Liste des étapes</h6>


					<div class="tab" id="myDiv">
						<input name="id_modules" id="ok" type="text" style="display: none">
					</div>

					<div id="${f.nom_filiere}" class="tabcontent">
						<table class="mb-0 table table-hover">
							<thead>
								<tr>
									<th class="th-sm">Etape</th>

								</tr>
							</thead>
							<tbody>
					<!-- ================================================================================== -->

								<c:forEach var="e" items="${etape}">
									<tr>



										<td><input type="radio" name="lesIdsE" 
											onclick="tableauModule(${e.id_etape},'lesIdsE')"> <label>${e.libelle_etape }</label>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- ================================================================================== -->
			<c:forEach var="e" items="${etape }">
				<div class="main-card mb-3 card" id="${e.id_etape }"
					style="display: none">
					<div class="card-body">
						<h5 class="card-title">Liste des semestres</h5>

						<div class="tab" id="myDiv">
							<input name="id_ip" id="ok" type="text" style="display: none">
						</div>

						<div id="${f.nom_filiere}" class="tabcontent">
							<table class="mb-0 table table-hover">
								<thead>
									<tr>
										
												<th class="th-sm">semestre</th>
											
									</tr>
								</thead>
								<tbody>
									<c:forEach var="s" items="${semestre}">
										<tr>
											<c:if test="${s.etape==e }">
												<td><input type="radio" name="lesIdsS" 
													onclick="tableauModule(${s.id_semestre+10},'lesIdsS')"> <label>${s.libelle_semestre }</label></td>
											</c:if>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</c:forEach>
			<!-- ================================================================================== -->

			<c:forEach var="s" items="${semestre}">
				<div class="main-card mb-3 card" id="${s.id_semestre+10}"
					style="display: none">
					<div class="card-body">
						<h5 class="card-title">Liste des modules du
							${s.libelle_semestre } </h5>

						<table class="mb-0 table table-hover">
							<thead>
								<tr>
									<th class="th-sm"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<c:forEach var="m" items="${modules}">
										<c:if test="${m.semestre==s }">
											<td><a style="color: black" name="linktobottom">${m.libelle_module}</a>
												<input type="checkbox" id="lesIdsM" name="${m.id_module}"
												onchange="envoi_form()"></td>
										</c:if>
									</c:forEach>
								</tr>
							</tbody>
						</table>

					</div>
					

				</div>
			</c:forEach>
			<button class="mt-2 btn btn-primary col-md-12" id="valider"
						type="submit" disabled>Valider</button>
		</form>

		<script>
		
		function tableauModule(idS,radio){
			const radios = document.querySelectorAll('input[name='+radio+']');
			var i;
			for(i=0 ; i<radios.length ; i++){
				radios[i].disabled = true;
			}
			
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
			
			const checkboxes = document.querySelectorAll('input[id="lesIdsM"]:checked');
			var names= new Array(checkboxes.length+1);
			var i;
			names[0]=${ia.id_ia};
			for(i=1 ; i<checkboxes.length+1 ; i++){
				names[i]=checkboxes[i-1].name;
			}
			var value=names[0]+"";
			var j;
			for(j=1 ; j<checkboxes.length+1 ; j++){
				value=value+","+names[j];
			}
			console.log(value);
			console.log(parseInt(value.length/2,10)+1);
			var input = document.createElement("input");

			input.setAttribute("type", "hidden");

			input.setAttribute("name", "id_modules");
			
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