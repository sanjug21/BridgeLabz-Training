import java.util.Scanner;

public class Average {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int num1=sc.nextInt();
        int num2=sc.nextInt();
        int num3=sc.nextInt();
        int avgerageOfThreeNumbers=(num1+num2+num3)/3;
        System.out.println(avgerageOfThreeNumbers);
        sc.close();
    }
    
}
