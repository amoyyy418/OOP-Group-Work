import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee {
    private int empID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String dateOfBirth;
    private String dateOfEmployment;
    private String department;
    private String gender;
    private String trn;
    private String jobTitle;
    private String assignedSupervisor;
    private String role;
    private List<String> certifications;

    public Employee(int empID, String firstName, String middleName, String lastName, String dateOfBirth,
                    String dateOfEmployment, String department, String gender, String trn,
                    String jobTitle, String assignedSupervisor, String role) {
        this.empID = empID;
        this.firstName = firstName;
        this.middleName = middleName != null ? middleName : "";
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfEmployment = dateOfEmployment;
        this.department = department;
        this.gender = gender;
        this.trn = trn;
        this.jobTitle = jobTitle;
        this.assignedSupervisor = assignedSupervisor;
        this.role = role;
        this.certifications = new ArrayList<>();
    }

    public int getEmpID() {
        return empID;
    }

    public String getRole() {
        return role;
    }

    public void addCertification(String certification) {
        certifications.add(certification);
    }

    public void displayProfile() {
        System.out.println("Employee ID: " + empID);
        System.out.println("Name: " + firstName + " " + (middleName.isEmpty() ? "" : middleName + " ") + lastName);
        System.out.println("DOB: " + dateOfBirth);
        System.out.println("Employment Date: " + dateOfEmployment);
        System.out.println("Department: " + department);
        System.out.println("Gender: " + gender);
        System.out.println("TRN: " + trn);
        System.out.println("Job Title: " + jobTitle);
        System.out.println("Assigned Supervisor: " + assignedSupervisor);
        System.out.println("Role: " + role);
        System.out.println("Certifications: " + certifications);
    }

    @Override
    public String toString() {
        return empID + "," + firstName + "," + middleName + "," + lastName + "," + dateOfBirth + "," +
                dateOfEmployment + "," + department + "," + gender + "," + trn + "," + jobTitle + "," +
                assignedSupervisor + "," + role;
    }
}

class StaffRecordsManagement {
    private static final String FILE_NAME = "staff_records.dat";
    private List<Employee> employees;

    public StaffRecordsManagement() {
        employees = new ArrayList<>();
        loadEmployeesFromFile();
    }

    public void addEmployee(Employee emp) {
        employees.add(emp);
        saveEmployeeToFile(emp);
    }

    public void viewEmployees(String role) {
        if (!role.equalsIgnoreCase("Admin") && !role.equalsIgnoreCase("Doctor")) {
            System.out.println("Access Denied! Only Admins and Doctors can view staff records.");
            return;
        }

        System.out.println("--- Staff Records ---");
        for (Employee emp : employees) {
            emp.displayProfile();
            System.out.println("------------------------------------");
        }
    }

    private void saveEmployeeToFile(Employee emp) {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            file.seek(file.length());  // Move to the end of the file to append data
            file.writeBytes(emp.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private void loadEmployeesFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (RandomAccessFile reader = new RandomAccessFile(FILE_NAME, "r")) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 12) {
                    employees.add(new Employee(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3],
                            parts[4], parts[5], parts[6], parts[7], parts[8], parts[9], parts[10], parts[11]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

