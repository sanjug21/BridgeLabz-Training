public class ShoppingCart{
    String itemName;
    double price;
    int quantity;

    public ShoppingCart(String itemName, double price, int quantity ){
        this.itemName=itemName;
        this.price=price;
        this.quantity=quantity;
    }

    public void add(int quantity){
        this.quantity+=quantity;
        System.out.println("Added "+quantity+" of "+itemName+" to the cart.");
    }

    public void remove(int quantity){
        if(quantity>=quantity){
            this.quantity-=quantity;
            System.out.println("Removed "+quantity+" of "+itemName+" from the cart.");
        }else{
            System.out.println("Cannot remove, insufficient quantity.");
        }
    }

    public void displayTotal(){
        double total=price*quantity;
        System.out.println("Total cost: $"+total);
    }

    public static void main(String[] args){
        ShoppingCart item=new ShoppingCart("Laptop", 999.99, 1);
        System.out.println("Item: "+item.itemName+", Price: $"+item.price+", Quantity: "+item.quantity);
        item.add(2);
        item.remove(1);
        item.displayTotal();
    }
}