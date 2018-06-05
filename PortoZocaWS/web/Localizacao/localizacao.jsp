<%-- 
    Document   : localizacao
    Created on : 04/06/2018, 23:26:18
    Author     : Spaniol
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Localizações</title>
        <script type="text/javascript">
            // Define a URL ao clicar no botão "Localização"
            function botaoAdd() {
                window.location = "?botaoLocaliz=Sim";
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
        <h2 align="center"> Localizações</h2>
        <h4>Estrutura</h4>
        <input type="text" value="${Localizacao}" name="Localizacao" id="Localizacao" size="80">
        <input name="submit" type="button" value="Ok" onclick=" ">
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
                    <td><input type="button" value="X" id="excluir" onclick="botaoExcluir('${d.divsao}')"></td>
                </tr>
            </c:forEach>
        </table>
        <input type="button" value="Adicionar Divisão" id="add" onclick="botaoAdd()">
    </body>
</html>
