import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

// Phone class
class Phone {
    private int areaCode;
    private int exchange;
    private int lineNumber;

    public Phone(int areaCode, int exchange, int lineNumber) {
        this.areaCode = areaCode;
        this.exchange = exchange;
        this.lineNumber = lineNumber;
    }

    public String getFullNumber() {
        return String.format("(%d) %d-%d", areaCode, exchange, lineNumber);
    }
}

// Address class
class Address {
    private String streetNumber;
    private String streetName;
    private String parish;
    private String country;

    public Address(String streetNumber, String streetName, String parish, String country) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.parish = parish;
        this.country = country;
    }

    public String getFullAddress() {
        return streetNumber + " " + streetName + ", " + parish + ", " + country;
    }
}

public class Patient {
    private static final int RECORD_SIZE = 215;
    private String firstName;
    private String middleName;
    private String lastName;
    private String trn;
    private String dob;
    private String gender;
    private String maritalStatus;
    private int patientNumber;
    private String firstVisitDate;
    private String nextOfKin;
    private String nextOfKinContact;
    private String email;
    private String contactNumber;
    private String medicalHistory;
    private boolean deleted = false;
    private Address address;
    private Phone phone;

    public Patient() {}

    public Patient(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getTrn() { return trn; }
    public void setTrn(String trn) { this.trn = trn; }
    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getMaritalStatus() { return maritalStatus; }
    public void setMaritalStatus(String maritalStatus) { this.maritalStatus = maritalStatus; }
    public int getPatientNumber() { return patientNumber; }
    public void setPatientNumber(int patientNumber) { this.patientNumber = patientNumber; }
    public String getFirstVisitDate() { return firstVisitDate; }
    public void setFirstVisitDate(String firstVisitDate) { this.firstVisitDate = firstVisitDate; }
    public String getNextOfKin() { return nextOfKin; }
    public void setNextOfKin(String nextOfKin) { this.nextOfKin = nextOfKin; }
    public String getNextOfKinContact() { return nextOfKinContact; }
    public void setNextOfKinContact(String nextOfKinContact) { this.nextOfKinContact = nextOfKinContact; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public String getMedicalHistory() { return medicalHistory; }
    public void setMedicalHistory(String medicalHistory) { this.medicalHistory = medicalHistory; }
    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }
    
    public void inputData() {
        firstName = JOptionPane.showInputDialog("Enter First Name:");
        middleName = JOptionPane.showInputDialog("Enter Middle Name (Optional):");
        lastName = JOptionPane.showInputDialog("Enter Last Name:");
        trn = JOptionPane.showInputDialog("Enter TRN:");
        dob = JOptionPane.showInputDialog("Enter Date of Birth (YYYY-MM-DD):");
        gender = JOptionPane.showInputDialog("Enter Gender:");
        maritalStatus = JOptionPane.showInputDialog("Enter Marital Status:");
        firstVisitDate = JOptionPane.showInputDialog("Enter Date of First Visit (YYYY-MM-DD):");
        nextOfKin = JOptionPane.showInputDialog("Enter Next of Kin Name:");
        nextOfKinContact = JOptionPane.showInputDialog("Enter Next of Kin Contact Number:");
        email = JOptionPane.showInputDialog("Enter Email Address:");
        contactNumber = JOptionPane.showInputDialog("Enter Contact Number:");
        medicalHistory = JOptionPane.showInputDialog("Enter Medical History:");

        // Input address
        String streetNumber = JOptionPane.showInputDialog("Enter Street Number:");
        String streetName = JOptionPane.showInputDialog("Enter Street Name:");
        String parish = JOptionPane.showInputDialog("Enter Parish:");
        String country = JOptionPane.showInputDialog("Enter Country:");
        address = new Address(streetNumber, streetName, parish, country);

        // Input phone number
        int areaCode = Integer.parseInt(JOptionPane.showInputDialog("Enter Area Code:"));
        int exchange = Integer.parseInt(JOptionPane.showInputDialog("Enter Exchange Code:"));
        int lineNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Line Number:"));
        phone = new Phone(areaCode, exchange, lineNumber);
    }

    public void editData() {
        String[] options = {"First Name", "Last Name","TRN", "Date of Birth","Gender", "Marital Status",
                 "Email", "Contact", "Next of Kin", "Next of Kin's Contact","Medical History"};
        String choice = (String) JOptionPane.showInputDialog(null, "Select field to edit:",
                         "Edit Patient", JOptionPane.QUESTION_MESSAGE, null,
                         options, options[0]);
        switch(choice) {
            case "First Name":
                firstName = JOptionPane.showInputDialog("Enter new First Name:");
                break;
            case "Last Name":
                lastName = JOptionPane.showInputDialog("Enter new Last Name:");
                break;
            case "TRN":
                trn = JOptionPane.showInputDialog("Enter new TRN:");
                break;
            case "Date of Birth":
                dob = JOptionPane.showInputDialog("Enter new Date of Birth (YYYY-MM-DD):");
                break;
            case "Gender":
                gender = JOptionPane.showInputDialog("Enter correct Gender:");
                break;
            case "Marital Status":
                maritalStatus = JOptionPane.showInputDialog("Enter new Marital Status:");
                break;
            case "Email":
                email = JOptionPane.showInputDialog("Enter Email Address:");
                break;
            case "Contact":
                contactNumber = JOptionPane.showInputDialog("Enter Contact Number:");
                break;
            case "Next of Kin":
                nextOfKin = JOptionPane.showInputDialog("Enter Next of Kin Name:");
                break;
            case "Next of Kin's Contact":
                nextOfKinContact = JOptionPane.showInputDialog("Enter Next of Kin's Contact:");
                break;
            case "Medical History":
                medicalHistory = JOptionPane.showInputDialog("Enter new Medical History:");
                break;
            default:
                System.out.println("Invalid Option");
        }
    }
    
    //Overload
    public void editData(boolean editAll) {
	  if (editAll) {
	    inputData();
	   } 
	 }

    // Write this patient record to a RandomAccessFile as a fixed-length record.
	    public void writeToFile(RandomAccessFile raf) throws IOException {
	        // Build a fixed-length string record
	        StringBuilder sb = new StringBuilder();
	        sb.append(String.format("%-10d", patientNumber));
	        sb.append(String.format("%-15s", firstName));
	        sb.append(String.format("%-15s", (middleName == null ? "" : middleName)));
	        sb.append(String.format("%-15s", lastName));
	        sb.append(String.format("%-15s", trn));
	        sb.append(String.format("%-12s", dob));
	        sb.append(String.format("%-12s", firstVisitDate));
	        sb.append(String.format("%-8s", gender));
	        sb.append(String.format("%-12s", maritalStatus));
	        sb.append(String.format("%-15s", nextOfKin));
	        sb.append(String.format("%-15s", nextOfKinContact));
	        sb.append(String.format("%-25s", email));
	        sb.append(String.format("%-15s", contactNumber));
	        sb.append(String.format("%-30s", medicalHistory));
	        sb.append(deleted ? "1" : "0");  // mark deletion status
	        
	        String record = sb.toString();
	        // Ensure the record fits the fixed length
	        if(record.length() > RECORD_SIZE) {
	            record = record.substring(0, RECORD_SIZE);
	        } else {
	            while(record.length() < RECORD_SIZE) {
	                record += " ";
	            }
	        }
	        raf.writeBytes(record);
	    }
	    
	    // Read a patient record from the RandomAccessFile.
	    public void readFromFile(RandomAccessFile raf) throws IOException {
	        byte[] buffer = new byte[RECORD_SIZE];
	        raf.read(buffer);
	        String record = new String(buffer);
	        try {
	            patientNumber = Integer.parseInt(record.substring(0, 10).trim());
	        } catch(Exception e) {
	            patientNumber = 0;
	        }
	        firstName      = record.substring(10, 25).trim();
	        middleName     = record.substring(25, 40).trim();
	        lastName       = record.substring(40, 55).trim();
	        trn            = record.substring(55, 70).trim();
	        dob            = record.substring(70, 82).trim();
	        firstVisitDate = record.substring(82, 94).trim();
	        gender         = record.substring(94, 102).trim();
	        maritalStatus  = record.substring(102, 114).trim();
	        nextOfKin      = record.substring(114, 129).trim();
	        nextOfKinContact = record.substring(129, 144).trim();
	        email          = record.substring(144, 169).trim();
	        contactNumber  = record.substring(169, 184).trim();
	        medicalHistory = record.substring(184, 214).trim();
	        String delFlag = record.substring(214, 215).trim();
	        deleted = delFlag.equals("1");
	    }

       // Overriding toString method for displaying patient details
	    @Override
	    public String toString() {
	        return "Patient Number: " + patientNumber + "\n" +
	               "Name: " + firstName + " " + middleName + " " + lastName + "\n" +
	               "TRN: " + trn + "\n" +
	               "DOB: " + dob + "\n" +
	               "First Visit: " + firstVisitDate + "\n" +
	               "Gender: " + gender + "\n" +
	               "Marital Status: " + maritalStatus + "\n" +
	               "Next of Kin: " + nextOfKin + "\n" +
	               "Next of Kin Contact: " + nextOfKinContact + "\n" +
	               "Email: " + email + "\n" +
	               "Contact Number: " + contactNumber + "\n" +
	               "Medical History: " + medicalHistory;
	    }
}