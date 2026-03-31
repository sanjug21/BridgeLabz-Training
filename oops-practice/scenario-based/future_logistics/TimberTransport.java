public class TimberTransport extends GoodsTransport{
    private float timberLength;
    private float timberRadius;
    private String timberType;
    private float timberPrice;

    TimberTransport(String transportId, String tranportDate, int tranportRating, float timberLength,
            float timberRadius, String timberType, float timberPrice) {
        super(transportId, tranportDate, tranportRating);
        this.timberLength = timberLength;
        this.timberRadius = timberRadius;
        this.timberType = timberType;
        this.timberPrice = timberPrice;
    }

    // getter and setter methods for timberLength, timberRadius, timberType, and timberPrice
    public float getTimberLength() {
        return timberLength;
    }
    public void setTimberLength(float timberLength){
        this.timberLength=timberLength;
    }
    public float getTimberRadius(){
        return timberRadius;
    }
    public void setTimberRadius(float timberRadius){
        this.timberRadius=timberRadius;
    }
    public String getTimberType(){
        return timberType;
    }
    public void setTimberType(String timberType){
        this.timberType=timberType;
    }
    public float getTimberPrice(){
        return timberPrice;
    }
    public void setTimberPrice(float timberPrice){
        this.timberPrice=timberPrice;
    }
    @Override
    public String vehicleSelection() {
        double area=2*3.147*timberRadius*timberLength;
        return Vehicle.getVehicleTypeForTimberTransport(area);
    }

    @Override
    public double calculateTotalCharge(){
        double volume=3.147*timberRadius*timberRadius*timberLength;
        double timberTypePrice=timberType.equalsIgnoreCase("Premium")?0.25:.15;
        double price=volume*timberPrice*timberTypePrice;
        double tax=0.3*price;
        double discount = 0;
        if (tranportRating > 4) {
            discount = 0.2 *price;
        } else if (tranportRating > 2) {
            discount = 0.1 * price;
        }
        String vehicleType=vehicleSelection();
        double vehiclePrice=Vehicle.getVehiclePrice(vehicleType);
        double totalCharge=price+vehiclePrice+tax-discount;
        return totalCharge;
    }

}
