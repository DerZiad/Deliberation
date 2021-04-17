<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<layout:extends name="layout.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Liste des inscriptions d'étudiants</h5>
				<table class="mb-0 table table-hover">
					<thead>
						<tr>
							<th>Nom</th>
							<th class="th-sm">Prénom</th>
							<th class="th-sm">Nationalité</th>
							<th class="th-sm">Date de naissance</th>
							<th class="th-sm">Type du Bac</th>
							<th class="th-sm">Mention</th>
							<th class="th-sm">Etat</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="i" items="${Inscription}">
							<tr onclick="window.location='/student/ProfilInscriptionEnLigne?idie=${i.getId()}'">
								<td><a style="color: black">${i.last_name_fr}</a></td>
								<td><a style="color: black">${i.first_name_fr}</a></td>
								<td><a style="color: black">${i.nationality}</a></td>
								<c:set var="date" value="${i.birth_date}"></c:set>
								<c:set var="birth_date" value="${fn:substring(date,0,10)}"></c:set>
								<td><a style="color: black">${birth_date}</a></td>
								<td><a style="color: black">${i.bac_type}</a></td>
								<td><a style="color: black">${i.mention}</a></td>
								<c:if test="${i.accepted == 0}">  
								<td><a style="color: black"><button disabled class="mb-2 mr-2 btn btn-danger disabled">Pas Encore
                                        </button></a></td>
                                 </c:if>
                                 <c:if test="${i.accepted != 0}">  
								<td><a style="color: black"><button disabled class="mb-2 mr-2 btn btn-success disabled">Accepté
                                        </button></a></td>
                                 </c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</layout:put>
</layout:extends>