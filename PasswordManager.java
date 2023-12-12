package PasswordManager;

import java.util.ArrayList;
import java.security.MessageDigest; //This is to hash the password
import java.security.NoSuchAlgorithmException; //This is to check if the algorithm is valid
import java.nio.charset.StandardCharsets; //This is to convert the password to bytes
import java.math.BigInteger; //This is to convert the bytes to a BigInteger

public class PasswordManager {
    private ArrayList<PasswordEntry> passwordEntries;
    private String hashedMasterPassword;

    public PasswordManager(String masterPassword) {
        this.passwordEntries = new ArrayList<>();
        this.hashedMasterPassword = hashPassword(masterPassword);
    }

    private String hashPassword(String password) {
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

    // TODO: Add methods to add and retrieve entries
}