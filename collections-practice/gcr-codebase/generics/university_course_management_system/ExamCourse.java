
public class ExamCourse extends CourseType {
    private int examWeight;

    public ExamCourse(String name, int examWeight) {
        super(name);
        this.examWeight = examWeight;
    }

    @Override
    public String getEvaluationType() {
        return "Exam-Based (" + examWeight + "% of final grade)";
    }
}
