
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
<link rel="icon" href="<c:url value="./inc/assets/login2.png"/>">



<!-- Theme included stylesheets -->
<link href="//cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
<link href="//cdn.quilljs.com/1.3.6/quill.bubble.css" rel="stylesheet">
<link href="<c:url value="./inc/css/quillCustomSize.css"/>"
	rel="stylesheet">

<!-- Bootstrap core CSS -->
<link href="<c:url value="./inc/css/bootstrap.css"/>" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<c:url value="./inc/css/signin.css"/>" rel="stylesheet">
<link href="<c:url value="./inc/css/buttonStyle.css"/>" rel="stylesheet">
<link href="<c:url value="./inc/css/loader.css"/>" rel="stylesheet">
<link href="<c:url value="./inc/css/Popups/Popups.css"/>"
	rel="stylesheet">
<link href="<c:url value="./inc/css/searchBar.css"/>" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Roboto+Mono:500"
	rel="stylesheet">
<script type="text/javascript">
	function exportPDF() {
		var doc = new jsPDF()

		doc.text('Hello world!', 10, 10)
		doc.fromHTML($('#data1').html(), 15, 15, {
			'width' : 170
		});
		doc.save('Export.pdf');

	}
</script>


<title>Cahier de laboratoire</title>

</head>
<body class="container-fluid"
	style="font-family: 'Roboto Mono', monospace;">

	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		<a class="navbar-brand" href="#"
			style="font-family: 'Roboto Mono', monospace;"> <img
			src="<c:url value="./inc/assets/agenda2.png"/>" alt="" width="40"
			height="40">
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarCollapse" aria-controls="navbarCollapse"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="accueil">Accueil
						<span class="sr-only">(current)</span> 
				</a></li>
				<c:if test="${ sessionScope.sessionUtilisateur.isAdmin== 1}">
					<li class="nav-item"><a class="nav-link"
						href="./inscription">Ajouter un utilisateur</a></li>
				</c:if>
				
				<li class="nav-item"><a class="nav-link"
						href="#">Aide ?</a></li>

			</ul>

			<button type="button" class="btn btn-info navbar-btn" id="buttonAjoutBillet" data-toggle="modal" data-target="" style="margin-left: 2%;">Creer un billet</button>

			<!-- <button onclick="window.location='../deconnexion';" type="button" class="btn btn-danger navbar-btn" id="buttonDeco" data-toggle="modal" data-target="#exampleModalCenter" style="margin-left: 2%;">Déconnexion</button>-->
				<button  type="button" class="btn btn-danger navbar-btn" id="buttonDeco" data-toggle="modal" data-target="#exampleModalCenter" style="margin-left: 2%;">Déconnexion</button>

		</div>


	</nav>

	<div class="container-fluid row w-100 h-100 position-absolute"
		>

		<div class="col-sm-3" style="margin-left: 0%;">
			<button id="buttonRechercherTag" type="button"
				class="btn btn-info w-100" style="margin-bottom: 5%; margin-top: 5%">Rechercher
				tag</button>

			<button id="buttonIdTag" type="button" class="btn btn-info w-100"
				style="margin-bottom: 5%; margin-top: 5%">+ Ajouter tag</button>


			<div class="card mb-4 shadow-sm">
				<div class="card-header">
					<h4 class="my-0 font-weight-normal ">Tags</h4>
				</div>
				<div id="categorylist" class="card-body row " style="overflow-y:scroll; height:600px;">


				</div>
			</div>
		</div>

		<div class="col-md-8 w-100 mx-auto" id="mainBilletsList"
			style="background: gainsboro; padding-top: 20px; border-radius: 1%;">
			<div class="row">
				<button type="button" id="printAllButton" class="btn btn-dark container-fluid"
					style="margin: 2%;margin-top:0px;" onclick="exportPDF()">
					<img src="./inc/assets/print.png" width="25" height="25" />
					Imprimer tous les billets ?
				</button>
				
			</div>
			<div id="billetsList"></div>
		</div>
		
		
		<footer class="footer w-100 text-center ">
			<p class="mt-5 mb-5 text-muted">Univ. Angers&copy; 2018-2019</p>
		</footer>
	</div>
	
</body>

<!-- 

<!-- <footer>
	<p class="mt-5 mb-5 text-muted">Univ. Angers&copy; 2018-2019</p>
</footer> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
	src="<c:url value="./inc/js/Scripts/Popup/Popup.js"/>"></script>
  
<!-- Main Quill library -->
<script src="//cdn.quilljs.com/1.3.6/quill.js"></script>
<script src="//cdn.quilljs.com/1.3.6/quill.min.js"></script>
<script type="text/javascript"
	src="<c:url value="./inc/js/Scripts/Wysiwyg/wysiwyg.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="./inc/js/Scripts/Billet/GenerateBillet.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="./inc/js/Scripts/Billet/billetQueryInDB.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="./inc/js/Scripts/Tags/addTag.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="./inc/js/Scripts/Tags/generateCategory.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="./inc/js/Scripts/Tags/generateTags.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="./inc/js/Scripts/Popup/Popup.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="./inc/js/Scripts/Recherche/popupRecherche.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="./inc/js/Scripts/Recherche/researchQueryInDB.js"/>"></script>	
<script type="text/javascript"
	src="<c:url value="./inc/js/Scripts/Connexion/deconnexionPopup.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="./inc/js/Scripts/Connexion/Deconnexion.js"/>"></script>	
<script type="text/javascript"
	src="<c:url value="./inc/js/Scripts/Tags/getTags.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="./inc/js/Scripts/BilletTag/GenerateTagInBillet.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="./inc/js/Scripts/BilletTag/popupAddTagToBillet.js"/>"></script>
	
	
	
	
	
<script type="text/javascript"
	src="<c:url value="./inc/js/Scripts/Quill/update.js"/>"></script>

	


	
<!-- JSPDF Library  -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.4.1/jspdf.debug.js"
	integrity="sha384-THVO/sM0mFD9h7dfSndI6TS0PgAGavwKvB5hAxRRvc0o9cPLohB0wb/PTA7LdUHs"
	crossorigin="anonymous"></script>
<!-- jquery -->


<script type="text/javascript"
	src="https://unpkg.com/popper.js"></script>

<script type="text/javascript"
	src="<c:url value="./inc/js/Bootstrap/bootstrap.min.js"/>"></script>

<script type="text/javascript"
	src="<c:url value="./inc/js/Scripts/MainPageManagement/mainPage.js"/>"></script>
</html>

