

public class Electronics extends WarehouseItem {
    private int warrantyMonths;

    public Electronics(String name, double price, int warrantyMonths) {
        super(name, price);
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Warranty: %d months", warrantyMonths);
    }
}
