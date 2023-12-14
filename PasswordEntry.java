package PasswordManager;

import java.time.LocalDateTime;

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
        this.password = password;
        this.dateStored = LocalDateTime.now();
        this.notes = notes;
    }

    

    // Getters //catto & izzu
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

  

    
 


    //catto 
    private static ValidationResult validatePassword(String password) {

    Pattern regex = Pattern.compile(PASSWORD_PATTERN);
    Matcher matcher = regex.matcher(password);
    ValidationResult result;

    if (matcher.matches()){
        result = new ValidationResult(true, "Password is valid.");
    }else {
        result = new ValidationResult(false, "Password is invalid. It must be at least 12 character long and contain at least one symbols");
    }
        return result;
    }
        

    //catto
    class ValidationResult{

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