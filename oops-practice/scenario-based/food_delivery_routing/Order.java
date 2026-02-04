
class Order {
    private String orderId;
    private String customerName;
    private String restaurant;
    private double latitude;
    private double longitude;
    private boolean assigned;

    public Order(String orderId, String customerName, String restaurant, double latitude, double longitude) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.restaurant = restaurant;
        this.latitude = latitude;
        this.longitude = longitude;
        this.assigned = false;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    @Override
    public String toString() {
        return "Order{" +
               "orderId='" + orderId + '\'' +
               ", customerName='" + customerName + '\'' +
               ", restaurant='" + restaurant + '\'' +
               ", location=(" + latitude + ", " + longitude + ")" +
               ", assigned=" + assigned +
               '}';
    }
}
