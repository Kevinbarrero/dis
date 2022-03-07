package ru.itis;

import java.sql.*;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        String url = "jdbc:postgresql://localhost/orm";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","");
        Connection conn = DriverManager.getConnection(url, props);
        DatabaseMetaData md = conn.getMetaData();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_type = 'BASE TABLE' AND table_schema NOT IN ('pg_catalog', 'information_schema')\n");


        while (rs.next()) {
            System.out.println(rs.getString("table_name"));
        }
    }
}
