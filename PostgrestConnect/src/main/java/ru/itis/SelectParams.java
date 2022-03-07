package ru.itis;

import java.sql.*;

public class SelectParams {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/uber";
        try (Connection conn = DriverManager.getConnection(url,"postgres", "passwd")) {
            String sql = "SELECT driver_id as id, name, car_id " +
                    "      FROM driver WHERE car_id = ?" +
                    "      and name LIKE ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,2);
            statement.setString(2,"%_");

            ResultSet rs = statement.executeQuery();
            System.out.println("----------------------------------------");
            System.out.println("name | driver_id | car_id");
            System.out.println("----------------------------------------");
            while(rs.next()){

                System.out.println(rs.getString("name") + "|"
                        + rs.getString("id") + "|"
                        + rs.getString("car_id"));
            }
            rs.close();
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
