package ru.itis.s2_lab4;

import ru.itis.s2_lab4.orm.EntityManagerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class pruebitas {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = new EntityManagerFactory("jdbc:postgresql://localhost:5432/orm").getConnection();
        List<String> lst = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement(
                "select COLUMN_NAME, DATA_TYPE, table_name from INFORMATION_SCHEMA.COLUMNS " +
                        "where TABLE_NAME= any (SELECT table_name FROM information_schema.tables " +
                        "WHERE table_type = 'BASE TABLE' AND table_schema NOT IN ('pg_catalog', 'information_schema') )");

        ResultSet resultSet = st.executeQuery();
        Map<String, Map<String, Class>> structureDB = new HashMap<>();
        List<String> temptablename = new ArrayList<>();
        List<Class<?>> temptype= new ArrayList<>();
        List<String> columnName = new ArrayList<>();
        while (resultSet.next()) {
            Class<?> datatype = null;
            String tableName = resultSet.getString("table_name");

            String columName = resultSet.getString("COLUMN_NAME");
            System.out.println(columName);
            String sdataType = resultSet.getString("DATA_TYPE");
            switch (sdataType){
                case "bigint":
                    datatype = Long.class;
                    break;
                case "character varying":
                    datatype = String.class;
                    break;
            }
            //nombre de clase(tipo de dato)
            temptype.add(datatype);
            //nombre de la tabla
            temptablename.add(tableName);
            //nomnbre de la colunma
            columnName.add(columName);


        }
        int y;
        Map<String, Class> tempmap2= new HashMap<>();
        Map<String, Class> tempmap = new HashMap<>();
        for (int i = 0; i < temptablename.size(); i++) {
                y = Collections.frequency(temptablename, temptablename.get(i));
            for (int j = 0; j < y ; j++) {
                if(tempmap.isEmpty()){
                    tempmap.put(columnName.get(j), temptype.get(i));
                }else
                    tempmap2.put(columnName.get(i), temptype.get(j));


            }
            System.out.println(tempmap);
            System.out.println(temptablename.get(i));
            structureDB.put(temptablename.get(i), tempmap);
            structureDB.put(temptablename.get(i), tempmap2);
            temptablename.removeAll(Collections.singleton(temptablename.get(i)));
            columnName = columnName.subList(y, columnName.size());
            temptype = temptype.subList(y, temptype.size());
        }


        System.out.println(structureDB);

    }
}
