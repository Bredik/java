package JDBC;

import java.sql.*;

/*
JDBC набор интерфейсов для взаимодействия SQL DB
 */

public class Train {
    static Connection myConnection; //Соединение с БД
    static Statement myStatement; //Отправка запросов в БД
    static PreparedStatement preparedStatement;

    public static void main(String[] args) {
        try {
            connect();
            // Запрос на обновление (добавление нового юзера)
            //myStatement.executeUpdate("INSERT INTO students (name, score) VALUES ('Katya', 50);");

            // Запрос на обновление скор
            //myStatement.executeUpdate("UPDATE students SET score = 140 WHERE id = 1");

            // Запрос на удаление
            //myStatement.executeUpdate("DELETE FROM students WHERE id = 3");

            /*
            preparedStatement.setString(1, "Masha25");
            preparedStatement.setInt(2, 56);
            preparedStatement.executeUpdate();
            */


            ResultSet rs = myStatement.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int score = rs.getInt("score");
                System.out.println("ID = " + id);
                System.out.println("name = " + name);
                System.out.println("score = " + score);
                System.out.println();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

    }

    public static void connect() throws SQLException {
        /*
        Загружает класс в память, при загрузке происходит
        инициализация и он регистрируется в драйверменеджере
         */
        try {
            myConnection = null;

            Class.forName("org.sqlite.JDBC");
            myConnection = DriverManager.getConnection("jdbc:sqlite:D:/IT/database/main.db");
            myStatement = myConnection.createStatement();
            preparedStatement = myConnection.prepareStatement("INSERT INTO students (name, score)" +
                    "VALUES (?, ?)");

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
