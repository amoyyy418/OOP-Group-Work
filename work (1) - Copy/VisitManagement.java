import java.io.*;
import java.util.*;
class VisitManagement {
    private List<PatientVisit> visits;
    private static final String FILENAME = "visits.dat";

    public VisitManagement() {
        visits = new ArrayList<>();
    }

    // Updated method to log a visit with new fields
    public void logVisit(String patientId, String patientName, String doctorId, String doctorName, String diagnosis,
                         String purposeOfVisit, String treatmentOrPrescription, String doctorNotes) {
        PatientVisit visit = new PatientVisit(patientId, patientName, doctorId, doctorName, diagnosis,
                                              purposeOfVisit, treatmentOrPrescription, doctorNotes);
        visits.add(visit);
        System.out.println("Visit logged: " + visit);
    }

    public void updateReferral(int visitIndex, String status, String feedback) {
        if (visitIndex >= 0 && visitIndex < visits.size()) {
            visits.get(visitIndex).updateReferral(status, feedback);
            System.out.println("Referral updated: " + visits.get(visitIndex));
        } else {
            System.out.println("Invalid visit index.");
        }
    }

    public void saveVisitsToFile() {
        try (RandomAccessFile file = new RandomAccessFile(FILENAME, "rw")) {
            file.setLength(0);
            file.writeInt(visits.size());
            for (PatientVisit visit : visits) {
                visit.writeToFile(file);
            }
            System.out.println("Visits saved to " + FILENAME);
        } catch (IOException e) {
            System.out.println("Error saving visits: " + e.getMessage());
        }
    }

    public void loadVisitsFromFile() {
        visits.clear();
        File fileObj = new File(FILENAME);
        if (!fileObj.exists()) {
            System.out.println("No previous visits file found. Creating a new one...");
            return;
        }
        try (RandomAccessFile file = new RandomAccessFile(FILENAME, "r")) {
            if (file.length() == 0) {
                System.out.println("No previous visits found.");
                return;
            }
            int count = file.readInt();
            for (int i = 0; i < count; i++) {
                visits.add(PatientVisit.fromFile(file));
            }
            System.out.println("Visits loaded from " + FILENAME);
        } catch (IOException e) {
            System.out.println("Error loading visits: " + e.getMessage());
        }
    }

    public void displayAllVisits() {
        if (visits.isEmpty()) {
            System.out.println("No visits recorded.");
        } else {
            for (int i = 0; i < visits.size(); i++) {
                System.out.println("Index " + i + ": " + visits.get(i));
            }
        }
    }
}
