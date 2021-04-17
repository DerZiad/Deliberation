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
						<input name="id_ias" id="ok" type="text" style="display: none">
							<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="Filiere" class="">Semestre</label>
								<select name="annee" id="myInput" class="form-control" id="filtre" onchange="myFunction(this)">
								<option selected="selected" disabled>Choisir le semestre</option>
								<c:forEach var="sem" items="${semestre }">
									<c:if test="${sem.filiere.id_filiere eq f.id_filiere }">
										<option value="${sem.libelle_semestre }">${sem.libelle_semestre }</option>
									</c:if>
								</c:forEach>
								</select>
							</div>
				
						</div>
						
			<c:forEach var="s" items="${semestre }">		
				<table class="mb-0 table table-hover" id="${s.libelle_semestre}" style="display: none">
					<thead>
						<tr>
							
							<td class="th-sm" >&nbsp;</td>
						
							<td class="th-sm" colspan="5" style="text-align: center; border-left: 0.1px solid #E9ECEF;border-right: 0.5px solid #E9ECEF;"><b>${s.libelle_semestre}</b></td>
							
							
							</tr>
							<tr>
							<th class="th-sm">Nom Etudiant</th>
							
							
							<c:forEach var="m" items="${module }">
							<c:if test="${f.id_filiere==m.semestre.filiere.id_filiere && m.semestre == s }">
							
							<td class="th-sm" style=" border: 0.1px solid #E9ECEF;">${m.libelle_module }</td>
							</c:if>
							</c:forEach>
						
						</tr>
					</thead>
					<tbody>
						<c:forEach var="i" items="${Inscription}">
							<tr>
							<c:if test="${f.id_filiere==i.semestre.filiere.id_filiere && i.semestre == s }">
								<td><a style="color: black">${i.etudiant.first_name_fr} ${i.etudiant.last_name_fr}</a></td>
								
								
								<c:forEach var="m" items="${module }">
									<c:set var = "condition" value = "${0}"/>
									<c:if test="${f.id_filiere==m.semestre.filiere.id_filiere && m.semestre == s }">
										<c:forEach var="ipm" items="${ipm}">
										 <c:if test="${ipm.inscription_pedagogique == i }">
										  
											<c:if test="${ipm.module==m }">
												<c:set var = "condition" value = "${1}"/>
												<c:if test="${empty ipm.validation }">
												<td style=" border: 0.1px solid #E9ECEF;"><div class="mb-2 mr-2 badge badge-primary">En cours</div></td>
												</c:if>
												<c:if test="${ipm.validation == 1.0 }">
												<td style=" border: 0.1px solid #E9ECEF;"><div class="mb-2 mr-2 badge badge-success">Validé</div></td>
												</c:if>
												<c:if test="${ipm.validation == 2.0 }">
												<td style=" border: 0.1px solid #E9ECEF;"><div class="mb-2 mr-2 badge badge-warning">Validé avec ratt</div></td>
												</c:if>
												<c:if test="${ipm.validation == 0.0 }">
												<td style=" border: 0.1px solid #E9ECEF;"><div class="mb-2 mr-2 badge badge-danger">Non Validé</div></td>
												</c:if>
											</c:if>
											</c:if>
										</c:forEach>
											<c:if test="${condition==0 }">
												<td style=" border: 0.1px solid #E9ECEF;"><div class="mb-2 mr-2 badge badge-focus">Pas Encore</div></td>
											</c:if>
									</c:if>
								</c:forEach>
								</c:if>
							
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</c:forEach>
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
		function myFunction(select) {
			 var valeur = select.value;
			 var table = document.getElementsByTagName("table");
			 var i;
			 for(i = 0 ; i < table.length ; i++){
				 table[i].style.display = "none";
			 }
			 var myTable = document.getElementById(valeur);
			 myTable.style.display = "";
			}
		
		//-------------------------------------------------------------------------------//
		
		</script>
	</layout:put>
</layout:extends>