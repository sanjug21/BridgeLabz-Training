
import java.time.LocalDate;
import java.util.Scanner;

public class OnlineCourseEnrollmentSystem {
    public static void main(String[] args) {
        EnrollmentManager manager = new EnrollmentManager();
        Scanner scanner = new Scanner(System.in);

        // Initialize with demo data
        initializeDemoData(manager);

        while (true) {
            System.out.println("\n========== ONLINE COURSE ENROLLMENT MANAGER ==========");
            System.out.println("1. Add New Enrollment");
            System.out.println("2. Display All Enrollments");
            System.out.println("3. Filter Enrollments by Course");
            System.out.println("4. Filter Enrollments by Category");
            System.out.println("5. Group Enrollments by Course Name");
            System.out.println("6. Count Enrollments by Category");
            System.out.println("7. Sort Enrollments by Enrollment Date");
            System.out.println("8. Display Summary Statistics");
            System.out.println("9. Exit");
            System.out.println("======================================================");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewEnrollment(manager, scanner);
                    break;

                case 2:
                    manager.displayAllEnrollments();
                    break;

                case 3:
                    filterByCourse(manager, scanner);
                    break;

                case 4:
                    filterByCategory(manager, scanner);
                    break;

                case 5:
                    manager.groupByCourse();
                    break;

                case 6:
                    manager.countEnrollmentsByCategory();
                    break;

                case 7:
                    manager.sortByEnrollmentDate();
                    break;

                case 8:
                    manager.displaySummaryStatistics();
                    break;

                case 9:
                    System.out.println("Exiting Online Course Enrollment Manager. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Initialize demo data
    private static void initializeDemoData(EnrollmentManager manager) {
        manager.addEnrollment(new Enrollment("Alice Johnson", "Java Programming", "Programming", LocalDate.of(2026, 1, 15)));
        manager.addEnrollment(new Enrollment("Bob Smith", "Data Structures and Algorithms", "Programming", LocalDate.of(2026, 1, 20)));
        manager.addEnrollment(new Enrollment("Charlie Brown", "Web Development with React", "Web Development", LocalDate.of(2026, 1, 18)));
        manager.addEnrollment(new Enrollment("Diana Prince", "Machine Learning Fundamentals", "Data Science", LocalDate.of(2026, 1, 22)));
        manager.addEnrollment(new Enrollment("Eve Wilson", "Java Programming", "Programming", LocalDate.of(2026, 1, 25)));
        manager.addEnrollment(new Enrollment("Frank Miller", "Python for Data Science", "Data Science", LocalDate.of(2026, 1, 28)));
        manager.addEnrollment(new Enrollment("Grace Lee", "Web Development with React", "Web Development", LocalDate.of(2026, 2, 1)));
        manager.addEnrollment(new Enrollment("Henry Taylor", "Cloud Computing with AWS", "Cloud Computing", LocalDate.of(2026, 2, 3)));
        manager.addEnrollment(new Enrollment("Iris Chen", "Data Structures and Algorithms", "Programming", LocalDate.of(2026, 2, 5)));
        manager.addEnrollment(new Enrollment("Jack Anderson", "Machine Learning Fundamentals", "Data Science", LocalDate.of(2026, 2, 8)));
        manager.addEnrollment(new Enrollment("Kate Martinez", "DevOps Essentials", "DevOps", LocalDate.of(2026, 2, 10)));
        manager.addEnrollment(new Enrollment("Liam Davis", "Java Programming", "Programming", LocalDate.of(2026, 2, 12)));
        
        System.out.println("\nDemo data initialized with 12 enrollments.\n");
    }

    // Add new enrollment
    private static void addNewEnrollment(EnrollmentManager manager, Scanner scanner) {
        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();

        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();

        System.out.print("Enter Course Category: ");
        String courseCategory = scanner.nextLine();

        System.out.print("Enter Enrollment Date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        LocalDate enrollmentDate = LocalDate.parse(dateStr);

        Enrollment enrollment = new Enrollment(studentName, courseName, courseCategory, enrollmentDate);
        manager.addEnrollment(enrollment);
    }

    // Filter by course
    private static void filterByCourse(EnrollmentManager manager, Scanner scanner) {
        System.out.println("\nAvailable Courses:");
        manager.getAllCourseNames().forEach(course -> System.out.println("  - " + course));
        
        System.out.print("\nEnter Course Name: ");
        String courseName = scanner.nextLine();
        manager.filterByCourse(courseName);
    }

    // Filter by category
    private static void filterByCategory(EnrollmentManager manager, Scanner scanner) {
        System.out.println("\nAvailable Categories:");
        manager.getAllCategories().forEach(category -> System.out.println("  - " + category));
        
        System.out.print("\nEnter Course Category: ");
        String category = scanner.nextLine();
        manager.filterByCategory(category);
    }
}
