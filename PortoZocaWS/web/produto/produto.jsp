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
        <title>Produtos</title>
        <script type="text/javascript">
            // Chama inclusão de produtos
            function botaoAdd() {
                var referencia = document.getElementById("Referencia").value;
                var descricao = document.getElementById("Descricao").value;
                window.location = "\\PortoZoca\\produto?botaoAdd=Sim" + "&referencia=" + referencia + "&descricao=" + descricao;
            }
            // Chama exclusão de produtos
            function botaoExc(id) {
                window.location = "\\PortoZoca\\produto?botaoExc=Sim" + "&id=" + id;
            }

            function botaoCon(prd){
                window.location = "\\PortoZoca\\produto?botaoCon=Sim" + "&id=" + prd.produtoId;
            }

        </script>
        <style>
            .botao {
                height: 10px;
                width: 40px;
            }
        </style>
        <link rel="stylesheet" type="text/css" href="https://unpkg.com/papercss/dist/paper.min.css">
    </head>
    <body>
        <h2 align="center"> Produtos</h2>

    <center>
        <div class="card" style="width: 430px;">
            <div class="card-body">
                <h4 class="card-title">Inclusão</h4>
                <div>Referência</div>
                <input type="text" value="${prod.referencia}" name="referencia" id="Referencia" style="width: 75%;">
                <br>
                <div>Descrição</div>
                <input type="text" value="${prod.descricao}" name="descricao" id="Descricao"  style="width: 75%;">
                <input name="submit" type="button" value="Ok" onclick="botaoAdd()">
            </div>
        </div>
    </center>

    <c:if test="${not empty gravou_ok}">
        <m:modal message="Gravou produto com sucesso!"
                 substitle="DEU BOM"
                 title="Informação!">
        </m:modal>
    </c:if>
    <c:if test="${not empty deletou_ok}">
        <m:modal message="Excluiu produto com sucesso!"
                 substitle="DEU BOM"
                 title="Informação!">
        </m:modal>
    </c:if>
    <c:if test="${not empty error}">

        <m:modal message="${error}"
                 substitle="DEU RUIM"
                 title="Informação!">
        </m:modal>
    </c:if>
    <br/><br/>
    <table style="width: 100%; text-align: center; border-collapse: collapse;">
        <caption style="background-color: #06C; color: white;">
            Produtos
        </caption>
        <tr>
            <th>ID</th>
            <th>Referência</th>
            <th>Descrição</th>
            <th>Excluir</th>
        </tr>
        <c:forEach items="${Produtos}" var="p" varStatus="idl">
            <tr style="line-height: 130%; background-color: #AC6; border-bottom: 1px solid lightgray;">
                <td>${p.produtoId}</td>
                <td>${p.referencia}</td>
                <td>${p.descricao}</td>
                <%-- Ações --%>
                <td>
                    <input type="button" value="C" id="consultar" onclick="botaoCon(${p})">
                    <input type="button" value="X" id="excluir" onclick="botaoExc(${p.produtoId})">
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
