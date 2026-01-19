package online_exam_review_system;

import java.util.Scanner;

public class ExamSystemApp {

    public static void main(String[] args) {
        ExamProctor exam = new ExamProctor();
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Exam Started ---\n");
        exam.displayQuestionDatabase();

        boolean exit=false;
        while(!exit){
            System.out.println("Pick a choice:");
            System.out.println("1. Navigate to a question");
            System.out.println("2. Go back");
            System.out.println("3. Submit answer");
            System.out.println("5. Display all questions");
            System.out.println("6. Exit");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.println("Enter question ID:");
                    int questionId = scanner.nextInt();
                    exam.navigateToQuestion(questionId);
                    break;
                case 2:
                    exam.goBack();
                    break;
                case 3:
                    System.out.println("Enter question ID:");
                    int qId = scanner.nextInt();
                    System.out.println("Enter your answer:");
                    String answer = scanner.next();
                    exam.submitAnswer(qId, answer);
                    break;
                case 5:
                    exam.displayQuestionDatabase();
                    break;
                case 6:
                    exit=true;
                    break;
            
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            
        }

        

        int finalScore = exam.calculateScore();
        System.out.println("\nTotal Score: " + finalScore);

        scanner.close();
    }
}
