public class Products{
    public static double discount=12;
    public String productName;
    public double price;
    public int quantity;
    public final int productId;
     Products(int productId,String productName,double price,int quantity){
        this.productId=productId;
        this.productName=productName;
        this.price=price;
        this.quantity=quantity;
     }
    public double calculateTotalPrice(){
        double totalPrice=price*quantity;
        double discountAmount=(totalPrice*discount)/100;
        return totalPrice-discountAmount;
    }

    // display method to display product details
    public void displayDetails(){
        System.out.println("Product id: "+productId);
        System.out.println("Product name: "+productName);
        System.out.println("Product price: "+price);
        System.out.println("Quantity: "+quantity);
        System.out.println("Discount: "+discount+"%");
        System.out.println("Total price after discount: "+calculateTotalPrice());
    }

    public static void main(String[] args) {
        Products p1=new Products(101,"Laptop",50000,2);
        Products p2=new Products(102,"Smartphone",20000,3);
        Products p3=new Products(103,"Tablet",15000,1);

        // instance method calls
        System.out.println("p1 is instanceof Products: "+(p1 instanceof Products));
        System.out.println("p2 is instanceof Products: "+(p2 instanceof Products));
        System.out.println("p3 is instanceof Products: "+(p3 instanceof Products));

        // displaying products
        p1.displayDetails();
        p2.displayDetails();
        p3.displayDetails();
    

    }    

}