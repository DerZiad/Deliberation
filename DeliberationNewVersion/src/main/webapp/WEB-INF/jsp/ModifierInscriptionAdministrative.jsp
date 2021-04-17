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
				<h5 class="card-title">Inscription Administrative de l'Etudiant</h5>
				<form class="" action="/inscription/ModifierInscriptionAdministrative/${ia.id_ia}" method="POST" enctype="multipart/form-data">


					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="Nom Etudiant" class="">Nom Etudiant</label>
								<select name="id_etudiant" id="exampleSelect" class="form-control">
								<option value="${ia.etudiant.id }" selected>${ia.etudiant.first_name_fr } ${ia.etudiant.last_name_fr }</option>
								<c:forEach var="e" items="${Etudiant }">
								<c:if test="${ia.etudiant.id!=e.id }">
								<option value=${e.getId() }>${e.getFirst_name_fr() } ${e.getLast_name_fr() }</option>
								</c:if>
								</c:forEach>
								</select>
							</div>
						</div>

					

							<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="Filiere" class="">Filiere</label>
								<select name="filiere" id="exampleSelect" class="form-control" >
								<option value="${ia.filieres.id_filiere }" selected>${ia.filieres.nom_filiere }</option>
								<c:forEach var="f" items="${Filiere }">
								<c:if test="${ia.filiers.id_filiere!=f.id_filiere }">
								<option value="${f.getId_filiere()}">${f.getNom_filiere() }</option>
								</c:if>
								</c:forEach>
								</select>
							</div>
				
						</div>

					</div>
					
					<div class="form-row">
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="date_pre_inscription" class="">Date de preinscription</label><input
									name="date_pre_inscription" id="date_pre_inscription" value="${ia.date_pre_inscription.toString().substring(0,10)}"
									type="date" class="form-control">
							</div>
						</div>
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="date_valid_inscription" class="">Date Validation d'inscription</label><input
									name="date_valid_inscription" id="date_valid_inscription" value="${ia.date_valid_inscription.toString().substring(0,10)}"
									type="date" class="form-control">
							</div>
						</div>
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Année academique</label><input
									name="annee_academique" id="date_valid_inscription" placeholder="xxxx/xxxx"
									value="${ia.annee_academique}" class="form-control">
							</div>
						</div>
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Operateur</label><input
									name="operateur" id="date_valid_inscription" value="${ia.operateur}"
									 class="form-control">
							</div>
						</div>
					</div>
					
						
						<div class="form-row">
					<div class="col-md-6">
							<div class="position-relative form-group">
			      				<input type="checkbox" class="form-check-input" onchange="displayInput('photo')" name="filtre" style="margin-left:1px"><label style="margin-left:15px">Photo</label>
			      				<input type="checkbox" class="form-check-input" onchange="displayInput('bac')" name="filtre" style="margin-left:10px"><label style="margin-left:28px">Bac</label>
			      				<input type="checkbox" class="form-check-input" onchange="displayInput('rn')" name="filtre" style="margin-left:10px"><label style="margin-left:28px">Relevé de note</label>
			      				<input type="checkbox" class="form-check-input" onchange="displayInput('an')" name="filtre" style="margin-left:10px"><label style="margin-left:28px">Acte de Naissance</label>
			      				<input type="checkbox" class="form-check-input" onchange="displayInput('cin')" name="filtre" style="margin-left:10px"><label style="margin-left:28px">Cin</label><br>
			      				<input type="checkbox" class="form-check-input" onchange="displayInput('document1')" name="filtre" style="margin-left:2px"><label style="margin-left:18px">Document sup 1</label>
			      				<input type="checkbox" class="form-check-input" onchange="displayInput('document2')" name="filtre" style="margin-left:2px"><label style="margin-left:18px">Document sup 2</label>
							</div>
						</div>
						
						<div class="col-md-6" id="photo" style="display:none">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="">Inserer photo</label>
							<c:if test="${ia.photo != null }">
			      				<i class="fa fa-fw" aria-hidden="true" title="Copy to use check-circle"></i>
			      				</c:if>
			      				<c:if test="${ia.photo == null }">
			      				<i class="fa fa-fw" aria-hidden="true" title="Copy to use times-circle"></i>
			      				</c:if>
			      				<input name="photo" id="file" type="file" class="form-control-file">
							</div>
						</div>
						<div class="col-md-6" id="bac" style="display:none">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Inserer Bac</label>		
							<c:if test="${ia.bac != null }">
			      				<i class="fa fa-fw" aria-hidden="true" title="Copy to use check-circle"></i>
			      				</c:if>
			      				<c:if test="${ia.bac == null }">
			      				<i class="fa fa-fw" aria-hidden="true" title="Copy to use times-circle"></i>
			      				</c:if>
			      				<input name="bac" id="file" type="file" class="form-control-file">
							</div>
						</div>
						
						<div class="col-md-6" id="rn" style="display:none">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Inserer Relevé de note</label>		
							<c:if test="${ia.rn != null }">
			      				<i class="fa fa-fw" aria-hidden="true" title="Copy to use check-circle"></i>
			      				</c:if>
			      				<c:if test="${ia.rn == null }">
			      				<i class="fa fa-fw" aria-hidden="true" title="Copy to use times-circle"></i>
			      				</c:if>
			      				<input name="rn" id="file" type="file" class="form-control-file">
							</div>
						</div>
						<div class="col-md-6" id="an" style="display:none">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Inserer Acte de naissance</label>		
							<c:if test="${ia.an != null }">
			      				<i class="fa fa-fw" aria-hidden="true" title="Copy to use check-circle"></i>
			      				</c:if>
			      				<c:if test="${ia.an == null }">
			      				<i class="fa fa-fw" aria-hidden="true" title="Copy to use times-circle"></i>
			      				</c:if>
			      				<input name="an" id="file" type="file" class="form-control-file">
							</div>
						</div>
						<div class="col-md-6" id="cin" style="display:none">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Inserer Cin</label>		
							<c:if test="${ia.cin != null }">
			      				<i class="fa fa-fw" aria-hidden="true" title="Copy to use check-circle"></i>
			      				</c:if>
			      				<c:if test="${ia.cin == null }">
			      				<i class="fa fa-fw" aria-hidden="true" title="Copy to use times-circle"></i>
			      				</c:if>
			      				<input name="cin" id="file" type="file" class="form-control-file">
							</div>
						</div>
						<div class="col-md-6" id="document1" style="display:none">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Inserer document supplémentaire 1</label>		
							<c:if test="${ia.document1 != null }">
			      				<i class="fa fa-fw" aria-hidden="true" title="Copy to use check-circle"></i>
			      				</c:if>
			      				<c:if test="${ia.document1 == null }">
			      				<i class="fa fa-fw" aria-hidden="true" title="Copy to use times-circle"></i>
			      				</c:if>
			      				<input name="document1" id="file" type="file" class="form-control-file">
							</div>
						</div>
						<div class="col-md-6" id="document2" style="display:none">
							<div class="position-relative form-group">
								<label for="annee_academique" class="">Inserer document supplémentaire 2</label>		
							<c:if test="${ia.document2 != null }">
			      				<i class="fa fa-fw" aria-hidden="true" title="Copy to use check-circle"></i>
			      				</c:if>
			      				<c:if test="${ia.document2 == null }">
			      				<i class="fa fa-fw" aria-hidden="true" title="Copy to use times-circle"></i>
			      				</c:if>
			      				<input name="document2" id="file" type="file" class="form-control-file">
							</div>
						</div>
						</div>
						
					<button class="mt-2 btn btn-primary col-md-12" type="submit">Valider</button>
				</form>
				
			</div>
		</div>

	<script>
function displayInput(idS){
		
		
		var x=document.getElementById(idS+"");
		if (x.style.display === "none") {
		    x.style.display = "block";
		  } else {
		    x.style.display = "none";
		  }
	}
	
	</script>

	</layout:put>
</layout:extends>