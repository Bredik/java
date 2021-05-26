package JDBC.task.tables;

import JDBC.task.myAnnotations.Column;
import JDBC.task.myAnnotations.Table;

@Table(title = "Animals")
public class AnimalsTable {
    @Column
    int id;

    @Column
    String type;

    @Column
    String name;

    public AnimalsTable(int id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }
}

