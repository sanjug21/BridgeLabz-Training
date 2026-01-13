package online_examination_system;

import java.util.HashMap;
import java.util.Map;

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        System.out.println("=== Online Examination System ===\n");

        // 1. Create Students
        Student s1 = new Student("Sanju", "S001");
        Student s2 = new Student("Shubham", "S002");

        // 2. Create Exam (Objective Type)
        // Polymorphism: Using ObjectiveEvaluation strategy
        Exam javaExam = new Exam("Java Basics", new ObjectiveEvaluation(), 2000); // 2 seconds duration for demo

        // 3. Add Questions
        javaExam.addQuestion(new Question(1, "What is the size of int in Java?", "4 bytes", 10));
        javaExam.addQuestion(new Question(2, "Is Java object-oriented?", "Yes", 10));

        // 4. Enroll Students
        javaExam.enrollStudent(s1);
        javaExam.enrollStudent(s2);

        // 5. Start Exam
        javaExam.startExam();

        // 6. Submit Answers
        // Sanju submits correct answers within time
        Map<Integer, String> sanjuAnswers = new HashMap<>();
        sanjuAnswers.put(1, "4 bytes");
        sanjuAnswers.put(2, "Yes");
        javaExam.submitAnswers(s1, sanjuAnswers);

        // Shubham waits too long (Simulating time expiry)
        try {
            Thread.sleep(3000); // Wait 3 seconds (Exam duration is 2 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Map<Integer, String> ShubhamAnswers = new HashMap<>();
        ShubhamAnswers.put(1, "4 bytes");
        ShubhamAnswers.put(2, "No");
        javaExam.submitAnswers(s2, ShubhamAnswers);
    }
}