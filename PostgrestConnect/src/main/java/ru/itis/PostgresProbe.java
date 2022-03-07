package ru.itis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresProbe {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/uber";
        try (Connection conn = DriverManager.getConnection(url,"postgres", "passwd")) {
            Statement statement = conn.createStatement();
            statement.execute("ALTER TABLE driver ADD COLUMN driver_id bigint");
            statement.execute("CREATE sequence driver_seq");
            statement.executeUpdate("UPDATE driver SET driver_id=nextval('driver_seq')");
            statement.execute("ALTER TABLE driver ADD CONSTRAINT pk_driver PRIMARY KEY (driver_id)");
            //statement.execute("ALTER TABLE driver ADD COLUMN driver_id serial PRIMARY KEY");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
