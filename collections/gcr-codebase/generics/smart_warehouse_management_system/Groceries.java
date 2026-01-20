
public class Groceries extends WarehouseItem {
    private String expirationDate;

    public Groceries(String name, double price, String expirationDate) {
        super(name, price);
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return super.toString() + " | Expires: " + expirationDate;
    }
}
