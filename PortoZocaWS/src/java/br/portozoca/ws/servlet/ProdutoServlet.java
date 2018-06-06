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
        try (Conexao conn = ConexaoFactory.transaction()) {
            DataAccessObject<Produto> dao = DAOFactory.create(Produto.class, conn);
            // Check's if user include product
            if (req.getParameter("botaoAdd") != null &&
                    req.getParameter("botaoAdd").equalsIgnoreCase("sim")) {
                Produto p = new Produto();
                p.setReferencia(req.getParameter("referencia"));
                p.setDescricao(req.getParameter("descricao"));
                dao.insert(p);
                conn.commit();
                req.setAttribute("gravou_ok", "true");
            }
            // Check's if user exclude product
            if (req.getParameter("botaoExc") != null &&
                    req.getParameter("botaoExc").equalsIgnoreCase("sim")) {
                Produto p = new Produto();
                p.setProdutoId(Integer.parseInt(req.getParameter("id")));
                dao.delete(p);
                conn.commit();
                req.setAttribute("deletou_ok", "true");
            }
            // Consulta
            if (req.getParameter("botaoCon") != null) {
                System.out.println("oi to consultando");
                String pId = req.getParameter("id");
                Produto prd = dao.selectOne("WHERE ProdutoId = '" + pId + "'");
                req.setAttribute("prod", prd);
            }
            // Reads all the products
            List<Produto> lista = dao.select();
            req.setAttribute("Produtos", lista);
        } catch (DBException e) {
            // Se der exception, põe nos atributos
            req.setAttribute("error", "Não foi possível acessar tabela de produtos.");
            req.setAttribute("exception", e);
        }
        // Redireciona para o produto.jsp
        RequestDispatcher rd = req.getServletContext().getRequestDispatcher("/produto/produto.jsp");
        rd.forward(req, resp);
    }

}
