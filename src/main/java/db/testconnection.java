package db;

import java.sql.Connection;

public class testconnection {

    public static void main(String[] args) {
        try {
            Connection con = DBconnection.getConnection();
            System.out.println("Database connected successfully!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}