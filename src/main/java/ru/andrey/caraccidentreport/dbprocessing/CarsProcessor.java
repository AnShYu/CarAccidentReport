package ru.andrey.caraccidentreport.dbprocessing;

import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.htttpmanager.CarData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarsProcessor {

    public List<CarData> getAllCars () throws DataAccessException {

        Connection connection = null;
        Statement stmt = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/andrey",
                    "andrey", "andrey");
            stmt = connection.createStatement();

            resultSet = stmt.executeQuery("select carplate, brand, model from car_accident_report.cars");
            List<CarData> allCars = new ArrayList<>();
            while (resultSet.next()) {
                CarData car = new CarData(resultSet.getString("brand"), resultSet.getString("model"),
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

    public void addACar (CarData car) throws DataAccessException {

        String carBrand = car.getCarBrand();
        String carModel = car.getCarModel();
        String carPlate = car.getCarPlate();

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
