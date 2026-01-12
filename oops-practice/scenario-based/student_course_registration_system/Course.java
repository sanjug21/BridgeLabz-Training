package student_course_registration_system;

public class Course {
    private String courseCode;
    private String title;
    private double credits;

    public Course(String courseCode, String title, double credits) {
        this.courseCode = courseCode;
        this.title = title;
        this.credits = credits;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public double getCredits() {
        return credits;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Course course = (Course) obj;
        return courseCode.equals(course.courseCode);
    }

    @Override
    public int hashCode() {
        return courseCode.hashCode();
    }

    
}
