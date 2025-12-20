import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int n=sc.nextInt();

        int[] physics=new int[n];
        int[] chemistry=new int[n];
        int[] maths=new int[n];
        double[] percentage=new double[n];
        String[] grade=new String[n];

        for(int i=0;i<n;i++){
            System.out.print("Enter marks for Physics, Chemistry, Maths for student "+(i+1)+": ");
            int p=sc.nextInt();
            int c=sc.nextInt();
            int m=sc.nextInt();

            // Validation for positive marks
            if(p<0 || c<0 || m<0){
                System.out.println("Invalid marks. Enter positive values.");
                i--;
                continue;
            }
            physics[i]=p;
            chemistry[i]=c;
            maths[i]=m;

            // Calculate avg
            percentage[i]=(p+c+m)/3.0;
            if(percentage[i]>=80) grade[i]="A";
            else if(percentage[i]>=70) grade[i]="B";
            else if(percentage[i]>=60) grade[i]="C";
            else if(percentage[i]>=50) grade[i]="D";
            else if(percentage[i]>=40) grade[i]="E";
            else grade[i]="R";

            System.out.println("Student "+(i+1)+": Marks: P="+physics[i]+" C="+chemistry[i]+" M="+maths[i]+", Percentage="+percentage[i]+", Grade="+grade[i]);
        }
        sc.close();
    }
}