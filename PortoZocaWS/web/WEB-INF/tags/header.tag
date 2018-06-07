<%--
    Document   : header
    Created on : 05/06/2018, 00:41:06
    Author     : joaovperin
--%>
<%@tag description="Header TAG" pageEncoding="UTF-8"%>
<%@attribute name="name" description="Name to show" required="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Tag content --%>
<c:if test="${not empty userlogado}">
    <div class="alert alert-secondary" style="height: 90px">
        <div style="float: right;">
            <button onclick="window.location.href = '/PortoZoca/Logout'">Logout</button>
        </div>
        <div style="text-align: center;padding-top: 13px;font-size: 33px;">
            ${name} - Bem vindo, Srº <c:out value='${userlogado.apelido != null ? userlogado.apelido : "Usuário desconhecido."}'></c:out>
        </div>
    </div>
    <br/>
</c:if>
<c:if test="${empty userlogado}">
    <% response.sendRedirect("login/form.jsp");%>
</c:if>