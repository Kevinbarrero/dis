package ru.itis.s2lab3spring.util;

import ru.itis.s2lab3spring.entity.Users;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class CRUDOperations {

    public void findEntity() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Users users = entityManager.find(Users.class, Long.parseLong("1"));
        System.out.println("student id :: " + users.getId());
        System.out.println("student firstname :: " + users.getName());
        System.out.println("student password :: " + users.getPassword());
        System.out.println("student role :: " + users.getRole());
        System.out.println("student role :: " + users.getGroup_id());
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public ArrayList<String> columnNamesInClass(Class<?> classname){
        ArrayList<String> names = new ArrayList<>();
        for (Field f: classname.getDeclaredFields()) {
            Column column = f.getAnnotation(Column.class);
            if (column != null)
                names.add(column.name());
        }
        for (Field f: classname.getDeclaredFields()) {
            JoinColumn manys = f.getAnnotation(JoinColumn.class);
            if (manys != null)
                names.add(manys.name());
        }

        return names;
    }
    public ArrayList<String> columnNamesInDB(String tableName) throws SQLException {
        ArrayList<String> attributes = new ArrayList<>();
        String url = "jdbc:postgresql://localhost/orm";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","");
        Connection conn = DriverManager.getConnection(url, props);
        Statement statement = conn.createStatement();
        String a = String.format("'%s'", tableName.toLowerCase());
        String query = "SELECT a.attname FROM pg_catalog.pg_attribute a WHERE a.attrelid = (SELECT c.oid FROM pg_catalog.pg_class c \n" +
                "\t\t\tLEFT JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace \n" +
                "\t\t\t\tWHERE pg_catalog.pg_table_is_visible(c.oid) AND c.relname = " +a+" ) AND a.attnum > 0 AND NOT a.attisdropped";
        ResultSet rs = statement.executeQuery(query);


        while (rs.next()) {
            attributes.add(rs.getString("attname"));

        }
        return attributes;
    }
    public Boolean compareColumnNames(ArrayList<String> arr1, ArrayList<String> arr2){
        return arr1.equals(arr2);
    }


}