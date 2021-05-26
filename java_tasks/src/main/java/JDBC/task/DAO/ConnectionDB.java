package JDBC.task.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
    static Connection myConnection;
    static Statement myStatement;

    public static void requestAddTable(String request) throws SQLException {
        myStatement.executeUpdate(request);
        System.out.println("Запрос!");
    }

    public static void connect() throws SQLException {
        try {
            myConnection = null;
            Class.forName("org.sqlite.JDBC");
            myConnection = DriverManager.getConnection("jdbc:sqlite:D:/IT/database/main.db");
            myStatement = myConnection.createStatement();
            System.out.println("База Подключена!");
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Что-то не то");
        }
    }

    public static void disconnect() {
        try {
            myStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            myConnection.close();
            System.out.println("База отключена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
