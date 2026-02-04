
import java.util.*;

public class OnlineClassroomAttendanceTracker {

    static void addDummySessions(AttendanceTracker tracker) {
        tracker.createSession(new Session("S001", "Java Basics", "2026-02-01"));
        tracker.createSession(new Session("S002", "OOP Concepts", "2026-02-02"));
        tracker.createSession(new Session("S003", "Collections Framework", "2026-02-03"));
        tracker.createSession(new Session("S004", "Exception Handling", "2026-02-04"));
    }

    static void addDummyAttendance(AttendanceTracker tracker) {
        try {
            tracker.markAttendance("S001", "STU001");
            tracker.markAttendance("S001", "STU002");
            tracker.markAttendance("S001", "STU003");
            tracker.markAttendance("S002", "STU001");
            tracker.markAttendance("S002", "STU004");
            tracker.markAttendance("S003", "STU002");
            tracker.markAttendance("S003", "STU003");
            tracker.markAttendance("S003", "STU005");
        } catch (DuplicateAttendanceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AttendanceTracker tracker = new AttendanceTracker();

        addDummySessions(tracker);
        System.out.println("\nAdding initial attendance...\n");
        addDummyAttendance(tracker);

        while (true) {
            System.out.println("\n===== Online Classroom Attendance Tracker =====");
            System.out.println("1. Create Session");
            System.out.println("2. Mark Attendance");
            System.out.println("3. Remove Attendance");
            System.out.println("4. Display Attendance for Session");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Session ID: ");
                        String sessionId = sc.nextLine();
                        System.out.print("Enter Topic: ");
                        String topic = sc.nextLine();
                        System.out.print("Enter Date (YYYY-MM-DD): ");
                        String date = sc.nextLine();
                        tracker.createSession(new Session(sessionId, topic, date));
                        break;

                    case 2:
                        System.out.print("Enter Session ID: ");
                        String sessId = sc.nextLine();
                        System.out.print("Enter Student ID: ");
                        String studentId = sc.nextLine();
                        tracker.markAttendance(sessId, studentId);
                        break;

                    case 3:
                        System.out.print("Enter Session ID: ");
                        String remSessId = sc.nextLine();
                        System.out.print("Enter Student ID: ");
                        String remStudentId = sc.nextLine();
                        tracker.removeAttendance(remSessId, remStudentId);
                        break;

                    case 4:
                        System.out.print("Enter Session ID: ");
                        String dispSessId = sc.nextLine();
                        tracker.displayAttendance(dispSessId);
                        break;

                    case 5:
                        System.out.println("Exiting... Thank you!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (DuplicateAttendanceException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
