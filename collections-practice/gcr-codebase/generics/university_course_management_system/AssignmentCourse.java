
public class AssignmentCourse extends CourseType {
    private int numberOfAssignments;

    public AssignmentCourse(String name, int numberOfAssignments) {
        super(name);
        this.numberOfAssignments = numberOfAssignments;
    }

    @Override
    public String getEvaluationType() {
        return "Assignment-Based (" + numberOfAssignments + " assignments)";
    }
}
