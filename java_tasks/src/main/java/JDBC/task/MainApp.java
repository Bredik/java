package JDBC.task;

import JDBC.task.tables.AnimalsTable;
import JDBC.task.tables.Persons;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        ProcessorAnnotations processor1 = new ProcessorAnnotations();
        Class newTablePersons = Persons.class;
        Class newTableAnimals = AnimalsTable.class;

        processor1.createTable(newTablePersons);
        processor1.createTable(newTableAnimals);
    }
}
