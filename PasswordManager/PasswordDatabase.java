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
                           "(URL PASSWORD PRIMARY KEY     NOT NULL," +
                           " URL            TEXT    NOT NULL, " + 
                           " LOGIN          TEXT    NOT NULL, " + 
                           " PASSWORD       TEXT    NOT NULL, " + 
                           " DATESTORED     TEXT    NOT NULL," +
                            " NOTES          TEXT    NOT NULL)";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
         } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
         }
         System.out.println("Table created successfully");
      }


    private void connect(String dbPath) throws SQLException {// connect to database
        Connection c = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:PasswordManager.db");
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Opened database successfully");
   }

    //Method to add a password entry to the database
    public void addPasswordEntry(PasswordEntry entry) {
        // Implement database logic here
        Connection c = null;
      Statement statement = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:PasswordManager.db");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         
         statement.close();
         c.commit();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");
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
         int Url = rs.getInt("url");
         String  Login = rs.getString("login");
         String Password  = rs.getString("password");
         String  DateStored = rs.getString("datestored");
         String Notes = rs.getString("notes");
         
         System.out.println( "URL = " + url );
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
    public void updatePasswordEntry(PasswordEntry entry) {
        // Implement database logic here
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }


    
}