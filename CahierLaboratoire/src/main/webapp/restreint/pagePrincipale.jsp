
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="<c:url value="/inc/assets/login2.png"/>">



<!-- Theme included stylesheets -->
<link href="//cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
<link href="//cdn.quilljs.com/1.3.6/quill.bubble.css" rel="stylesheet">
<link href="<c:url value="/inc/css/quillCustomSize.css"/>"
	rel="stylesheet">

<!-- Bootstrap core CSS -->
<link href="<c:url value="/inc/css/bootstrap.css"/>" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<c:url value="/inc/css/signin.css"/>" rel="stylesheet">
<link href="<c:url value="/inc/css/buttonStyle.css"/>" rel="stylesheet">
<link href="<c:url value="/inc/css/Popups/Popups.css"/>"
	rel="stylesheet">
<link href="<c:url value="/inc/css/searchBar.css"/>" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Roboto+Mono:500"
	rel="stylesheet">

<title>Cahier de laboratoire</title>

</head>
<body class="container-fluid"
	style="font-family: 'Roboto Mono', monospace">
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		<a class="navbar-brand" href="#"
			style="font-family: 'Roboto Mono', monospace;"> <img
			src="<c:url value="/inc/assets/agenda2.png"/>" alt="" width="40"
			height="40"> Cahier de laboratoire
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarCollapse" aria-controls="navbarCollapse"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
				</a></li>
				

				
				<c:if test="${ sessionScope.sessionUtilisateur.isAdmin== 1}">
					<li class="nav-item"><a class="nav-link" href="/CahierLaboratoire/inscription">Inscription</a></li>
				</c:if> 			 		

				
				
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="true"> Dropdown </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="#">Action</a> <a
							class="dropdown-item" href="#">Another action</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Something else here</a>
					</div></li>

			</ul>
			<input type="search" placeholder="Search">


			<button class="btn btn-danger navbar-btn" style="margin-left: 2%;">Déconnexion</button>

		</div>
	</nav>
	<div class="row w-100" style="top: 10%; position: absolute">
	
		<div class="col ">
			<button id="buttonIdTag" type="button" class="btn btn-info w-100" style="margin-bottom: 5%;"  >
				+ Ajouter tag 
			</button>
			<div class="card mb-4 shadow-sm">
				<div class="card-header">
					<h4 class="my-0 font-weight-normal ">Tags </h4>
				</div>
				<div id="taglist"class="card-body">
					<a href="#" class="badge badge-primary">Test</a> <a href="#"
						class="badge badge-success">Success</a> <a href="#"
						class="badge badge-danger">Danger</a> <a href="#"
						class="badge badge-info">Info</a> <a href="#"
						class="badge badge-light">Light</a> <a href="#"
						class="badge badge-dark">Dark</a> <a href="#"
						class="badge badge-primary">Test</a> <a href="#"
						class="badge badge-secondary ">Secondary</a> <a href="#"
						class="badge badge-primary">Test</a> <a href="#"
						class="badge badge-success">Success</a> <a href="#"
						class="badge badge-danger">Danger</a> <a href="#"
						class="badge badge-info">Info</a> <a href="#"
						class="badge badge-light">Light</a> <a href="#"
						class="badge badge-dark">Dark</a> <a href="#"
						class="badge badge-primary">Test</a> <a href="#"
						class="badge badge-secondary ">Secondary</a> <a href="#"
						class="badge badge-primary">Test</a> <a href="#"
						class="badge badge-success">Success</a> <a href="#"
						class="badge badge-danger">Danger</a> <a href="#"
						class="badge badge-info">Info</a> <a href="#"
						class="badge badge-light">Light</a> <a href="#"
						class="badge badge-dark">Dark</a> <a href="#"
						class="badge badge-primary">Test</a> <a href="#"
						class="badge badge-secondary ">Secondary</a> <a href="#"
						class="badge badge-primary">Test</a> <a href="#"
						class="badge badge-success">Success</a> <a href="#"
						class="badge badge-danger">Danger</a> <a href="#"
						class="badge badge-info">Info</a> <a href="#"
						class="badge badge-light">Light</a> <a href="#"
						class="badge badge-dark">Dark</a> <a href="#"
						class="badge badge-primary">Test</a> <a href="#"
						class="badge badge-secondary ">Secondary</a> <a href="#"
						class="badge badge-primary">Test</a> <a href="#"
						class="badge badge-success">Success</a> <a href="#"
						class="badge badge-danger">Danger</a> <a href="#"
						class="badge badge-info">Info</a> <a href="#"
						class="badge badge-light">Light</a> <a href="#"
						class="badge badge-dark">Dark</a> <a href="#"
						class="badge badge-primary">Test</a> <a href="#"
						class="badge badge-secondary ">Secondary</a> <a href="#"
						class="badge badge-primary">Test</a> <a href="#"
						class="badge badge-success">Success</a> <a href="#"
						class="badge badge-danger">Danger</a> <a href="#"
						class="badge badge-info">Info</a> <a href="#"
						class="badge badge-light">Light</a> <a href="#"
						class="badge badge-dark">Dark</a> <a href="#"
						class="badge badge-primary">Test</a> <a href="#"
						class="badge badge-secondary ">Secondary</a> <a href="#"
						class="badge badge-primary">Test</a> <a href="#"
						class="badge badge-success">Success</a> <a href="#"
						class="badge badge-danger">Danger</a> <a href="#"
						class="badge badge-info">Info</a> <a href="#"
						class="badge badge-light">Light</a> <a href="#"
						class="badge badge-dark">Dark</a> <a href="#"
						class="badge badge-primary">Test</a> <a href="#"
						class="badge badge-secondary ">Secondary</a>
				</div>
			</div>
		</div>
		
		<div class="col-10">
