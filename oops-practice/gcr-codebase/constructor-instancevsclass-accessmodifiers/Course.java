
public class Course {
    String courseName;
    int duration;
    double fee;
    // made static to keep track across all instances of course class
    static String instituteName="GLA University";

    public Course(String courseName,int duration,double fee) {
        this.courseName=courseName;
        this.duration=duration;
        this.fee=fee;
    }
    public void displayCourseDetails() {
        System.out.println("Course: "+courseName+", Duration: "+duration+", Fee: "+fee+", Institute: "+instituteName);
    }
    public static  void updateInstituteName(String newInstituteName) {
        instituteName=newInstituteName;
    }
    public static void main(String[] args) {
        // created 3 course objects
        Course c1=new Course("B.Tech",4,400000);
        Course c2=new Course("MBA",2,200000);
        Course c3=new Course("MCA",3,300000);

        // displaying details of each course
        c1.displayCourseDetails();
        c2.displayCourseDetails();
        c3.displayCourseDetails();
        // displaying current institute name
        System.out.println("Institute Name: "+instituteName);
        // updating institute name
        updateInstituteName("BITS Pilani");
        System.out.println("Updated Institute Name: "+instituteName);
    }
}