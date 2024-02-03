package ru.andrey.caraccidentreport.dbprocessing;

import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.model.CarData;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarsProcessor {

    public long addCar (CarData car, long personId) throws DataAccessException {

        String carBrand = car.getCarBrand();
        String carModel = car.getCarModel();
        String carPlate = car.getCarPlate();
        int ownerId = (int) personId;

        Connection connection = null;
        PreparedStatement pstmt = null;

        long id;

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/andrey",
                    "andrey", "andrey");

            String insert = "insert into car_accident_report.cars (owner_id, carplate, " +
                    "brand, model) values (?, ?, ?, ?)";
            pstmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, ownerId);
            pstmt.setString(2, carPlate);
            pstmt.setString(3, carBrand);
            pstmt.setString(4, carModel);

            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getLong(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

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

        return id;
    }


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

    public long checkIfCarIsInDB(CarData car) throws DataAccessException {

        String carPlate = car.getCarPlate();

        Connection connection = null;
        PreparedStatement pstmt = null;

        long id;

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/andrey",
                    "andrey", "andrey");

            String carIDRequest = "select id from car_accident_report.cars c where c.carplate=?";
            pstmt = connection.prepareStatement(carIDRequest);

            pstmt.setString(1, carPlate);



            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            else {
                id = -1;
            }

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
        return id;
    }

}
