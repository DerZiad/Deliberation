

**UNIVERSITE MOULAY ISMAIL –UMI-**

**BACHELOR EN GENIE INFORMATIQUE -BGI-**

**Projet**

**Intitulé** :

Délibération

**Réalisé par :**:

**Encadré par** :

**Ali Oubelkacem**

**Chaimae Lamini**

**Ali Bekri**

. **Ziad Bougrine**

Soutenu le 11 devant le jury :

**Bekri Ali, Professeur à la Faculté des Sciences- Meknès**

**Benhlima Said, Professeur à la Faculté des Sciences- Meknès**

**Lamini Chaimae, Professeur à la Faculté des Sciences- Meknès**

**Oubelkacem Ali, Professeur à la Faculté des Sciences- Meknès**

**Année Universitaire : 2020-2021**





Tout d'abord, je tiens à remercier tout particulièrement les personnes suivantes et à leur témoigner

toute ma reconnaissance, pour leur dévouement et leur soutien pour la concrétisation de ce projet:

· ***M. Ali OUBELKACEM***, responsable projet, pour ses conseils éclairés, sa patience, sa disponibilité et

pour la confiance qu'il m'a accordée dès l'ébauche du projet et tout au long de ces quatre mois.

· ***M. Ali BEKRI***, responsable projet, pour m'avoir accordé toute la confiance nécessaire pour élaborer

ce projet librement, et avoir mis à ma disposition tous les moyens nécessaires.

· ***Mme. CHAYMAA LAMINI***, enseignante-chercheuse, pour son investissement, sa contribution et

toute l'aide et les conseils apportés durant ce projet.

· Les enseignants pour leur coopération professionnelle tout au long de cette expérience, pour leur

patience malgré les conditions causées par la pandémie Covid-19 et pour avoir partagé avec nous, les

étudiants du Bachelor, une partie de leurs savoir-faire et de leurs expériences professionnelles.

Et toutes les personnes qui ont facilité la participation à l'élaboration de ce projet.





Table des matières

