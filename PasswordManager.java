package PasswordManager;

import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;

public class PasswordManager {
    private ArrayList<PasswordEntry> passwordEntries;
    private String hashedMasterPassword;

    public PasswordManager(String masterPassword) {
        this.passwordEntries = new ArrayList<>();
        this.hashedMasterPassword = hashPassword(masterPassword);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password.getBytes(StandardCharsets.UTF_8));
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: Add methods to add and retrieve entries
}