import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Main class with GUI and file operations
public class PatientManagement extends JFrame {
    // Static variable for generating unique patient numbers
    private static int patientCounter = 1;
    
    // RandomAccessFile for storing patient records
    private RandomAccessFile pfile;
    
    // GUI components
    private JButton btnAdd, btnEdit, btnDelete, btnView;
    private JTextArea textArea;

    public PatientManagement() {
        super("Patient Management System");
        setLayout(new BorderLayout());
        
        // Setup GUI controls
        JPanel panel = new JPanel();
        btnAdd = new JButton("Add Patient");
        btnEdit = new JButton("Edit Patient");
        btnDelete = new JButton("Delete Patient");
        btnView = new JButton("View Patients");
        panel.add(btnAdd);
        panel.add(btnEdit);
        panel.add(btnDelete);
        panel.add(btnView);
        add(panel, BorderLayout.NORTH);
        
        textArea = new JTextArea(20, 50);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        // Initialize file (creates file if not exists)
        try {
            pfile = new RandomAccessFile("patients.dat", "rw");
        } catch(IOException ex) {
            JOptionPane.showMessageDialog(this, "Error opening file: " + ex.getMessage());
        }
        
        // Button actions
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPatient();
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editPatient();
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletePatient();
            }
        });
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewPatients();
            }
        });
    }
    
    // Add new patient record
    private void addPatient() {
        Patient patient = new Patient();
        patient.setPatientNumber(patientCounter++);
        try {
            patient.inputData();
            savePatient(patient);
            JOptionPane.showMessageDialog(this, "Patient added successfully.");
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding patient: " + ex.getMessage());
        }
    }
    
    // Edit an existing patient record (using method overriding/overloading)
    private void editPatient() {
        String numStr = JOptionPane.showInputDialog("Enter Patient Number to Edit:");
        try {
            int pnum = Integer.parseInt(numStr);
            long pos = findPatientPosition(pnum);
            if (pos < 0) {
                JOptionPane.showMessageDialog(this, "Patient not found.");
                return;
            }
            pfile.seek(pos);
            Patient patient = new Patient();
            patient.readFromFile(pfile);
            // Using the overloaded editData method: user may choose to edit selected fields
            patient.editData();
            pfile.seek(pos);
            patient.writeToFile(pfile);
            JOptionPane.showMessageDialog(this, "Patient record updated successfully.");
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Error editing patient: " + ex.getMessage());
        }
    }
    
    // "Delete" a patient record (mark as deleted)
    private void deletePatient() {
        String numStr = JOptionPane.showInputDialog("Enter Patient Number to Delete:");
        try {
            int pnum = Integer.parseInt(numStr);
            long pos = findPatientPosition(pnum);
            if (pos < 0) {
                JOptionPane.showMessageDialog(this, "Patient not found.");
                return;
            }
            pfile.seek(pos);
            Patient patient = new Patient();
            patient.readFromFile(pfile);
            patient.setDeleted(true);
            pfile.seek(pos);
            patient.writeToFile(pfile);
            JOptionPane.showMessageDialog(this, "Patient record deleted successfully.");
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Error deleting patient: " + ex.getMessage());
        }
    }
    
    // View all patient records (only those not marked as deleted)
    private void viewPatients() {
        try {
            pfile.seek(0);
            textArea.setText("");
            while (pfile.getFilePointer() < pfile.length()) {
                Patient patient = new Patient();
                patient.readFromFile(pfile);
                if (!patient.isDeleted()) {
                    textArea.append(patient.toString() + "\n----------------------\n");
                }
            }
        } catch(IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage());
        }
    }
    
    // Save patient record to the end of file
    private void savePatient(Patient patient) throws IOException {
        pfile.seek(pfile.length());
        patient.writeToFile(pfile);
    }
    
    // Find a patient record's position in the file by patient number
    private long findPatientPosition(int patientNumber) throws IOException {
       pfile.seek(0);
        long pos = 0;
        while (pos < pfile.length()) {
            pfile.seek(pos);
            Patient patient = new Patient();
            patient.readFromFile(pfile);
            if (!patient.isDeleted() && patient.getPatientNumber() == patientNumber) {
                return pos;
            }
            pos = pfile.getFilePointer();
        }
        return -1;
    }
    
}
