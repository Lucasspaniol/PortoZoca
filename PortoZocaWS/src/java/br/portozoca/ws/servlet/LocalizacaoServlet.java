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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Spaniol
 */
@WebServlet("/localizacao")
public class LocalizacaoServlet extends HttpServlet {

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

        //Retorna parametros
        int supId;
        String estrutura = req.getParameter("loc");
        if (estrutura == null || estrutura.isEmpty()) {
            locAtual = null;
        }
        String addDiv = req.getParameter("addDiv");
        String eliDiv = req.getParameter("eliDiv");
        String entrar = req.getParameter("entrar");
        String voltar = req.getParameter("voltar");
        String div = req.getParameter("Div");
        String accObs = req.getParameter("accObs");
        String regravaObs = req.getParameter("regravaObs");
        String obs = req.getParameter("obs");

        // Le as localizações
        try (Conexao conn = ConexaoFactory.transaction()) {
            DataAccessObject<Localizacao> dao = DAOFactory.create(Localizacao.class, conn);

            // Se deve adicionar uma divisão
            if (addDiv != null && addDiv.equals("Sim")) {
                Localizacao l = new Localizacao();
                l.setDivision(div);
                l.setSup(locAtual);
                dao.insert(l);
                conn.commit();
                req.setAttribute("gravou_ok", "true");
            }

            // Se deve eliminar uma divisão
            if (eliDiv != null && eliDiv.equals("Sim")) {
                Localizacao l = new Localizacao();
                l.setLocalizacaoid(Integer.parseInt(req.getParameter("id")));
                dao.delete(l);
                conn.commit();
                req.setAttribute("deletou_ok", "true");
            }

             if (regravaObs != null && regravaObs.equals("Sim")){
                Localizacao l = dao.selectOne("WHERE LocalizacaoId = " + Integer.parseInt(req.getParameter("id")));
                l.setObservacao(obs);
                dao.update(l);
                conn.commit();
            }

            // Se deve entrar na estrutura
            if (entrar != null && entrar.equals("Sim")) {
                Localizacao l = dao.selectOne("WHERE LocalizacaoId = " + Integer.parseInt(req.getParameter("id")));
                estrutura = l.getEstrutura();
            }

            // Se deve entrar na estrutura anterior
            if (voltar != null && voltar.equals("Sim")) {
                if (locAtual != null){
                   estrutura = locAtual.getEstrutura();
                }
            }
            // Se deve aceitar a observação da divisão
            if (accObs != null && accObs.equals("Sim")) {
                Localizacao l = dao.selectOne("WHERE LocalizacaoId = " + Integer.parseInt(req.getParameter("id")));
                req.setAttribute("estruturaId", l.getEstrutura());
                req.setAttribute("aceitaObs", true);
                req.setAttribute("IdDiv", l.getLocalizacaoid());
                req.setAttribute("ObsDiv", l.getObservacao());
            }
            //Lê a localização
            List<Localizacao> lista = new ArrayList<>();
            supId = 0;
            if (estrutura != null && !estrutura.equals("")) {
                String[] divisao = estrutura.split("\\.");
                // Le as localizações
                Localizacao x;
                for (int i = 0; i <= divisao.length - 1; i++) {
                    if (supId == 0) {
                        x = dao.selectOne("WHERE LocalizacaoSuperiorId IS NULL AND Divisao = '" + divisao[i] + "'");
                    } else {
                        x = dao.selectOne("WHERE LocalizacaoSuperiorId = '" + supId + "' AND Divisao = '" + divisao[i] + "'");
                    }
                    if (x != null) {
                        supId = x.getLocalizacaoid();
                    } else {
                        req.setAttribute("error", "Estrutura não encontrada :/");
                        break;
                    }
                }
            }
            if (supId != 0) {
                locAtual = dao.selectOne("WHERE LocalizacaoId = " + supId);
                lista = dao.select("WHERE LocalizacaoSuperiorId = " + supId);
            } else {
                if (locAtual != null && estrutura != null && !estrutura.isEmpty()) {
                    lista.clear();
                } else {
                    lista = dao.select("WHERE LocalizacaoSuperiorId IS NULL");
                }
            }
            //Carrega atributos
            if (lista.isEmpty()) {
                req.setAttribute("msg", "Estrutura não possui divisões");
            }
            req.setAttribute("Localizacoes", lista);
            req.setAttribute("Localizacao", estrutura);
            if (locAtual != null) {
                req.setAttribute("exbVoltar", true);
            } else {
                req.setAttribute("exbVoltar", false);
            }
        } catch (DBException e) {
            // Se der exception, põe nos atributos
            req.setAttribute("error", "Falha no banco de dados :/");
            req.setAttribute("exception", e);
            locAtual = null;
        }
        // Redireciona para o localizacao.jsp
        RequestDispatcher rd = req.getServletContext().getRequestDispatcher("/localizacao/index.jsp");
        rd.forward(req, resp);
    }

}
