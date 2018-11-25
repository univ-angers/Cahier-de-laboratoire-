<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Connexion</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/form.css"/>" />
    </head>
    <body>
        <form method="post" action="<c:url value="/connexion" />">
            <fieldset>
                <legend>Connexion</legend>
                <p>Vous pouvez vous connecter via ce formulaire.</p>
                <c:if test="${empty sessionScope.sessionUtilisateur && !empty requestScope.intervalleConnexions}">
                	<p class="info">(Vous ne vous êtes pas connecté(e) depuis ce navigateur depuis ${requestScope.intervalleConnexions})</p>
                </c:if>

                <label for="nom">Adresse email <span class="requis">*</span></label>
                <input type="email" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['email']}</span>
                <br />

                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['motdepasse']}</span>
                <br />
                
                <br />
                <label for="memoire">Se souvenir de moi</label>
                <input type="checkbox" id="memoire" name="memoire" />
                <br />

                <input type="submit" value="Connexion" class="sansLabel" />
                <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                
                Vérification de la présence d'un objet utilisateur en session
                <c:if test="${!empty sessionScope.sessionUtilisateur}">
                    Si l'utilisateur existe en session, alors on affiche son adresse email.
                	<p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.email}</p>
                </c:if>
            </fieldset>
        </form>
    </body>
</html>
 --%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="<c:url value="/inc/assets/login2.png"/>">

<title>Login</title>

<!-- Bootstrap core CSS -->
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/css/bootstrap.css"/>" />

<!-- Custom styles for this template -->

<link rel="stylesheet" href="<c:url value="/inc/css/signin.css"/>" />
<link rel="stylesheet" href="<c:url value="/inc/css/buttonStyle.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/inc/css/Popups/Popups.css"/>" />
<link rel="stylesheet"
	href="<c:url value="https://fonts.googleapis.com/css?family=Roboto+Mono:500"/>" />
<body class="text-center"
	datacustomTest="${empty form.erreurs ? 'succes' : 'erreur'}">
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark ">
		<a class="navbar-brand" href="#"
			style="font-family: 'Roboto Mono', monospace;"> <img
			src="<c:url value="/inc/assets/agenda2.png"/>" alt="" width="40"
			height="40"> Cahier de laboratoire
		</a>
	</nav>

	<form id="form-connexion" class="form-signin"
		style="font-family: 'Roboto Mono', monospace;" method="post"
		action="connexion">
		<img src="<c:url value="/inc/assets/login2.png"/>" width="64"
			height="64">
		<h1 class="h3 mb-3 font-weight-normal">Login</h1>
		<label for="inputEmail" class="sr-only">Email</label> <input
			type="email" id="email" name="email" class="form-control"
			placeholder="Email" value="<c:out value="${utilisateur.email}"/>"
			required autofocus> <label for="inputPassword"
			class="sr-only">Mot de passe</label> <input type="password"
			id="motdepasse" name="motdepasse" class="form-control"
			placeholder="Mot de passe" required>
		<div class="checkbox mb-3">
			<label> <input type="checkbox" value="remember-me">
				Se souvenir de moi
			</label> <label> <a href="" id="forgotPassword">Mot de passe
					oublié ?</a>
			</label>

		</div>
		<input id="loginButton" class="btn btn-lg btn-primary btn-block"
			type="submit" value="Connexion" />

		<footer>
			<p class="mt-5 mb-3 text-muted">Univ. Angers&copy; 2018-2019</p>
		</footer>

	</form>
</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/inc/js/Scripts/Popup/Popup.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/inc/js/Scripts/LoginPageManagement/ForgotPassword.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/inc/js/Scripts/LoginPageManagement/LoginPage.js"/>"></script>
</html>