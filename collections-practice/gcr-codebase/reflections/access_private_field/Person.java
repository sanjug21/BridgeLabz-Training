package access_private_field;

public class Person {
    private String name;
    private int age;
    private String email;
    private double salary;
    
    public Person() {
        this.name = "Unknown";
        this.age = 0;
    }
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public Person(String name, int age, String email, double salary) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.salary = salary;
    }
    
    // Public getter
    public int getAge() {
        return age;
    }
    
    // Public setter with validation
    public void setAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Invalid age: " + age);
        }
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return String.format("Person[name=%s, age=%d, email=%s, salary=%.2f]", 
                           name, age, email != null ? email : "N/A", salary);
    }
}
