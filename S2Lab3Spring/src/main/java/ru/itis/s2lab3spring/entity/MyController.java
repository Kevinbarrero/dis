package ru.itis.s2lab3spring.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.s2lab3spring.util.CRUDOperations;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class MyController {
    CRUDOperations crudOperations = new CRUDOperations();
    @RequestMapping("/users")
    public String home() throws SQLException {
        ArrayList<String> dataClass = crudOperations.columnNamesInClass(Users.class);
        ArrayList<String> dataDB = crudOperations.columnNamesInDB("Users");
        Boolean compare = crudOperations.compareColumnNames(dataDB, dataClass);
        return compare ?  "The Columns " + dataClass + "<br> in " + Users.class.getName() + "<br>" + "are the same in the table Users: " + dataDB : "The User's Columns are no the same" ;
    }

    @RequestMapping("/groups")
    public String groups() throws SQLException {
        ArrayList<String> dataClass = crudOperations.columnNamesInClass(Groups.class);
        ArrayList<String> dataDB = crudOperations.columnNamesInDB("Groups");
        Boolean compare = crudOperations.compareColumnNames(dataDB, dataClass);
        return compare ?  "The Columns " + dataClass + "<br> in " + Groups.class.getName() + "<br>" + "are the same in the table Groups: " + dataDB : "The User's Columns are no the same" ;
    }

}