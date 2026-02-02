package custom_object_mapper;

public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    
    public Address() {}
    
    @Override
    public String toString() {
        return String.format("Address[%s, %s, %s %s, %s]",
                           street, city, state, zipCode, country);
    }
}
