public class InventoryItems {
    String itemCode;
    String itemName;
    double price;

    public InventoryItems(String itemCode, String itemName, double price){
        this.itemCode=itemCode;
        this.itemName=itemName;
        this.price=price;
    }

    public void display(){
        System.out.println("itemCode : "+itemCode);
        System.out.println("itemPrice : "+price);
        System.out.println("itemName : "+itemName);
        System.out.println("----------------------------");
    }

    public double calculateTotal(int qty){
        return price*qty;
    }

    public static void main(String[] args){
        InventoryItems i1=new InventoryItems("01AA", "Water bottle", 500.0);
        i1.display();
        InventoryItems i2=new InventoryItems("01BB", "Rice", 700.0);
        i2.display();
        InventoryItems i3=new InventoryItems("02AA", "blackboard", 400.0);
        i3.display();
    }
}