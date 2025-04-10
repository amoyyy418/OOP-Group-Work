package as;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

class PatientManager {
    private static final String FILE_NAME = "PatientData.dat";
    private static final int RECORD_SIZE = 1024; // Fixed record size
    
    public int addPatient(Patient patient) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            long numRecords = file.length() / RECORD_SIZE;
            int patientNumber = (int) numRecords + 1;
            
            file.seek(numRecords * RECORD_SIZE);
            writePatient(file, patient);
            
            return patientNumber;
        }
    }
    
    public Patient getPatient(int patientNumber) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
            if ((patientNumber - 1) * RECORD_SIZE >= file.length()) {
                return null; // Patient number out of range
            }
            
            file.seek((patientNumber - 1) * RECORD_SIZE);
            return readPatient(file);
        }
    }
    
    public void updatePatient(int patientNumber, Patient patient) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            if ((patientNumber - 1) * RECORD_SIZE >= file.length()) {
                throw new IOException("Patient number out of range");
            }
            
            file.seek((patientNumber - 1) * RECORD_SIZE);
            writePatient(file, patient);
        }
    }
    
    public void deletePatient(int patientNumber) throws IOException {
        // In fixed-length record system, we can just mark as deleted
        // Here we'll just write an empty record
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            if ((patientNumber - 1) * RECORD_SIZE >= file.length()) {
                throw new IOException("Patient number out of range");
            }
            
            file.seek((patientNumber - 1) * RECORD_SIZE);
            file.write(new byte[RECORD_SIZE]); // Write empty record
        }
    }
    
    private void writePatient(RandomAccessFile file, Patient patient) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        
        // Write all fields
        dos.writeUTF(patient.getFirstName());
        dos.writeUTF(patient.getMiddleName());
        dos.writeUTF(patient.getLastName());
        dos.writeUTF(patient.getTrn());
        dos.writeUTF(patient.getDob());
        dos.writeUTF(patient.getGender());
        dos.writeUTF(patient.getMaritalStatus());
        dos.writeUTF(patient.getFirstVisitDate());
        dos.writeUTF(patient.getNextOfKin());
        dos.writeUTF(patient.getNextOfKinContact());
        dos.writeUTF(patient.getEmail());
        dos.writeUTF(patient.getContactNumber().toString());
        dos.writeUTF(patient.getAddress().toString());
        dos.writeUTF(patient.getMedicalHistory());
        
        byte[] record = baos.toByteArray();
        if (record.length > RECORD_SIZE) {
            throw new IOException("Patient record too large");
        }
        
        file.write(record);
        // Pad with zeros if necessary
        if (record.length < RECORD_SIZE) {
            byte[] padding = new byte[RECORD_SIZE - record.length];
            file.write(padding);
        }
    }
    
    private Patient readPatient(RandomAccessFile file) throws IOException {
        byte[] record = new byte[RECORD_SIZE];
        file.readFully(record);
        
        ByteArrayInputStream bais = new ByteArrayInputStream(record);
        DataInputStream dis = new DataInputStream(bais);
        
        Patient patient = new Patient();
        try {
            patient.setFirstName(dis.readUTF());
            patient.setMiddleName(dis.readUTF());
            patient.setLastName(dis.readUTF());
            patient.setTrn(dis.readUTF());
            patient.setDob(dis.readUTF());
            patient.setGender(dis.readUTF());
            patient.setMaritalStatus(dis.readUTF());
            patient.setFirstVisitDate(dis.readUTF());
            patient.setNextOfKin(dis.readUTF());
            patient.setNextOfKinContact(dis.readUTF());
            patient.setEmail(dis.readUTF());
            patient.setContactNumber(Phone.fromString(dis.readUTF()));
            patient.setAddress(new Address(dis.readUTF()));
            patient.setMedicalHistory(dis.readUTF());
            
            return patient;
        } catch (EOFException e) {
            // This indicates a deleted record
            return null;
        }
    }
}