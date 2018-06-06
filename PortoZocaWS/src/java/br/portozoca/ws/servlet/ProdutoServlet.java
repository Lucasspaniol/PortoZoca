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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jonas
 */
@WebServlet("/produto")
public class ProdutoServlet extends HttpServlet {

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("carai " + resp.toString());
        HttpSession session = req.getSession(true);
        // Check's if user include product
        if(req.getParameter("botaoProdut") != null && 
           req.getParameter("botaoProdut").equalsIgnoreCase("sim")){
            Produto p = new Produto();
            try (Conexao cn = ConexaoFactory.transaction()) {
                p.setProdutoId(0);
                p.setReferencia(req.getParameter("referencia"));
                p.setDescricao(req.getParameter("descricao"));
                DataAccessObject<Produto> dao = DAOFactory.create(Produto.class, cn);
                dao.insert(p);
                cn.commit();
                session.setAttribute("gravou_ok", "Gravou o produto com sucesso.");
            } catch (DBException e) {
            // Se der exception, põe nos atributos
                session.setAttribute("error", "Não foi possível incluir o produto.");
                session.setAttribute("exception", e);
            }
        }
        // Le os produtos
        try (Conexao conn = ConexaoFactory.query()) {
            DataAccessObject<Produto> dao = DAOFactory.create(Produto.class, conn);
            List<Produto> lista = dao.select();
            // Põe na lista
            session.setAttribute("Produtos", lista);
        } catch (DBException e) {
            // Se der exception, põe nos atributos
            session.setAttribute("error", "Não foi possível encontrar o produto.");
            session.setAttribute("exception", e);
        }
        // Redireciona para o produto.jsp
        resp.sendRedirect("produto/produto.jsp");
    }
    
    

}
