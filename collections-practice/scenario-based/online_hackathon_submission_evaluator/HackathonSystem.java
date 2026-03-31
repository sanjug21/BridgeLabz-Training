import java.util.Scanner;

public class HackathonSystem {

    public static void main(String[] args) {
        HackathonEvaluator evaluator = new HackathonEvaluator(1000);
        Scanner sc = new Scanner(System.in);

        initializeDemoData(evaluator);

        while (true) {
            System.out.println("\n========================================");
            System.out.println("  ONLINE HACKATHON SUBMISSION EVALUATOR");
            System.out.println("========================================");
            System.out.println("1. Add Participant");
            System.out.println("2. Add Test Case Result");
            System.out.println("3. Display Leaderboard");
            System.out.println("4. View Participant Details");
            System.out.println("5. View All Participants");
            System.out.println("6. Exit");
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
                        addParticipant(evaluator, sc);
                        break;
                    case 2:
                        addTestCaseResult(evaluator, sc);
                        break;
                    case 3:
                        evaluator.displayLeaderboard();
                        break;
                    case 4:
                        viewParticipantDetails(evaluator, sc);
                        break;
                    case 5:
                        evaluator.displayAllParticipants();
                        break;
                    case 6:
                        System.out.println("Exiting system.");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (LateSubmissionException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    private static void initializeDemoData(HackathonEvaluator evaluator) {
        try {
            Participant p1 = new Participant("P001", "Alice", 500);
            p1.addTestCaseResult("Q1", true);
            p1.addTestCaseResult("Q2", true);
            p1.addTestCaseResult("Q3", false);
            evaluator.addParticipant(p1);

            Participant p2 = new Participant("P002", "Bob", 700);
            p2.addTestCaseResult("Q1", true);
            p2.addTestCaseResult("Q2", false);
            p2.addTestCaseResult("Q3", true);
            evaluator.addParticipant(p2);

            Participant p3 = new Participant("P003", "Charlie", 600);
            p3.addTestCaseResult("Q1", true);
            p3.addTestCaseResult("Q2", true);
            p3.addTestCaseResult("Q3", true);
            evaluator.addParticipant(p3);

            Participant p4 = new Participant("P004", "Diana", 800);
            p4.addTestCaseResult("Q1", true);
            p4.addTestCaseResult("Q2", true);
            p4.addTestCaseResult("Q3", false);
            evaluator.addParticipant(p4);

            System.out.println("\nDemo data initialized successfully!");

        } catch (LateSubmissionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addParticipant(HackathonEvaluator evaluator, Scanner sc) 
            throws LateSubmissionException {
        System.out.print("\nEnter Participant ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Submission Time: ");
        long submissionTime = sc.nextLong();
        sc.nextLine();

        Participant participant = new Participant(id, name, submissionTime);
        evaluator.addParticipant(participant);
    }

    private static void addTestCaseResult(HackathonEvaluator evaluator, Scanner sc) {
        System.out.print("\nEnter Participant ID: ");
        String participantId = sc.nextLine();

        System.out.print("Enter Question ID: ");
        String questionId = sc.nextLine();

        System.out.print("Test Case Passed (true/false): ");
        boolean passed = sc.nextBoolean();
        sc.nextLine();

        evaluator.displayAllParticipants();
    }

    private static void viewParticipantDetails(HackathonEvaluator evaluator, Scanner sc) {
        System.out.print("\nEnter Participant ID: ");
        String participantId = sc.nextLine();
        evaluator.displayParticipantDetails(participantId);
    }
}
