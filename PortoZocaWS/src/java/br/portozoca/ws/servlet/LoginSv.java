/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.servlet;

import br.portozoca.ws.entidade.Usuario;
import br.portozoca.ws.service.LoginService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
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

        Usuario usr = LoginService.autentica(req.getParameter("user"), req.getParameter("pass"));
        if (usr != null) {
            ses.setAttribute("userlogado", usr);
            resp.sendRedirect("index.jsp");
        } else {
            // Redirect to login
            req.setAttribute("error", "Você não é o Adalberto.");
            RequestDispatcher rd = req.getServletContext().getRequestDispatcher("/login/form.jsp");
            rd.forward(req, resp);
        }
    }

}
