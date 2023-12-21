package ru.andrey.caraccidentreport.httpmanager;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.andrey.caraccidentreport.dto.FullNameDTO;
import ru.andrey.caraccidentreport.service.NameAccidentService;

import java.io.IOException;
import java.io.PrintWriter;

public class PersonsAccidentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // пользователь передает ФИО в качестве параметра в формате Фамилия_Имя_Отчетство
        String fullName = req.getParameter("fullname");
        FullNameDTO fnDTO = new FullNameDTO(fullName);

        String ladString = new NameAccidentService().getAccidentDetails(fnDTO);
        String ladJson = new Gson().toJson(ladString);

        PrintWriter printWriter = resp.getWriter();
        printWriter.write(ladJson);

    }
}
