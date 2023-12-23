package ru.andrey.caraccidentreport.dbprocessing;

import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.model.LimitedAccidentData;

import java.sql.*;

public class FullNameProcessor {

    public LimitedAccidentData getAccidentData(String surname, String name, String fathersName) {

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/andrey",
                    "andrey", "andrey");

            String getDriverID = "select a.province, a.city, a.district, a.street, a.bld_number, a.driver_claimed_guilt, a.accident_time from car_accident_report.accident a " +
                    "where id = (select ap.accident_id from car_accident_report.accident_participant ap " +
                    "where ap.driver_id = (select p.id from car_accident_report.persons p " +
                    "where p.surname=? and p.first_name = ? and p.fathers_name = ?));";

            pstmt = connection.prepareStatement(getDriverID);

            pstmt.setString(1, surname);
            pstmt.setString(2, name);
            pstmt.setString(3, fathersName);

            ResultSet rs = pstmt.executeQuery();
            String province = null;
            String city = null;
            String district = null;
            String street = null;
            String building = null;
            Integer guilt = null;
            Timestamp time = null;

            if(rs.next()) {
                province = rs.getString("province");
                city = rs.getString("city");
                district = rs.getString("district");
                street = rs.getString("street");
                building = rs.getString("bld_number");
                guilt = rs.getInt("driver_claimed_guilt");
                time = rs.getTimestamp("accident_time");
            }

            String address = makeAddress(province, city, district, street, building);
            LimitedAccidentData lad = makeLad(address, guilt, time);

            return lad;

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

    private LimitedAccidentData makeLad (String address, int guilt, Timestamp time) {
        LimitedAccidentData lad = new LimitedAccidentData();
        lad.setAddress(address);
        if (guilt != 1) {
            lad.setGuilt("Вину не признал");
        }  else {
            lad.setGuilt("Признал вину");
        }
        if (!time.equals(null)) {
            lad.setDate(time.toString());
        }
        return lad;
    }

    private String makeAddress (String province, String city, String district, String street, String building) {
        StringBuilder stringBuilder = new StringBuilder();
        if (province != null) {
            stringBuilder.append(province);
            stringBuilder.append(", ");
        }
        if (city != null) {
            stringBuilder.append(city);
            stringBuilder.append(", ");
        }
        if (district != null) {
            stringBuilder.append(district);
            stringBuilder.append(", ");
        }
        if (street != null) {
            stringBuilder.append(street);
            stringBuilder.append(", ");
        }
        if (building != null) {
            stringBuilder.append(building);
            stringBuilder.append(", ");
        }
        if (stringBuilder.length() != 0 ) {
            return stringBuilder.toString();
        } else {
            return null;
        }

    }

}
