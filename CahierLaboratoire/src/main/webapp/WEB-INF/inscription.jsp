<%-- <%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Inscription</title>
        <link type="text/css" rel="stylesheet" href="inc/form.css" />
    </head>
    <body>
        <form method="post" action="inscription">
            <fieldset>
                <legend>Inscription</legend>
                <p>Vous pouvez vous inscrire via ce formulaire.</p>

                <label for="nom">Nom d'utilisateur</label>
                <input type="text" id="nom" name="nom" value="<c:out value="${utilisateur.nom}"/>" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['nom']}</span>
                <br />

                <label for="prenom">Prenom d'utilisateur</label>
                <input type="text" id="prenom" name="prenom" value="<c:out value="${utilisateur.prenom}"/>" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['prenom']}</span>
                <br />

                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['motdepasse']}</span>
                <br />

                <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['confirmation']}</span>
                <br />
                
                <label for="email">Adresse email <span class="requis">*</span></label>
                <input type="email" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['email']}</span>
                <br />

                <input type="submit" value="Inscription" class="sansLabel" />
                <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
            </fieldset>
        </form>
    </body>
</html> --%>
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
    <link href="<c:url value="inc/css/bootstrap.css"/>" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/inc/css/signin.css"/>" rel="stylesheet">
    <link href="<c:url value="/inc/css/buttonStyle.css"/>" rel="stylesheet">
    <link href="<c:url value="/inc/css/Popups/Popups.css"/>" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Mono:500" rel="stylesheet">

<body class="text-center" style="font-family: 'Roboto Mono', monospace;">
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="#" style="font-family: 'Roboto Mono', monospace;"> <img src="<c:url value="/inc/assets/agenda2.png"/>"  alt="" width="40" height="40"> Cahier de laboratoire</a>
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
            <div class="col mt-3 mb-3">
                <input id="email" type="text" name="email" class="form-control" placeholder="Email" value="<c:out value="${utilisateur.email}"/>">
                 <span class="erreur">${form.erreurs['email']}</span>
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

</html>