package get_class_information;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

public class GetClassInformation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter class name: ");
        String className = scanner.nextLine().trim();

        try {
            Class<?> clazz = Class.forName(className);
            System.out.println("Class: " + clazz.getName());

            System.out.println("\nConstructors:");
            for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
                System.out.println("  " + constructor);
            }

            System.out.println("\nFields:");
            for (Field field : clazz.getDeclaredFields()) {
                System.out.println("  " + field.getType().getSimpleName() + " " + field.getName());
            }

            System.out.println("\nMethods:");
            for (Method method : clazz.getDeclaredMethods()) {
                System.out.println("  " + method.getName());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Class '" + className + "' not found!");
            System.out.println("Please provide the fully qualified class name.");
        } finally {
            scanner.close();
        }
    }
}
