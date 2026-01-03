class Courses {
    String courseName;
    String duration;

    public Courses(String courseName, String duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    public void displayDetails() {
        System.out.println("Course Name: " + courseName);
        System.out.println("Duration: " + duration);
    }
}

class OnlineCourse extends Courses {
    String platform;
    boolean isRecorded;

    public OnlineCourse(String courseName, String duration, String platform, boolean isRecorded) {
        super(courseName, duration);
        this.platform = platform;
        this.isRecorded = isRecorded;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Platform: " + platform);
        System.out.println("Is Recorded: " + (isRecorded ? "Yes" : "No"));
    }
}

class PaidOnlineCourse extends OnlineCourse {
    double fee;
    double discount;

    public PaidOnlineCourse(String courseName, String duration, String platform, boolean isRecorded, double fee, double discount) {
        super(courseName, duration, platform, isRecorded);
        this.fee = fee;
        this.discount = discount;
    }
    // overriding displayDetails method from OnlineCourse
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Fee: " + fee);
        System.out.println("Discount: " + discount + "%");
    }
}

public class CourseHierarchy {
    public static void main(String[] args) {
       // multi-level inheritance demonstration

       // creating an instance of course
        Courses course = new Courses("Introduction to Programming", "3 months");
        course.displayDetails();
        System.out.println("\n\n");

         // creating an instance of online course
        OnlineCourse onlineCourse = new OnlineCourse("Data Structures", "4 months", "Udemy", true);
        onlineCourse.displayDetails();
        System.out.println("\n\n");

         // creating an instance of paid online course
        Courses paidOnlineCourse = new PaidOnlineCourse("Machine Learning", "6 months", "Coursera", false, 499.99, 10);
        paidOnlineCourse.displayDetails();
    }
}