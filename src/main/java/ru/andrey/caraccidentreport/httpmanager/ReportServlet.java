package ru.andrey.caraccidentreport.httpmanager;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.andrey.caraccidentreport.dto.ReportTextDTO;
import ru.andrey.caraccidentreport.service.AllReportServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class ReportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String reportInJSON = reader.lines().collect(Collectors.joining());

        ReportTextDTO reportDTO = new Gson().fromJson(reportInJSON, ReportTextDTO.class);

        AllReportServices rts = new AllReportServices();
        rts.parceAndAddToDB(reportDTO);

        PrintWriter printWriter = resp.getWriter();
        printWriter.write("Data added");
    }
}
