<%--
    Document   : produtos
    Created on : 06/06/2018, 23:26:18
    Author     : Jonas
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" uri="/WEB-INF/tlds/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="https://unpkg.com/papercss/dist/paper.min.css">
    </head>
    <body>
        <h2 align="center"> Login :D</h2>
    <center>
        <%-- Formulário de login --%>
        <form method="POST" action="/PortoZoca/Login">
            <label for="user">User</label>
            <input type="text" name="user" id="user" value="Adalberto" placeholder="Usuário" autofocus="autofocus" />
            <label for="pass">Pass</label>
            <input type="password" name="pass" placeholder="Senha"/>
            <br/>
            <input type="submit" value="Login">
            <br/><a href="javascript:alert('se fudeu bocaberta')">Esqueci minha senha :/</a>
        </form>
        <%-- Mensagens --%>
        <c:if test="${not empty msg}">
            <br/><br/><h2>Mensagem: </h2>
            <div class="alert alert-success">${msg}</div>
            <% request.setAttribute("msg", null); %>
        </c:if>
        <c:if test="${not empty error}">
            <br/><br/><h2>Erro: </h2>
            <div class="alert alert-danger">${error}</div>
            <% request.setAttribute("error", null);%>
        </c:if>
    </center>
</body>
</html>
