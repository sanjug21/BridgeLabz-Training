package dynamically_create_objects;

import java.lang.reflect.Constructor;

public class DynamicallyCreateObjects {

    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("dynamically_create_objects.Student");
            Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, String.class);
            Object student = constructor.newInstance("STU001", "Alice Johnson");
            System.out.println(student);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
