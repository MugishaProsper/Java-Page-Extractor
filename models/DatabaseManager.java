package models;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager(String dbPath) {
        try {
            // Ensure the directory exists
            File dbFile = new File(dbPath);
            File parentDir = dbFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                if (parentDir.mkdirs()) {
                    System.out.println("Directory created: " + parentDir.getAbsolutePath());
                }
            }

            // Establish the database connection
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        } catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }

    public void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS links (id INTEGER PRIMARY KEY AUTOINCREMENT, url TEXT)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (Exception e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public void insertLink(Link link) {
        String insertSQL = "INSERT INTO links(url) VALUES(?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, link.getUrl());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error inserting link: " + e.getMessage());
        }
    }

    public List<Link> getAllLinks() {
        List<Link> links = new ArrayList<>();
        String query = "SELECT * FROM links";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String url = rs.getString("url");
                links.add(new Link(id, url));
            }
        } catch (Exception e) {
            System.out.println("Error fetching links: " + e.getMessage());
        }
        return links;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("Error closing database connection: " + e.getMessage());
        }
    }
}
