package ru.andrey.caraccidentreport.httpmanager;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.andrey.caraccidentreport.dto.FullNameDTO;
import ru.andrey.caraccidentreport.dto.LimitedAccidentDataDTO;
import ru.andrey.caraccidentreport.service.NameAccidentService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class PersonsAccidentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String alternativeFullNameInput = this.getServletConfig().getInitParameter("alternativeFullNameInput");
        String fullName;
        if(alternativeFullNameInput.equals("true")) {
            // пользователь передает ФИО в качестве трех отдельных параметров фамилия, имя, отчество
            String lastName = req.getParameter("lastname");
            String firstName = req.getParameter("firstname");
            String fathersName = req.getParameter("fathersname");
            fullName = lastName + "_" + firstName + "_" + fathersName;
        } else {
            // пользователь передает ФИО в качестве параметра в формате Фамилия_Имя_Отчетство
            fullName = req.getParameter("fullname");
        }

        FullNameDTO fnDTO = new FullNameDTO(fullName);

        PrintWriter printWriter = resp.getWriter();
        List<LimitedAccidentDataDTO> ladDTOsList = new NameAccidentService().getAccidentDetails(fnDTO);

        String newJson = new Gson().toJson(ladDTOsList); // нужно ли здесь сначала переводить всё из DTO?
        printWriter.write(newJson);

    }
}
