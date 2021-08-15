<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="../layout.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Filter</h5>
				<form class="form-group"
					action="/delib/generatePvElement/${deliberation.idDeliberation }"
					method="GET">
					<input type="hidden" name="idDeliberation"
						value="${deliberation.idDeliberation}" />
					<div class="col-md-6">
						<div class="row">
							<div class="col-md-3">
								<div class="position-relative form-group">
									<label for="name" class="">Rattrapage</label>
								</div>
							</div>
							<div class="col-md-2">
								<div class="position-relative form-group">
									<input name="ratt" id="exampleSelect" class="form-check-input"
										type="checkbox" value="1" />
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="position-relative form-group">
							<button class="btn btn-success">Download Pdf Resument</button>
						</div>
					</div>


				</form>
			</div>
			<div class="card-body">
				<table class="mb-0 table table-striped" id="myTable">
					<thead>
						<tr>
							<th class="th-sm">Massar</th>
							<th class="th-sm">Nom</th>
							<th class="th-sm">Prénom</th>
							<th class="th-sm">Note</th>
							<th class="th-sm">Statut</th>
						</tr>
					</thead>
					<tbody id="notes">
						<c:forEach var="note" items="${deliberation.getNotesElement()}">
							<tr>
								<td style="color: black">${note.idCompose.etudiant.massar_edu}</td>
								<td style="color: black">${note.idCompose.etudiant.last_name_fr}</td>
								<td style="color: black">${note.idCompose.etudiant.first_name_fr}</td>
								<c:if test="${note.isAbscent() }">
									<td style="color: black">Abscent</td>
								</c:if>
								<c:if test="${ not note.isAbscent() }">
									<td style="color: black">${note.note_element}</td>
								</c:if>
								<td style="color: black">${note.etat}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<c:if test="${not deliberation.isDelibered() }">
			<div class="main-card mb-3 card">
				<div class="card-body">
					<h5 class="card-title">Déliberation d'element en Rattrapage</h5>
					<form class=""
						action="/delib/deliberationelementprofile/${deliberation.idDeliberation }"
						method="POST" enctype="multipart/form-data">

						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="element-class" class="element-class">Excel
									Rattrapage </label> <input type="file" name="file" id="exampleSelect"
									class="form-control">
							</div>
						</div>
						<div class="col-md-6">
							<div class="position-relative form-group">
								<label for="element-class" class="element-class">Coefficient
									Exam </label> <input type="number" name="coefficentExam"
									id="exampleSelect" class="form-control">
							</div>
						</div>

						<div class="col-md-12">
							<div class="position-relative form-group">
								<div class="form-check">
									<input type="checkbox" class="form-check-input"
										id="consideration" name="consideration" value="1"> <label
										class="form-check-label" for="consideration">Les notes
										controles et Tp seront considérés dans le calcul</label>
								</div>
							</div>
						</div>
						<button formmethod="GET"
							formaction="/delib/downloadrattrapage/${deliberation.idDeliberation }"
							class="mt-2 btn btn-primary col-md-12" type="submit">Download
							Liste Rattrapage</button>
						<button class="mt-2 btn btn-primary col-md-12" type="submit">Valider</button>
					</form>
				</div>
				${error}

			</div>
		</c:if>
		<script>
	var notes = JSON.parse('${notesjson}');

jQuery(document).ready(function(){
					$("input[name=ratt]").click(function(){
							enable_cb($('input[name=ratt]'),"tbody[id=notes]");
					});
					remplir();
});
			
			
function enable_cb(checkboxobject,select) {
		var ch = "";
		if (checkboxobject.is(':checked')) {
			for(let i = 0;i<notes.length;i++){
				if(notes[i].etat == "RATTRAPAGE"){
										ch = ch + "<tr>\n";
										ch = ch + '<td style="color: black">' + notes[i].massar_edu + '</td>\n';
										ch = ch + '<td style="color: black">' + notes[i].last_name_fr + '</td>\n';
										ch = ch + '<td style="color: black">' + notes[i].first_name_fr + '</td>\n';
										if(notes[i].isAbscent == true){
											ch = ch + '<td style="color: black">' + "Abscent" + '</td>\n';
										}else{
											ch = ch + '<td style="color: black">' + notes[i].note + '</td>\n';
										}
										ch = ch + '<td style="color: black">' + notes[i].etat + '</td>\n';
										ch = ch + '</tr>\n'		
				}
			}
		} else {
			for(let i = 0;i<notes.length;i++){
										ch = ch + "<tr>\n";
										ch = ch + '<td style="color: black">' + notes[i].massar_edu + '</td>\n';
										ch = ch + '<td style="color: black">' + notes[i].last_name_fr + '</td>\n';
										ch = ch + '<td style="color: black">' + notes[i].first_name_fr + '</td>\n';
										if(notes[i].isAbscent == true){
											ch = ch + '<td style="color: black">' + "Abscent" + '</td>\n';
										}else{
											ch = ch + '<td style="color: black">' + notes[i].note + '</td>\n';
										}
										ch = ch + '<td style="color: black">' + notes[i].etat + '</td>\n';
										ch = ch + '</tr>\n'		
			}
		}
		$(select).html(ch);
}
function remplir(){
	var ch = "";
	for(let i = 0;i<notes.length;i++){
										ch = ch + "<tr>\n";
										ch = ch + '<td style="color: black">' + notes[i].massar_edu + '</td>\n';
										ch = ch + '<td style="color: black">' + notes[i].last_name_fr + '</td>\n';
										ch = ch + '<td style="color: black">' + notes[i].first_name_fr + '</td>\n';
										if(notes[i].isAbscent == true){
											ch = ch + '<td style="color: black">' + "Abscent" + '</td>\n';
										}else{
											ch = ch + '<td style="color: black">' + notes[i].note + '</td>\n';
										}
										ch = ch + '<td style="color: black">' + notes[i].etat + '</td>\n';
										ch = ch + '</tr>\n'		
	}
	$("tbody[id=notes]").html(ch);
}
		</script>
	</layout:put>
</layout:extends>