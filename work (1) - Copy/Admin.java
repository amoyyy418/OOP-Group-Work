import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class Admin extends User{
    private String adminPrivileges;
    private int loginAttempts;

    public Admin(int userNumber, String username, String password, Role jobRole, String adminPrivileges) {
        super(userNumber, username, password, jobRole);
        this.adminPrivileges = adminPrivileges;
    }

    public String getAdminPrivileges() {
        return adminPrivileges;
    }

    public void setAdminPrivileges(String adminPrivileges) {
        this.adminPrivileges = adminPrivileges;
    }

    public void manageUsers() {
        System.out.println(getUsername() + " is managing users with privileges: " + adminPrivileges);
    }

    // Unlock account (Admin action)
    public void unlockAccount() {
        loginAttempts = 0;
        System.out.println("Account unlocked by Admin.");
    }

}