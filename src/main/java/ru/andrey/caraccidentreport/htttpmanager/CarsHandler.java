package ru.andrey.caraccidentreport.htttpmanager;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.andrey.caraccidentreport.dto.CarDTO;
import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.service.ServiceForCars;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CarsHandler implements HttpHandler {

    @Override
    public void handle (HttpExchange exchange) throws IOException {
//        if (exchange.getRequestMethod().equals("GET")) {
//
//            try (OutputStream ops = exchange.getResponseBody()) {
//
//                ServiceForCars sfc = new ServiceForCars();
//                List<CarDTO> allCarDTOs = sfc.getAllCars();
//
//                exchange.sendResponseHeaders(200, 0);
//                String allCarsJson = new Gson().toJson(allCarDTOs);
//                ops.write(allCarsJson.getBytes(StandardCharsets.UTF_8));
//
//            } catch (DataAccessException e) {
//                exchange.sendResponseHeaders(500, 0);
//                try (OutputStream ops = exchange.getResponseBody()) {
//                    ops.write("Server Error".getBytes(StandardCharsets.UTF_8));
//                }
//            }
//
//        } else if (exchange.getRequestMethod().equals("POST")) {
//
//            try (InputStream is = exchange.getRequestBody(); OutputStream ops = exchange.getResponseBody()) {
//                byte[] bytes = is.readAllBytes();
//                String carToAdd = new String(bytes,StandardCharsets.UTF_8);
//                CarDTO carDto = new Gson().fromJson(carToAdd, CarDTO.class);
//
////                ServiceForCars sfc = new ServiceForCars();
////                sfc.addACar(carDto);
//
//                exchange.sendResponseHeaders(200, 0);
//                ops.write("Car was added".getBytes(StandardCharsets.UTF_8));
//
//            } catch (DataAccessException e) {
//                exchange.sendResponseHeaders(500, 0);
//                try (OutputStream ops = exchange.getResponseBody()) {
//                    ops.write("Server Error".getBytes(StandardCharsets.UTF_8));
//                }
//            }
//        }
    }
}
