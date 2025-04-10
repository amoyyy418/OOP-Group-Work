package as;

class Patient {
    private String firstName;
    private String middleName;
    private String lastName;
    private String trn;
    private String dob;
    private String gender;
    private String maritalStatus;
    private String firstVisitDate;
    private String nextOfKin;
    private String nextOfKinContact;
    private String email;
    private Phone contactNumber;
    private Address address;
    private String medicalHistory;
    
    // Getters and setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getFullName() { 
        return firstName + (middleName.isEmpty() ? "" : " " + middleName) + " " + lastName; 
    }
    
    public String getTrn() { return trn; }
    public void setTrn(String trn) { this.trn = trn; }
    
    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    
    public String getMaritalStatus() { return maritalStatus; }
    public void setMaritalStatus(String maritalStatus) { this.maritalStatus = maritalStatus; }
    
    public String getFirstVisitDate() { return firstVisitDate; }
    public void setFirstVisitDate(String firstVisitDate) { this.firstVisitDate = firstVisitDate; }
    
    public String getNextOfKin() { return nextOfKin; }
    public void setNextOfKin(String nextOfKin) { this.nextOfKin = nextOfKin; }
    
    public String getNextOfKinContact() { return nextOfKinContact; }
    public void setNextOfKinContact(String nextOfKinContact) { this.nextOfKinContact = nextOfKinContact; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public Phone getContactNumber() { return contactNumber; }
    public void setContactNumber(Phone contactNumber) { this.contactNumber = contactNumber; }
    
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    
    public String getMedicalHistory() { return medicalHistory; }
    public void setMedicalHistory(String medicalHistory) { this.medicalHistory = medicalHistory; }
}

