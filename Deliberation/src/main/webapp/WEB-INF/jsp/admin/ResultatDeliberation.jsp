<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="${mero}">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Filter</h5>
				<c:if test="${deliberation.module ne null }">
					<c:url var="link" value="/delib/generatePvModule">
						<c:param name="idDeliberation">${deliberation.idDeliberation}</c:param>
					</c:url>
				</c:if>
				<c:if test="${deliberation.semestre ne null}">
					<c:url var="link" value="/delib/generateUltimatePv">
						<c:param name="idDeliberation">${deliberation.idDeliberation}</c:param>
					</c:url>
				</c:if>
				<c:if test="${deliberation.etape ne null }">
					<c:url var="link" value="/delib/generatePvEtape">
						<c:param name="idDeliberation">${deliberation.idDeliberation}</c:param>
					</c:url>
				</c:if>

				<div class="col-md-6">
					<div class="position-relative form-group">
						<a class="btn btn-success" href="${link}">Download Pdf</a>
					</div>
				</div>


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
						<c:if test="${deliberation.notesModule ne null}">
							<c:forEach var="note" items="${deliberation.notesModule}">
								<tr>
									<td style="color: black">${note.idComposed.etudiant.massar_edu}</td>
									<td style="color: black">${note.idComposed.etudiant.last_name_fr}</td>
									<td style="color: black">${note.idComposed.etudiant.first_name_fr}</td>
									<td style="color: black">${note.note}</td>
									<td style="color: black">${note.etat}</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${deliberation.notesSemestre ne null}">
							<c:forEach var="note" items="${deliberation.notesSemestre}">
								<tr>
									<td style="color: black">${note.idComposed.etudiant.massar_edu}</td>
									<td style="color: black">${note.idComposed.etudiant.last_name_fr}</td>
									<td style="color: black">${note.idComposed.etudiant.first_name_fr}</td>
									<td style="color: black">${note.note}</td>
									<td style="color: black">${note.etat}</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${deliberation.notesEtape ne null}">
							<c:forEach var="note" items="${deliberation.notesEtape}">
								<tr>
									<td style="color: black">${note.idComposed.etudiant.massar_edu}</td>
									<td style="color: black">${note.idComposed.etudiant.last_name_fr}</td>
									<td style="color: black">${note.idComposed.etudiant.first_name_fr}</td>
									<td style="color: black">${note.note}</td>
									<td style="color: black">${note.etat}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<script>
			var notes = JSON.parse('${notejson}');

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
										console.log("Ok");
										ch = ch + "<tr>\n";
										ch = ch + '<td style="color: black">' + notes[i].massar_edu + '</td>\n';
										ch = ch + '<td style="color: black">' + notes[i].last_name_fr + '</td>\n';
										ch = ch + '<td style="color: black">' + notes[i].first_name_fr + '</td>\n';
										ch = ch + '<td style="color: black">' + notes[i].note + '</td>\n';
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
										ch = ch + '<td style="color: black">' + notes[i].note + '</td>\n';
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
										ch = ch + '<td style="color: black">' + notes[i].note + '</td>\n';
										ch = ch + '<td style="color: black">' + notes[i].etat + '</td>\n';
										ch = ch + '</tr>\n'		
	}
	$("tbody[id=notes]").html(ch);
}
		</script>
	</layout:put>
</layout:extends>