package ru.andrey.caraccidentreport.dbprocessing;

import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.model.AccidentCircumstances;

import java.sql.*;

public class AccidentProcessor {

    public long addAccident(AccidentCircumstances accident) throws DataAccessException {

        String city = accident.getCity();
        String street = accident.getStreet();
        String building = accident.getBuilding();
        int guilt;
        if (accident.isDriverClaimedGuilt()) {
            guilt = 1;
        } else {
            guilt = 0;
        }
        Timestamp dateAndTime = accident.getDateAndTime();

        Connection connection = null;
        PreparedStatement pstmt = null;

        long id;

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/andrey",
                    "andrey", "andrey");
            String insert = "insert into car_accident_report.accident (city, street, " +
                    "bld_number, driver_claimed_guilt, accident_time) values (?, ?, ?, ?, ?)";

            pstmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, city);
            pstmt.setString(2, street);
            pstmt.setString(3, building);
            pstmt.setInt(4, guilt); //driver_claimed_guilt, Неправильно добавляет guilt. Нужно вернуть в sql запрос + ? (строка 34) + в строке 42 номер поменять на 5
            pstmt.setTimestamp(5, dateAndTime); // TimeInNunoseconds неправильно создает Timestamp. Вместо 2023-02 указывает 3923-03

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


}
