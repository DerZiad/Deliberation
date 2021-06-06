<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="../layout.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<input type="hidden" name="id_filiere"
					value="${filiere.id_filiere }" />
				<div class="row">
					<div class="col-md-6">
						<h5 class="card-title">Liste des etapes</h5>
					</div>
					<div class="col-md-4"></div>
					<div class="col-md-2">
						<a
							href="/admin/filiere/profile/${filiere.id_filiere}/etapes/create"><i
							class="fas fa-plus-circle"></i> </a>
					</div>
				</div>

				<table class="mb-0 table table-striped">
					<thead>
						<tr>
							<th>Etape</th>
							<th>Semestre</th>
							<th>Action sur semestre</th>
							<th>Diplomante</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="etape" items="${etapes}">
							<tr>
								<td><a
									href="/admin/filiere/profile/${filiere.id_filiere}/etapes/modify/${etape.id_etape }">${etape.libelle_etape}</a></td>
								<td><c:forEach var="semestre" items="${etape.semestres}">
										<a
											href="/admin/filiere/profile/${filiere.id_filiere}/etapes/semestres/modify/${semestre.id_semestre}">${semestre.libelle_semestre}</a>
									</c:forEach></td>
								<td><a class="btn btn-primary"
									href="/admin/filiere/profile/${filiere.id_filiere}/etapes/semestres/create"><i
										class="fas fa-plus-circle"></i> </a></td>
								<c:if test="${etape.diplomante eq true}">
									<td><a class="btn btn-primary"
										href="/admin/filiere/profile/${filiere.id_filiere}/etapes/diplomer/${etape.id_etape }?diplomante=0">suprimer
											diplomation</a></td>
								</c:if>


								<c:if test="${etape.diplomante eq false}">
									<td><a class="btn btn-primary"
										href="/admin/filiere/profile/${filiere.id_filiere}/etapes/diplomer/${etape.id_etape }?diplomante=1">ajouter
											diplomation</a></td>
								</c:if>
								<td><a class="btn btn-primary"
									href="/admin/filiere/profile/${filiere.id_filiere}/etapes/delete/${etape.id_etape}"><i
										class="fas fa-trash"></i> </a></td>


							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</layout:put>
</layout:extends>