<%--
    Document   : index
    Created on : 23/05/2018, 21:16:31
    Author     : programacao
--%>
<%@page import="br.portozoca.ws.database.ConexaoFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" uri="/WEB-INF/tlds/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WMS Porto Zoca</title>
        <link rel="stylesheet" type="text/css" href="https://unpkg.com/papercss/dist/paper.min.css">
    </head>
    <body>
        <%-- Header --%>
        <t:header name="Início" ></t:header>
            <h1>Menu principal</h1>
            <h2>Este é o menu principal, aqui você pode selecionar a função que você quer que nosso sistema execute, e talvez, essa função será executada!</h2>
            <input type="button"
                   name="botao-produto"
                   value="Produto"
                   style = "width: 200px"
                   onclick="window.location.href = '/PortoZoca/produto'">
            <input type="button"
                   name="botao-localizacao"
                   value="Localização"
                   style = "width: 200px"
                   onclick="window.location.href = '/PortoZoca/localizacao'">
            <input type="button"
                   name="botao-lpn"
                   value="LPN's"
                   style = "width: 200px"
                   onclick="window.location.href = '/PortoZoca/lpn'">
            <!-- Abre uma conexão e  o Driver JDBC -->
        <%
            ConexaoFactory.start();
        %>

    </body>
</html>
