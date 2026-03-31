package hotel_management_system;

// OOP: Guest class
public class Guest {
    private String name;
    private String contactNumber;

    public Guest(String name, String contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public String getName() { return name; }
    
    @Override
    public String toString() {
        return name + " (" + contactNumber + ")";
    }
}