import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class User {
    private int userNumber;
    private String username;
    private String encryptedPassword;
    private Role jobRole;

    public User(int userNumber, String username, String password, Role jobRole) {
        if (userNumber < 0 || userNumber > 99999) {
            throw new IllegalArgumentException("User number cannot be more than 5 digits.");
        }
        this.userNumber = userNumber;
        this.username = username;
        this.encryptedPassword = encryptPassword(password);
        this.jobRole = jobRole;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public Role getJobRole() {
        return jobRole;
    }
    

    private String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
