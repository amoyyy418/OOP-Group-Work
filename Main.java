import java.util.Scanner;

  
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Select a module:");
        System.out.println("1. Login and Authentication");
        System.out.println("2. Staff Records Management");
        System.out.println("3. Visit and Referral Management");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            authenticationModule();
        } else if (choice == 2) {
            staffRecordsModule();
        } else if (choice == 3){
            visitReferralModule();
        } else {
            System.out.println("Invalid choice. Exiting...");
        }

        scanner.close();
    }

    // Method for user authentication module
    private static void authenticationModule() {
        Authentication auth = new Authentication();
        
        // Adding dummy users
        Role r1 = new Role(1, "Doctor", "Senior");
        Role r2 = new Role(2, "Nurse", "Mid-level");
        Role r3 = new Role(3, "Admin", "Junior");
        Role r4 = new Role(4, "Dietitian", "Senior");
        Role r5 = new Role(5, "Psychologist", "Senior");
        Role r6 = new Role(6, "Doctor", "Mid-level");
        Role r7 = new Role(7, "Receptionist", "Junior");
        Role r8 = new Role(8, "Nurse", "Senior");
        Role r9 = new Role(9, "Patient", "N/A");
        Role r10 = new Role(10, "Admin", "Senior");

        User User1 = new User(10001, "john_doe", "jDoe123", r1);
        User User2 = new User(10002, "jane_smith", "jSmith123", r2);
        User User3 = new User(10003, "alice_johnson", "aJohnson123", r3);
        User User4 = new User(10004, "michael_brown", "mBrown123", r4);
        User User5 = new User(10005, "sarah_white", "sWhite123", r5);
        User User6 = new User(10006, "david_martinez", "dMartinez123", r6);
        User User7 = new User(10007, "emily_clark", "eClark123", r7);
        User User8 = new User(10008, "robert_davis", "rDavis123", r8); 
        User User9 = new User(10009, "olivia_miller", "oMiller123", r9);
        Admin User10 = new Admin(10010, "daniel_wilson", "dWilson123", r10, "Full Access");

        auth.registerUser(User1);
        auth.registerUser(User2);
        auth.registerUser(User3);
        auth.registerUser(User4);
        auth.registerUser(User5);
        auth.registerUser(User6);
        auth.registerUser(User7);
        auth.registerUser(User8);
        auth.registerUser(User9);
        auth.registerUser(User10);

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Register User");
        System.out.println("2. Login");
        System.out.println("3. Display File Contents");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (option == 1) {
            System.out.print("Enter user number (1 to 99999): ");
            int userNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (userNumber < 0 || userNumber > 99999) {
                System.out.println("Invalid user number. It should be between 1 and 99999.");
                return;
            }

            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            System.out.print("Enter your job role: ");
            String roleName = scanner.nextLine();
            System.out.print("Enter role level (e.g., Junior, Senior): ");
            String roleLevel = scanner.nextLine();

            Role jobRole = new Role(userNumber, roleName, roleLevel);

            try {
                User newUser = new User(userNumber, username, password, jobRole);
                auth.registerUser(newUser);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        } else if (option == 2) {
            int attempts = 0;
            boolean loggedIn = false;

            while (attempts < 3 && !loggedIn) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                loggedIn = auth.login(username, password);
                if (!loggedIn) {
                    System.out.println("Failed to log in. Attempt " + (attempts + 1) + " of 3.");
                    attempts++;
                    if (attempts >= 3) {
                        System.out.println("Account locked due to too many failed attempts. Contact Admin.");
                    }
                }
            }

            if (loggedIn) {
                System.out.println("Successfully logged in!");
            }

        } else if (option == 3) {
            auth.displayFileContents();
        } else {
            System.out.println("Invalid option.");
        }

        scanner.close();
    }

    // Method for staff records management module
    private static void staffRecordsModule() {
        StaffRecordsManagement srm = new StaffRecordsManagement();
        Scanner scanner = new Scanner(System.in);

        Employee emp1 = new Employee(101, "John", "A", "Doe", "1990-05-15", "2020-06-01", "Cardiology",
                "Male", "123456789", "Doctor", "Dr. Smith", "Doctor");
        emp1.addCertification("Cardiology Specialist");

        Employee emp2 = new Employee(102, "Jane", null, "Smith", "1985-08-22", "2018-09-10", "Nursing",
                "Female", "987654321", "Nurse", "Dr. John", "Nurse");
        emp2.addCertification("Pediatric Nursing");

        Employee emp3 = new Employee(103, "Alice", "B", "Johnson", "1992-07-10", "2021-03-15", "Administration", 
                "Female", "112233445", "Admin", "Mr. Roberts", "Admin");
        emp3.addCertification("HR Management");

        Employee emp4 = new Employee(104, "Michael", "C", "Brown", "1980-12-05", "2015-01-20", "Nutrition", 
                "Male", "223344556", "Dietitian", "Dr. Green", "Dietitian");
        emp4.addCertification("Clinical Nutritionist");

        Employee emp5 = new Employee(105, "Sarah", "D", "White", "1995-04-18", "2019-07-30", "Psychology", 
                "Female", "334455667", "Psychologist", "Dr. Adams", "Psychologist");
        emp5.addCertification("Cognitive Behavioral Therapy");

        srm.addEmployee(emp1);
        srm.addEmployee(emp2);
        srm.addEmployee(emp3);
        srm.addEmployee(emp4);
        srm.addEmployee(emp5);

        System.out.print("Enter your role to access staff records: ");
        String userRole = scanner.nextLine();
        srm.viewEmployees(userRole);

        scanner.close();
    }
    
    public static void visitReferralModule(){
        VisitManagement visitManagement = new VisitManagement();
        Scanner scanner = new Scanner(System.in);
        visitManagement.loadVisitsFromFile();
        boolean continueProgram = true;
        while (continueProgram) {
            System.out.println("\nEnter 'log' to log a visit, 'update' to update a referral, 'save' to save visits, 'load' to load visits, 'display' to display all visits, or 'exit' to quit:");
            String command = scanner.nextLine().trim().toLowerCase();
            switch (command) {
                case "log":
                    System.out.print("Enter Patient ID: ");
                    String patientId = scanner.nextLine();
                    System.out.print("Enter Patient Name: ");
                    String patientName = scanner.nextLine();
                    System.out.print("Enter Doctor ID: ");
                    String doctorId = scanner.nextLine();
                    System.out.print("Enter Doctor Name: ");
                    String doctorName = scanner.nextLine();
                    System.out.print("Enter Diagnosis: ");
                    String diagnosis = scanner.nextLine();
                    System.out.print("Enter Purpose of Visit: ");
                    String purposeOfVisit = scanner.nextLine();
                    System.out.print("Enter Treatment/Prescription: ");
                    String treatmentOrPrescription = scanner.nextLine();
                    System.out.print("Enter Doctor's Notes/Observations: ");
                    String doctorNotes = scanner.nextLine();
                    visitManagement.logVisit(patientId, patientName, doctorId, doctorName, diagnosis,
                                              purposeOfVisit, treatmentOrPrescription, doctorNotes);
                    break;
                case "update":
                    visitManagement.displayAllVisits();
                    System.out.print("Enter Visit Index to Update: ");
                    int visitIndex = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter New Referral Status: ");
                    String status = scanner.nextLine();
                    System.out.print("Enter Referral Feedback: ");
                    String feedback = scanner.nextLine();
                    visitManagement.updateReferral(visitIndex, status, feedback);
                    break;
                case "save":
                    visitManagement.saveVisitsToFile();
                    break;
                case "load":
                    visitManagement.loadVisitsFromFile();
                    break;
                case "display":
                    visitManagement.displayAllVisits();
                    break;
                case "exit":
                    System.out.println("Exiting program.");
                    continueProgram = false;
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
        }
        scanner.close();
    }
    }

