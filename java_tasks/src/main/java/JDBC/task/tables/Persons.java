package JDBC.task.tables;

import JDBC.task.myAnnotations.Column;
import JDBC.task.myAnnotations.Table;

@Table(title = "Persons")
public class Persons {
    @Column
    int id;

    @Column
    String name;

    @Column
    String surname;

    @Column
    double salary;

    public Persons(int id, String name, String surname, double salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }
}

