import java.util.*;

public class NumberCheck1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number=sc.nextInt();
        
        NumberCheck1 numberCheck=new NumberCheck1();
        // Check the number weather it is positive negative or zero
        int result=numberCheck.checkNumber(number);
        
        if(result==1){
            System.out.println("The number is positive.");
        }else if(result==-1){
            System.out.println("The number is negative.");
        }else{
            System.out.println("The number is zero.");
        }
        sc.close();
    }

    public int checkNumber(int number){
        if(number>0) return 1;
        else if(number<0) return -1;
        else return 0;
    }
}