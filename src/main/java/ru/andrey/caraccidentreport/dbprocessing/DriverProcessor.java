package ru.andrey.caraccidentreport.dbprocessing;

import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.model.DriverData;

import java.sql.*;
import java.util.Objects;

public class DriverProcessor {

    public long addDriver (DriverData driver) throws DataAccessException {

        String firstName = driver.getFirstName();
        String fathersName = driver.getFathersName();
        String lastName = driver.getLastName();
        String passport = driver.getPassport();
        String OSAGONumber = driver.getOSAGONumber();

        Connection connection = null;
        PreparedStatement pstmt = null;

        long id;

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/andrey",
                    "andrey", "andrey");

            String insert = "insert into car_accident_report.persons (surname, first_name, " +
                    "fathers_name, passport_number) values (?, ?, ?, ?)";
            pstmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, lastName);
            pstmt.setString(2, firstName);
            pstmt.setString(3, fathersName);
            pstmt.setString(4, passport);

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
