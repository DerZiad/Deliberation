<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<layout:extends name="layout.jsp">
	<layout:put block="content" type="REPLACE">


		<div class="row">
			<div class="col-md-3">
				<div class="main-card mb-3 card">
					<div class="card-body">
						<h5 class="card-title">Historique: ${filiere.nom_filiere }</h5>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="main-card mb-3 card">
					<div class="card-body">
						<h5 class="card-title"></h5>
						
							<div class="position-relative form-group">
								<label for="date" class="">date: </label><input name="name"
									id="name" placeholder="Nom de la filière" type="text"
									class="form-control" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${historique.date}" />">
							</div>
							<div class="position-relative form-group">
								<label for="semester_number" class="">Historique: </label><input name="semester_number"
									id="semester_number" placeholder="Nombre de semestres" type="text"
									class="form-control" value="${historique.historique }">
							</div>
					</div>
				</div>
			</div>
		</div>
		
	</layout:put>
</layout:extends>