<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<layout:extends name="layout.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			
				<h5 class="card-title">Créer elements de module</h5>
				
									
											
										
								
				<form class="" action="/elements/creer?nbr=${element_number}" method="Post">
				<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="Module" class="">id du Module</label><input
									name="module" id="module" value="${ module.id_module}"
									type="text" class="form-control" readonly>
							</div>
						</div>
					</div>
<c:forEach var="nbr" begin="1" end="${element_number }" >
					
					<div class="card-body">
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="Module" class="">Nom du Elements</label><input
									name="libelle_element_${ nbr}" id="libelle_module" placeholder="Nom du module"
									type="text" class="form-control" required>
							</div>
						</div>
					</div><div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="Module" class="">Coeficient</label><input
									name="coeficient_${ nbr}" id="libelle_module" placeholder="Nom du module"
									type="text" class="form-control" required>
							</div>
						</div>
					</div>
						<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="professeurs" class="">Professeurs</label>
								<select name="id_professeur_${ nbr}" id="professeur" class="form-control" >
								<option default disabled>Choisir le semestre</option>
								<c:forEach var="p" items="${professeurs}">
									
											<option value="${p.id_professeur}">${p.nom_professeur} - ${p.prenom_professeur}</option>
										
								</c:forEach>
								</select>
							</div>
						</div>
					</div>
					</div>
					</c:forEach>
					<button class="mt-2 btn btn-primary col-md-12" type="submit">Valider</button>
				</form>
				
			</div>
		
	</layout:put>
</layout:extends>