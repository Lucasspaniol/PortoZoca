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
import br.portozoca.ws.entidade.Lpn;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jonas
 */
@WebServlet("/lpn")
public class LpnServlet extends HttpServlet {

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Conexao conn = ConexaoFactory.transaction()) {
            DataAccessObject<Lpn> dao = DAOFactory.create(Lpn.class, conn);
            
            // Reads all the products
            List<Lpn> lista = dao.select();
            req.setAttribute("Lpns", lista);
        } catch (DBException e) {
            // Se der exception, põe nos atributos
            req.setAttribute("error", "Não foi possível acessar tabela de produtos.");
            req.setAttribute("exception", e);
        }
        // Redireciona para o produto.jsp
        RequestDispatcher rd = req.getServletContext().getRequestDispatcher("/lpn/index.jsp");
        rd.forward(req, resp);
    }

}
