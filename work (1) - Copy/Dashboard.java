import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class Dashboard {

    // Method to show the dashboard based on the user role
    public static void showDashboard(String role) {
        switch (role) {
            case "Admin":
                System.out.println("Welcome to the Admin Dashboard");
                // Add admin-specific options here
                break;
            case "Doctor":
                System.out.println("Welcome to the Doctor Dashboard");
                // Add doctor-specific options here
                break;
            case "Receptionist":
                System.out.println("Welcome to the Receptionist Dashboard");
                // Add receptionist-specific options here
                break;
            case "Patient":
                System.out.println("Welcome to the Patient Dashboard");
                // Add patient-specific options here
                break;
            case "Dietitian":
                System.out.println("Welcome to the Dietician Dashboard");
                // Add patient-specific options here
                break;
            case "Nurse":
                System.out.println("Welcome to the Nurse Dashboard");
                // Add patient-specific options here
                break;
            case "Psychologist":
                System.out.println("Welcome to the Psychologist Dashboard");
                // Add patient-specific options here
                break;
            default:
                System.out.println("Unknown role. Please contact support.");
                break;
        }
    }
}
