<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<layout:extends name="layout.jsp">
	<layout:put block="content" type="REPLACE">
		<form method="POST"
			action="/delib/importRattrapage">
			<div class="main-card mb-3 card">
				<div class="card-body">
					<h5 class="card-title">Choix du module</h5>

					<h6 class="card-title">Liste des modules</h6>


					<div class="tab" id="myDiv">
						<input name="id_modules" id="ok" type="text" style="display: none">
					</div>

					<div class="tabcontent">
						<div class="form-row" id="myDiv">

							<input name="id_ias" id="ok" type="text" style="display: none">
							<div class="col-md-6">
								<div class="position-relative form-group">
									<label for="Filiere" class=""> Filiere</label> <select
										name="filiere" id="filiere" class="form-control"
										onchange="changeFunc();changeFuncSemestre();changeFuncModule();changeFuncElement()">
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
										onchange="changeFuncSemestre();changeFuncModule();changeFuncElement()">
										<option selected default disabled>Choisir l'etape</option>

									</select>
								</div>

							</div>
						</div>
<div class="form-row">
				<div class="col-md-6">
					<div class="position-relative form-group">
						<label for="Filiere" class="">Semestre</label> <select
							name="semestre" id="semestre" class="form-control"
							onchange="changeFuncModule();changeFuncElement()">
							<option selected default disabled>Choisir semestre</option>

						</select>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="position-relative form-group">
						<label for="Filiere" class="">Modules</label> <select
							name="module" id="module" class="form-control" onchange="changeFuncElement()">
							<option selected default disabled>Choisir module</option>

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
		<!-- Semestre-->
		<script language="javascript">

	var x = document.getElementById("etape").value;
	var values = [];
	var value = [];
	<c:forEach items="${semestres}" var="id">
	if (x==${id.etape.id_etape}) {
		values.push("${id.id_semestre}");
		value.push("${id.libelle_semestre}");}
</c:forEach>

	var select = document.getElementById("semestre");
	for(index in values) {
		 select.options[select.options.length] = new Option(value[index],values[index]);
		    select.options[select.options.length-1].value =values[index];
		
	};
	
	 function changeFuncSemestre() {
		 var select = document.getElementById("semestre");
		 var length = select.options.length;
		 for (i = length-1; i >= 0; i--) {
		   select.options[i] = null;
		 }

	var x = document.getElementById("etape").value;
	var values = [];
	var value = [];
	<c:forEach items="${semestres}" var="id">
	if (x==${id.etape.id_etape}) {
		values.push("${id.id_semestre}");
		value.push("${id.libelle_semestre}");
		}
</c:forEach>
	var select = document.getElementById("semestre");
	for(index in values) {
	    select.options[select.options.length] = new Option(value[index],values[index]);
	    select.options[select.options.length-1].value =values[index];
	
	 
	};
	
	}
	</script>

		<!-- Module-->
		<script language="javascript">

	var x = document.getElementById("semestre").value;
	var values = [];
	var value = [];
	<c:forEach items="${modules}" var="id">
	if (x==${id.semestre.id_semestre}) {
		values.push("${id.id_module}");
		value.push("-${id.libelle_module}");}
</c:forEach>

	var select = document.getElementById("module");
	for(index in values) {
		 select.options[select.options.length] = new Option(value[index],values[index]);
		    select.options[select.options.length-1].value =values[index];
		
	};
	
	 function changeFuncModule() {
		 var select = document.getElementById("module");
		 var length = select.options.length;
		 for (i = length-1; i >= 0; i--) {
		   select.options[i] = null;
		 }

	var x = document.getElementById("semestre").value;
	var values = [];
	var value = [];
	<c:forEach items="${modules}" var="id">
	if (x==${id.semestre.id_semestre}) {
		values.push("${id.id_module}");
		value.push("-${id.libelle_module}");
		}
</c:forEach>
	var select = document.getElementById("module");
	for(index in values) {
	    select.options[select.options.length] = new Option(value[index],values[index]);
	    select.options[select.options.length-1].value =values[index];
	
	 
	};
	
	}
	</script>
	
	<!-- Element-->
		<script language="javascript">

	var x = document.getElementById("module").value;
	var values = [];
	var value = [];
	<c:forEach items="${elements}" var="id">
	if (x==${id.module.id_module}) {
		values.push("${id.id_element}");
		value.push("${id.libelle_element}");}
</c:forEach>

	var select = document.getElementById("element");
	for(index in values) {
		 select.options[select.options.length] = new Option(value[index],values[index]);
		    select.options[select.options.length-1].value =values[index];
		
	};
	
	 function changeFuncElement() {
		 var select = document.getElementById("element");
		 var length = select.options.length;
		 for (i = length-1; i >= 0; i--) {
		   select.options[i] = null;
		 }

	var x = document.getElementById("module").value;
	var values = [];
	var value = [];
	<c:forEach items="${elements}" var="id">
	if (x==${id.module.id_module}) {
		values.push("${id.id_element}");
		value.push("${id.libelle_element}");
		}
</c:forEach>
	var select = document.getElementById("element");
	for(index in values) {
	    select.options[select.options.length] = new Option(value[index],values[index]);
	    select.options[select.options.length-1].value =values[index];
	
	 
	};
	
	}
	</script>
	
	</layout:put>
</layout:extends>