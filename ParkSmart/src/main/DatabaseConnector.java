package main;

import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;

public class DatabaseConnector {
	
	// link to database for establish connection
//	private static final String database_link = "jdbc:sqlite:garage.db";
	
	public static void main(String[] args) {
		//
	}
	
	public static Connection connect() {
		try {
            // Load the database file from resources
            URL resource = DatabaseConnector.class.getClassLoader().getResource("database/mydatabase.db");
            if (resource == null) {
                throw new IllegalStateException("Database file not found in resources.");
            }

            String dbPath = Paths.get(resource.toURI()).toString();
            String url = "jdbc:sqlite:" + dbPath;

            Connection conn = DriverManager.getConnection(url);
            System.out.println("✅ Connected to SQLite at " + dbPath);
            return conn;

        } catch (Exception e) {
            System.err.println("❌ Failed to connect: " + e.getMessage());
            return null;
        }
	}
	
	public void DatabaseInsert(String arg, String ontoLocation) {
		try (Connection conn = DatabaseConnector.connect()){
			String statement = "INSERT ";
		}
	}
	
	
	
	

    
}
