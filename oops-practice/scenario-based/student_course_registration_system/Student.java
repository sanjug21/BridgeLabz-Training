package student_course_registration_system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends Person {
    private String studentId;
    private List<Course> enrolledCourses;
    private Map<String, Double> grades;
    private static final int MAX_COURSES = 3;

    public Student(String name, int age, String studentId) {
        super(name, age);
        this.studentId = studentId;
        this.enrolledCourses = new ArrayList<>();
        this.grades = new HashMap<>();
    }

    public String getStudentId() { return studentId; }
    public List<Course> getEnrolledCourses() { return enrolledCourses; }

    public void addCourse(Course course) throws CourseLimitExceededException {
        if (enrolledCourses.size() >= MAX_COURSES) {
            throw new CourseLimitExceededException("Student " + getName() + " cannot enroll in more than " + MAX_COURSES + " courses.");
        }
        enrolledCourses.add(course);
        System.out.println(getName() + " enrolled in " + course.getTitle());
    }

    public void removeCourse(Course course) {
        if (enrolledCourses.remove(course)) {
            grades.remove(course.getCourseCode());
            System.out.println(getName() + " dropped " + course.getTitle());
        } else {
            System.out.println("Course not found in enrollment list.");
        }
    }

    public void assignGrade(Course course, double grade) {
        if (enrolledCourses.contains(course)) {
            grades.put(course.getCourseCode(), grade);
            System.out.println("Grade " + grade + " assigned to " + getName() + " for " + course.getTitle());
        } else {
            System.out.println("Cannot assign grade. Student is not enrolled in " + course.getTitle());
        }
    }

    public void viewGrades() {
        System.out.println("\nGrades for " + getName() + ":");
        if (enrolledCourses.isEmpty()) {
            System.out.println("No courses enrolled.");
        } else {
            for (Course course : enrolledCourses) {
                Double grade = grades.get(course.getCourseCode());
                System.out.println("- " + course.getTitle() + ": " + (grade != null ? grade : "Not Graded"));
            }
        }
    }
}