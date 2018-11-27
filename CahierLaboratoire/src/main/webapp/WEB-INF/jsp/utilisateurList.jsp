
<%@ page session="false" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

<c:url var="edit" value="/actions/product/edit" />

<html>
<head>
<title>Liste Utilisateur</title>
</head>
<body>
    <h1>Utilisateurs</h1>
    <table border='1'>
        <tr>
	    	<th>Nom</th>
	    	<th>PrÈnom</th>
	    	<th>Email</th>
	    	<th>Mot de passe</th>
	    	<th>Administrateur</th>
	    </tr>
	    <c:forEach items="${utilisateurs}" var="u">
        	<tr>
		        <td><c:out value="${u.nom}" /></td>
		        <td><c:out value="${u.prenom}" /></td>
		        <td><c:out value="${u.email}" /></td>
		        <td><c:out value="${u.motDePasse}" /></td>
		        <td><c:out value="${u.isAdmin}" /></td>   
		        		</c:forEach>
			</tr>


    </table>
    <p><a href="${edit}">Nouvel Utilisateur (non implÈmentÈ pour le moment)</a></p>
</body>
</html>