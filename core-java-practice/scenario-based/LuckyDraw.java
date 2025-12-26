import java.util.*;

public class LuckyDraw {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Festival Lucky Draw ");

        while(true){
            System.out.print("\nEnter your lucky number (or -1 to exit): ");

            // check for invalid input for number  
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
                continue;
            }

            int number=sc.nextInt();

            if(number==-1){
                System.out.println("Lucky draw ended.");
                break;
            }

            // Check if number is divisible by 3 and 5  
            if(number%3==0 && number%5==0){
                System.out.println("Congratulations! You win a gift.");
            }else{
                System.out.println("Better luck next time.");
            }
        }
        sc.close();
    }
}