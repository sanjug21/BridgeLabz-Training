
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnrollmentManager {
    private List<Enrollment> enrollments;

    public EnrollmentManager() {
        this.enrollments = new ArrayList<>();
    }

    // Add new enrollment
    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
        System.out.println("Enrollment added successfully for student: " + enrollment.getStudentName());
    }

    // Display all enrollments
    public void displayAllEnrollments() {
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
            return;
        }

        System.out.println("\n========== ALL ENROLLMENTS ==========");
        enrollments.forEach(System.out::println);
        System.out.println("Total Enrollments: " + enrollments.size());
        System.out.println("=====================================\n");
    }

    // Filter enrollments by specific course using Streams and Lambda
    public void filterByCourse(String courseName) {
        System.out.println("\n========== ENROLLMENTS FOR COURSE: " + courseName + " ==========");
        
        List<Enrollment> filteredEnrollments = enrollments.stream()
                .filter(e -> e.getCourseName().equalsIgnoreCase(courseName))
                .collect(Collectors.toList());

        if (filteredEnrollments.isEmpty()) {
            System.out.println("No enrollments found for course: " + courseName);
        } else {
            filteredEnrollments.forEach(System.out::println);
            System.out.println("Total: " + filteredEnrollments.size() + " enrollments");
        }
        System.out.println("==========================================================\n");
    }

    // Filter enrollments by course category using Streams and Lambda
    public void filterByCategory(String category) {
        System.out.println("\n========== ENROLLMENTS FOR CATEGORY: " + category + " ==========");
        
        List<Enrollment> filteredEnrollments = enrollments.stream()
                .filter(e -> e.getCourseCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());

        if (filteredEnrollments.isEmpty()) {
            System.out.println("No enrollments found for category: " + category);
        } else {
            filteredEnrollments.forEach(System.out::println);
            System.out.println("Total: " + filteredEnrollments.size() + " enrollments");
        }
        System.out.println("===========================================================\n");
    }

    // Group enrollments by course name using Collectors.groupingBy
    public void groupByCourse() {
        System.out.println("\n========== ENROLLMENTS GROUPED BY COURSE ==========");
        
        Map<String, List<Enrollment>> groupedByCourse = enrollments.stream()
                .collect(Collectors.groupingBy(Enrollment::getCourseName));

        if (groupedByCourse.isEmpty()) {
            System.out.println("No enrollments to group.");
        } else {
            groupedByCourse.forEach((course, enrollmentList) -> {
                System.out.println("\nCourse: " + course + " (" + enrollmentList.size() + " students)");
                enrollmentList.forEach(e -> System.out.println("  - " + e.getStudentName() + 
                        " (Enrolled: " + e.getEnrollmentDate() + ")"));
            });
        }
        System.out.println("===================================================\n");
    }

    // Count enrollments per category using Collectors.groupingBy and Collectors.counting
    public void countEnrollmentsByCategory() {
        System.out.println("\n========== ENROLLMENT COUNT BY CATEGORY ==========");
        
        Map<String, Long> countByCategory = enrollments.stream()
                .collect(Collectors.groupingBy(Enrollment::getCourseCategory, Collectors.counting()));

        if (countByCategory.isEmpty()) {
            System.out.println("No enrollments to count.");
        } else {
            countByCategory.forEach((category, count) -> 
                System.out.println(category + ": " + count + " enrollments"));
            
            System.out.println("\nTotal Categories: " + countByCategory.size());
        }
        System.out.println("==================================================\n");
    }

    // Sort and display enrollments by enrollment date using Comparator
    public void sortByEnrollmentDate() {
        System.out.println("\n========== ENROLLMENTS SORTED BY DATE ==========");
        
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments to sort.");
        } else {
            enrollments.stream()
                    .sorted(Comparator.comparing(Enrollment::getEnrollmentDate))
                    .forEach(System.out::println);
            
            System.out.println("Total: " + enrollments.size() + " enrollments");
        }
        System.out.println("================================================\n");
    }

    // Get all unique course names using Streams
    public List<String> getAllCourseNames() {
        return enrollments.stream()
                .map(Enrollment::getCourseName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    // Get all unique categories using Streams
    public List<String> getAllCategories() {
        return enrollments.stream()
                .map(Enrollment::getCourseCategory)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    // Display summary statistics using Streams
    public void displaySummaryStatistics() {
        System.out.println("\n========== ENROLLMENT SUMMARY ==========");
        
        long totalEnrollments = enrollments.size();
        long uniqueStudents = enrollments.stream()
                .map(Enrollment::getStudentName)
                .distinct()
                .count();
        long uniqueCourses = enrollments.stream()
                .map(Enrollment::getCourseName)
                .distinct()
                .count();
        long uniqueCategories = enrollments.stream()
                .map(Enrollment::getCourseCategory)
                .distinct()
                .count();

        System.out.println("Total Enrollments: " + totalEnrollments);
        System.out.println("Unique Students: " + uniqueStudents);
        System.out.println("Unique Courses: " + uniqueCourses);
        System.out.println("Unique Categories: " + uniqueCategories);
        
        if (!enrollments.isEmpty()) {
            LocalDate earliestEnrollment = enrollments.stream()
                    .map(Enrollment::getEnrollmentDate)
                    .min(LocalDate::compareTo)
                    .orElse(null);
            
            LocalDate latestEnrollment = enrollments.stream()
                    .map(Enrollment::getEnrollmentDate)
                    .max(LocalDate::compareTo)
                    .orElse(null);
            
            System.out.println("Earliest Enrollment: " + earliestEnrollment);
            System.out.println("Latest Enrollment: " + latestEnrollment);
        }
        
        System.out.println("========================================\n");
    }
}
