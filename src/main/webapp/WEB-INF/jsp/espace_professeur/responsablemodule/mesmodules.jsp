<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="../layout-prof.jsp">
	<layout:put block="content" type="REPLACE">


		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Mes modules</h5>
				<div class="col-md-6">
					<div class="position-relative form-group">
						<label for="name" class="">Module</label> <select name="module"
							id="exampleSelect" class="form-control">
							<c:forEach var="module" items="${modules}">
								<option value="${module.id_module }">${module.libelle_module }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<p>Elements</p>
				<table class="mb-0 table table-striped">
					<thead>
						<tr>
							<th>Nom</th>
							<th>Coefficient</th>
							<th>Validation</th>
						</tr>
					</thead>
					<tbody id="elements">
						<c:forEach var="element" items="${elements}">
							<tr>
								<td><a style="color: black" href="/professeur/responsablemodule/element/${element.id_element}">${element.libelle_element}</a></td>
								<td style="color: black">${element.coeficient}</td>
								<td style="color: black">${element.validation}</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<script>
			var elements = JSON.parse('${elementsjson}');
jQuery(document).ready(function(){
	$('select[name=module]').change(function(){
		var list_value = $('select[name=module]').val();
		var remplir = "";
		for (var i = 0;i<elements.length;i++) {
			if(elements[i].id_element == list_value){
				remplir = remplir + '<tr>' + '\n';
				remplir = remplir + '<a style="color: black" href="/professeur/responsablemodule/element/' + elements[i].id_element + '">' + elements[i].libelle_element + '</td> \n';
				remplir = remplir + '<td style="color: black">' + elements[i].coeficient + '</td> \n';
				remplir = remplir + '<td style="color: black">' + elements[i].validation + '</td> \n';
				remplir = remplir + '</tr>' + '\n';
			}
		}
		$('tbody[id=elements]').html(remplir);
	});
});
		</script>

	</layout:put>
</layout:extends>