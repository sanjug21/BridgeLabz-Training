
public class Circle {
    double radius;
    public Circle() {
        this(1.0);
    }
    public Circle(double radius) {
        this.radius=radius;
    }
    public void display() {
        System.out.println("Radius: "+radius);
    }
    public static void main(String[] args) {
        // default constructor
        Circle defCircle=new Circle();
        // parameterized constructor
        Circle paramCircle=new Circle(5.0);
        defCircle.display();
        paramCircle.display();
    
    }
}