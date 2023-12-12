package PasswordManager;

import java.time.LocalDateTime;

public class PasswordEntry {
    private String url;
    private String login;
    private String password;
    private LocalDateTime dateStored;
    private String notes;

    public PasswordEntry(String url, String login, String password, String notes) {
        this.url = url;
        this.login = login;
        this.password = password;
        this.dateStored = LocalDateTime.now();
        this.notes = notes;
    }

    // TODO: Add getters and setters for each field
    // TODO: Add a method to validate the password according to the rules
}