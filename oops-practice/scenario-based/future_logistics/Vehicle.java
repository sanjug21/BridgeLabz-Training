enum Vehicle {
    TRUCK("Truck",1000.00),
    LORRY("Lorry",1700.00),
    MONSTER_LORRY("Monster Lorry",3000.00);
   
    private final double price;
    private final String type;
    private Vehicle(String type, double price) {
        this.type = type;
        this.price = price;
    }
    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
    // this method returns vehicle price based on vehicle type
    public static double getVehiclePrice(String vehicleType) {
        switch (vehicleType.toUpperCase()) {
            case "TRUCK":
                return TRUCK.getPrice();
            case "LORRY":
                return LORRY.getPrice();
            case "MONSTER LORRY":
                return MONSTER_LORRY.getPrice();
            default:
                return 0.0;
        }
    }
    // this method returns vehicle type based on brick quantity for BrickTransport
    public static String getVehicleTypeForBrickTransport(int brickQuantity) {
        if(brickQuantity < 300) {
            return TRUCK.getType();
        } else if (brickQuantity >= 300 && brickQuantity <= 500) {
            return LORRY.getType();
        } else {
            return MONSTER_LORRY.getType();
        }
    }

    // this method returns vehicle type based on volume for TimberTransport
    public static String getVehicleTypeForTimberTransport(double area) {
        if(area < 250) {
            return TRUCK.getType();
        } else if (area >= 250 && area <= 400) {
            return LORRY.getType();
        } else {
            return MONSTER_LORRY.getType();
        }
    }
}
