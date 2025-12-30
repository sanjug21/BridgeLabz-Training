public class StudentReport {
    String name;
    String rollNumber;
    double[] marks;

    public StudentReport(String name, String rollNumber, double[] marks){
        this.name=name;
        this.rollNumber=rollNumber;
        this.marks=marks;
    }

    public char calculateGrade(){
        double sum=0;
        for(double m:marks){
            sum+=m;
        }
        double avg=sum/marks.length;
        if(avg>=80)return 'A';
        else if(avg>=70)return 'B';
        else if(avg>=50)return 'C';
        else return 'F';
    }

    public void display(){
        System.out.println("Student Name: "+name);
        System.out.println("Student RollNumber: "+rollNumber);
        System.out.println("Student Marks:");
        for(int i=0;i<marks.length;i++){
            System.out.println("Mark"+(i+1)+": "+marks[i]);
        }
        System.out.println("Grade "+calculateGrade());
    }

    public static void main(String[] args){
        StudentReport s1=new StudentReport("Thamarai", "ECE001", new double[]{80.0, 70.0, 75.0});
        s1.display();
        StudentReport s2=new StudentReport("Kannan", "CSC002", new double[]{60.0, 65.0, 50.0});
        s2.display();
    }
}