<%--
    Document   : produtos
    Created on : 09/06/2018, 12:58:18
    Author     : Jonas
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" uri="/WEB-INF/tlds/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Produtos</title>
        <style>
            .botao {height: 10px; width: 40px;}
        </style>
        <link rel="stylesheet" type="text/css" href="https://unpkg.com/papercss/dist/paper.min.css">
    </head>
    <body>
        <%-- Header --%>
        <t:header name="Lpn" backUrl="index.jsp" ></t:header>
            <h2 align="center">LPN's</h2>
        <%-- Card com os dados da LPN --%>
        <center>
            <div class="card" style="width: 430px;">
                <div class="card-body">
                    <h4 class="card-title">Armazenamento</h4>
                    <div>Referência</div>
                    <input type="text" value="" name="referencia" id="referencia" style="width: 75%;" autofocus="autofocus">
                    <br>
                    <div>Quantidade</div>
                    <input type="text" value="" name="quantidade" id="quantidade"  style="width: 75%;">
                    <br>
                    <div>Localização</div>
                    <input type="text" value="" name="localizacao" id="localizacao"  style="width: 75%;">
                    <br>
                    <input name="submit" type="button" value="Entrada de LPN" onclick="">
                </div>
            </div>
        </center>
    <%-- Mensagens --%>
    <c:if test="${not empty gravou_ok}">
        <t:modal message="Gravou produto com sucesso!"
                 substitle="DEU BOM"
                 title="Informação!">
        </t:modal>
    </c:if>
    <c:if test="${not empty deletou_ok}">
        <t:modal message="Excluiu produto com sucesso!"
                 substitle="DEU BOM"
                 title="Informação!">
        </t:modal>
    </c:if>
    <c:if test="${not empty error}">
        <t:modal message="${error}"
                 substitle="DEU RUIM"
                 title="Informação!">
        </t:modal>
        <c:if test="${not empty exception}">
            exc: ${exception}
        </c:if>
    </c:if>
    <br/><br/>
    <table style="width: 100%; text-align: center; border-collapse: collapse;">
        <caption style="background-color: #06C; color: white;">
            Produtos
        </caption>
        <tr>
            <th>LPN</th>
            <th>Produto</th>
            <th>Localização</th>
            <th>Quantidade</th>
            <th>LPN contenedor</th>
            <th>Funções</th>
        </tr>
        <c:forEach items="${Lpns}" var="lpn" varStatus="idl">
            <tr style="line-height: 130%; background-color: #AC6; border-bottom: 1px solid lightgray;">
                <td>${lpn.lpnId}</td>
                <td>${lpn.produto}</td>
                <td>${lpn.localizacao}</td>
                <td>${lpn.quantidade}</td>
                <td>${lpn.lpnContenedor}</td>
                <%-- Ações --%>
                <td>
                    <input type="button" title="Eliminar" value="X" id="excluir" onclick="botaoExcluir(${lpn})">
                    <input type="button" title="Realizar saída do LPN" value="->" id="Saida" onclick="botaoSaida(${lpn})">
                    <input type="button" title="Observações" value="..." id="obs" onclick="accObs(${lpn})">
                    <input type="button" title="Alterar" value="A" id="alterar" onclick="botaoAlt(${lpn})">                    
                    <input type="button" title="Inserir LPN dentro de contenedor" value="In" id="inserirLpn" onclick="botaoInserirLpn(${lpn})">
                </td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <br/>    
    <div class="card" style="width: 430px;">
        <div class="card-body">
            <form action="">
            Apenas LPNs não contidos <input type="checkbox" name="naoContidos" value="naoContidos" style="float:right; width: 100px;">
            </form>
        </div>
    </div>    
    <br/>
    <br/>
    <%-- Scripts --%>
    <script type="text/javascript">
        
        
        
    </script>

</body>
</html>
