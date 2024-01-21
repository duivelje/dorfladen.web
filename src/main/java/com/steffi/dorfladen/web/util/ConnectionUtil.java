package com.steffi.dorfladen.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
// import java.sql.ResultSet;
import java.sql.SQLException;
// import java.sql.Statement;

public class ConnectionUtil {

    private static Connection connection;
    private static String url = "jdbc:postgresql://localhost:5432/dorfladen-db";
    private static String user = "Admin";
    private static String password_verschluesselt = "stXN26/2bQ8BE7haHQHfoA==";

    public static Connection getConnection(){
        if(connection == null){
            try {
                connection = DriverManager.getConnection(url, user, PasswortHashing.decrypt(password_verschluesselt));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                System.out.println("Connection zur Datenbank schlug fehl!");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void close() {
        try {
            getConnection().close();
        }
        catch (SQLException e) {
            //Dann halt nicht
        }
        System.exit(0);
    }

    

    // public static void main(String[] args) {
    //     try {
    //         Statement stmt = getConnection().createStatement();
    //         ResultSet rs = stmt.executeQuery("SELECT * FROM dorfladen.mitarbeiter");

    //         while (rs.next()) {
    //             // Bearbeite hier deine Ergebnisse
    //             System.out.println(rs.getString("vorname"));
    //         }

    //         rs.close();
    //         stmt.close();
    //         getConnection().close();
    //     } catch (Exception e) {
    //         System.out.println("fehler");
    //     }

    // }
}
