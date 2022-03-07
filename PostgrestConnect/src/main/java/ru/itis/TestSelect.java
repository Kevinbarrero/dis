package ru.itis;

import java.sql.*;

public class TestSelect {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/uber";
        try (Connection conn = DriverManager.getConnection(url,"postgres", "passwd")) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM driver INNER JOIN ");
            System.out.println("----------------------------------------");
            System.out.println("name | driver_id | car_id");
            System.out.println("----------------------------------------");
            while(rs.next()){

                System.out.println(rs.getString("name") + "|"
                                + rs.getString("driver_id") + "|"
                                + rs.getString("car_id"));
            }
            rs.close();
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
