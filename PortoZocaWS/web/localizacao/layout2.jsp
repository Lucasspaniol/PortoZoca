<%--
    Document   : localizacao
    Created on : 04/06/2018, 23:26:18
    Author     : Spaniol
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="m" uri="/WEB-INF/tlds/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Localizações</title>
        <script type="text/javascript">
            // Define a URL ao clicar no botão "Localização"
            function botaoOk() {
                var loc = document.getElementById("Localizacao").value;
                window.location = "/PortoZoca/localizacao?loc=" + loc;
            }
            function botaoAdd() {
                var loc = document.getElementById("Localizacao").value;
                var div = document.getElementById("Divisao").value;
                window.location = "/PortoZoca/localizacao?addDiv=Sim&Div=" + div + "&loc=" + loc;
            }
            function botaoExcluir(id) {
                var loc = document.getElementById("Localizacao").value;
                window.location = "/PortoZoca/localizacao?eliDiv=Sim&id=" + id + "&loc=" + loc;
            }
        </script>
        <link rel="stylesheet" type="text/css" href="https://unpkg.com/papercss/dist/paper.min.css">
    </head>
    <body>
        <h2 align="center"> Localizações</h2>


    <center>
        <div class="card" style="width: 430px;">
            <div class="card-body">
                <h4 class="card-title">Estrutura</h4>
                <div id="acc">
                    <input type="text" value="${Localizacao}" name="Localizacao" id="Localizacao" style="width: 75%">
                    <input type="submit" value="OK" id="ok" class="botao" onclick="botaoOk()"/>
                </div>
            </div>
        </div>
    </center>
    <br/><br/>

    <!--Mensagem-->
    <div id="msg" align="center">
        ${error}
    </div>
    <table style="width: 100%; text-align: center; border-collapse: collapse;">
        <caption style="background-color: #06C; color: white;">
            Divisões da localização
        </caption>
        <tr>
            <th>Divisão</th>
            <th>Exc</th>
        </tr>
        <c:forEach items="${Localizacoes}" var="l" varStatus="idl">
            <tr style="line-height: 130%; background-color: ${leitura.cor}; border-bottom: 1px solid lightgray;">
                <td>${l.division}</td>
                <td><input type="button" value="X" id="excluir" onclick="botaoExcluir(${l.localizacaoid})"></td>
            </tr>
        </c:forEach>
    </table>
    <br/><br/>
    <%-- Mensagens --%>
    <c:if test="${not empty msg}">
        <div class="alert alert-success">${msg}</div>
        <% request.setAttribute("msg", null); %>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
        <% request.setAttribute("error", null);%>
    </c:if>
    <br/><br/>
    <div class="card" style="width: 700px;">
        <div class="card-body" id="add">
            <center><h4 class="card-title">Divisão</h4></center>
            <input type="text" value="${Divisao}" name="Divisao" id="Divisao" size="68%">
            <input type="button" value="Adicionar" id="add" onclick="botaoAdd()" size="20%">
        </div>
    </div>
</body>
</html>
