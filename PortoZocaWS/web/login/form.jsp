<%--
    Document   : produtos
    Created on : 06/06/2018, 23:26:18
    Author     : Jonas
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="m" uri="/WEB-INF/tlds/tags"%>
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
        <form method="POST" action="/PortoZoca/Login">
            <label for="user">User</label>
            <input type="text" name="user" id="user" placeholder="Usuário"/>
            <label for="pass">Pass</label>
            <input type="text" name="pass" placeholder="Senha"/>
            <br/>
            <input type="submit" value="Login">
            <a href="javascript:alert('se fudeu bocaberta')">Esqueci minha senha :/</a>
        </form>

        <c:if test="${not empty error}">
           <br/>
           <br/>
           <h2>Erro: ${error}.</h2>
        </c:if>
    </center>

    <script type="text/javascript">
    </script>
</body>
</html>
