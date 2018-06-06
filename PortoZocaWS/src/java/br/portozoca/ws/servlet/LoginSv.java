/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.servlet;

import br.portozoca.ws.entidade.Usuario;
import java.io.IOException;
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
@WebServlet("/Login")
public class LoginSv extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ses = req.getSession(true);
        Usuario obj = new Usuario();
        obj.setApelido((String) req.getParameter("user"));
        obj.setSenha((String) req.getParameter("pass"));
        ses.setAttribute("userlogado", obj);
        // Redirect to index
        resp.sendRedirect("index.jsp");
    }

}
