package PasswordManager;

import java.time.LocalDateTime; // This is to get the current date and time
import java.util.regex.Matcher; // This is to use regex for password validation
import java.util.regex.Pattern; // This is to use regex for password validation
public class PasswordEntry {
    private String url;
    private String login;
    private String password;
    private LocalDateTime dateStored;
    private String notes;
    private static final String PASSWORD_PATTERN = "^(?=.*[@#$%])[a-zA-Z0-9@#$%]{12,}$";


    public PasswordEntry(String url, String login, String password, String notes) { // This is the constructor method for the PasswordEntry class. Afiq (2228775)
        this.url = url;
        this.login = login;
        setPassword(password); //Validate password before setting it
        this.dateStored = LocalDateTime.now();
        this.notes = notes;
    }


    // Getters. Afiq (2228775) and Izzu (2226833)
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

    // Setters. Afiq (2228775) and Izzu (2226833)
    public void setUrl(String url) {
        this.url = url;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) { //This method is to ensure that the password is valid before setting it. Afiq (2228775)
        ValidationResult result = validatePassword(password); 
        if (result.isValid()) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Invalid password: " + result.getMessage()); //This throws an exception if the password is invalid
        }
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String display(){ // This is to display the password entry. Afiq (2228775)
        return "URL: " + url + "\n" +
                "Login: " + login + "\n" +
                "Password: " + password + "\n" +
                "Date Stored: " + dateStored + "\n" +
                "Notes: " + notes + "\n";

    }

    public static ValidationResult validatePassword(String password) { // This is to validate the password. Afiq (2228775)

    Pattern regex = Pattern.compile(PASSWORD_PATTERN);
    Matcher matcher = regex.matcher(password);
    ValidationResult result;


    if (matcher.matches()){
        result = new ValidationResult(true, "Password is valid.");
    }else {
        result = new ValidationResult(false, "Invalid password! Please retry! \n" +
                                                    "1. It must be at least 12 characters long. \n" +
                                                    "2. It must contain at least one of these symbols ONLY: @#$% \n" +
                                                    "3. It must consist of alphanumeric characters.");
    }
        return result;
    }

    public static class ValidationResult{ // This is a class to store the result of the password validation. Afiq (2228775)

    private boolean valid;
    private String message;

    public ValidationResult(boolean valid, String message) { // This is the constructor method for the ValidationResult class. Afiq (2228775)
        this.valid = valid;
        this.message = message;
    }   

    public boolean isValid(){ // This is to check if the password is valid. Afiq (2228775)
        return valid;
    }

    public String getMessage(){ // This is to get the error message. Afiq (2228775)
        return message;
    }

    }

}