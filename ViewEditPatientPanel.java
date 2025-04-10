package as;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

class ViewEditPatientPanel extends JPanel {
    private JTextField searchField;
    private JButton searchButton, editButton, deleteButton;
    private JTextArea patientDisplayArea;
    private Patient currentPatient;
    private int currentPatientNumber;
    
    public ViewEditPatientPanel() {
        setLayout(new BorderLayout());
        
        // Search panel
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(15);
        searchButton = new JButton("Search by Patient Number");
        
        searchPanel.add(new JLabel("Patient Number:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        
        // Patient display
        patientDisplayArea = new JTextArea(20, 60);
        patientDisplayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(patientDisplayArea);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        editButton = new JButton("Edit Patient");
        deleteButton = new JButton("Delete Patient");
        
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
        
        searchButton.addActionListener(e -> searchPatient());
        editButton.addActionListener(e -> editPatient());
        deleteButton.addActionListener(e -> deletePatient());
        
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void searchPatient() {
        try {
            int patientNumber = Integer.parseInt(searchField.getText());
            PatientManager manager = new PatientManager();
            Patient patient = manager.getPatient(patientNumber);
            
            if (patient != null) {
                currentPatient = patient;
                currentPatientNumber = patientNumber;
                displayPatient(patient);
                editButton.setEnabled(true);
                deleteButton.setEnabled(true);
            } else {
                patientDisplayArea.setText("Patient not found.");
                editButton.setEnabled(false);
                deleteButton.setEnabled(false);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error searching patient: " + ex.getMessage(),
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void displayPatient(Patient patient) {
        StringBuilder sb = new StringBuilder();
        sb.append("Patient Number: ").append(currentPatientNumber).append("\n");
        sb.append("Full Name: ").append(patient.getFullName()).append("\n");
        sb.append("TRN: ").append(patient.getTrn()).append("\n");
        sb.append("Date of Birth: ").append(patient.getDob()).append("\n");
        sb.append("Gender: ").append(patient.getGender()).append("\n");
        sb.append("Marital Status: ").append(patient.getMaritalStatus()).append("\n");
        sb.append("First Visit Date: ").append(patient.getFirstVisitDate()).append("\n");
        sb.append("Next of Kin: ").append(patient.getNextOfKin()).append("\n");
        sb.append("Next of Kin Contact: ").append(patient.getNextOfKinContact()).append("\n");
        sb.append("Email: ").append(patient.getEmail()).append("\n");
        sb.append("Phone: ").append(patient.getContactNumber()).append("\n");
        sb.append("Address: ").append(patient.getAddress()).append("\n");
        sb.append("Medical History:\n").append(patient.getMedicalHistory()).append("\n");
        
        patientDisplayArea.setText(sb.toString());
    }
    
    private void editPatient() {
        // Create a dialog similar to AddPatientPanel but pre-filled with current data
        JDialog editDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit Patient", true);
        editDialog.setSize(600, 700);
        editDialog.setLayout(new BorderLayout());
        
        // Form panel (similar to AddPatientPanel)
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JTextField firstNameField = new JTextField(currentPatient.getFirstName());
        JTextField middleNameField = new JTextField(currentPatient.getMiddleName());
        JTextField lastNameField = new JTextField(currentPatient.getLastName());
        JTextField trnField = new JTextField(currentPatient.getTrn());
        JTextField dobField = new JTextField(currentPatient.getDob());
        JTextField genderField = new JTextField(currentPatient.getGender());
        JTextField maritalStatusField = new JTextField(currentPatient.getMaritalStatus());
        JTextField nextOfKinField = new JTextField(currentPatient.getNextOfKin());
        JTextField nextOfKinContactField = new JTextField(currentPatient.getNextOfKinContact());
        JTextField emailField = new JTextField(currentPatient.getEmail());
        JTextField phoneField = new JTextField(currentPatient.getContactNumber().toString());
        JTextField addressField = new JTextField(currentPatient.getAddress().toString());
        JTextArea medicalHistoryArea = new JTextArea(currentPatient.getMedicalHistory(), 5, 20);
        medicalHistoryArea.setLineWrap(true);
        JScrollPane medicalHistoryScroll = new JScrollPane(medicalHistoryArea);
        
        // Add fields to form
        formPanel.add(new JLabel("First Name:"));
        formPanel.add(firstNameField);
        formPanel.add(new JLabel("Middle Name:"));
        formPanel.add(middleNameField);
        formPanel.add(new JLabel("Last Name:"));
        formPanel.add(lastNameField);
        formPanel.add(new JLabel("TRN:"));
        formPanel.add(trnField);
        formPanel.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
        formPanel.add(dobField);
        formPanel.add(new JLabel("Gender:"));
        formPanel.add(genderField);
        formPanel.add(new JLabel("Marital Status:"));
        formPanel.add(maritalStatusField);
        formPanel.add(new JLabel("Next of Kin:"));
        formPanel.add(nextOfKinField);
        formPanel.add(new JLabel("Next of Kin Contact:"));
        formPanel.add(nextOfKinContactField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Phone (XXX-XXX-XXXX):"));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("Address:"));
        formPanel.add(addressField);
        formPanel.add(new JLabel("Medical History:"));
        formPanel.add(medicalHistoryScroll);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save Changes");
        JButton cancelButton = new JButton("Cancel");
        
        saveButton.addActionListener(e -> {
            try {
                Patient updatedPatient = new Patient();
                updatedPatient.setFirstName(firstNameField.getText());
                updatedPatient.setMiddleName(middleNameField.getText());
                updatedPatient.setLastName(lastNameField.getText());
                updatedPatient.setTrn(trnField.getText());
                updatedPatient.setDob(dobField.getText());
                updatedPatient.setGender(genderField.getText());
                updatedPatient.setMaritalStatus(maritalStatusField.getText());
                updatedPatient.setFirstVisitDate(currentPatient.getFirstVisitDate()); // Keep original
                updatedPatient.setNextOfKin(nextOfKinField.getText());
                updatedPatient.setNextOfKinContact(nextOfKinContactField.getText());
                updatedPatient.setEmail(emailField.getText());
                updatedPatient.setContactNumber(Phone.fromString(phoneField.getText()));
                updatedPatient.setAddress(new Address(addressField.getText()));
                updatedPatient.setMedicalHistory(medicalHistoryArea.getText());
                
                PatientManager manager = new PatientManager();
                manager.updatePatient(currentPatientNumber, updatedPatient);
                
                JOptionPane.showMessageDialog(editDialog, "Patient updated successfully!",
                                            "Success", JOptionPane.INFORMATION_MESSAGE);
                editDialog.dispose();
                searchPatient(); // Refresh the view
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(editDialog, "Error updating patient: " + ex.getMessage(),
                                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelButton.addActionListener(e -> editDialog.dispose());
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        editDialog.add(formPanel, BorderLayout.CENTER);
        editDialog.add(buttonPanel, BorderLayout.SOUTH);
        editDialog.setVisible(true);
    }
    
    private void deletePatient() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this patient record?", 
            "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                PatientManager manager = new PatientManager();
                manager.deletePatient(currentPatientNumber);
                
                JOptionPane.showMessageDialog(this, "Patient deleted successfully!",
                                            "Success", JOptionPane.INFORMATION_MESSAGE);
                
                // Clear the display
                patientDisplayArea.setText("");
                searchField.setText("");
                editButton.setEnabled(false);
                deleteButton.setEnabled(false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error deleting patient: " + ex.getMessage(),
                                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
