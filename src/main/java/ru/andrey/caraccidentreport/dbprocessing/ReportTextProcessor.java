package ru.andrey.caraccidentreport.dbprocessing;

import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.model.ReportText;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReportTextProcessor {

    public void addReportText (ReportText rt, long accidentId) throws DataAccessException {

        String reportText = rt.getReportText();
        int aID = (int) accidentId;

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/andrey",
                    "andrey", "andrey");

            pstmt = connection.prepareStatement("insert into car_accident_report.initial_text (accident_text, accident_id) " +
                    "values (?, ?)");

            pstmt.setString(1, reportText);
            pstmt.setInt(2, aID);

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
