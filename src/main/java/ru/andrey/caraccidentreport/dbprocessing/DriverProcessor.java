package ru.andrey.caraccidentreport.dbprocessing;

import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.model.DriverData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DriverProcessor {

    public void addDriver (DriverData driver) throws DataAccessException {

        String firstName = driver.getFirstName();
        String fathersName = driver.getFathersName();
        String lastName = driver.getLastName();
        String passport = driver.getPassport();
        String OSAGONumber = driver.getOSAGONumber();

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/andrey",
                    "andrey", "andrey");

            pstmt = connection.prepareStatement("insert into car_accident_report.persons (surname, first_name, " +
                    "fathers_name, passport_number) values (?, ?, ?, ?)");

            pstmt.setString(1, lastName);
            pstmt.setString(2, firstName);
            pstmt.setString(3, fathersName);
            pstmt.setString(4, passport);

            pstmt.executeUpdate();

            pstmt = connection.prepareStatement("insert into car_accident_report.accident_participant (driver_id, osago_number) " +
                    "values ((select id from car_accident_report.persons where passport_number = ?), ?)");
            pstmt.setString(1, passport);
            pstmt.setString(2, OSAGONumber);

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
