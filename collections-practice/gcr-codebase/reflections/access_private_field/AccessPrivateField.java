package access_private_field;

import java.lang.reflect.Field;

public class AccessPrivateField {

    public static void main(String[] args) {
        Person person = new Person("John Doe", 30);

        try {
            Field ageField = Person.class.getDeclaredField("age");
            ageField.setAccessible(true);

            int age = (int) ageField.get(person);
            System.out.println("Original age: " + age);

            ageField.set(person, 35);
            System.out.println("Updated age: " + ageField.get(person));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
