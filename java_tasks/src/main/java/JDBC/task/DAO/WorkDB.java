package JDBC.task.DAO;

import java.sql.SQLException;

public class WorkDB {

    public static void exe(String request) {
        try {
            ConnectionDB.connect();
            ConnectionDB.requestAddTable(request);
        } catch(SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            ConnectionDB.disconnect();
        }
    }
}


