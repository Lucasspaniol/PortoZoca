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
            .container-center {
                height: 30px;
                display: flex;
                flex-direction: row;
                justify-content: space-between;
                align-content: center;
                align-items: center;
            }
            .botao {
                height: 30px;
                width: 80px;
            }
        </style>        
        <link rel="stylesheet" type="text/css" href="https://unpkg.com/papercss/dist/paper.min.css">
    </head>
    <body>
        <h1>Localizações</h1>
        <div>
            <h2>Estrutura</h2>
            <input type="text" value="${Localizacao}" name="Localizacao" id="Localizacao">
            <input  name="submit" type="button" value="Ok" onclick=" ">
        </div>
        <table style="width: 100%; text-align: center; border-collapse: collapse;">
            <caption style="background-color: #06C; color: white;">
                Divisões da localização
            </caption>
            <tr>
                <th>Divisão</th>
                <th>Exc</th>
            </tr>
            <c:forEach items="${divisoesLocalizacao}" var="leitura" varStatus="idl">
                <tr style="line-height: 130%; background-color: ${leitura.cor}; border-bottom: 1px solid lightgray;">
                    <td>${divisao.divsao}</td>
                    <td><input type="button" value="X" id="excluir" onclick="botaoExcluir('${divisao.divsao}')"></td>
                </tr>
            </c:forEach>
        </table>
        <input type="button" value="Adicionar Divisão" id="add" onclick="botaoAdd()">
    </body>
</html>
