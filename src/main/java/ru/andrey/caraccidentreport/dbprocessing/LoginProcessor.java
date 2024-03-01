package ru.andrey.caraccidentreport.dbprocessing;

import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.model.AuthorizationData;

import java.sql.*;

public class LoginProcessor {

    public Boolean checkLoginAndPasswordInDB(String login, String password) {

        Connection connection = null;
        PreparedStatement pstmt = null;

        Boolean exists;

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/andrey",
                    "andrey", "andrey");

            String userIDRequest = "SELECT id FROM car_accident_report.authorization_data ad WHERE ad.user_login=? AND ad.user_password=?";
            pstmt = connection.prepareStatement(userIDRequest);

            pstmt.setString(1, login);
            pstmt.setString(2, password);


            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                exists = true;
            }
            else {
                exists = false;
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
        return exists;
    }

}
