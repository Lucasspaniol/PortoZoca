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
import br.portozoca.ws.entidade.Localizacao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Spaniol
 */
@WebServlet(name = "/localizacao")
public class LocalizacaoServelet extends HttpServlet {

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        // Le as localizações
        try (Conexao conn = ConexaoFactory.query()) {
            DataAccessObject<Localizacao> dao = DAOFactory.create(Localizacao.class, conn);
            List<Localizacao> lista = dao.select();
            // Põe na lista
            session.setAttribute("divisoesLocalizacao", lista);
        } catch (DBException e) {
            // Se der exception, põe nos atributos
            session.setAttribute("error", "Não foi possível encontrar a localização.");
            session.setAttribute("exception", e);
        }
        // Redireciona para o test.jsp
        resp.sendRedirect("localizacao/localizacao.jsp");
    }

}
