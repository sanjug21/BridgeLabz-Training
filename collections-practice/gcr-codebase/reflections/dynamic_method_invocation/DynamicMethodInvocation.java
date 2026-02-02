package dynamic_method_invocation;

import java.lang.reflect.Method;
import java.util.Scanner;

public class DynamicMethodInvocation {

    public static void main(String[] args) {
        MathOperations mathOps = new MathOperations();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter method (add/subtract/multiply): ");
        String methodName = scanner.nextLine().trim();

        System.out.print("Enter first number: ");
        int a = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter second number: ");
        int b = Integer.parseInt(scanner.nextLine());

        try {
            Method method = MathOperations.class.getMethod(methodName, int.class, int.class);
            int result = (int) method.invoke(mathOps, a, b);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
