public class BrickTransport extends GoodsTransport {
    private float brickSize;
    private int brickQuantity;
    private float brickPrice;

    BrickTransport(String transportId, String tranportDate, int tranportRating, float brickSize, int brickQuantity,
            float brickPrice) {
        super(transportId, tranportDate, tranportRating);
        this.brickSize = brickSize;
        this.brickQuantity = brickQuantity;
        this.brickPrice = brickPrice;
    }

    // getter and setter methods for brickSize, brickQuantity, and brickPrice
    public float getBrickSize() {
        return brickSize;
    }

    public void setBrickSize(float brickSize) {
        this.brickSize = brickSize;
    }

    public int getBrickQuantity() {
        return brickQuantity;
    }

    public void setBrickQuantity(int brickQuantity) {
        this.brickQuantity = brickQuantity;
    }

    public float getBrickPrice() {
        return brickPrice;
    }

    public void setBrickPrice(float brickPrice) {
        this.brickPrice = brickPrice;
    }

    @Override
    public String vehicleSelection() {
        return Vehicle.getVehicleTypeForBrickTransport(brickQuantity);
    }


    @Override
    public double calculateTotalCharge() {
        double totalBrickCost = brickPrice * brickQuantity;
        String vehicleType = vehicleSelection();
        double vehiclePrice = Vehicle.getVehiclePrice(vehicleType);
        double tax = 0.3 * totalBrickCost;
        double discount = 0;
        if (tranportRating > 4) {
            discount = 0.2 * totalBrickCost;
        } else if (tranportRating >2) {
            discount = 0.1 * totalBrickCost;
        }
        double totalCharge = totalBrickCost + vehiclePrice + tax - discount;
        return totalCharge;
    }

}
