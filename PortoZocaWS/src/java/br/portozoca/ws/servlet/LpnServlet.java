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
import br.portozoca.ws.entidade.Lpn;
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
            DataAccessObject<Lpn> daoLpn = DAOFactory.create(Lpn.class, conn);
            DataAccessObject<Produto> daoProduto = DAOFactory.create(Produto.class, conn);
            DataAccessObject<Localizacao> daoLocalizacao = DAOFactory.create(Localizacao.class, conn);
            // Check's if user include product
            if (req.getParameter("botaoAdd") != null) {
                Lpn lpn = new Lpn();
                lpn.setProduto(daoProduto.selectOne(" WHERE Referencia ='" + req.getParameter("referencia") + "'"));
                lpn.setLocalizacao(daoLpn.loadLocalizacao(req.getParameter("localizacao")));
                lpn.setQuantidade(1f);
                daoLpn.insert(lpn);
                conn.commit();
                req.setAttribute("gravou_ok", "true");
            }
            
            // Reads all the products
            List<Lpn> lista = daoLpn.select();
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
