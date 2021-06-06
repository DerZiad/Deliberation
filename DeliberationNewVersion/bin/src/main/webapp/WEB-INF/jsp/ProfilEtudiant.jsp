<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<layout:extends name="layout.jsp">
	<layout:put block="content" type="REPLACE">

<div class="row">
	<div class="col-md-4">
		<div class="main-card mb-3 card">
			<div class="card-body">
			<img src="data:image/jpeg;base64,${ia.encodedPhoto}"
			onerror="if (this.src != '/images/profile.jpg') this.src = '/images/profile.jpg';"
			width="200" height="240" style="margin-left:30px;  border-radius: 100%;
			width: 80%;
  			background-color: white;
  			box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);"><br>	
		
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="Nom Etudiant" class="" style="font-weight:bold;margin-top:20px">Nom Etudiant :</label>
								
							</div>
						</div>

					

							<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="Filiere" class="" style="margin-top:20px">${etudiant.first_name_fr } ${etudiant.last_name_fr }</label>
							</div>
				
						</div>

					</div>
					
					
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="Nom Etudiant" class="" style="font-weight:bold">Nom Etudiant (ar) :</label>
								
							</div>
						</div>

					

							<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="Filiere" class="" >${etudiant.first_name_ar } ${etudiant.last_name_ar }</label>
							</div>
				
						</div>

					</div>
					
					
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Gender :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">${etudiant.gender }</label>		
							</div>
						</div>
						</div>
						
						<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Nationalité :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">${etudiant.nationality }</label>		
							</div>
						</div>
						</div>
					
					<div class="form-row">
						
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="annee_academique" class="" style="font-weight:bold">Date de naissance :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">${etudiant.birth_date.toString().substring(0,10)}</label>
							</div>
						</div>
					</div>
					
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Place de naissance :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">${etudiant.birth_place }</label>		
							</div>
						</div>
						</div>
					
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Ville d'origine :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">${etudiant.city }</label>		
							</div>
						</div>
						</div>
						
						<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Province :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">${etudiant.province }</label>		
							</div>
						</div>
						</div>
						
						<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Année bac :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">${etudiant.bac_year }</label>		
							</div>
						</div>
						</div>
						
						
						<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">type de bac :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">${etudiant.bac_type }</label>		
							</div>
						</div>
						</div>
						
						<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Mentien bac :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">${etudiant.mention }</label>		
							</div>
						</div>
						</div>
						
						<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Massar :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">${etudiant.massar_edu }</label>		
							</div>
						</div>
						</div>
						
						<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">CIN :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">${etudiant.cne }</label>		
							</div>
						</div>
						</div>
						
						
						
						
				
			</div>
		</div>
		</div>
			<div class="col-md-8">
			
			<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Inscription Administrative de l'Etudiant</h5>
				<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold;margin-top:20px">Date de préinscription :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="annee_academique" class="" style="margin-top:20px">${ia.date_pre_inscription.toString().substring(0,10) }</label>		
							</div>
						</div>
						</div>
						
						<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Date de validation d'inscription :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">${ia.date_valid_inscription.toString().substring(0,10) }</label>		
							</div>
						</div>
						</div>
						
						
				
				<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Filiére :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">${ia.filieres.nom_filiere }</label>		
							</div>
						</div>
						</div>
						
						<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Année académique :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">${ia.annee_academique}</label>		
							</div>
						</div>
						</div>
						
						<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Opérateur :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">${ia.operateur}</label>		
							</div>
						</div>
						</div>
						
						<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Photo :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<c:if test="${ia.photo != null }">
								<td>
								<i class="fa fa-fw" aria-hidden="true" title="Copy to use check-circle" style="margin-left:6.5px">
								<a id="link" style="color:green;font-size:30px;" href="data:image/jpeg;base64,${ia.encodedPhoto}" download="photo_de_${i.etudiant.first_name_fr }_${i.etudiant.last_name_fr}"></a>
								</i></td>
								
								</c:if>
								<c:if test="${ia.photo == null }">
								<td>
								<i class="fa fa-fw" aria-hidden="true" title="Copy to use times-circle" style="color:red;font-size:30px;"></i>
								</td>
								</c:if>		
							</div>
						</div>
						</div>
						
						<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Bac :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<c:if test="${ia.bac != null }">
								<td>
								<i class="fa fa-fw" aria-hidden="true" title="Copy to use check-circle" style="margin-left:6.5px">
								<a id="link" style="color:green;font-size:30px;" href="data:application/pdf;base64,${ia.encodedBac}" download="bac_de_${i.etudiant.first_name_fr }_${i.etudiant.last_name_fr}"></a>
								</i></td>
								
								</c:if>
								<c:if test="${ia.bac == null }">
								<td>
								<i class="fa fa-fw" aria-hidden="true" title="Copy to use times-circle" style="color:red;font-size:30px;"></i>
								</td>
								</c:if>		
							</div>
						</div>
						</div>
						
						<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Relevé de note :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<c:if test="${ia.rn != null }">
								<td>
								<i class="fa fa-fw" aria-hidden="true" title="Copy to use check-circle" style="margin-left:6.5px">
								<a id="link" style="color:green;font-size:30px;" href="data:application/pdf;base64,${ia.encodedRv}" download="RelevéDeNote_de_${i.etudiant.first_name_fr }_${i.etudiant.last_name_fr}"></a>
								</i></td>
								
								</c:if>
								<c:if test="${ia.rn == null }">
								<td>
								<i class="fa fa-fw" aria-hidden="true" title="Copy to use times-circle" style="color:red;font-size:30px;"></i>
								</td>
								</c:if>		
							</div>
						</div>
						</div>
						
						<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Acte de naissance :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<c:if test="${ia.an != null }">
								<td>
								<i class="fa fa-fw" aria-hidden="true" title="Copy to use check-circle" style="margin-left:6.5px">
								<a id="link" style="color:green;font-size:30px;" href="data:application/pdf;base64,${ia.encodedAn}" download="ActeDeNaissance_de_${i.etudiant.first_name_fr }_${i.etudiant.last_name_fr}"></a>
								</i></td>
								
								</c:if>
								<c:if test="${ia.an == null }">
								<td>
								<i class="fa fa-fw" aria-hidden="true" title="Copy to use times-circle" style="color:red;font-size:30px;"></i>
								</td>
								</c:if>		
							</div>
						</div>
						</div>
						
						<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Cin :</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<c:if test="${ia.cin != null }">
								<td>
								<i class="fa fa-fw" aria-hidden="true" title="Copy to use check-circle" style="margin-left:6.5px">
								<a id="link" style="color:green;font-size:30px;" href="data:application/pdf;base64,${ia.encodedCin}" download="cin_de_${i.etudiant.first_name_fr }_${i.etudiant.last_name_fr}"></a>
								</i></td>
								
								</c:if>
								<c:if test="${ia.cin == null }">
								<td>
								<i class="fa fa-fw" aria-hidden="true" title="Copy to use times-circle" style="color:red;font-size:30px;"></i>
								</td>
								</c:if>		
							</div>
						</div>
						</div>
						<c:if test="${ia.document1 != null}">
						<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Document supplémentaire 1 :</label>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="position-relative form-group">
								<c:if test="${ia.document1 != null }">
								<td>
								<i class="fa fa-fw" aria-hidden="true" title="Copy to use check-circle" style="margin-left:6.5px">
								<a id="link" style="color:green;font-size:30px;" href="data:application/pdf;base64,${ia.encodedDocument1}" download="docSupp_de_${i.etudiant.first_name_fr }_${i.etudiant.last_name_fr}"></a>
								</i></td>
								
								</c:if>
								<c:if test="${ia.document1 == null }">
								<td>
								<i class="fa fa-fw" aria-hidden="true" title="Copy to use times-circle" style="color:red;font-size:30px;"></i>
								</td>
								</c:if>		
							</div>
						</div>
						</div>
						</c:if>
						<c:if test="${ ia.document2 != null }">
						<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Document supplémentaire 2 :</label>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="position-relative form-group">
								<c:if test="${ia.document2 != null }">
								<td>
								<i class="fa fa-fw" aria-hidden="true" title="Copy to use check-circle" style="margin-left:6.5px">
								<a id="link" style="color:green;font-size:30px;" href="data:image/jpeg;base64,${ia.encodedDocument2}" download="DocSupp2_de_${i.etudiant.first_name_fr }_${i.etudiant.last_name_fr}"></a>
								</i></td>
								
								</c:if>
								<c:if test="${ia.document2 == null }">
								<td>
								<i class="fa fa-fw" aria-hidden="true" title="Copy to use times-circle" style="color:red;font-size:30px;"></i>
								</td>
								</c:if>		
							</div>
						</div>
						</div>
						</c:if>
						
			</div>
			</div>
			
			
			
	
		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Inscription pédagogique</h5>
				
				<div class="form-row">
						<div class="col-md-4">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Semestre Inscrit :</label>
							</div>
						</div>
						<div class="col-md-12">
							<div class="position-relative form-group">
							<c:forEach var="i" items="${ip }">
								<span class="label info" style="color: white;
  							padding: 8px;
  							font-family: Arial;
  							background-color: #2196F3;" id="${i.id_ip }" 
  							onclick="showData(this.id)">${i.semestre.libelle_semestre} &nbsp;</span>
							</c:forEach>	
							</div>
						</div>
						</div>
				
			<div id="mydiv">
				<div class="form-row">
						<div class="col-md-4">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Modules en cours :</label>
							</div>
						</div>
						<div class="col-md-12">
							<div class="position-relative form-group">
				<div class="tab">
				

	<div class="tabcontent">
				
					<c:forEach var="i" items="${ip }">
						<c:forEach var="ipm" items="${ipm }">
							<c:if test="${ipm.inscription_pedagogique == i }">
								<c:if test="${ipm.validation == 2.0 }">
							<span class="label info" style="color: white;
  							padding: 8px;
  							font-family: Arial;
  							background-color: #2196F3;" id="${i.id_ip }">${ipm.module.libelle_module } &nbsp;</span>
  							</c:if>
  							</c:if>
						</c:forEach>
						</c:forEach>
						
				</div>
				
				
				
			</div>
			</div>
			</div>
			
		</div>
		
		
		<div class="form-row">
						<div class="col-md-4">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Modules validés :</label>
							</div>
						</div>
						<div class="col-md-12">
							<div class="position-relative form-group">
				<div class="tab">
				

		<div class="tabcontent">
				
					<c:forEach var="i" items="${ip }">
						<c:forEach var="ipm" items="${ipm }">
							<c:if test="${ipm.inscription_pedagogique == i }">
								<c:if test="${ipm.validation == 0.0 }">
							<span class="label info" style="color: white;
  							padding: 8px;
  							font-family: Arial;
  							background-color: #008000;" id="${i.id_ip }">${ipm.module.libelle_module } &nbsp;</span>
  							</c:if>
  							</c:if>
						</c:forEach>
						</c:forEach>
						
				</div>
				
				
				
			</div>
			</div>
			</div>
			
		</div>
		
		
		
		<div class="form-row">
						<div class="col-md-4">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="" style="font-weight:bold">Modules non Validés :</label>
							</div>
						</div>
						<div class="col-md-12">
							<div class="position-relative form-group">
				<div class="tab">
				

	<div class="tabcontent">
				
					<c:forEach var="i" items="${ip }">
						<c:forEach var="ipm" items="${ipm }">
							<c:if test="${ipm.inscription_pedagogique == i }">
								<c:if test="${ipm.validation == -1.0 }">
							<span class="label info" style="color: white;
  							padding: 8px;
  							font-family: Arial;
  							background-color: #FF0000;" id="${i.id_ip }">${ipm.module.libelle_module } &nbsp;</span>
  							</c:if>
  							</c:if>
						</c:forEach>
						</c:forEach>
						
				</div>
				
				
				
			</div>
			</div>
			</div>
			
		</div>
		
		</div>
		</div>
	</div>
	</div>
	</div>
	
	<script>
	function showData(ids){
		var div = document.getElementById("mydiv");
		var spans =div.querySelectorAll('span');
		var i ;
	 for(i=0 ; i <spans.length ; i++){
		 if(spans[i].id == ids){
		if (spans[i].style.display === "none") {
			spans[i].style.display = "";
		  } else {
			  spans[i].style.display = "none";
		  }
		 }
	 }
		
	}
	</script>
	</layout:put>
</layout:extends>