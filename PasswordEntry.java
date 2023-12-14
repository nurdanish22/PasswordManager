package PasswordManager;

import java.time.LocalDateTime;

public class PasswordEntry { // 
    private String url;
    private String login;
    private String password;
    private LocalDateTime dateStored;
    private String notes;

    public PasswordEntry(String url, String login, String password, String notes) { // Constructor method.
        this.url = url;
        this.login = login;
        this.password = password;
        this.dateStored = LocalDateTime.now();
        this.notes = notes;
    }

    // Getters
    public String getUrl() {
        return url;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getDateStored() {
        return dateStored;
    }

    public String getNotes() {
        return notes;
    }

    // Setters
    public void setUrl(String url) {
        this.url = url;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String passwordRequirement(String password){
       
        

        
    }

    private static boolean condition(String password){

        String rules = "^(?=.*[@#$%^&+=])([a-zA-Z0-9@#$%^&+=]*)$";
        return password.matches(rules);
        
    }

    
    // TODO: Add getters and setters for each field
    // TODO: Add a method to validate the password according to the rules
}