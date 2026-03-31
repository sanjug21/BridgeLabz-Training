package online_examination_system;

import java.util.*;

// OOP: Exam class managing questions and lifecycle
public class Exam {
    private String title;
    private List<Question> questions;
    private List<Student> enrolledStudents;
    private EvaluationStrategy evaluationStrategy;
    private long durationInMillis;
    private long startTime;
    private boolean isStarted;

    public Exam(String title, EvaluationStrategy evaluationStrategy, long durationInMillis) {
        this.title = title;
        this.evaluationStrategy = evaluationStrategy;
        this.durationInMillis = durationInMillis;
        this.questions = new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();
        this.isStarted = false;
    }

    // Exam Creation: Add questions
    public void addQuestion(Question q) {
        questions.add(q);
    }

    // Student Enrollment
    public void enrollStudent(Student s) {
        enrolledStudents.add(s);
        System.out.println("Student " + s.getName() + " enrolled in " + title);
    }

    public void startExam() {
        this.startTime = System.currentTimeMillis();
        this.isStarted = true;
        System.out.println("Exam '" + title + "' started. Duration: " + (durationInMillis / 1000) + " seconds.");
    }

    // Answer Submission & Result Generation
    public void submitAnswers(Student student, Map<Integer, String> answers) {
        try {
            if (!isStarted) {
                System.out.println("Error: Exam has not started yet.");
                return;
            }

            long currentTime = System.currentTimeMillis();
            if (currentTime - startTime > durationInMillis) {
                throw new ExamTimeExpiredException("Submission failed: Time limit exceeded for student " + student.getName());
            }

            if (!enrolledStudents.contains(student)) {
                System.out.println("Error: Student " + student.getName() + " is not enrolled.");
                return;
            }
            int score = evaluationStrategy.evaluate(questions, answers);
            System.out.println("Result for " + student.getName() + ": " + score + " marks.");
        } catch (ExamTimeExpiredException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}