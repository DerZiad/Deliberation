<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<layout:extends name="layout.jsp">
	<layout:put block="content" type="REPLACE">
		<form method="POST"
			action="/delib/listEtape">
			<div class="main-card mb-3 card">
				<div class="card-body">
					<h5 class="card-title">Choix du etapes</h5>

					<h6 class="card-title">Liste des etapes</h6>



					<div class="tabcontent">
						<div class="form-row" id="myDiv">

							<input name="id_ias" id="ok" type="text" style="display: none">
							<div class="col-md-6">
								<div class="position-relative form-group">
									<label for="Filiere" class=""> Filiere</label> <select
										name="filiere" id="filiere" class="form-control"
										onchange="changeFunc();">
										<option selected default disabled>Choisir la filiere</option>
										<c:forEach items="${filieres}" var="s">
											<option value="${s.id_filiere}">${s.nom_filiere}</option>
										</c:forEach>
									</select>
								</div>

							</div>
							<div class="col-md-6">
								<div class="position-relative form-group">
									<label for="Filiere" class="">Etape</label> <select
										name="etape" id="etape" class="form-control"
										onchange="changeFuncSemestre();">
										<option selected default disabled>Choisir l'etape</option>

									</select>
								</div>

							</div>
						</div>

			
			
					</div>
				</div>
			</div>

		

			

			<div class="form-row">
				
			</div>
			<!-- ================================================================================== -->

			<button class="mt-2 btn btn-primary col-md-12" id="valider"
				type="submit" >Valider</button>
		</form>

		<!-- etape-->
		<script language="javascript">

	var x = document.getElementById("filiere").value;
	var values = [];
	var value = [];
	<c:forEach items="${etapes}" var="id">
	if (x==${id.filiere.id_filiere}) {
		values.push("${id.id_etape}");
		value.push("${id.libelle_etape}");}
</c:forEach>

	var select = document.getElementById("etape");
	for(index in values) {
		 select.options[select.options.length] = new Option(value[index],values[index]);
		    select.options[select.options.length-1].value =values[index];
		
	};
	
	 function changeFunc() {
		 var select = document.getElementById("etape");
		 var length = select.options.length;
		 for (i = length-1; i >= 0; i--) {
		   select.options[i] = null;
		 }

	var x = document.getElementById("filiere").value;
	var values = [];
	var value = [];
	<c:forEach items="${etapes}" var="id">
	if (x==${id.filiere.id_filiere}) {
		values.push("${id.id_etape}");
		value.push("${id.libelle_etape}");}
</c:forEach>
	var select = document.getElementById("etape");
	for(index in values) {
	    select.options[select.options.length] = new Option(value[index],values[index]);
	    select.options[select.options.length-1].value =values[index];
	
	 
	};
	
	}
	</script>
		
		
	
	
	
	</layout:put>
</layout:extends>