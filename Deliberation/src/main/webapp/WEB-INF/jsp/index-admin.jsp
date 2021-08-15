<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<layout:extends name="layout.jsp">
	<layout:put block="content" type="REPLACE">


		<div class="app-page-title">
			<div class="page-title-wrapper">
				<div class="page-title-heading">
					<div class="page-title-icon">
						<i class="pe-7s-car icon-gradient bg-mean-fruit"
							style="color: red;"> </i>
					</div>
					<div>
						Tableau de bords
						<div class="page-title-subheading">Les détails généraux en
							ce qui concerne les délibérations des étudiants.</div>
					</div>
				</div>

			</div>
		</div>
		<div class="row">
			<div class="col-md-6 col-xl-4">
				<div class="card mb-3 widget-content bg-midnight-bloom">
					<div class="widget-content-wrapper text-white">
						<div class="widget-content-left">
							<div class="widget-heading">Nombre d'étudiants</div>
							<div class="widget-subheading"></div>
						</div>
						<div class="widget-content-right">
							<div class="widget-numbers text-white">
								<span>${etudiants.size()}</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6 col-xl-4">
				<div class="card mb-3 widget-content bg-arielle-smile">
					<div class="widget-content-wrapper text-white">
						<div class="widget-content-left">
							<div class="widget-heading">Nombre de professeurs</div>
							<div class="widget-subheading"></div>
						</div>
						<div class="widget-content-right">
							<div class="widget-numbers text-white">
								<span>${professeurs.size() }</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6 col-xl-4">
				<div class="card mb-3 widget-content bg-grow-early">
					<div class="widget-content-wrapper text-white">
						<div class="widget-content-left">
							<div class="widget-heading">Filières</div>
							<div class="widget-subheading"></div>
						</div>
						<div class="widget-content-right">
							<div class="widget-numbers text-white">
								<span>${filieres.size() }</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="d-xl-none d-lg-block col-md-6 col-xl-4">
				<div class="card mb-3 widget-content bg-premium-dark">
					<div class="widget-content-wrapper text-white">
						<div class="widget-content-left">
							<div class="widget-heading">Nombre de filières</div>
							<div class="widget-subheading"></div>
						</div>
						<div class="widget-content-right">
							<div class="widget-numbers text-warning">
								<span>${filieres.size() }</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-lg-12">
				<div class="mb-3 card">
					<div class="card-header-tab card-header-tab-animation card-header">
						<div class="card-header-title">
							<i class="header-icon lnr-apartment icon-gradient bg-love-kiss">
							</i> Statistiques
						</div>
					</div>
					<div class="card-body">
						<div class="tab-content">
							<div class="tab-pane fade show active" id="tabs-eg-77">
								<!-- chart here -->
								<center>
									<div id="columnchart_material"
										style="width: 800px; height: 500px;"></div>
								</center>
								<h6
									class="text-muted text-uppercase font-size-md opacity-5 font-weight-normal">Meilleurs
									etudiants</h6>

								<div class="scroll-area-sm">
									<div class="scrollbar-container">
										<ul
											class="rm-list-borders rm-list-borders-scroll list-group list-group-flush">
											<c:forEach var="note" items="${notes}">
												<li class="list-group-item">

													<div class="widget-content p-0">
														<div class="widget-content-wrapper">
															<div class="widget-content-left mr-3">
																<c:if test="${dic.get(note.idCompose.etudiant).encodedPhoto ne null }">
																	<img width="42" class="rounded-circle"
																	src="data:image/png;base64, ${dic.get(note.idCompose.etudiant).encodedPhoto }==" alt="">
																</c:if>
											
															</div>
															<div class="widget-content-left">
																<div class="widget-heading">${note.idCompose.etudiant.first_name_fr }</div>
																<div class="widget-subheading">${note.idCompose.etudiant.last_name_fr }</div>
															</div>
															<div class="widget-content-right">
																<div class="font-size-xlg text-muted">
																	<span>${note.note }</span>
																	<small class="text-danger pl-2"> <i
																		class="fa fa-angle-down"></i>
																	</small>
																</div>
															</div>
														</div>
													</div>
												</li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script>
				
		</script>
	</layout:put>
</layout:extends>