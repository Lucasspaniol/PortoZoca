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
@WebServlet("/localizacao")
public class LocalizacaoServelet extends HttpServlet {
    
    private Localizacao locAtual;
    
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
        //Retorna parametros
        int supId;
        String estrutura = req.getParameter("loc");
        String addDiv = req.getParameter("addDiv");
        String Div = req.getParameter("Div");
        
        if ("Sim".equals(addDiv)) {
            
            
            
            //locAtual.getEstrutura()
            
            
            
            
            
            
        }
        
        
        // Le as localizações
        try (Conexao conn = ConexaoFactory.query()) {
            //Lê a localização
            List<Localizacao> lista;
            DataAccessObject<Localizacao> dao = DAOFactory.create(Localizacao.class, conn);
            supId = 0;
            if (estrutura != null && !estrutura.equals("")) {            
                String[] divisao = estrutura.split("\\.");
                // Le as localizações
                Localizacao x;
                for (int i=0; i<= divisao.length - 1; i++){
                    if (supId == 0) {
                        x = dao.selectOne("WHERE LocalizacaoSuperiorId IS NULL AND Divisao = '" + divisao[i] + "'");
                    } else {
                        x = dao.selectOne("WHERE LocalizacaoSuperiorId = '" + supId + "' AND Divisao = '" + divisao[i] + "'");
                    }
                    if (x != null) {
                        supId = x.getLocalizacaoid();   
                    }
                }
            }
            if (supId != 0){
                locAtual = dao.selectOne("WHERE LocalizacaoId = " + supId);
                lista = dao.select("WHERE LocalizacaoSuperiorId = " + supId);
            } else {
                locAtual = null;
                lista = dao.select("WHERE LocalizacaoSuperiorId IS NULL");
            }
            //Carrega atributos
            if (lista.isEmpty()) {
                session.setAttribute("error", "Não encontou nenhum localização");
            } else {
                session.setAttribute("error", " ");
            }
            session.setAttribute("Localizacoes", lista);
            if (locAtual != null){
                session.setAttribute("Localizacao", estrutura);
            }
        } catch (DBException e) {
            // Se der exception, põe nos atributos
            session.setAttribute("error", "Erro ao ler localizações");
            session.setAttribute("exception", e);
        }
        // Redireciona para o test.jsp
        resp.sendRedirect("Localizacao/localizacao.jsp");
    }
    
}
