package PasswordManager;

import java.sql.*;

public class PasswordDatabase {
    private Connection connection;
    private Statement statement;


    public PasswordDatabase(String dbPath) throws SQLException {//create a table
        connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:PasswordManager.db");
            System.out.println("Opened database successfully");
   
            statement = connection.createStatement();
            String sql = "CREATE TABLE DATABASE " +
                        "(URL            TEXT    NOT NULL, " + 
                        " LOGIN          TEXT    NOT NULL, " + 
                        " PASSWORD       TEXT    NOT NULL, " + 
                        " DATESTORED     TEXT    NOT NULL," +
                        " NOTES          TEXT    NOT NULL," +
                        " PRIMARY KEY (URL, LOGIN))";
            statement.executeUpdate(sql);
         } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
         }
         System.out.println("Table created successfully");
      }


    //Method to add a password entry to the database
   public void addPasswordEntry(PasswordEntry entry) throws SQLException {
      String sql = "INSERT INTO DATABASE (URL, LOGIN, PASSWORD, DATESTORED, NOTES) VALUES (?,?,?,?,?)";
      
      PreparedStatement pstmt = connection.prepareStatement(sql);
       pstmt.setString(1, entry.getUrl());
       pstmt.setString(2, entry.getLogin());
       pstmt.setString(3, entry.getPassword());
       pstmt.setString(4, entry.getDateStored().toString()); // Convert LocalDateTime to string
       pstmt.setString(5, entry.getNotes());

       pstmt.executeUpdate();
     }
    

    // Method to retrieve a password entry from the database
    public PasswordEntry getPasswordEntry(String url) {
        // Implement database logic here
         Connection c = null;
   Statement statement = null;
   try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:PasswordManager.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      statement = c.createStatement();
      ResultSet rs = statement.executeQuery( "SELECT * FROM DATABASE;" );
      
      while ( rs.next() ) {
         String Url = rs.getString("url");
         String Login = rs.getString("login");
         String Password  = rs.getString("password");
         String DateStored = rs.getString("datestored");
         String Notes = rs.getString("notes");
         
         System.out.println( "URL = " + Url );
         System.out.println( "LOGIN = " + Login );
         System.out.println( "PASSWORD = " + Password );
         System.out.println( "DATESTORED = " + DateStored );
         System.out.println( "NOTES = " + Notes );
         System.out.println();
      }
      rs.close();
      statement.close();
      c.close();
   } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
   }
   System.out.println("Operation done successfully");
   return null;
  }
        
    

    //Method to update a password entry in the database
    public void updatePasswordEntry(PasswordEntry entry) throws SQLException {
      String sql = "UPDATE DATABASE SET URL = ? , "
              + "LOGIN = ? , "
              + "PASSWORD = ? , "
              + "DATESTORED = ? , "
              + "NOTES = ? "
              + "WHERE URL = ? AND LOGIN = ?";
      
      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setString(1, entry.getUrl());
      pstmt.setString(2, entry.getLogin());
      pstmt.setString(3, entry.getPassword());
      pstmt.setString(4, entry.getDateStored().toString()); // Convert LocalDateTime to string
      pstmt.setString(5, entry.getNotes());

      pstmt.executeUpdate();

    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            statement.close();
            connection.close();
        }
    }


    
}