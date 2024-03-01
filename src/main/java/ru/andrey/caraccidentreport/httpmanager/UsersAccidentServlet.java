package ru.andrey.caraccidentreport.httpmanager;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.andrey.caraccidentreport.dto.AuthorizationDataDTO;
import ru.andrey.caraccidentreport.dto.FullNameDTO;
import ru.andrey.caraccidentreport.dto.LimitedAccidentDataDTO;
import ru.andrey.caraccidentreport.service.LoginAccidentService;
import ru.andrey.caraccidentreport.service.NameAccidentService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UsersAccidentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = (String) req.getSession().getAttribute("login");

        List<LimitedAccidentDataDTO> ladDTOsList = new LoginAccidentService().getAccidentDataByLogin(login);

        String newJson = new Gson().toJson(ladDTOsList);

        PrintWriter printWriter = resp.getWriter();
        printWriter.write(newJson);

    }

}
