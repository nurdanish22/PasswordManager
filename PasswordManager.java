package PasswordManager;

import java.util.ArrayList; // This is to use ArrayLists
import java.security.MessageDigest; // This is to hash the master password
import java.security.NoSuchAlgorithmException; // This is to check if the algorithm is valid
import java.nio.charset.StandardCharsets; // This is to convert the password to bytes
import java.math.BigInteger; // This is to convert the bytes to a BigInteger

public class PasswordManager { // Nurdanish (2224875)
    private ArrayList<PasswordEntry> passwordEntries;
    private String hashedMasterPassword;

    public PasswordManager(String masterPassword) { // The constructor method for the PasswordManager class. Nurdanish (2224875)
        this.passwordEntries = new ArrayList<>();
        this.hashedMasterPassword = hashPassword(masterPassword);
    }

    static String hashPassword(String password) { // This method is to hash the master password for security. Nurdanish (2224875)
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512"); //This is the hashing algorithm
            byte[] messageDigest = md.digest(password.getBytes(StandardCharsets.UTF_8)); //This converts the password to bytes and hashes it
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16); //This converts the bytes to a BigInteger and then to a string
            while (hashtext.length() < 32) { //This adds a 0 to the beginning of the string if it is too short
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) { //This is to catch if the algorithm is invalid
            throw new RuntimeException(e);
        }
    }

    public String getHashedMasterPassword() { // This is to retrieve the hashed master password. Nurdanish (2224875)
        return hashedMasterPassword;
    }

    public void addEntry(PasswordEntry entry) { // This is to add an entry to the passwordEntries ArrayList. Nurdanish (2224875)
        this.passwordEntries.add(entry);
    }

    public PasswordEntry getEntry(String url){ //This is to retrieve an entry by its url. Nurdanish (2224875)
        for (PasswordEntry entry : this.passwordEntries) {
            if (entry.getUrl().equals(url)) {
            return entry;
            }
        }
        return null; // If no entry is found, return null
    }

    public ArrayList<PasswordEntry> getAllEntries() { // This is to retrieve all entries. Nurdanish (2224875)
        return new ArrayList<>(this.passwordEntries); // Return a copy of the passwordEntries ArrayList to ensure that the original ArrayList is not modified (encapsulation)
    }
}