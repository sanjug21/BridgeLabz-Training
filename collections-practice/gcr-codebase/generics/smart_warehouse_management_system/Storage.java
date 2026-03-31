
import java.util.ArrayList;
import java.util.List;


public class Storage<T extends WarehouseItem> {
    private List<T> inventory;

    public Storage() {
        this.inventory = new ArrayList<>();
    }

    public void addItem(T item) {
        inventory.add(item);
        System.out.println("Added to storage: " + item.getName());
    }

    public T getItem(int index) {
        if (index >= 0 && index < inventory.size()) {
            return inventory.get(index);
        }
        return null;
    }

    public List<T> getInventory() {
        return inventory;
    }
}
