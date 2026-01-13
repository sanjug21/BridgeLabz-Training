package online_examination_system;

// OOP: Question class
class Question {
    private int id;
    private String text;
    private String correctAnswer; // Used for objective checking
    private int marks;

    public Question(int id, String text, String correctAnswer, int marks) {
        this.id = id;
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.marks = marks;
    }

    public int getId() { return id; }
    public String getText() { return text; }
    public String getCorrectAnswer() { return correctAnswer; }
    public int getMarks() { return marks; }

    @Override
    public String toString() {
        return id + ". " + text + " [Marks: " + marks + "]";
    }
}