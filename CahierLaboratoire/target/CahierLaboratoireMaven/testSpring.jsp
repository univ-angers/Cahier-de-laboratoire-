<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="titre.bonjour"/></title>
</head>
<body>
        <p><spring:message code="libelle.bonjour.lemonde"/></p>


		<table border="1">
            <thead>
                <tr>
                    <th>Email</th>
                    <th>Nom</th>
                    <th>isAdmin?</th>
                </tr>
            </thead>
            <tbody>
           		<c:url var="edit" value="/testSpring" />
           		<c:url var="edit2" value="/list" />
                <c:forEach items="${utilisateurs}" var="utilisateur">
                <c:set var="myVar" value='${utilisateurs}' />

   				 <% System.out.println("edit1: "+ pageContext.findAttribute("edit") ); %>
   				 <% System.out.println(pageContext.findAttribute("edit2") ); %>
                    <tr>
                        <td><c:out value="${utilisateur.email}"/></td>
                        <td><c:out value="${utilisateur.nom}"/></td>
                        <td><c:out value="${utilisateur.isAdmin}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        
</body>
</html>