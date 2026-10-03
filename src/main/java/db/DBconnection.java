package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {

    public static Connection getConnection() throws Exception {
        String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
        String user = "system";
        String password = "Dharshini123";

        return DriverManager.getConnection(url, user, password);
    }
}