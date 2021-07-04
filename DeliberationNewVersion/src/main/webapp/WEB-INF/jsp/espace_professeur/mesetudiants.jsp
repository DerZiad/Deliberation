<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<layout:extends name="layout-prof.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Mes étudiants</h5>
				<form class="form-group"
					action="/professeur/listerElements/${element.id_element}"
					method="POST">
					<div class="col-md-6">
						<div class="position-relative form-group">
							<label for="name" class="">Année academique</label> 
							<select id="annee_academique" name="annee" class="form-control">
									<option value="${annee.id_annee_academique}" selected>${annee}</option>
								</select>
								
								
						</div>
						<div class="position-relative form-group">
							<label for="type" class="">Type</label>
							<div>
								<input type="radio" id="box" name="type" value="${ordinaire}"
									checked> <label for="box">Ordinaire</label>
							</div>
							<div>
								<input type="radio" id="box" name="type" value="${rattrapage}"
									checked> <label for="box">Rattrapage</label>
							</div>
						</div>
						<div class="position-relative form-group">
							<button class="btn btn-success">Download excel</button>
						</div>
					</div>


				</form>
				<table class="mb-0 table table-striped" id="myTable">
					<thead>
						<tr>
							<th class="th-sm">CNE</th>
							<th class="th-sm">Nom</th>
							<th class="th-sm">Prénom</th>
						</tr>
					</thead>
					<tbody id="etudiants">
						<c:forEach var="inscription" items="${inscriptions}">
							<tr>
								<td><a style="color: black" href="#">${inscription.etudiant.massar_edu}</a></td>
								<td><a style="color: black" href="#">${inscription.etudiant.last_name_fr}</a></td>
								<td><a style="color: black" href="#">${inscription.etudiant.first_name_fr}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</layout:put>
</layout:extends>