<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bingo!</title>
    </head>
    <body>
        <h1>Bingo nº ${bingo.id}</h1>
        
        <c:if test="${fn:length(bingo.numerosSorteados)==0}">
        <p><a href="../cartela/nova?id=${bingo.id}">Nova Cartela</a></p>
        </c:if>
        
        <c:if test="${bingo.vencedora == null}">
        <p><a href="sortear?id=${bingo.id}">Sortear Número!</a></p>
        </c:if>
        
        <c:if test="${numero != null}">
            <h1>E veio um ${numero}</h1>
        </c:if>
           
        <p>
            Números sorteados até agora:
            <c:forEach items="${bingo.numerosSorteados}" var="numeroSorteado">
                <span>${numeroSorteado}</span>
            </c:forEach>
        </p>
        
        <c:if test="${bingo.vencedora != null}">
            <h1>Temos um vencedor! Cartela nº ${bingo.vencedora.id}</h1>
        </c:if>
    </body>
</html>
