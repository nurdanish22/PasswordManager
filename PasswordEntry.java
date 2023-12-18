package PasswordManager;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class PasswordEntry { // 
    private String url;
    private String login;
    private String password;
    private LocalDateTime dateStored;
    private String notes;
    private static final String PASSWORD_PATTERN = "^(?=.*[@#$%])[a-zA-Z0-9@#$%]{12,}$";


    public PasswordEntry(String url, String login, String password, String notes) { // Constructor method.
        this.url = url;
        this.login = login;
        setPassword(password); //Validate password before setting it
        this.dateStored = LocalDateTime.now();
        this.notes = notes;
    }


    // Getters by catto & izzu
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

    // Setters by Catto and Izzu
    public void setUrl(String url) {
        this.url = url;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    //catto
    public void setPassword(String password) { //This method is to ensure that the password is valid before setting it.
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

    public String display(){
        return "URL: " + url + "\n" +
                "Login: " + login + "\n" +
                "Password: " + password + "\n" +
                "Date Stored: " + dateStored + "\n" +
                "Notes: " + notes + "\n";

    }

    //catto 
    public static ValidationResult validatePassword(String password) {

    Pattern regex = Pattern.compile(PASSWORD_PATTERN);
    Matcher matcher = regex.matcher(password);
    ValidationResult result;


    if (matcher.matches()){
        result = new ValidationResult(true, "Password is valid.");
    }else {
        result = new ValidationResult(false, "It must be at least 12 character long and contain at least one symbols");
    }
        return result;
    }

    //catto
    public static class ValidationResult{

    private boolean valid;
    private String message;

    public ValidationResult(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }   

    public boolean isValid(){
        return valid;
    }

    public String getMessage(){
        return message;
    }

    }

}