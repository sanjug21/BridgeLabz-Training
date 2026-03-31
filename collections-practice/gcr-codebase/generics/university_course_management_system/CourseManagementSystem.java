import java.util.ArrayList;
import java.util.List;

public class CourseManagementSystem {
    private List<Course<? extends CourseType>> courses;

    public CourseManagementSystem() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course<? extends CourseType> course) {
        this.courses.add(course);
    }

    public void displayCourses() {
        for (Course<?> course : courses) {
            System.out.println(course);
        }
    }

    public static void main(String[] args) {
        CourseManagementSystem cms = new CourseManagementSystem();

        // Create some courses
        ExamCourse examCourse = new ExamCourse("Midterm Exam", 60);
        Course<ExamCourse> introToCS = new Course<>("CS101", "Introduction to Computer Science", examCourse);

        AssignmentCourse assignmentCourse = new AssignmentCourse("Weekly Assignments", 10);
        Course<AssignmentCourse> linearAlgebra = new Course<>("MA201", "Linear Algebra", assignmentCourse);

        ResearchCourse researchCourse = new ResearchCourse("Literature Review", "Artificial Intelligence");
        Course<ResearchCourse> aiFundamentals = new Course<>("AI301", "AI Fundamentals", researchCourse);

        cms.addCourse(introToCS);
        cms.addCourse(linearAlgebra);
        cms.addCourse(aiFundamentals);

        // Display all courses
        cms.displayCourses();
    }
}
