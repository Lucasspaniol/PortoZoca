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
        <div>Referência</div>
        <input type="text" value="" name="Localizacao" id="Referencia" size="80">
        <br>
        <div>Descrição</div>
        <input type="text" value="" name="Localizacao" id="Descricao" size="80">
        <input name="submit" type="button" value="Ok" onclick="botaoAdd()">
        
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
                    <td><input type="button" value="X" id="excluir" onclick="botaoExc(${p.produtoId})"></td>
                </tr>
            </c:forEach>
        </table>
        
    </body>
</html>
