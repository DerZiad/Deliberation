<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<layout:extends name="../layout.jsp">
	<layout:put block="content" type="REPLACE">
		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Liste des notes</h5>
				<div class="col-md-6">
						<label>po</label><input type="checkbox" name="discuss" />
				</div>
				<table class="mb-0 table table-hover" id="myTable">
					<thead>
						<tr>
							<th class="th-sm">Nom Etudiant</th>
							<th class="th-sm">Element</th>
							<th class="th-sm">Type</th>
							<th class="th-sm">Note</th>
							<th class="th-sm">Etat</th>
							<th class="th-sm">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="note" items="${notes}">
							<tr id="notes">
								<td><a style="color: black">
										${note.getEtudiant().first_name_fr}
										${note.getEtudiant().last_name_fr}</a></td>
								<td><a style="color: black">
										${note.getElement().getName()}</a></td>
								<td><a style="color: black">
										${note.getElement().getType()}</a></td>
								<td><a style="color: black"> ${note.getNote()}</a></td>
								<td><a style="color: black"> ${note.getEtat()}</a></td>
								
								<c:url var="url" value="/gestionnote/edit/${note.getEtudiant().id_etudiant }/${note.getElement().getId()}">
									<c:param name="type">${note.getElement().getType()}</c:param>
								</c:url>
								<td><a class="btn btn-primary"
									href="${url }">
										Edit</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<script>
			var notes = JSON.parse('${notesjson}');

jQuery(document).ready(function(){
	$('input[name=discuss]').click(function(){
		if (checkboxobject.is(':checked')) {
			remplirNotesByFilter();
		} else {
			remplirNotes();
		}
	});
});

function remplirNotes(){
	var chnote = "";

	for(let i = 0;i<notes.length;i++){
		chnote = chnote + '<td><a style="color: black">' + notes[i].first_name_fr + ' ' + notes[i].last_name_fr + '</a></td>' +"\n";
		chnote = chnote + '<td><a style="color: black">' + notes[i].libelle_element + '</a></td>'
		chnote = chnote + '<td><a style="color: black">' + notes[i].type_element + '</a></td>';
		chnote = chnote + '<td><a style="color: black">' + notes[i].note  +'</a></td>';
		chnote = chnote + '<td><a style="color: black">' + notes[i].etat  + '</a></td>'				
		chnote = chnote + '<td><a class="btn btn-primary" href="/gestionnote/edit/' + notes[i].idStudent + '/' + notes[i].idElement+ '?type=' + notes[i].type_element + '">Edit</a></td>';
	}

	$('tr[id=notes]').html(chnote);
}

function remplirNotesByFilter(){
	var chnote = "";
	for(let i = 0;i<notes.length;i++){

		if(notes[i].validation - 1 <notes[i].note < notes[i].validation){
			chnote = chnote + '<td><a style="color: black">' + notes[i].first_name_fr + ' ' + notes[i].last_name_fr + '</a></td>' +"\n";
			chnote = chnote + '<td><a style="color: black">' + notes[i].libelle_element + '</a></td>'
			chnote = chnote + '<td><a style="color: black">' + notes[i].type_element + '</a></td>';
			chnote = chnote + '<td><a style="color: black">' + notes[i].note  +'</a></td>';
			chnote = chnote + '<td><a style="color: black">' + notes[i].etat  + '</a></td>'				
			chnote = chnote + '<td><a class="btn btn-primary" href="/gestionnote/edit/' + notes[i].idStudent + '/' + notes[i].idElement+ '?type=' + notes[i].type_element + '">Edit</a></td>';
		}
	}

	$('tr[id=notes]').html(chnote);
}
		</script>
	</layout:put>
</layout:extends>