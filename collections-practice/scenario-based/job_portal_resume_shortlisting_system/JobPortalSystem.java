import java.util.Scanner;

public class JobPortalSystem {

    public static void main(String[] args) {
        ResumeShortlistingManager manager = new ResumeShortlistingManager();
        Scanner sc = new Scanner(System.in);

        initializeDemoData(manager);

        while (true) {
            System.out.println("\n========================================");
            System.out.println("  JOB PORTAL RESUME SHORTLISTING SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Add Required Skill");
            System.out.println("2. Add Resume");
            System.out.println("3. Rank Resumes");
            System.out.println("4. Display Shortlisted Resumes");
            System.out.println("5. View Resume Details");
            System.out.println("6. Display All Resumes");
            System.out.println("7. Display Required Skills");
            System.out.println("8. Display Statistics");
            System.out.println("9. Exit");
            System.out.print("\nChoose an option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Exiting.");
                break;
            }
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        addRequiredSkill(manager, sc);
                        break;
                    case 2:
                        addResume(manager, sc);
                        break;
                    case 3:
                        manager.rankResumes();
                        break;
                    case 4:
                        manager.displayShortlistedResumes();
                        break;
                    case 5:
                        viewResumeDetails(manager, sc);
                        break;
                    case 6:
                        manager.displayAllResumes();
                        break;
                    case 7:
                        manager.displayRequiredSkills();
                        break;
                    case 8:
                        manager.displayStatistics();
                        break;
                    case 9:
                        System.out.println("Exiting system.");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (InvalidResumeException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    private static void initializeDemoData(ResumeShortlistingManager manager) {
        manager.addRequiredSkill("Java");
        manager.addRequiredSkill("Spring Boot");
        manager.addRequiredSkill("MySQL");
        manager.addRequiredSkill("REST API");

        try {
            Resume r1 = new Resume("C001", "Amit Kumar", "amit@email.com", 5);
            r1.addSkill("Java");
            r1.addSkill("Spring Boot");
            r1.addSkill("MySQL");
            r1.addSkill("REST API");
            manager.addResume(r1);

            Resume r2 = new Resume("C002", "Priya Singh", "priya@email.com", 3);
            r2.addSkill("Java");
            r2.addSkill("MySQL");
            r2.addSkill("Python");
            manager.addResume(r2);

            Resume r3 = new Resume("C003", "Rahul Shah", "rahul@email.com", 7);
            r3.addSkill("Java");
            r3.addSkill("Spring Boot");
            r3.addSkill("MongoDB");
            manager.addResume(r3);

            Resume r4 = new Resume("C004", "Sneha Patel", "sneha@email.com", 4);
            r4.addSkill("Java");
            r4.addSkill("Spring Boot");
            r4.addSkill("MySQL");
            r4.addSkill("REST API");
            r4.addSkill("Docker");
            manager.addResume(r4);

            Resume r5 = new Resume("C005", "Vikram Reddy", "vikram@email.com", 2);
            r5.addSkill("Python");
            r5.addSkill("Django");
            manager.addResume(r5);

            System.out.println("\nDemo data initialized successfully!");

        } catch (InvalidResumeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addRequiredSkill(ResumeShortlistingManager manager, Scanner sc) {
        System.out.print("\nEnter Skill Name: ");
        String skill = sc.nextLine();
        manager.addRequiredSkill(skill);
    }

    private static void addResume(ResumeShortlistingManager manager, Scanner sc) 
            throws InvalidResumeException {
        System.out.print("\nEnter Candidate ID: ");
        String candidateId = sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Experience (years): ");
        int experience = sc.nextInt();
        sc.nextLine();

        Resume resume = new Resume(candidateId, name, email, experience);

        System.out.print("Number of skills: ");
        int numSkills = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numSkills; i++) {
            System.out.print("Skill " + (i + 1) + ": ");
            String skill = sc.nextLine();
            resume.addSkill(skill);
        }

        manager.addResume(resume);
    }

    private static void viewResumeDetails(ResumeShortlistingManager manager, Scanner sc) {
        System.out.print("\nEnter Candidate ID: ");
        String candidateId = sc.nextLine();
        manager.displayResumeDetails(candidateId);
    }
}
