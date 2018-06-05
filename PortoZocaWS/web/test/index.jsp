<%-- 
    Document   : index
    Created on : 04/06/2018, 23:51:03
    Author     : jonas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="https://unpkg.com/papercss/dist/paper.min.css">
    </head>
    <body>
        <h1>Hello TEST</h1>

        <h2>Produtos:</h2>
        <c:forEach items="${produtos}" var="p" varStatus="idl">
            <p>Produto ${p.produtoId} - ${p.descricao}</p>
        </c:forEach>

        <p></p>
    </body>
</html>
