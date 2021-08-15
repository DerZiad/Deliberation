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
				<h5 class="card-title">Historiques</h5>
					<div class="col-md-6">
						<div class="position-relative form-group">
							<a class="btn btn-success" href="/admin/historique/vider">Vider</a>
						</div>
					</div>
				<table class="mb-0 table table-hover" id="myTable">
					<thead>
						<tr>
							<th class="th-sm">Historique</th>
							<th class="th-sm">Date</th>
							<th class="th-sm">Action</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="hist" items="${historiques}">
							<tr>
								<td><a style="color: black"> ${hist.historique}</a></td>
								<td><a style="color: black"> ${hist.date} </a></td>
								<td><a class="btn btn-success"
									href="/admin/historique/delete/${hist.id_historique }"
									style="color: black"> delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</layout:put>
</layout:extends>