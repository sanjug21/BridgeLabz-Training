import java.util.*;

public class StudentVoteChecker {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int[] ages=new int[10];
        StudentVoteChecker checker=new StudentVoteChecker();
        
        for(int i=0;i<10;i++){
            System.out.print("Enter age for student "+(i+1)+": ");
            ages[i]=sc.nextInt();
            if(checker.canStudentVote(ages[i]))System.out.println("Student can vote.");
            else System.out.println("Student cannot vote.");
        }
        sc.close();
    }

    public boolean canStudentVote(int age){
        if(age<0) return false;
        return age>=18;
    }
}