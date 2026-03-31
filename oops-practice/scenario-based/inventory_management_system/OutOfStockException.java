package inventory_management_system;

public class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }
}
