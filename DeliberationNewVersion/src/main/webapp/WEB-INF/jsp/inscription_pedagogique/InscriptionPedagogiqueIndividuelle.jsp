<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<layout:extends name="../layout.jsp">
	<layout:put block="content" type="REPLACE">
		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Création d'une Inscirption Pedagogique</h5>
				<form class=""
					action="/admin/inscriptionpedagogique/creer/${inscription_administrative.filiere.id_filiere}/${inscription_administrative.etudiant.id_etudiant}"
					method="POST">
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="name" class="">Type inscription pedagogique</label><select
									name="type-inscription" id="exampleSelect" class="form-control">
									<!--  Noch nicht -->
									<c:forEach var="type" items="${types}">
										<option value="${type}">${type}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="name" class="">Elements</label> <select name="lists"
									id="exampleSelect" class="form-control">

								</select>
							</div>
						</div>
					</div>

					<button class="mt-2 btn btn-primary col-md-12" type="submit">Valider</button>
				</form>
			</div>
		</div>
		<script>
			var modules = JSON.parse('${modules}');
			var elements = JSON.parse('${elements}');
			var semestres = JSON.parse('${semestres}');
			jQuery(document).ready(function(){
				$('select[name=type-inscription]').change(function(){
					let listvalue = $('select[name=type-inscription]').val();
					$('select[name=lists]').html("");
					if(listvalue == 'semestre'){
						var listes_options = "";
						for (var i = 0; i < semestres.length; i++) {
							let id = semestres[i].id_semestre;
							let name = semestres[i].libelle_semestre;
							listes_options = listes_options + "<option value=" + id + "> " + name +  "</option> \n";
						}
						$('select[name=lists]').html(listes_options);
					}else if(listvalue == 'module'){
						var listes_options = "";
						for (var i = 0; i < modules.length; i++) {
							let id = modules[i].id_module;
							let name = modules[i].libelle_module;
							listes_options = listes_options + "<option value=" + id + "> " + name +  "</option> \n";
						}
						$('select[name=lists]').html(listes_options);
					}else{
						var listes_options = "";
						for (var i = 0; i < modules.length; i++) {
							let id = modules[i].id_element;
							let name = modules[i].libelle_element;
							listes_options = listes_options + "<option value=" + id + "> " + name +  "</option> \n";
						}
						$('select[name=lists]').html(listes_options);
					}
				});
			});
		</script>
	</layout:put>
</layout:extends>