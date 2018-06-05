/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.servlet;

import br.portozoca.ws.dao.DAOFactory;
import br.portozoca.ws.dao.DataAccessObject;
import br.portozoca.ws.database.Conexao;
import br.portozoca.ws.database.ConexaoFactory;
import br.portozoca.ws.database.DBException;
import br.portozoca.ws.entidade.Produto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author joaovperin
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        // Lê os produtos
        try (Conexao conn = ConexaoFactory.query()) {
            DataAccessObject<Produto> dao = DAOFactory.create(Produto.class, conn);
            List<Produto> lista = dao.select();
            // Põe na lista
            session.setAttribute("produtos", lista);
        } catch (DBException e) {
            // Se der exception, põe nos atributos
            session.setAttribute("error", "Não foi possível ler os produtos.");
            session.setAttribute("exception", e);
        }
        // Redireciona para o test.jsp
        resp.sendRedirect("test/index.jsp");
    }

}
