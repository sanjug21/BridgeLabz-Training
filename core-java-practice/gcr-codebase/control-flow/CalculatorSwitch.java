import java.util.Scanner;

public class CalculatorSwitch {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        double first=sc.nextDouble();
        double second=sc.nextDouble();
        String operator=sc.next();
        
        switch(operator){
            case "+":
                System.out.println(first+second);
                break;
            case "-":
                System.out.println(first-second);
                break;
            case "*":
                System.out.println(first*second);
                break;
            case "/":
                System.out.println(first/second);
                break;
            default:
                System.out.println("Invalid Operator");
        }
        sc.close();
    }
}