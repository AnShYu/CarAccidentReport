package ru.andrey.caraccidentreport.htttpmanager;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.andrey.caraccidentreport.dto.ReportTextDTO;
import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.service.AllReportServices;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ReportHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if (exchange.getRequestMethod().equals("GET")) {
            // TODO
            throw new RuntimeException("Method is not implemented");
        } else if (exchange.getRequestMethod().equals("POST")) {
            try (InputStream is = exchange.getRequestBody(); OutputStream ops = exchange.getResponseBody()) {
                byte[] data = is.readAllBytes();
                String reportInJSON = new String(data, StandardCharsets.UTF_8);
                ReportTextDTO reportDTO = new Gson().fromJson(reportInJSON, ReportTextDTO.class);

                AllReportServices rts = new AllReportServices();

                rts.parceAndAddToDB(reportDTO);

                exchange.sendResponseHeaders(200,0);
                ops.write("Data added".getBytes(StandardCharsets.UTF_8));

            } catch (DataAccessException e) {
                exchange.sendResponseHeaders(500, 0);
                try (OutputStream ops = exchange.getResponseBody()) {
                    ops.write("Server Error".getBytes(StandardCharsets.UTF_8));
                }
            }
        }
    }
}
