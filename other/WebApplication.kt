package com.steffi.dorfladen.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebApplication

fun main(args: Array<String>) {
	//runApplication<WebApplication>(*args)
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
