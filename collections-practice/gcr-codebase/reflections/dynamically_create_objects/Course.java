package dynamically_create_objects;

public class Course {
    private String courseId;
    private String courseName;
    private int credits;
    
    public Course() {
        this.courseId = "COURSE-000";
        this.courseName = "Unnamed Course";
        this.credits = 0;
    }
    
    public Course(String courseId, String courseName, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
    }
    
    @Override
    public String toString() {
        return String.format("Course[ID=%s, Name=%s, Credits=%d]", courseId, courseName, credits);
    }
}
