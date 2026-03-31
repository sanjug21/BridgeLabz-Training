package invoke_private_method;

import java.lang.reflect.Method;

public class InvokePrivateMethod {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        try {
            Method method = Calculator.class.getDeclaredMethod("multiply", int.class, int.class);
            method.setAccessible(true);
            int result = (int) method.invoke(calculator, 6, 7);
            System.out.println("6 * 7 = " + result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
