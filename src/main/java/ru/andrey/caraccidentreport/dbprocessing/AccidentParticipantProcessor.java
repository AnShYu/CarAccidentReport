package ru.andrey.caraccidentreport.dbprocessing;

import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.model.AccidentParticipantData;
import java.sql.*;

public class AccidentParticipantProcessor {

    public void addAccidentParticipant (AccidentParticipantData apd) throws DataAccessException {

        String OSAGO = apd.getOSAGO();
        int accidentID = apd.getAccidentID();
        int driverID = apd.getDriverID();
        int carID = apd.getCarID();

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/andrey",
                    "andrey", "andrey");

            String insert = "insert into car_accident_report.accident_participant (accident_id, driver_id, " +
                    "car_id, osago_number) values (?, ?, ?, ?)";
            pstmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, accidentID);
            pstmt.setInt(2, driverID);
            pstmt.setInt(3, carID);
            pstmt.setString(4, OSAGO);

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