[Introduction](#br4)[ ](#br4)[Général](#br4)[................................................................................................................................](#br4)[ ](#br4)[4](#br4)

[Contexte](#br6)[ ](#br6)[général](#br6)[ ](#br6)[du](#br6)[ ](#br6)[projet](#br6)[ ](#br6)[......................................................................................................................](#br6)[ ](#br6)[6](#br6)

[Etude](#br8)[ ](#br8)[préliminaire](#br8)[...................................................................................................................................](#br8)[ ](#br8)[8](#br8)

[Conception](#br13)[ ](#br13)[............................................................................................................................................](#br13)[ ](#br13)[13](#br13)

[Environnement](#br32)[ ](#br32)[et](#br32)[ ](#br32)[outils](#br32)[ ](#br32)[de](#br32)[ ](#br32)[développement](#br32)[ ](#br32)[..........................................................................................](#br32)[ ](#br32)[32](#br32)

[Architecture](#br43)[ ](#br43)[et](#br43)[ ](#br43)[couches](#br43)[ ](#br43)[de](#br43)[ ](#br43)[l‘application](#br43)[ ](#br43)[...............................................................................................](#br43)[ ](#br43)[43](#br43)

[Démonstration](#br50)[ ](#br50)[.......................................................................................................................................](#br50)[ ](#br50)[50](#br50)

[Lien](#br56)[ ](#br56)[du](#br56)[ ](#br56)[projet](#br56)[ ](#br56)[........................................................................................................................................](#br56)[ ](#br56)[56](#br56)

[CONCLUSION](#br57)[ ](#br57)[ET](#br57)[ ](#br57)[PERSPECTIVES](#br57)[ ](#br57)[..................................................................................................](#br57)[ ](#br57)[57](#br57)

[BIBLIOGRAPHIE](#br59)[ ](#br59)[................................................................................................................................](#br59)[ ](#br59)[59](#br59)





Introduction

Général





Introduction General

Dans le cadre de ma seconde année du cycle Génie Informatique au Bachelor de l'Université Moulay

Ismaïl, il m'est proposé un projet de 4 mois me permettant de mettre en pratique mes connaissances

théoriques au travers un cahier des charges définissant la conception et le développement d'une

application destinée à la délibération des étudiants du Bachelor. Ce projet a été réalisé durant

l'année universitaire 2019-2020 par mes camarades, et il m'a été proposé d'y apporter des

modifications et des améliorations éventuelles.

Au début, je ne savais pas vraiment ce que c'est une gestion administrative, et j'ai cherché sur la toile

pour comprendre qu'il s'agit de la gestion des étudiants, de leurs notes, des professeurs et de leurs

présences.

Le projet commença début Mars 2020. Mes camarades m'avaient fourni le code nécessaire pour

déployer le projet localement sur mon ordinateur, et le rapport qui contient le cahier des charges sur

la base duquel le projet a été développé. Ma première tâche était de vérifier et de tester l'application

développée par mon camarade, pour savoir si les fonctionnalités s'exécutent correctement, et s'il y a

quelques modifications à appliquer. Après le test de ce programme, et la compréhension de son

mode de construction et de fonctionnement je voulais savoir quelles sont les taches qui me seront

confiée, alors j'ai posé la question à mes encadreurs qui m'ont donné un descriptif oral de l'objet de

mon projet, et m'ont remis également le nouveau cahier des charges sous format électronique. J’ai

trouvé un problème de réalisation du projet car les fonctionnalités étaient incomplètes, pour les

compléter, je devrais changer de conception. J’ai décidé de reprendre un nouveau projet dans lequel

je ferai une nouvelle conception. J'ai conceptualisé un premier diagramme de cas d’utilisation afin de

bien comprendre le cahier de charge et être capable de bien compléter les fonctionnalités.

Après la réalisation de mon premier diagramme de classe, j’ai réalisé un petit projet de test afin de

s’adapter aux bibliothèques nécessaires. J’ai commencé à coder dans le 7 Avril. J’ai pris en

considération un concept de génie logiciel qui est extensibilité afin d’ajouter des fonctionnalités à

l’application, j’ai aussi respecté un dictionnaire de donnée pour rendre mon code lisible et

compréhensible par un autre programmeur. L'application peut être déployée dans le serveur local du

département du Bachelor afin qu'il soit testé en utilisant les données réelles.

Le présent rapport donne un descriptif complet sur le développement de ce projet, et comment j'ai

procédé pour étendre, sur le projet initial, la conception et l'analyse, la sélection des éléments à

ajouter, à modifier, à maintenir ou à abandonner, selon le nouveau cahier des charges. J'ai

également essayé de mettre en place un planning dans le but de réaliser cette application, et pour

qu'elle soit conforme aux objectifs fixées.





Contexte

général du

projet

A – Présentation général

B – Objectifs





Contexte général du projet

A – Présentation général

La formation Bachelor à Meknès suscite de plus en plus d’engouement de la part des nouveaux

bacheliers au niveau nationale. L'accroissement de la demande vis-à-vis de cette formation donne un

travail de plus en plus lourds à l'administration. L'informatisation de la gestion administrative est une

bonne solution pour faciliter, entre autres, les tâches du calcul des notes des étudiants inscrits dans

les différentes filières et semestres, et pour le suivie des appointements des enseignants.

Un système qui est fort, le tout est soumis sous contrôle, et une plateforme permettant à chaque

étudiant de consulter son profil, voir ses notes, une possibilité d’impression d’une candidature

scolaire pour chaque étudiant qui est inscrit administrativement et inscrit pédagogiquement dans le

site web.

B – Objectifs

Cette application développée a pour but non seulement de mettre à disposition des personnes

s'occupant de l'administration de la formation Bachelor, des outils adéquats pour mener à bien ces

tâches quotidiennes, mais aussi permettre d'autres outils aux étudiants et aux professeurs pour une

communication plus rapide et effective avec l'administration. L’application facilite aussi la gestion des

notes par un système de délibération qui se chargera de la récupération des notes, la délibération des

notes, et l’inscription automatique soumit à des conditions définies par l’administrateur. L’application

gère aussi un portail aux étudiants qui offrira une manière facile de consultation des notes et le

parcours de chaque étudiant.





Etude

préliminaire

A – Etude de l’existant

B – Spécification des fonctionnalités

attendues





ETUDE PRELIMIAIRE

A – Etude de l’existant

1- Interface Administrateur : permettant de gérer l’établissement et tous ce qui concernent la

structure académique.

2- Interface Administrateur : permettant de gérer les étudiants et les inscriptions

administratives et pédagogiques des étudiants.

3- Interface Administrateur : permettant de modifier les notes et délibérer les notes des

étudiants avec un système d’inscription pédagogique intelligent qui permet d’un

enregistrement automatique dans l’étape suivant si l’étudiant a validé et une fonctionnalité

d’imprimer un PV qui résume chaque délibération effectuée.

4- Interface Administrateur : permettant de gérer le profil de l’administrateur

5- Interface Administrateur : voir l’historique de chaque action que l’administrateur ferra

6- Interface Administrateur : possibilité de voir les notes et les modifier.

7- Interface Professeur : poser les notes de son élément et télécharger la liste des étudiants

qu’il enseigne pour chaque année académique par rapport à son élément.

8- Interface Professeur : gérer le compte d’utilisateur.

9- Interface Responsable module : c’est une interface professeur spécialisé par des interfaces

de plus qui joueront le rôle de la modification des coefficients des éléments de chaque

module qui lui est responsable.

10- Interface Responsable module : une délibération offerte au responsable de chaque module

de pouvoir délibérer ses propres modules et imprimer un PV PDF qui résume tous les notes

de ses étudiants.

11- Interface Responsable filière : c’est une interface professeur spécialisé par des interfaces de

plus qui joueront le rôle de la modification des coefficients de chaque module et les notes de

validation et d’élimination correspondantes.

12- Interface Responsable filière : Un système qui permet de délibérer la filière dans laquelle il

est responsable avec des impressions des PV qui résument la délibération effectuée.

13- Interface Etudiant : Un système qui offre aux étudiants le droit de consulter ses notes après

chaque délibération effectuée.

14- Interface Etudiant : Une possibilité de télécharger une relevée note par rapport à un

semestre

15- Interface Etudiant : Une possibilité de télécharger un certificat de scolarité de cette

candidature

16- Chaque acteur a le droit de consulter la gestion de compte et modifier son mot de passe





B - Spécification des fonctionnalités attendues

**Spécification**

**fonctionnelles**

**Maintenue par**

**moi**

**Maintenue**

Gestion des étudiants et tous ce X

X

qui rapporte avec les

inscriptions administratives et

pédagogiques

Gestion des modules et

éléments et filières et modules

et tous ce qui rapporte avec la

structure académique

Gestion des systèmes

délibérations

X

X

X

X

Délibération automatique après X

une inscription pédagogique ou

bien après validation de

l’étudiant de tous son semestre

Modification des notes après

uploadé par Excel

X

X

X

Interface Professeur avec

possibilité d’ajouter des notes

avec Excel

Interface Professeur avec

possibilité de poster les notes

des étudiants et de délibérer son

Module si il est un responsable

module

Interface Professeur

X

X

X

X

responsable filière avec

possibilité de délibérer les

notes de sa filière

Interface

Professeur Responsable module

avec possibilité de délibérer les

modules qui est responsable

Possibilité de télécharger

toutes sorte de PV académique

sous format PDF résumant une

délibération

Un module se compose de

plusieurs éléments ou bien d’un

seul élément selon

l’administrateur, responsable

filière et responsable module

Une possibilité de modification X

de compte pour chaque acteur

Une interface étudiant qui

permet de consulter ses notes

par son semestre et imprimer

X





un PV résumant.

Une interface étudiant qui

permet d’imprimer un PV

Archivage des notes après

chaque délibération faite

X

X

J’ai utilisé le sonar cube comme logiciel de scan qui est utilisé

par plusieurs grands société, pour voir la qualité du code et

effectuer une comparaison afin de donner une valeur au travail





Scan de mon projet

Scan du projet que j’ai reçu au début





Conception

Conception et figures





Conception

Une gestion qui facilite la gestion des notes et système de délibération automatique dans des cas qui

sera compatible avec plusieurs environnements et plus optimisé pour offrir une utilisation

remarquable.

\- **L’administrateur** aura le contrôle sur toute l’application et

dans toutes les côtés, l’étudiant doit s’inscrire en ligne, puis

on devra enregistrer l’étudiant administrativement dont

laquelle l’étudiant doit apporter des fichiers confirmatoires

afin que l’administrateur lui valide cette inscription,

finalement, il fera une inscription pédagogiquement qui se

compose d’une affectation des éléments, des modules ou

des semestres, dans ce cas précis, l’étudiant aura l’accès à

son espace.

\- **L’administrateur** aura la possibilité de créer son propre

établissement qui contiendra des filières différentes, chaque

filière aura des étapes qui se composent de semestre (on

pourra définir les semestres diplômantes). Chaque semestre

se compose de plusieurs modules avec des coefficients. Ces

modules sont quelque part composée des plusieurs éléments.

\- **L’administrateur** aura la possibilité de créer des professeurs,

qui auront un espace propre pour consulter, les notes de ses

étudiant ou bien de saisir les notes de chaque étudiant. Cette

saisi est sous forme de deux types différents (Une

importation d’un Excel ou bien une saisi directe).





\- **Responsable module** permet de définir les

coefficients de chaque élément qui constituent son

module, avec une possibilité de délibération de son

module.

\- **Responsable filière** est acteur qui aura le droit de

définir les coefficients de chaque module et les

coefficients des éliminations. Il aura le droit délibérer

les modules de sa filière avec des droits d’impression

des PV résumant la délibération par module et par

semestre.

\- **Le professeur** aura la possibilité de télécharger une

liste des étudiants qui étudient selon l’année

académique et par élément. Le professeur est compté

responsable de cette élément et aura un droit

d’ajouter les notes de cette élément. Les notes sont

calculées

Automatiques.





\- **L’étudiant** pourra accéder à son espace

étudiant et accéder à ses notes. Il a le

droit d’imprimer un PV pour chaque

semestre, et imprimer un certificat de

scolarité





***Conclusion :***

***Le tout sera mis sous la lumière et sous***

***contrôle.***





Description textuelles

Titre : Délibération

Acteurs :

Gestion des étudiants et tous ce qui rapporte

avec les inscriptions administratives et

pédagogiques

Gestion des modules et éléments et filières et

modules et tous ce qui rapporte avec la

structure académique

Administrateur

Gestion des systèmes délibérations

Modification des notes après uploadé par

Excel

Professeur

Professeur a le droit d’ajouter les notes de

son élément

Professeur a le droit de télécharger la liste

des étudiants de son éléments.

Responsable Module

Responsable de module a le droit de émettre

les coefficients de les éléments de ses

modules

Délibérer ses modules associé

Définir les coefficients de module de sa

filière

Responsable de filière

Délibérer et modifier les notes et générer les

PV.

Consulter les notes

Imprimer le relevé de note

Etudiant

Imprimer la certificat scolaire





Diagramme de cas d’utilisation













Description des scénarios

Authentification :

Cas

Authentifier

d’utilisation

Acteur

Utilisateur

Préconditions Utilisateur doit être crée dans la base de données et connaitre ses

identifiants

Scenarios

Nominales

1-L’utilisateur introduit son login et son mot de passe.

2-Le système vérifie les données saisies.

3-Le système redirige l’utilisateur vers son espace

2-a-L’utilisateur saisit des données manquantes.

Scenarios

Alternatifs

2-1-Le système affiche un message d’erreur précisant quelles

données sont manquantes

2-2-L'utilisateur introduit les données manquantes

2-3-Reprise de l'étape 2 du scénario principal

2-b-Les données saisies sont invalide

2-1-Le système affiche un message d'erreur précisant le type de

l'erreur

2-2-L'utilisateur réintroduit les données

2-3-Reprise de l'étape 2 du scénario principal

Accès chaque utilisateur a son espace

Post

Conditions





Ajout d’un professeur :

Cas

Ajout Professeur

d’utilisation

Acteur

Admin

Préconditions Admin accède à son espace d’administration dans le menu d’ajout

professeur

Scenarios

1-l'administrateur cliquer sur l'onglet professeur

Nominales

2-l'administrateur clique sur le bouton ajouter un professeur

3-le système affiche à l'administrateur une page d'ajout d'un prof

4-l'administrateur rempli le formulaire

5-l'administrateur clique sur le bouton enregistrer un professeur

6-Le système vérifie la validité des données saisies

7-le système vérifier l'existence du professeur avec ses données

8-le système enregistre les nouvelles donnes

Scenarios

Exception

Le système affiche un message d’erreur en cas ou l admin manque

la saisie d’un formulaire

Post

Prof Ajoute

Conditions





Inscrire un étudiant administrativement :

Cas

d’utilisation

Inscrire Un étudiant Administrativement

Admin

Acteur

Préconditions Admin accède à son espace d’administration dans le menu

d’inscription administrativement

Scenarios

1-l'administrateur cliquer sur l'onglet inscription

Nominales

2-l'administrateur clique sur le bouton inscription administrative

3-le système affiche à l'administrateur un formulaire concernant

l’importation des papiers pour la phase inscription

4-l'administrateur sélectionne les fichiers demandes

5-l'administrateur clique sur le bouton enregistrer l’inscription

6-Le système vérifie la validité des données saisies

7-le système enregistre les nouvelles donnes

Scenarios

Exception

Le système affiche un message d’erreur en cas ou l admin manque

l’importation d’un formulaire

Post

Etudiant inscris Administrativement passe à la phase d’inscription

Conditions

pédagogique





Modifier les coordonnées pour un utilisateur :

Cas

Gestion profil

d’utilisation

Acteur

Utilisateur

Préconditions L’utilisateur doit être identifié.

Scenarios

\1. L’utilisateur sélectionne dans le menu le lien : “Mes

Nominales

informations”.

\2. Le système renvoie les coordonnées de l’utilisateur connecté et

affiche un bouton de modification

\3. L’utilisateur connecté clique sur le bouton de modification

\4. Le système renvoie un formulaire à l’utilisateur avec les

informations qu’il a le droit de modifier.

\5. L’utilisateur réalise des modifications et les valides.

\6. Le système confirme les modifications.

Scenarios

Exception

Les informations saisies lors de la modification n’ont pas pu être

validée faute d’une erreur de saisie.

Post

Modifications Confirmis

Conditions





Diagramme de Séquence :

Qui sont une représentation temporelle des objets et de leurs intersections.

Authentification

Diagramme de séquence associe à l’authentification d’un utilisateur





Gestion Etablissement





Gestion Module









Inscription en ligne d’un Etudiant non Inscrit :









Environnement

et outils de

développement





SPRING FRAMEWORK

Spring est effectivement un conteneur dit « léger », c’est-à-dire une

infrastructure similaire à un [serveur](https://fr.wikipedia.org/wiki/Serveur_d%27applications)[ ](https://fr.wikipedia.org/wiki/Serveur_d%27applications)[d'applications](https://fr.wikipedia.org/wiki/Serveur_d%27applications)[ ](https://fr.wikipedia.org/wiki/Serveur_d%27applications)[J2EE](https://fr.wikipedia.org/wiki/Java_EE)[.](https://fr.wikipedia.org/wiki/Java_EE)[ ](https://fr.wikipedia.org/wiki/Java_EE)Il prend donc

en charge la création d’objets et la mise en relation d’objets par

l’intermédiaire d’un fichier de configuration qui décrit les objets à

fabriquer et les relations de dépendances entre ces objets. Le gros

avantage par rapport aux serveurs d’application est qu’avec Spring, les

classes n’ont pas besoin d’implémenter une quelconque interface pour

être prises en charge par le framework (au contraire des [serveurs](https://fr.wikipedia.org/wiki/Serveur_d%27applications)

[d'applications](https://fr.wikipedia.org/wiki/Serveur_d%27applications)[ ](https://fr.wikipedia.org/wiki/Serveur_d%27applications)[J2EE](https://fr.wikipedia.org/wiki/Java_EE)[ ](https://fr.wikipedia.org/wiki/Java_EE)et des [EJBs](https://fr.wikipedia.org/wiki/Enterprise_JavaBeans)[).](https://fr.wikipedia.org/wiki/Enterprise_JavaBeans)

SPRING DATA

SPRING SECURITY





SPRING BOOT

SPRING MAIL

Libraries (JAVAX MAIL, POI APACHE(Excel), LIBRE PDF,

GOOGLE GSON)

***HTML – CSS – BOOTSTRAP***

Le HTML et CSS sont le couple qui sert à créer des pages web.

Bootstrap framework HTML/CSS qui améliore la gestion de

la page grâce au concept des conteneurs





***JQUERY – JAVASCRIPT - AJAX***

J’ai utilisé le concept JQUERY-JAVASCRIPT

dans le projet pour introduire de la dynamique dans

les pages web et réduire la pression des demandes

sur le serveur, par exemple pour les opérations de

filtre

JAVASCRIPT: m’aide pour créer des algorithmes

et faire des opération dans le côté client

JQuery: Framework basé sur le javascript

permettant de gérer les évenements dans les pages

web avec un allez retour des donées JSON.





***LESS***

C’est un language dynamique de la géneration de

CSS





***JPQL – SQL – MYSQL-SPRING***

***DATA(hibernate)***

J’ai utilisé comme une base donée mysql et comme

language SQL, j‘ai évité d’utiliser MONGO DB et

le language NoSQL car c’est un SQL non contrôlé

et le projet est soumis sous contrôle de conception.





**SPRING DATA (Hibernate + améliorations):**

Il donne la possibilité de géner le SQL directe

d’après le code JAVA en evitant un contact très

proche avec SQL. Le JPQL joue le rôle dans les

jointures nécessaire pour le fonctionnement.Une

connaissance puissante sur le HIBERNATE et la

partie STATELESS est obligatoire afin de

comprendre le système de MODELS utilisé.





***SPRING TOOLS SUITE***

J’ai préferé d’utilisé le SPRING TOOLS SUITE

comme IDE de programmation pour être au

courant des nouveauté que SPRING apporte

***APACHE TOMCAT SERVEUR***

Le serveur qui permet de recevoir les donées et les

connexions des clients





***ENTREPRISE ARCHITECT***

C’est le logiciel utilisé pour concevoir et déposer

les diagrammes et les figures

***MAVEN***

MAVEN propose des architectures pour les sites

web et un téléchargement très facile pour les

libraries





***SMTP-GMAIL***

Gmail offre un SMTP gratuit afin de pouvoir

échanger des emails d’une manière automatique,

s’il vous plaît, il faut créer un compte et désactiver

l’accès des appareils étranges





***GITHUB***

J’ai employé le serveur GITHUB pour la restauration de

code à chaque essaie durant la période du projet





Architecture et

couches de

l‘application





Architecture du système

J’ai considéré l’architecture MVC comme architecture principale du

système et au relation entre les couches DOA METIER CONTROLLERS, mais

pour la couche METIER, j’ai pris la décision à employer une nouvelle méthode

de travail, c’est le Micro services, dans laquelle la couche métier se compose de

plusieurs couches, afin de travailler d’une façon à l’aise et produire un code

intuitif et puissant, et le meilleur but est d’essayer un nouveau travail

Architecture principale :





Couche Controller





Couche Modèles





Couche DAO

Couches Micro services





Gestion des erreurs





J’ai ajouté un package ADVICE qui signifie des conseils en cas d’erreur, dans

laquelle se trouve la réaction du serveur en cas d’erreur commise





Démonstration





Plateforme administrateur





Plateforme responsable module





Plateforme responsable filière





Plateforme Etudiant





Authentification





Lien du projet

https://github.com/Bz-Root/deliberation





CONCLUSION ET

PERSPECTIVES





CONCLUSION ET PERSPECTIVES

Le projet que j'ai exécuté était la continuation du projet de mes collègues. Je l'ai lu, compris,

expérimenté, et saisis les moindres détails, pour pouvoir bâtir la suite sur des bases solides. Ces

travaux ont nécessité beaucoup de temps pour pouvoir les exécuter avant de commencer mon

propre travail.

Parmi les difficultés que j'ai rencontrées au cours de mes travaux sur ce projet, je peux citer:

· **Premièrement**: Le nouveau cahier des charges a proposé des attribues qui

n'existaient pas dans l'ancien cahier des charger sur lequel mon ancien collègue s'est basé, ce qui m’a

poussé et m’encouragé de commencer un nouveau projet.

· **Deuxièmement**: Des classes et des méthodes déjà établies sont plus avancées par

rapport à mon niveau de formation et que je n'ai découvert qu'au moment où j'étais en train de

travailler sur mon projet, ceci m'a obligé de faire des recherches personnelles sur ces classes et ces

méthodes: j'ai établis un projet exemple (recherche sur SPRING et HIBERNATE) pour pouvoir

maitriser ces librairies.

· **Troisièmement**: La documentation sur le HIBERNATE est insuffisante malgré sa

célébrité, j’ai trouvé plusieurs difficultés pour debuggé les erreurs et j’ai utilisé beaucoup le

STACKOVERFLOW

Comme perspectives éventuelles d'amélioration de cette application, il est possible d'ajouter les

fonctionnalités suivantes :

\-

\-

\-

Améliorer l’interface utilisateur

Ajouter des statistiques vivants

Ajouter une candidature pour l’inscription administrative.

Toutes les fonctionnalités prévues par le cahier des charges ont été réalisées : de la gestion des

étudiants, professeurs et les systèmes de délibérations et le calcul des notes et l’archivage.

J’ai géré toute sorte de fonctionnalité que j’ai étudié en génie logiciel, comme l’extensibilité que j’ai

provisionnée qu’il est possible d’ajouter une plateforme Android au étudiant qui va comporter des

fonctionnalité plus facile, et on pourra ajouter une méthode de scan des notes avec intelligence

artificielle afin de faciliter la tâche de l’Excel.

La programmation d’application web est une tendance de plus en plus adoptée par les entreprises en

raison de ses nombreux avantages. Ce qui rend ce travail très utile pour ma formation, car il m'a

permis de découvrir certains mécanismes de développent Web, et aussi d'améliorer mes

connaissances au niveau du langage Java.





BIBLIOGRAPHIE

· "Cours initiation à Java EE", de l'école EISTI: [https://www.cours-gratuit.com/coursj2ee/cours-](https://www.cours-gratuit.com/coursj2ee/cours-initiation-a-java-ee-pdf/download?chk=0ee7541bc76772411a73795372f36c09)

[initiation-a-java-ee-pdf/download?chk=0ee7541bc76772411a73795372f36c09](https://www.cours-gratuit.com/coursj2ee/cours-initiation-a-java-ee-pdf/download?chk=0ee7541bc76772411a73795372f36c09)

· "Programmation Web avancée Hibernate", par Thierry Hamon:

<https://perso.limsi.fr/hamon/Teaching/P13/PWA-2013-2014/Cours/Hibernate.pdf>

· "Persistance des objets et bases de données relationnelles", par Maude Manouvrier:

<https://www.lamsade.dauphine.fr/~manouvri/HIBERNATE/SLIDES/ORM.pdf>

· Documentation Spring: https://docs.spring.io/spring/docs/current/spring-

frameworkreference/web.html · "Maven: The Definitive Guide":

<http://yassine.ab.free.fr/Documents/maven-guide-fr.pdf>

· "Construction et gestion de développement avec Maven 3.0":

<https://mermet.users.greyc.fr/Enseignement/CoursPDF/maven.pdf>

· Documentation Officiel d'Eclipse: <https://help.eclipse.org/2020-06/index.jsp>

· Documentation Officiel de MySQL: <https://downloads.mysql.com/docs/refman-5.0-fr.a4.pdf>

· "Java WebServer Tomcat, JBoss, JRun, JOnAS", par Michaël Tranchant:

https://ligmembres.imag.fr/plumejeaud/NFE107-fichesLecture/webserver-tomcat-jboss-

jrunjonas\_DOC.pdf

· "Initiation HTML et CSS", par Stéphanie Walter:

<https://stephaniewalter.design/formationscours/initiation-HTML-CSS.pdf>

· Page encyclopédique de Less: <https://fr.wikipedia.org/wiki/Less_(langage)>

· "Javascript", par Therry Lecroq: www-igm.univ-mlv.fr/~lecroq/cours/javascript.pdf

· "Bibliothèque Javascript JQuery", par Yacine Bouzidi:

<http://researchers.lille.inria.fr/~yabouzid/cours3.pdf>

Je propose openclassrooms comme un site des cours, j’ai eu des diplômes et j’ai pu acquérir

plusieurs compétences et connaissances.

