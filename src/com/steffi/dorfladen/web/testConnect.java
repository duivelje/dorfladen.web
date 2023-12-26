package com.steffi.dorfladen.web

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/dorfladen-db";
        String user = "Admin";
        String password = "ThisIsATest";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM dorfladen.mitarbeiter");

            while (rs.next()) {
                // Bearbeite hier deine Ergebnisse
                System.out.println(rs.getString("vorname"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
