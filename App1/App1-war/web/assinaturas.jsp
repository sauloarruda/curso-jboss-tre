<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assinaturas</title>
    </head>
    <body>
        <h1>Já estiveram por aqui:</h1>
        <ul>
            <c:forEach var="nome" items="${assinaturas}">
                <li><c:out value="${nome}" /></li>
            </c:forEach>
        </ul>
        <p><a href="/app1">Voltar</a></p>
    </body>
</html>
