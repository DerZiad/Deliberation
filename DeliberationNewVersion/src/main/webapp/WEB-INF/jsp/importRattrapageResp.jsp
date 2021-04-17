<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<layout:extends name="layout-resp.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Deliberation Rattrapage</h5>
				<form method="get" action="/delib/ListRatt">
				<input name="module" id="module" value="${module.id_module}"
							type="hidden" class="form-control" readonly>
				<button class="mb-2 mr-2 btn btn-success" >Liste rattrapage</button>
				</form>
				<form action="/delib/UploadRattrapage" method="Post" enctype="multipart/form-data">


				<input name="module" id="module" value="${module.id_module}"
							type="hidden" class="form-control" readonly>
					<div class="form-row">
						<div class="col-md-6">
							<div class="position-relative form-group">
			      				<label for="annee_academique" class="">Inserer le fichier excel</label>
			      				<input name="file" id="file" type="file" class="form-control-file">
							</div>
						</div>
						
						</div>
						
					<button class="mt-2 btn btn-primary col-md-12" type="submit">Valider</button>
				</form>
				
			</div>
		</div>

	</layout:put>
</layout:extends>