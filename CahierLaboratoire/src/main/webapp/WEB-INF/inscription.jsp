<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="<c:url value="/inc/assets/login2.png"/>">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/inc/js/Scripts/Popup/Popup.js"/>"></script>

    <title>Inscription</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/inc/css/bootstrap.css"/>" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/inc/css/signin.css"/>" rel="stylesheet">
    <link href="<c:url value="/inc/css/buttonStyle.css"/>" rel="stylesheet">
    <link href="<c:url value="/inc/css/Popups/Popups.css"/>" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Mono:500" rel="stylesheet">
    
    <link href="<c:url value="/inc/css/searchBar.css"/>" rel="stylesheet">

<body class="text-center" style="font-family: 'Roboto Mono', monospace;">
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		<a class="navbar-brand" href="/CahierLaboratoire/accueil"
			style="font-family: 'Roboto Mono', monospace;"> <img
			src="<c:url value="/inc/assets/agenda2.png"/>" alt="" width="40" 
			height="40">
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarCollapse" aria-controls="navbarCollapse"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<DIV >				
	    	 <img src="<c:url value='./inc/assets/Mitolab.png'/>" style="height: 60px;width : 300px"/>
	    </DIV>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="./accueil">Accueil
						<span class="sr-only">(current)</span>
					</a>
				</li>
			</ul>

			<button class="btn btn-danger navbar-btn" style="margin-left: 2%;">Déconnexion</button>

		</div>
		
	</nav>	
<div class="d-flex justify-content-center align-items-center container ">
    <div class="row ">
       <form method="post" action="inscription">
            <div class="form-row">
                <div class="col">
                    <input id="nom" name="nom" type="text" class="form-control" placeholder="First name" value="<c:out value="${utilisateur.nom}"/>">
                     <span class="erreur">${form.erreurs['nom']}</span>
                </div>
                <div class="col">
                    <input id="prenom" name="prenom" type="text" class="form-control" placeholder="Last name" value="<c:out value="${utilisateur.prenom}"/>">
                     <span class="erreur">${form.erreurs['prenom']}</span>
                </div>
                
            </div>
            <div class="form-row mt-3">
                <div class="col">
                    <input id="motdepasse" name="motdepasse" type="password" class="form-control" placeholder="Password">
                </div>
                <div class="col">
                    <input id="confirmation" name="confirmation" type="password" class="form-control" placeholder="Confirm Password">
                </div>
                
            </div>
            <div class="form-row">
            	<div class="col mt-3 mb-3">
                	<input id="email" type="text" name="email" class="form-control" placeholder="Email" value="<c:out value="${utilisateur.email}"/>">
                 	<span class="erreur">${form.erreurs['email']}</span>
            	</div>
            </div>
            <div class="form-row">
           		<div class="col mt-3 mb-3">
                	<input id="tag" type="text" name="tag" class="form-control" placeholder="Tag" value="<c:out value=""/>">
                 	<span class="erreur">${form.erreurs['nom']}</span>
            	</div>
            </div>
            <div class="mt-5 mb-3">
    			<input type="checkbox" class="form-check-input" name="admincheck" id="admincheck">
            	<label class="form-check-label" for="admincheck">Administrateur</label>
  			</div>
            <div class="mt-5 mb-3">
                <button id="loginButton" class="btn-lg btn-primary" type="submit">Créer utilisateur</button>
            </div>
             <script type="text/javascript">
             	if("${form.resultat}"=="Succes"){
             		 var popupName= popupCreation("inscriptionPopup");
             		 popupSuccess(popupName,"Successfully created");
             	      $("#"+popupName).fadeTo("slow",1);
             	      $("#"+popupName).delay(1000).fadeTo("slow", 0);
             	}
             </script>
            <footer>
                <p class="mt-5 mb-5 text-muted">Univ. Angers&copy; 2018-2019</p>
            </footer>
        </form>
    </div>
</div>
</body>
<!-- jquery -->

 <script type="text/javascript" src="<c:url value="/inc/js/Bootstrap/bootstrap.min.js"/>"></script>

</html>


