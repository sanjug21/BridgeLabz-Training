public class MobileDetails {
    String brand;
    String model;
    double price;
    
    public MobileDetails(String brand, String model, double price){
        this.brand=brand;
        this.model=model;
        this.price=price;
    }

    public void display(){
        System.out.println("Brand of mobile: "+brand);
        System.out.println("Model of mobile: "+model);
        System.out.println("Price of mobile: "+price);
        System.out.println("------------------------------");
    }

    public static void main(String[] args){
        MobileDetails m1=new MobileDetails("VIVO", "VIVO V29", 15999.0);
        m1.display();
        MobileDetails m2=new MobileDetails("ONE PLUS", "ONE PLUS nord4", 39999.0);
        m2.display();
        MobileDetails m3=new MobileDetails("APPLE", "iphone pro16", 79999.0);
        m3.display();
    }
}