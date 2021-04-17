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
						<i class="pe-7s-car icon-gradient bg-mean-fruit" style="color: red;"> </i>
					</div>
					<div>
						Tableau de bords
						<div class="page-title-subheading">Les détails généraux en ce qui concerne les délibérations
						des étudiants.</div>
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
								<span>${nombreEtudiants}</span>
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
								<span>${nombreProfesseurs }</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6 col-xl-4">
				<div class="card mb-3 widget-content bg-grow-early">
					<div class="widget-content-wrapper text-white">
						<div class="widget-content-left">
							<div class="widget-heading">Nombre de responsable de filières</div>
							<div class="widget-subheading"></div>
						</div>
						<div class="widget-content-right">
							<div class="widget-numbers text-white">
								<span>${nombreResponsables }</span>
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
								<span>${nombreFilieres }</span>
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
								<center><div id="columnchart_material" style="width: 800px; height: 500px;"></div></center>
								<h6
									class="text-muted text-uppercase font-size-md opacity-5 font-weight-normal">Meilleurs etudiants</h6>
								<div class="scroll-area-sm">
									<div class="scrollbar-container">
										<ul
											class="rm-list-borders rm-list-borders-scroll list-group list-group-flush">
											<li class="list-group-item">
												<div class="widget-content p-0">
													<div class="widget-content-wrapper">
														<div class="widget-content-left mr-3">
															<img width="42" class="rounded-circle"
																src="/assets/images/avatars/9.jpg" alt="">
														</div>
														<div class="widget-content-left">
															<div class="widget-heading">Coming soon ...</div>
															<div class="widget-subheading">Coming soon ...</div>
														</div>
														<div class="widget-content-right">
															<div class="font-size-xlg text-muted">
																<small class="opacity-5 pr-1">$</small> <span>1337</span>
																<small class="text-danger pl-2"> <i
																	class="fa fa-angle-down"></i>
																</small>
															</div>
														</div>
													</div>
												</div>
											</li>
											<li class="list-group-item">
												<div class="widget-content p-0">
													<div class="widget-content-wrapper">
														<div class="widget-content-left mr-3">
															<img width="42" class="rounded-circle"
																src="/assets/images/avatars/5.jpg" alt="">
														</div>
														<div class="widget-content-left">
															<div class="widget-heading">Coming soon ...</div>
															<div class="widget-subheading">Coming soon ...</div>
														</div>
														<div class="widget-content-right">
															<div class="font-size-xlg text-muted">
																<small class="opacity-5 pr-1">$</small> <span>1</span>
																<small class="text-success pl-2"> <i
																	class="fa fa-angle-up"></i>
																</small>
															</div>
														</div>
													</div>
												</div>
											</li>
											<li class="list-group-item">
												<div class="widget-content p-0">
													<div class="widget-content-wrapper">
														<div class="widget-content-left mr-3">
															<img width="42" class="rounded-circle"
																src="/assets/images/avatars/4.jpg" alt="">
														</div>
														<div class="widget-content-left">
															<div class="widget-heading">Coming soon ...</div>
															<div class="widget-subheading">Java Programmer</div>
														</div>
														<div class="widget-content-right">
															<div class="font-size-xlg text-muted">
																<small class="opacity-5 pr-1">$</small> <span>429</span>
																<small class="text-warning pl-2"> <i
																	class="fa fa-dot-circle"></i>
																</small>
															</div>
														</div>
													</div>
												</div>
											</li>
											<li class="list-group-item">
												<div class="widget-content p-0">
													<div class="widget-content-wrapper">
														<div class="widget-content-left mr-3">
															<img width="42" class="rounded-circle"
																src="/assets/images/avatars/3.jpg" alt="">
														</div>
														<div class="widget-content-left">
															<div class="widget-heading">Coming soon ...</div>
															<div class="widget-subheading">Web Developer</div>
														</div>
														<div class="widget-content-right">
															<div class="font-size-xlg text-muted">
																<small class="opacity-5 pr-1">$</small> <span>129</span>
																<small class="text-danger pl-2"> <i
																	class="fa fa-angle-down"></i>
																</small>
															</div>
														</div>
													</div>
												</div>
											</li>
											<li class="list-group-item">
												<div class="widget-content p-0">
													<div class="widget-content-wrapper">
														<div class="widget-content-left mr-3">
															<img width="42" class="rounded-circle"
																src="/assets/images/avatars/2.jpg" alt="">
														</div>
														<div class="widget-content-left">
															<div class="widget-heading">Coming soon ...</div>
															<div class="widget-subheading">UI Designer</div>
														</div>
														<div class="widget-content-right">
															<div class="font-size-xlg text-muted">
																<small class="opacity-5 pr-1">$</small> <span>54</span>
																<small class="text-success pl-2"> <i
																	class="fa fa-angle-up"></i>
																</small>
															</div>
														</div>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</layout:put>
</layout:extends>