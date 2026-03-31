
import java.time.LocalDate;

public class Enrollment {
    private String studentName;
    private String courseName;
    private String courseCategory;
    private LocalDate enrollmentDate;

    public Enrollment(String studentName, String courseName, String courseCategory, LocalDate enrollmentDate) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.courseCategory = courseCategory;
        this.enrollmentDate = enrollmentDate;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCategory() {
        return courseCategory;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    @Override
    public String toString() {
        return String.format("Student: %-20s | Course: %-30s | Category: %-15s | Date: %s",
                studentName, courseName, courseCategory, enrollmentDate);
    }
}
