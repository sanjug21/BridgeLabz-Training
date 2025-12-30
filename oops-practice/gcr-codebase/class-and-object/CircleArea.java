public class CircleArea {
    
    double radius;

    public CircleArea(double r){
        this.radius=r;
    }

    public void calculateAndDisplay(){
        double area=3.14*radius*radius;
        double circumference=2*3.14*radius;
        System.out.println("Circumference of Circle with radius "+radius+" is: "+circumference);
        System.out.println("Area of Circle with radius "+radius+" is: "+area);
        
    }

    public static void main(String[] args){
        CircleArea c=new CircleArea(2.5);
        c.calculateAndDisplay();
    }
}