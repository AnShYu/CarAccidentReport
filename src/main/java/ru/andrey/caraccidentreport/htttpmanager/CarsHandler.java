package ru.andrey.caraccidentreport.htttpmanager;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.andrey.caraccidentreport.dbprocessing.CarsProcessor;
import ru.andrey.caraccidentreport.exceptions.DataAccessException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarsHandler implements HttpHandler {

    @Override
    public void handle (HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("GET")) {

            Connection connection = null;
            Statement stmt = null;
            ResultSet resultSet = null;

            try (OutputStream ops = exchange.getResponseBody()) {



                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/andrey",
                        "andrey", "andrey");
                stmt = connection.createStatement();

                resultSet = stmt.executeQuery("select carplate, brand, model from car_accident_report.cars");
                List<Car> allCars = new ArrayList<>();
                while (resultSet.next()) {
                    Car car = new Car(resultSet.getString("brand"), resultSet.getString("model"),
                            resultSet.getString("carplate"));
                    allCars.add(car);
                }


                List<CarDTO> allCarDTOs = new ArrayList<>();
                for (Car car: allCars) {
                    CarDTO carDTO = new CarDTO();
                    carDTO.setCarBrand(car.getCarBrand());
                    carDTO.setCarModel(car.getCarModel());
                    carDTO.setCarPlate(car.getCarPlate());
                    allCarDTOs.add(carDTO);
                }


                exchange.sendResponseHeaders(200, 0);
                String allCarsJson = new Gson().toJson(allCarDTOs);
                ops.write(allCarsJson.getBytes(StandardCharsets.UTF_8));


            } catch (ClassNotFoundException e) {
                exchange.sendResponseHeaders(500, 0);
                try (OutputStream ops = exchange.getResponseBody()) {
                    ops.write("Server Error CNF".getBytes(StandardCharsets.UTF_8));
                }
            } catch (SQLException e) {
                exchange.sendResponseHeaders(500, 0);
                try (OutputStream ops = exchange.getResponseBody()) {
                    ops.write("Server Error SQL".getBytes(StandardCharsets.UTF_8));
                }
            } finally {
                try {
                    resultSet.close();
                    stmt.close();
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Runtime Exception");
                }

            }
        } else if (exchange.getRequestMethod().equals("POST")) {

            try (InputStream is = exchange.getRequestBody(); OutputStream ops = exchange.getResponseBody()) {
                byte[] bytes = is.readAllBytes();
                String carToAdd = new String(bytes,StandardCharsets.UTF_8);
                CarDTO carDto = new Gson().fromJson(carToAdd, CarDTO.class); // обязательно ли здесь использовать DTO?

                CarsProcessor cp = new CarsProcessor();
                cp.addACar(carDto);

                exchange.sendResponseHeaders(200, 0);
                ops.write("Car was added".getBytes(StandardCharsets.UTF_8));

            } catch (DataAccessException e) {
                exchange.sendResponseHeaders(500, 0);
                try (OutputStream ops = exchange.getResponseBody()) {
                    ops.write("Server Error".getBytes(StandardCharsets.UTF_8));
                }
            }
        }
    }
}
