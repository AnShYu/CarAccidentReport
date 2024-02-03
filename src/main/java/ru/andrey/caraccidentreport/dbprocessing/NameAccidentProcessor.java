package ru.andrey.caraccidentreport.dbprocessing;

import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.model.Address;
import ru.andrey.caraccidentreport.model.LimitedAccidentData;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NameAccidentProcessor {

    public List<LimitedAccidentData> getAccidentData(String surname, String name, String fathersName) {

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/andrey",
                    "andrey", "andrey");

            String getAccidentDataSQLQuery = "select a.province, a.city, a.district, a.street, a.bld_number, a.driver_claimed_guilt, a.accident_time from car_accident_report.accident a " +
                    "where id in (select ap.accident_id from car_accident_report.accident_participant ap " +
                    "where ap.driver_id = (select p.id from car_accident_report.persons p " +
                    "where p.surname=? and p.first_name = ? and p.fathers_name = ?));";

            pstmt = connection.prepareStatement(getAccidentDataSQLQuery);

            pstmt.setString(1, surname);
            pstmt.setString(2, name);
            pstmt.setString(3, fathersName);

            ResultSet rs = pstmt.executeQuery();
            String province;
            String city;
            String district;
            String street;
            String building;
            Integer guilt;
            Timestamp time;

            List<LimitedAccidentData> resultSetList = new ArrayList<>();
            while(rs.next()) {
                province = rs.getString("province");
                city = rs.getString("city");
                district = rs.getString("district");
                street = rs.getString("street");
                building = rs.getString("bld_number");
                guilt = rs.getInt("driver_claimed_guilt");
                time = rs.getTimestamp("accident_time");
                Address address = new Address(province, city, district, street, building);
                LimitedAccidentData lad = new LimitedAccidentData(address, guilt, time);
                resultSetList.add(lad);
            }
            return resultSetList;

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
