package br.portozoca.ws.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.portozoca.ws.database.Conexao;
import br.portozoca.ws.database.ConexaoFactory;
import br.portozoca.ws.database.DBException;
import br.portozoca.ws.entidade.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Produtos Controller
 *
 * @author joaovperin
 */
@WebServlet("/produto")
public class Produtos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // response.sendRedirect("/PortoZoca/batata/index.jsp");
        // Says to browser if have to render as an HTML
        response.setContentType("text/html;charset=UTF-8");
        // Starts writing to the response stream
        try (PrintWriter out = response.getWriter()) {
            out.println("<h1>Produtos:</h1>");
            getProdutos().forEach((Produto p) -> {
                out.printf("<h3>Produto %d (%s) - %s </h3>", p.getProdutoId(), p.getReferencia(), p.getDescricao());
                if (p.getObservacao() != null) {
                    out.printf("<p>Obs: %s </p>", p.getObservacao());
                }
            });
            out.println("<br/><br/><br/><h3>Fim</h3>.");
            out.flush();
        }
    }

    /**
     * Reads a list of products from the database and return to the page
     *
     * @return List
     */
    public List<Produto> getProdutos() {
        // Cria uma lista de produtos
        List<Produto> list = new ArrayList<>();
        // Cria uma conexão com o banco de dados para consulta
        try (Connection conn = getConnection()) {
            // Cria um Statement e executa uma consulta no banco
            ResultSet rs = conn.createStatement().executeQuery("SELECT ProdutoId, Referencia, Descricao, Observacao FROM Produto");
            // Percorre os resultados
            while (rs.next()) {
                // Cria um novo produto e vai setando os parâmetros conforme veio do banco (em ordem)
                Produto p = new Produto();
                p.setProdutoId(rs.getInt(1));
                p.setReferencia(rs.getString(2));
                p.setDescricao(rs.getString(3));
                p.setObservacao(rs.getString(4));
                // Adiciona o produto na lista
                list.add(p);
            }
            // Se der exception printa na tela
        } catch ( SQLException ex) {
            System.out.println("*** Falha ao carregar os produtos.");
        }
        return list;
    }
    // Cria uma conexão com o banco de dados
    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://blablabla.bancodedados.com:3306");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
