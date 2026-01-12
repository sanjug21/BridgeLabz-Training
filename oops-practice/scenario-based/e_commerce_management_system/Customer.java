package e_commerce_management_system;

public class Customer {

    private String customerId;
    private String name;
    private String email;
    private String address;

    public Customer(String customerId, String name, String email, String address) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void updateAddress(String newAddress) {
        this.address = newAddress;
        System.out.println("Address updated for " + name + " to " + newAddress);
    }
    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}