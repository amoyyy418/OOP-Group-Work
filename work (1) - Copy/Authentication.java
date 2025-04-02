import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class Authentication {
    private static final String FILE_PATH = "users.dat";
    private static int loginAttempts = 0;

    public Authentication() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error initializing authentication file.");
        }
    }

    // Register a new user
    public void registerUser(User user) {
        try (RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "rw")) {
            raf.seek(raf.length());
            raf.writeInt(user.getUserNumber()); // Write user number
            raf.writeUTF(user.getUsername());
            raf.writeUTF(user.getEncryptedPassword());
            raf.writeUTF(user.getJobRole().getRoleName());
            System.out.println("Registration successful!");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
    
    //Overload
    public void registerUser(int userNumber, String username, String password, Role jobRole) {
    User user = new User(userNumber, username, password, jobRole);
    registerUser(user); // Calls the existing method
  }


    // User login
    public boolean login(String username, String password) {
        if (loginAttempts >= 3) {
            System.out.println("Account locked due to too many failed attempts. Contact Admin.");
            return false;
        }

        String encryptedPassword = new User(0, username, password, null).getEncryptedPassword();
        try (RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                int storedUserNumber = raf.readInt(); // Read user number
                String storedUsername = raf.readUTF();
                String storedPassword = raf.readUTF();
                String role = raf.readUTF();

                if (storedUsername.equals(username) && storedPassword.equals(encryptedPassword)) {
                    System.out.println("Login successful! Welcome, " + username );
                    Dashboard.showDashboard(role); // Show the corresponding dashboard
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file.");
        }

        loginAttempts++;
        System.out.println("Invalid credentials. Attempt " + loginAttempts + " of 3.");
        return false;
    }


    // Method to display file contents
    public void displayFileContents() {
        try (RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                int userNumber = raf.readInt(); // Read user number
                String username = raf.readUTF();
                String encryptedPassword = raf.readUTF();
                String role = raf.readUTF();
                
                // Display the user details
                System.out.println("User Number: " + userNumber);
                System.out.println("Username: " + username);
                System.out.println("Password: " + encryptedPassword);
                System.out.println("Role: " + role);
                System.out.println("-----------------------------");
            }
        } catch (IOException e) {
            System.out.println("Error reading from file.");
        }
    }
}

