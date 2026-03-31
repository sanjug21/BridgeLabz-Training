
public class Course<T extends CourseType> {
    private String courseCode;
    private String courseName;
    private T courseType;

    public Course(String courseCode, String courseName, T courseType) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseType = courseType;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public T getCourseType() {
        return courseType;
    }

    public String getEvaluationType() {
        return courseType.getEvaluationType();
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + ", Name: " + courseName + ", Evaluation: " + getEvaluationType();
    }
}
