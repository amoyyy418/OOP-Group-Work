package as;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    
    public MainFrame() {
        setTitle("Patient Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Add Patient", new AddPatientPanel());
        tabbedPane.addTab("View/Edit Patient", new ViewEditPatientPanel());
        
        add(tabbedPane);
    }
}

class AddPatientPanel extends JPanel {
    private JTextField firstNameField, middleNameField, lastNameField, trnField;
    private JTextField dobField, genderField, maritalStatusField;
    private JTextField firstVisitField, nextOfKinField, nextOfKinContactField;
    private JTextField emailField, phoneField, addressField;
    private JTextArea medicalHistoryArea;
    private JButton saveButton, clearButton;
    
    public AddPatientPanel() {
        setLayout(new BorderLayout());
        
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Personal Information
        firstNameField = new JTextField();
        middleNameField = new JTextField();
        lastNameField = new JTextField();
        trnField = new JTextField();
        dobField = new JTextField();
        genderField = new JTextField();
        maritalStatusField = new JTextField();
        
        // Contact Information
        firstVisitField = new JTextField(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        firstVisitField.setEditable(false);
        nextOfKinField = new JTextField();
        nextOfKinContactField = new JTextField();
        emailField = new JTextField();
        phoneField = new JTextField();
        addressField = new JTextField();
        
        // Medical History
        medicalHistoryArea = new JTextArea(5, 20);
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
        formPanel.add(new JLabel("First Visit Date:"));
        formPanel.add(firstVisitField);
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
        saveButton = new JButton("Save Patient");
        clearButton = new JButton("Clear Form");
        
        saveButton.addActionListener(e -> savePatient());
        clearButton.addActionListener(e -> clearForm());
        
        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);
        
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void savePatient() {
        try {
            Patient patient = new Patient();
            patient.setFirstName(firstNameField.getText());
            patient.setMiddleName(middleNameField.getText());
            patient.setLastName(lastNameField.getText());
            patient.setTrn(trnField.getText());
            patient.setDob(dobField.getText());
            patient.setGender(genderField.getText());
            patient.setMaritalStatus(maritalStatusField.getText());
            patient.setFirstVisitDate(firstVisitField.getText());
            patient.setNextOfKin(nextOfKinField.getText());
            patient.setNextOfKinContact(nextOfKinContactField.getText());
            patient.setEmail(emailField.getText());
            patient.setContactNumber(Phone.fromString(phoneField.getText()));
            patient.setAddress(new Address(addressField.getText()));
            patient.setMedicalHistory(medicalHistoryArea.getText());
            
            PatientManager manager = new PatientManager();
            int patientNumber = manager.addPatient(patient);
            
            JOptionPane.showMessageDialog(this, "Patient saved successfully!\nPatient Number: " + patientNumber,
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving patient: " + ex.getMessage(),
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearForm() {
        firstNameField.setText("");
        middleNameField.setText("");
        lastNameField.setText("");
        trnField.setText("");
        dobField.setText("");
        genderField.setText("");
        maritalStatusField.setText("");
        nextOfKinField.setText("");
        nextOfKinContactField.setText("");
        emailField.setText("");
        phoneField.setText("");
        addressField.setText("");
        medicalHistoryArea.setText("");
    }
}