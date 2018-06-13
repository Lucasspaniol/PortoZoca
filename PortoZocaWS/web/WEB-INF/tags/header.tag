<%--
    Document   : header
    Created on : 05/06/2018, 00:41:06
    Author     : joaovperin
--%>
<%@tag description="Header TAG" pageEncoding="UTF-8"%>
<%@attribute name="name" description="Name to show" required="true" %>
<%@attribute name="backUrl" description="URL to back button" required="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Tag content --%>
<c:if test="${not empty userlogado}">
    <div class="alert alert-secondary" style="height: 90px">
        <%-- Se informou URL para o botão de voltar --%>
        <c:if test="${not empty backUrl}">
            <div style="float: left; margin-left: 10px;">
                <button onclick="window.location.href = '/PortoZoca/${backUrl}'">Voltar</button>
            </div>
        </c:if>
        <div style="float: left; margin-left: 10px;">
            <button onclick="window.location.href = '/PortoZoca/produto'">Produto</button>
        </div>
        <div style="float: left; margin-left: 10px;">
            <button onclick="window.location.href = '/PortoZoca/localizacao'">Localização</button>
        </div>
        <div style="float: left; margin-left: 10px;">
            <button onclick="window.location.href = '/PortoZoca/lpn'">LPN</button>
        </div>
        <%-- Botão de Logout --%>
        <div style="float: right;">
            <button onclick="window.location.href = '/PortoZoca/Logout'">Logout</button>
        </div>
        <%-- Bem vindo Srº fulano --%>
        <center style="margin-left: -50px">
            <span style="text-align: center;padding-top: 13px;font-size: 33px;">
                ${name} - Bem vindo, Srº <c:out value='${userlogado.apelido != null ? userlogado.apelido : "Usuário desconhecido."}'></c:out>
                </span>
            </center>
        </div>
        <br/>
</c:if>
<c:if test="${empty userlogado}">
    <% response.sendRedirect("login/form.jsp");%>
</c:if>