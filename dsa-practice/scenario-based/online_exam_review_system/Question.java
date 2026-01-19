package online_exam_review_system;

public class Question {
    private int id;
    private String text;
    private String correctAnswer;
    private int points;

    public Question(int id, String text, String correctAnswer, int points) {
        this.id = id;
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public int getPoints() {
        return points;
    }
}
