package ru.andrey.caraccidentreport.httpmanager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.andrey.caraccidentreport.dto.AuthorizationDataDTO;
import ru.andrey.caraccidentreport.service.LoginService;

import java.io.IOException;
import java.io.PrintWriter;

public class LoginViaDBServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        LoginService loginService = new LoginService();

        Boolean exists = loginService.checkIfAuthorized(login, password);

        if (exists) {
            req.getSession().setAttribute("isAuthorised", "true");
            req.getSession().setAttribute("login", login);
            PrintWriter pw = resp.getWriter();
            pw.write("Login sucsessful");
        } else {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter pw = resp.getWriter();
            pw.write("Invalid user data");
        }

    }
}
