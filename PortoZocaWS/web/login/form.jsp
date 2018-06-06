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
        <form method="GET" action="/PortoZoca/Login">
            <label for="user">User</label>
            <input type="text" name="user" placeholder="Usuário"></input>
            <label for="pass">Pass</label>
            <input type="text" name="pass" placeholder="Senha"></input>

            <button type="submit" name="submit">Login</button>
            <a href="javascript:alert('se fudeu bocaberta')">Esqueci minha senha :/</a>
        </form>
    </center>

    <script type="text/javascript">
        // Chama inclusão de produtos
        function botaoAdd() {
            var referencia = document.getElementById("Referencia").value;
            var descricao = document.getElementById("Descricao").value;
            window.location = "\\PortoZoca\\produto?botaoAdd=Sim" + "&referencia=" + referencia + "&descricao=" + descricao;
        }
    </script>
</body>
</html>
