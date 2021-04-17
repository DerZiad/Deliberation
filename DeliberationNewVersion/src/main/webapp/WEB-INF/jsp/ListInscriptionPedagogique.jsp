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
				<c:forEach var="f" items="${f}">
			
	<div id="${f.nom_filiere}" class="tabcontent">
					<c:if test="${ parModule==0}">
						<input name="id_ias" id="ok" type="text" style="display: none">
							<div class="col-md-6">
							
				
						</div>
						</c:if>
						
				<table class="mb-0 table table-hover" id="myTable">
					<thead>
						<tr>
							<c:if test="${ parSemestre==1}">
							<td class="th-sm" >&nbsp;</td>
							<td class="th-sm" >&nbsp;</td>
							<td class="th-sm" >&nbsp;</td>
							<td class="th-sm" >&nbsp;</td>
							</c:if>
							<td class="th-sm" >&nbsp;</td>
							<c:if test="${ parModule==1}">
							<c:forEach var="m" items="${module}">
							<c:if test="${m.semestre.filiere==f && testM!=m.semestre}">
							<c:set var = "testM" value = "${m.semestre}"/>
							<td class="th-sm" colspan="5" style="text-align: center; border-left: 0.1px solid #E9ECEF;border-right: 0.5px solid #E9ECEF;"><b>${m.semestre.libelle_semestre}</b></td>
							</c:if>
							</c:forEach>
							</c:if>
							</tr>
							<tr>
							<th class="th-sm">Nom Etudiant</th>
							<c:if test="${ parSemestre==1}">
							<th class="th-sm">Année universitaire</th>
							<th class="th-sm">Date de preinscription</th>
							<th class="th-sm">Date Validation d'inscription</th>
							<th class="th-sm" rowspan="2">Semestre</th>
							
							
							</c:if>
							<c:if test="${ parModule==1}">
							<c:forEach var="m" items="${module }">
							<c:if test="${f.id_filiere==m.semestre.filiere.id_filiere }">
							<td class="th-sm" style=" border: 0.1px solid #E9ECEF;">${m.libelle_module }</td>
							</c:if>
							</c:forEach>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="i" items="${Inscription}">
							<tr>
							<c:if test="${f.id_filiere==i.semestre.filiere.id_filiere }">
								<td><a style="color: black">${i.etudiant.first_name_fr} ${i.etudiant.last_name_fr}</a></td>
								<c:if test="${ parSemestre==1}">
								<td><a style="color: black">${i.annee_academique}</a></td>
								<td><a style="color: black">${i.date_pre_inscription}</a></td>
								<td><a style="color: black">${i.date_valid_inscription}</a></td>
								<td><a style="color: black">${i.semestre.libelle_semestre}</a></td>
								</c:if>
								<c:if test="${ parModule==1}">
								<c:forEach var="m" items="${module }">
									<c:set var = "condition" value = "${0}"/>
									<c:if test="${f.id_filiere==m.semestre.filiere.id_filiere }">
										<c:forEach var="im" items="${i.module}">
											<c:if test="${im.id_module==m.id_module }">
												<c:set var = "condition" value = "${1}"/>
												<td style=" border: 0.1px solid #E9ECEF;"><div class="mb-2 mr-2 badge badge-primary">En cours</div></td>
											</c:if>
										</c:forEach>
											<c:if test="${condition==0 }">
												<td style=" border: 0.1px solid #E9ECEF;"><div class="mb-2 mr-2 badge badge-focus">Pas Encore</div></td>
											</c:if>
									</c:if>
								</c:forEach>
								</c:if>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				</c:forEach>
			</div>
		</div>
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
			    td = tr[i].getElementsByTagName("td")[4];
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
		
		//-------------------------------------------------------------------------------//
		
		</script>
	</layout:put>
</layout:extends>