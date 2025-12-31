
public class Person {
    String name;
    int age;
    public Person(String name,int age) {
        this.name=name;
        this.age=age;
    }
    public Person(Person other) {
        this.name=other.name;
        this.age=other.age;
    }
    public void display() {
        System.out.println("Name: "+name+", Age: "+age);
    }
    public static void main(String[] args) {
        // creating useing parameterized constructor
        Person p1=new Person("Sanju",24);
        // creating copy using copy constructor
        Person p2=new Person(p1);
        // displaying details
        p1.display();
        p2.display();
    }
}