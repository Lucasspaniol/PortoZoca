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

    private static final String ADMIN = "admin";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ses = req.getSession(true);
        String usr = req.getParameter("user");
        String pass = req.getParameter("pass");
        // Autentica
        Usuario user = LoginService.autentica(usr, pass);
        if (user != null) {
            ses.setAttribute("userlogado", user);
            resp.sendRedirect("index.jsp");
        } else {
            // Redirect to login
            String msgErro = "Você não é o Adalberto.";
            if (usr != null && pass != null && ADMIN.equalsIgnoreCase(usr) && ADMIN.equalsIgnoreCase(pass)) {
                msgErro = "ENGRAÇADINHO VOCÊ, FERA. Admin Admin é a mãe.";
            }
            req.setAttribute("error", msgErro);
            RequestDispatcher rd = req.getServletContext().getRequestDispatcher("/login/form.jsp");
            rd.forward(req, resp);
        }
    }

}
