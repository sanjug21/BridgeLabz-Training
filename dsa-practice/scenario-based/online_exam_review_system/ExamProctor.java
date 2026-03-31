package online_exam_review_system;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExamProctor {

    // Database of questions (QuestionID -> Question Object)
    private Map<Integer, Question> questionDatabase;

    // Requirement 1: Track navigation with Stack (Stores Question IDs)
    private Stack<Integer> navigationHistory;

    // Requirement 2: Store answers in a Map (QuestionID -> Student's Answer)
    private Map<Integer, String> studentAnswers;

    public ExamProctor() {
        this.questionDatabase = new HashMap<>();
        this.navigationHistory = new Stack<>();
        this.studentAnswers = new HashMap<>();
        loadExamQuestions();
    }

    // method to print question database

    public void displayQuestionDatabase() {
        System.out.println("\n--- Question Database ---");
        for (Map.Entry<Integer, Question> entry : questionDatabase.entrySet()) {
            System.out.println("ID: " + entry.getKey() + ", Question: " + entry.getValue().getText());
        }
    }

    // Helper method to load dummy exam data
    private void loadExamQuestions() {
        questionDatabase.put(101,
                new Question(101, "What is the time complexity of accessing an array element?", "O(1)", 10));
        questionDatabase.put(102, new Question(102, "Which data structure follows LIFO?", "Stack", 10));
        questionDatabase.put(103, new Question(103, "What is the base class of all classes in Java?", "Object", 10));
        questionDatabase.put(104,
                new Question(104, "Which map implementation maintains insertion order?", "LinkedHashMap", 10));
    }

    
     //Simulates the student navigating to a specific question.
     // Uses Stack to record the history.
     
    public void navigateToQuestion(int questionId) {
        if (questionDatabase.containsKey(questionId)) {
            navigationHistory.push(questionId);
            System.out.println("[Navigation] Visited Question ID: " + questionId + " ("
                    + questionDatabase.get(questionId).getText() + ")");
        } else {
            System.out.println("[Error] Question ID " + questionId + " does not exist.");
        }
    }

    
    //  Simulates the student pressing a 'Back' button.
    // Demonstrates Stack LIFO behavior.
     
    public void goBack() {
        if (!navigationHistory.isEmpty()) {
            navigationHistory.pop(); // Remove current view

            if (!navigationHistory.isEmpty()) {
                int previousId = navigationHistory.peek(); // Look at previous
                System.out.println("[Navigation] Went back. Now viewing Question ID: " + previousId);
            } else {
                System.out.println("[Navigation] Went back. You are at the start of the exam.");
            }
        } else {
            System.out.println("[Navigation] No history to go back to.");
        }
    }

    
    // Records the student's answer.
    // Uses HashMap to store the data.
    
    public void submitAnswer(int questionId, String answer) {
        if (questionDatabase.containsKey(questionId)) {
            studentAnswers.put(questionId, answer);
            System.out.println("[Action] Answer saved for Question " + questionId + ": \"" + answer + "\"");
        } else {
            System.out.println("[Error] Cannot answer invalid Question ID " + questionId);
        }
    }

   
    //   Requirement 3: Evaluate using functions for scoring logic.
    //   Iterates through the student's answers and compares them with the correct
    //   answers.
     
    public int calculateScore() {
        System.out.println("\n--- Calculating Final Score ---");
        int totalScore = 0;

        for (Map.Entry<Integer, String> entry : studentAnswers.entrySet()) {
            int qId = entry.getKey();
            String studentAns = entry.getValue();
            Question q = questionDatabase.get(qId);

            // Case-insensitive comparison
            if (q != null && q.getCorrectAnswer().equalsIgnoreCase(studentAns)) {
                System.out.println("Question " + qId + ": Correct! (+" + q.getPoints() + " pts)");
                totalScore += q.getPoints();
            } else {
                System.out.println("Question " + qId + ": Incorrect. (Expected: "
                        + (q != null ? q.getCorrectAnswer() : "N/A") + ")");
            }
        }
        return totalScore;
    }
}
