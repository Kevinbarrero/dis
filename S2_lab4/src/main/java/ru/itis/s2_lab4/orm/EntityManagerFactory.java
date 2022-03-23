package ru.itis.s2_lab4.orm;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityManagerFactory {

    private CharSequence url;

    private Connection connection;

    private Map<Long, EntityManager> entityManagerMap = new HashMap<>();

    private Map<String, Map<String, Class>> structureDB;


    public void scanDB() throws SQLException {
        EntityManagerFactory entityManagerFactory = new EntityManagerFactory("jdbc:postgresql://localhost:5432/orm");
        List<String> lst = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement(
                "SELECT table_name FROM information_schema.tables " +
                        "WHERE table_type = 'BASE TABLE' AND" +
                        " table_schema NOT IN ('pg_catalog', 'information_schema')");

        ResultSet resultSet = st.executeQuery();

        while (resultSet.next()) {
            String s = resultSet.getString("table_name");
            lst.add(s);
        }
        ResultSet typedata = entityManagerFactory.getConnection().prepareStatement("select COLUMN_NAME, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH, \n" +
                "       NUMERIC_PRECISION, DATETIME_PRECISION, \n" +
                "       IS_NULLABLE \n" +
                "from INFORMATION_SCHEMA.COLUMNS\n" +
                "where TABLE_NAME='users'").executeQuery();
        while (typedata.next()) {

            System.out.println(typedata.getString("COLUMN_NAME"));
            System.out.println(typedata.getString("DATA_TYPE"));

        }

    }

    public EntityManagerFactory(CharSequence url) {
        this.url = url;
    }

    public Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection((String) url, "postgres", "");
        return connection;
    }

    public EntityManager getEntityManager() {
        Long id = Thread.currentThread().getId();
        EntityManager em = entityManagerMap.get(id);
        if (em == null) {
            em = new EntityManagerImpl();
        }
        return em;
    }

    public void closeEntityManager() {
        Long id = Thread.currentThread().getId();
        entityManagerMap.remove(id);
    }

    public String testDbWork() {
        return this.toString();
    }

    public void destroy() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            System.out.println("DbWork destroed");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}