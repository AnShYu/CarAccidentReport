package ru.andrey.caraccidentreport.dbprocessing;

import com.google.gson.Gson;
import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.htttpmanager.Car;
import ru.andrey.caraccidentreport.htttpmanager.CarDTO;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarsProcessor {

    public List<Car> getAllCars () throws DataAccessException {

        Connection connection = null;
        Statement stmt = null;
        ResultSet resultSet = null;

        try {
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

            return allCars;

        } catch (ClassNotFoundException e) {
            throw new DataAccessException("Postgres Driver Error", e);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Exception", e);
        } finally {
            try {
                resultSet.close();
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("RunTimeException", e);
            }
        }
    }

    public void addACar (CarDTO carDto) throws DataAccessException {

        String carBrand = carDto.getCarBrand();
        String carModel = carDto.getCarModel();
        String carPlate = carDto.getCarPlate();

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/andrey",
                    "andrey", "andrey");

            pstmt = connection.prepareStatement("insert into car_accident_report.cars (brand, model, carplate) values " +
                    "(?, ?, ?)");

            pstmt.setString(1, carBrand);
            pstmt.setString(2, carModel);
            pstmt.setString(3, carPlate);

            pstmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new DataAccessException("Postgres Driver Error", e);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Exception", e);
        } finally {
            try {
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("RunTimeException", e);
            }
        }
    }

}
