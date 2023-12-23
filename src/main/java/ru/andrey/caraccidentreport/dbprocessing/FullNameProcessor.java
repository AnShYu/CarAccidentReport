package ru.andrey.caraccidentreport.dbprocessing;

import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.model.LimitedAccidentData;

import java.sql.*;

public class FullNameProcessor {

    public LimitedAccidentData getAccidentData(String surname, String name, String fathersName) {
        LimitedAccidentData lad = new LimitedAccidentData();

        String accidentAddress = "TestAddress";
        String accidentDate = "TestDate";
        String accidentGuilt = "TestGuilt";

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/andrey",
                    "andrey", "andrey");

            String getDriverID = "select a.province, a.city, a.district, a.street, a.bld_number, a.driver_claimed_guilt, a.accident_time from accident a\n" +
                    "\twhere id = (select ap.accident_id from accident_participant ap \n" +
                    "\t\twhere ap.driver_id = (select p.id from persons p\n" +
                    "\t\t\twhere p.surname=? and p.first_name = ? and p.fathers_name = ?));";
            pstmt = connection.prepareStatement(getDriverID);

            pstmt.setString(1, surname);
            pstmt.setString(2, name);
            pstmt.setString(3, fathersName);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                int personId = rs.getInt("id");
            }
            //TODO
            String join = "select id from persons ";






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

        lad.setAddress(accidentAddress);
        lad.setDate(accidentDate);
        lad.setGuilt(accidentGuilt);

        return lad;
    }

}
