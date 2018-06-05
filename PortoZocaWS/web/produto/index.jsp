<%-- 
    Document   : produtos
    Created on : 06/06/2018, 23:26:18
    Author     : Jonas
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Produtos</title>
        <script type="text/javascript">
            // Define a URL ao clicar no botão "Produtos"
            function botaoAdd() {
                window.location = "?botaoProdut=Sim";
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
        <input type="text" value="${Referencia}" name="Localizacao" id="Referencia" size="80">
        <br>
        <div>Descrição</div>
        <input type="text" value="${Descricao}" name="Localizacao" id="Descricao" size="80">
        <input name="submit" type="button" value="Ok" onclick=" ">
    </body>
</html>
