import java.io.*;
import java.util.*;

class PatientVisit {
    private String patientId;
    private String patientName;
    private String doctorId;
    private String doctorName;
    private String diagnosis;
    private Date timestamp;
    private String referralStatus;
    private String referralFeedback;
    
    // New fields
    private String purposeOfVisit;     // Purpose assigned by the doctor
    private String treatmentOrPrescription;  // Treatment or prescription given by the doctor
    private String doctorNotes;        // Doctor's notes/observations

    // Constructor updated to handle new fields
    public PatientVisit(String patientId, String patientName, String doctorId, String doctorName, String diagnosis,
                        String purposeOfVisit, String treatmentOrPrescription, String doctorNotes) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.timestamp = new Date();
        this.referralStatus = "Pending";
        this.referralFeedback = "";
        this.purposeOfVisit = purposeOfVisit;
        this.treatmentOrPrescription = treatmentOrPrescription;
        this.doctorNotes = doctorNotes;
    }

    // Updated method to include new fields for updating referral and doctor's notes
    public void updateReferral(String status, String feedback) {
        this.referralStatus = status;
        this.referralFeedback = feedback;
    }

    // Updated method to write all fields to the file
    public void writeToFile(RandomAccessFile file) throws IOException {
        file.writeUTF(patientId);
        file.writeUTF(patientName);
        file.writeUTF(doctorId);
        file.writeUTF(doctorName);
        file.writeUTF(diagnosis);
        file.writeLong(timestamp.getTime());
        file.writeUTF(referralStatus);
        file.writeUTF(referralFeedback);
        file.writeUTF(purposeOfVisit);         // Writing new field
        file.writeUTF(treatmentOrPrescription);  // Writing new field
        file.writeUTF(doctorNotes);            // Writing new field
    }

    // Updated method to read all fields from the file
    public static PatientVisit fromFile(RandomAccessFile file) throws IOException {
        String patientId = file.readUTF();
        String patientName = file.readUTF();
        String doctorId = file.readUTF();
        String doctorName = file.readUTF();
        String diagnosis = file.readUTF();
        long timestamp = file.readLong();
        String referralStatus = file.readUTF();
        String referralFeedback = file.readUTF();
        String purposeOfVisit = file.readUTF();  // Reading new field
        String treatmentOrPrescription = file.readUTF();  // Reading new field
        String doctorNotes = file.readUTF();  // Reading new field

        PatientVisit visit = new PatientVisit(patientId, patientName, doctorId, doctorName, diagnosis,
                                              purposeOfVisit, treatmentOrPrescription, doctorNotes);
        visit.timestamp = new Date(timestamp);
        visit.referralStatus = referralStatus;
        visit.referralFeedback = referralFeedback;

        return visit;
    }

    @Override
    public String toString() {
        return patientId + "," + patientName + "," + doctorId + "," + doctorName + "," +
               diagnosis + "," + timestamp + "," + referralStatus + "," + referralFeedback + "," +
               purposeOfVisit + "," + treatmentOrPrescription + "," + doctorNotes;
    }
}




