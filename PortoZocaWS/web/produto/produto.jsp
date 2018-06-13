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
        <title>Produtos</title>
        <style>
            .botao {height: 10px; width: 40px;}
        </style>
        <link rel="stylesheet" type="text/css" href="https://unpkg.com/papercss/dist/paper.min.css">
    </head>
    <body>
        <%-- Header --%>
        <t:header name="Produto" backUrl="index.jsp" ></t:header>
            <h2 align="center"> Produtos</h2>
        <%-- Card com os dados para inclusão/alteração --%>
    <center>
        <div class="card" style="width: 430px;">
            <div class="card-body">
                <c:if test='${!funcaoAlt}'>
                    <h4 class="card-title">Inclusão</h4>
                </c:if>
                <c:if test='${funcaoAlt}'>
                    <h4 class="card-title">Alteração</h4>
                </c:if>
                <div>Referência</div>
                <input type="text" value="${prod.referencia}" name="referencia" id="Referencia" style="width: 75%;" autofocus="autofocus">
                <br>
                <div>Descrição</div>
                <input type="text" value="${prod.descricao}" name="descricao" id="Descricao"  style="width: 75%;">
                <br/>
                <c:if test='${!funcaoAlt}'>
                    <input name="submit" type="button" value="Ok" onclick="botaoAdd()">
                </c:if>
                <c:if test='${funcaoAlt}'>
                    <input name="submit" type="button" value="Cancelar" onclick="recarregaPagina()">
                    <input name="submit" type="button" value="Ok" onclick="botaoAltFim(${prod})">
                </c:if>
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
            <th>ID</th>
            <th>Referência</th>
            <th>Descrição</th>
            <th>Funções</th>
        </tr>
        <c:forEach items="${Produtos}" var="p" varStatus="idl">
            <tr style="line-height: 130%; background-color: #AC6; border-bottom: 1px solid lightgray;">
                <td>${p.produtoId}</td>
                <td>${p.referencia}</td>
                <td>${p.descricao}</td>
                <%-- Ações --%>
                <td>
                    <input type="button" value="A" id="consultar" onclick="botaoAlt(${p})">
                    <input type="button" value="X" id="excluir" onclick="botaoExc(${p.produtoId})">
                    <input name="submit" type="button" value="Obs" onclick="accObs(${p})">
                </td>
            </tr>
        </c:forEach>
    </table>
    <%-- Modal para aceitar observações --%>
    <c:if test="${aceitaObs}">
        <input class="modal-state" id="modal-obs" type="checkbox" checked </div>
        <div class="modal">
            <label class="modal-bg" for="modal-obs"></label>
            <div class="modal-body" style="width:70%;height: 50%;">
                <label class="btn-close" for="modal-obs">X</label>
                <h4 class="modal-title">Produto: ${prod.referencia}</h4>
                <h5 class="modal-subtitle">Observação</h5>
                <textarea rows="4" cols="50" name="Obs" id="Obs" style="width: 100%;height: 50%">${prod.observacao}</textarea>
                <button for="modal-obs" class="paper-btn" autofocus="autofocus" onclick="grvObs(${prod})">Ok</button>
            </div>
        </div>
    </c:if>
    <%-- Scripts --%>
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

        function botaoAlt(prd) {
            window.location = "\\PortoZoca\\produto?botaoAlt=Sim" + "&id=" + prd.produtoId;
        }

        function botaoAltFim(prd) {
            var referencia = document.getElementById("Referencia").value;
            var descricao = document.getElementById("Descricao").value;
            window.location = "\\PortoZoca\\produto?botaoAltFim=Sim" + "&id=" + prd.produtoId + "&referencia=" + referencia + "&descricao=" + descricao;
        }
        function recarregaPagina() {
            window.location = "\\PortoZoca\\produto";
        }
        function accObs(prd) {
            var referencia = document.getElementById("Referencia").value;
            var descricao = document.getElementById("Descricao").value;
            window.location = "\\PortoZoca\\produto?accObs=Sim" + "&id=" + prd.produtoId + "&referencia=" + referencia + "&descricao=" + descricao;
        }
        function grvObs(prd) {
            var obs = document.getElementById("Obs").value;
            window.location = "\\PortoZoca\\produto?gravaObs=Sim" + "&id=" + prd.produtoId + "&obs=" + obs;
        }
        
    </script>

</body>
</html>
