<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="app-sidebar sidebar-shadow" scrolling="yes">
	<div class="app-header__logo">
		<div class="logo-src"></div>
		<div class="header__pane ml-auto">
			<div>
				<button type="button"
					class="hamburger close-sidebar-btn hamburger--elastic"
					data-class="closed-sidebar">
					<span class="hamburger-box"> <span class="hamburger-inner"></span>
					</span>
				</button>
			</div>
		</div>
	</div>
	<div class="app-header__mobile-menu">
		<div>
			<button type="button"
				class="hamburger hamburger--elastic mobile-toggle-nav">
				<span class="hamburger-box"> <span class="hamburger-inner"></span>
				</span>
			</button>
		</div>
	</div>
	<div class="app-header__menu">
		<span>
			<button type="button"
				class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
				<span class="btn-icon-wrapper"> <i
					class="fa fa-ellipsis-v fa-w-6"></i>
				</span>
			</button>
		</span>
	</div>
	<div class="scrollbar-sidebar">
		<div class="app-sidebar__inner">
			<ul class="vertical-nav-menu">
				<li class="app-sidebar__heading">Accueil</li>
				<li><a href="/" class="${dashboard}"> <i
						class="metismenu-icon pe-7s-rocket"></i> Tableau de bords
				</a></li>

				<li><a href="#" aria-expanded="false"> <i
						class="metismenu-icon pe-7s-id"></i> Etablissements <i
						class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
				</a>
					<ul class="mm-collapse" style="height: 7.04px;">
						<li><a href="/admin/etablissement/creer"
							class="${EtablissementCreate}"> <i
								class="metismenu-icon pe-7s-add-user"></i>Ajouter un
								etablissement
						</a></li>
						<li><a href="/admin/etablissement/liste"
							class="${listEtablissement}"> <i
								class="metismenu-icon pe-7s-menu"></i>Liste des etablissements
						</a></li>
					</ul></li>
				<li><a href="#" aria-expanded="false"> <i
						class="metismenu-icon pe-7s-id"></i> Responsables <i
						class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
				</a>
					<ul class="mm-collapse" style="height: 7.04px;">
						<li><a href="/admin/responsable/creer"
							class="${ResponsableCreate}"> <i
								class="metismenu-icon pe-7s-add-user"></i>Ajouter un
								responsable
						</a></li>
						<li><a href="/admin/responsable/liste"
							class="${listResponsable}"> <i
								class="metismenu-icon pe-7s-menu"></i>Liste des responsables
						</a></li>
					</ul></li>
				<li><a href="#" aria-expanded="false"> <i
						class="metismenu-icon pe-7s-id"></i> Filières <i
						class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
				</a>
					<ul class="mm-collapse" style="height: 7.04px;">
						<li><a href="/admin/filiere/creer" class="${FiliereCreate}"> <i
								class="metismenu-icon pe-7s-add-user"></i>Ajouter une filière
						</a></li>
						<li><a href="/admin/filiere/liste" class="${listFiliere}"> <i
								class="metismenu-icon pe-7s-menu"></i>Liste des filières
						</a></li>
					</ul></li>
				<li><a href="#" aria-expanded="false"> <i
						class="metismenu-icon pe-7s-id"></i> Professeurs <i
						class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
				</a>
					<ul class="mm-collapse" style="height: 7.04px;">
						<li><a href="/admin/professeur/creer" class="${ProfesseurCreate}">
								<i class="metismenu-icon pe-7s-add-user"></i>Ajouter un
								Professeur
						</a></li>
						<li><a href="/admin/professeur/liste" class="${listProfesseur}">
								<i class="metismenu-icon pe-7s-menu"></i>Liste des Professeurs
						</a></li>
					</ul></li>
				<li><a href="#" aria-expanded="false"> <i
						class="metismenu-icon pe-7s-id"></i> Modules <i
						class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
				</a>
					<ul class="mm-collapse" style="height: 7.04px;">
						<li><a href="/admin/module/creer" class="${ModuleCreate}"> <i
								class="metismenu-icon pe-7s-add-user"></i>Créer un Module
						</a></li>
						<li><a href="/admin/module/liste" class="${listModule}"> <i
								class="metismenu-icon pe-7s-menu"></i>Liste des Modules
						</a></li>
					</ul></li>
				<li><a href="/admin/student/list" class="${listStudent}"> <i
						class="metismenu-icon pe-7s-study"></i> Liste des étudiants
				</a></li>
				<li><a href="#" aria-expanded="false"> <i
						class="metismenu-icon pe-7s-id"></i> Inscription EnLigne <i
						class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
				</a>
					<ul class="mm-collapse" style="height: 7.04px;">
						<li><a href="/admin/student/InscriptionEnLigne"
							class="${InscriptionEnLigne}"> <i
								class="metismenu-icon pe-7s-add-user"></i>Ajouter I. En ligne
						</a></li>
						<li><a href="/admin/student/ListInscriptionEnligne"
							class="${listInscriptions}"> <i
								class="metismenu-icon pe-7s-menu"></i>Liste I. en ligne
						</a></li>
					</ul></li>

				<li><a href="#" aria-expanded="false"> <i
						class="metismenu-icon pe-7s-id"></i> Inscription Administrative <i
						class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
				</a>
					<ul class="mm-collapse" style="height: 7.04px;">

						<li><a href="/admin/inscription/InscriptionAdministrative"
							class="${InscriptionAdministrative}"> <i
								class="metismenu-icon pe-7s-add-user"></i>Ajouter I.
								Administrative
						</a></li>
						<li><a href="/inscription/ListInscriptionAdministrative"
							class="${listAdministartive}"> <i
								class="metismenu-icon pe-7s-menu"></i> Listes I. Administratives
						</a></li>
					</ul></li>

				<li><a href="#" aria-expanded="false"> <i
						class="metismenu-icon pe-7s-id"></i> Inscription Pedagogique <i
						class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
				</a>
					<ul class="mm-collapse" style="height: 7.04px;">
						<li><a href="/inscription/MenuPedagogique"
							class="${InscriptionPedagogique}"> <i
								class="metismenu-icon pe-7s-add-user"></i>Ajouter I. Pédagogique
						</a></li>
						<li><a href="/inscription/menuListPedagogique"
							class="${listPedagogique}"> <i
								class="metismenu-icon pe-7s-menu"></i>Listes I. Pedagogique
						</a></li>
					</ul></li>
				<li><a href="/admin/inscription/StructureEnseignement"
					class="${StructureEnseignement}"> <i
						class="metismenu-icon pe-7s-graph3"></i> Structure d'Enseignement
				</a></li>
				
				<li><a href="#" aria-expanded="false"> <i
						class="metismenu-icon pe-7s-id"></i> Note Element de module <i
						class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
				</a>
					<ul class="mm-collapse" style="height: 7.04px;">
						<li><a href="/admin/note/choix"
					class="${choixElement}"> <i
						class="metismenu-icon pe-7s-graph3"></i> Ajouter une Note
				</a></li>
						<li><a href="/admin/note/choixList"
					class="${choixElementList}"> <i
						class="metismenu-icon pe-7s-graph3"></i> List des Note
				</a></li>
					</ul></li>
					
					
				
				<li><a href="#" aria-expanded="false"> <i
						class="metismenu-icon pe-7s-id"></i>Deliberation<i
						class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
				</a>
					<ul class="mm-collapse" style="height: 7.04px;">
					<li><a href="/admin/delib/menuDeliberation"
					class="${importdelib}"> <i
						class="metismenu-icon pe-7s-graph3"></i> Deliberation module
				</a></li>
				<li><a href="/admin/delib/choixList"
					class="${importdelib}"> <i
						class="metismenu-icon pe-7s-graph3"></i> Resultat Deliberation module
				</a></li>	
				<li><a href="/admin/delib/choixListSemestre"
					class="${importdelib}"> <i
						class="metismenu-icon pe-7s-graph3"></i> Resultat Deliberation semestre
				</a></li>
				<li><a href="/admin/delib/choixListEtape"
					class="${importdelib}"> <i
						class="metismenu-icon pe-7s-graph3"></i> Resultat Deliberation anuelle
				</a></li>
						
					</ul></li>
				<li><a href="/admin/historique/liste"
					class=""> <i
						class="metismenu-icon pe-7s-graph3"></i> Historique
				</a></li>
				<li><a href="/logout"
					class=""> <i
						class="metismenu-icon pe-7s-graph3"></i> Se déconnecter
				</a></li>
			</ul>
		</div>
	</div>
</div>