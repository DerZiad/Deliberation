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
 					 <button class="mb-2 mr-2 btn btn-primary" onclick="openCity(event, '${f.nom_filiere}')">${f.nom_filiere}</button>
  				</c:forEach>
				</div>
				<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="Filiere" class="">Année universitaire</label>
								<select name="annee" id="myInput" class="form-control" onchange="filtre()">
								<option selected="selected" disabled>Choisir l'année universitaire</option>
								<option value="${annee-1}/${annee}">${annee-1}/${annee}</option>
								<option value="${annee-2}/${annee-1}">${annee-2}/${annee-1}</option>
								<option value="${annee-3}/${annee-2}">${annee-3}/${annee-2}</option>
								<option value="${annee-4}/${annee-3}">${annee-4}/${annee-3}</option>
								<option value="${annee-5}/${annee-4}">${annee-5}/${annee-4}</option>
								<option value="${annee-6}/${annee-5}">${annee-6}/${annee-5}</option>
								</select>
							</div>
				
						</div>
				<c:forEach var="f" items="${f}">
			
	<div id="${f.nom_filiere}" class="tabcontent">
				<table class="mb-0 table table-hover" id="myTable">
					<thead>
						<tr>
							<th class="th-sm">Nom Etudiant</th>
							<th class="th-sm">Année universitaire</th>
							<th class="th-sm">Date de preinscription</th>
							<th class="th-sm">Date Validation d'inscription</th>
							<th class="th-sm">Operateur</th>
							<th class="th-sm"></th>
							<th class="th-sm"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="i" items="${Inscription}">
							<tr>
							<c:if test="${f.id_filiere==i.filieres.id_filiere }">
								<td onclick="window.location.href ='ProfilEtudiant/${i.id_ia}'">
								<a style="color: black" >
								${i.etudiant.first_name_fr} ${i.etudiant.last_name_fr}</a></td>
								<td onclick="window.location.href ='ProfilEtudiant/${i.id_ia}'">
								<a style="color: black">
								${i.annee_academique}</a></td>
								<td onclick="window.location.href ='ProfilEtudiant/${i.id_ia}'">
								<a style="color: black">
								${i.date_pre_inscription.toString().substring(0,10)}</a></td>
								<td onclick="window.location.href ='ProfilEtudiant/${i.id_ia}'">
								<a style="color: black">
								${i.date_valid_inscription.toString().substring(0,10)}</a></td>
								<td onclick="window.location.href ='ProfilEtudiant/${i.id_ia}'">
								<a style="color: black">
								${i.operateur}</a></td>
								
								
								<td> 
								<i class="fa fa-fw" aria-hidden="true" title="Copy to use pencil-square-o"><a href="PageModifierInscriptionAdministrative?id=${i.id_ia}" style="font-size:20px;"></a></i>
								</td>
								<td>
								<i class="fa fa-fw" aria-hidden="true" title="Copy to use trash">
								<a href="SupprimerInscriptionAdministrative/${i.id_ia}" style="color:red;font-size:20px;"></a>
								</i>       
								</td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				</c:forEach>
			</div>
		</div>
		<script type="text/javascript" src="./assets/scripts/main.js"></script>
		<script>
		
		
		
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
		
		function filtre() {
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
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p class="mb-0">Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

</layout:extends>