<%-- 			 		<div id="billet-1" class="card mb-4 shadow-sm">
				<div class="card-header">

					<h6 class="my-0 font-weight-normal d-inline">Projet_5 Mycose
						de cyrillepinette</h6>
					<button id="buttonAddtag" type="button" class="btn btn-dark d-inline float-right buttonQuill">
						<img id="testquill" src="<c:url value="/inc/assets/edit1.svg"/>"
							alt="" width="25" height="25">
					</button>

				</div>
				<div class="card-body">
					Nom : ${sessionScope.sessionUtilisateur.nom} Prénom : <br>
					${sessionScope.sessionUtilisateur.prenom} Email :<br>
					${sessionScope.sessionUtilisateur.email} MDP :<br>
					${sessionScope.sessionUtilisateur.motdepasse} ADMIN :<br>
					${sessionScope.sessionUtilisateur.isadmin}<br>
					<div id=" data1">
					 Le Lorem Ipsum est
					simplement du faux texte employé dans la composition et la mise en
					page avant impression. Le Lorem Ipsum est le faux texte standard de
					l'imprimerie depuis les années 1500, quand un imprimeur anonyme
					assembla ensemble des morceaux de texte pour réaliser un livre
					spécimen de polices de texte. Il n'a pas fait que survivre cinq
					siècles, mais s'est aussi adapté à la bureautique informatique,
					sans que son contenu n'en soit modifié. Il a été popularisé dans
					les années 1960 grâce à la vente de feuilles Letraset contenant des
					passages du Lorem Ipsum, et, plus récemment, par son inclusion dans
					des applications de mise en page de texte, comme Aldus PageMaker.
					</div>
					<div id="content-container">
						<div id="toolbar-container">
						</div>
						<div id="editor-container"></div>
					</div>
				</div>
			</div> --%>

			<%-- 	<div class="card mb-4 shadow-sm">
				<div class="card-header">
					<h6 class="my-0 font-weight-normal d-inline">Projet_5 Mycose
						de cyrillepinette</h6>
					<button type="button" class="btn btn-dark d-inline float-right">
						<img src="<c:url value="/inc/assets/edit1.svg"/>" alt=""
							width="25" height="25">
					</button>

				</div>
				<div class="card-body">Le Lorem Ipsum est simplement du faux
					texte employé dans la composition et la mise en page avant
					impression. Le Lorem Ipsum est le faux texte standard de
					l'imprimerie depuis les années 1500, quand un imprimeur anonyme
					assembla ensemble des morceaux de texte pour réaliser un livre
					spécimen de polices de texte. Il n'a pas fait que survivre cinq
					siècles, mais s'est aussi adapté à la bureautique informatique,
					sans que son contenu n'en soit modifié. Il a été popularisé dans
					les années 1960 grâce à la vente de feuilles Letraset contenant des
					passages du Lorem Ipsum, et, plus récemment, par son inclusion dans
					des applications de mise en page de texte, comme Aldus PageMaker.

					Le Lorem Ipsum est simplement du faux texte employé dans la
					composition et la mise en page avant impression. Le Lorem Ipsum est
					le faux texte standard de l'imprimerie depuis les années 1500,
					quand un imprimeur anonyme assembla ensemble des morceaux de texte
					pour réaliser un livre spécimen de polices de texte. Il n'a pas
					fait que survivre cinq siècles, mais s'est aussi adapté à la
					bureautique informatique, sans que son contenu n'en soit modifié.
					Il a été popularisé dans les années 1960 grâce à la vente de
					feuilles Letraset contenant des passages du Lorem Ipsum, et, plus
					récemment, par son inclusion dans des applications de mise en page
					de texte, comme Aldus PageMaker. Le Lorem Ipsum est simplement du
					faux texte employé dans la composition et la mise en page avant
					impression. Le Lorem Ipsum est le faux texte standard de
					l'imprimerie depuis les années 1500, quand un imprimeur anonyme
					assembla ensemble des morceaux de texte pour réaliser un livre
					spécimen de polices de texte. Il n'a pas fait que survivre cinq
					siècles, mais s'est aussi adapté à la bureautique informatique,
					sans que son contenu n'en soit modifié. Il a été popularisé dans
					les années 1960 grâce à la vente de feuilles Letraset contenant des
					passages du Lorem Ipsum, et, plus récemment, par son inclusion dans
					des applications de mise en page de texte, comme Aldus PageMaker.</div>
			</div>
 --%>
		</div>
		<footer class="footer w-100 text-center ">
			<p class="mt-5 mb-5 text-muted">Univ. Angers&copy; 2018-2019</p>
		</footer>
	</div>

</body>
<!-- <footer>
	<p class="mt-5 mb-5 text-muted">Univ. Angers&copy; 2018-2019</p>
</footer> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/inc/js/Scripts/Popup/Popup.js"/>"></script>

<!-- Main Quill library -->
<script src="//cdn.quilljs.com/1.3.6/quill.js"></script>
<script src="//cdn.quilljs.com/1.3.6/quill.min.js"></script>
<script type="text/javascript"src="<c:url value="/inc/js/Scripts/Wysiwyg/wysiwyg.js"/>"></script>
<script type="text/javascript"src="<c:url value="/inc/js/Scripts/Billet/GenerateBillet.js"/>"></script>
<script type="text/javascript"src="<c:url value="/inc/js/Scripts/MainPageManagement/mainPage.js"/>"></script>
<script type="text/javascript"src="<c:url value="/inc/js/Scripts/Tags/addTag.js"/>"></script>
<script type="text/javascript"src="<c:url value="/inc/js/Scripts/Popup/Popup.js"/>"></script>
</html>

