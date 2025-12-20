import java.util.Scanner;

public class NumberDivisible {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int number=sc.nextInt();
        if(number%5==0){
            System.out.println("Number "+number+" Divisible by 5");
        } else {
            System.out.println("Number "+number+" Not Divisible by 5");
        }
        sc.close();
    }
}
