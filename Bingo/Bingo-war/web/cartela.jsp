<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cartela</title>
        <style type="text/css">
            h2 { color: green }
            h3 { display: inline-block; width: 40px; }
        </style>
        <meta http-equiv="refresh" content="2"/>
    </head>
    <body>
        <h1>Cartela nº ${cartela.id} / Bingo nº ${cartela.bingo.id}</h1>
        <c:if test="${cartela.vencedor}">
            <h2>Bingo!!!</h2>
        </c:if>
        <c:forEach items="${cartela.numeros}" var="cartelaNumero">
            <h3<c:if test="${cartelaNumero.sorteado == true}"> style="color:red;text-decoration:line-through"</c:if>>${cartelaNumero.numero}</h3>
        </c:forEach>
    </body>
</html>
