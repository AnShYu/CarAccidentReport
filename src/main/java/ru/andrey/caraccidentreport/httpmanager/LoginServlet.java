package ru.andrey.caraccidentreport.httpmanager;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    Map<String, String> users = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        users.put("Alexandrov", "123");
        users.put("Gavrilov", "234");
        users.put("Glinka", "345");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (users.containsKey(login) && users.get(login).equals(password)) {
            req.getSession().setAttribute("isAuthorised", "true");
            req.getSession().setAttribute("login", login);
        } else {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter pw = resp.getWriter();
            pw.write("Invalid user data");
        }

    }
}
